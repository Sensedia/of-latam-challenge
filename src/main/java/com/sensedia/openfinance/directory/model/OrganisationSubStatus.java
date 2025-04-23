package com.sensedia.openfinance.directory.model;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum OrganisationSubStatus {
    // Active states
    NORMAL("Operación normal con mecanismos habilitados"),
    ALTERNATIVE_MECHANISM("Solo mecanismo alternativo habilitado"),
    // Inactive states
    DISCONNECTED("Participante temporalmente autoexcluido"),
    SYSTEM_MAINTENANCE("Indisponibilidad operativa temporal"),
    SUSPENDED("Suspensión impuesta por CMF"),
    ONBOARDING("Participante aún no habilitado, en fase de configuración inicial");

    private final String description;
}