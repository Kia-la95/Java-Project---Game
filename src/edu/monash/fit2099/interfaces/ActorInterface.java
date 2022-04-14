package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */



public interface ActorInterface {
	
	
	public default void subtractMoney(int amount){
		return;
	}
	public default void addMoney(int amount){
		return;
	}
	public default boolean hasMoney(int amount){
		return true;
	}

}
