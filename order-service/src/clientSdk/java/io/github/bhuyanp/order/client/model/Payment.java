package io.github.bhuyanp.order.client.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.constraints.NotNull;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Payment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-14T07:54:12.454468-05:00[America/New_York]", comments = "Generator version: 7.16.0")
public class Payment {

  private String nameOnTheCard;

  private @Nullable String creditCardNumber;

  private @Nullable Integer expiryMonth;

  private @Nullable Integer expiryYear;

  private @Nullable Integer ccv;

  public Payment() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Payment(String nameOnTheCard) {
    this.nameOnTheCard = nameOnTheCard;
  }

  public Payment nameOnTheCard(String nameOnTheCard) {
    this.nameOnTheCard = nameOnTheCard;
    return this;
  }

  /**
   * Get nameOnTheCard
   * @return nameOnTheCard
   */
  @NotNull
  @JsonProperty("nameOnTheCard")
  public String getNameOnTheCard() {
    return nameOnTheCard;
  }

  public void setNameOnTheCard(String nameOnTheCard) {
    this.nameOnTheCard = nameOnTheCard;
  }

  public Payment creditCardNumber(@Nullable String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
    return this;
  }

  /**
   * Get creditCardNumber
   * @return creditCardNumber
   */
  
  @JsonProperty("creditCardNumber")
  public @Nullable String getCreditCardNumber() {
    return creditCardNumber;
  }

  public void setCreditCardNumber(@Nullable String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public Payment expiryMonth(@Nullable Integer expiryMonth) {
    this.expiryMonth = expiryMonth;
    return this;
  }

  /**
   * Get expiryMonth
   * minimum: 1
   * maximum: 12
   * @return expiryMonth
   */
  
  @JsonProperty("expiryMonth")
  public @Nullable Integer getExpiryMonth() {
    return expiryMonth;
  }

  public void setExpiryMonth(@Nullable Integer expiryMonth) {
    this.expiryMonth = expiryMonth;
  }

  public Payment expiryYear(@Nullable Integer expiryYear) {
    this.expiryYear = expiryYear;
    return this;
  }

  /**
   * Get expiryYear
   * minimum: 2025
   * @return expiryYear
   */
  
  @JsonProperty("expiryYear")
  public @Nullable Integer getExpiryYear() {
    return expiryYear;
  }

  public void setExpiryYear(@Nullable Integer expiryYear) {
    this.expiryYear = expiryYear;
  }

  public Payment ccv(@Nullable Integer ccv) {
    this.ccv = ccv;
    return this;
  }

  /**
   * Get ccv
   * minimum: 99
   * maximum: 999
   * @return ccv
   */
  
  @JsonProperty("ccv")
  public @Nullable Integer getCcv() {
    return ccv;
  }

  public void setCcv(@Nullable Integer ccv) {
    this.ccv = ccv;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payment payment = (Payment) o;
    return Objects.equals(this.nameOnTheCard, payment.nameOnTheCard) &&
        Objects.equals(this.creditCardNumber, payment.creditCardNumber) &&
        Objects.equals(this.expiryMonth, payment.expiryMonth) &&
        Objects.equals(this.expiryYear, payment.expiryYear) &&
        Objects.equals(this.ccv, payment.ccv);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nameOnTheCard, creditCardNumber, expiryMonth, expiryYear, ccv);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payment {\n");
    sb.append("    nameOnTheCard: ").append(toIndentedString(nameOnTheCard)).append("\n");
    sb.append("    creditCardNumber: ").append(toIndentedString(creditCardNumber)).append("\n");
    sb.append("    expiryMonth: ").append(toIndentedString(expiryMonth)).append("\n");
    sb.append("    expiryYear: ").append(toIndentedString(expiryYear)).append("\n");
    sb.append("    ccv: ").append(toIndentedString(ccv)).append("\n");
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

