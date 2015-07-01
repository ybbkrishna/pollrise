package com.example.balabh.pollrise.service;

import com.example.balabh.pollrise.model.Poll;
import com.example.balabh.pollrise.model.PollOption;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by balabh on 6/25/2015.
 */
public class DataStoreService {
    private static DataStoreService dataStore = null;
    private List<Poll> polls = new ArrayList<>();
    private DataStoreService() {
        polls.addAll(generatePolls(50));
    }
    public static DataStoreService getDataStore() {
        if(dataStore == null)
            dataStore = new DataStoreService();
        return dataStore;
    }

    public void addPole(Poll poll) {
        polls.add(poll);
    }

    public List<Poll> getAllPoles() {
        return polls;
    }

    public Poll getPoll(int index) {
        return polls.get(index);
    }

    public void loadNext() {
        polls.addAll(generatePolls(50));
    }

    public List<Poll> generatePolls(int num) {
        List<Poll> polls = new ArrayList<>();
        String[] questions = new String[] {"Your favourite cricket player."
                , "your favourite Cuisine.", "which is the best place to visit."};
        String[] options = new String[] {"Sachin", "Dravid", "Dhoni", "Kohli", "Andhra", "Italian"
                , "Mexican", "Chinese", "Vietnam", "United States", "India", "Japan"};
        for (int i=0;i<num; i++) {
            Poll poll = new Poll(questions[(int)(Math.random()*questions.length)], new Date());
            for(int j=0;j<4;j++) {
                PollOption option = new PollOption(options[(int)(Math.random()*options.length)]);
                poll.addOption(option);
            }
            polls.add(poll);
        }
        return polls;
    }


}
