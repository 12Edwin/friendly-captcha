import axios from "axios";

const API_URL = 'http://localhost:8080/verificar-captcha; '
// Reemplaza con la URL de tu backend Spring Boot

const CaptchaService = {
    verificarCaptcha(solution) {
        return axios.post(API_URL+"?solution="+solution )
            .then(response => response.data)
            .catch(error => {
                console.error('Error al verificar el captcha:', error);
                throw error;
            });
    }
};

export {CaptchaService};