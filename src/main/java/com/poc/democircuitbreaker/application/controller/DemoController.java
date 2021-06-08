package com.poc.democircuitbreaker.application.controller;

import com.poc.democircuitbreaker.application.dto.ExchangeRequest;
import com.poc.democircuitbreaker.application.dto.ExchangeResponse;
import com.poc.democircuitbreaker.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("demo")
public class DemoController {

  private final DemoService service;

  @PostMapping("exchange")
  @ResponseStatus(HttpStatus.CREATED)
  public ExchangeResponse createExchange(@RequestBody ExchangeRequest request) {
    log.info("## [DemoController:createExchange] Starting method.");
    return service.create(request);
  }

  @GetMapping("exchange")
  @ResponseStatus(HttpStatus.OK)
  public List<ExchangeResponse> getAll() {
    log.info("## [DemoController:getAll] Starting method.");
    return service.getAll();
  }

  @GetMapping("exchange/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ExchangeResponse getById(@PathVariable("id") String id) {
    log.info("## [DemoController:getById] Starting method.");
    return service.getById(Long.parseLong(id));
  }

  @GetMapping("failure")
  public String failure(){
    return service.failure();
  }

  @GetMapping("success")
  public String success(){
    return service.success();
  }

  @GetMapping("successException")
  public String successException(){
    return service.successException();
  }

  @GetMapping("ignore")
  public String ignore(){
    return service.ignoreException();
  }

  @GetMapping("fallback")
  public String failureWithFallback(){
    return service.failureWithFallback();
  }

}
