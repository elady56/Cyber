package Cyber_Community.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    private Long blog_id;

    static private Integer aux=0;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content", length = 1000)
    private String content;

    @Lob
    @Column(name = "resume", length = 1000)
    private String resume;

    @CreatedDate
    @Column(name = "date")
    private Date date;

    @Column(name = "nLike")
    private int nLike;

    @ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER, targetEntity = Club.class)
    @JoinColumn(name = "club_id")
    @JsonBackReference(value = "def")
    private Club club;

    @CreatedBy
    @ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_nickname")
    @JsonBackReference(value = "default")
    private User author;

    //Comments of this blog
    //Future implementation
    /*@OneToMany(mappedBy = "blog")
    List<BlogComent>coments;*/

    //builder

    public Blog(String t, String c, String r, User user){
        this.title = t;
        this.content = c;
        this. resume = r;
        this.date = new Date();
        this.nLike=0;
        this.author=user;
    }






    //auxiliar method
    public void addLike(){
        this.nLike++;
    }
    public void setDefaultSetting(){
        this.aux++;
        String num=this.aux.toString();
        String name="DefaultUser ";
        String email="4n0nymou5@"+this.aux.toString()+"cyber.es";
        User defaultUser=new User(name+num,email+num,"******","----",Role.USER);
        this.date=new Date();
        this.author=defaultUser;
    }

   /* @Override
    public String toString() {
        return "Blog {" + "title='" + title + '\'' + ", content='" + content + '\'' + ", resume='" + resume + '\'' +
                ", author='" + author + '\'' +", date=" + date + '}';
    }*/



}
