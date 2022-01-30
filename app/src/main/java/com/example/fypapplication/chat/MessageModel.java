package com.example.fypapplication.chat;

public class MessageModel {

    private String uid;
    private String text;

    public MessageModel() {
    }

    public MessageModel(String uid, String text) {
        this.uid = uid;
        this.text = text;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

//    private String idSender;
//    public MessageModel(String idSender, String idReceiver, String text) {
//        this.idSender = idSender;
//        this.idReceiver = idReceiver;
//        this.text = text;
//    }
//    public String getIdSender() {
//        return idSender;
//    }
//
//    public void setIdSender(String idSender) {
//        this.idSender = idSender;
//    }