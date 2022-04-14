package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 *this is action to buy Pteranodons Egg for $5000.
 */
public class BuyPteranodonsAction extends Action {


	private PteranodonsEggItem egg;
	private int price = 5000;
	
	public BuyPteranodonsAction(PteranodonsEggItem egg){
		this.egg = egg;
		
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor.hasMoney(price)){
			actor.subtractMoney(price);
			actor.addItemToInventory(egg);
			return menuDescription(actor);
		}
		return "Not enough money to buy " + egg;
		
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " buys " + egg + " for $" + price + "\n";
	}

}
