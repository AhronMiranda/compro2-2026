import java.util.Scanner;


//OLD HANGMAN
public class Hangman {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int save = 0;
        

        int[] score = new int[50];
                score[save] = 0; //Save will increment for every rotation so the size of the array gets bigger every game

        String[] userDatabase = new String[50];
        int currentGame = 0;
        newSave(userDatabase, save, sc);
        

        String toGuess = wordCategory(sc);
            
        
        String[] hiddenWord = new String[toGuess.length()];
        hide(toGuess, hiddenWord);


        boolean playing = true; //Game start to be able to loop the game!
        startGame(sc, toGuess, hiddenWord, score, currentGame, playing, userDatabase); //to start the game



        while (sc.nextLine() != "n") {
            int startAgain = playAgain(sc, userDatabase, save, toGuess, toGuess, score, currentGame, playing);
            
            if (startAgain == 1) { //all the methods needed to restart
                currentGame++;
                save++;
                newSave(userDatabase, save, sc);
                score[save] = 0; //RESETS THE SCORE TO A NEW SAVE
                toGuess = wordCategory(sc);
                hide(toGuess, hiddenWord);
                startGame(sc, toGuess, hiddenWord, score, currentGame, playing, userDatabase);
            } else {
                Leaderboard(userDatabase, score);
                break;
            }
        }


        
    }// main end

    public static int newSave(String[] userDatabase, int save, Scanner sc) {
            String user = "";
        System.out.print("Player Name: ");
            while (user.isEmpty()) { // if newline was consumed, ask again (I searched how to check if enter buffer is being skipped, and how to ask again if it is)
                user = sc.nextLine().trim();
            }
            userDatabase[save] = user;
            save++;
            
        
        return save;
    }
    
    //WORD BANK
    public static String wordCategory(Scanner sc) {
        System.out.println("""
            Choose word category!
            (1) 5 letters 
            (2) 7 letters
            (3) General Words               
                    """);
        int category = sc.nextInt();
        String toGuess = "";

        switch (category) {
            case 1:
            System.out.println("You choose 5 letter.");
            String[] wordsFive = {
            "apple","brine","chair","delta","eagle","frost","grape","honey","ivory","joker","knead","lemon","mango","nudge", "ocean",
            "prism","quill","rover","stone","tiger","unity","vapor","waltz","xenon","yacht","zesty","bloom","cider","drift","ember",
            "flint","gloom","haste","inlet","jolly","karma","lurch","mural","novel","orbit","peach","quick","ranch","swirl","trace",
            "under","vivid","weary","yield","zippy"
        };
        toGuess = wordsFive[(int) ( Math.random() * (50))];
        break;
            case 2:
            System.out.println("You choose 7 letter.");
            String[] wordsSeven = {
            "ability","balance","capture","diamond","elastic","fortune","gateway","harvest","imagine","journey","kingdom","library","machine",
            "natural","october","passion","quality","resolve","silence","teacher","unified","victory","weather","zealous","account","between",
            "careful","digital","examine","freedom","general","history","inspire","justice","leading","message","nothing","opinion","protect",
            "request","science","support","through","upgrade","variety","welcome","yearning","zippers","airflow","bravery"
        };
        toGuess = wordsSeven[(int) ( Math.random() * (50))];
        break;
            case 3:
            System.out.println("General Words");
            String[] wordsGeneral = {
            "cat","planet","running","sky","elephant","map","whisper","code","mountain","aqua","stormy","glide","pencil","ox","brilliant","fog",
            "harmony","zip","lantern","pulse","riverbank","sun","echo","velocity","tree","moment","crystal","ink","wander","galaxy","note","firefly",
            "shadow","ice","thunder","leaf","orbit","dreaming","stone","wave","compass","light","forest","spark","universe","path","breeze","signal",
            "time","quartz"
        };
        toGuess = wordsGeneral[(int) ( Math.random() * (50))];
        break;
        
            
    }
        return toGuess;
    }

    //HIDE THE WORD
    public static void hide(String toGuess, String[] hiddenWord) {
        System.out.print("You need to guess the word: ");
        for (int i = 0; i < toGuess.length(); i++) {
        hiddenWord[i] = "*";
        System.out.print(hiddenWord[i] + "");
        }

    }

    //RUN THE GAME
    public static void startGame(Scanner sc, String toGuess, String [] hiddenWord, int[] score, int currentGame, boolean playing, String[] userDatabase) {
        int lives = 6;
        int guessed = 0;
        String guessedLetters = "";

        playing = true; //Game start to be able to loop the game!
        System.out.println(); // space
        
        
        while (playing) {
    
        System.out.print("\nGuess: ");
        String guess = sc.next().toLowerCase();

        boolean run = false;
        if (guess.length() == 1) {
            run = true;
        } else {
            System.out.println("[INVALID] Input one character!");
            continue; // no life lost
        }

        // Check if already guessed
        if (guessedLetters.contains(guess)) {
            System.out.println("[INVALID] You already guessed that letter!");
            continue; // no life lost
        }

        // Record new guess
        guessedLetters += guess;

        while (run) {
            if (toGuess.contains(guess)) {
                for (int i = 0; i < toGuess.length(); i++) {
                    if (toGuess.charAt(i) == (guess.charAt(0))) {
                      hiddenWord[i] = guess;
                      guessed++;
                }
            }
            System.out.print("[CORRECT GUESS] Updated word is ");
            for (int i = 0; i < toGuess.length(); i++) {
            System.out.print(hiddenWord[i] + "");
            }
            


            if (guessed == toGuess.length()) {
                System.out.println();
                System.out.println("YOU WON!!\n");
                playing = false;
            }
            score[currentGame] += 1;
            break;
            } else {
                System.out.println("[WRONG GUESS] The word does not contain that.");
                lives--;
                score[currentGame] -= 1;

                if (lives == 0) {
                        System.out.println("YOU LOSE GAME OVER!!\n");
                        playing = false;
                    }
                break;
            }
        }
        }
        System.out.printf("\nThis game you got \nPlayer: %s - Score %d \n",userDatabase[currentGame], score[currentGame]);
        
    } // END OF GAME

    public static int playAgain(Scanner sc, String[] userDatabase, int save, String toGuess, String hiddenWord, int[] score, int currentGame, boolean playing) {
        System.out.println("Do you want to play again? Y/N :");
            char playAgain = sc.next().toLowerCase().charAt(0);
            int Answer = 0;
                switch (playAgain) {
                    case 'y':
                        System.out.println("LETS PLAY AGAIN!\n");
                        Answer = 1;
                        System.out.println();
                        break;
                    default: 
                        System.out.println("Thank you for playing\n");
                        Answer = 0;
                        break;
                }
                return Answer;
    }

    public static void Leaderboard(String[] userDatabase, int[] score) {
       System.out.println("Leaderboard (sorted by score):");

        // Count how many players are actually saved
        int count = 0;
        for (int i = 0; i < userDatabase.length; i++) {
            if (userDatabase[i] != null) {
                count++;
            }
        }

        // Copy into temporary arrays
        String[] names = new String[count];
            int[] scores = new int[count];
            int copyIndex = 0;
            for (int i = 0; i < userDatabase.length; i++) {
                if (userDatabase[i] != null) {
                    names[copyIndex] = userDatabase[i];
                    scores[copyIndex] = score[i];
                copyIndex++;
            }
        }

        // Sort by score (descending)
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (scores[j] > scores[i]) {
                    // swap scores
                    int tempScore = scores[i];
                    scores[i] = scores[j];
                    scores[j] = tempScore;

                    // swap names
                    String tempName = names[i];
                    names[i] = names[j];
                    names[j] = tempName;
                }
            }
        }

        // Print sorted leaderboard
        for (int i = 0; i < count; i++) {
            System.out.printf("Rank %d: %s - Score: %d\n", i + 1, names[i], scores[i]);
        }
    }
}