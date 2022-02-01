package StructuralClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Topic {
    String title;
    Member lead;
    ArrayList<Task> tasks;
    ArrayList<Meeting> meetings;
    ArrayList<ForumThread> forumThreads;

    //Base constructor
    Topic(String inputTitle, Member inputLead) {
        tasks = new ArrayList<>();
        meetings = new ArrayList<>();
        forumThreads = new ArrayList<>();
        title = inputTitle;
        lead = inputLead;
    }

    //Rebuilding constroctor for opening from file
    Topic(String path, String inputTitle, ArrayList<Member> members) {
        tasks = new ArrayList<>();
        meetings = new ArrayList<>();
        forumThreads = new ArrayList<>();
        title = inputTitle;
        try {
            File dir = new File (path);
            File file = new File (dir, title + ".txt");
            Scanner myReader = new Scanner(file);
            String tempString = myReader.nextLine();
            for (Member member : members) {
                if (tempString.equals(member.getName())) {
                    lead = member;
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Member getLead() {
        return lead;
    }
    public String getTitle() {
        return title;
    }
    public ArrayList<ForumThread> getForumThreads() {
        return forumThreads;
    }

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    //Creating the string used to save the data to file
    String saveToFile() {
        return lead.getName();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void addMeeting(Meeting newMeeting) {
        meetings.add(newMeeting);
    }

    public void addForumThread(ForumThread newForumThread) {
        forumThreads.add(newForumThread);
    }

}
