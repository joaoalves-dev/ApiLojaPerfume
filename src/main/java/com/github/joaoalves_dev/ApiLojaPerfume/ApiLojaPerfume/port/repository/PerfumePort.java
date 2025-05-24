package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PerfumePort {
    public ResponseEntity<Perfume> criaPerfume(PerfumeDTO perfumeDTO);

    public ResponseEntity<PerfumeDTO> buscaPerfumeId(String id);

    public ResponseEntity<List<PerfumeDTO>> buscaTodosPerfumes();

    public ResponseEntity<Void> atualizaPerfume(String id, PerfumeDTO perfumeDTO);

    public ResponseEntity<Void> deletaPerfumeId(String id);
}
