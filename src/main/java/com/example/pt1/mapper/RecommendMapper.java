package com.example.pt1.mapper;

import com.example.pt1.bean.GameBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendMapper {

    String getGenre(@Param("userid") int UserID);

    List<GameBean> getGames(@Param("genre") String Genre);

    void addHistory(@Param("userid") int userID, @Param("gamename") String gameName);
}
