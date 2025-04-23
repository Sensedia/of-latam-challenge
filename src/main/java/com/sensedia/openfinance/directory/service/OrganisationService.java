package com.sensedia.openfinance.directory.service;

import com.sensedia.openfinance.directory.model.Organisation;
import com.sensedia.openfinance.directory.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganisationService {
    private final OrganisationRepository organisationRepository;

    @Transactional(readOnly = true)
    public Page<Organisation> getAllOrganisations(Pageable pageable) {
        return organisationRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Organisation> getOrganisationById(Long id) {
        return organisationRepository.findById(id);
    }

    public Organisation createOrganisation(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    public Optional<Organisation> deleteOrganisation(Long id) {
        Optional<Organisation> organisation = organisationRepository.findById(id);
        organisation.ifPresent(org -> organisationRepository.deleteById(id));
        return organisation;
    }

    public void updateOrganisationStatus(Organisation organisation) {
        //code here
        throw new UnsupportedOperationException("not implemented");
    }
}