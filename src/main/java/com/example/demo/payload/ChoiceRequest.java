package com.example.demo.payload;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ChoiceRequest {
    @NotBlank
    @Size(max = 40)
    private String text;
}
