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
 * The server listens for incoming connections, then spawns a new thread and hnads off the socket connection
 * to the new thread.
 * 
 * Created 11/24/2011
 *
 */

public class TicTacToeServer extends Thread{
	final int PORT=6223;
	private boolean verbose=true;
	
	
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
