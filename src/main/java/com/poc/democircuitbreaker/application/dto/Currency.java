package com.poc.democircuitbreaker.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Currency {

  PEN(1),
  USD(2),
  EUR(3),
  CAD(4);

  private final int code;

  public static Currency from(int code) {
    return Arrays.stream(Currency.values())
      .filter(s -> s.code == code)
      .findFirst()
      .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", code)));
  }

}
