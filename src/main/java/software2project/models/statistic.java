package software2project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * This is a Statistic Model Class
 * @author Rick & morty
 *
 */
@Entity
public class statistic {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private boolean added;
	statistic(){
		
	}
	public statistic(Integer id, String name, boolean added) {
		super();
		this.id = id;
		this.name = name;
		this.added = added;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAdded() {
		return added;
	}
	public void setAdded(boolean added) {
		this.added = added;
	}
}
