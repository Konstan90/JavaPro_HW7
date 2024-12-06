package ru.kononov.controller;

import lombok.RequiredArgsConstructor;
import main.java.ru.kononov.entity.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.kononov.exception.LogicalException;
import ru.kononov.service.PaymentService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PaymentController {

//    @Autowired
//    private PaymentServiceImpl paymentService;

    private final PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${product-service-url}")
    private String productServiceUrl;

    @Value("${product.client.token}")
    private String productClientToken;

    // Эндпоинт для исполнения платежа
    @GetMapping("/execute-payment/{productId}")
    public ResponseEntity<Object> executePayment(@PathVariable Long productId, @RequestParam BigDecimal sum) {
        try {
            ResponseEntity<UserProduct> response = paymentService.executePayment(productId,sum);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        }
        catch (LogicalException e) {
            String responseMsg = e.getMessage();
            return new ResponseEntity<>(responseMsg,HttpStatus.BAD_GATEWAY);
        }
        catch (Exception e) {
            // Обработка ошибок, если сервис продуктов недоступен
            if(e.getClass().getName().equals("org.springframework.web.client.HttpClientErrorException$NotFound"))
            {
                String responseMsg = "Продуктовый сервис недоступен";
                return new ResponseEntity<>(responseMsg,HttpStatus.NOT_FOUND);
            }
            else if(e.getClass().getName().equals("org.springframework.web.client.HttpClientErrorException$Unauthorized"))
            {
                String responseMsg = "Отказано в доступе к продуктовому сервису";
                return new ResponseEntity<>(responseMsg,HttpStatus.UNAUTHORIZED);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    // Эндпоинт для получения продуктов пользователя
    @GetMapping("/{id}/getAllProducts")
    public ResponseEntity<Object> getProducts(@PathVariable("id") BigDecimal userId) {
        try {
            // Запрос всех продуктов пользователя у сервиса продуктов
            String url = productServiceUrl + userId + "/getAllProducts";
            // Устанавливаем заголовки запроса
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-access-token", productClientToken); // Добавляем заголовок с токеном

            // Оборачиваем заголовки в HttpEntity
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<List<UserProduct>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});
            // Запрос через Feign
            // ResponseEntity<List<UserProduct>> response = paymentService.getAllProductsByUserId(userId);
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            // Обработка ошибок, если сервис продуктов недоступен
            if(e.getClass().getName().equals("org.springframework.web.client.HttpClientErrorException$NotFound"))
            {
                String responseMsg = "Продуктовый сервис недоступен";
                return new ResponseEntity<>(responseMsg,HttpStatus.NOT_FOUND);
            }
            else if(e.getClass().getName().equals("org.springframework.web.client.HttpClientErrorException$Unauthorized"))
            {
                String responseMsg = "Отказано в доступе к продуктовому сервису";
                return new ResponseEntity<>(responseMsg,HttpStatus.UNAUTHORIZED);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}
