package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.controller;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumePort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfume")
public class PerfumeController {

    @Autowired
    private PerfumePort perfumePort;

    @PostMapping
    public ResponseEntity<Perfume> criaPerfume(@RequestBody @Valid PerfumeDTO perfumeDTO) {
        return perfumePort.criaPerfume(perfumeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfumeDTO> buscaPerfumeId(@PathVariable String id) {
        return perfumePort.buscaPerfumeId(id);
    }

    @GetMapping
    public ResponseEntity<List<PerfumeDTO>> buscaTodosPerfumes() {
        return perfumePort.buscaTodosPerfumes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPerfumeId(@PathVariable String id) {
        return perfumePort.deletaPerfumeId(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizaPerfume(@PathVariable("id") String id, @RequestBody @Valid PerfumeDTO perfumeDTO) {
        return perfumePort.atualizaPerfume(id, perfumeDTO);
    }
}
