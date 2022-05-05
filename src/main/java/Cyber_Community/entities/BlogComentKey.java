package Cyber_Community.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Enzo Cotter on 12/04/2022.
 */
@Embeddable
public class BlogComentKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name="blog_id")
    Long blogId;
}
