package com.poc.democircuitbreaker.service.client;

import com.poc.democircuitbreaker.application.dto.ExchangeRequest;
import com.poc.democircuitbreaker.application.dto.ExchangeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "demo-client", url = "http://localhost:8099")
public interface ExchangeRestClient {

  @PostMapping("/api/exchanges")
  ExchangeResponse createExchange(ExchangeRequest request);

  @GetMapping("/api/exchanges")
  List<ExchangeResponse> getAllExchanges();

  @GetMapping("/api/exchanges/{id}")
  ExchangeResponse getByid(@PathVariable("id") long idExchange);

}
