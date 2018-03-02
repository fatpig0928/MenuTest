package com.example.menutest.bean;

/**
 * Created by Administrator on 2017/5/28.
 */

public class Plan {
    int planId;
    int year;
    int month;
    int day;

    public Plan(int planId, int year, int month, int day) {
        this.planId = planId;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
