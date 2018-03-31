package software2project.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<buyProducts> buyed;
	@OneToMany(mappedBy="user")
    private Set<store> store;
	
	public user() {
		
	}
	public user(String email, String name, String password, String type,Set<buyProducts> buyed) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.type = type;
		this.buyed = buyed;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Set<store> getStore() {
		return store;
	}
	public void setStore(Set<store> store) {
		this.store = store;
	}
	public Set<buyProducts> getBuyed() {
		return buyed;
	}
	public void setBuyed(Set<buyProducts> buyed) {
		this.buyed = buyed;
	}
}
