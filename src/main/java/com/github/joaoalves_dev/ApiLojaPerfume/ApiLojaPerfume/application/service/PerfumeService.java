package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.service;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.mappers.PerfumeMapper;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumePort;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerfumeService implements PerfumePort {

    @Autowired
    private PerfumeRepository repository;

    @Autowired
    private PerfumeMapper mapper;


    @Override
    public ResponseEntity<Perfume> criaPerfume(PerfumeDTO perfumeDTO) {
        Perfume perfume = mapper.toEntity(perfumeDTO);
        return new ResponseEntity(repository.save(perfume), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PerfumeDTO> buscaPerfumeId(String id) {
        Optional<Perfume> resultado = repository.findById(id);

        if (!resultado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        PerfumeDTO retornoDTO = mapper.toDTO(resultado.get());
        return ResponseEntity.ok(retornoDTO);
    }

    @Override
    public ResponseEntity<List<PerfumeDTO>> buscaTodosPerfumes() {
        List<Perfume> perfume = repository.findAll();

        List<PerfumeDTO> listaPerfumesDTOs = perfume.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return new ResponseEntity(listaPerfumesDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> atualizaPerfume(String id, PerfumeDTO perfumeDTO) {
        Optional<Perfume> resultado = repository.findById(id);

        if (!resultado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Perfume dadosPerfumeAtualizar = mapper.toEntity(perfumeDTO);
        resultado.get().setNome(dadosPerfumeAtualizar.getNome());
        resultado.get().setPreco(dadosPerfumeAtualizar.getPreco());
        resultado.get().setMarca(dadosPerfumeAtualizar.getMarca());
        resultado.get().setVolume(dadosPerfumeAtualizar.getVolume());
        resultado.get().setGenero(dadosPerfumeAtualizar.getGenero());

        repository.save(resultado.get());
        return new ResponseEntity("Perfume atualizado com sucesso!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletaPerfumeId(String id) {
        Optional<Perfume> resultado = repository.findById(id);

        if (!resultado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return new ResponseEntity("Perfume deletado com sucesso", HttpStatus.OK);
    }
}
