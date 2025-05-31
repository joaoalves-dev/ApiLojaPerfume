package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.FuncionarioDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Funcionario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FuncionarioPort {

    public ResponseEntity<Funcionario> criaFuncionario(FuncionarioDTO FuncionarioDTO);

    public ResponseEntity<FuncionarioDTO> buscaFuncionarioId(String cpf);

    public ResponseEntity<List<FuncionarioDTO>> buscaTodosFuncionarios();

    public ResponseEntity<Void> atualizaFuncionario(String id, FuncionarioDTO FuncionarioDTO);

    public ResponseEntity<Void> deletaFuncionarioId(String id);

}
