// TODO: comment this file


//Case Insensitive
//

import acm.program.*;
import acm.util.*;
import java.io.*;    // for File
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;  // for Scanner

public class Hangman extends HangmanProgram {
	
	
	public void run() {
		// TODO: write this method
		
		int num_game = 0;
		int win_game = 0;
		int best = 8;
		int guessLeft;
		intro();
		String filename = promptUserForFile("Input Dictionary?", "res");
		
		guessLeft=playOneGame(getRandomWord(filename));
		num_game++;
		if (guessLeft!=0) {
			win_game++;
			if (guessLeft<best) {
				best=guessLeft;
			}
		}
		while (readBoolean("Play again (Y/N)?", "Y", "N")) {
			playOneGame(getRandomWord(filename));
			num_game++;
			if (guessLeft!=0) {
				win_game++;
				if (guessLeft<best) {
					best=guessLeft;
				}
			}
		}
		stats(num_game, win_game, best);
	}
	
	// TODO: comment this method
	private void intro() {
		// TODO: write this method
		printf("CS 106A Hangman!\n");
		printf("I will think of a random word.\n");
		printf("You'll try to guess its letters.\n");
		printf("Every time you guess a letter\n");
		printf("that isn't in my word, a new body\n");
		printf("part of the hanging man appears.\n");
		printf("Guess correctly to avoid the gallows!\n");
		printf("\n");
	}
	
	// TODO: comment this method
	private int playOneGame(String secretWord) {
		//Display Hint about the secret word
		int count = 0;
		String guessHistory = "";
		displayHangman(8);
		
		while(true){
			printf("Secret word : "+createHint(secretWord, guessHistory)+"\n");
			//Ask the user to type valid guess
			printf("Your Guesses: "+guessHistory+"\n");
			printf("Guesses left: "+String.valueOf(8-count)+"\n");
			
			while (true) {
				String guesses = readLine("Your Guesses?");
				guesses = guesses.toUpperCase();

				// if the secret word contains input char
				
				if (guessHistory.contains(guesses)) {
					printf("You already guessed that letter.\n");
				}
				
				// If more than single character is inputed
				else if (guesses.length()>1 || guesses.length()==0||guesses.contains(" ")) {
					printf("Try a Single Letter from A-Z\n");
				}
				// If the guess is right
				else if(secretWord.contains(guesses)){
					printf("Correct!\n\n");
					guessHistory=guessHistory+guesses;
					count=count+1;
					break;
				}
				else {
					printf("Incorrect!\n\n");
					count=count+1;
					printf("Guessess Left: "+(8-count)+"\n");
					displayHangman(8-count);
					guessHistory=guessHistory+guesses;
					break;
				}
			}
			
			if (!createHint(secretWord, guessHistory).contains("-")) {
				printf("You win! My word was "+secretWord);
				break;
			}else if((8-count)==0){
				break;
			}
			}
		return 8-count;
	}
	
	
	// TODO: comment this method
	private String createHint(String secretWord, String guessedLetters) {
		// TODO: write this method
		
		//Creating Hint Dash
		String dash = "";
		
		for (int i=0; i<secretWord.length();i++) {
			char charSW = secretWord.charAt(i);
			String charSWS = String.valueOf(charSW);
			if (guessedLetters.contains(charSWS)) {
				dash=dash+charSWS;
			}else {
				dash=dash+"-";
			}
		}
		return dash;
	}
	
	// TODO: comment this method
	private char readGuess(String guessedLetters) {
		// TODO: write this method
		return '?';
	}
	
	// TODO: comment this method
	private void displayHangman(int guessCount){
		canvas.clear();
		String fileName = "res/display"+String.valueOf(guessCount)+".txt";
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		try {
			while((line = in.readLine()) != null){
				canvas.println(line);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String String(int guessCount) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO: comment this method
	private void stats(int gamesCount, int gamesWon, int best) {
		printf("Number of game played: "+ gamesCount+"\n");
		printf("Game Won: "+gamesWon+"\n");
		printf("Won Percentage: "+(gamesWon/gamesCount*100)+"%%\n"  );
		printf("The Best Guess(es): "+ best+"\n");
	}
	
	// TODO: comment this method
	private String getRandomWord(String filename) {
		File file = new File(filename); 
		try {
			Scanner sc = new Scanner(file);
		    int length = sc.nextInt();
		    int random = (int)(Math.random() * length + 1);
		    skipLines(sc, random);
		    String str = sc.nextLine();
		return str;
	
		}catch (Exception e) {
		return"";
	}
	}
	public static void skipLines(Scanner s,int lineNum){
        for(int i = 0; i < lineNum;i++){
            if(s.hasNextLine())s.nextLine();
        }
    }
}
