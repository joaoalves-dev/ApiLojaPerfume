package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.validator;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.exceptions.RegistroDuplicadoException;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PerfumeValidator {

    @Autowired
    private PerfumeRepository repository;

    public void validar(Perfume perfume){
        if(existePerfumeCadastrado(perfume)){
            throw new RegistroDuplicadoException("Perfume já cadastrado!");
        }
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
