package edu.server.businesslogic.comshandling;

import java.io.PrintWriter;

import edu.luc.tictactoe.server.businesslogic.NPlayer;

/***
 * 
 * @author pstasiuk
 * 
 * Originally created on 11/14/2011
 * 
 * The class that processes all the communications between the server and the client applications
 *
 */
public class TicTacToeServerComsHandling {
	private final int WAITING=0;
	private final int CREDENTAILS=1;
	private final int GAMEPLAY=2;
	private final int GAMEOVER=3;
	private NPlayer player;
	private int state=0;
	private boolean verbose=true;
	
	
	
	
	public TicTacToeServerComsHandling(NPlayer player){
		this.player=player;
	}
	
	public String process(String message){
		String returnMessage = null;
		if(state==WAITING){
			returnMessage="go";
			state=CREDENTAILS;
		}		
		if(state==CREDENTAILS){
			if(message==null){
				return returnMessage;
			}
			if(message.contains("name:")){
				print("Setting the players name: "+message.substring(5,message.length()));
				player.setName(message.substring(5,message.length()));
				returnMessage="nameSet";
			}
	
		}if(state==GAMEPLAY){
			
			
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
