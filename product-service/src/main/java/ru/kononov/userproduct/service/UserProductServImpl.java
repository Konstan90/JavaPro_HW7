package ru.kononov.userproduct.service;

import main.java.ru.kononov.entity.UserProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kononov.userproduct.dao.UserProductDao;

import java.math.BigDecimal;
import java.util.List;
@Service
public class UserProductServImpl implements UserProductServ {
    @Autowired
    private UserProductDao userProductDao;
    @Override
    public List<UserProduct> getAllProductsByUserId(BigDecimal userId) {
        return userProductDao.getAllProductsByUserId(userId);
    }
    @Override
    public UserProduct getProductById(Long id) {
        return userProductDao.getProductById(id);
    }

    @Override
    public UserProduct updateProductBalance(Long id, BigDecimal amount) {
        return userProductDao.updateProductBalance(id,amount);

    }
}
