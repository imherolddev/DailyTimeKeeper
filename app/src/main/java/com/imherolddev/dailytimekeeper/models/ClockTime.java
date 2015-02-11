package com.imherolddev.dailytimekeeper.models;

/**
 * Created by imherolddev on 2/7/2015.
 */
public class ClockTime {

    private String jobName;
    private long time;
    private int multiplier;

    public ClockTime(String jobName, long time, int multiplier) {

        this.setJobName(jobName);
        this.setTime(time);
        this.setMultiplier(multiplier);

    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
