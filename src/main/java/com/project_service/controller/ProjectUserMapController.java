package com.project_service.controller;

import com.project_service.model.ProjectUserMap;
import com.project_service.repository.ProjectUserMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project-users")
@CrossOrigin(origins = "http://localhost:3000") // Vue tarafından istek alınabilmesi için
public class ProjectUserMapController {

    @Autowired
    private ProjectUserMapRepository projectUserMapRepository;

    @GetMapping
    public List<ProjectUserMap> getAllProjectUserMaps() {
        return projectUserMapRepository.findAll();
    }

    @GetMapping("/{projectId}")
    public List<ProjectUserMap> getProjectUserMapsById(@PathVariable Long projectId) {
        return projectUserMapRepository.findByProjectId(projectId);
    }

    @PostMapping
    public ProjectUserMap createProjectUserMap(@RequestBody ProjectUserMap projectUserMap) {
        return projectUserMapRepository.save(projectUserMap);
    }

    @DeleteMapping
    public ResponseEntity<String> removeProjectUserMap(@RequestBody ProjectUserMap request) {
        try {
            ProjectUserMap projectUserMap = projectUserMapRepository.findByUserEmailAndProjectId(request.getUserEmail(), request.getProjectId());

            if (projectUserMap != null) {
                projectUserMapRepository.delete(projectUserMap);
                return ResponseEntity.ok("Çalışan projeden başarıyla çıkarıldı.");
            } else {
                return ResponseEntity.status(404).body("Çalışan bulunamadı veya projeye atanmadı.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Silme işlemi sırasında bir hata oluştu.");
        }
    }

}
