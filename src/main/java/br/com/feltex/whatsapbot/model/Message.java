package br.com.feltex.whatsapbot.model;


import java.util.Objects;
import java.util.Set;

public class Message {
    private Set<String> contacts;
    private String content;
    private String pathImage;

    public Message(Set<String> contacts, String content, String pathImage) {
        this.contacts = contacts;
        this.content = content;
        this.pathImage = pathImage;
    }

    public Set<String> getContacts() {
        return contacts;
    }

    public void setContacts(Set<String> contacts) {
        this.contacts = contacts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(contacts, message.contacts) && Objects.equals(content, message.content) && Objects.equals(pathImage, message.pathImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contacts, content, pathImage);
    }

    @Override
    public String toString() {
        return "Message{" +
                "contacts=" + contacts +
                ", content='" + content + '\'' +
                ", pathImage='" + pathImage + '\'' +
                '}';
    }
}
