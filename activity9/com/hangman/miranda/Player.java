package com.hangman.miranda;

public class Player {
    private String username;
    private int password;
    
    public Player(String username, int password) {
        this.username = username;
        this.password = password;
    }
    private int score;

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    }

}
