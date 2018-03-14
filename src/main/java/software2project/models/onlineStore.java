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
	private String webSite;

	public onlineStore() {
		super();
	}
	
	public onlineStore(String name,String type ,user user,Set<storeProducts> products,String webSite, boolean accepted) {
		super(name, type,user,products, accepted);
		this.webSite = webSite;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}


}
