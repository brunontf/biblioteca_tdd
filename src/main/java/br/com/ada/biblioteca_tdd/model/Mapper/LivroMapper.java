package br.com.ada.biblioteca_tdd.model.Mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ada.biblioteca_tdd.model.DTO.LivroDTO;
import br.com.ada.biblioteca_tdd.model.Entity.LivroEntity;

@Component
public class LivroMapper {
    public LivroDTO update(LivroEntity livroEntity) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livroEntity.getId());
        livroDTO.setIsbn(livroEntity.getIsbn());
        livroDTO.setNumeroDePaginas(livroEntity.getNumeroDePaginas());
        livroDTO.setPreco(livroEntity.getPreco());
        livroDTO.setResumo(livroEntity.getResumo());
        livroDTO.setSumario(livroEntity.getSumario());
        livroDTO.setTitulo(livroEntity.getTitulo());
        livroDTO.setDataDePublicacao(livroEntity.getDataDePublicacao());
        return livroDTO;
    }

    public LivroEntity update(LivroDTO livroDTO) {
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setIsbn(livroDTO.getIsbn());
        livroEntity.setDataDePublicacao(livroDTO.getDataDePublicacao());
        livroEntity.setNumeroDePaginas(livroDTO.getNumeroDePaginas());
        livroEntity.setPreco(livroDTO.getPreco());
        livroEntity.setResumo(livroDTO.getResumo());
        livroEntity.setSumario(livroDTO.getSumario());
        livroEntity.setTitulo(livroDTO.getTitulo());

        return livroEntity;
    }

    public List<LivroDTO> updateListaLivroDTO (List<LivroEntity> listaLivroEntity) {
        return listaLivroEntity.stream().map(livroEntity -> this.update(livroEntity)).toList();
    }

}
