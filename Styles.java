import java.awt.*;
import java.util.*;
import static java.util.Map.entry;

public class Styles {
	//colors
	
	//Reef
	public static Color teal = new Color(0, 128, 128);
	public static Color coral = new Color(255, 127, 80);

	//Dark
	public static Color black = Color.black;
	public static Color grey = new Color(145, 145, 145);

	//Scarlet
	public static Color maroon = new Color(128, 0, 0);
	public static Color silver = new Color(192, 192, 192);

	//Ocean
	public static Color navy = new Color(0, 0, 128);
	public static Color lightBlue = new Color(0, 128, 225);

	//Purplishous
	public static Color purple = new Color(128, 0, 128);
	public static Color lilac = new Color(200, 162, 200);

	//Popsical
	public static Color burntOrange = new Color(204, 85, 0);
	public static Color white = Color.white;

	//12AM
	public static Color red = Color.red;
  		//black

	//Planes
	public static Color appleGreen = new Color(141, 182, 0);
	 	//light blue

	//fonts
	public static Font buttonFont = new Font("Ariel", Font.PLAIN, 40);
	public static Font buttonFont2 = new Font("Ariel", Font.BOLD, 25);
	public static Font labelsFont = new Font("Ariel", Font.BOLD, 20);
	public static Font logiFont = new Font("Ariel", Font.PLAIN, 15);

	public static HashMap<String, Color[]> colorMap = new HashMap<String, Color[]>();
	public static HashMap<String, Color[]> getColorMap(){
		colorMap.put("Reef", new Color[] {teal, coral});
		colorMap.put("Dark", new Color[] {black, grey});
		colorMap.put("Scarlet", new Color[] {maroon, silver});
		colorMap.put("Ocean", new Color[] {navy, lightBlue});
		colorMap.put("Purplishous", new Color[] {purple, lilac});
		colorMap.put("Popsical", new Color[] {burntOrange, white});
		colorMap.put("12 A.M", new Color[] {black, red});
		colorMap.put("Planes", new Color[] {appleGreen, lightBlue});
		return colorMap;
	}
}