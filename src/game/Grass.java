package game;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * this is a terrain type that herbivorous eat. 
 *
 */

public class Grass extends Ground {
	
	private int age = 0;
	
	public Grass() {
		super(',');
		addSkill(Status.GRASS);
	}
	
	@Override
	public boolean canActorEnter(Actor actor){ 
	if(actor.hasSkill(Status.PLESIOSAURS)){
		return false;
	}
	else
		return true;
	}
	
	/**
	 * Grass is converted to bare dirt after 8 turn in order to avoid overcrowdness.
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		age++;
		if(age == 8){
			location.setGround(new Dirt());
		}
	}
}
