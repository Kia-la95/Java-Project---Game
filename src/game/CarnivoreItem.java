package game;

/**
 * 
 * an Item that is fed to carnivorous. it gives different foodValue to different carnivorous.  
 *
 */
public class CarnivoreItem extends PortableDinoItem {

	public CarnivoreItem(String name) {
		super(name, '&');
		addSkill(Status.CARNIVORE);
		addSkill(Status.CARNIVOREFOOD);
		
	}

}
