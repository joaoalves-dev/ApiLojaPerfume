package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.mappers;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.adapter.dto.FuncionarioDTO;
import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {
    FuncionarioDTO toDTO(Funcionario Funcionario);
    Funcionario toEntity(FuncionarioDTO FuncionarioDTO);
}
