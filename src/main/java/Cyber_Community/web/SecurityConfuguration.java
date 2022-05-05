package Cyber_Community.web;

import Cyber_Community.entities.User;
import Cyber_Community.serviceBean.RepositoryUserDetailsService;
import Cyber_Community.serviceBean.UserServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity()
public class SecurityConfuguration extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Autowired
    UserServiceBean userServiceBean;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.userDetailsService=new RepositoryUserDetailsService();

        for(User user:userServiceBean.getUsers()){
            auth.inMemoryAuthentication().withUser(user.getNickname())
                    .password(encoder.encode(user.getPassword())).roles(user.getRole().getName());
        }




        //ADMIN
        auth.inMemoryAuthentication().withUser("admin")
                .password(encoder.encode("adminPass")).roles("ADMIN","MANAGER","USER");
        //MANAGER
        auth.inMemoryAuthentication().withUser("manager")
                .password(encoder.encode("managerPass")).roles("MANAGER","USER");
        //USER
        auth.inMemoryAuthentication().withUser("Brenda")
                .password(encoder.encode("pass")).roles("USER","ADMIN");
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }




    @Override
    protected  void configure(HttpSecurity http)throws Exception{



//static mappings
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/css/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/images/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/js/**").permitAll();

        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/images/**").permitAll();
        http.authorizeRequests().antMatchers("/js/**").permitAll();
        http.authorizeRequests().antMatchers("/error").permitAll();
        //Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/club").permitAll();
        http.authorizeRequests().antMatchers("/club/{id}").permitAll();
        http.authorizeRequests().antMatchers("/club/{idC}/blog/{idB}").permitAll();
        http.authorizeRequests().antMatchers("/lmau").permitAll();
        http.authorizeRequests().antMatchers("/club/blogSearch").permitAll();
        http.authorizeRequests().antMatchers("/club/search/byName").permitAll();
        http.authorizeRequests().antMatchers("/user/search/byName").permitAll();
        //Logging
        http.authorizeRequests().antMatchers("/Login").permitAll();
        http.authorizeRequests().antMatchers("/login/error").permitAll();
        http.authorizeRequests().antMatchers("/signup").permitAll();
        http.authorizeRequests().antMatchers("/signup/error").permitAll();
        http.authorizeRequests().antMatchers("/Login").permitAll();
        http.authorizeRequests().antMatchers("/user/check").permitAll();
        http.authorizeRequests().antMatchers("/user/new").permitAll();
        http.authorizeRequests().antMatchers("/user/seeProfile/{id}").permitAll();
        http.authorizeRequests().antMatchers("/search/byName").permitAll();


        //Private pages - USER
        http.authorizeRequests().antMatchers("/upUser").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/logged/{id}").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/logged/{id}/lmau").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/logged/{id}/club").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/logged/{id}/user").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/user/seeProfile/{id}").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/logged/club").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/blog/new").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/{idC}/blog/new").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/application").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/{idC}/blog/{idB}/addLike").hasAnyRole("USER","MANAGER","ADMIN");

        //ONLY OWNER OR ADMIN
        http.authorizeRequests().antMatchers("/club/{idC}/blog/{idB}/delete").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/{idC}/blog/{idB}/edit").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/blog/edit").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/user/update/{id}").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/user/update/{id}/user").hasAnyRole("USER","MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/user/delete/{id}").hasAnyRole("USER","MANAGER","ADMIN");


        //Private pages - MANAGER of their own club
        http.authorizeRequests().antMatchers("/club/new").hasAnyRole("MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/update").hasAnyRole("MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/delete").hasAnyRole("MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/delete/{id}").hasAnyRole("MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/Edit").hasAnyRole("MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/edit/{id}").hasAnyRole("MANAGER","ADMIN");
        http.authorizeRequests().antMatchers("/club/edit").hasAnyRole("MANAGER","ADMIN");


        //Private pages - ADMIN
        http.authorizeRequests().antMatchers("/logged/{id}/admin/**").hasAnyRole("ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/error").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/forbidden").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/login/error").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/logout").permitAll();

        //http.authorizeRequests().anyRequest().denyAll();

        //Login form
        http.formLogin().loginPage("/Login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/Login/error");

        //Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

        http.exceptionHandling().accessDeniedPage("/error/forbidden");

        http.csrf().disable();


    }



}
