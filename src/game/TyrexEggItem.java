package game;

import edu.monash.fit2099.engine.Location;

public class TyrexEggItem extends PortableDinoItem {

	private int age = 0;
	
	public TyrexEggItem() {
		super("T-rex egg", 'o');
		
	}
	

	/**
	 * This method counts the egg age
	 */
	@Override 
	public void tick(Location location){
		age++;
		if(age == 10){
			location.addActor(new Tyrex());
			location.removeItem(this);
		}
		
	}
	
	
}
