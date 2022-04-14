package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 *this is action to buy Plesopsaurs Egg for $5000.
 */
public class BuyPlesiosaursEggAction extends Action {


	private PlesiosaursEggItem egg;
	private int price = 5000;
	
	public BuyPlesiosaursEggAction(PlesiosaursEggItem egg){
		this.egg = egg;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor.hasMoney(price)){
			actor.subtractMoney(price);
			actor.addItemToInventory(egg);
			return menuDescription(actor);
		}
		return "Not enough money to but item " + egg;
		
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " buys " + egg + " for $" + price;
	}

}
