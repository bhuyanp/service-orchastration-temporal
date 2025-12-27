package io.github.bhuyanp.shipping.client.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.bhuyanp.shipping.client.model.ShippingItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ShippingRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.16.0")
public class ShippingRequest {

  private UUID orderId;

  
  private List<ShippingItem> shippingItems = new ArrayList<>();

  private String customerId;

  public ShippingRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ShippingRequest(UUID orderId, List<ShippingItem> shippingItems, String customerId) {
    this.orderId = orderId;
    this.shippingItems = shippingItems;
    this.customerId = customerId;
  }

  public ShippingRequest orderId(UUID orderId) {
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

  public ShippingRequest shippingItems(List<ShippingItem> shippingItems) {
    this.shippingItems = shippingItems;
    return this;
  }

  public ShippingRequest addShippingItemsItem(ShippingItem shippingItemsItem) {
    if (this.shippingItems == null) {
      this.shippingItems = new ArrayList<>();
    }
    this.shippingItems.add(shippingItemsItem);
    return this;
  }

  /**
   * Get shippingItems
   * @return shippingItems
   */
  @NotNull
  @JsonProperty("shippingItems")
  public List<ShippingItem> getShippingItems() {
    return shippingItems;
  }

  public void setShippingItems(List<ShippingItem> shippingItems) {
    this.shippingItems = shippingItems;
  }

  public ShippingRequest customerId(String customerId) {
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
    ShippingRequest shippingRequest = (ShippingRequest) o;
    return Objects.equals(this.orderId, shippingRequest.orderId) &&
        Objects.equals(this.shippingItems, shippingRequest.shippingItems) &&
        Objects.equals(this.customerId, shippingRequest.customerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, shippingItems, customerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ShippingRequest {\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    shippingItems: ").append(toIndentedString(shippingItems)).append("\n");
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

