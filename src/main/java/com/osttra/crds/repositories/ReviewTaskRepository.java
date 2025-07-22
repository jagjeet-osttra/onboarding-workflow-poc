package com.osttra.crds.repositories;

import com.osttra.crds.entities.ReviewTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewTaskRepository extends JpaRepository<ReviewTask,Long> {
}
