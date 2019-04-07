import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please input the number of columns.");
		int numColumns = scanner.nextInt();
		
		System.out.println("Please input the number of rows.");
		int numRows = scanner.nextInt();
		
		// Levels
		//	1 - 10% are bombs
		//  2 - 30% are bombs
		//  3 - 50% are bombs
		System.out.println("What level do you want to play at?");
		int numLevel = scanner.nextInt();
		
		while(numLevel > 3 || numLevel < 1) {
			System.out.println("Not a valid level - try again");
			numLevel = scanner.nextInt();
		}
		
		Game game = new Game(numColumns, numRows, numLevel);
		
		game.playGame(scanner);
	}
}