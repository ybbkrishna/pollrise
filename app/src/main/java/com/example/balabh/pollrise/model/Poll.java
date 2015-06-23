package com.example.balabh.pollrise.model;

import java.util.Date;

/**
 * Created by balabh on 6/23/2015.
 */
public class Poll {
    private long id;
    private String question;
    private Date date;
    private PollOption[] options = new PollOption[4];

    public Poll(String question, Date date) {
        this.question = question;
        this.date = date;
    }

    public PollOption[] getOptions() {
        return options;
    }

    public void addOption(PollOption option, int index) {
        options[index] = option;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
