package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CardIdTempo {

	private long id;
	private String idArecuperer;
	
	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdArecuperer() {
		return idArecuperer;
	}

	public void setIdArecuperer(String idArecuperer) {
		this.idArecuperer = idArecuperer;
	}
	
	public void initializeIdArecuperer() {
		this.idArecuperer = "nocard";
	}

	
}
