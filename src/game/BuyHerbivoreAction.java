package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * 
 * an action to buy herbivore for herbivorous dinosaurs. 
 *
 */
public class BuyHerbivoreAction extends Action {

	private int price = 20;
	private HerbivoreItem herbs;
	
	
	public BuyHerbivoreAction(HerbivoreItem herbs){
		this.herbs = herbs;
	
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor.hasMoney(price)){
			actor.subtractMoney(price);
			actor.addItemToInventory(herbs);
			return menuDescription(actor);
		}
		else{
			return "Not enough money to but item " + herbs;
		}
	}

	@Override
	public String menuDescription(Actor actor) {

		return actor + " buys " + herbs + " for $" + price;
	}

}
