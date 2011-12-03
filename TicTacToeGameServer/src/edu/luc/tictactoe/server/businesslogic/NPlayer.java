package edu.luc.tictactoe.server.businesslogic;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/***
 * 
 * 
 * @author Paul Stasiuk
 * 
 * Originally cared on
 *
 */
public class NPlayer {
	private String name;
	private BufferedReader input;
	private PrintWriter output;
	private Socket socket;
	
	
	public NPlayer(String name,BufferedReader input, PrintWriter output, Socket socket){
		this.name=name;
		this.input=input;
		this.output=output;
		this.socket=socket;
		
	}
	
	public String getName(){
		return name;
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public BufferedReader getInput(){
		return input;
	}
	
	public PrintWriter getOutput(){
		return output;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	
}
