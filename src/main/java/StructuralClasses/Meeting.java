package StructuralClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Meeting extends Event {
    Date day;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    //Base constructor
    public Meeting(String newTitle, String newDescription, Date meetingDay) {
        topics = new ArrayList<>();
        members = new ArrayList<>();
        title = newTitle;
        description = newDescription;
        day = meetingDay;
    }

    //Rebuilding constroctor for opening from file
    Meeting(String path, String inputTitle, ArrayList<Topic> listTopics, ArrayList<Member> listMembers) {
        title = inputTitle;
        topics = new ArrayList<>();
        members = new ArrayList<>();
        try {
            File dir = new File (path);
            File file = new File (dir, title + ".txt");
            Scanner myReader = new Scanner(file);
            description = myReader.nextLine();
            day = sdf.parse(myReader.nextLine());
            int temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                String tempString = myReader.nextLine();
                for (Topic listTopic : listTopics) {
                    if (tempString.equals(listTopic.getTitle())) {
                        topics.add(listTopic);
                        listTopic.addMeeting(this);
                        break;
                    }
                }
            }
            temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                String tempString = myReader.nextLine();
                for (Member listMember : listMembers) {
                    if (tempString.equals(listMember.getName())) {
                        members.add(listMember);
                        listMember.meetings.add(this);
                        break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Date getDay() {
        return day;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void addTopic(Topic newTopic) {
        topics.add(newTopic);
        newTopic.getLead().addNotification(new Notification("A new meeting has been created concerning a topic you may be able to help with!", this));
    }

    public void addMember(Member newMember) {
        members.add(newMember);
    }

    //Creating the string used to save the data to file
    String saveToFile() {
        String data = description + "\r\n" + sdf.format(day) + "\r\n";
        data += topics.size() + "\r\n";
        for (Topic topic : topics) {
            data += topic.getTitle() + "\r\n";
        }
        data += members.size() + "\r\n";
        for (int i = 0; i < members.size(); i++) {
            data += members.get(i).getName();
            if (i < members.size() - 1) {
                data += "\r\n";
            }
        }
        return data;
    }
}