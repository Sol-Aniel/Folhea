package br.folhea.folhea.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
// import Arrays
public class CookieService {

    public static void setCookie(HttpServletResponse response, String key, String valor, int segundos) throws UnsupportedEncodingException{
        Cookie cookie = new Cookie(key, URLEncoder.encode(valor,"UTF-8"));
        cookie.setMaxAge(segundos);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServeletRequest request, String key)throws UnsupportedEncodingException{
        String valor = Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookie))
                .filter(cookie -> key.equals(cookie.getName())).findAny())
                .map(e -> e.getValue())
                .orElse(null);
     if(valor != null){
         valor = URLDecoder.decode(valor, "UTF-8");
         return valor;

     }
     return valor;
    }
}
