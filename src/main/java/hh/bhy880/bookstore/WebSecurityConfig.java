package hh.bhy880.bookstore;



import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
@EnableMethodSecurity(securedEnabled = true)  
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	http
		.authorizeHttpRequests( authorize -> authorize
			.anyRequest().authenticated()
		)
		.formLogin( formlogin -> formlogin
			.loginPage("/login")
			.defaultSuccessUrl("/booklist", true)
			.permitAll()
		)
		        .logout(logout -> logout
                .permitAll()
            );
		return http.build();
}

  @Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
	UserDetails user = User.builder()
		.username("user")
		.password(passwordEncoder.encode("password"))
		.roles("USER")
		.build();
		UserDetails admin = User.builder()
        .username("admin")
        .password(passwordEncoder.encode("admin123"))
        .roles("USER", "ADMIN")
        .build();

	
	return new InMemoryUserDetailsManager(user, admin);
}

}
