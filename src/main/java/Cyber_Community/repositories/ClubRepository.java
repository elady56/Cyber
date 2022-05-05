package Cyber_Community.repositories;

import Cyber_Community.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {
    //Queries
    List<Club> findClubsByNameContaining(String nombre);
    //List<Club> findClubsByNameContainingAndDescriptionContaining(String name,String description);

}
