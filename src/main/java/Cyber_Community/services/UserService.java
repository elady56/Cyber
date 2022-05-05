package Cyber_Community.services;

import Cyber_Community.entities.User;

import java.util.Collection;


public interface UserService {

     void add(User user);
     Collection<User> getUsers();
     User getUser(long id);
     void removeUser(long id);
     boolean NickNameExisted(String nickName);
     boolean EmailExisted(String email);
     User update(Long id,User user);
     long getID(String nickName);
     User getUser(String nickName);

}