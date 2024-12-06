package ru.kononov.service;

import main.java.ru.kononov.entity.UserProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    public String executePayment(Long userId, Long productId, BigDecimal amount);
    public ResponseEntity<List<UserProduct>> getAllProductsByUserId(BigDecimal userId);
    public ResponseEntity<UserProduct> executePayment(Long id, BigDecimal sum);
}
