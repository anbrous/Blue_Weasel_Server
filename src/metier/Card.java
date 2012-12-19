package metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {

	private String idRFID;
	public enum Value{ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN};
	public enum Color{HEART, DIAMOND, SPADE, CLUB};
	
	private Value value;
	private Color color;
	
	public Card (){
		
	}
	
	public Card (String _idRFID, Value _value, Color _color){
		
		this.idRFID = _idRFID;
		this.setValue(_value);
		this.setColor(_color);
	}
	
	public void initializeCards (){
		
	}

	
	@Id
	public String getIdRFID() {
		return idRFID;
	}

	public void setIdRFID(String idRFID) {
		this.idRFID = idRFID;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}

