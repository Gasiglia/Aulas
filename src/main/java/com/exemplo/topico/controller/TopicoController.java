package com.exemplo.topico.controller;

import com.exemplo.topico.model.Topico;
import com.exemplo.topico.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @GetMapping
    public List<Topico> listar() {
        return repository.findAll();
    }
}
