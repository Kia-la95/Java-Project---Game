package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SellAliveDinosaurs extends Action {

	Actor target;
	int price = 0;
	
	public SellAliveDinosaurs(Actor target, int price){
		this.target = target;
		this.price = price;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		
		actor.addMoney(price);
		map.removeActor(target);
		return menuDescription(actor);
	
	}

	@Override
	public String menuDescription(Actor actor) {
		
		return actor + " sels " + target + " for $" + price;
	}

}
