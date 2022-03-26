import java.util.*;
import java.io.*;

public class Profile {

	ArrayList<String> freqMissedWords;
	ArrayList<GameStat> gamesPlayed;
	String playerName;

	public Profile(){
		gamesPlayed = new ArrayList<GameStat>();
		playerName = "Player";
		freqMissedWords = new ArrayList<String>();
	}

	public Profile(String file){
		gamesPlayed = new ArrayList<GameStat>();
		freqMissedWords = new ArrayList<String>();
		initProfileStats(file);
	}

	public void initProfileStats(String file){
		try {
			Scanner scan = new Scanner(new File("SavedProfiles" + File.separator + file));
			playerName = scan.nextLine().substring(13);
			int numGames = Integer.valueOf(scan.nextLine().substring(8));
			String fmw = scan.nextLine().substring(17);
			String temp = "";
			for (int i = 0; i < fmw.length(); i++){
				String c = fmw.substring(i,i+1);
				if (c.equals(",")){
					freqMissedWords.add(temp);
				} else {
					temp += c;
				}
			}
			for (int i = 0; i < numGames; i++){
				scan.nextLine();
				scan.nextLine();
				int gameSize = Integer.valueOf(scan.nextLine().substring(11));
				int numErrors = Integer.valueOf(scan.nextLine().substring(14));
				System.out.println("gameSize: " + gameSize + "numErrors");
			}
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}

	public ArrayList<GameStat> getGamesPlayed(){
		return gamesPlayed;
	}

	public void add(GameStat g, ArrayList<String> missedWords){
		gamesPlayed.add(g);
		for (String s : missedWords){
			if (freqMissedWords.contains(s)){
				freqMissedWords.remove(s);
				freqMissedWords.add(0, s);
			} else {
				freqMissedWords.add(s);
			}
		}
		//if (freqMissedWords.size() > 
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
		toReturn += "#Games: " + gamesPlayed.size() + "\n";
		toReturn += "FreqMissedWords: " + freqMissedWords;
		int count = 0;
		for (GameStat stat : gamesPlayed){
			toReturn += "\n%\ntag: " + count + "\n";
			toReturn += stat;
			count++;
		}
		return toReturn;
	}

	public String getfreqMissedWords(){
		return GameStat.arrListToString(freqMissedWords);
	}

	public String getName(){
		return playerName;
	}

	public void setName(String name){
		playerName = name;
	}
}