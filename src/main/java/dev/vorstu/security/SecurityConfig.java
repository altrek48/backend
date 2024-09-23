package dev.vorstu.security;

import dev.vorstu.entities.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
//                        //для незарегестрированных пользователей
//                        .requestMatchers("/api/registration").not().fullyAuthenticated()
//
//                        //для всех пользователей
//                        .requestMatchers("/api/login/**").permitAll()
//
//                        //для ролей: студент, админ
//                        .requestMatchers("/api/base/students").hasAnyAuthority("STUDENT", "ADMIN")
//
//                        //только для админов
//                        .requestMatchers("/api/base/students/**").hasAuthority("ADMIN")

                        //на все остальные запросы только для аутенфицированных пользователей
//                        .anyRequest().authenticated()



                //ПОТОМ УДАЛИТЬ
                                .anyRequest().hasAnyAuthority("STUDENT", "ADMIN")
                )
                .httpBasic(basic -> basic
                        .authenticationEntryPoint(new AuthenticationEntryPoint() {
                            @Override
                            public void commence(HttpServletRequest request, HttpServletResponse response,
                                                 AuthenticationException authException) {
                                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            }
                        })
                )
                .csrf(csrf -> csrf.disable())   // Disable CSRF protection
                .cors(cors -> cors.disable());  // Disable CORS

        return http.build();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(
                        "select username, p.password as password, enable "
                                + "from users as u "
                                + "inner join passwords as p on u.password_id = p.id "
                                + "where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");

    }

}
