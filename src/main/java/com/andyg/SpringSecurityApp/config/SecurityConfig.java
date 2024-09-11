package com.andyg.SpringSecurityApp.config;

import com.andyg.SpringSecurityApp.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) //este es un control para una vulnerabilidad
                .httpBasic(Customizer.withDefaults())
        //                esta línea de configuración establece la autenticación básica HTTP para tu aplicación utilizando los
        //                valores predeterminados proporcionados por Spring Security. Esto significa que Spring Security
        //                manejará la autenticación de los usuarios utilizando un formulario de inicio de sesión básico y
        //                proporcionará las funcionalidades estándar asociadas con la autenticación básica HTTP
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //        La configuración STATELESS es comúnmente utilizada en aplicaciones RESTful donde se desea que cada
                //        solicitud sea independiente y no se requiera mantener un estado de sesión en el servidor. Esto puede
                //        ser útil para mejorar la escalabilidad y la seguridad al eliminar la necesidad de mantener sesiones en el servidor.
                .authorizeHttpRequests(http -> {


                    //endpoints de administracion de ususarios
                    http.requestMatchers(HttpMethod.GET, "/auth/users/list").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.GET, "/auth/users/{id}").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/auth/users/create").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/auth/users/update/{id}").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/auth/users/delete/{id}").hasAnyRole("DEVELOPER");

                    http.requestMatchers(HttpMethod.GET, "/auth/marcas/list").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/auth/marcas/{id}").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/auth/marcas/create").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/auth/marcas/update/{id}").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/auth/marcas/delete/{id}").hasAnyRole("DEVELOPER");

                    http.requestMatchers(HttpMethod.GET, "/auth/category/list").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/auth/category/{id}").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/auth/category/create").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/auth/category/update/{id}").hasAnyRole("DEVELOPER", "ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/auth/category/delete/{id}").hasAnyRole("DEVELOPER");

                    http.requestMatchers(HttpMethod.GET, "/auth/model/list").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/auth/model/{id}").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/auth/model/create").hasAuthority("CREATE");
                    //configurar el resto de endpoints NO ESPECIFICADOS
                    http.anyRequest().denyAll();
                })

                //        Esta configuración establece reglas de autorización específicas para diferentes endpoints y métodos HTTP
                //        en tu aplicación Spring Boot, permitiendo o denegando el acceso basado en los roles de usuario y las
                //        autoridades específicas definidas en tu sistema de autenticación.
                .build();
                //        finaliza la configuración de seguridad y construye el objeto HttpSecurity final que será utilizado
                //        por Spring Security para aplicar las reglas de autorización definidas en tu aplicación.
                //        Una vez que se llama a .build(), la configuración de seguridad se considera completa y lista para ser aplicada.
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(csrf -> csrf.disable()) //este es un control para una vulnerabilidad
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    //Usuario configurado para prueba



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();   //NoOpPasswordEncoder solo para pruebas y BCryptPasswordEncoder encripta
    }
//
//    public static void main(String[] args){
//        System.out.println(new BCryptPasswordEncoder().encode("1234"));
////        metodo de encriptacion de textos, se debe mejorar para configurar en cada password registrada
//    }

}
