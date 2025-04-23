package com.sensedia.openfinance.directory.service;

import com.sensedia.openfinance.directory.model.Organisation;
import com.sensedia.openfinance.directory.model.StatusChangeEvent;

public interface NotificationService {
    void notifyStatusChange(StatusChangeEvent event, Organisation recipient);
}