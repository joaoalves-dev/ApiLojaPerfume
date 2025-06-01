package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.controller;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository.PerfumePort;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfume")
public class PerfumeController {

    @Autowired
    private PerfumePort perfumePort;

    @PostMapping("{id}")
    public ResponseEntity<Perfume> criaPerfume(@RequestBody @Valid PerfumeDTO perfumeDTO, @PathVariable("id") String id) {
        return perfumePort.criaPerfume(perfumeDTO, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfumeDTO> buscaPerfumeId(@PathVariable String id) {
        return perfumePort.buscaPerfumeId(id);
    }

    @GetMapping
    public ResponseEntity<Page<PerfumeDTO>> buscaTodosPerfumes(
            @RequestParam(value = "pagina", defaultValue = "0")
            Integer pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "10")
            Integer tamanhoPagina
    ) {
        return perfumePort.buscaTodosPerfumes(pagina, tamanhoPagina);
    }

    @DeleteMapping("/{id}/{cpf}")
    public ResponseEntity<Void> deletaPerfumeId(@PathVariable String id, @PathVariable("cpf") String cpfFuncionario) {
        return perfumePort.deletaPerfumeId(id, cpfFuncionario);
    }

    @PatchMapping("/{id}/{cpf}")
    public ResponseEntity<Void> atualizaPerfume(@PathVariable("id") String id, @PathVariable("cpf") String cpfFuncionario, @RequestBody @Valid PerfumeDTO perfumeDTO) {
        return perfumePort.atualizaPerfume(id, cpfFuncionario, perfumeDTO );
    }
}
