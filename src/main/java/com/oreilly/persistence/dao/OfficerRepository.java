package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {
}
