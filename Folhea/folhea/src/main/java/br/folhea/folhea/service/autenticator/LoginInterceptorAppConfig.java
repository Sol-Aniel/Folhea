package br.folhea.folhea.service.autenticator;

@Configuration
public class LoginInterceptorAppConfig implements  WebMvcConfigurer{

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addinternceptor(loginInterceptor).excludePathPatterns(

                "/login", "/logar", "error","/cadastroUsuario"
        );
    }
}