package Cyber_Community.entities;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table
@Table(name="clubs")

public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "club_id")
    private Long club_id;

    @Column(name = "name")
    private String name;

    @Lob
    //@Size(max=1000,message="Description too long!")
    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "contact")
    private String contact;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Blog.class, mappedBy = "club")
    @Column(nullable = true)
    @JsonManagedReference(value = "def")
    private List<Blog> blogs;

    //@Valid
    //@NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference (value = "defRef")
    private User admin;

    @Column(nullable = true)
    @ManyToMany
    @JsonIgnoreProperties("clubs")
    @JoinTable(
            name = "club_members",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members;

    //builder
    public Club(String name,String description,User user, String contact) {
        this.name = name;
        this.description=description;
        this.admin=user;
        this.admin.setClub(this);
        this.contact= contact;
        this.blogs=new ArrayList<>();
        this.members=new ArrayList<>();
    }

    //getter and setter
    public void setBlogs(List<Blog> blogs) {
        blogs.forEach(blog -> blog.setClub(this));
        this.blogs = blogs;
    }

    public List<Blog>getBlogs(){
        return this.blogs;
    }

    //auxiliar method
    public void addBlog(Blog blog){
        this.blogs.add(blog);
        blog.setClub(this);
        blog.setDefaultSetting();
    }

    public void setDefaultSetting(){
        String name="DefaultAdmin";
        String email="4n0nymou5@cyber.es";
        User defaultAdmin=new User(name,email,"******","----",Role.USER);
        this.admin=defaultAdmin;
    }



}
