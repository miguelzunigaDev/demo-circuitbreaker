package com.poc.democircuitbreaker.service.impl;

import com.poc.democircuitbreaker.application.dto.Currency;
import com.poc.democircuitbreaker.application.dto.ExchangeRequest;
import com.poc.democircuitbreaker.application.dto.ExchangeResponse;
import com.poc.democircuitbreaker.infrastructure.exceptions.BusinessException;
import com.poc.democircuitbreaker.service.DemoService;
import com.poc.democircuitbreaker.service.client.ExchangeRestClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {

  private static final String TRANSFER_INSTANCE = "transfer";

  private final ExchangeRestClient client;

  @Override
  @CircuitBreaker(name = TRANSFER_INSTANCE)
  @Bulkhead(name = TRANSFER_INSTANCE)
  @Retry(name = TRANSFER_INSTANCE)
  public ExchangeResponse create(ExchangeRequest request) {
    log.info("## [DemoServiceImpl:create] Starting method.");
    return client.createExchange(request);
  }

  @Override
  @CircuitBreaker(name = TRANSFER_INSTANCE)
  @Bulkhead(name = TRANSFER_INSTANCE)
  @Retry(name = TRANSFER_INSTANCE)
  public List<ExchangeResponse> getAll() {
    log.info("## [DemoServiceImpl:getAll] Starting method.");
    return client.getAllExchanges();
  }

  @Override
  @CircuitBreaker(name = TRANSFER_INSTANCE)
  @Bulkhead(name = TRANSFER_INSTANCE)
  @Retry(name = TRANSFER_INSTANCE)
  public ExchangeResponse getById(long id) {
    log.info("## [DemoServiceImpl:getById] Starting method.");
    return client.getByid(id);
  }

  @Override
  @CircuitBreaker(name = TRANSFER_INSTANCE)
  @Bulkhead(name = TRANSFER_INSTANCE)
  @Retry(name = TRANSFER_INSTANCE)
  public String failure() {
    log.info("$$$ failure");
    throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception");
  }

  @Override
  @CircuitBreaker(name = TRANSFER_INSTANCE)
  @Bulkhead(name = TRANSFER_INSTANCE)
  public String ignoreException() {
    log.info("$$$ ignoreException");
    throw new BusinessException("This exception is ignored by the CircuitBreaker of transfers");
  }

  @Override
  @CircuitBreaker(name = TRANSFER_INSTANCE)
  @Bulkhead(name = TRANSFER_INSTANCE)
  @Retry(name = TRANSFER_INSTANCE)
  public String success() {
    log.info("$$$ success");
    return "Hello World from demo-circuitBreaker";
  }

  @Override
  @CircuitBreaker(name = TRANSFER_INSTANCE)
  @Bulkhead(name = TRANSFER_INSTANCE)
  public String successException() {
    log.info("$$$ successException");
    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This is a remote client exception");
  }

  @Override
  @CircuitBreaker(name = TRANSFER_INSTANCE, fallbackMethod = "fallback")
  public String failureWithFallback() {
    log.info("$$$ failureWithFallback");
    return failure();
  }

  private String fallback(HttpServerErrorException ex) {
    log.error("## [DemoServiceImpl:fallback] HttpServerErrorException: ", ex);
    return defaultExchangeResponse().toString();
  }

  private String fallback(Exception ex) {
    log.error("## [DemoServiceImpl:fallback] Exception: ", ex);
    return defaultExchangeResponse().toString();
  }

  private ExchangeResponse defaultExchangeResponse() {
    ExchangeResponse defaultResponse = new ExchangeResponse();
    defaultResponse.setId(456L);
    defaultResponse.setAmount(100.0);
    defaultResponse.setSourceCurrency(Currency.USD);
    defaultResponse.setTargetCurrency(Currency.EUR);
    return defaultResponse;
  }

}
