package StructuralClasses;

import java.util.ArrayList;

public class Event {
    ArrayList<Topic> topics;
    ArrayList<Member> members;
    String title;
    String description;

    public String getTitle() {
        return title;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public String getDescription() {
        return description;
    }

    String saveToFile() {
        return "";
    }
}
