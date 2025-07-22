package com.osttra.crds.repositories;

import com.osttra.crds.entities.WorkflowStatusTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowStatusTrackerRepository extends JpaRepository<WorkflowStatusTracker,Long> {
    WorkflowStatusTracker findByProcessInstanceId(String processInstanceId);
    WorkflowStatusTracker findByTaskId(String taskId);
}
