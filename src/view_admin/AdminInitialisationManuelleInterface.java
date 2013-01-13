package view_admin;

import model.Card.Color;
import model.Card.Value;

public interface AdminInitialisationManuelleInterface {

	
	public void MakeTable(); // créé dans la base de données la 
							//liste de cartes avec RFID en NULL
	
	public void ManualCardConfiguration(Value value, Color color, String idRFID); // permet de modifier la RFID d'une carte
																	// dans la base de données

	
}
