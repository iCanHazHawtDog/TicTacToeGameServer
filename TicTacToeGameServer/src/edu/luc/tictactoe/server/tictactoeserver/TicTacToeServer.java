package edu.luc.tictactoe.server.tictactoeserver;

import java.io.IOException;
import java.net.ServerSocket;

import edu.luc.tictactoe.server.threadhandling.TicTacToeServerThreadHandling;

/***
 * 
 * @author Paul Stasiuk
 * 
 * The main server for TicTacToe. This server is spawned by the MainApplication. 
 * 
 * The server listens for an 
 *
 */

public class TicTacToeServer extends Thread{
	final int PORT=6212;
	
	
	@Override
	public void run(){
		try{
			//Create a new ServerSocket that listens on a specified port
			ServerSocket serverSocket= new ServerSocket(PORT);
			//Constantly accept new connections/attempts to connect to the server
			while(true){
				new TicTacToeServerThreadHandling(serverSocket.accept()).start();
			}
			
		}catch(IOException e){
			e.printStackTrace();
			this.interrupt();
		}
		
	}
	
	
	
}
