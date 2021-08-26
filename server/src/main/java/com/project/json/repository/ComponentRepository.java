package com.project.json.repository;

import com.project.json.dto.ComponentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<ComponentDto, String> {


}
