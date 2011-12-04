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
			
			TicTacToePlay game = new TicTacToePlay(GameType.WithAnotherPersonInSameComputer);
			
			GamePlayComsHandling coms= new GamePlayComsHandling(game, player1, player2);
			
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
			}else{
				player2Output.println("yourTurn");
				player1Output.println("player2Turn");
			}
			
			
			while((player1Inputline=player1Input.readLine())!=null && 
					(player2Inputline=player2Input.readLine())!=null ){
				//If either player exits the game at any time, we go ahead and make sure that both players exit ina  timepley fashion then break out and close evreything up
				if(player1Inputline.equals("bye")|| player2Inputline.equals("bye")){
					player1Output.println("bye");
					player2Output.println("bye");
					break;
				}if(!(player1Inputline==null)){
					print("From player1:"+player1Inputline);
					player1Outputline=coms.process(player1Inputline, WhosTurn.player1);
				}if(!(player2Inputline==null)){
					print("From player2:"+player2Inputline);
				}
			}
			
			player1Output.close();
			player2Output.close();
			player1Input.close();
			player2Input.close();
			this.interrupt();
			
		}catch(Exception e){
			print("There was an error in the run method in the GamePlayThread class:"+e);
			e.printStackTrace();
			
		}
				
	}
	
	private void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}
	
	
	
}
