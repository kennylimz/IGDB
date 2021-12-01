package com.example.pt1.bean;

public class ReviewBean {
    private int ReviewID;
    private int UserID;
    private String GameName;
    private int Score;
    private String Comment;

    public ReviewBean(int reviewID, int userID, String gameName, int score, String comment) {
        ReviewID = reviewID;
        UserID = userID;
        GameName = gameName;
        Score = score;
        Comment = comment;
    }

    public int getReviewID() {
        return ReviewID;
    }

    public void setReviewID(int reviewID) {
        ReviewID = reviewID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
