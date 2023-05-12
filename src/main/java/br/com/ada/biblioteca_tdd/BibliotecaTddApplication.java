package br.com.ada.biblioteca_tdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ada.biblioteca_tdd.model.DTO.LivroDTO;

@SpringBootApplication
public class BibliotecaTddApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaTddApplication.class, args);
		// LivroDTO livroDTO = new LivroDTO("titulo", "resumo", 10.0d, 100, "isbn");
		// LivroDTO livroDTO = new LivroDTO();
		// System.out.println(livroDTO.getNumeroDePaginas() + " nPPPPPPPPPPPPPPP " + livroDTO.getTitulo());
	}

}
