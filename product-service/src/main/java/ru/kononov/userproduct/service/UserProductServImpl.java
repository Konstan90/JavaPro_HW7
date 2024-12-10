package ru.kononov.userproduct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kononov.entity.UserProduct;
import ru.kononov.userproduct.repository.UserProductRepo;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductServImpl implements UserProductServ {
    @Autowired
    private UserProductRepo userProductRepo;

    @Override
    public List<UserProduct> getAllProductsByUserId(BigDecimal userId) {
        return userProductRepo.findAllByUserId(userId);
    }

    @Override
    public UserProduct getProductById(Long id) {

        return userProductRepo.findFirstById(id);
    }

    @Override
    public UserProduct updateProductBalance(Long id, BigDecimal amount) {

        int updatedRows = userProductRepo.updateBalance(id, amount);

        if (updatedRows == 0) {
            throw new IllegalArgumentException("Product not found or balance not updated");
        }
        return userProductRepo.findFirstById(id);

    }
}
