package com.osttra.crds;

import com.osttra.crds.services.ClientService;
import com.osttra.crds.services.WorkflowStatusTrackerService;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Named
@Slf4j
public class FinalDataSubmission implements JavaDelegate {

    @Autowired
    ClientService clientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("All needed Approvals has been done... Process ID: {}",delegateExecution.getProcessInstanceId());
        clientService.createClientData(delegateExecution.getProcessInstanceId());
    }
}
