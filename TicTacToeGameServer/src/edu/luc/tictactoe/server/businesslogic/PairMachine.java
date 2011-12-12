package edu.luc.tictactoe.server.businesslogic;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * 
 * @author Paul Stasiuk
 * 
 * The class that pairs players and starts a new game based on the players that have been paired
 * Leverage java TimerTask to continuously check for a number of players in the Queue.
 * 
 * Created on 12/1/2011
 */

public class PairMachine {
	private boolean verbose=false;
	
	public void runMachine(){
		int delay = 2000;   // delay for 5 sec.
		int period = 1000;  // repeat every sec.
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				if(PlayerQueue.playersWaiting()>=2){
					print("More than two players, starting a game..");
					GamePlayThread gamePlayThread= new GamePlayThread(PlayerQueue.removeTwo());
					new Thread(gamePlayThread).start();
					
				}else{
					print("Less than two players, no need to start a game...");
				}
			}
		}, delay, period);
		
	}
	
	/**
	 * Method that prints to the console
	 * @param message
	 */
	private void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}
	

}
