package com.example.pt1.serviceImpl;

import com.example.pt1.bean.GameBean;
import com.example.pt1.bean.TopGame;
import com.example.pt1.bean.UserBean;
import com.example.pt1.mapper.GameMapper;
import com.example.pt1.mapper.RecommendMapper;
import com.example.pt1.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Override
    public GameBean searchGameId(int id) {
        return gameMapper.getInfoFromId(id);
    }

    @Override
    public List<GameBean> searchGameName(String searchName) {
        return gameMapper.searchByName(searchName);
    }

    @Override
    public List<GameBean> selectTop() {
        return gameMapper.selectTop();
    }

    @Override
    public List<GameBean> filterGame(String Platform, String Genre) {
//        if (Platform.equals("All") && Genre.equals("All")){
//            return gameMapper.selectTop();
//        }
//        else if (Platform.equals("All")){
//            return gameMapper.selectGenre(Genre);
//        }
//        else if (Genre.equals("All")){
//            return gameMapper.selectPlatform(Platform);
//        }
//        else{
//            return gameMapper.filterGame(Platform, Genre);
//        }
        return gameMapper.filterGame(Platform, Genre);
    }

    @Override
    public List<TopGame> selectTopGame() {
        return gameMapper.selectTopGame();
    }

    @Override
    public List<TopGame> selectTopPublisher() {
        return gameMapper.selectTopPub();
    }

    @Override
    public int getReviewID(String GameName) {
        return gameMapper.getReviewID(GameName);
    }

    @Override
    public String getReviewPub(int pageID) {
        return gameMapper.getReviewPub(pageID);
    }

    @Override
    public String getReviewName(int pageID) {
        return gameMapper.getReviewName(pageID);
    }

    @Override
    public String getAvgScore(int pageID) {
        float AvgScore = gameMapper.getAvgScore(pageID);
        if (AvgScore<0){
            return "Not Applicable";
        }
        else{
            return String.valueOf(AvgScore);
        }
    }

    @Override
    public List<GameBean> getGames(String Genre) {
        return recommendMapper.getGames(Genre);
    }

}
