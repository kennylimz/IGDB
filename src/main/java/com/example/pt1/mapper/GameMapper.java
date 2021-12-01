package com.example.pt1.mapper;

import com.example.pt1.bean.GameBean;
import com.example.pt1.bean.TopGame;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameMapper {

    GameBean getInfoFromId(@Param("id") int id);

    List<GameBean> searchByName(@Param("searchName") String searchName);

    List<GameBean> selectTop();

    List<GameBean> filterGame(@Param("Platform") String Platform, @Param("Genre") String Genre);

    List<TopGame> selectTopGame();

    List<TopGame> selectTopPub();

    int getReviewID(@Param("GameName") String GameName);

    String getReviewPub(@Param("PageID") int pageID);

    String getReviewName(int pageID);

    float getAvgScore(int pageID);
}
