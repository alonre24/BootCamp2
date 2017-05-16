package com.example.rest;

import java.util.Map;

/**
 * Created by Administrator on 11/05/2017.
 */
public class Company_A {
    private int totalSize;
    private Map recruits;
    private Map staff;
    private Map allSodiers;
    private Soldier companyCommander;
    private Soldier companySergeantMajor;
    private Soldier deputyCompanyCommander;
    //private Map platoons;


    public int getTotalSize() {
        return totalSize;
    }

    public Map<Integer, Soldier> getRecruits() {
        return recruits;
    }

    public Map<Integer, Soldier> getStaff() {
        return staff;
    }

    public Soldier getCompanyCommander() {
        return companyCommander;
    }

    public Soldier getCompanySergeantMajor() {
        return companySergeantMajor;
    }

    public Soldier getDeputyCompanyCommander() {
        return deputyCompanyCommander;
    }

    public void setCompanyCommander(Soldier companyCommander) {
        this.companyCommander = companyCommander;
    }

    public void setCompanySergeantMajor(Soldier companySergeantMajor) {
        this.companySergeantMajor = companySergeantMajor;
    }

    public void setDeputyCompanyCommander(Soldier deputyCompanyCommander) {
        this.deputyCompanyCommander = deputyCompanyCommander;
    }

    public void addSoldier(Soldier solider, Boolean staff) {

        if (staff == Boolean.TRUE) {
            this.recruits.put(solider.getPersonalId(), solider);

        } else {
            this.staff.put(solider.getPersonalId(), solider);
        }
        this.allSodiers.put(solider.getPersonalId(), solider);
        this.totalSize++;
    }

    public Soldier removeSolider(int solId, Boolean staff) throws SoldierNotExistsException {

        Map<Long, Soldier> sols;
        if (staff == Boolean.TRUE) {
            sols = this.staff;
        } else {
            sols = this.recruits;
        }
        Soldier sol = sols.get(solId);
        if (sol == null) {
            throw new SoldierNotExistsException();
        }
        sols.remove(solId);
        this.allSodiers.remove(solId);
        this.totalSize--;
        return sol;
    }
}

