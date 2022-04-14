package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class VeloEatAction extends Actions {

	public VeloEatAction(Velociraptors V, Location location, Item item, int value){
		
		location.removeItem(item);
		V.setFoodLevel(V.getFoodLevel() + value);
		
	}
	
	
}
