package software2project.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * This is a Brand Model Class
 * @author Rick & morty
 *
 */
@Entity
public class brand {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String company;
	
	@OneToMany(mappedBy="brand")
    private Set<product> products;
	
	public brand() {
		
	}
	public brand(String name, String company) {
		super();
		this.name = name;
		this.company = company;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
