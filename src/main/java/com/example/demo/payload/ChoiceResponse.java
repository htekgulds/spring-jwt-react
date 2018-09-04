package com.example.demo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoiceResponse {
    private Long id;
    private String text;
    private long voteCount;
}
