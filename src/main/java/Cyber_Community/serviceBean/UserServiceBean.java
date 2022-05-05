package Cyber_Community.serviceBean;

import Cyber_Community.entities.Role;
import Cyber_Community.repositories.*;
import Cyber_Community.services.UserService;
import Cyber_Community.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;


@Service
public class UserServiceBean implements UserService {
    @Autowired
    UserRepository userRepository;

   /*@PostConstruct //Example users
    public void init(){
        User us1 = new User("user","anon@hotmail.es","userPass","Hello", Role.USER);
        User us2 = new User("Brenda","hello@gmail.com","pass","Good Morning",Role.USER);
        this.userRepository.save(us1);
        this.userRepository.save(us2);
    }*/

    @Override
    public Collection<User> getUsers(){
        return this.userRepository.findAll();
    }

    @Override
    public void add(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User getUser(long id){
        return this.userRepository.findById(id).get();
    }

    @Override
    public long getID(String nickName) {
        for (User user : this.userRepository.findAll()) {
            if (user.getNickname().equals(nickName)) {
                return user.getId_User();
            }
        }
        return -1;
    }

    @Override
    public void removeUser(long id) {
        this.userRepository.deleteById(id);
    }

    /*
     * Future change id of user to nickname of user
     */
    @Override
    public boolean NickNameExisted(String nickName){
        for(User user : this.userRepository.findAll()){
            if(user.getNickname().equals(nickName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean EmailExisted(String email){
        for(User user : this.userRepository.findAll()){
            if(user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User update(Long id,User user){
        User newUser = this.userRepository.getById(id);
        newUser.setPassword(user.getPassword());
        newUser.setBiography(user.getBiography());
        newUser.setEmail(user.getEmail());
        newUser.setNickname(user.getNickname());
        return newUser;
    }
    @Override
    public User getUser(String nickName){
        for(User user:this.userRepository.findAll()){
            if(user.getNickname().equals(nickName)){
                return user;
            }
        }
        return null;
    }

}
