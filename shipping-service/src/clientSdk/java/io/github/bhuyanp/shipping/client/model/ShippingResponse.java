package io.github.bhuyanp.shipping.client.model;

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
 * ShippingResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.16.0")
public class ShippingResponse {

  private UUID trackingId;

  public ShippingResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ShippingResponse(UUID trackingId) {
    this.trackingId = trackingId;
  }

  public ShippingResponse trackingId(UUID trackingId) {
    this.trackingId = trackingId;
    return this;
  }

  /**
   * Get trackingId
   * @return trackingId
   */
  @NotNull
  @JsonProperty("trackingId")
  public UUID getTrackingId() {
    return trackingId;
  }

  public void setTrackingId(UUID trackingId) {
    this.trackingId = trackingId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShippingResponse shippingResponse = (ShippingResponse) o;
    return Objects.equals(this.trackingId, shippingResponse.trackingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trackingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ShippingResponse {\n");
    sb.append("    trackingId: ").append(toIndentedString(trackingId)).append("\n");
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

