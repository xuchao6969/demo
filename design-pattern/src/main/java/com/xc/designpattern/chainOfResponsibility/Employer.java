package com.xc.designpattern.chainOfResponsibility;

public class Employer {
    private Boolean gitHandOver;
    private Boolean jobHandOver;
    private Boolean fileHandOver;

    public Employer(Boolean gitHandOver, Boolean jobHandOver, Boolean fileHandOver) {
        this.gitHandOver = gitHandOver;
        this.jobHandOver = jobHandOver;
        this.fileHandOver = fileHandOver;
    }

    public Boolean getGitHandOver() {
        return gitHandOver;
    }

    public void setGitHandOver(Boolean gitHandOver) {
        this.gitHandOver = gitHandOver;
    }

    public Boolean getJobHandOver() {
        return jobHandOver;
    }

    public void setJobHandOver(Boolean jobHandOver) {
        this.jobHandOver = jobHandOver;
    }

    public Boolean getFileHandOver() {
        return fileHandOver;
    }

    public void setFileHandOver(Boolean fileHandOver) {
        this.fileHandOver = fileHandOver;
    }
}
