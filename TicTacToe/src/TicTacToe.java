import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	
	
	public static void printPlayArea(char[][] playArea) {
		for(char [] row: playArea) {
			for(char c: row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] playArea, int position, String user) {
		
		char symbol = ' ';
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(position);
		} else if(user.equals("cpu")) {
			symbol = '0';
			cpuPositions.add(position);
		}
		
		switch(position) {
			case 1:
				playArea[0][0] = symbol;
				break;
			case 2:
				playArea[0][2] = symbol;
				break;
			case 3:
				playArea[0][4] = symbol;
				break;
			case 4:
				playArea[2][0] = symbol;
				break;
			case 5:
				playArea[2][2] = symbol;
				break;
			case 6:
				playArea[2][4] = symbol;
				break;
			case 7:
				playArea[4][0] = symbol;
				break;
			case 8:
				playArea[4][2] = symbol;
				break;
			case 9:
				playArea[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List lCol = Arrays.asList(1, 4, 7);
		List mCol = Arrays.asList(2, 5, 8);
		List rCol = Arrays.asList(3, 6, 9);
		List diag1 = Arrays.asList(1, 5, 9);
		List diag2 = Arrays.asList(7, 5, 3);
		
		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(botRow);
		winningConditions.add(lCol);
		winningConditions.add(mCol);
		winningConditions.add(rCol);
		winningConditions.add(diag1);
		winningConditions.add(diag2);
		
		for(List l : winningConditions) {
			if(playerPositions.containsAll(l)) {
				return "YOU WIN! CONGRATS!";
			} else if(cpuPositions.containsAll(l)) {
				return "CPU WINS! HOLD THAT L!";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "TIE";
			}
		}
		
		
		return "";
	}
	
	public static void main(String[] args) {

		char[][] playArea = {{' ', '|', ' ', '|', ' '},
					{'-', '+', '-', '+', '-'},
					{' ', '|', ' ', '|', ' '},
					{'-', '+', '-', '+', '-'},
					{' ', '|', ' ', '|', ' '}};
		
		printPlayArea(playArea);
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter you placement 1-9");
			int playerPosition = sc.nextInt();
			
			while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
				System.out.println("Position already in play. Enter a unused position.");
				playerPosition = sc.nextInt();
			}
		
			placePiece(playArea, playerPosition, "player");
			
			String result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPosition = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
				cpuPosition = rand.nextInt(9) + 1;
			}
			placePiece(playArea, cpuPosition, "cpu");
			
			printPlayArea(playArea);
			
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
		}
		
	}

}