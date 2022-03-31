package com.example.demo.src.user.model.response;

import com.example.demo.src.user.model.entity.Gift;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetGiftRes {
    private List<Gift> takeGift;
    private List<Gift> giveGift;
}
