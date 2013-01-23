package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardValueTempo {

	private long id;
	private String valueArecuperer;
	
	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValueArecuperer() {
		return valueArecuperer;
	}

	public void setValueArecuperer(String valueArecuperer) {
		this.valueArecuperer = valueArecuperer;
	}



}
