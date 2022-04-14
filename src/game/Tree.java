package game;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Tree extends Ground {
 
	private int age = 0;
	private Random random = new Random();
	
	public Tree() {
		super('+');
		this.addSkill(Status.YOUNG_TREE);
	}

	
	/**
	 * this if 1/120 is the probablilty of growing tree  in the map
	 * this method makes tree next to another tree.
	 * @param location location of the ground
	 */
	public void grow(Location location){
		int x = location.x();
		int y = location.y();
		int aNumber = random.nextInt(50);
		if(location.x() < 79 && location.y() < 23){
			if(aNumber == 1){
				if(location.map().at(x, y+1).getGround().hasSkill(Status.DIRT)){
					location.map().at(x, y+1).setGround(new Tree());
				}
			}
			else if(aNumber == 2){
				if(location.map().at(x+1, y+1).getGround().hasSkill(Status.DIRT)){
					location.map().at(x+1, y+1).setGround(new Tree());
				}
			}
			else if(aNumber == 3){
				if(location.map().at(x+1, y+1).getGround().hasSkill(Status.DIRT)){
					location.map().at(x+1, y+1).setGround(new Tree());
				}
			}
			else if(aNumber == 4){
				if(location.map().at(x+1, Math.abs(y-1)).getGround().hasSkill(Status.DIRT)){
					location.map().at(x+1, Math.abs(y-1)).setGround(new Tree());
				}
			}
			else if(aNumber == 5){
				if(location.map().at(x, Math.abs(y-1)).getGround().hasSkill(Status.DIRT)){
					location.map().at(x, Math.abs(y-1)).setGround(new Tree());
				}
			}
			else if(aNumber == 6){
				if(location.map().at(Math.abs(x-1), Math.abs(y-1)).getGround().hasSkill(Status.DIRT)){
					location.map().at(Math.abs(x-1), Math.abs(y-1)).setGround(new Tree());
				}
			}
			else if(aNumber == 7){
				if(location.map().at(Math.abs(x-1), y).getGround().hasSkill(Status.DIRT)){
					location.map().at(Math.abs(x-1), y).setGround(new Tree());
				}
			}
			else if(aNumber == 8){
				if(location.map().at(Math.abs(x-1), y+1).getGround().hasSkill(Status.DIRT)){
					location.map().at(Math.abs(x-1), y+1).setGround(new Tree());
				}
			}
		}
	}
	
	public void die(Location location){
		if(aliveNeighboursCount(location) == 2){
			location.setGround(new Dirt());
		}
	}
	private int aliveNeighboursCount(Location location) {
		return (int) location.getExits().stream().map(exit -> exit.getDestination().getGround())
				.filter(ground -> ground.hasSkill(Status.OLD_TREE)).count();
	}
	
	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10){
			displayChar = 't';
			this.addSkill(Status.OLD_TREE);
		}
		if (age == 20){
			displayChar = 'T';
			this.addSkill(Status.OLD_TREE);
		}	
		if ( age == 30){
			location.setGround(new Dirt());
		}
		grow(location);
		die(location);
	}
	
	@Override
	public boolean canActorEnter(Actor actor){ 
	if(actor.hasSkill(Status.PLESIOSAURS)){
		return false;
	}
	else
		return true;
	}
}
