package io.github.bhuyanp.notification.client.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OrderConfirmationNotification
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.16.0")
public class OrderConfirmationNotification {

  private UUID orderId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime orderPlaced;

  private String customerId;

  public OrderConfirmationNotification() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public OrderConfirmationNotification(UUID orderId, LocalDateTime orderPlaced, String customerId) {
    this.orderId = orderId;
    this.orderPlaced = orderPlaced;
    this.customerId = customerId;
  }

  public OrderConfirmationNotification orderId(UUID orderId) {
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

  public OrderConfirmationNotification orderPlaced(LocalDateTime orderPlaced) {
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

  public OrderConfirmationNotification customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
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
    OrderConfirmationNotification orderConfirmationNotification = (OrderConfirmationNotification) o;
    return Objects.equals(this.orderId, orderConfirmationNotification.orderId) &&
        Objects.equals(this.orderPlaced, orderConfirmationNotification.orderPlaced) &&
        Objects.equals(this.customerId, orderConfirmationNotification.customerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, orderPlaced, customerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderConfirmationNotification {\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    orderPlaced: ").append(toIndentedString(orderPlaced)).append("\n");
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

