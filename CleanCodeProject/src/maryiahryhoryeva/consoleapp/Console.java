package maryiahryhoryeva.consoleapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Console {

    ArrayList<Message> arrayList;

    public Console() {
        arrayList = new ArrayList<>();
        System.out.println("Hello! What do you want to do? " +
                "\n add - add new message " +
                "\n load - load history " +
                "\n save - save history " +
                "\n print - print history " +
                "\n delete - delete message" +
                "\n search author - search by author " +
                "\n search word - search by key-word " +
                "\n exit - close program");
        functional();

    }

    private void functional(){
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            String answer = scanner.nextLine();
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
                case "search author":
                    searchAuthor();
                    break;
                case "search word":
                    searchWord();
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
        scanner.close();
        System.exit(0);

    }

    private void add() {
        System.out.println("Enter author and message");
        Scanner scanner = new Scanner(System.in);
        String author = scanner.nextLine();
        String message = scanner.nextLine();
        Message m = new Message(author, message);
        arrayList.add(m);
        System.out.println("Added.");
    }

    private void delete() {
        System.out.println("Enter id");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        int n = arrayList.size();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getId().equals(id)) {
                arrayList.remove(i);
                System.out.println("Deleted.");
            }
        }
        if (arrayList.size() == n) {
            System.out.println("id not found");
        }
        scanner.close();
    }

    private void load() {
        try {
            Reader reader = new InputStreamReader(new FileInputStream("log.txt"));
            Gson gson = new GsonBuilder().create();
            Message[] m = gson.fromJson(reader, Message[].class);
            arrayList.clear();
            Collections.addAll(arrayList, m);
            reader.close();
            System.out.println("Loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Reader not closed");
        }
    }

    private void save() {
        try {
            Gson gson = new GsonBuilder().create();
            FileWriter fw = new FileWriter("output.txt");
            gson.toJson(arrayList, fw);
            fw.close();
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("Save error");
        }
    }

    private void searchAuthor() {
        System.out.println("Enter author");
        Scanner scanner = new Scanner(System.in);
        String author = scanner.nextLine();
        boolean flag = false;
        for (Message m : arrayList) {
            if (m.getAuthor().equals(author)) {
                System.out.println(m);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Author not found");
        }
        scanner.close();
    }

    private void searchWord() {
        System.out.println("Enter word(phrase)");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        boolean flag = false;
        for (Message m : arrayList) {
            if (m.getMessage().contains(text)) {
                System.out.println(m);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Word not found");
        }
        scanner.close();
    }

    private void print() {
        System.out.println(arrayList);
    }
}
