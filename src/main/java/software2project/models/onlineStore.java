package software2project.models;

import java.util.Set;

import javax.persistence.Entity;

/**
 * This is a OnlineStore Model Class Extends From Store Model Class
 * @author Rick & morty
 *
 */
@Entity
public class onlineStore extends store{
	private String webSiteName;

	public onlineStore() {
		
	}
	public onlineStore(String name, Set<product> products,String webSiteName) {
		super(name, products);
		this.webSiteName = webSiteName;
	}

	public String getWebSiteName() {
		return webSiteName;
	}

	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
	}


}
