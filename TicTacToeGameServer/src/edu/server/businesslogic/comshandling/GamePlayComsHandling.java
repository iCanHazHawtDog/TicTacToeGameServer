package edu.server.businesslogic.comshandling;

import edu.luc.tictactoe.server.businesslogic.NPlayer;

/**
 * @author Paul Stasiuk
 * 
 * Class that handles all of the GamePlay communications..
 * 
 * Created on 12/1/2011
 */

public class GamePlayComsHandling {
	
	private final int GAMEPLAY=2;
	private final int GAMEOVER=3;
	private NPlayer player;
	private int state=2;
	private boolean verbose=true;
	
	
	public String process(String message){
		String returnMessage = null;
		if(state==GAMEPLAY){
			
			
		}if(state==GAMEOVER){
			
			
		}
	
		return returnMessage;
	}
	
	private void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}

}
