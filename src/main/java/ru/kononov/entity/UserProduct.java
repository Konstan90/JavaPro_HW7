package main.java.ru.kononov.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserProduct {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private String productType;
    private BigDecimal userId;
}
