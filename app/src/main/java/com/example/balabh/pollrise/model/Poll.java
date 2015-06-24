package com.example.balabh.pollrise.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by balabh on 6/23/2015.
 */
public class Poll {
    private long id;
    private String question;
    private Date date;
    private static String QUESTION = "question";
    private static String DATE = "data";
    private static String ID = "id";
    private static String OPTIONS = "options";
    private List<PollOption> options = new ArrayList<>();

    public Poll(String question, Date date) {
        this.question = question;
        this.date = date;
    }

    private void setOptions(List<PollOption> options) {
        this.options = options;
    }

    public List<PollOption> getOptions() {
        return options;
    }

    public void addOption(PollOption option) {
        options.add(option);
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

    private static Poll buildFromJson(String s) throws JSONException{
        JSONObject pollJson = new JSONObject(s);
        Poll poll = new Poll(pollJson.getString(Poll.QUESTION),
                new Date(pollJson.getString(Poll.DATE)));
        poll.setOptions(PollOption.fromJSON(pollJson.getString(Poll.OPTIONS)));
        poll.setId(pollJson.getLong(Poll.ID));
        return poll;
    }

    public static List<Poll> fromJson(String s) throws JSONException{
        if(s==null || s.length()==0)
            throw new JSONException("non zero length input expected to parse Poll JSON");
        List<Poll> polls = new ArrayList<>();
        if(s.charAt(0) != '{') {
            JSONArray pollsJs = new JSONArray(s);
            for(int i=0; i<pollsJs.length(); i++) {
                Poll poll = buildFromJson(pollsJs.get(i).toString());
                polls.add(poll);
            }
        }
        else {
            polls.add(buildFromJson(s));
        }
        return polls;
    }

    public static String toJson(List<Poll> polls) throws JSONException {
        JSONArray jsarr = new JSONArray();
        for (int i=0; i<polls.size(); i++) {
            jsarr.put(Poll.toJson(polls.get(i)));
        }
        return jsarr.toString();
    }

    public static String toJson(Poll p) throws JSONException {
        JSONObject poll = new JSONObject();
        poll.put(Poll.QUESTION, p.getQuestion());
        poll.put(Poll.ID, p.getId());
        poll.put(Poll.DATE, p.getDate());
        poll.put(Poll.OPTIONS, p.getOptions());
        return poll.toString();
    }
}
