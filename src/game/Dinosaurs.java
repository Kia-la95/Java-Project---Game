package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public abstract class Dinosaurs extends Actor {

	protected int foodLevel;
	protected Behaviour behaviour;
	protected Random random = new Random();
	protected int number;
	protected int dinoAge;
	protected int maxFL;
	

	
	/**
	 * This is the Constructor 
	 * The reason of creating this class is, we extends it from Actor and put the similar methods for both Dinosaurs to avoid repetition
	 * @param name
	 */
	public Dinosaurs(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		
	}
	
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		return null;
	}
	
	
	
	/**
	 * This method is recieveing the Dinosaur's FoodLevel each turn and shows a message and says if the Dinosaur is hungry in any location
	 * @param location
	 */
	protected void hunger(Location location){
		getFoodLevel();
		if(foodLevel <= 10){
			System.out.println(this.name + " in location(" + location.x() + "," + location.y() + ") is hungry");
		}
	}
	
	
	/**
	 * This method checks the dinosar health and if the dinosaur health is zero it becomes Corpse
	 */
	protected void healthCheck(Actor actor,GameMap map){
		
		Location here = map.locationOf(this);
		map.removeActor(this);
		CorpseItem corpse = new CorpseItem("corpse");
		here.addItem(corpse);
		
		
}
	
	protected int getFoodLevel() {
		return foodLevel;
	}

	protected void setFoodLevel(int minFoodLevel) {
		this.foodLevel = minFoodLevel;
	}
	
}
