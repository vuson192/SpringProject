package com.sonvv.spring.repository;

import com.sonvv.spring.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    // Có thể custom thêm query nếu muốn
}
