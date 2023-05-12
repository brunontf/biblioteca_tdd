package br.com.ada.biblioteca_tdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ada.biblioteca_tdd.model.Entity.LivroEntity;

public interface LivroRepository extends JpaRepository<LivroEntity,Long> {
    
}
