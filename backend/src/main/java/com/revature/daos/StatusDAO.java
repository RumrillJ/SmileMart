package com.revature.DAOs;

import com.revature.models.Status;
import com.revature.models.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDAO extends JpaRepository<Status, String> {
    public Optional<Status> findByStatus(String status);
}
