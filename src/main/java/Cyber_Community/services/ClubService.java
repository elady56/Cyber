package Cyber_Community.services;

import Cyber_Community.entities.Club;

import java.util.Collection;

public interface ClubService {
    Collection<Club> findAll();
    Collection<Club> findClubsByNameContaining(String name);
    Club findOne(Long id);
    Club create(Club club);
    Club update(Club club,Long id);
    void delete(Long id);


}
