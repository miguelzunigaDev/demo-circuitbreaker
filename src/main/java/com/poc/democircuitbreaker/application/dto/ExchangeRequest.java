package com.poc.democircuitbreaker.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExchangeRequest {

  private double amount;
  private Currency sourceCurrency;
  private Currency targetCurrency;

}
