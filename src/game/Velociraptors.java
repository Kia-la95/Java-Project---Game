package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Velociraptors extends VelociraptorsBaby {

	public Behaviour follow;
	
	/**
	 * All Velociraptors are represented by a 'V' and have 100 hit points.
	 * @param name
	 */
	public Velociraptors(String name) {
		super(name, 'V', 100);
		foodLevel = 30;
		dinoAge = 30;
	}

	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new AttackAction(this));
	}
	
	
	/**
	 * Figure out what to do next.
	 * 
	 * Before the dinosaur goes and follows its behaviours we should check something. We need to check teh dinosaur foodLevel, Chance of Breed and
	 * the Behaviour for eating food.
	 * 
	 * FIXME: Protoceratops wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 * 
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		List<Item> groundItems = map.locationOf(this).getItems();
		behaviour = new VeloFoodBehaviour(map.locationOf(this));
		foodLevel--;
		if(getFoodLevel() == 0){
			super.healthCheck(this,map);
				return new DoNothingAction();
		}
		
		Action findfood = behaviour.getAction(this, map);
		
		if(groundItems.size() > 0){
			for(int i = 0; i<= groundItems.size();i++){
				if(groundItems.get(i).hasSkill(Status.CORPSE)){
					map.locationOf(this).removeItem(groundItems.get(i));
					this.foodLevel += 50;
					System.out.println(name + " ate Corpse " + this.foodLevel);
					behaviour.getAction(this, map);
					}
				
				else if (groundItems.get(i).hasSkill(Status.PROEGG)){
					map.locationOf(this).removeItem(groundItems.get(i));
					this.foodLevel += 10;
					System.out.println(name + " ate ProEgg " + this.foodLevel);
					behaviour.getAction(this, map);
					}
				}
			}
		else{
			return findfood;
		}
		this.chanceOfBreed(map.locationOf(this));
		
		if (findfood != null)
			return findfood;
		
		return new DoNothingAction();
	}
	

	
	/**
	 * The reason behind this Override is we have 2 types of egg, one for Protoceraptos and the other is for Velociraptors. If we do not
	 * differentiate them, the Velociraptors might eat its own egg. So, to avoid this issue, we should addSkill to both eggs and differentiate
	 * them
	 */
	
	public void chanceOfBreed(Location location){
		if(dinoAge >= 30 && !location.containsAnActor()){
			if (this.getFoodLevel() > 20){
				number = random.nextInt(100) + 1;
				if (number > 90){
					VelociraptorsEggItem egg = new VelociraptorsEggItem("egg");
					location.addItem(egg);
			
		}
					
				}
	
		}
					
				}	
	
}
