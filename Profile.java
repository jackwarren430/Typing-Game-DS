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

	public ArrayList<GameStat> getGamesPlayed(){
		return gamesPlayed;
	}

	public void add(GameStat g){
		gamesPlayed.add(g);
	}

	public void saveProfile(){
		PrintWriter writer = null;
		try {
			String fileDir = "SavedProfiles" + File.separator + playerName + ".txt";
			writer = new PrintWriter(fileDir, "UTF-8");
		} catch(IOException e) {
			e.printStackTrace();
		}

		String data = getString();

		writer.println(data);
		writer.close();
		
	}

	public String getString(){
		String toReturn = "";
		toReturn += "ProfileName: " + playerName + "\n";
		toReturn += "#Games: " + gamesPlayed.size();
		int count = 0;
		for (GameStat stat : gamesPlayed){
			toReturn += "\n%\ntag: " + count + "\n";
			toReturn += stat;
			count++;
		}
		return toReturn;
	}

	public String getName(){
		return playerName;
	}

	public void setName(String name){
		playerName = name;
	}
}