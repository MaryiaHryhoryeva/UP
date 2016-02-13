import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Console {
    public Console() {
        arrayList = new ArrayList<>();
        System.out.println("Hello! What do you want to do? \n add - add new message \n load - load history " +
                "\n save - save history \n print - print history \n delete - delete message \n exit - close program");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String answer = scanner.next();
            switch (answer) {
                case "add":
                    add();
                    break;
                case "load":
                    load();
                    break;
                case "save":
                    save();
                    break;
                case "print":
                    print();
                    break;
                case "delete":
                    delete();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
    }
    ArrayList<Message> arrayList;
    public static void main(String[] args) {
        new Console();
    }

    private void add() {
        Scanner scanner = new Scanner(System.in);
        String author = scanner.nextLine();
        String message = scanner.nextLine();
        Message m = new Message(author,message);
        arrayList.add(m);
        System.out.println("Added.");
    }

    private void delete() {
        System.out.println("Enter id");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        int n=arrayList.size();
        for (int i=0; i<arrayList.size();i++){
            if (arrayList.get(i).getId().equals(id)){
                arrayList.remove(i);
                System.out.println("Deleted.");
            }
        }
        if(arrayList.size()==n)
            System.out.println("id not found");
    }

    private void load() {
        try{
            Reader reader = new InputStreamReader(new FileInputStream("log.txt"));
            Gson gson = new GsonBuilder().create();
            Message[] m = gson.fromJson(reader, Message[].class);
            arrayList.clear();
            Collections.addAll(arrayList,m);
            System.out.println("Loaded.");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    private void save() {
        try {
            Gson gson = new GsonBuilder().create();
            FileWriter fw = new FileWriter("output.txt");
            gson.toJson(arrayList, fw);
            fw.close();
            System.out.println("Saved");
        }
        catch (IOException e){
            System.out.println("Save error");
        }
    }

    private void print() {
            System.out.println(arrayList);
    }
}
