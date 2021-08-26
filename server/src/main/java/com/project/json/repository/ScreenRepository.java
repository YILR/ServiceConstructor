package com.project.json.repository;

import com.project.json.dto.ScreenDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<ScreenDto, String> {

    void deleteById(String id);
}
