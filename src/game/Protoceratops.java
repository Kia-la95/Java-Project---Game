package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A herbivorous dinosaur.
 *
 */
public class Protoceratops extends ProtoceratopsBaby {
	
	
	
	
	// Will need to change this to a collection if Protoceratops gets additional Behaviours.
	
	private Behaviour behaviour;
			
	/** 
	 * Constructor.
	 * All Protoceratops are represented by a 'P' and have 100 hit points.
	 * 
	 * @param name the name of this Protoceratops
	 */
	public Protoceratops(String name) {
		super(name, 'P', 100);
		foodLevel = 30;
		dinoAge = 30;
		addSkill(Status.PROTOCERATOPS);

		
	}

	/**
	 * Figure out what to do next.
	 * 
	 * Before the dinosaur goes and follows its behaviours we should check something. We need to check teh dinosaur foodLevel, Chance of Breed and
	 * the Behaviour for eating food
	 * 
	 * FIXME: Protoceratops wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 * 
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		behaviour = new ProFoodBehaviour(map.locationOf(this));
		
		foodLevel--;
		if(getFoodLevel() == 0){
		super.healthCheck(this,map);
		return new DoNothingAction();}
		
		Action findfood = behaviour.getAction(this, map);
		eat(map.locationOf(this));
		this.chanceOfBreed(map.locationOf(this));
		
		
		if (findfood != null){
			return findfood;
		}
		return new DoNothingAction();
	}
	
	
	/**
	 * The reason behind this Override is we have 2 types of egg, one for Protoceraptos and the other is for Velociraptors. If we do not
	 * differentiate them, the Velociraptors might eat its own egg. So, to avoid this issue, we should addSkill to both eggs and differentiate
	 * them
	 */
	
	public void chanceOfBreed(Location location){
		if(dinoAge >= 30 && foodLevel >= 50){
			if (this.getFoodLevel() > 20){
				number = random.nextInt(100) + 1;
				if (number > 95){
					ProtoceratopsEggItem egg = new ProtoceratopsEggItem("egg");
					location.addItem(egg);
			
		}
					
				}
	
		}
	}
	
}
