package com.facturation.facture;

import com.facturation.facture.dao.JpaClientDao;
import com.facturation.facture.model.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class FacturationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturationApplication.class, args);
	}

	@Bean
	CommandLineRunner init(JpaClientDao clientDao) {
		return args -> {
			if (clientDao.count() == 0) {
				Client client = new Client();
				client.setNom("Client Test");
				client.setEmail("test@example.com");
				client.setDateCreation(LocalDate.now());
				client.setSiret("12345678900011");
				clientDao.save(client); // ✅ méthode save() OK
			}
		};
	}
}
