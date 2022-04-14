package game;


import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * 
 * a terrain which makes fish
 *
 */
public class Reeds extends Ground {
	
	private Random random = new Random(); 
	private int age;

	public Reeds() {
		super('^');
		addSkill(Status.REEDS);
			}
	/**
	 * does not allow land-based dinosaurs to enter on this ground
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	/**
	 * reeds with having 2 reeds neighbour are eliminated due to over crowdness
	 */
	public void die(Location location){
		
			if(aliveNeighboursCount(location) >= 2 && location.getGround().hasSkill(Status.REEDS)){
				location.setGround(new Water());
		}
	}
	public void fish(Location location){
		int number = random.nextInt(20);
		if(number == 1){
			location.setGround(new Water());
			location.addItem(new Fish());		
		}
		
	}

	/**
	 * 
	 * @param location is the location of the reeds.
	 * @return returns the number of neighbers which are reeds. 
	 */
	
	private int aliveNeighboursCount(Location location) {
		return (int) location.getExits().stream().map(exit -> exit.getDestination().getGround())
				.filter(ground -> ground.hasSkill(Status.REEDS)).count();
	}
	
	@Override
	public void tick(Location location) {
		super.tick(location);	
		fish(location);
		age++;
		if(age == 10){
			location.setGround(new Water());
		}
		die(location);

	}
}
