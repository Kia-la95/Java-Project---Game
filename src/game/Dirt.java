package game;


import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	
	private Random random = new Random();
	
	public Dirt() {
		super('.');
		addSkill(Status.DIRT);
		
	}
	/**
	 * this method has a chance of 1/35 to to grow a grass on a bare dirt.
	 * @param location is the location of this ground. 
	 */
	public void grow(Location location){
		int aNumber = random.nextInt(35);
		if(aNumber == 1){
			location.setGround(new Grass());
		}
		
	}

	/**
	 * does not allow plesiosaurs to enter on the dirt land base. 
	 */
	@Override
	public boolean canActorEnter(Actor actor){ 
	if(actor.hasSkill(Status.PLESIOSAURS)){
		return false;
	}
	else
		return true;
	}
	
	/**
	 * allow this ground to feel the joy ogf time.
	 */
	@Override
	public void tick(Location location){
		super.tick(location);
		grow(location);
		
	}
	
}
