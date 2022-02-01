package StructuralClasses;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Member {
    String name;
    List<Task> currentTasks;
    List<Task> completedTasks;
    List<Meeting> meetings;
    List<Notification> notifications;

    //Base constructor. File path is needed so it can create folders to hold notification info relating to this member
    Member(String newName, String filePath) {
        name = newName;
        currentTasks = new ArrayList<>();
        completedTasks = new ArrayList<>();
        meetings = new ArrayList<>();
        notifications = new ArrayList<>();
        new File(filePath + "/Members/" + newName).mkdirs();
    }

    public String getName() {
        return name;
    }

    void addTask(Task newTask) {
        if (newTask.completed) {
            completedTasks.add(newTask);
        }
        else {
            currentTasks.add(newTask);
        }
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public List<Task> getCompletedTasks() {
        return completedTasks;
    }

    public List<Task> getCurrentTasks() {
        return currentTasks;
    }

    public void addNotification(Notification newNotification) {
        notifications.add(newNotification);
    }

    public void removeNotification(Notification thisNotification) {
        notifications.remove(thisNotification);
    }

    public void addMeeting(Meeting newMeeting) {
        meetings.add(newMeeting);
    }

    public void completeEvent(String title) {
        for (int i = 0; i < currentTasks.size(); i++) {
            if (currentTasks.get(i).getTitle().equals(title)) {
                completedTasks.add(currentTasks.get(i));
                currentTasks.remove(i);
                return;
            }
        }
    }

    //Creating the string used to save the data to file
    String saveToFile() {
        String data = String.valueOf(notifications.size());
        return data;
    }
}