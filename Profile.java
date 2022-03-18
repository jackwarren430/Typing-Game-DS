import java.util.*;
import java.io.*;

public class Profile {
	ArrayList<GameStat> gamesPlayed;
	String playerName;

	public Profile(){
		gamesPlayed = new ArrayList<GameStat>();
		playerName = "Player";
	}

	public Profile(String file){
		
	}

	public void add(GameStat g){
		gamesPlayed.add(g);
	}
}