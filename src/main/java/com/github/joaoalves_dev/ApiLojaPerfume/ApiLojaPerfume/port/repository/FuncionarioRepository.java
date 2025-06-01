package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {

    Optional<Funcionario> findByCpfAndNome(
            String cpf, String nome
    );
}
