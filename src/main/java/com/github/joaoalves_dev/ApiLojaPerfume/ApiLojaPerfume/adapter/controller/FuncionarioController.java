package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.controller;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.FuncionarioDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Funcionario;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.FuncionarioPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioPort FuncionarioPort;

    @PostMapping
    public ResponseEntity<Funcionario> criaFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO) {
        return FuncionarioPort.criaFuncionario(funcionarioDTO);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioDTO> buscaFuncionarioId(@PathVariable String cpf) {
        return FuncionarioPort.buscaFuncionarioId(cpf);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> buscaTodosFuncionarios() {
        return FuncionarioPort.buscaTodosFuncionarios();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaFuncionarioId(@PathVariable String id) {
        return FuncionarioPort.deletaFuncionarioId(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizaFuncionario(@PathVariable("id") String id, @RequestBody @Valid FuncionarioDTO FuncionarioDTO) {
        return FuncionarioPort.atualizaFuncionario(id, FuncionarioDTO);
    }
}
