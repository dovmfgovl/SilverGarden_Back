package com.sg.silvergarden.config.login;

import com.sg.silvergarden.service.login.UserService;
import com.sg.silvergarden.vo.empcreate.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserService userService;

    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//
//        // CORS 필터를 보안 필터 체인에 추가
//        http.cors().configurationSource(request -> corsConfiguration);

        http.cors().and().addFilter(corsFilter)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/**").permitAll()
//                        .requestMatchers(HttpMethod.OPTIONS, "/api/v1/**").permitAll()
//                        .requestMatchers(HttpMethod.GET,
//                                "/approval/**",
//                                "/at/**",
//                                "/dept/**",
//                                "/program/**",
//                                "/calendar/**",
//                                "/program/**",
//                                "/notice/**",
//                                "/my/**",
//                                "/message/**",
//                                "/member/**",
//                                "/emplist/**",
//                                "/emp/**",
//                                "/empcreate/**",
//                                "/crawling/**",
//                                "/schedule/**",
//                                "/payment/**",
//                                "/sms/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/**").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                        .requestMatchers("/approval/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/notice/fileDownload/**").permitAll()
                        .requestMatchers("/notice/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/schedule/schedulelist/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/at/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/dept/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/program/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/calendar/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/my/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/message/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/member/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/emplist/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/member/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/emp/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/empcreate/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/crawling/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/payment/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .requestMatchers("/sms/**").hasAnyAuthority(Role.ADMIN.name(),Role.USERA.name(),Role.USERB.name())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();// 사용자 인증 정보를 데이터베이스에서 가져옴
        authenticationProvider.setUserDetailsService(userService.userDetailsService());//
        authenticationProvider.setPasswordEncoder(passwordEncoder());// 반환된 패스워드 인코더 설정
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
