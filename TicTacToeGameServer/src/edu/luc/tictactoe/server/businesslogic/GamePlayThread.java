package edu.luc.tictactoe.server.businesslogic;

import java.io.BufferedReader;
import java.io.PrintWriter;

import edu.luc.tictactoe.businesslogic.implementation.GameType;
import edu.luc.tictactoe.businesslogic.implementation.TicTacToePlay;
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
	public boolean player1Turn=false;
	private boolean keepPlaying=true;
	PrintWriter player1Output;
	PrintWriter player2Output;
	
	/**
	 * Constructor for the GamePlayThread class.
	 * 
	 * @param players
	 */
	public GamePlayThread(NPlayer[] players){
		player1=players[0];
		player2=players[1];
	}
	
	@Override
	public void run(){
		try{
			player1Output= player1.getOutput();
			player2Output= player2.getOutput();
			BufferedReader player1Input= player1.getInput();
			BufferedReader player2Input= player2.getInput();
			String player1Inputline=null;
			String player2Inputline=null;
			String player1Outputline=null;
			String player2Outputline=null;
			
			TicTacToePlay game = new TicTacToePlay(GameType.WithAnotherPersonInSameComputer);
			
			GamePlayComsHandling coms= new GamePlayComsHandling(game, player1, player2, this);
			
			//Set the two players names locally
			game.setPlayerOne(player1.getName());
			game.setPlayerTwo(player2.getName());
			
			//Send the names off to both machines...
			player1Output.println("player2Name:"+player2.getName());
			player2Output.println("player1Name:"+player1.getName());
			
			//Pick who comes first and send it off to the two machines
			if(game.whoseTurn().getName().equals(player1.getName())){
				player1Output.println("yourTurn");
				player2Output.println("player1Turn");
				player1Turn=true;
			}else{
				player2Output.println("yourTurn");
				player1Output.println("player2Turn");
				player1Turn=false;
			}
			
			while(keepPlaying){
				if(player1Turn){
					while((player1Inputline=player1Input.readLine())!=null && player1Turn){
						if(player1Inputline.equals("bye")){
							player1Output.println("bye");
							player2Output.println("bye");
							keepPlaying=false;
							break;
						}
						player1Turn=false;
						print("From player1:"+player1Inputline);
						player1Outputline=coms.process(player1Inputline, WhosTurn.player1);
						break;
					}
				}else{
					while((player2Inputline=player2Input.readLine())!=null && !player1Turn){
						if(player2Inputline.equals("bye")){
							player1Output.println("bye");
							player2Output.println("bye");
							keepPlaying=false;
							break;
						}
						player1Turn=true;
						print("From player2:"+player2Inputline);
						player1Outputline=coms.process(player2Inputline, WhosTurn.player2);
						break;
					}
				}
			}
			
			
			player1Output.close();
			player2Output.close();
			player1Input.close();
			player2Input.close();
			this.interrupt();
			
		}catch(Exception e){
			player1Output.println("bye");
			player2Output.println("bye");
			print("There was an error in the run method in the GamePlayThread class:"+e);
			e.printStackTrace();
		}
				
	}
	
	/**
	 * Method that prints to the console.
	 * 
	 * @param message
	 */
	private void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}
	
	
	
}
