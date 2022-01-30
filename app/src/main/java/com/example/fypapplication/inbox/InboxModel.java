package com.example.fypapplication.inbox;

public class InboxModel {
    private String userId;
    private String email;
    private String lastMessage;

    public InboxModel() {

    }

    public InboxModel(String userId, String email, String lastMessage) {
        this.userId = userId;
        this.email = email;
        this.lastMessage = lastMessage;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "InboxModel{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                '}';
    }
}
