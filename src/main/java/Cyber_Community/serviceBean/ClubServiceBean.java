package Cyber_Community.serviceBean;

import Cyber_Community.entities.Blog;
import Cyber_Community.entities.Club;
import Cyber_Community.entities.Role;
import Cyber_Community.entities.User;
import Cyber_Community.repositories.*;
import Cyber_Community.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;


@Service
public class ClubServiceBean implements ClubService {
    @Autowired
    ClubRepository clubRepository;

    @Autowired
    BlogRepository blogRepository;

   /* @PostConstruct //Example club one
    public void init(){
        //Create users
        //User user=new User("NoBody","anyone@hotmail.es","pass","---",false);
        User belen=new User("Belén","belen@hotmail.com","Trower","No~", Role.USER);
        User enzo=new User("Enzo","enzo@hotmail.es","Pass","This is me",Role.USER);
        User elady=new User("Elady","elady@hotmail.es","passwd","-Hi",Role.USER);
        String desciption= "Test" + "\n" + "This is a test and a description just to so: " +
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem.\n" +
                "\n" +
                "Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a,";
        String description2="To join this club, you must unscramble the following sentence and follow the instructions. \nFUORRJDXDLQQWW wr ghflskhu wkh vhqwhqfh, wkh rqob wklqj brx qhhg wr gr lv vhqg dq hpdlo wr wkh dgplq zlwk wklv udqgrp qxpehu soxv brxu djh dqg iroorzhg eb brxu qlfnqdph.\n" +
                "Wkh hpdlo ri wkh dgplq lv : dgplqFubswr@fbehu.frp";
        //Create clubs
        Club club1=new Club("Test club 1",desciption,belen,"Contact");
        Club club2=new Club("Cryptography",description2,enzo,"98eakrLgCFrcitwgMM8KDqtBGHwykrEt4");
        Club club3=new Club("Test club 3","Club 3",elady,"65897256");
        //club3.getMembers().add(belen);
       // club4.getMembers().add(elady);
        //Create blogs
        Blog test1 = new Blog("Blog test 1","Content", "Resume",belen);
        Blog test2 = new Blog("Blog test 2","To find the content yo need to decrpyt this :)", "V2VsY29tZSB0byB0aGUgYmxvZyE=",enzo);
        Blog test3 = new Blog("Blog test 3","Content....", "Resume....",enzo);
        club1.addBlog(test1);
        club2.addBlog(test2);
        club3.addBlog(test3);
        this.clubRepository.save(club1);
        this.clubRepository.save(club2);
        this.clubRepository.save(club3);
        //this.clubRepository.save(club4);
        this.blogRepository.save(test1);
        this.blogRepository.save(test2);
        this.blogRepository.save(test3);
    }*/

    @Override
    public Collection<Club> findAll() {
        return this.clubRepository.findAll();
    }

    @Override
    public List<Club>findClubsByNameContaining(String name){
        return clubRepository.findClubsByNameContaining(name);
    }

    @Override
    public Club findOne(Long id) {
        return this.clubRepository.getById(id);
    }

    @Override
    public Club create(Club club) {
        return this.clubRepository.save(club);
    }

    @Override
    public Club update(Club club,Long id) {

        Club club1=this.clubRepository.getById(id);
        club1.setName(club.getName());
        club1.setDescription(club.getDescription());
        club1.setContact(club.getContact());
        return club1;
    }

    @Override
    public void delete(Long id) {
        this.clubRepository.deleteById(id);
    }



}
