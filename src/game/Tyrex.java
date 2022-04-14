package game;


import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


public class Tyrex extends Dinosaurs {

	private static int maxFL = 2000;
	private Random random = new Random();
	private int age = 12;
	
	public Tyrex() {
		super("The great T-Rex", 'z', 10000);
		
		foodLevel = 50;
		
	}
	
	/**
	 * This is the Dinosaur playturn, it includes all the actions that this dinosaur must perform. like as findin food, eating and if
	 * it's the adult dinosaur, the breeding
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		behaviour = new TyrexBehviours(map.locationOf(this));
		
		
		foodLevel--;
		if(this.getFoodLevel() == 0){
			healthCheck(this,map);
			return new DoNothingAction();
			}
		
		age++;
		if(age == 21){
			displayChar = 'Z';
		}
		if(age == 11){
			System.out.println("\n\nWinner Winner Dinner T-Rex!");
			System.exit(1);
		}
		
		Action findfood = behaviour.getAction(this, map);
		eat(map.locationOf(this));
		eatAliveDino(map);
		breed(map.locationOf(this));

		
		if (findfood != null)
			return findfood;
		

		
		
		return new DoNothingAction();
	}
	
	/**
	 * 
	 * @param location gets the location of dinosaur and we check what items are on that location, if we the items have those skills 
	 * that the dinoasur looking for them, this methods add a specific amount of foodlevel to dinosaur's foodlevel
	 */
	private void eat(Location location){
		if(foodLevel <= maxFL){
			
			for(int x = 0; x<location.getItems().size(); x++){
				if(location.getItems().get(x).hasSkill(Status.CORPSE)){
					location.removeItem(location.getItems().get(x));
					foodLevel += 50;
				}
				else if(location.getItems().get(x).hasSkill(Status.EGG)){
					location.removeItem(location.getItems().get(x));
					foodLevel += 10;
				}
				else if(location.getItems().get(x).hasSkill(Status.CARNIVORE)){
					location.removeItem(location.getItems().get(x));
					foodLevel += 200;
					
				}
			}
		
	}
}
	/**
	 * This method gives an ability to the Tyrex to eat the live Dinosaurs, because Tyrex is the strongest Dinosaur in the game and
	 * can eat the Dinosaurs instantly
	 * @param map
	 */
	private void eatAliveDino(GameMap map){
		if(foodLevel <= maxFL){
			for(Exit exit: map.locationOf(this).getExits()){
				Location destination = exit.getDestination();
				if(destination.containsAnActor()){
					foodLevel += 300;
					System.out.println(this + " eats a " + destination.getActor() + " it's foodLevel is now " + foodLevel);
					map.removeActor(destination.getActor());
					
					
				}
			}
		}
	}
	

	/**
	 * This is a method that get a random number and give the dinousar a chance to breed and create a new egg 
	 * 
	 * 
	 */
	private void breed(Location location){
		if(foodLevel == 1600 && age >= 21){
			int number = random.nextInt(50);
			if(number == 1){
				location.addItem(new TyrexEggItem());
				age = 0;
			}
		}
		
	}


}
