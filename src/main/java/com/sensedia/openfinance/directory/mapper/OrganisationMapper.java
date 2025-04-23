package com.sensedia.openfinance.directory.mapper;

import com.sensedia.openfinance.directory.dto.OrganisationDTO;
import com.sensedia.openfinance.directory.model.Organisation;
import org.springframework.stereotype.Component;

@Component
public class OrganisationMapper {
    
    public Organisation toEntity(OrganisationDTO dto) {
        return Organisation.builder()
                .id(dto.getId())
                .organisationName(dto.getOrganisationName())



                .build();
    }

    public OrganisationDTO toDTO(Organisation entity) {
        return OrganisationDTO.builder()
                .id(entity.getId())
                .organisationName(entity.getOrganisationName())

                .build();
    }
}