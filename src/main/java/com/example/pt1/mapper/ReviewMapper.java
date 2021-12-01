package com.example.pt1.mapper;

import com.example.pt1.bean.ReviewBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

    void addReview(@Param("userid") int userID, @Param("gamename") String gameName, @Param("score") int Score, @Param("comment") String Comment);

    List<ReviewBean> getReviews(@Param("gamename") String gameName);

}
