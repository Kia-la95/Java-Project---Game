package game;

import edu.monash.fit2099.engine.Location;

public class PteranodonsEggItem extends PortableDinoItem {

	private int age = 0;
	
	/**
	 * this is just teh constructor of egg
	 */
	public PteranodonsEggItem(String name) {
		super(name, 'o');
		addSkill(Status.EGG);
		addSkill(Status.CARNIVOREFOOD);
		
	}
	
	
	/**
	 * This Override method counts the turn for 10 times and let the egg hatch and becomes a baby
	 */
	@Override 
	public void tick(Location location){
		age++;
		if(age==10 && !location.containsAnActor()){
			location.addActor(new PteranodonsBaby("Baby Pter", 'g' ,100));
			location.removeItem(this);
		}
	}

}
