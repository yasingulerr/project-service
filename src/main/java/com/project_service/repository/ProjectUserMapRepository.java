package com.project_service.repository;

import com.project_service.model.ProjectUserMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProjectUserMapRepository extends JpaRepository<ProjectUserMap, Long> {
    List<ProjectUserMap> findByProjectId(Long projectId);
    ProjectUserMap findByUserEmailAndProjectId(String userEmail, Long projectId);
}
