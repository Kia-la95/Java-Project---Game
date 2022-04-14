package game;

import edu.monash.fit2099.engine.Location;
/**
 * 
 * this item is made to feed plesiosaurs
 *
 */
public class Fish extends PortableDinoItem {

	private int age = 0;
	
	public Fish() {
		super("fish", '¤');
		addSkill(Status.FISH);
		
	}

	/**
	 * fish die after 10 turns.
	 */
	@Override
	public void tick(Location location) {
		
		age++;
		if(age==10){
			location.removeItem(this);
		}
	}
}
