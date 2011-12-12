package edu.luc.tictactoe.server.businesslogic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 
 * @author Paul Stasiuk
 *
 *Created on 12/1/2011
 *
 *The PlayerQueue creates a modified Queue of NPlayers.
 *
 */
public class PlayerQueue {
	boolean isEmpty;
	//Create a queue of NPlayers..
	public static Queue<NPlayer> playerQueue = new LinkedList<NPlayer>();
	
	/**
	 * Adds a player to the Queue.
	 * 
	 * @param player
	 */
	public static void addPlayer(NPlayer player){
		playerQueue.add(player);
	}
	
	/**
	 * Removes the "top" two players from the Queue.
	 * 
	 * @return
	 */
	public static NPlayer[] removeTwo(){
		NPlayer[] playerArray= new NPlayer[2];
		
		if(playerQueue.size()>=2){
			playerArray[0]=playerQueue.remove();
			playerArray[1]=playerQueue.remove();
			return playerArray;
		}else{
			return null;
		}
	
	}
	
	/**
	 * Emptys the Queue
	 * 
	 */
	public static void emptyQueue(){
		playerQueue.clear();
	}
	
	/**
	 * Returns how many players are waiting in the Queue.
	 * 
	 * @return
	 */
	public static int playersWaiting(){
		return playerQueue.size();
	}
	
	
	
	

}
