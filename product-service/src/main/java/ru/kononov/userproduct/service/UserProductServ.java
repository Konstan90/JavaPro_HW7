package ru.kononov.userproduct.service;


import main.java.ru.kononov.entity.UserProduct;

import java.math.BigDecimal;
import java.util.List;

public interface UserProductServ {
    List<UserProduct> getAllProductsByUserId(BigDecimal userId);
    UserProduct getProductById(Long id);
    UserProduct updateProductBalance(Long id, BigDecimal sum);
}
