package com.livioneiva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.livioneiva.model.entity.Cliente;
import com.livioneiva.model.repository.ClienteRepository;

@SpringBootApplication
public class ClienteApplication {
	
	@Bean //com a anotação bean esse metodo será scanneado, e CommandLine executa a ação em tempo de execução do sistemas
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args ->{
			Cliente cliente = Cliente.builder().cpf("76679268320").nome("Jose Alencar").build();
			repository.save(cliente);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}

}
