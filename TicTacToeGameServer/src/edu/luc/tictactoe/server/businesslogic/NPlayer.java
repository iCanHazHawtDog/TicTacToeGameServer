package edu.luc.tictactoe.server.businesslogic;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/***
 * 
 * 
 * @author Paul Stasiuk
 * 
 * Originally created on 12/1/2011
 * 
 * The NPlayer is a class that contains all the information about a player including its
 * Socket, the name of the player, the BufferedReader that is associated with the socket
 * and the PrintWriter associated with the socket.
 *
 */
public class NPlayer {
	private String name;
	private BufferedReader input;
	private PrintWriter output;
	private Socket socket;
	
	/**
	 * Constructor for the NPlayer class
	 * @param name
	 * @param input
	 * @param output
	 * @param socket
	 */
	public NPlayer(String name,BufferedReader input, PrintWriter output, Socket socket){
		this.name=name;
		this.input=input;
		this.output=output;
		this.socket=socket;
		
	}
	
	/**
	 * Method that returns the name of the player
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Method that returns the socket associated with the player
	 * @return
	 */
	public Socket getSocket(){
		return socket;
	}
	
	/**
	 * Method that returns the BufferedReader associated with the socket 
	 * that is associated with this player
	 * @return
	 */
	public BufferedReader getInput(){
		return input;
	}
	
	/**
	 * Method that returns the PrintWriter associated with the socket
	 * that is associated with this player
	 * @return
	 */
	public PrintWriter getOutput(){
		return output;
	}
	
	/**
	 * Method that sets the name of a the player
	 * @param name
	 */
	public void setName(String name){
		this.name=name;
	}
	
	
}
