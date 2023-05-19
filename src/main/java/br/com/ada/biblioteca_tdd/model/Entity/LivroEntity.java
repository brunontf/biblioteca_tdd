package br.com.ada.biblioteca_tdd.model.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @Column(length = 500)
    private String resumo;
    private String sumario;
    private Double preco;
    private Integer numeroDePaginas;
    private String isbn;
    private LocalDate dataDePublicacao;

}
