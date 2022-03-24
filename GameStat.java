import java.util.*;

public class GameStat {

	private int finalTime;
	private ArrayList<String> missedWords;
	private int numMissedWords;
	private int totalCharCount;
	private int gameSize;

	private int WPM;
	private float CPS;

	public GameStat(ArrayList<String> missedWords, int finalTime, int totalCharCount, int gameSize){
		this.finalTime = finalTime;
		this.missedWords = missedWords;
		this.numMissedWords = missedWords.size();
		this.totalCharCount = totalCharCount;
		this.gameSize = gameSize;
		calcSpeeds();
	}

	private void calcSpeeds(){
		WPM = 60 * gameSize / finalTime;
		CPS = (float)totalCharCount / (float)finalTime;
		WPM = Math.round(WPM);
		CPS = Math.round(CPS * 100.00f)/100.00f;
		
	}

	public String toString(){
		String toReturn = "";
		toReturn += "Total Errors: " + numMissedWords + "\n";
		toReturn += "Missed Words: ";
		for (String word : missedWords){
			toReturn += word + ", ";
		}
		toReturn += "\nFinal Time: " + finalTime + " seconds\n";
		toReturn += "Words Per Minute (WPM): " + WPM + " w/m\n";
		toReturn += "Characters Per Second (CPS): " + CPS + " c/s\n";
		return toReturn;
	}

	public int getTime(){
		return finalTime;
	}

	public int getNumMissedWords(){
		return numMissedWords;
	}

	public ArrayList<String> getMissedWords(){
		return missedWords;
	}

	public int getWPM(){
		return WPM;
	}

	public float getCPS(){
		return CPS;
	}


}