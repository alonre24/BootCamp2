

/**
 * Created by Administrator on 27/04/2017.
 */
package com.example.rest;


import java.io.Serializable;

public class Soldier implements Serializable {
    private Long personalId;
    private String firstName;
    private String lastName;
    private int profile;
    private int personalCommanderId;
    private int crewNum; // only for recruits, 0 for staff
    //private Rank rank;
    private String rank;

    public void build (long personalId, String firstName, String lastName, int profile, int personalCommanderId,
                       int crewNum, String rank) {

        setCrewNum(crewNum);
        setFirstName(firstName);
        setLastName(lastName);
        setPersonalCommanderId(personalCommanderId);
        setProfile(profile);
        setRank(rank);
        setPersonalId(personalId);

    }

    public int getPersonalCommanderId() {
        return personalCommanderId;
    }

    public Long getPersonalId(){
        return this.personalId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getCrewNum() {
        return crewNum;
    }

    public int getProfile() {
        return profile;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString(){
        return "ID: " + personalId
                + " Name: " + firstName + " " + lastName + "\n"
                + "Profile: " + profile + "\n"
                + "Crew Number: " + crewNum + "\n"
                + "Personal Commander: " + personalCommanderId;
    }

    public void setCrewNum(int crewNum) {
        this.crewNum = crewNum;
    }


    public void setProfile(int profile) {
        this.profile = profile;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Boolean isStaff() {
        return rank!="RECRUIT";
    }

    public void setPersonalId(long personalId) {
        this.personalId = personalId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setPersonalCommanderId(int personalCommanderId) {
        this.personalCommanderId = personalCommanderId;
    }
}