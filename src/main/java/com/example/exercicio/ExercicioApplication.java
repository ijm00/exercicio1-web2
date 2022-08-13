package com.example.exercicio;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.exercicio.model.Group;
import com.example.exercicio.model.User;
import com.example.exercicio.repository.GroupRepository;
import com.example.exercicio.repository.UserRepository;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class ExercicioApplication {
	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(ExercicioApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExercicioApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadDB(UserRepository userRepository, GroupRepository groupRepository) throws Exception {
		return (args) -> {
			LOGGER.info("Carregando a base de dados...");

			Group groupUsers = groupRepository.findByCode("users").get(0);
			Group groupAdmins = groupRepository.findByCode("admins").get(0);

			var user1 = new User();
			user1.setName("Joaquina");
			user1.setAge(25);
			user1.setVatNumber("AB987654321");
			user1.setEmail("joaquina.aqwe@asidj.com");
			user1.setGroup(groupUsers);

			var user2 = new User();
			user2.setName("Pedro");
			user2.setAge(41);
			user2.setVatNumber("CD123456789");
			user2.setEmail("pp_xpto@asidj.com");
			user2.setGroup(groupUsers);

			userRepository.save(user1);
			userRepository.save(user2);

			var admin1 = new User();
			admin1.setName("Administrador 1");
			admin1.setAge(42);
			admin1.setVatNumber("BC938475612");
			admin1.setEmail("admin1@admin.com");
			admin1.setGroup(groupAdmins);

			var admin2 = new User();
			admin2.setName("Administrador 2");
			admin2.setAge(42);
			admin2.setVatNumber("CD954786132");
			admin2.setEmail("admin2@admin.com");
			admin2.setGroup(groupAdmins);

			userRepository.save(admin1);
			userRepository.save(admin2);


			userRepository.findAll().forEach((u) -> {
				LOGGER.info("User criado com sucesso" + u + "\n");
			});

			LOGGER.info("A base de dados esta carregada");
		};
	}

}
