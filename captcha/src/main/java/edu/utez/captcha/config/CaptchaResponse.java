package edu.utez.captcha.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CaptchaResponse {

    private boolean success;
    private List<String> errors;
}
