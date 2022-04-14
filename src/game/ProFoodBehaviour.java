package game;



import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;



public class ProFoodBehaviour implements Behaviour{

	protected Location location;
	public ProFoodBehaviour(Location location) {
		this.location=location;
	}
	
	/**
	 * This class is basically check for the food in a specific range for each Dinosaur. This part get location of Dinosaur at a time
	 * and save the location and search for the food in that range and help Dinosaur to find foods
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		
		Location here = map.locationOf(actor);
		int x = here.x();
		int y = here.y();
		
		
		for (int xa = x-1; xa <= x+1 ; xa++ ) {
			for (int ya = y-1; ya <= y+1; ya++) {
				if(xa<=map.getXRange().max() && ya<=map.getYRange().max() &&xa>=map.getXRange().min() &&ya>=map.getYRange().min()) 
				{
				Ground ground=map.at(xa,ya).getGround();
				if(ground.getClass()==Tree.class || ground.getClass()==Grass.class){
					Location destination = map.at(xa, ya);
					if (destination.canActorEnter(actor)) 
						return new MoveActorAction(destination,"towards food");
					
					
						}
					}
				
				}
			}

		return new WanderBehaviour().getAction(actor, map);

			}
	}