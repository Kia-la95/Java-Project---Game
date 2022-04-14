package game;


import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class PlesiosaursEggItem extends PortableDinoItem {

	public Location currentLocation;
	private int eggAge;
	public GameMap map;
	
	
	/**
	 *  This is the Constructor of DinosaurEgg
	 *	The DinosaurEgg must be an Item because it needs to be picked up by Player During the game  
	 *
	 */
	public PlesiosaursEggItem(String name) {
		super(name, 'o');
		this.addSkill(Status.PLEEGG);
		eggAge = 0;
	
	}

	
	/**
	 * This Override method add 1 each turn to age of the egg and when it becomes 10, we hatch the egg and create a new baby dinosaur
	 * This one is a bit different, because we need to check when the egg wants to hatch, it must be near the water or the lake.
	 */
	@Override
	public void tick(Location currentLocation){
		eggAge++;
		if (eggAge == 10){
			if(currentLocation.getGround().getClass() == Water.class){
	            	currentLocation.removeItem(this);
	    			currentLocation.addActor(new PlesiosaursBaby("BabyPlesiosaurs",'b',100));

	            }
			else if(currentLocation.getGround().getClass() != Water.class){
				currentLocation.removeItem(this);
				for (Exit exit : currentLocation.getExits()){
					Location destination = exit.getDestination();
						if(destination.getGround().getClass() == Water.class){
							destination.addActor(new PlesiosaursBaby("BabyPlesiosaurs",'b',100));
							break;
				}
		      }	
		   }

		}
	 }
}
