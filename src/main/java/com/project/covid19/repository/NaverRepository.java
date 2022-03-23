package com.project.covid19.repository;

import com.project.covid19.NaverVO;
import com.project.covid19.entity.Naver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface NaverRepository extends JpaRepository<Naver, UUID> {

    int countById(UUID id);

    @Query("select n.id, n.link from Naver n where id = :id")
    Object[] findColumnById(UUID id);

}
