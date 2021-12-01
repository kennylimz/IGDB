package com.example.pt1.mapper;

import com.example.pt1.bean.GenreCount;
import com.example.pt1.bean.GameBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecommendMapper {

    List<GenreCount> getGenre();

    List<GameBean> getGames(@Param("genre") String Genre);

}
