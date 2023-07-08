package org.example.entity;
import lombok.Data;
import org.example.pages.tasks.TaskCategory;

import java.util.Date;

@Data
public class Task {
    private String taskName;
    private TaskCategory category;
    private String taskDeadline;
    private String taskDescription;
    private String taskResult;
    private String volunteerBenefit;
    private int savedMoney;
    private int stage1Duration;
    private int stage2Duration;

    public Task(String taskName, TaskCategory category, String taskDeadline, String taskDescription, String taskResult,
                String volunteerBenefit, int savedMoney, int stage1Duration, int stage2Duration) {
        this.taskName = taskName;
        this.category = category;
        this.taskDeadline = taskDeadline;
        this.taskDescription = taskDescription;
        this.taskResult = taskResult;
        this.volunteerBenefit = volunteerBenefit;
        this.savedMoney = savedMoney;
        this.stage1Duration = stage1Duration;
        this.stage2Duration = stage2Duration;
    }
}
