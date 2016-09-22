package sand_box.tables.login;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Zinoviy on 9/5/16.
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private User_Role role;
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date last_seen_time;
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date registret_time;

    public User() {
    }

    public User(String login, String password, String email, User_Role role, Date last_seen_time, Date registret_time) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.last_seen_time = last_seen_time;
        this.registret_time = registret_time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User_Role getRole() {
        return role;
    }

    public void setRole(User_Role role) {
        this.role = role;
    }

    public Date getLast_seen_time() {
        return last_seen_time;
    }

    public void setLast_seen_time(Date last_seen_time) {
        this.last_seen_time = last_seen_time;
    }

    public Date getRegistret_time() {
        return registret_time;
    }

    public void setRegistret_time(Date registret_time) {
        this.registret_time = registret_time;
    }
}
