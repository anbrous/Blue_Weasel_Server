package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card {

	private long id;
	private String idRFID;
	private String player;
	public enum Value{Ace, King, Queen, Jack, Ten, Nine, Eight, Seven};
	public enum Color{Spade, Heart, Club, Diamond};
	
	private Value value;
	private Color color;
	public Card (){
		
	}
	
	public Card (String _idRFID, String player, Value _value, Color _color){
		
		this.idRFID = _idRFID;
		this.player = player;
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
	
	//méthode peut etre obsolète si on peut récupérer dans la base de données directement la valeur en enum
	public static Color StringColorToEnum(String color){ //color vaut entre 0 et 3 
		
		switch (color){
		
			case "Spade" : return Color.Spade; 		
			case "Heart" : return Color.Heart; 
			case "Club" : return Color.Club; 
			case "Diamond" : return Color.Diamond; 
		}
		return Color.Spade;
	}
	
	//méthode peut etre obsolète si on peut récupérer dans la base de données directement la valeur en enum
	public static Value StringValueToEnum(String value){ //color vaut entre 0 et 7
		
		switch (value){
		
			case "Ace" : return Value.Ace; 		
			case "King" : return Value.King; 
			case "Queen" : return Value.Queen; 
			case "Jack" : return Value.Jack; 
			case "Ten" : return Value.Ten; 		
			case "Nine" : return Value.Nine; 
			case "Eight" : return Value.Eight; 
			case "Seven" : return Value.Seven;
		}
		return Value.Ace;
	}
	// methods to retrieve values and colors into type int
	
	public int ValueToInt(){
		String string_value = ""+ this.value;
		switch (string_value){
			case "Ace" : return 0; 		
			case "King" : return 1; 
			case "Queen" : return 2; 
			case "Jack" : return 3; 
			case "Ten" : return 4; 		
			case "Nine" : return 5; 
			case "Eight" : return 6; 
			case "Seven" : return 7;
		}
		return (Integer) null;
	}
	
	public int ColorToInt() {
		String string_color = ""+ this.color;
		switch (string_color){
			case "Spade" : return 0; 		
			case "Heart" : return 1;
			case "Club" : return 2; 
			case "Diamond" : return 3;
		}
		return (Integer) null;
	}
	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}
	
	
}

