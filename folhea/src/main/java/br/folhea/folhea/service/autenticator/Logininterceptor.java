package br.folhea.folhea.service.autenticator;
@Component
public class Logininterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if(Cookie.Service.getCookie(request, "usuarioId")!= null){
            return true;
        }
        response.sendRedirect("/login");
    }

}