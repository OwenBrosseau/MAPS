package StructuralClasses;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Project {
    String title;
    String description;
    String filePath;
    ArrayList<Member> members;
    Member projectManager;
    ArrayList<Topic> topics;
    ArrayList<ForumThread> forumThreads;
    ArrayList<Task> uncompletedTasks;
    ArrayList<Task> completedTasks;
    ArrayList<Meeting> meetings;
    Member loggedIn;
    Date startDay;
    Date dueDay;
    Date currentDay;
    boolean isCompleted;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    //Creation through GUI constructor
    public Project(String newTitle, String newDescription, String newPMName, Date newDueDay) throws ParseException {
        members = new ArrayList<>();
        topics = new ArrayList<>();
        forumThreads = new ArrayList<>();
        uncompletedTasks = new ArrayList<>();
        completedTasks = new ArrayList<>();
        meetings = new ArrayList<>();
        title = newTitle;
        dueDay = newDueDay;
        isCompleted = false;
        startDay = sdf.parse(sdf.format(new Date()));
        currentDay = sdf.parse(sdf.format(new Date()));
        filePath = System.getProperty("user.dir") + "/Projects";
        ArrayList<String> projectTitles = new ArrayList<>();
        projectTitles.add(title);
        try {
            File dir = new File(filePath);
            File file = new File(dir, "Projects.txt");
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()) {
                String temp = myReader.nextLine();
                projectTitles.add(temp);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String out = "";
        for (int i = 0; i < projectTitles.size(); i++) {
            out += projectTitles.get(i);
            if (i < projectTitles.size() - 1) {
                out += "\r\n";
            }
        }
        writeToFile(filePath, "Projects", out);
        description = newDescription;
        //Creating directories for the project
        new File(filePath + "/" + title).mkdirs();
        filePath = filePath + "/" + title;
        projectManager = new Member(newPMName, filePath);
        members.add(projectManager);
        new File(filePath + "/ForumThreads").mkdirs();
        new File(filePath + "/Topics").mkdirs();
        new File(filePath + "/Members").mkdirs();
        new File(filePath + "/UncompletedTasks").mkdirs();
        new File(filePath + "/CompletedTasks").mkdirs();
        new File(filePath + "/Meetings").mkdirs();

        loggedIn = projectManager;
    }

    //Rebuilding constructor
    public Project(String openTitle) throws ParseException {
        members = new ArrayList<>();
        topics = new ArrayList<>();
        forumThreads = new ArrayList<>();
        uncompletedTasks = new ArrayList<>();
        completedTasks = new ArrayList<>();
        meetings = new ArrayList<>();
        currentDay = sdf.parse(sdf.format(new Date()));
        loggedIn = null;
        title = openTitle;
        filePath = System.getProperty("user.dir") + "/Projects/" + openTitle;
        try {
            File dir = new File (filePath);
            File file = new File (dir, title + ".txt");
            Scanner myReader = new Scanner(file);
            description = myReader.nextLine();
            projectManager = new Member(myReader.nextLine(), filePath);
            isCompleted = Boolean.parseBoolean(myReader.nextLine());
            startDay = sdf.parse(myReader.nextLine());
            dueDay = sdf.parse(myReader.nextLine());
            members.add(projectManager);
            int temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                Member newMember = new Member(myReader.nextLine(), filePath);
                if (!newMember.getName().equals(projectManager.getName())) {
                    members.add(newMember);
                }
            }
            temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                topics.add(new Topic(filePath + "/Topics", myReader.nextLine(), members));
            }
            temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                forumThreads.add(new ForumThread(filePath + "/ForumThreads", myReader.nextLine(), topics, members));
            }
            temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                uncompletedTasks.add(new Task(filePath + "/UncompletedTasks", myReader.nextLine(), topics, members));
            }
            temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                completedTasks.add(new Task(filePath + "/CompletedTasks", myReader.nextLine(), topics, members));
            }
            temp = Integer.parseInt(myReader.nextLine());
            for (int i = 0; i < temp; i++) {
                meetings.add(new Meeting(filePath + "/Meetings", myReader.nextLine(), topics, members));
            }
            for (Member thisMember : members) {
                File dir2 = new File (filePath + "/Members");
                File file2 = new File (dir2, thisMember.getName() + ".txt");
                Scanner myReader2 = new Scanner(file2);
                int numNotifications = Integer.parseInt(myReader2.nextLine());
                for (int i = 0; i < numNotifications; i++) {
                    thisMember.addNotification(new Notification(filePath + "/Members/" + thisMember.getName(), i, uncompletedTasks, completedTasks, meetings, forumThreads));
                }
                myReader2.close();
            }
            myReader.close();
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public Member getProjectManager() {
        return projectManager;
    }

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public ArrayList<ForumThread> getForumThreads() {
        return forumThreads;
    }

    public ArrayList<Task> getUncompletedTasks() {
        return uncompletedTasks;
    }

    public ArrayList<Task> getCompletedTasks() {
        return completedTasks;
    }

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    public Member getLoggedIn() {
        return loggedIn;
    }

    public Date getStartDay() {
        return startDay;
    }

    public Date getDueDay() {
        return dueDay;
    }

    public Date getCurrentDay() {
        return currentDay;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Member getMember(String memberName) {
        for (Member searchMember : members) {
            if (searchMember.getName().equals(memberName)) {
                return searchMember;
            }
        }
        return null;
    }

    public Topic getTopic(String topicName) {
        for (Topic searchTopic : topics) {
            if (searchTopic.getTitle().equals(topicName)) {
                return searchTopic;
            }
        }
        return null;
    }

    public Task getUncompletedTask(String taskTitle) {
        for (Task uncompletedTask : uncompletedTasks) {
            if (uncompletedTask.getTitle().equals(taskTitle)) {
                return uncompletedTask;
            }
        }
        return null;
    }

    Task getCompletedTask(String taskTitle) {
        for (Task completedTask : completedTasks) {
            if (completedTask.getTitle().equals(taskTitle)) {
                return completedTask;
            }
        }
        return null;
    }

    public void completeTask(String taskTitle) {
        Task temp;
        for (int i = 0; i < uncompletedTasks.size(); i++) {
            if (uncompletedTasks.get(i).getTitle().equals(taskTitle)) {
                temp = uncompletedTasks.get(i);
                for (Member member : members) {
                    for (int k = 0; k < temp.getMembers().size(); k++) {
                        if (member == temp.getMembers().get(k)) {
                            member.completeEvent(temp.getTitle());
                        }
                    }
                }
                uncompletedTasks.remove(i);
                temp.setCompleted(true);
                completedTasks.add(temp);
                break;
            }
        }
    }

    public void claimTask(Task taskToClaim) {
        taskToClaim.addMember(loggedIn);
    }

    public void createForumThread(String threadTitle, String threadMessage, Member memberCreator) {
        forumThreads.add(new ForumThread(threadTitle, threadMessage, memberCreator));
        System.out.println("Forum created");
    }

    public ForumThread getForumThread(String threadTitle) {
        for (ForumThread forumThread : forumThreads) {
            if (forumThread.forumTitle.equals(threadTitle)) {
                return forumThread;
            }
        }
        return null;
    }

    public void addMember(String name) {
        Member newMember = new Member(name, filePath);
        members.add(newMember);
    }

    public void createTopic(String topicName, Member topicLead) {
        Topic newTopic = new Topic(topicName, topicLead);
        topics.add(newTopic);
    }

    public void addTask(String taskTitle, String taskDescription) {
        Task newTask = new Task(taskTitle, taskDescription);
        uncompletedTasks.add(newTask);
    }

    public String generateSuggestion() {
        String returnString = "";
        if (!isCompleted) {
            returnString += "Hi " + loggedIn.getName() + "! ";
            if (getDifferenceDays(startDay, dueDay) >= 0) {
                returnString += "There's " + getDifferenceDays(currentDay, dueDay) + " days left before this project is due. ";
            }
            if (loggedIn.currentTasks.size() > 1) {
                returnString += "Looks like you've got some things you're assigned to that you could work on. Why not get started with one of those?";
            } else if (loggedIn.meetings.size() > 0) {
                returnString += "You have a meeting in the future, try to see if you need to do anything to prepare for it";
            } else if (loggedIn.currentTasks.size() > 0) {
                returnString += "You're almost done everything you have to do for now, just one more thing to worry about! Get that done and you'll be stress free!";
            } else if (uncompletedTasks.size() > 0) {
                if (loggedIn.completedTasks.size() > 0) {
                    returnString += "Wow! You did everything you were assigned to so far! Now that you're done that, maybe pick up one of the tasks that nobody has been assigned to so far";
                } else {
                    returnString += "Looks like you're about to get started on your part of the project. Try looking at the tasks that need to be done to see what you can do!";
                }
            } else if (completedTasks.size() > 0) {
                returnString += "You guys really did it! You've done all of the tasks you created for this project! Time to relax for a bit and go do something fun to unwind!";
            } else {
                returnString += "To get started, try adding a new task to be completed, a new group member, or maybe a topic relevant to this project";
            }
        }
        else {
            returnString += "Well done, the project is complete! Go into the project manager action menu to see an overview of who participated in which tasks for this project";
        }
        return returnString;
    }

    //Creating the string used to save the data to file
    public String generateSaveString() {
        String out = description + "\r\n" + projectManager.getName() + "\r\n" + isCompleted + "\r\n" + sdf.format(startDay) + "\r\n" + sdf.format(dueDay) + "\r\n" + members.size() + "\r\n";
        for (Member member : members) {
            out += member.getName() + "\r\n";
        }
        out += topics.size() + "\r\n";
        for (Topic newTopic : topics) {
            out += newTopic.getTitle() + "\r\n";
        }
        out += forumThreads.size() + "\r\n";
        for (ForumThread newThread : forumThreads) {
            out += newThread.forumTitle + "\r\n";
        }
        out += uncompletedTasks.size() + "\r\n";
        for (Task newTask : uncompletedTasks) {
            out += newTask.getTitle() + "\r\n";
        }
        out += completedTasks.size() + "\r\n";
        for (Task newTask : completedTasks) {
            out += newTask.getTitle() + "\r\n";
        }
        out += meetings.size() + "\r\n";
        for (int i = 0; i < meetings.size(); i++) {
            out += meetings.get(i).getTitle();
            if (i < meetings.size() - 1) {
                out += "\r\n";
            }
        }
        return out;
    }

    //Saving the data to files
    public void saveToFile() {
        String out = generateSaveString();

        writeToFile(filePath, title, out);

        for (ForumThread forumThread : forumThreads) {
            out = forumThread.saveToFile();

            writeToFile(filePath + "/ForumThreads", forumThread.forumTitle, out);
        }
        for (Topic saveTopic : topics) {
            out = saveTopic.saveToFile();

            writeToFile(filePath + "/Topics", saveTopic.getTitle(), out);
        }
        for (Meeting meeting : meetings) {
            out = meeting.saveToFile();

            writeToFile(filePath + "/Meetings", meeting.getTitle(), out);
        }
        for (Task uncompletedTask : uncompletedTasks) {
            out = uncompletedTask.saveToFile();

            writeToFile(filePath + "/UncompletedTasks", uncompletedTask.getTitle(), out);
        }
        for (Task completedTask : completedTasks) {
            out = completedTask.saveToFile();

            writeToFile(filePath + "/CompletedTasks", completedTask.getTitle(), out);
        }
        for (Member thisMember : members) {
            out = thisMember.saveToFile();

            writeToFile(filePath + "/Members", thisMember.getName(), out);

            for (int i = 0; i < thisMember.getNotifications().size(); i++) {
                out = thisMember.getNotifications().get(i).saveToFile();
                writeToFile(filePath + "/Members/" + thisMember.getName(), String.valueOf(i), out);
            }
        }
    }

    void writeToFile(String destinationPath, String newTitle, String contents) {
        Writer output = null;
        File dir = new File (destinationPath);
        File file = new File (dir, newTitle + ".txt");
        try {
            output = new BufferedWriter(new FileWriter(file));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            //write the data to the file
            assert output != null;
            output.write(contents);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            //close the output file
            output.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static int getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public void setLoggedIn(Member newLogin) {
        loggedIn = newLogin;
    }
}
