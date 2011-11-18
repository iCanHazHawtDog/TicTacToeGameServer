package edu.server.businesslogic.comshandling;

/***
 * 
 * @author pstasiuk
 * 
 * Originally created on 11/14/2011
 * 
 * The class that processes all the communications between the server and the client applications
 *
 */
public class TicTacToeServerComsHandling {
	private final int CREDENTAILS=0;
	private final int GAMEPLAY=1;
	private final int GAMEOVER=2;
	private int state=0;
	
	
	
	
	public TicTacToeServerComsHandling(){
		
	}
	
	public String process(String message){
		String returnMessage=null;
		if(state==CREDENTAILS){
			if(message==null){
				returnMessage="go";
			}if(message.contains("name:")){
				
			}
			
			
		}if(state==GAMEPLAY){
			
			
		}if(state==GAMEOVER){
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		return returnMessage;
	}
	
	
}
