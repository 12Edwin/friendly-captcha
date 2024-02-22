package edu.utez.captcha.controller;

import edu.utez.captcha.config.CaptchaResponse;
import edu.utez.captcha.service.CapthaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
@RequestMapping("/api/captcha")
public class CaptchaController {

    @Autowired
    private CapthaService captchaService;

    @PostMapping("/verificar-captch")
    public CaptchaResponse verificarCaptcha(@RequestParam("solution") String solution){
        return captchaService.verificarCaptcha(solution);
    }
}
