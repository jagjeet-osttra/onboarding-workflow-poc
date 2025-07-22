package com.osttra.crds.services.impl;

import com.osttra.crds.dtos.ReviewTaskRequestDto;
import com.osttra.crds.dtos.ReviewTaskResponseDto;
import com.osttra.crds.entities.ClientStaging;
import com.osttra.crds.entities.ReviewTask;
import com.osttra.crds.entities.WorkflowStatusTracker;
import com.osttra.crds.entities.enums.RequestStatus;
import com.osttra.crds.repositories.ReviewTaskRepository;
import com.osttra.crds.services.ClientStagingService;
import com.osttra.crds.services.ReviewTaskService;
import com.osttra.crds.services.WorkflowStatusTrackerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReviewTaskServiceImpl implements ReviewTaskService {

    private final ModelMapper modelMapper;
    private final ReviewTaskRepository reviewTaskRepository;
    private final WorkflowStatusTrackerService workflowStatusTrackerService;

    @Autowired
    ClientStagingService clientStagingService;
    @Override
    public ReviewTaskResponseDto getReviewTaskById(Long id) {
        ReviewTask reviewTask = getReviewTask(id);
        if(reviewTask==null)
            return null;
        return modelMapper.map(reviewTask,ReviewTaskResponseDto.class);
    }

    @Override
    public ReviewTaskResponseDto createReviewTask(ReviewTaskRequestDto reviewTaskRequestDto) {
        ReviewTask reviewTask = modelMapper.map(reviewTaskRequestDto,ReviewTask.class);
        return modelMapper.map(reviewTaskRepository.save(reviewTask),ReviewTaskResponseDto.class);
    }

    @Override
    @Transactional
    public ReviewTaskResponseDto createReviewTask(ReviewTaskRequestDto reviewTaskRequestDto,String taskId) {
        ReviewTask reviewTask = modelMapper.map(reviewTaskRequestDto,ReviewTask.class);
        reviewTask.setRequestStatus(RequestStatus.PENDING_REVIEW);
        ReviewTask savedReviewTask = reviewTaskRepository.save(reviewTask);
        ClientStaging clientStaging = ClientStaging.builder()
                .data(savedReviewTask.getStagedData()).reviewTask(savedReviewTask).build();
        Long clientStagingId = clientStagingService.createClientStagingData(clientStaging);
        WorkflowStatusTracker workflowStatusTracker = workflowStatusTrackerService.getWorkflowStatusTrackerByTaskId(taskId);
        workflowStatusTrackerService.updateClientStagingIdByProcessId(workflowStatusTracker.getProcessInstanceId(),clientStagingId);
        workflowStatusTrackerService.updateReviewTaskId(workflowStatusTracker.getProcessInstanceId(), savedReviewTask.getId());
        //updated client staging_id in Review task
        clientStaging.setId(clientStagingId);
        savedReviewTask.setClientStaging(clientStaging);
        reviewTaskRepository.save(savedReviewTask);
        return modelMapper.map(reviewTaskRepository.save(reviewTask),ReviewTaskResponseDto.class);
    }

    @Override
    public void updateRequestStatus(Long id, RequestStatus requestStatus) {
        ReviewTask reviewTask = getReviewTask(id);
        reviewTask.setRequestStatus(requestStatus);
        reviewTaskRepository.save(reviewTask);
    }

    ReviewTask getReviewTask(Long id)
    {
        return reviewTaskRepository.findById(id).orElse(null);
    }
}
