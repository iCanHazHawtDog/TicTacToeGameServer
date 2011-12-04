package edu.luc.tictactoe.server.threadhandling;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import edu.luc.tictactoe.server.businesslogic.NPlayer;
import edu.luc.tictactoe.server.businesslogic.PlayerQueue;
import edu.server.businesslogic.comshandling.TicTacToeServerComsHandling;

/***
 * 
 * @author Paul Stasiuk
 * 
 * Originally created on 11/14/2011
 * 
 * This is where all of the matching and paring occur.
 *
 */
public class TicTacToeServerThreadHandling extends Thread {
	private PrintWriter output;
	private BufferedReader input;
	private Socket socket;
	private boolean verbose=true;
	
	public TicTacToeServerThreadHandling(Socket socket){
		this.socket=socket;
		
		
	}
	
	@Override
	public void run(){
		try{
			output = new PrintWriter(socket.getOutputStream(), true);
		    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    String inputline=null;
		    String outputline=null;
		    
		    NPlayer player = new NPlayer(null,input,output,socket);
		    TicTacToeServerComsHandling coms= new TicTacToeServerComsHandling(player);
		    output.println((outputline=coms.process(inputline)));
		    print(inputline);
		    
		    print("Player connected! Adding to player Queue!");
		    
		    
		    while((inputline=input.readLine())!=null){
		    	if(inputline.equals("waitingToPlay")){
		    		print("Player is waiting to play. Interrupting this thread and breaking out..");
		    		this.interrupt();
		    		break;
		    	}
		    	
		    	print("From Cleint: "+ inputline);
		    	output.println((outputline=coms.process(inputline)));
		    	print("From Server: "+ outputline);
		    }
		    
		    PlayerQueue.addPlayer(player);
		     
		}catch(IOException e){
			e.printStackTrace();
			this.interrupt();
		}
		
	}
	
	private void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}
	
	
	
	
}
