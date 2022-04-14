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


public class VelociraptorsBaby extends Dinosaurs {
	
	public Location currentLocation;
	private Actions actions = new Actions();
	
	public VelociraptorsBaby(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		foodLevel = 50;
		dinoAge = 0;
		maxFL = 300;
		actions.add(new AttackAction(this));
		actions.add(new SellAliveDinosaurs(this,2500));
		
		
		}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return actions;
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		List<Item> groundItems = map.locationOf(this).getItems();
		behaviour = new VeloFoodBehaviour(map.locationOf(this));
		foodLevel--;
		if(getFoodLevel() == 0){
			healthCheck(this,map);
			return new DoNothingAction();
			}
		
		this.dinoAge++;
		if(this.dinoAge == 20){
			Location here = map.locationOf(this);
			map.removeActor(this);
			map.addActor(new Velociraptors("Velociraptors"), here);
			return new DoNothingAction();
		}
		
		Action findfood = behaviour.getAction(this, map);
		if(groundItems.size() > 0){
			for(int i = 0; i<= groundItems.size();i++){
				if(groundItems.get(i).hasSkill(Status.CORPSE) && foodLevel <= maxFL){
					map.locationOf(this).removeItem(groundItems.get(i));
					this.foodLevel += 50;
					System.out.println(name + " ate Corpse " + this.foodLevel);
					behaviour.getAction(this, map);
					}
				
				else if (groundItems.get(i).hasSkill(Status.PROEGG) && foodLevel <= maxFL){
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
		if (findfood != null)
			return findfood;
		return new DoNothingAction();
	}
	
	
	
	
}

