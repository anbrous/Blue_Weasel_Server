package metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {

	private long id;
	private String idRFID;
	public enum Value{Ace, King, Queen, Jack, Ten, Nine, Eight, Seven};
	public enum Color{Spade, Heart, Club, Diamond};
	
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
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
	
	//m�thode peut etre obsol�te si on peut r�cup�rer dans la base de donn�es directement la valeur en enum
	public Color StringColorToEnum(String color){ //color vaut entre 0 et 3 
		
		switch (color){
		
			case "0" : return Color.Spade; 		
			case "1" : return Color.Heart; 
			case "2" : return Color.Club; 
			case "3" : return Color.Diamond; 
		}
		return Color.Spade;
	}
	
	//m�thode peut etre obsol�te si on peut r�cup�rer dans la base de donn�es directement la valeur en enum
	public Value StringValueToEnum(String value){ //color vaut entre 0 et 7
		
		switch (value){
		
			case "0" : return Value.Ace; 		
			case "1" : return Value.King; 
			case "2" : return Value.Queen; 
			case "3" : return Value.Jack; 
			case "4" : return Value.Ten; 		
			case "5" : return Value.Nine; 
			case "6" : return Value.Eight; 
			case "7" : return Value.Seven;
		}
		return Value.Ace;
	}
	
	
}

