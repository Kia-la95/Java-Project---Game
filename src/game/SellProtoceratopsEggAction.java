package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class SellProtoceratopsEggAction extends Action {

	private int price = 10;
	private Item egg;
	
	public SellProtoceratopsEggAction(Item egg){
		this.egg = egg;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		
		actor.removeItemFromInventory(egg);
		actor.addMoney(price);
		return menuDescription(actor);

	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " sells " + egg + " for $" + price;
	}

}
