package com.example.pt1.controller;

import com.example.pt1.bean.*;
import com.example.pt1.serviceImpl.GameServiceImpl;
import com.example.pt1.serviceImpl.UserServiceImpl;
import freemarker.ext.dom.NodeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    private UserBean curUserBean;
    private GameBean curGameBean;

    //将Service注入Web层
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    GameServiceImpl gameServiceImpl;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String show(){
        return "login";
    }

    @PostMapping(value = "/loginIn")
    public String login(String username, String password, Model model){
        UserBean userBean = userServiceImpl.loginIn(username, password);
        if(userBean!=null){
            curUserBean = new UserBean(
                    userBean.getId(),
                    userBean.getUsername(),
                    userBean.getPassword(),
                    userBean.getNickname()
            );
            model.addAttribute("nickname", curUserBean.getNickname());
            return "loginSuccess";
        }else {
            return "loginError";
        }
    }

    @PostMapping(value = "/signUp")
    public String signUp(String username, String password, String nickname){
        if(userServiceImpl.signUp(username,password,nickname)){
            return "signupSuccess";
        }else {
            return "invalidReg";
        }
    }

    @RequestMapping(value = "/toSignUp")
    public String toSignUp() {return "signup";}

    @RequestMapping(value = "/manager")
    public String manager(Model model){
        int totalNum = userServiceImpl.getTotalNum();
        List<UserBean> userBeanList= new ArrayList<>();
        int cnt = 0;
        for (int i=0; i<totalNum; i++){
            if (userServiceImpl.searchId(i+1)!=null){
                userBeanList.add(userServiceImpl.searchId(i+1));
                cnt++;
            }
        }
        String[][] userArray = new String[cnt][4];
        for (int i=0; i<cnt; i++){
            userArray[i][0] = String.valueOf(userBeanList.get(i).getId());
            userArray[i][1] = userBeanList.get(i).getUsername();
            userArray[i][2] = userBeanList.get(i).getPassword();
            userArray[i][3] = userBeanList.get(i).getNickname();
        }
        model.addAttribute("userList", userArray);
        return "userManager";
    }

    @RequestMapping(value = "/gameList")
    public String gameList(Model model){
        List<GameBean> gameBeanList= gameServiceImpl.selectTop();
        int cnt = gameBeanList.size();
        String[][] gameArray = new String[cnt][8];
        for (int i=0; i<cnt; i++){
            gameArray[i][0] = gameBeanList.get(i).getGameName();
            gameArray[i][1] = gameBeanList.get(i).getPlatform();
            gameArray[i][2] = gameBeanList.get(i).getReleaseDate();
            gameArray[i][3] = String.valueOf(gameBeanList.get(i).getMetaScore());
            gameArray[i][4] = gameBeanList.get(i).getPublisher();
            gameArray[i][5] = gameBeanList.get(i).getMetacriticURL();
            gameArray[i][6] = gameBeanList.get(i).getOfficialURL();
            gameArray[i][7] = "/reviews?gameid="+gameServiceImpl.getReviewID(gameArray[i][0]);
        }
        model.addAttribute("gameList", gameArray);
        model.addAttribute("curUserName", curUserBean.getNickname());

        List<String> recommendNames = userServiceImpl.getRecommend(curUserBean.getId());
        List<GameBean> recommendList= gameServiceImpl.getGameBeans(recommendNames);
        cnt = recommendList.size();
        String[][] recomendArray = new String[cnt][8];
        for (int i=0; i<cnt; i++){
            recomendArray[i][0] = recommendList.get(i).getGameName();
            recomendArray[i][1] = recommendList.get(i).getPlatform();
            recomendArray[i][2] = recommendList.get(i).getReleaseDate();
            recomendArray[i][3] = String.valueOf(recommendList.get(i).getMetaScore());
            recomendArray[i][4] = recommendList.get(i).getPublisher();
            recomendArray[i][5] = recommendList.get(i).getMetacriticURL();
            recomendArray[i][6] = recommendList.get(i).getOfficialURL();
            recomendArray[i][7] = "/reviews?gameid="+gameServiceImpl.getReviewID(recomendArray[i][0]);
        }
        model.addAttribute("recommendList", recomendArray);

        return "gameList";
    }

    @RequestMapping(value = "/whichIsBest")
    public String whichIsBest(Model model) {
        List<TopGame> gameBeanList = gameServiceImpl.selectTopGame();
        int cnt = gameBeanList.size();
        String[][] gameArray = new String[cnt][2];
        for (int i=0; i<cnt; i++){
            gameArray[i][0] = gameBeanList.get(i).GameName;
            gameArray[i][1] = String.valueOf(gameBeanList.get(i).AvgScore);
        }
        model.addAttribute("gameList", gameArray);
        gameBeanList = gameServiceImpl.selectTopPublisher();
        cnt = gameBeanList.size();
        gameArray = new String[cnt][2];
        for (int i=0; i<cnt; i++){
            gameArray[i][0] = gameBeanList.get(i).GameName;
            gameArray[i][1] = String.valueOf(gameBeanList.get(i).AvgScore);
        }
        model.addAttribute("pubList", gameArray);
        return "whichIsBest";
    }

    @PostMapping(value = "/managerSignUp")
    public String managerSignup(String username, String password, String nickname, Model model) {
        userServiceImpl.signUp(username, password, nickname);
        return manager(model);
    }

    @PostMapping(value = "/delUser")
    public String delUser(String idToDel, Model model){
        int id = Integer.parseInt(idToDel);
        userServiceImpl.delById(id);
        return manager(model);
    }

    @PostMapping(value = "/managerLogIn")
    public String managerLogIn(String loginusername, String loginpassword, Model model){
        UserBean userBean = userServiceImpl.loginIn(loginusername, loginpassword);
        if(userBean!=null){
            curUserBean = new UserBean(
                    userBean.getId(),
                    userBean.getUsername(),
                    userBean.getPassword(),
                    userBean.getNickname()
            );
            model.addAttribute("nickname", curUserBean.getNickname());
            return "loginSuccess";
        }else {
            return "loginError";
        }
    }

    @PostMapping(value = "/managerEdit")
    public String managerEdit(String edit_id, String edit_un, String edit_pw, String edit_nn, Model model){
        int id = Integer.parseInt(edit_id);
        userServiceImpl.editById(id, edit_un, edit_pw, edit_nn);
        System.out.println(edit_id+edit_un);
        return manager(model);
    }

    @PostMapping(value = "/managerSearch")
    public String managerSearch(String searchName, Model model){
        List<UserBean> userBeanList= userServiceImpl.searchName(searchName);
        int cnt = userBeanList.size();
        String[][] userArray = new String[cnt][4];
        for (int i=0; i<cnt; i++){
            userArray[i][0] = String.valueOf(userBeanList.get(i).getId());
            userArray[i][1] = userBeanList.get(i).getUsername();
            userArray[i][2] = userBeanList.get(i).getPassword();
            userArray[i][3] = userBeanList.get(i).getNickname();
        }
        model.addAttribute("userList", userArray);
        return "userSearchPage";
    }

    @PostMapping(value = "/gameSearch")
    public String gameSearch(String searchName, Model model){
        List<GameBean> gameBeanList= gameServiceImpl.searchGameName(searchName);
        int cnt = gameBeanList.size();
        String[][] gameArray = new String[cnt][8];
        for (int i=0; i<cnt; i++){
            gameArray[i][0] = gameBeanList.get(i).getGameName();
            gameArray[i][1] = gameBeanList.get(i).getPlatform();
            gameArray[i][2] = gameBeanList.get(i).getReleaseDate();
            gameArray[i][3] = String.valueOf(gameBeanList.get(i).getMetaScore());
            gameArray[i][4] = gameBeanList.get(i).getPublisher();
            gameArray[i][5] = gameBeanList.get(i).getMetacriticURL();
            gameArray[i][6] = gameBeanList.get(i).getOfficialURL();
            gameArray[i][7] = "/reviews?gameid="+gameServiceImpl.getReviewID(gameArray[i][0]);
        }
        model.addAttribute("gameList", gameArray);
        model.addAttribute("curUserName", curUserBean.getNickname());
        return "gameSearchPage";
    }

    @PostMapping(value = "/gameFilter")
    public String gameFilter(String Platform, String Genre, Model model){
        List<GameBean> gameBeanList = gameServiceImpl.filterGame(Platform, Genre);
        int cnt = gameBeanList.size();
        String[][] gameArray = new String[cnt][8];
        for (int i=0; i<cnt; i++){
            gameArray[i][0] = gameBeanList.get(i).getGameName();
            gameArray[i][1] = gameBeanList.get(i).getPlatform();
            gameArray[i][2] = gameBeanList.get(i).getReleaseDate();
            gameArray[i][3] = String.valueOf(gameBeanList.get(i).getMetaScore());
            gameArray[i][4] = gameBeanList.get(i).getPublisher();
            gameArray[i][5] = gameBeanList.get(i).getMetacriticURL();
            gameArray[i][6] = gameBeanList.get(i).getOfficialURL();
            gameArray[i][7] = "/reviews?gameid="+gameServiceImpl.getReviewID(gameArray[i][0]);
        }
        model.addAttribute("gameList", gameArray);
        return "gameSearchPage";
    }

    @RequestMapping(value = "/reviews")
    public String gameReview(@RequestParam(value="gameid") String gameID, Model model){
        int pageID = Integer.parseInt(gameID);
        String gameName = gameServiceImpl.getReviewName(pageID);
        if (curUserBean == null){
            System.out.println("Guest");
        }
        else{
            System.out.println(curUserBean.getNickname());
            userServiceImpl.addHistory(curUserBean.getId(), gameName);
        }
        String GameName = gameServiceImpl.getReviewName(pageID);
        model.addAttribute("GameName", GameName);
        System.out.println(GameName);
        String Publisher = gameServiceImpl.getReviewPub(pageID);
        model.addAttribute("Publisher", Publisher);
        String AvgScore = gameServiceImpl.getAvgScore(pageID);
        model.addAttribute("AvgScore", AvgScore);
        String AvgMetaScore = gameServiceImpl.getAvgMetaScore(GameName);
        model.addAttribute("AvgMetaScore", AvgMetaScore);

        List<ReviewBean> reviewList = userServiceImpl.getReviews(GameName);
        int cnt = reviewList.size();
        String[][] reviewArray = new String[cnt][3];
        for (int i=0; i<cnt; i++){
            int commentorID = reviewList.get(i).getUserID();
            reviewArray[i][0] = userServiceImpl.searchId(commentorID).getNickname();
            reviewArray[i][1] = String.valueOf(reviewList.get(i).getScore());
            reviewArray[i][2] = reviewList.get(i).getComment();
        }
        model.addAttribute("reviewList", reviewArray);
        model.addAttribute("UserId", curUserBean.getId());
        return "reviewPage";
    }

    @PostMapping(value = "/createReview")
    public String createReview(String userId, String gameName, String newScore, String newComment, Model model){
        userServiceImpl.addReview(Integer.parseInt(userId), gameName, Integer.parseInt(newScore), newComment);
        int gameID = gameServiceImpl.getReviewID(gameName);
        return gameReview(String.valueOf(gameID), model);
    }

}
