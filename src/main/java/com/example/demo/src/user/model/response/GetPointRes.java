package com.example.demo.src.user.model.response;

import com.example.demo.src.user.model.entity.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPointRes {
    private int userPoint;
    private List<Point> pointList;
}
