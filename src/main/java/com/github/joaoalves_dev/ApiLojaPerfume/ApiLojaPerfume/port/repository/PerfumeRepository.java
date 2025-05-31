package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, String> {

    ResponseEntity<PerfumeDTO> findByNome(String nome);

    ResponseEntity<PerfumeDTO> findByMarca(String marca);

    ResponseEntity<PerfumeDTO> findByNomeAndVolume(String nome, Double volume);

    Optional<Perfume> findByNomeAndMarcaAndGeneroAndVolume(
            String nome, String marca, String genero, Double volume
    );
}
