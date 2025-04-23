package com.sensedia.openfinance.directory.service;

import com.sensedia.openfinance.directory.model.Organisation;
import com.sensedia.openfinance.directory.model.StatusChangeEvent;
import org.springframework.stereotype.Service;

@Service
public class ConsoleNotificationService implements NotificationService {
    @Override
    public void notifyStatusChange(StatusChangeEvent event, Organisation recipient) {
        System.out.println("Notificación para: " + recipient.getOrganisationName());
        System.out.println("Fecha y hora: " + event.getTimestamp());
        System.out.println("Tipo de evento: " + event.getEventType());
        System.out.println("Organización origen: " + event.getOrganisation().getOrganisationName());
        System.out.println("Nuevo estado: " + event.getOrganisation().getStatus());
        System.out.println("Nuevo sub-estado: " + event.getOrganisation().getSubStatus());
        System.out.println("----------------------------------------");
    }
}