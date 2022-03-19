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
		CPS = Math.round(CPS * 100)/100;
		
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


}