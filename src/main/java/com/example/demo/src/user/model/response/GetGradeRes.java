package com.example.demo.src.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetGradeRes {
    private int gradeId;
    private String gradeName;
    private double rewardRate;
    private String nextMonth;
    private String currentMonthLast;
}
