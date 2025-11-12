package io.github.bhuyanp.notification.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * OrderCompletionNotification
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-11T19:36:08.257033-05:00[America/New_York]", comments = "Generator version: 7.16.0")
public class OrderCompletionNotification {

    private UUID orderId;

    private UUID shippingTrackingNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime orderPlaced;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime orderCompleted;

    private String customerId;

    public OrderCompletionNotification() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public OrderCompletionNotification(UUID orderId, UUID shippingTrackingNumber, LocalDateTime orderPlaced, LocalDateTime orderCompleted, String customerId) {
        this.orderId = orderId;
        this.shippingTrackingNumber = shippingTrackingNumber;
        this.orderPlaced = orderPlaced;
        this.orderCompleted = orderCompleted;
        this.customerId = customerId;
    }

    public OrderCompletionNotification orderId(UUID orderId) {
        this.orderId = orderId;
        return this;
    }

    /**
     * Get orderId
     *
     * @return orderId
     */
    @NotNull
    @JsonProperty("orderId")
    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public OrderCompletionNotification shippingTrackingNumber(UUID shippingTrackingNumber) {
        this.shippingTrackingNumber = shippingTrackingNumber;
        return this;
    }

    /**
     * Get shippingTrackingNumber
     *
     * @return shippingTrackingNumber
     */
    @NotNull
    @JsonProperty("shippingTrackingNumber")
    public UUID getShippingTrackingNumber() {
        return shippingTrackingNumber;
    }

    public void setShippingTrackingNumber(UUID shippingTrackingNumber) {
        this.shippingTrackingNumber = shippingTrackingNumber;
    }

    public OrderCompletionNotification orderPlaced(LocalDateTime orderPlaced) {
        this.orderPlaced = orderPlaced;
        return this;
    }

    /**
     * Get orderPlaced
     *
     * @return orderPlaced
     */
    @NotNull
    @JsonProperty("orderPlaced")
    public LocalDateTime getOrderPlaced() {
        return orderPlaced;
    }

    public void setOrderPlaced(LocalDateTime orderPlaced) {
        this.orderPlaced = orderPlaced;
    }

    public OrderCompletionNotification orderCompleted(LocalDateTime orderCompleted) {
        this.orderCompleted = orderCompleted;
        return this;
    }

    /**
     * Get orderCompleted
     *
     * @return orderCompleted
     */
    @NotNull
    @JsonProperty("orderCompleted")
    public LocalDateTime getOrderCompleted() {
        return orderCompleted;
    }

    public void setOrderCompleted(LocalDateTime orderCompleted) {
        this.orderCompleted = orderCompleted;
    }

    public OrderCompletionNotification customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    /**
     * Get customerId
     *
     * @return customerId
     */
    @NotNull
    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderCompletionNotification orderCompletionNotification = (OrderCompletionNotification) o;
        return Objects.equals(this.orderId, orderCompletionNotification.orderId) &&
                Objects.equals(this.shippingTrackingNumber, orderCompletionNotification.shippingTrackingNumber) &&
                Objects.equals(this.orderPlaced, orderCompletionNotification.orderPlaced) &&
                Objects.equals(this.orderCompleted, orderCompletionNotification.orderCompleted) &&
                Objects.equals(this.customerId, orderCompletionNotification.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, shippingTrackingNumber, orderPlaced, orderCompleted, customerId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderCompletionNotification {\n");
        sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
        sb.append("    shippingTrackingNumber: ").append(toIndentedString(shippingTrackingNumber)).append("\n");
        sb.append("    orderPlaced: ").append(toIndentedString(orderPlaced)).append("\n");
        sb.append("    orderCompleted: ").append(toIndentedString(orderCompleted)).append("\n");
        sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
