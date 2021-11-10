package com.example.pt1.bean;

public class GameBean {
    private int GameID;
    private String GameName;
    private String Platform;
    private String ReleaseDate;
    private int MetaScore;
    private String Publisher;
    private String MetacriticURL;
    private String OfficialURL;

    public GameBean(int gameID, String gameName, String platform, String releaseDate, int metaScore, String publisher, String metacriticURL, String officialURL) {
        GameID = gameID;
        GameName = gameName;
        Platform = platform;
        ReleaseDate = releaseDate;
        MetaScore = metaScore;
        Publisher = publisher;
        MetacriticURL = metacriticURL;
        OfficialURL = officialURL;
    }

    public int getGameID() {
        return GameID;
    }

    public void setGameID(int gameID) {
        GameID = gameID;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public int getMetaScore() {
        return MetaScore;
    }

    public void setMetaScore(int metaScore) {
        MetaScore = metaScore;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getMetacriticURL() {
        return MetacriticURL;
    }

    public void setMetacriticURL(String metacriticURL) {
        MetacriticURL = metacriticURL;
    }

    public String getOfficialURL() {
        return OfficialURL;
    }

    public void setOfficialURL(String officialURL) {
        OfficialURL = officialURL;
    }
}

