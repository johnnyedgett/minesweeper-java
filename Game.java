import java.util.Scanner;

public class Game {
	public int numRows;
	public int numColumns;
	public int numLevel;
	public int bombsLeft;
	public double percent;
	public int[][] arr;
	public int[][] playerArr;
	
	public Game(int numColumns, int numRows, int numLevel) {
		System.out.println("Creating a game with " + numColumns + " columns and " + numRows + " rows at level " + numLevel);
		this.numColumns=numColumns;
		this.numRows=numRows;
		this.numLevel=numLevel;
		
		switch(numLevel) {
			case 1: percent=.1; break;
			case 2: percent=.2; break;
			case 3: percent=.3; break;
		default:
			percent=1; break;
		}
		initializeField();
	}
	
	public void initializeField() {
		arr = new int[numRows][numColumns];
		for(int i = 0; i < arr.length; i++) {
			for(int j=0; j < arr[i].length; j++) {
				if(Math.random() > (1-percent)) {
					arr[i][j]=-1;
					updateSurrounding(i, j);
				}
			}
		}
		playerArr = new int[numRows][numColumns];
//		showBoard("bombs");
	}
	
	public void showBoard(String type) {
		if(type.equals("player")) {
			for(int x = 0; x < arr.length; x++) {
				for(int y=0; y<arr[x].length; y++) {
					System.out.print(playerArr[x][y] + ", ");
				}
				System.out.println();
			}		
		} else if(type.equals("bombs")) {
			for(int x = 0; x < arr.length; x++) {
				for(int y=0; y<arr[x].length; y++) {
					System.out.print(arr[x][y] + ", ");
				}
				System.out.println();
			}	
		}
	}
	
	public void playGame(Scanner scanner) {
		System.out.println("Time to play the game!");
		System.out.println("Please input the coordinates as x,y you want to check for a bomb. Type 'show' to view the board."
				+ " Alternatively, type 'quit' to quit.");
		boolean playing = true;
		scanner.reset();
		while(playing) {
			System.out.print("$> ");
			String input = scanner.nextLine();
			if(input.equals("show")) {
				showBoard("player");
			} else if(input.equals("quit")) {
				System.exit(0);
			} else {
				String[] coordinates = input.split(",");
				if(coordinates.length!=2) {
					System.out.println("Unrecognized input - try again. 'show' to continue, 'quit' to quit, 'x,y' to check.");
				} else {
					checkForBomb(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
				}
			}
		}
	}
	
	public void checkForBomb(int y, int x) {
		if(x >= 0 && y >= 0) {
			if(arr[x][y] == -1) {
				System.out.println("That's a bomb!"); 
				System.out.println("Game over.");
			} else {
				System.out.println("You survived.");
				playerArr[x][y] = arr[x][y];
			}	
		}
	}
	
	public void updateSurrounding(int i, int j) {
		if(i-1 >= 0 && j-1 >= 0) 
			if(arr[i-1][j-1]!=-1) 
				arr[i-1][j-1]++;
		
		if(i-1 >= 0)
			if(arr[i-1][j]!=-1)
				arr[i-1][j]++;
		
		if(i-1 >= 0 && j+1 <= numColumns-1)
			if(arr[i-1][j+1] != -1)
				arr[i-1][j+1]++;
		
		if(j-1 >=0)
			if(arr[i][j-1] != -1)
				arr[i][j-1]++;
		
		if(j+1 <= numColumns-1)
			if(arr[i][j+1]!=-1)
				arr[i][j+1]++;
			
		if(i+1 <= numRows-1 && j-1 >= 0) 
			if(arr[i+1][j-1]!=-1)
				arr[i+1][j-1]++;
		
		if(i+1 <= numRows-1)
			if(arr[i+1][j]!=-1)
				arr[i+1][j]++;
		
		if(i+1 <= numRows-1 && j+1 <= numColumns-1)
			if(arr[i+1][j+1]!=-1)
				arr[i+1][j+1]++;
	}
}