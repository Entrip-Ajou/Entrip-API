package OperationSystemAssignment;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
@Getter
public class PriorityQueue {

    private List<Process> priorityQueue = new ArrayList<Process>();

    public void start() {
        Scanner sc = new Scanner(System.in);
        do {
            getCommandFromUser();
            executeProcess();
            checkProcess();
        }while(!priorityQueue.isEmpty());
        System.out.println("Priority Queue가 비어서 프로그램을 종료합니다.");
    }

    public Process makeNewProcess() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter name of Process : ");
        String name = sc.next();
        System.out.println("Please Enter taskAmount of Process : ");
        int taskAmount = sc.nextInt();
        System.out.println("Please Enter priority of Process : ");
        int priority = sc.nextInt();

        return Process.builder()
                .priority(priority)
                .taskAmount(taskAmount)
                .name(name)
                .build();
    }

    public void executeProcess() {
        if (priorityQueue.isEmpty()) return;
        Process now = this.priorityQueue.get(0);
        now.executeTask();
    }

    public void checkProcess() {
        for (Process process : priorityQueue) {
            if(process.getTaskAmount() == 0) {
                System.out.println(process.getName() + " Process의 taskAmount가 0이 되어 종료되었습니다.");
                priorityQueue.remove(process);
                return;
            }
        }
    }

    public void getCommandFromUser () {
        System.out.println("1. Process 생성 후 실행");
        System.out.println("2. Process 생성 없이 실행");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        if (input == 1) {
            Process process = makeNewProcess();
            insertProcess(process);
        }
    }

    public void insertProcess (Process process) {
        for (int i=0;i<priorityQueue.size();i++) {
            Process compareProcess = priorityQueue.get(i);
            if (compareProcess.getPriority() > process.getPriority()) {
                priorityQueue.add(i,process);
                return;
            }
        }
        priorityQueue.add(process);
    }

}
