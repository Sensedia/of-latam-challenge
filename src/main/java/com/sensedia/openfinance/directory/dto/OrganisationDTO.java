package com.sensedia.openfinance.directory.dto;

import com.sensedia.openfinance.directory.model.OrganisationSubStatus;
import com.sensedia.openfinance.directory.model.OrganisationStatus;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationDTO {
    private Long id;
    private String organisationName;
    private OrganisationStatus status;
    private OrganisationSubStatus subStatus;
}