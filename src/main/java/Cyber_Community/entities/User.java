package Cyber_Community.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_User;

    //@Column(name = "admin")
    //private boolean admin;
    //@JoinColumn
    //@ElementCollection
    //@LazyCollection(LazyCollectionOption.TRUE)
    //@JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (user_entity_username) references user_entity(username) on delete cascade on update cascade"))
    private Role role;

    @Column(name = "nickname")
    private String nickname; //Unique

    //@NotBlank(message = "Email is required")
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "biography")
    private String biography;

    @OneToMany (cascade=CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference(value = "default")
    private List<Blog> blogs;

    //The comments made by this user to blogs
    //Future implementation
    /*@OneToMany(mappedBy = "user")
    List<BlogComent>coments;*/

    @ManyToMany(mappedBy = "members")
    @JsonIgnore
    @Column(nullable = true)
    @JsonIgnoreProperties("members")
    private List<Club> clubs;

    @OneToOne(cascade = CascadeType.REFRESH,mappedBy = "admin")
    @JsonBackReference (value = "defRef")
    private Club club;

    //builders
    public User(String nickname){
        //BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(10,new SecureRandom());
        this.role=Role.USER;
        this.nickname=nickname;
        this.email="default@gmail.com";
        this.biography="biography";
        //this.password=bCryptPasswordEncoder.encode("password");
        this.password="--";
    }

    public User(String nickname,String email,String password, String biography, Role role){

        //BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(10,new SecureRandom());
        this.nickname=nickname;
        this.email=email;
        //this.password=bCryptPasswordEncoder.encode(password);
        this.password=password;
        this.biography=biography;
        this.role=role;
        this.club=null;
    }

    /*@Override
    public String toString() {
        return "User [nickname:" + nickname + ", email:" + email + ", admin:" + admin + ", biography:" + biography+"]" ;
    }*/

    public void setId(long i){
        this.id_User = i;
    }

    public String getNickname (){
        return this.nickname;
    }


}
