package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 *this is action to buy Plesopsaurs Egg for $50000.
 */
public class BuyTyrexEggAction extends Action {

	private int price = 50000;
	private TyrexEggItem egg;
	
	public BuyTyrexEggAction(TyrexEggItem egg){
		this.egg = egg;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor.hasMoney(price)){
			actor.subtractMoney(price);
			actor.addItemToInventory(egg);
			return menuDescription(actor);
		}
		else{
			return "not enough money to buy " + egg;
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " buys " + egg + " for $" + price;
	}

}
