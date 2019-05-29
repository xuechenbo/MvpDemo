package com.kahuanbao.com.abother.newnetwork;

public class BannerRec {

    /**
     * seniorMember : 2
     * level : 1
     * member : 1
     * limit : 0
     * authenticationStatus : 1
     * status : 0
     */

    private int seniorMember;
    private int level;
    private int member;
    private int limit;
    private int authenticationStatus;
    private int status;

    public int getSeniorMember() {
        return seniorMember;
    }

    public void setSeniorMember(int seniorMember) {
        this.seniorMember = seniorMember;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(int authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
