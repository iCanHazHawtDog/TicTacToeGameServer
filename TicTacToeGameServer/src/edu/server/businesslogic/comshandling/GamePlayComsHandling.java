package edu.server.businesslogic.comshandling;

import edu.luc.tictactoe.businesslogic.IPlayer;
import edu.luc.tictactoe.businesslogic.implementation.SelectionResult;
import edu.luc.tictactoe.businesslogic.implementation.TicTacToePlay;
import edu.luc.tictactoe.server.businesslogic.GamePlayThread;
import edu.luc.tictactoe.server.businesslogic.NPlayer;
import edu.luc.tictactoe.server.businesslogic.PositionInterpretor;
import edu.luc.tictactoe.server.businesslogic.WhosTurn;

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
	private final int WAITFORPLAYER=4;
	private int state=2;
	private boolean verbose=true;
	private TicTacToePlay game;
	private NPlayer player1;
	private NPlayer player2;
	private boolean player1Coms=false;
	private boolean player2Coms=false;
	private IPlayer previousPlayer;
	private GamePlayThread gameThread;
	
	public GamePlayComsHandling(TicTacToePlay game, NPlayer player1, NPlayer player2,GamePlayThread gameThread){
		this.game=game;
		this.player1= player1;
		this.player2= player2;
		this.gameThread=gameThread;
		
	}
	
	public String process(String message, WhosTurn whosTurn){
		String returnMessage = null;
		
		if(state==GAMEPLAY){
			if(message.contains("setPos:")){
				int position=Integer.valueOf(message.substring(7,message.length()));
				
				previousPlayer=game.whoseTurn();
				print("It is "+ previousPlayer.getName()+" turn to set the position.");
				
				PositionInterpretor pos= new PositionInterpretor(position);
				if(whosTurn==WhosTurn.player1){
					print(player1.getName()+ "is attempting to set the position:"+position);
					SelectionResult result= game.selectPosition(pos.x, pos.y);
					
					if(result==SelectionResult.Continue){
						player1.getOutput().println("posSet");
						player2.getOutput().println("setPos:"+position);
	
					}if(result==SelectionResult.Draw){
						print("There was a draw!");
						player1.getOutput().println("draw");
						player2.getOutput().println("draw");
						player1.getOutput().println("replay");
						if(!(gameThread.player1Turn)){
							gameThread.player1Turn=true;
						}
						game.resetBoard();
						state=GAMEOVER;
						
					}if(result==SelectionResult.Win){
						print("There was a win!");
						if(player1.getName().equals(previousPlayer.getName())){
							print("Player 1 Won!");
							player1.getOutput().println("player1win");
							player2.getOutput().println("player1win");
							player1.getOutput().println("replay");
							if(!(gameThread.player1Turn)){
								gameThread.player1Turn=true;
							}
						}if(player2.getName().equals(previousPlayer.getName())){
							print("Player 2 Won!");
							player1.getOutput().println("player2win");
							player2.getOutput().println("player2win");
							player2 .getOutput().println("replay");
							if(gameThread.player1Turn){
								gameThread.player1Turn=false;
							}
						}
						state=GAMEOVER;
						game.resetBoard();
					}
					
				}if(whosTurn==WhosTurn.player2){
					print(player2.getName()+ "is attempting to set the position:"+position);
					
					SelectionResult result= game.selectPosition(pos.x, pos.y);
					
					if(result==SelectionResult.Continue){
						player1.getOutput().println("setPos:"+position);
						player2.getOutput().println("posSet");
	
					}if(result==SelectionResult.Draw){
						print("There was a draw!");
						player1.getOutput().println("draw");
						player2.getOutput().println("draw");
						player1.getOutput().println("replay");
						if(!(gameThread.player1Turn)){
							gameThread.player1Turn=true;
						}
						game.resetBoard();
						state=GAMEOVER;
						
					}if(result==SelectionResult.Win){
						print("There was a win!");
						if(player1.getName().equals(previousPlayer.getName())){
							print("Player 1 Won!");
							player1.getOutput().println("player1win");
							player2.getOutput().println("player1win");
							player1.getOutput().println("replay");
							if(!(gameThread.player1Turn)){
								gameThread.player1Turn=true;
							}
						}if(player2.getName().equals(previousPlayer.getName())){
							print("Player 2 Won!");
							player1.getOutput().println("player2win");
							player2.getOutput().println("player2win");
							player2.getOutput().println("replay");
							if(gameThread.player1Turn){
								gameThread.player1Turn=false;
							}
						}
						state=GAMEOVER;
						game.resetBoard();
					}
					
					
				}
				
			}
			
			
		}if(state==GAMEOVER){
			if(message.equals("replay")){
				if(whosTurn==WhosTurn.player1){
					print("Starting a new game..");
					game.randomStart();
					if(player1.getName().equals(game.whoseTurn().getName())){
						player1.getOutput().println("yourTurn");
						player2.getOutput().println("player1Turn");
						gameThread.player1Turn=true;
					}else{
						player1.getOutput().println("player2Turn");
						player2.getOutput().println("yourTurn");
						gameThread.player1Turn=false;
						
					}	
					state=GAMEPLAY;
				}if(whosTurn==WhosTurn.player2){
					print("Starting a new game..");
					game.randomStart();
					if(player1.getName().equals(game.whoseTurn().getName())){
						player1.getOutput().println("yourTurn");
						player2.getOutput().println("player1Turn");
						gameThread.player1Turn=true;
					}else{
						player1.getOutput().println("player2Turn");
						player2.getOutput().println("yourTurn");
						gameThread.player1Turn=false;
					}	
					state=GAMEPLAY;		
				}
				
			}
			
		}if(state==WAITFORPLAYER){
			
		}
	
		return returnMessage;
	}
	
	private void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}

}
