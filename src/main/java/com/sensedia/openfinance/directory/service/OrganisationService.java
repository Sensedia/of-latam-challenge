package com.sensedia.openfinance.directory.service;

import com.sensedia.openfinance.directory.model.Organisation;
import com.sensedia.openfinance.directory.model.OrganisationSubStatus;
import com.sensedia.openfinance.directory.model.OrganisationStatus;
import com.sensedia.openfinance.directory.model.StatusChangeEvent;
import com.sensedia.openfinance.directory.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganisationService {
    private final OrganisationRepository organisationRepository;
    private final NotificationService notificationService;

    @Transactional(readOnly = true)
    public Page<Organisation> getAllOrganisations(Pageable pageable) {
        return organisationRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Organisation> getOrganisationById(Long id) {
        return organisationRepository.findById(id);
    }

    @Transactional
    public Organisation createOrganisation(Organisation organisation) {
        organisation.setActive(organisation.getStatus() == OrganisationStatus.ACTIVE);
        return organisationRepository.save(organisation);
    }

    @Transactional
    public Optional<Organisation> deleteOrganisation(Long id) {
        Optional<Organisation> organisation = organisationRepository.findById(id);
        organisation.ifPresent(org -> organisationRepository.deleteById(id));
        return organisation;
    }

    @Transactional
    public void updateOrganisationStatus(Organisation organisation, 
                                       OrganisationStatus newStatus,
                                       OrganisationSubStatus newSubStatus) {
        // Verificar si hay un cambio de ACTIVE a INACTIVE
        boolean shouldNotify = organisation.getStatus() == OrganisationStatus.ACTIVE 
                             && newStatus == OrganisationStatus.INACTIVE
                             && newSubStatus == OrganisationSubStatus.SYSTEM_MAINTENANCE;

        // Actualizar el estado
        organisation.setStatus(newStatus);
        organisation.setSubStatus(newSubStatus);
        organisation.setActive(newStatus == OrganisationStatus.ACTIVE);
        
        // Guardar los cambios
        organisationRepository.save(organisation);

        // Si debe notificar, crear y enviar las notificaciones
        if (shouldNotify) {
            notifyStatusChangeToParticipants(organisation);
        }
    }

    @Async
    protected void notifyStatusChangeToParticipants(Organisation changedOrganisation) {
        // Crear el evento de cambio de estado
        StatusChangeEvent event = StatusChangeEvent.builder()
                .timestamp(LocalDateTime.now())
                .eventType("CAMBIO_ESTADO")
                .organisation(changedOrganisation)
                .build();

        // Obtener todos los participantes activos excepto el que cambió
        List<Organisation> activeParticipants = 
            organisationRepository.findAllActiveExcept(changedOrganisation.getId());

        // Notificar a cada participante
        activeParticipants.forEach(participant -> 
            notificationService.notifyStatusChange(event, participant));
    }
}