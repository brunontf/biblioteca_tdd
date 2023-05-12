package br.com.ada.biblioteca_tdd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.biblioteca_tdd.model.DTO.LivroDTO;
import br.com.ada.biblioteca_tdd.model.DTO.MensagemDTO;
import br.com.ada.biblioteca_tdd.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    LivroService livroService = new LivroService();

    @GetMapping
    public ResponseEntity<Object> listarLivros() {
        try {
            return ResponseEntity.ok(livroService.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MensagemDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> criar(@Valid @RequestBody LivroDTO livroDTO) {
        try {
            return ResponseEntity.ok()
                .body(livroService.criarLivro(livroDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MensagemDTO(e.getMessage()));
        }
    }

    public String saveBasicInfo(
        @Valid @ModelAttribute("useraccount") LivroDTO useraccount, 
        BindingResult result, 
        ModelMap model) {
            if (result.hasErrors()) {
                return "error";
            }
            return "success";
    }
}
