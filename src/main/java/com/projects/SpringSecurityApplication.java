package com.projects;

		import com.projects.Models.ApplicationUser;
		import com.projects.Models.Role;
		import com.projects.Repository.RoleRepository;
		import com.projects.Repository.UserRepository;
		import org.springframework.boot.CommandLineRunner;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.context.annotation.Bean;
		import org.springframework.security.crypto.password.PasswordEncoder;

		import java.util.HashSet;
		import java.util.Set;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository , UserRepository userRepository , PasswordEncoder passwordEncoder){
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return ;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			ApplicationUser admin = new ApplicationUser( "admin" ,
					passwordEncoder.encode("admin") , roles );
			userRepository.save(admin);
		};
	}

}
