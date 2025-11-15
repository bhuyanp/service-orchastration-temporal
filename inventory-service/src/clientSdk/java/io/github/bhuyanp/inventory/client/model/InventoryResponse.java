package io.github.bhuyanp.inventory.client.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * InventoryResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-15T08:20:28.530530-05:00[America/New_York]", comments = "Generator version: 7.16.0")
public class InventoryResponse {

  private UUID inventoryRefId;

  public InventoryResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InventoryResponse(UUID inventoryRefId) {
    this.inventoryRefId = inventoryRefId;
  }

  public InventoryResponse inventoryRefId(UUID inventoryRefId) {
    this.inventoryRefId = inventoryRefId;
    return this;
  }

  /**
   * Get inventoryRefId
   * @return inventoryRefId
   */
  @NotNull
  @JsonProperty("inventoryRefId")
  public UUID getInventoryRefId() {
    return inventoryRefId;
  }

  public void setInventoryRefId(UUID inventoryRefId) {
    this.inventoryRefId = inventoryRefId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InventoryResponse inventoryResponse = (InventoryResponse) o;
    return Objects.equals(this.inventoryRefId, inventoryResponse.inventoryRefId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inventoryRefId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InventoryResponse {\n");
    sb.append("    inventoryRefId: ").append(toIndentedString(inventoryRefId)).append("\n");
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

