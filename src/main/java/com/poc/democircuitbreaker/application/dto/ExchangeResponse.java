package com.poc.democircuitbreaker.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeResponse {

  private long id;
  private double amount;
  private double amountWithExchangeRate;
  private Currency sourceCurrency;
  private Currency targetCurrency;
  private double exchangeRate;

}
