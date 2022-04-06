import java.util.*;
import java.io.*;

public class Profile {

	ArrayList<String> freqMissedWords;
	ArrayList<GameStat> gamesPlayed;
	String playerName;
	int avgWPM;

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
			avgWPM = Integer.valueOf(scan.nextLine().substring(8));
			String fmw = scan.nextLine().substring(16);
			String temp = "";
			for (int i = 0; i < fmw.length(); i++){
				String c = fmw.substring(i,i+1);
				if (c.equals(",") || c.equals("\n")){
					freqMissedWords.add(temp.substring(0,temp.length()));
					temp = "";
				} else {
					temp += c;
				}
			}
			for (int i = 0; i < numGames; i++){
				scan.nextLine(); // %
				scan.nextLine(); // tag
				int gameSize = Integer.valueOf(scan.nextLine().substring(11));
				int numErrors = Integer.valueOf(scan.nextLine().substring(14));
				ArrayList<String> missedWords = new ArrayList<String>();
				String mw = scan.nextLine().substring(13);
				temp = "";
				for (int j = 0; j < mw.length(); j++){
					String c = mw.substring(j,j+1);
					if (c.equals(",") || c.equals("\n")){
						missedWords.add(temp.substring(0,temp.length()));
						temp = "";
					} else {
						temp += c;
					}
				}
				int finalTime = Integer.valueOf(scan.nextLine().substring(15));
				int WPM = Integer.valueOf(scan.nextLine().substring(24));
				float CPS = Float.valueOf(scan.nextLine().substring(29));
				GameStat toAdd = new GameStat(missedWords, finalTime, 0, gameSize);
				toAdd.setCPS(CPS);
				gamesPlayed.add(toAdd);

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
		while (freqMissedWords.size() > 36){
			freqMissedWords.remove(freqMissedWords.size()-1);
		}
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
		toReturn += "AvgWPM: " + calcAvgWPM() + "\n";
		toReturn += "FreqMissedWords: " + GameStat.arrListToString(freqMissedWords);
		int count = 0;
		for (GameStat stat : gamesPlayed){
			toReturn += "\n%\ntag: " + count + "\n";
			toReturn += stat;
			count++;
		}
		return toReturn;
	}

	public String getFreqMissedWords(){
		return GameStat.arrListToString(freqMissedWords);
	}

	public String getName(){
		return playerName;
	}

	public int getAvgWPM(){
		return calcAvgWPM();
	}

	public int calcAvgWPM(){
		int collect = 0;
		for (GameStat s : gamesPlayed){
			collect += s.getWPM();
		}
		return collect / gamesPlayed.size();
	}

	public void setName(String name){
		playerName = name;
	}
}