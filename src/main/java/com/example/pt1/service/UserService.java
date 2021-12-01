package com.example.pt1.service;

import com.example.pt1.bean.ReviewBean;
import com.example.pt1.bean.UserBean;

import java.util.List;

public interface UserService {

    UserBean loginIn(String name, String password);

    boolean signUp(String name, String password, String nickname);

    int getTotalNum();

    UserBean searchId(int id);

    void delById(int id);

    void editById(int id, String edit_un, String edit_pw, String edit_nn);

    List<UserBean> searchName(String searchName);

    void addHistory(int userID, String gameID);

    List<ReviewBean> getReviews(String gameName);

    void addReview(int userId, String gameName, int newScore, String newComment);
}
