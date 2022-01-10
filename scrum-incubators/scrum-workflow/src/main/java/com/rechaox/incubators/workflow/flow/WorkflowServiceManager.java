package com.rechaox.incubators.workflow.flow;

import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 工作流组件
 *
 *
 * @author Bing D. Yee
 * @since 2021/07/31
 */
@Component
public class WorkflowServiceManager {

    private final TaskService taskService;

    private final RepositoryService repositoryService;

    private final RuntimeService runtimeService;

    private final HistoryService historyService;

    private final ManagementService managementService;

    public WorkflowServiceManager(TaskService taskService, RepositoryService repositoryService, RuntimeService runtimeService, HistoryService historyService, TaskRuntime taskRuntime, ManagementService managementService) {
        this.taskService = taskService;
        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
        this.historyService = historyService;
        this.managementService = managementService;
    }

    /**
     * 通过流程定义key启动流程实例
     *
     * @param processKey 流程定义key
     * @return 流程实例
     */
    public ProcessInstance startProcessByKey(String processKey) {
        return runtimeService.startProcessInstanceByKey(processKey);
    }

    /**
     * 启动流程实例
     *
     * @param processKey 流程定义key
     * @param businessKey 业务key
     * @return 流程实例
     */
    public ProcessInstance startProcessByKey(String processKey, String businessKey) {
        return runtimeService.startProcessInstanceByKey(processKey, businessKey);
    }

    /**
     * 启动流程实例
     *
     * @param processKey 流程定义ke
     * @param businessKey 业务key
     * @param variables 流程变量
     * @return 流程实例
     */
    public ProcessInstance startProcessByKey(String processKey, String businessKey, Map<String, Object> variables) {
        return runtimeService.startProcessInstanceByKey(processKey, businessKey, variables);
    }

    /**
     * 发布流程
     *
     * @param deployName 流程名称
     * @param classpathResource  路径
     */
    public void deployProcess(String deployName, String classpathResource) {
        repositoryService.createDeployment().name(deployName).addClasspathResource(classpathResource).deploy();
    }

    /**
     * 删除流程
     *
     * @param deploymentId 部署ID
     */
    public void deleteDeployment(String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
    }

    /**
     * 完成任务
     *
     * @param businessKey 业务key
     */
    public void completeTask(String businessKey) {
        List<Task> taskList = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
        for (Task task : taskList) {
            taskService.complete(task.getId());
        }
    }

    /**
     * 完成任务
     *
     * @param businessKey 业务key
     * @param variables 流程变量
     */
    public void completeTaskByBusinessKey(String businessKey, Map<String, Object> variables) {
        List<Task> taskList = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
        for (Task task : taskList) {
            taskService.complete(task.getId(), variables);
        }

    }

    /**
     * 获取任务
     *
     * @param businessKey 业务key
     * @return 任务
     */
    public Task getTaskByBusinessKey(String businessKey) {
        return taskService.createTaskQuery().processInstanceBusinessKey(businessKey).singleResult();
    }

    /**
     * 完成任务
     *
     * @param taskId 任务ID
     * @param userId 任务责任人
     * @param variables 流程变量
     */
    public void completeTask(String taskId, Long userId, Map<String, Object> variables) {
        taskService.setAssignee(taskId, String.valueOf(userId));
        taskService.setVariables(taskId, variables);
        taskService.complete(taskId);
    }

    /**
     * 完成任务
     *
     * @param taskId 任务ID
     * @param userId 任务责任人
     */
    public void completeTask(String taskId, Long userId) {
        taskService.setAssignee(taskId, String.valueOf(userId));
        taskService.complete(taskId);
    }

    /**
     * 获取任务
     *
     * @param procInstId 流程实例ID
     * @return 任务
     */
    public Task getTaskByProcInstId(String procInstId) {
        return taskService.createTaskQuery().processInstanceId(procInstId).includeProcessVariables().singleResult();
    }

    /**
     * 获取任务详情
     *
     * @param taskId 任务ID
     * @return 任务详情
     */
    public Task getTaskByTaskId(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    /**
     * 获取任务业务key
     *
     * @param taskId 任务ID
     * @return 业务key
     */
    public String getBusinessKeyByTaskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task != null) {
            String processInstanceId = task.getProcessInstanceId();
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            return pi.getBusinessKey();
        } else {
            return null;
        }
    }

    /**
     * 获取任务业务key
     *
     * @param task 任务
     * @return 业务key
     */
    public String getBusinessKeyByTask(Task task) {
        String processInstanceId = task.getProcessInstanceId();
        return runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult()
                .getBusinessKey();
    }

    /**
     * 获取待办任务
     *
     * @param currentUserId 责任人
     * @param entityList 候选人
     * @return 代办任务列表
     */
    public List<Task> listToDoTasks(Long currentUserId, List<String> entityList) {
        return taskService.createTaskQuery()
                .taskCandidateOrAssigned(String.valueOf(currentUserId), entityList)
                .list();
    }

    /**
     * 获取待办任务
     *
     * @param definitionKey 流程定义key
     * @param authorities 权限列表
     * @return 代办任务列表
     */
    public List<Task> listTodoTasks(String definitionKey, List<String> authorities) {
        return taskService.createTaskQuery()
                .taskCandidateGroupIn(authorities)
                .processDefinitionKey(definitionKey)
                .list();
    }

    /**
     * 获取候选组
     *
     * @param procInstId 流程实例ID
     * @return 候选组
     */
    public List<String> getCandidateGroupsByProcInstId(String procInstId) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstId).list();
        List<String> candidateGroups = new ArrayList<>();
        if (tasks != null) {
            tasks.forEach((task) -> {
                List<IdentityLink> list = taskService.getIdentityLinksForTask(task.getId());
                if (list != null) {
                    List<String> groups = list.stream().map(IdentityLink::getGroupId).collect(Collectors.toList());
                    candidateGroups.addAll(groups);
                }

            });
        }
        return candidateGroups;
    }

    /**
     * 获取候选组
     *
     * @param taskId 任务ID
     * @return 候选组
     */
    public List<String> getCandidateGroupsByTaskId(String taskId) {
        List<String> candidateGroups = new ArrayList<>();
        List<IdentityLink> list = taskService.getIdentityLinksForTask(taskId);
        if (list != null) {
            List<String> groups = list.stream().map(IdentityLink::getGroupId).collect(Collectors.toList());
            candidateGroups.addAll(groups);
        }
        return candidateGroups;
    }

    /**
     * 获取历史已处理任务列表
     *
     * @param definitionKey 流程定义key
     * @param userId 任务负责人
     * @return 历史任务列表
     */
    public List<HistoricTaskInstance> getDoneHistoryTaskList(String definitionKey, String userId) {
        return historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userId)
                .processDefinitionKey(definitionKey)
                .list();
    }

    /**
     * 触发事件
     *
     * @param businessKey 业务key
     * @param eventName 事件名
     */
    public void signalEvent(String businessKey, String eventName) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        if (processInstance != null) {
            Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstance.getId())
                    .signalEventSubscriptionName(eventName)
                    .singleResult();
            if (execution != null) {
                runtimeService.signalEventReceived(eventName, execution.getId());
            }
        }
    }

    /**
     * 获取变量值
     *
     * @param businessKey 业务key
     * @param varKey 变量名
     * @return 变量值
     */
    public Object getVariable(String businessKey, String varKey) {
        Task task = getTaskByBusinessKey(businessKey);
        String processInstanceId = task.getProcessInstanceId();
        List<HistoricVariableInstance> hisTaskList = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
        for (HistoricVariableInstance historicTaskInstance : hisTaskList) {
            if (historicTaskInstance.getVariableName().equals(varKey)) {
                return historicTaskInstance.getValue();
            }
        }
        return null;
    }

}
