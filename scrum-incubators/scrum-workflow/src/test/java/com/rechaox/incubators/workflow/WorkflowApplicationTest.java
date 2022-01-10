package com.rechaox.incubators.workflow;

import com.rechaox.incubators.workflow.flow.WorkflowServiceManager;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Bing D. Yee
 * @since 2021/12/22
 */
@SpringBootTest(classes = WorkflowApplication.class)
public class WorkflowApplicationTest {

    public static final String EVECTION_PROCESS = "Evection_process";
    // 6cc1ab3a9cf143e49c9c266be2fb675f
    public static final String businessKey = "ff7d6250ebe2492aa6cfcee92e13dbec";

    @Autowired
    private WorkflowServiceManager workflowServiceManager;

    @Test
    @WithMockUser
    public void startProcessTest() {
        String businessKey = UUID.randomUUID().toString().replaceAll("-", "");
        HashMap<String, Object> variables = new HashMap<>(3);
        variables.put("hours", 73);
        ProcessInstance processInstance = workflowServiceManager.startProcessByKey(EVECTION_PROCESS, businessKey, variables);
        System.err.println(processInstance);
    }

    @Test
    @WithMockUser
    public void queryTaskTest() {
        Task task = workflowServiceManager.getTaskByBusinessKey(businessKey);
        HashMap<String, Object> variables = new HashMap<>(3);
        variables.put("hours", 73);
        workflowServiceManager.completeTaskByBusinessKey(businessKey, variables);
        Task nextTask = workflowServiceManager.getTaskByBusinessKey(businessKey);
        System.err.printf("%s -> %s", task.getTaskDefinitionKey(), nextTask.getTaskDefinitionKey());
    }

}
