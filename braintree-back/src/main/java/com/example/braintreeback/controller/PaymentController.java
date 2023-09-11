package com.example.braintreeback.controller;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.example.braintreeback.dto.ClientTokenDto;
import com.example.braintreeback.dto.PurchaseDto;
import com.example.braintreeback.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {
  @Autowired
  PaymentService paymentService;

  @GetMapping("/token")
  public ResponseEntity<ClientTokenDto> getToken(){
    return ResponseEntity.ok(paymentService.getToken());
  }

  @PostMapping("/checkout")
  public ResponseEntity<Result<Transaction>> checkout(@RequestBody PurchaseDto dto){
    return ResponseEntity.ok(paymentService.checkout(dto));
  }
}
