package io.github.bhuyanp.order.client.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.bhuyanp.order.client.model.Customer;
import io.github.bhuyanp.order.client.model.OrderItem;
import io.github.bhuyanp.order.client.model.Payment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Order
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.16.0")
public class Order {

  private UUID orderId;

  private Customer customer;

  
  private List<OrderItem> orderItems = new ArrayList<>();

  private Payment payment;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime orderPlaced;

  public Order() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Order(UUID orderId, Customer customer, List<OrderItem> orderItems, Payment payment, LocalDateTime orderPlaced) {
    this.orderId = orderId;
    this.customer = customer;
    this.orderItems = orderItems;
    this.payment = payment;
    this.orderPlaced = orderPlaced;
  }

  public Order orderId(UUID orderId) {
    this.orderId = orderId;
    return this;
  }

  /**
   * Get orderId
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

  public Order customer(Customer customer) {
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

  public Order orderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
    return this;
  }

  public Order addOrderItemsItem(OrderItem orderItemsItem) {
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

  public Order payment(Payment payment) {
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

  public Order orderPlaced(LocalDateTime orderPlaced) {
    this.orderPlaced = orderPlaced;
    return this;
  }

  /**
   * Get orderPlaced
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.orderId, order.orderId) &&
        Objects.equals(this.customer, order.customer) &&
        Objects.equals(this.orderItems, order.orderItems) &&
        Objects.equals(this.payment, order.payment) &&
        Objects.equals(this.orderPlaced, order.orderPlaced);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, customer, orderItems, payment, orderPlaced);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    orderItems: ").append(toIndentedString(orderItems)).append("\n");
    sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
    sb.append("    orderPlaced: ").append(toIndentedString(orderPlaced)).append("\n");
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

