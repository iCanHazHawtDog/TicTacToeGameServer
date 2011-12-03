package edu.luc.tictactoe.server.businesslogic;

import java.io.BufferedReader;
import java.io.PrintWriter;

import edu.server.businesslogic.comshandling.GamePlayComsHandling;

/**
 * @author Paul Stasiuk
 * 
 * Class that starts the gameplay and takes care of all the moves.
 * 
 * Created on 12/1/2011
 *
 */

public class GamePlayThread extends Thread{
	private NPlayer player1;
	private NPlayer player2;
	private boolean verbose=true;
	
	public GamePlayThread(NPlayer[] players){
		player1=players[0];
		player2=players[1];
	}
	
	@Override
	public void run(){
		try{
			PrintWriter player1Output= player1.getOutput();
			PrintWriter player2Output= player2.getOutput();
			BufferedReader player1Input= player1.getInput();
			BufferedReader player2Input= player2.getInput();
			String player1Inputline=null;
			String player2Inputline=null;
			String player1Outputline=null;
			String player2Outputline=null;
			
			GamePlayComsHandling comsHandling= new GamePlayComsHandling();
			
			//First we send the names off to both machines...
			player1Output.println("player2Name:"+player2.getName());
			player2Output.println("player1Name:"+player1.getName());
			//Next we pick who comes first. I am saying that it is player1's turn first just to check
			player1Output.println("yourTurn");
			
			while((player1Inputline=player1Input.readLine())!=null && 
					(player2Inputline=player2Input.readLine())!=null ){
				if(!(player1Inputline==null)){
					print(player1Inputline);
				}if(!(player2Inputline==null)){
					print(player2Inputline);
				}
				
			}
			
		
			
			
			
			
			
			
			
			
		}catch(Exception e){
			print("There was an error in the run method in the GamePlayThread class.");
			
		}
				
	}
	
	private void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}
	
	
	
}
