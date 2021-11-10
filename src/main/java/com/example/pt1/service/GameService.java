package com.example.pt1.service;

import com.example.pt1.bean.GameBean;
import com.example.pt1.bean.TopGame;

import java.util.List;

public interface GameService {

    GameBean searchGameId(int id);

    List<GameBean> searchGameName(String searchName);

    List<GameBean> selectTop();

    List<GameBean> filterGame(String Platform, String Genre);

    List<TopGame> selectTopGame();

    List<TopGame> selectTopPublisher();
}
