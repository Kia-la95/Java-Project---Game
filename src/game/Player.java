package game;



import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;


/**
 * Class representing the Player.
 */
public class Player extends Actor {

	
	private Menu menu = new Menu();
	private int budget;


	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		
		actions.add(new EndGameAction());
		
		System.out.println("Budget: " + budget + "\n");
				
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		
		return menu.showMenu(this, actions, display);
	}
	
	

	/**
	 * checks if player has money in its budget to buy somthing.
	 */
	public boolean hasMoney(int amount){
		if(budget >= amount){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * adds money to player's budget
	 */
	public void addMoney(int amount){
		budget += amount;
	}
	/**
	 * subtracts money from budget.
	 */
	public void subtractMoney(int amount){
		budget -= amount;
	}

}
	



