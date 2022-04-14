package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Pteranodons extends PteranodonsBaby {


		/**
		 * It is just a Constructor
		 * @param name of Dinosaur
		 */
	    public Pteranodons(String name) {
		super("Pteranodons", 'G', 500);
		foodLevel = 30;
		maxFL = 200;
		dinoAge = 30;
		addSkill(Status.PTERANODONS);
	}
	

	/**
	 * This is the Dinosaur playturn, it includes all the actions that this dinosaur must perform. like as findin food, eating and if
	 * it's the adult dinosaur, the breeding
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		behaviour = new PteranodonsBehaviour(map.locationOf(this));
		foodLevel--;
		if(getFoodLevel() == 0){
			healthCheck(this,map);
			return new DoNothingAction();
			}
		
		Action findfood = behaviour.getAction(this, map);
		eat(map.locationOf(this));
		chanceOfBreed(map.locationOf(this));

		
		if (findfood != null)
			return findfood;
		
		
		return new DoNothingAction();
	}
	
	
	

	
	/**
	 * This is a method that get a random number and give the dinousar a chance to breed and create a new egg 
	 * 
	 * 
	 */
	
	public void chanceOfBreed(Location location){
		if(dinoAge >= 30 && foodLevel >= 200){
			if (foodLevel > 20){
				number = random.nextInt(100) + 1;
				if (number > 90){
					ProtoceratopsEggItem egg = new ProtoceratopsEggItem("egg");
					location.addItem(egg);
			
		}
					
				}
	
		}
					
				}	
	
	

}
