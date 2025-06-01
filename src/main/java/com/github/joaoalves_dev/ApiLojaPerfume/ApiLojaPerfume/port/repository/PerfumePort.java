package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.port.repository;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PerfumePort {
    public ResponseEntity<Perfume> criaPerfume(PerfumeDTO perfumeDTO, String id);

    public ResponseEntity<PerfumeDTO> buscaPerfumeId(String id);

    public ResponseEntity<Page<PerfumeDTO>> buscaTodosPerfumes(Integer pagina, Integer tamanhoPagina);

    public ResponseEntity<Void> atualizaPerfume(String id, String cpfFuncionario, PerfumeDTO perfumeDTO);

    public ResponseEntity<Void> deletaPerfumeId(String id, String cpfFuncionario);

    public ResponseEntity<PerfumeDTO> pesquisaComFiltro(String nome, String marca, String genero, Double volume);
}
