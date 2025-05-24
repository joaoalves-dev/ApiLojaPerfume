package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.mappers;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.PerfumeDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Perfume;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PerfumeMapper {
    PerfumeDTO toDTO(Perfume perfume);
    Perfume toEntity(PerfumeDTO perfumeDTO);
}
