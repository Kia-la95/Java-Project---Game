package game;

import java.util.Scanner;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class EndGameAction extends Action {

	private Scanner input = new Scanner(System.in);
	@Override
	public String execute(Actor actor, GameMap map) {
		System.out.println("would you like to exit the game? type 1 as No, 2 as Yes");
		int a = input.nextInt();

		if(a == 1){
			return actor + " resumes the game \n";
		}
		else if ( a == 2){
			
			map.removeActor(actor);
			return menuDescription(actor);
			
			
		}
		else{
			return "Invalid charancter is chosen.\n";
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Exit The Game\n";
	}
	 @Override 
	 public String hotkey(){
		return "0";
		 
	 }

}
