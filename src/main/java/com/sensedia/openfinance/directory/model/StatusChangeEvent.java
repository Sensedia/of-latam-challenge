package com.sensedia.openfinance.directory.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class StatusChangeEvent {
    private LocalDateTime timestamp;
    private String eventType;
    private Organisation organisation;
}