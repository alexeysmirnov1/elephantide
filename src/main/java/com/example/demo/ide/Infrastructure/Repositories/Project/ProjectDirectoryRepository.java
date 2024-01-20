package com.example.demo.ide.Infrastructure.Repositories.Project;

import com.example.demo.ide.Domain.Project.Entities.ProjectDirectory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDirectoryRepository extends CrudRepository<ProjectDirectory, Integer> {
    @Query("select max(id) from ProjectDirectory")
    Integer findMaxOrder();

    ProjectDirectory findByPath(String path);
}
