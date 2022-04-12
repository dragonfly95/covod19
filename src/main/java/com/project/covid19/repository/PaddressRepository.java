package com.project.covid19.repository;

import com.project.covid19.entity.Paddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaddressRepository extends JpaRepository<Paddress, Long> {
}
