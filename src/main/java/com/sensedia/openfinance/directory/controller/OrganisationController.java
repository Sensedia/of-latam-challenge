package com.sensedia.openfinance.directory.controller;

import com.sensedia.openfinance.directory.dto.OrganisationDTO;
import com.sensedia.openfinance.directory.mapper.OrganisationMapper;
import com.sensedia.openfinance.directory.model.Organisation;
import com.sensedia.openfinance.directory.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/organisations")
@RequiredArgsConstructor
public class OrganisationController {

    private final OrganisationService organisationService;
    private final OrganisationMapper organisationMapper;

    @GetMapping
    public ResponseEntity<Page<OrganisationDTO>> getAllOrganisations(Pageable pageable) {
        Page<Organisation> organisations = organisationService.getAllOrganisations(pageable);
        Page<OrganisationDTO> dtos = organisations.map(organisationMapper::toDTO);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganisationDTO> getOrganisationById(@PathVariable Long id) {
        return organisationService.getOrganisationById(id)
                .map(organisationMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrganisationDTO> createOrganisation(@Valid @RequestBody OrganisationDTO organisationDTO) {
        Organisation organisation = organisationMapper.toEntity(organisationDTO);
        Organisation savedOrganisation = organisationService.createOrganisation(organisation);
        return new ResponseEntity<>(organisationMapper.toDTO(savedOrganisation), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganisationDTO> updateOrganisation(
            @PathVariable Long id,
            @Valid @RequestBody OrganisationDTO organisationDTO) {
        
        return organisationService.getOrganisationById(id)
                .map(existingOrg -> {
                    organisationDTO.setId(id);
                    Organisation organisation = organisationMapper.toEntity(organisationDTO);
                    organisationService.updateOrganisationStatus(
                        organisation
                    );
                    return ResponseEntity.ok(organisationDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganisation(@PathVariable Long id) {
        return organisationService.deleteOrganisation(id)
                .map(org -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrganisationDTO> updateOrganisationStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrganisationDTO organisationDTO) {
        
        //code here
        throw new UnsupportedOperationException("not implemented");

    }
}