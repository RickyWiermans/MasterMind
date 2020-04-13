import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class MasterMind {
    static ArrayList<Character> code = new ArrayList<>();
    static String possibleLetters = "abcdef";

    public static void main (String[] args) {
        Random rd = new Random();
        char rdLetter;
        int rdNumber;

        //Random code wordt gemaakt als startpunt
        for (int i = 0; i < 4; i++) {
            rdNumber = rd.nextInt(6);
            rdLetter = possibleLetters.charAt(rdNumber);
            code.add(rdLetter);
        }

        //Game start
        new MasterMind().game();
    }

    void game() {
        boolean win = false;
        String inputUser;

        System.out.println("Welcome to the game MasterMind.");
        System.out.println();

        //Speler wint zodra de code is geraden, dus win is dan true.
        while(!win) {
            inputUser = getInputUser();
            if (inputUser.equals("q")){
                break;
            }
            win = codeCheck(inputUser);
            System.out.println();
        }
        System.out.println("Thank you for playing MasterMind.");
    }

    //In deze sectie wordt gekeken als de code die de gebruiker invoert wel mogelijk is.
    String getInputUser(){
        boolean correctInput = false;
        Scanner sc = new Scanner(System.in);
        String inputUser = "";

        while (!correctInput) {
            correctInput = true;
            System.out.println("Do a guess: ");
            inputUser = sc.nextLine().toLowerCase();

            if (inputUser.equals("q")){
                return inputUser;
            }

            for (int i = 0; i < 4; i++) {
                if(inputUser.length() != 4 || !possibleLetters.contains(inputUser.substring(i, i + 1))){
                    System.out.println("Wrong user input, try again!");
                    System.out.println();
                    correctInput = false;
                    break;
                }
            }
        }
        return inputUser;
    }

    //Wat de gebruiker heeft ingevoerd wordt hier vergeleken met de random code.
    boolean codeCheck(String inputUser){
        int blackPin = 0;
        int whitePin = 0;
        ArrayList<Character> guess = new ArrayList<>();
        ArrayList<Character> codeCheck = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            guess.add(inputUser.charAt(i));
            codeCheck.add(i,code.get(i));
            if (guess.get(i) == code.get(i)) {
                blackPin++;
                guess.set(i,'g');
                codeCheck.set(i,'h');
            }
        }

        for (int i = 0; i < 4; i++){
            if(guess.contains(codeCheck.get(i))){
                whitePin++;
                guess.set(guess.indexOf(codeCheck.get(i)),'g');
                codeCheck.set(i,'h');
            }
        }

        System.out.println("Number of correct letter positions: " + blackPin);
        System.out.println("Number of correct letters: " + whitePin);

        if (blackPin == 4){
            System.out.println();
            System.out.println("You win!");
        }

        guess.clear();
        codeCheck.clear();
        return blackPin == 4;
    }
}
