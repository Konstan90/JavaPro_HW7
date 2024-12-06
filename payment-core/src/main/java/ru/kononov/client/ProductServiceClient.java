package ru.kononov.client;

import main.java.ru.kononov.entity.UserProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "product-service", url = "${product-service-url}")
public interface ProductServiceClient {
    @GetMapping("/getProduct")
    ResponseEntity<UserProduct> getProduct(
            @RequestParam("id") Long id,
            @RequestHeader("x-access-token") String accessToken
    );

    @GetMapping("/{userId}/getAllProducts")
    ResponseEntity<List<UserProduct>> getAllProducts(
            @PathVariable("userId") BigDecimal userId,
            @RequestHeader("x-access-token") String accessToken
    );

    @GetMapping("/updateBalance")
    ResponseEntity<UserProduct> updateBalance(
            @RequestParam("id") Long id,
            @RequestParam("amount") BigDecimal amount,
            @RequestHeader("x-access-token") String accessToken
    );


}
