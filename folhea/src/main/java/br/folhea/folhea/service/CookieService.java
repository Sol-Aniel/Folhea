package br.folhea.folhea.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// import Arrays
public class CookieService {

    public static void setCookie(HttpServletResponse response, String key, String valor, int segundos) throws UnsupportedEncodingException{
        try {
            Cookie cookie = new Cookie(key, URLEncoder.encode(valor, "UTF-8"));
            cookie.setMaxAge(segundos);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException {
        if (request.getCookies() == null) {
            return null;
        }

        String valor = Arrays.stream(request.getCookies())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);

        if (valor != null) {
            try {
                valor = URLDecoder.decode(valor, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return valor;
    }

//    public static String getCookie(HttpServletRequest request, String key)throws UnsupportedEncodingException{
//        String valor = Optional.ofNullable(request.getCookies())
//                .flatMap(cookies -> Arrays.stream(cookies))
//                .filter(cookie -> key.equals(cookie.getName())).findAny()
//                .map(Cookie::getValue)
//                .orElse(null);
//     if(valor != null){
//         valor = URLDecoder.decode(valor, "UTF-8");
//         return valor;
//
//     }
//     return valor;
//    }
}
