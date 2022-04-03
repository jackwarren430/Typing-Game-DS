import java.awt.*;
import java.util.*;
import static java.util.Map.entry;

public class Styles {
	//colors
	public static ArrayList<String> colorSchemes = new ArrayList<String>(Arrays.asList(
		"Reef",
		"Dark"
	));
	
	//Reef
	public static Color teal = new Color(0, 128, 128);
	public static Color coral = new Color(255, 127, 80);

	//Dark
	public static Color black = Color.black;
	public static Color grey = new Color(145, 145, 145);

	//fonts
	public static Font buttonFont = new Font("Ariel", Font.PLAIN, 40);
	public static Font buttonFont2 = new Font("Ariel", Font.BOLD, 25);
	public static Font labelsFont = new Font("Ariel", Font.BOLD, 20);
	public static Font logiFont = new Font("Ariel", Font.PLAIN, 15);

	public static HashMap<String, Color[]> colorMap = new HashMap<String, Color[]>();
	public static HashMap<String, Color[]> getColorMap(){
		colorMap.put("Reef", new Color[] {teal, coral});
		colorMap.put("Dark", new Color[] {black, grey});
		return colorMap;
	}
}