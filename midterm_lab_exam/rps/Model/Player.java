package com.rps.Model;

public class Player {
    private String username;
    private int password;
    private int wins;
    private int loses;
    private int winrate; // store as int for JSON

    public Player(String username, int password) {
        this.username = username;
        this.password = password;
        this.wins = 0;
        this.loses = 0;
        updateWinRate();
    }

    public String getUsername() {
        return username;
    }

    public int getPassword() {
        return password;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getWinRate() {
        return winrate;
    }

    public void setWins(int wins) {
        this.wins = wins;
        updateWinRate();
    }

    public void setLoses(int loses) {
        this.loses = loses;
        updateWinRate();
    }

    public void setWinrate(int winrate) {
        this.winrate = winrate;
    }

    public void addWin() {
        this.wins++;
        updateWinRate();
    }

    public void addLose() {
        this.loses++;
        updateWinRate();
    }

    private void updateWinRate() {
        int total = wins + loses;
        this.winrate = total == 0 ? 0 : (int) Math.round((wins * 100.0) / total);
    }
}