package com.crud.tasks.scheduler;

public class TaskWord {
    public static String chooseRightWord(long size) {

        String taskWord = "zadań";

        if(size == 1 ) {
            taskWord = "zadanie";
        }
        else if(size%10 > 1 && size%10 < 5) {
            taskWord = "zadania";
        }

        return taskWord;
    }
}
