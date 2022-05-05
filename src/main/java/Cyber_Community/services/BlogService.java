package Cyber_Community.services;

import Cyber_Community.entities.Blog;

import java.util.Collection;

public interface BlogService {

    Collection<Blog> findAll();
    Blog findOne(Long id);
    Blog create(Long id,Blog blog);
    Blog update(Blog blog,Long id);
    void delete(Long id);

}
