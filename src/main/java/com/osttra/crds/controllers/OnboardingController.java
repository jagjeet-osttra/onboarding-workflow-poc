package com.osttra.crds.controllers;

import com.osttra.crds.dtos.CamundaTask;
import com.osttra.crds.dtos.ReviewTaskRequestDto;
import com.osttra.crds.dtos.StatusTrackerResponseDto;
import com.osttra.crds.services.ReviewTaskService;
import com.osttra.crds.services.WorkflowStatusTrackerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/client/onboarding")
@CrossOrigin(origins = "*")
@Slf4j
public class OnboardingController {
    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    RestTemplate restTemplate;

    private final WorkflowStatusTrackerService workflowStatusTrackerService;
    private final ReviewTaskService reviewTaskService;

    @PostMapping("/start")
    public ResponseEntity<String> startKyc() //@RequestBody Map<String,Object> payload
    {
       ProcessInstance instance = runtimeService.startProcessInstanceByKey("Client-onboarding"); //,payload
       StatusTrackerResponseDto statusTrackerResponseDto = workflowStatusTrackerService.createProcessId(instance.getId());
       log.info("Starting new process with id: {}",instance.getId());
       return ResponseEntity.ok(statusTrackerResponseDto.getProcessInstanceId());
    }
    @GetMapping("/get-task-id/{processInstanceId}")
    public ResponseEntity<String> taskId(@PathVariable(name = "processInstanceId") String processInstanceId)
    {
        log.info("Attempting to fetch task with process id: {}",processInstanceId);
        String camundaUrl = "http://localhost:8080/engine-rest/task?processInstanceId="+processInstanceId;
        ResponseEntity<CamundaTask[]> response = restTemplate.getForEntity(camundaUrl, CamundaTask[].class);
        CamundaTask[] camundaTask = response.getBody();
        String taskId = camundaTask[0].getId();
        log.info("Task id fetched successfully.Task id: {}",taskId);
        workflowStatusTrackerService.updateTaskIdByProcessId(processInstanceId,taskId);
        return ResponseEntity.ok(camundaTask[0].getId());
    }

    @PostMapping("/complete-task/{taskId}")
    public ResponseEntity<String> completeTask(@PathVariable(name = "taskId")String taskId,
                                               @RequestBody(required = false)ReviewTaskRequestDto reviewTaskRequestDto)
    {
        log.info("Attempting to complete task with id: {}",taskId);
        if(reviewTaskRequestDto!=null)
            reviewTaskService.createReviewTask(reviewTaskRequestDto,taskId);
        taskService.complete(taskId);
        log.info("Successfully submitted task with id:{}",taskId);
        return ResponseEntity.ok("Task has been completed!");
    }
}
