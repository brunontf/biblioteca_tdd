package br.com.ada.biblioteca_tdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ada.biblioteca_tdd.model.DTO.LivroDTO;

@SpringBootApplication
public class BibliotecaTddApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaTddApplication.class, args);
		
	}

}
