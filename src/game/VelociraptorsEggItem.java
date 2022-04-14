package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class VelociraptorsEggItem extends PortableDinoItem {

	public Location currentLocation;
	private int eggAge;
	public GameMap map;
	
	
	/**
	 *  This is the Constructor of DinosaurEgg
	 *	The DinosaurEgg must be an Item because it needs to be picked up by Player During the game  
	 *
	 * @param name
	 */
	public VelociraptorsEggItem(String name) {
		super(name, 'o');
		addSkill(Status.VELOEGG);
		addSkill(Status.EGG);
		eggAge = 0;
	}

	
	/**
	 * This Override method add 1 each turn to age of the egg and when it becomes 10, we hatch the egg and create a new baby dinosaur
	 */
	@Override
	public void tick(Location currentLocation){
		eggAge++;
		if (eggAge == 10 && !currentLocation.containsAnActor()){
			currentLocation.removeItem(this);
			currentLocation.addActor(new VelociraptorsBaby("BabyVelo",'v',100));

		}
	}
	
	
}
