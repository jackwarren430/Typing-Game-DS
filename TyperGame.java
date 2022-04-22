import java.util.*;
import java.io.*;

public class TyperGame {
	private ArrayList<String> wordArr;
	private static final String FILE = "dictionary.txt";

	private int gameSize;
	private int totalCharCount;

	public TyperGame(int gameSize) throws IOException{
		this.gameSize = gameSize;
		totalCharCount = 0;
		wordArr = new ArrayList<String>();
		initWordArr();
	}

	public ArrayList<String> getErrors(ArrayList<String> finalInput){
		ArrayList<String> missedWords = new ArrayList<String>();
		for (int i = 0; i < finalInput.size(); i++){
			String inWord = finalInput.get(i).strip().toLowerCase();
			String gWord = wordArr.get(i).strip().toLowerCase();
			if (!gWord.equals(inWord)){
				missedWords.add(wordArr.get(i));
			}
		}
		return missedWords;
	}

	public ArrayList<String> getWordArr(){
		return wordArr;
	}

	public void setWordArr(ArrayList<String> a){
		wordArr = a;
	}

	public int getTotalCharCount(){
		return totalCharCount;
	}

	public void initWordArr() throws IOException{
		Scanner scan = null;
		try {
			scan = new Scanner(new File("dictionary.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> hold = new ArrayList<String>();
		while (scan.hasNextLine()){
			hold.add(scan.nextLine().strip());
		}
		scan.close();
		for (int i = 0; i < gameSize; i++){
			int randInt =  (int)((Math.random() * hold.size()));
			String word = hold.get(randInt);
			wordArr.add(word);
			totalCharCount += word.length();
		}
	}




}