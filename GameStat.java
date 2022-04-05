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
		toReturn += "Game Size: " + gameSize + "\n";
		toReturn += "Total Errors: " + numMissedWords + "\n";
		toReturn += "Missed Words: " + arrListToString(missedWords) + "\n";
		toReturn += "Final Time(s): " + finalTime + "\n";
		toReturn += "Words Per Minute (WPM): " + WPM + "\n";
		toReturn += "Characters Per Second (CPS): " + CPS;
		return toReturn;
	}

	public static String arrListToString(ArrayList<String> arrList){
		String toReturn = "";
		for (String word : arrList){
			toReturn += word + ", ";
		}
		return toReturn;
	}

	public void setWPM(int WPM){
		this.WPM = WPM;
	}

	public void setCPS(float CPS){
		this.CPS = CPS;
	}

	public int getTime(){
		return finalTime;
	}

	public int getGameSize(){
		return gameSize;
	}

	public int getNumMissedWords(){
		return numMissedWords;
	}

	public String getMissedWords(){
		return arrListToString(missedWords);
	}

	public int getWPM(){
		return WPM;
	}

	public float getCPS(){
		return CPS;
	}


}