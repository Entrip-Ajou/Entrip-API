package OperationSystemAssignment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Process {
    private String name;
    private int taskAmount;
    private int priority;

    @Builder
    public Process(String name, int taskAmount, int priority) {
        this.name = name;
        this.taskAmount = taskAmount;
        this.priority = priority;
    }

    public void executeTask() {
        System.out.println(name + " Process를 실행합니다. taskAmount : " + taskAmount + " -> " + (taskAmount-1) );
        this.taskAmount--;
    }
}
