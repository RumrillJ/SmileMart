package com.revature.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "statuses")
@Component
public class Status {

    /* PK can be a string and probably should be in this case so that it
    ** is constrained unique and table joins arent needed
    */
    @Id
    private String statusId;

    public Status() {
    }

    public Status(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Status{" +
                "statusId=" + statusId +
                '}';
    }
}