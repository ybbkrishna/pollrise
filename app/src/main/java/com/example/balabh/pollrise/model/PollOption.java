package com.example.balabh.pollrise.model;

/**
 * Created by balabh on 6/23/2015.
 */
public class PollOption {
    private String label;
    private int count = 0;

    public PollOption(String label) {
        this.label = label;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount(int count) {
        this.count++;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
