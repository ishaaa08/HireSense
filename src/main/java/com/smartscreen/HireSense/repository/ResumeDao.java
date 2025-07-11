package com.smartscreen.HireSense.repository;

import com.smartscreen.HireSense.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeDao extends JpaRepository<ResumeEntity, Long> {
}
