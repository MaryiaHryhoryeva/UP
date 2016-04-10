package maryiahryhoryeva.consoleapp;

public class Message implements Comparable<Message> {
    private String id;
    private String author;
    private String text;
    private Long timestamp;
    private static int nextId = 1;
    private boolean deleted;

    public Message() {
        id = Integer.toString(nextId++);
        author = "me";
        timestamp = System.currentTimeMillis();
        text = "Hello!";
        deleted = false;
    }

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
        this.id = Integer.toString(nextId++);
        this.timestamp = System.currentTimeMillis();
        this.deleted = false;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int compareTo(Message o) {
        return timestamp.compareTo(o.timestamp);
    }

    @Override
    public String toString() {
        return id + " " + author + " " + timestamp + " " + text;
    }
}
