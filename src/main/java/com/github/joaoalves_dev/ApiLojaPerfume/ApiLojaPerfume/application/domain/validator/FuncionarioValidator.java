package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.validator;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.exceptions.RegistroDuplicadoException;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Funcionario;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FuncionarioValidator {

    @Autowired
    private FuncionarioRepository repository;

    public void validar(Funcionario funcionario) {
        if (existeFuncionarioCadastrado(funcionario)) {
            throw new RegistroDuplicadoException("Funcionario já cadastrado!");
        }
    }

    private boolean existeFuncionarioCadastrado(Funcionario funcionario) {
        Optional<Funcionario> funcionarioEncontrado = repository.findByCpfAndNomeAndCargoAndEmail(
                funcionario.getCpf(), funcionario.getNome(), funcionario.getCargo(), funcionario.getEmail()
        );

        if(funcionarioEncontrado.isPresent()){
         return true;
        }

        return false;
    }
}
