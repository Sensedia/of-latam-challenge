package com.sensedia.openfinance.directory.mapper;

import com.sensedia.openfinance.directory.dto.OrganisationDTO;
import com.sensedia.openfinance.directory.model.Organisation;
import com.sensedia.openfinance.directory.model.OrganisationStatus;
import org.springframework.stereotype.Component;

@Component
public class OrganisationMapper {
    
    public Organisation toEntity(OrganisationDTO dto) {
        return Organisation.builder()
                .id(dto.getId())
                .organisationName(dto.getOrganisationName())
                .status(dto.getStatus())
                .subStatus(dto.getSubStatus())
                .isActive(dto.getStatus() == OrganisationStatus.ACTIVE)
                .build();
    }

    public OrganisationDTO toDTO(Organisation entity) {
        return OrganisationDTO.builder()
                .id(entity.getId())
                .organisationName(entity.getOrganisationName())
                .status(entity.getStatus())
                .subStatus(entity.getSubStatus())
                .build();
    }
}