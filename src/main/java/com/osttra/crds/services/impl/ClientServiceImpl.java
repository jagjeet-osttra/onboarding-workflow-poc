package com.osttra.crds.services.impl;

import com.osttra.crds.entities.Client;
import com.osttra.crds.entities.ClientStaging;
import com.osttra.crds.entities.WorkflowStatusTracker;
import com.osttra.crds.entities.enums.RequestStatus;
import com.osttra.crds.repositories.ClientRepository;
import com.osttra.crds.services.ClientService;
import com.osttra.crds.services.ClientStagingService;
import com.osttra.crds.services.ReviewTaskService;
import com.osttra.crds.services.WorkflowStatusTrackerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final WorkflowStatusTrackerService workflowStatusTrackerService;
    private final ClientStagingService clientStagingService;
    private final ReviewTaskService reviewTaskService;

    @Override
    public void createClientData(String processId) {
        WorkflowStatusTracker workflowStatusTracker = workflowStatusTrackerService.getDetailsByProcessId(processId);
        ClientStaging clientStaging = clientStagingService.getClientStagingDataById(workflowStatusTracker.getClientStagingId());
        Client client = Client.builder().data(clientStaging.getData()).build();
        Long clientId = createClientData(client);
        workflowStatusTracker.setClientId(clientId);
        workflowStatusTrackerService.updateWorkflowStatusTrackerById(workflowStatusTracker);
        reviewTaskService.updateRequestStatus(workflowStatusTracker.getReviewTaskId(), RequestStatus.APPROVED);
    }

    Long createClientData(Client client)
    {
        Client savedClient = clientRepository.save(client);
        return savedClient.getId();
    }

}
