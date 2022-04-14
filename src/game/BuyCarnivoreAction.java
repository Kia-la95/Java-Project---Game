package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * 
 * and action to buy carnivore food for carnivorous.
 *
 */
public class BuyCarnivoreAction extends Action {

	private int price = 100;
	private CarnivoreItem carnivore;
	
	public BuyCarnivoreAction(CarnivoreItem carnivore){
		this.carnivore = carnivore;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor.hasMoney(price)){
			actor.subtractMoney(price);
			actor.addItemToInventory(carnivore);
			return menuDescription(actor);
		}
		else{
			return "Not enough money to but item " + carnivore;
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		return  actor + " buys " + carnivore + " for $" + price;
	}

}
