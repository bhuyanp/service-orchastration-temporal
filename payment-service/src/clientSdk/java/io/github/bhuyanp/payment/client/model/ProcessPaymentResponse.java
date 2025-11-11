package io.github.bhuyanp.payment.client.model;

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
 * ProcessPaymentResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-10T11:22:49.127572-05:00[America/New_York]", comments = "Generator version: 7.16.0")
public class ProcessPaymentResponse {

  private UUID paymentRefId;

  public ProcessPaymentResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProcessPaymentResponse(UUID paymentRefId) {
    this.paymentRefId = paymentRefId;
  }

  public ProcessPaymentResponse paymentRefId(UUID paymentRefId) {
    this.paymentRefId = paymentRefId;
    return this;
  }

  /**
   * Get paymentRefId
   * @return paymentRefId
   */
  @NotNull
  @JsonProperty("paymentRefId")
  public UUID getPaymentRefId() {
    return paymentRefId;
  }

  public void setPaymentRefId(UUID paymentRefId) {
    this.paymentRefId = paymentRefId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessPaymentResponse processPaymentResponse = (ProcessPaymentResponse) o;
    return Objects.equals(this.paymentRefId, processPaymentResponse.paymentRefId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentRefId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessPaymentResponse {\n");
    sb.append("    paymentRefId: ").append(toIndentedString(paymentRefId)).append("\n");
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

