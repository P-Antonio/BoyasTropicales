package com.Boyas.Tropicales;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.Boyas.Tropicales.Entity.Authority;
import com.Boyas.Tropicales.Entity.Role;
import com.Boyas.Tropicales.Entity.Usuario;
import com.Boyas.Tropicales.Entity.Enums.EnumRole;
import com.Boyas.Tropicales.Reposiory.UsuarioRepository;

@SpringBootApplication
public class BoyasTropicalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoyasTropicalesApplication.class, args);
	}

	@Bean
	CommandLineRunner init (UsuarioRepository usuarioRepository){
		return args->{
			
			Authority autorizacionRead = Authority.builder()
					.nombre("READ")
					.build();
			Authority autorizacionCreate = Authority.builder()
					.nombre("CREATE")
					.build();
			Authority autorizacionDelete = Authority.builder()
					.nombre("DELETE")
					.build();
			Authority autorizacionUpdate = Authority.builder()
					.nombre("UPDATE")
					.build();
			
			Role roleAdmin = Role.builder()
					.roles(EnumRole.ADMIN)
					.authorizations (Set.of(autorizacionRead, autorizacionCreate,autorizacionUpdate))
					.build();
			Role roleUser = Role.builder()
					.roles(EnumRole.USER)
					.authorizations(Set.of(autorizacionRead, autorizacionCreate))
					.build();
			
			/*crear usuarios*/
			Usuario usuarioPedro = Usuario.builder()
					.username("pedro")
					.password("$2a$10$BCQL/H5C/v/osCTp4LPc/.NMdAv0TCGInkYuzEvhfzDTWZ0ouH67u")
					.roles(Set.of(roleAdmin))
					.build();
			
			Usuario usuarioJohn = Usuario.builder()
					.username("John")
					.password("$2a$10$BCQL/H5C/v/osCTp4LPc/.NMdAv0TCGInkYuzEvhfzDTWZ0ouH67u")
					.roles(Set.of(roleAdmin))
					.build();
			
			usuarioRepository.saveAll(List.of(usuarioPedro, usuarioJohn));
		};
	}
}
