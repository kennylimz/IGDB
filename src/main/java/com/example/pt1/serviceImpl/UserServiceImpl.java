package com.example.pt1.serviceImpl;

import com.example.pt1.bean.UserBean;
import com.example.pt1.mapper.UserMapper;
import com.example.pt1.mapper.RecommendMapper;
import com.example.pt1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Override
    public UserBean loginIn(String username, String password) {
        return userMapper.getInfo(username,password);
    }

    @Override
    public boolean signUp(String name, String password, String nickname) {
        if (name.isEmpty() || nickname.isEmpty()){
            return false;
        }
        else if(userMapper.getInfo(name,password)!=null){
            return false;
        }
        else if(!userMapper.ifAvailable(name,nickname)){
            return false;
        }
        else {
            userMapper.addInfo(name, password, nickname, 0);
            return true;
        }
    }

    @Override
    public int getTotalNum() {
        return userMapper.getMaxId();
    }

    @Override
    public UserBean searchId(int id) {
        return userMapper.getInfoFromId(id);
    }

    @Override
    public void delById(int id){
        userMapper.deleteById(id);
    }

    @Override
    public void editById(int id, String edit_un, String edit_pw, String edit_nn) {
        userMapper.editUsername(id, edit_un);
        userMapper.editPassword(id, edit_pw);
        userMapper.editNickname(id, edit_nn);
    }

    @Override
    public List<UserBean> searchName(String searchName) {
        List<Integer> idList1 = userMapper.searchByName(searchName);
        List<Integer> idList2 = userMapper.searchByNickName(searchName);
        List<Integer> idList;
        idList1.addAll(idList2);
        idList = idList1.stream().distinct().collect(Collectors.toList());
        List<UserBean> userBeanList = new ArrayList<>();
        for (int i: idList){
            userBeanList.add(userMapper.getInfoFromId(i));
        }
        return userBeanList;
    }

    @Override
    public void addHistory(int userID, String gameName) {
        recommendMapper.addHistory(userID, gameName);
    }
}

