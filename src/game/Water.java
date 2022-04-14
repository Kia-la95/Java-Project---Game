package game;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import java.util.Random;

public class Water extends Ground {

	private Random random = new Random();


	
	public Water() {
		super('~');
		addSkill(Status.WATER);
	}
	
	/**
	 * does not allow land-Based anumal to enter in water 
	 */
	@Override
	public boolean canActorEnter(Actor actor){ 
	if(actor.hasSkill(Status.PLESIOSAURS) || actor.hasSkill(Status.PTERANODONS)){
		return true;
	}
	else
		return false;
	}
	
	/**
	 * water with land-based terrein neighbour will have 10% chance of growing reeds
	 * water next to water will have 5% chance of growing reeds
	 */
	
	public void grow(Location location){
	
		int number = random.nextInt(100);
		for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if(destination.getGround().hasSkill(Status.DIRT) && number <= 10){
            	location.setGround(new Reeds());
            }
            else if(destination.getGround().hasSkill(Status.WATER) && number >= 95){
            	location.setGround(new Reeds());
            }
		}
	}
	


	@Override
	public void tick(Location location) {
		super.tick(location);	
		grow(location);
	}
}
