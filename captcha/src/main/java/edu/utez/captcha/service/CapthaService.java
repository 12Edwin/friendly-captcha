package edu.utez.captcha.service;

import edu.utez.captcha.config.CaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class CapthaService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${FRIEDLYCAPTCHA.CAPTCHAKEY}")
    private String captchaKey;

    @Value("${FRIEDLYCAPTCHA.SITEKEY}")
    private String siteKey;

    public CaptchaResponse verificarCaptcha(String solution){
        String url = "https://api.friendlycaptcha.com/api/v1/siteverify";

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(
                MediaType.APPLICATION_JSON);
        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("solution", solution);
        requestBody.put("sitekey", siteKey);
        requestBody.put("secret", captchaKey);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<CaptchaResponse> responseEntity = restTemplate.postForEntity(url, requestEntity, CaptchaResponse.class);
        return responseEntity.getBody();
    }
}
