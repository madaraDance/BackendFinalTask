package hourlyworkapplication.hourlyworkapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name= "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    @Column(name="username", nullable = false, updatable = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;
    
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<TimeLog> timeLogList;

    
    public AppUser(){}

    public AppUser(String username, String passwordHash, String role, String email){
        super();
        this.username = username;
        this.password = passwordHash;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TimeLog> getTimeLogs() {
		return timeLogList;
	}

	public void setTimeLogs(List<TimeLog> timeLogList) {
		this.timeLogList = timeLogList;
	}
    
}
