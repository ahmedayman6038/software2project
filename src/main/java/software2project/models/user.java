package software2project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This is a User Model Class
 * @author Rick & morty 
 *
 */
@Entity
public class user {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String email;
	private String name;
	private String password;
	private String type;
	
	public user() {
		
	}
	public user(String email, String name, String password, String type) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
