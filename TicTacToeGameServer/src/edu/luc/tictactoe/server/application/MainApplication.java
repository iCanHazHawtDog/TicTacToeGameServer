package edu.luc.tictactoe.server.application;

import edu.luc.tictactoe.server.businesslogic.PairMachine;
import edu.luc.tictactoe.server.tictactoeserver.TicTacToeServer;


/***
 * 
 * @author Paul Stasiuk
 * Date Created: 11/14/2011
 * 
 * The MainApplication class spawns all of the subsequent threads. It is also what actually when the application is
 * called.
 * 
 */


public class MainApplication {
	private static Thread serverStarter;
	private static Thread mainThread;
	private static boolean verbose=true;

	public static void main(String[] args){
		print("Starting the server!");
		TicTacToeServer server= new TicTacToeServer();
		new Thread(server).start();
		print("Server started!");
		print("Starting the PairMachine!");
		PairMachine pairMachine= new PairMachine();
		print("Running the PairMachine!");
		pairMachine.runMachine();
		print("PairMachine running!");
	}
	
	
	
	/***
	 * Adds a shutdown hook to the application.
	 */
	protected static void addDaemonShutdownHook(){
	   Runtime.getRuntime().addShutdownHook( new Thread() { public void run() { MainApplication.shutdown(); }});
	}
	
	/***
	 * Shuts down the server application.
	 */
	public static void shutdown(){
		try {
			getMainDaemonThread().join();
			serverStarter.join();
			serverStarter.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/***
	 * Returns the mainThread- which the thread that is spawned for this instance of the server.
	 * @return
	 */
	static private Thread getMainDaemonThread(){
		return mainThread;
	}
	
	/**
	 * Closes System.out so that it does not bind to a Unix shell instance. If System.out is not closed, then once the shell window
	 * is closed the instance of the server will also close. 
	 * 
	 */
	static public void daemonize(){
	   mainThread= Thread.currentThread();
	   System.out.close();
	   System.err.close();
	}
	
	private static void print(String message){
		if(verbose){
			System.out.println(message);
		}
	}
	

}
