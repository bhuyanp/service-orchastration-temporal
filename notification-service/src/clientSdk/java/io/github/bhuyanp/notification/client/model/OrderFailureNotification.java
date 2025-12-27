package io.github.bhuyanp.notification.client.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OrderFailureNotification
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.16.0")
public class OrderFailureNotification {

  private UUID orderId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime orderPlaced;

  private String customerId;

  /**
   * Gets or Sets failureReason
   */
  public enum FailureReasonEnum {
    PAYMENT_FAILURE("PAYMENT_FAILURE"),
    
    INSUFFICIENT_INVENTORY("INSUFFICIENT_INVENTORY");

    private final String value;

    FailureReasonEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static FailureReasonEnum fromValue(String value) {
      for (FailureReasonEnum b : FailureReasonEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private FailureReasonEnum failureReason;

  public OrderFailureNotification() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public OrderFailureNotification(UUID orderId, LocalDateTime orderPlaced, String customerId, FailureReasonEnum failureReason) {
    this.orderId = orderId;
    this.orderPlaced = orderPlaced;
    this.customerId = customerId;
    this.failureReason = failureReason;
  }

  public OrderFailureNotification orderId(UUID orderId) {
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

  public OrderFailureNotification orderPlaced(LocalDateTime orderPlaced) {
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

  public OrderFailureNotification customerId(String customerId) {
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

  public OrderFailureNotification failureReason(FailureReasonEnum failureReason) {
    this.failureReason = failureReason;
    return this;
  }

  /**
   * Get failureReason
   * @return failureReason
   */
  @NotNull
  @JsonProperty("failureReason")
  public FailureReasonEnum getFailureReason() {
    return failureReason;
  }

  public void setFailureReason(FailureReasonEnum failureReason) {
    this.failureReason = failureReason;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderFailureNotification orderFailureNotification = (OrderFailureNotification) o;
    return Objects.equals(this.orderId, orderFailureNotification.orderId) &&
        Objects.equals(this.orderPlaced, orderFailureNotification.orderPlaced) &&
        Objects.equals(this.customerId, orderFailureNotification.customerId) &&
        Objects.equals(this.failureReason, orderFailureNotification.failureReason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, orderPlaced, customerId, failureReason);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderFailureNotification {\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    orderPlaced: ").append(toIndentedString(orderPlaced)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    failureReason: ").append(toIndentedString(failureReason)).append("\n");
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

