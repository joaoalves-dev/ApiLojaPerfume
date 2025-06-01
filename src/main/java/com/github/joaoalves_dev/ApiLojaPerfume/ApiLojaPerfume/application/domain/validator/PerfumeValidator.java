package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.validator;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.enums.FuncionarioCargo;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.exceptions.OperacaoNaoPermitidaException;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.exceptions.RegistroDuplicadoException;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Funcionario;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.FuncionarioRepository;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PerfumeValidator {

    @Autowired
    private PerfumeRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void validaCadastro(Perfume perfume, String id) {
        if(existePerfumeCadastrado(perfume)){
            throw new RegistroDuplicadoException("Perfume já cadastrado!");
        }

        Funcionario funcionario = existeFuncionarioCadastrado(id);
        if (!funcionario.getCargo().name().equalsIgnoreCase(FuncionarioCargo.GERENTE.name()) &&
                !funcionario.getCargo().name().equalsIgnoreCase(FuncionarioCargo.VENDEDOR.name())) {
            throw new OperacaoNaoPermitidaException("Funcionário não tem permissão! Somente GERENTE e VENDEDOR pode cadastrar registro");
        }
    }

    public void validaAtualizarRegistro(String cpfFuncionario) {
        Funcionario funcionario = existeFuncionarioCadastrado(cpfFuncionario);
        if (!funcionario.getCargo().name().equalsIgnoreCase(FuncionarioCargo.GERENTE.name()) &&
                !funcionario.getCargo().name().equalsIgnoreCase(FuncionarioCargo.VENDEDOR.name())) {
            throw new OperacaoNaoPermitidaException("Funcionário não tem permissão! Somente GERENTE e VENDEDOR pode cadastrar registro");
        }
    }

    public void validaExclusao(String id) {
        Funcionario funcionario = existeFuncionarioCadastrado(id);
        if (!funcionario.getCargo().name().equalsIgnoreCase(FuncionarioCargo.GERENTE.name())) {
            throw new OperacaoNaoPermitidaException("Funcionário não tem permissão! Somente GERENTE pode excluir registro!");
        }
    }

    private Funcionario existeFuncionarioCadastrado(String id) {
        Optional<Funcionario> funcionarioEncontrado = funcionarioRepository.findById(id);

        return funcionarioEncontrado.orElseGet(Funcionario::new);
    }

    private boolean existePerfumeCadastrado(Perfume perfume){
        Optional<Perfume> perfumeEncontrado = repository.findByNomeAndMarcaAndGeneroAndVolume(
                perfume.getNome(), perfume.getMarca(), perfume.getGenero(), perfume.getVolume()
        );

        if(perfumeEncontrado.isPresent()){
            return true;
        }

        return false;
    }
}
