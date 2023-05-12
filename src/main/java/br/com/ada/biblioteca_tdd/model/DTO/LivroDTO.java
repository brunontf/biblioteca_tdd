package br.com.ada.biblioteca_tdd.model.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
// @RequiredArgsConstructor
// @AllArgsConstructor
public class LivroDTO {
    private Long id;

    @NotBlank(message = "resumo não pode ser em branco")
    private String titulo;

    @NotBlank(message = "resumo não pode ser em branco")
    @Size(max = 500, message = "Tamanho acima do permitido. Máximo de 500 caracteres.")
    private String resumo;

    private String sumario;

    @NotNull
    @Min(value = 20, message = "Tamanho abaixo do permitido. Mínimo de 20 caracteres.")
    private Double preco;

    @NotNull
    @Min(value = 100, message = "Tamanho abaixo do permitido. Mínimo de 100 caracteres.")
    private int numeroDePaginas;

    @NotBlank(message = "isbn não pode ser branco")
    private String isbn;

    @Future
    private LocalDate dataDePublicacao;
}
