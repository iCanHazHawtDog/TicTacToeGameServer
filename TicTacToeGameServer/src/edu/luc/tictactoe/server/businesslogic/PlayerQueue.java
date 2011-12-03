package edu.luc.tictactoe.server.businesslogic;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerQueue {
	boolean isEmpty;
	//Create a queue of NPlayers..
	public static Queue<NPlayer> playerQueue = new LinkedList<NPlayer>();
	
	
	public static void addPlayer(NPlayer player){
		playerQueue.add(player);
	}
	
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
	
	public static void emptyQueue(){
		playerQueue.clear();
	}
	
	public static int playersWaiting(){
		return playerQueue.size();
	}
	
	
	
	

}
