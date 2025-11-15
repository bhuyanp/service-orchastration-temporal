package io.github.bhuyanp.inventory.client.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.bhuyanp.inventory.client.model.BlockItem;
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
 * InventoryRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-15T08:20:28.530530-05:00[America/New_York]", comments = "Generator version: 7.16.0")
public class InventoryRequest {

  private UUID orderId;

  
  private List<BlockItem> blockItems = new ArrayList<>();

  public InventoryRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InventoryRequest(UUID orderId, List<BlockItem> blockItems) {
    this.orderId = orderId;
    this.blockItems = blockItems;
  }

  public InventoryRequest orderId(UUID orderId) {
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

  public InventoryRequest blockItems(List<BlockItem> blockItems) {
    this.blockItems = blockItems;
    return this;
  }

  public InventoryRequest addBlockItemsItem(BlockItem blockItemsItem) {
    if (this.blockItems == null) {
      this.blockItems = new ArrayList<>();
    }
    this.blockItems.add(blockItemsItem);
    return this;
  }

  /**
   * Get blockItems
   * @return blockItems
   */
  @NotNull
  @JsonProperty("blockItems")
  public List<BlockItem> getBlockItems() {
    return blockItems;
  }

  public void setBlockItems(List<BlockItem> blockItems) {
    this.blockItems = blockItems;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InventoryRequest inventoryRequest = (InventoryRequest) o;
    return Objects.equals(this.orderId, inventoryRequest.orderId) &&
        Objects.equals(this.blockItems, inventoryRequest.blockItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, blockItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InventoryRequest {\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    blockItems: ").append(toIndentedString(blockItems)).append("\n");
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

