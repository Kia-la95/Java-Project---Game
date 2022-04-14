package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class PlesiosaursBaby extends Dinosaurs {

	private Actions actions = new Actions();
	
	/**
	 *  This is just a Constructor
	 * @param name of BabyDinosaur
	 * @param displayChar
	 * @param hitPoints
	 */
	public PlesiosaursBaby(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		foodLevel = 10;
		maxFL = 300;
		dinoAge = 0;
		addSkill(Status.PLESIOSAURS);
		actions.add(new AttackAction(this));
		actions.add(new SellAliveDinosaurs(this,10000));
		
		
		}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return actions;
	}
		
	
	
	/**
	 * This is the Dinosaur playturn, it includes all the actions that this dinosaur must perform. like as findin food, eating and if
	 * it's the adult dinosaur, the breeding
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		behaviour = new PlesiosaursBehaviours(map.locationOf(this));
		foodLevel--;
		
		if(getFoodLevel() == 0){
		healthCheck(this,map);
		return new DoNothingAction();}
		
		this.dinoAge++;
		if(this.dinoAge == 20){
			Location here = map.locationOf(this);
			map.removeActor(this);
			map.addActor(new Plesiosaurs("Plesiosaurs"), here);
			return new DoNothingAction();
		}
		
		Action findfood = behaviour.getAction(this, map);
		eat(map.locationOf(this));
		if (findfood != null)
			return findfood;
		
		return new DoNothingAction();
	}
	
	/**
	 * 
	 * @param location gets the location of dinosaur and we check what items are on that location, if we the items have those skills 
	 * that the dinoasur looking for them, this methods add a specific amount of foodlevel to dinosaur's foodlevel
	 */
	protected void eat(Location location){
			
		if(foodLevel <= maxFL)
			for(int x = 0; x<location.getItems().size(); x++){
				if(location.getItems().get(x).hasSkill(Status.CORPSE)){
					location.removeItem(location.getItems().get(x));
					foodLevel += 50;
				}
				else if(location.getItems().get(x).hasSkill(Status.FISH)){
					location.removeItem(location.getItems().get(x));
					foodLevel += 10;
				}
			}
			System.out.println(this + " food level increased and it is now " + this.foodLevel);
	}
}
	

