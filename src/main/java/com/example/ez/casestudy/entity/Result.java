package com.example.ez.casestudy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
@Embeddable
@Data
public class Result {
    private String metric;
    @Column(name = "result_value") // 'value' is often reserved
    private String value;
    private String period;
}