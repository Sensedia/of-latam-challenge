package com.sensedia.openfinance.directory.model;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organisation {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String organisationName;

    private boolean isActive;
}