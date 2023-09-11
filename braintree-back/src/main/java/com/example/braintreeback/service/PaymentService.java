package com.example.braintreeback.service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.example.braintreeback.config.BraintreeConfig;
import com.example.braintreeback.dto.ClientTokenDto;
import com.example.braintreeback.dto.PurchaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {
  @Autowired
  BraintreeConfig config;

  public BraintreeGateway getGateway(){
    return new BraintreeGateway(
        Environment.SANDBOX,
        config.getMerchantId(),
        config.getPublicKey(),
        config.getPrivateKey()
    );
  }

  public ClientTokenDto getToken(){
    ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
    String token = getGateway().clientToken().generate(clientTokenRequest);
    return new ClientTokenDto(token);
  }

  public Result<Transaction> checkout(PurchaseDto purchaseDto){
    TransactionRequest request = new TransactionRequest()
        .amount(purchaseDto.getAmount())
        .paymentMethodNonce(purchaseDto.getNonce())
        .options()
        .submitForSettlement(true)
        .done();
    return getGateway().transaction().sale(request);
  }
}
