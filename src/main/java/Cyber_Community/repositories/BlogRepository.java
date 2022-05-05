package Cyber_Community.repositories;

import Cyber_Community.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long>{
    //methods from spring data
    List<Blog> findByTitleContaining(String text); //find title
    //List<Blog>findByDate(Date date);
    //List<Blog>findByAuthor(String n);
   // List<Blog>findByTitleContainingAndDateBetween(String text,Date date1,Date date2);
}
