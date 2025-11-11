package io.github.bhuyanp.order.interceptor;

import io.github.bhuyanp.order.dto.Order;
import io.temporal.common.interceptors.*;
import io.temporal.failure.ApplicationFailure;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@Component
public class OrderWorkerInterceptor extends WorkerInterceptorBase {

    @Override
    public ActivityInboundCallsInterceptor interceptActivity(ActivityInboundCallsInterceptor next) {
        return new OrderActivityInterceptor(next);
    }

    @Override
    public WorkflowInboundCallsInterceptor interceptWorkflow(WorkflowInboundCallsInterceptor next) {
        return new OrderWorkflowInterceptor(next);
    }


    static class OrderActivityInterceptor extends ActivityInboundCallsInterceptorBase {
        // Lazy loading to avoid cyclic dependency
        public OrderActivityInterceptor(@Lazy ActivityInboundCallsInterceptor next) {
            super(next);
        }

        @Override
        public ActivityOutput execute(ActivityInput activityInput) {
            try {
                Arrays.stream(activityInput.getArguments()).filter(it -> it instanceof Order).findFirst()
                        .ifPresent(order -> MDC.put("orderId", ((Order) order).orderId().toString()));
                return super.execute(activityInput);
            } catch (WebClientResponseException e) {
                log.error("WebClientResponseException: {}", e.getResponseBodyAsString());
                if (e.getStatusCode().is4xxClientError()) {
                    throw ApplicationFailure.newNonRetryableFailureWithCause(e.getMessage(), e.getClass().getSimpleName(), e.getCause(), e.getResponseBodyAs(ProblemDetail.class));
                } else {
                    throw ApplicationFailure.newFailureWithCause(e.getMessage(), e.getClass().getSimpleName(), e.getCause(), e.getResponseBodyAs(ProblemDetail.class));
                }
            } finally {
                MDC.clear();
            }
        }
    }

    static class OrderWorkflowInterceptor extends WorkflowInboundCallsInterceptorBase {
        // Lazy loading to avoid cyclic dependency
        public OrderWorkflowInterceptor(@Lazy WorkflowInboundCallsInterceptor next) {
            super(next);
        }

        public WorkflowOutput execute(WorkflowInput input) {
            Arrays.stream(input.getArguments()).filter(it -> it instanceof Order).findFirst()
                    .ifPresent(order -> MDC.put("orderId", ((Order) order).orderId().toString()));
            try {
                return super.execute(input);
            } finally {
                MDC.clear();
            }
        }

    }

}
