package com.osttra.crds.services.impl;

import com.osttra.crds.dtos.StatusTrackerResponseDto;
import com.osttra.crds.entities.Client;
import com.osttra.crds.entities.ClientStaging;
import com.osttra.crds.entities.WorkflowStatusTracker;
import com.osttra.crds.entities.enums.RequestStatus;
import com.osttra.crds.repositories.WorkflowStatusTrackerRepository;
import com.osttra.crds.services.ClientService;
import com.osttra.crds.services.ClientStagingService;
import com.osttra.crds.services.ReviewTaskService;
import com.osttra.crds.services.WorkflowStatusTrackerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkflowStatusTrackerServiceImpl implements WorkflowStatusTrackerService {

    private final ModelMapper modelMapper;
    private final WorkflowStatusTrackerRepository workflowStatusTrackerRepository;

    @Override
    public WorkflowStatusTracker getDetailsByProcessId(String processInstanceId) {
        return workflowStatusTrackerRepository.findByProcessInstanceId(processInstanceId);
    }

    @Override
    public void updateTaskIdByProcessId(String processInstanceId, String taskId) {
    WorkflowStatusTracker workflowStatusTracker = workflowStatusTrackerRepository.findByProcessInstanceId(processInstanceId);
    workflowStatusTracker.setTaskId(taskId);
    workflowStatusTrackerRepository.save(workflowStatusTracker);
    }

    @Override
    public void updateClientIdByProcessId(String processInstanceId, Long clientId) {
        WorkflowStatusTracker workflowStatusTracker = workflowStatusTrackerRepository.findByProcessInstanceId(processInstanceId);
        workflowStatusTracker.setClientId(clientId);
        workflowStatusTrackerRepository.save(workflowStatusTracker);
    }

    @Override
    public void updateClientStagingIdByProcessId(String processInstanceId, Long clientStagingId) {
        WorkflowStatusTracker workflowStatusTracker = workflowStatusTrackerRepository.findByProcessInstanceId(processInstanceId);
        workflowStatusTracker.setClientStagingId(clientStagingId);
        workflowStatusTrackerRepository.save(workflowStatusTracker);
    }

    @Override
    public StatusTrackerResponseDto createProcessId(String processId) {
        WorkflowStatusTracker workflowStatusTracker = new WorkflowStatusTracker();
        workflowStatusTracker.setProcessInstanceId(processId);
        return modelMapper.map(workflowStatusTrackerRepository.save(workflowStatusTracker),StatusTrackerResponseDto.class);
    }

    @Override
    public void createClientFromProcessId(String processId) {
//        WorkflowStatusTracker workflowStatusTracker = workflowStatusTrackerRepository.findByProcessInstanceId(processId);
//        ClientStaging clientStaging = clientStagingService.getClientStagingDataById(workflowStatusTracker.getClientStagingId());
//        Client client = Client.builder().data(clientStaging.getData()).build();
//        Long clientId = clientService.createClientData(client);
//        workflowStatusTracker.setClientId(clientId);
//        workflowStatusTrackerRepository.save(workflowStatusTracker);
//        reviewTaskService.updateRequestStatus(workflowStatusTracker.getReviewTaskId(), RequestStatus.APPROVED);
    }

    @Override
    public WorkflowStatusTracker getWorkflowStatusTrackerByTaskId(String taskId) {
        return workflowStatusTrackerRepository.findByTaskId(taskId);
    }

    @Override
    public void updateWorkflowStatusTrackerById(WorkflowStatusTracker workflowStatusTracker) {
        workflowStatusTrackerRepository.save(workflowStatusTracker);
    }

    @Override
    public void updateReviewTaskId(String processInstanceId, Long reviewTaskId) {
        WorkflowStatusTracker workflowStatusTracker = workflowStatusTrackerRepository.findByProcessInstanceId(processInstanceId);
        workflowStatusTracker.setReviewTaskId(reviewTaskId);
        workflowStatusTrackerRepository.save(workflowStatusTracker);
    }
}
