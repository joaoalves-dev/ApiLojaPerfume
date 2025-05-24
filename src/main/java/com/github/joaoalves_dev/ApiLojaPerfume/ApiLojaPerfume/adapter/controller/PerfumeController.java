package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.controller;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.service.PerfumeService;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumePort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfume")
public class PerfumeController implements PerfumePort {

    @Autowired
    private PerfumeService service;

    @Override
    @PostMapping
    public ResponseEntity<Perfume> criaPerfume(@RequestBody @Valid PerfumeDTO perfumeDTO) {
        return service.salvar(perfumeDTO);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PerfumeDTO> buscaPerfumeId(@PathVariable String id) {
        return service.buscaPerfumeId(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PerfumeDTO>> buscaTodosPerfumes() {
        return service.buscaPerfumes();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPerfumeId(@PathVariable String id) {
        return service.deletaPerfume(id);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizaPerfume(@PathVariable("id") String id, @RequestBody @Valid PerfumeDTO perfumeDTO) {
        return service.atualizaPerfume(id, perfumeDTO);
    }
}
