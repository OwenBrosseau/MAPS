package StructuralClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Notification {
    String message;
    int type;
    Task notificationTask;
    ForumThread notificationThread;
    Meeting notificationMeeting;

    //base constructors (type of notification changes based on arguments)
    public Notification(String creationMessage) {
        message = creationMessage;
        type = 0;
    }

    public Notification(String creationMessage, Task newNotificationTask) {
        message = creationMessage;
        notificationTask = newNotificationTask;
        type = 1;
    }

    public Notification(String creationMessage, ForumThread newNotificationThread) {
        message = creationMessage;
        notificationThread = newNotificationThread;
        type = 2;
    }

    public Notification(String creationMessage, Meeting newNotificationMeeting) {
        message = creationMessage;
        notificationMeeting = newNotificationMeeting;
        type = 3;
    }

    //Rebuilding constroctor for opening from file
    Notification(String path, int notifNumber, ArrayList<Task> listUncompletedTasks, ArrayList<Task> listCompletedTasks, ArrayList<Meeting> listMeetings, ArrayList<ForumThread> listThreads) {
        try {
            File dir = new File (path);
            File file = new File (dir, notifNumber + ".txt");
            Scanner myReader = new Scanner(file);
            message = myReader.nextLine();
            type = Integer.parseInt(myReader.nextLine());
            switch (type) {
                case 1:
                    String tempString = myReader.nextLine();
                    for (int i = 0; i < listUncompletedTasks.size(); i++) {
                        for (Task listTask : listUncompletedTasks) {
                            if (tempString.equals(listTask.getTitle())) {
                                notificationTask = listTask;
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < listCompletedTasks.size(); i++) {
                        for (Task listTask : listCompletedTasks) {
                            if (tempString.equals(listTask.getTitle())) {
                                notificationTask = listTask;
                                break;
                            }
                        }
                    }
                    break;
                case 2:
                    tempString = myReader.nextLine();
                    for (int i = 0; i < listThreads.size(); i++) {
                        for (ForumThread listThread : listThreads) {
                            if (tempString.equals(listThread.forumTitle)) {
                                notificationThread = listThread;
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    tempString = myReader.nextLine();
                    for (int i = 0; i < listMeetings.size(); i++) {
                        for (Meeting listMeeting : listMeetings) {
                            if (tempString.equals(listMeeting.getTitle())) {
                                notificationMeeting = listMeeting;
                                break;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    public Task getTask() {
        if (type == 1) {
            return notificationTask;
        }
        else {
            return null;
        }
    }

    public ForumThread getThread() {
        if (type == 2) {
            return notificationThread;
        }
        else {
            return null;
        }
    }

    public Meeting getMeeting() {
        if (type == 3) {
            return notificationMeeting;
        }
        else {
            return null;
        }
    }

    //Creating the string used to save the data to file
    String saveToFile() {
        String data = message + "\r\n" + type;
        switch (type) {
            case 1:
                data += "\r\n" + notificationTask.title;
                break;
            case 2:
                data += "\r\n" + notificationThread.forumTitle;
                break;
            case 3:
                data += "\r\n" + notificationMeeting.title;
                break;
            default:
                break;
        }
        return data;
    }
}
