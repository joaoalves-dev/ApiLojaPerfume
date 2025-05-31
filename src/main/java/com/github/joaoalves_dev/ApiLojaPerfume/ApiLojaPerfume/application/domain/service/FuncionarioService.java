package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.service;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.FuncionarioDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.mappers.FuncionarioMapper;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.mappers.PerfumeMapper;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Funcionario;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.validator.FuncionarioValidator;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.validator.PerfumeValidator;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.FuncionarioPort;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.FuncionarioRepository;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService implements FuncionarioPort {

    @Autowired
    private FuncionarioValidator validator;

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FuncionarioMapper mapper;

    @Override
    public ResponseEntity<Funcionario> criaFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = mapper.toEntity(funcionarioDTO);
        validator.validar(funcionario);
        return new ResponseEntity(repository.save(funcionario), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<FuncionarioDTO> buscaFuncionarioId(String cpf) {
        Optional<Funcionario> resultado = repository.findById(cpf);

        if (!resultado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        FuncionarioDTO retornoDTO = mapper.toDTO(resultado.get());
        return ResponseEntity.ok(retornoDTO);
    }

    @Override
    public ResponseEntity<List<FuncionarioDTO>> buscaTodosFuncionarios() {
        List<Funcionario> funcionario = repository.findAll();

        List<FuncionarioDTO> listaFuncionariosDTOs = funcionario.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return new ResponseEntity(listaFuncionariosDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> atualizaFuncionario(String id, FuncionarioDTO funcionarioDTO) {
        Optional<Funcionario> resultado = repository.findById(id);

        if (!resultado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Funcionario dadosFuncionarioAtualizar = mapper.toEntity(funcionarioDTO);
        resultado.get().setNome(dadosFuncionarioAtualizar.getNome());
        resultado.get().setCargo(dadosFuncionarioAtualizar.getCargo());
        resultado.get().setEmail(dadosFuncionarioAtualizar.getEmail());

        repository.save(resultado.get());
        return new ResponseEntity("Funcionario atualizado com sucesso!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletaFuncionarioId(String id) {
        Optional<Funcionario> resultado = repository.findById(id);

        if (!resultado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return new ResponseEntity("Funcionario deletado com sucesso", HttpStatus.OK);
    }
}
