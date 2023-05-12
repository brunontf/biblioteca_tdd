package br.com.ada.biblioteca_tdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ada.biblioteca_tdd.model.DTO.LivroDTO;
import br.com.ada.biblioteca_tdd.model.Entity.LivroEntity;
import br.com.ada.biblioteca_tdd.model.Mapper.LivroMapper;
import br.com.ada.biblioteca_tdd.repository.LivroRepository;

@Service
public class LivroService {
    @Autowired
    LivroMapper livroMapper;
    @Autowired
    LivroRepository livroRepository;

    public LivroDTO criarLivro(LivroDTO livroDTO) {
        LivroEntity livroEntity = livroMapper.update(livroDTO);
        livroRepository.save(livroEntity);
        return livroMapper.update(livroEntity);
    }

    public List<LivroDTO> listarTodos() {
        List<LivroEntity> listaEntities = livroRepository.findAll();
        return livroMapper.updateListaLivroDTO(listaEntities);
    }

    
    
}
