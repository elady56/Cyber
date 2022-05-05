package Cyber_Community.entities;

import javax.persistence.*;

/**
 * Created by Enzo Cotter on 12/04/2022.
 */
@Entity
public class BlogComent {
    @EmbeddedId
    BlogComentKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;


    @ManyToOne
    @MapsId("blogId")
    @JoinColumn(name = "blog_id")
    Blog blog;

    String comment;

}
