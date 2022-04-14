package game;

/**
 *this is an Item that dinosaurs become after death. it is also a source of carnivore food.
 */
import edu.monash.fit2099.engine.Action;

public class CorpseItem extends PortableDinoItem {

	public CorpseItem(String name) {
		super(name, '%');
		addSkill(Status.CORPSE);
		addSkill(Status.CARNIVOREFOOD);
	}
	
	public void addAction(Action action){
	this.allowableActions.add(action);
	}
}
