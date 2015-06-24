package com.example.balabh.pollrise.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by balabh on 6/23/2015.
 */
public class PollOption {
    private String label;
    private int count = 0;
    private static String LABEL = "label";
    private static String COUNT = "count";

    public PollOption(String label) {
        this.label = label;
    }

    public PollOption(String label, int count) {
        this.label = label;
        this.count = count;
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

    public static String toJson(List<PollOption> pollOptions) throws JSONException {
        JSONArray options = new JSONArray();
        for (int i=0; i<pollOptions.size();i++) {
            options.put(PollOption.toJson(pollOptions.get(i)));
        }
        return options.toString();
    }

    public static String toJson(PollOption pollOption) throws JSONException {
        JSONObject option = new JSONObject();
        option.put(PollOption.LABEL,pollOption.getLabel());
        option.put(PollOption.COUNT,pollOption.getCount());
        return option.toString();
    }

    private static PollOption buildFromJson(String option) throws JSONException {
        JSONObject optionJson = new JSONObject(option);
        PollOption pollOption = new PollOption(optionJson.getString(PollOption.LABEL),
                optionJson.getInt(PollOption.COUNT));
        return pollOption;
    }

    public static List<PollOption> fromJSON(String options) throws JSONException{
        if(options ==null || options.length() == 0)
            throw new JSONException("non zero length input expected to parse PollOption");
        List<PollOption> pollOptions = new ArrayList<>();
        if(options.charAt(0) != '{') {
            JSONArray pollOpts = new JSONArray(options);
            for(int i=0; i<pollOpts.length(); i++) {
                PollOption pollOption = buildFromJson(pollOpts.get(i).toString());
                pollOptions.add(pollOption);
            }
        }
        else {
            pollOptions.add(buildFromJson(options));
        }
        return pollOptions;
    }
}
