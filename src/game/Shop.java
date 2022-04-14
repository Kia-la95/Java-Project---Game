package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


public class Shop extends Ground {

	private Actions actions = new Actions();

	public Shop() {
		super('s');
		addSkill(Status.SHOP);
		actions.add(new BuyCarnivoreAction(new CarnivoreItem("Carnivore Food")));
		actions.add(new BuyHerbivoreAction(new HerbivoreItem("Herbivore Food")));
		actions.add(new BuyProtoceratopsEggAction(new ProtoceratopsEggItem("Protoceratops egg")));
		actions.add(new BuyVelociraptorsEggAction(new VelociraptorsEggItem("VeloCiraptors egg")));
		actions.add(new BuyPlesiosaursEggAction(new PlesiosaursEggItem("Plesiosaurs Egg")));
		actions.add(new BuyTyrexEggAction(new TyrexEggItem()));
		actions.add(new BuyPteranodonsAction(new PteranodonsEggItem("Pteranodons Egg")));
	

		
	}		
	
	@Override 
	public boolean canActorEnter(Actor actor){
		return false;
	}
	
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		for(int x=0;x<actor.getInventory().size();x++){
			if(actor.getInventory().get(x).hasSkill(Status.CORPSE)){
				actions.add(new SellCorpseAction(actor.getInventory().get(x)));
			}
			else if(actor.getInventory().get(x).hasSkill(Status.PROEGG)){
				actions.add(new SellProtoceratopsEggAction(actor.getInventory().get(x)));
			}
		}
		
		return actions;
	}
	@Override 
	public void tick(Location location){
		location.setGround(new Shop());
	}
	
}
