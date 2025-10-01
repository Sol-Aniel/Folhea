package br.folhea.folhea.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieService {

    public static void setCookie(HttpServletResponse response, String key, String valor, int segundos) throws UnsupportedEncodingException{
        Cookie cookie = new Cookie(key, URLEncoder.encode(valor,"UTF-8"));
        cookie.setMaxAge(segundos);
        response.addCookie(cookie);
    }

    
}
