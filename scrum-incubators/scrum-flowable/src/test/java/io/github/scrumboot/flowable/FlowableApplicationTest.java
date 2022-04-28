package io.github.scrumboot.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author Bing D. Yee
 * @since 2022/04/28
 */
@SpringBootTest(classes = FlowableApplication.class)
public class FlowableApplicationTest {

    public static final String PROCESS_ID = "back_process";

    private final Logger log = LoggerFactory.getLogger(FlowableApplicationTest.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void deployProcess() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/back_process.bpmn")
                .name("任务回退").deploy();
        log.info("部署ID：{}，名称：{}，时间：{}", deployment.getId(), deployment.getName(), LocalDateTime.now());
    }

    @Test
    public void startProcess() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(PROCESS_ID);
        log.info("实例ID：{}", processInstance.getId());
    }

    @Test
    public void completeTask() {
        Task task = taskService.createTaskQuery()
                .processDefinitionId(PROCESS_ID)
                .taskAssignee("user1")
                .singleResult();
        taskService.complete(task.getId());
    }

    @Test
    public void backProcess() {
        runtimeService.createChangeActivityStateBuilder();
    }

}

