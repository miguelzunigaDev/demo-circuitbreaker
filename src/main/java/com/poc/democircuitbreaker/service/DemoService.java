package com.poc.democircuitbreaker.service;

import com.poc.democircuitbreaker.application.dto.ExchangeRequest;
import com.poc.democircuitbreaker.application.dto.ExchangeResponse;

import java.util.List;

public interface DemoService {

  ExchangeResponse create(ExchangeRequest request);
  List<ExchangeResponse> getAll();
  ExchangeResponse getById(long id);

  String failure();
  String success();
  String successException();
  String ignoreException();
  String failureWithFallback();
}
