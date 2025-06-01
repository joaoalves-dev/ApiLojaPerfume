package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.service;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.mappers.PerfumeMapper;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.validator.PerfumeValidator;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumePort;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerfumeService implements PerfumePort {

    @Autowired
    private PerfumeValidator validator;

    @Autowired
    private PerfumeRepository repository;

    @Autowired
    private PerfumeMapper mapper;


    @Override
    public ResponseEntity<Perfume> criaPerfume(PerfumeDTO perfumeDTO, String id) {
        Perfume perfume = mapper.toEntity(perfumeDTO);
        validator.validaCadastro(perfume, id);

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
    public ResponseEntity<Page<PerfumeDTO>> buscaTodosPerfumes(Integer pagina, Integer tamanhoPagina) {

        Pageable pageable = PageRequest.of(pagina,tamanhoPagina);
        Page<Perfume> perfume = repository.findAll(pageable);

        Page<PerfumeDTO> resultado = perfume.map(mapper::toDTO);
        return ResponseEntity.ok(resultado);
    }

    @Override
    public ResponseEntity<Void> atualizaPerfume(String id, String cpfFuncionario, PerfumeDTO perfumeDTO) {
        validator.validaAtualizarRegistro(cpfFuncionario);
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
    public ResponseEntity<Void> deletaPerfumeId(String id, String cpfFuncionario) {
        validator.validaExclusao(cpfFuncionario);
        Optional<Perfume> resultado = repository.findById(id);

        if (!resultado.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return new ResponseEntity("Perfume deletado com sucesso", HttpStatus.OK);
    }

    public ResponseEntity<PerfumeDTO> pesquisaComFiltro(String nome, String marca, String genero, Double volume) {

        if (nome != null && volume != null) {
            return repository.findByNomeAndVolume(nome, volume);
        } else if (nome != null) {
            return repository.findByNome(nome);
        } else if (marca != null) {
            return repository.findByMarca(marca);
        }

        List<PerfumeDTO> listaPerfumesDTOs = repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return new ResponseEntity(listaPerfumesDTOs, HttpStatus.OK);
    }
}
