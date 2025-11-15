package io.github.bhuyanp.order.client.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.bhuyanp.order.client.model.Customer;
import io.github.bhuyanp.order.client.model.OrderItem;
import io.github.bhuyanp.order.client.model.Payment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateOrderRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-14T07:54:12.454468-05:00[America/New_York]", comments = "Generator version: 7.16.0")
public class CreateOrderRequest {

  private Customer customer;

  
  private List<OrderItem> orderItems = new ArrayList<>();

  private Payment payment;

  public CreateOrderRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateOrderRequest(Customer customer, List<OrderItem> orderItems, Payment payment) {
    this.customer = customer;
    this.orderItems = orderItems;
    this.payment = payment;
  }

  public CreateOrderRequest customer(Customer customer) {
    this.customer = customer;
    return this;
  }

  /**
   * Get customer
   * @return customer
   */
  @NotNull
  @JsonProperty("customer")
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public CreateOrderRequest orderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
    return this;
  }

  public CreateOrderRequest addOrderItemsItem(OrderItem orderItemsItem) {
    if (this.orderItems == null) {
      this.orderItems = new ArrayList<>();
    }
    this.orderItems.add(orderItemsItem);
    return this;
  }

  /**
   * Get orderItems
   * @return orderItems
   */
  @NotNull
  @JsonProperty("orderItems")
  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public CreateOrderRequest payment(Payment payment) {
    this.payment = payment;
    return this;
  }

  /**
   * Get payment
   * @return payment
   */
  @NotNull
  @JsonProperty("payment")
  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateOrderRequest createOrderRequest = (CreateOrderRequest) o;
    return Objects.equals(this.customer, createOrderRequest.customer) &&
        Objects.equals(this.orderItems, createOrderRequest.orderItems) &&
        Objects.equals(this.payment, createOrderRequest.payment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer, orderItems, payment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateOrderRequest {\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    orderItems: ").append(toIndentedString(orderItems)).append("\n");
    sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
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

