

public class Message implements Comparable<Message> {
    private String id, author, message;
    private Long timestamp;
    private static int nextId=1;
    public Message() {
        id=Integer.toString(nextId++);
        author="me";
        timestamp=System.currentTimeMillis();
        message="Hello!";
    }
    public Message(String author, String message){
        this.author=author;
        this.message=message;
        this.id=Integer.toString(nextId++);
        this.timestamp=System.currentTimeMillis();
    }
    public String getId(){
        return id;
    }
    public String getAuthor(){
        return author;
    }
    public String getMessage(){
        return message;
    }
    public Long getTimestamp(){
        return timestamp;
    }

    @Override
    public int compareTo(Message o) {
        return timestamp.compareTo(o.timestamp);
    }

    @Override
    public String toString() {
        return id+" "+author+" "+timestamp+" "+message;
    }
}
