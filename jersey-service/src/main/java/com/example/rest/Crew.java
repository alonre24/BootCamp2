package com.example.rest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 27/04/2017.
 */
public class Crew {

    private int numberOfSoliders;
    private Map soldiers;
    private final int crewNum;
    private Soldier commander;
    //private final Platoon platoon;


    public Crew(int crewNum/*, Platoon platoon*/, Soldier commander) {
        this.numberOfSoliders = 0;
        this.soldiers = new HashMap();
        this.crewNum = crewNum;
        //this.platoon = platoon;
        this.commander = commander;

    }

    public int getCrewNum() {
        return crewNum;
    }

    public Map getSoliders() {
        return soldiers;
    }

    public int getNumberOfSoliders() {
        return numberOfSoliders;
    }

    /*public Platoon getPlatoon() {
        return platoon;
    }*/

    public Soldier getCommander() {
        return commander;
    }

    public void setCommander(Soldier commander) {
        this.commander = commander;
    }

    public void addSoilder(Soldier soldier) {
        this.soldiers.put(soldier.getPersonalId(), soldier);
        this.numberOfSoliders++;
    }

    public Soldier removeSolider(int solId) throws SoldierNotExistsException {
        Map<Integer, Soldier> sols = this.soldiers;
        Soldier sol = sols.get(solId);
        if (sol == null) {
            throw new SoldierNotExistsException();
        }
        sols.remove(solId);
        this.numberOfSoliders--;
        return sol;
    }

}
