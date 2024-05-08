package com.revature.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "statuses")
@Component
public class Status {

    @Id
    private String statusId;
}
