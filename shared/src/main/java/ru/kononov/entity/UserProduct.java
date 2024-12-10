package ru.kononov.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "user_product")
public class UserProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acc_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "amount", nullable = false)
    private BigDecimal balance;

    @Column(name = "type_product", nullable = false)
    private String productType;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}