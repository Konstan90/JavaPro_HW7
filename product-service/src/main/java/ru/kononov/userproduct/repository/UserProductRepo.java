package ru.kononov.userproduct.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kononov.entity.UserProduct;

import java.math.BigDecimal;
import java.util.List;

public interface UserProductRepo extends JpaRepository<UserProduct, Long> {
    List<UserProduct> findAllByUserId(BigDecimal Id);

    UserProduct findFirstById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE UserProduct p SET p.balance = :newBalance WHERE p.id = :productId")
    int updateBalance(Long productId, BigDecimal newBalance);
}
