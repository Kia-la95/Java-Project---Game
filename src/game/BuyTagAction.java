package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class BuyTagAction extends Action {

	private int price = 0;
	private TagItem tag;
	
	public BuyTagAction(TagItem tag){
		this.tag = tag;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.subtractMoney(price);
		actor.addItemToInventory(tag);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " buys " + tag + " for free";
	}

}
