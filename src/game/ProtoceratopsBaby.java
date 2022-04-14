package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class ProtoceratopsBaby extends Dinosaurs {

	private Actions actions = new Actions();
	
	public ProtoceratopsBaby(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		foodLevel = 10;
		dinoAge = 0;
		maxFL = 200;
		actions.add(new AttackAction(this));
		actions.add(new SellAliveDinosaurs(this,500));
		
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return actions;
	}
	
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		behaviour = new ProFoodBehaviour(map.locationOf(this));
		
		foodLevel--;
		
		
		if(getFoodLevel() == 0){
		healthCheck(this,map);
		return new DoNothingAction();}
		
		this.dinoAge++;
		if(this.dinoAge == 20){
			Location here = map.locationOf(this);
			map.removeActor(this);
			map.addActor(new Protoceratops("Pro"), here);
			return new DoNothingAction();
		}
		
		Action findfood = behaviour.getAction(this, map);
		if(foodLevel >= maxFL){ eat(map.locationOf(this)); }
		
		
		
		if (findfood != null)
			return findfood;
		
		return new DoNothingAction();
	}
	
	/**
	 * This method is adding the Grass and Tree foodLevel to dinosaur's foodLevel
	 * This method shows a message and says if Dinosaur ate Grass or Tree and the increase of foodLevel
	 * This method is also replace the Grass and Tree to the dirt after they've eaten by a dinosaur
	 * 
	 * @param location of Trees and Grasses
	 * 
	 */
	protected int eat(Location location){
		hunger(location);
		if(location.getGround().hasSkill(Status.GRASS)){
			location.setGround(new Dirt());
			System.out.println(name + " ate grass, its food level is " + foodLevel);
			return foodLevel += 5;
		}
		else if(location.getGround().hasSkill(Status.YOUNG_TREE)){
			location.setGround(new Dirt());
			System.out.println(name + " ate tree, its food level is " + foodLevel);
			return foodLevel += 10;
		}
		else{
			return foodLevel;
		}
	}
	
}
