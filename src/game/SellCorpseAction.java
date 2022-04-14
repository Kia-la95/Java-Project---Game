package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class SellCorpseAction extends Action {

	private int price;
	private Item corpse;
	
	public SellCorpseAction(Item item){
		this.corpse = item;
		price = 50;
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		
		actor.addMoney(price);
		actor.removeItemFromInventory(corpse);
		return menuDescription(actor);

	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " sells " + corpse + " for $" + price;
	}

}
