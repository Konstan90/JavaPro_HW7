package ru.kononov.service;

import lombok.RequiredArgsConstructor;
import main.java.ru.kononov.entity.UserProduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kononov.client.ProductServiceClient;
import ru.kononov.config.RestTemplateConfig;
import ru.kononov.exception.LogicalException;

import java.math.BigDecimal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ProductServiceClient productServiceClient;

    @Value("${product.client.token}")
    private String productClientToken;
    @Override
    public String executePayment(Long userId, Long productId, BigDecimal amount) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserProduct>> getAllProductsByUserId(BigDecimal userId) {
        return productServiceClient.getAllProducts(userId,productClientToken);
    }

    @Override
    public ResponseEntity<UserProduct> executePayment(Long id, BigDecimal sum) {
        UserProduct userProduct;
        try{
            userProduct = productServiceClient.getProduct(id,productClientToken).getBody();
        }
        catch (Exception e) {
            throw new LogicalException("Продукт не найден");
        }
        if(userProduct.getBalance().compareTo(sum) < 0) {
            throw new LogicalException("Сумма превышает доступный остаток");
        }

        BigDecimal newBalance = userProduct.getBalance().subtract(sum);
        return productServiceClient.updateBalance(id,newBalance,productClientToken);
    }
}
