import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String [] grid = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        ArrayList<Integer> options = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac No \n\nTo play the game you just simply need to enter the number of the field you want to occupied \nThe numbers are as shown in the following example\n");

        displayGrid(grid);

        System.out.println("So let's play...\n\n\nNow it's time to choose your shape of choice.\nFirst player would you like to have 'X' or 'O'\nEnter your preferred character");
        String player1Object = "";
        String player2Object = "";

        while(true){
            player1Object = scanner.nextLine();

            if(player1Object.equalsIgnoreCase("X")){
                player2Object = "O";
                System.out.println("Second player then you are " + player2Object);
                break;
            }
            else if(player1Object.equalsIgnoreCase("O") || player1Object.equalsIgnoreCase("0")){
                player2Object = "X";
                System.out.println("Second player then you are " + player2Object);
                break;
            }else {
                System.out.println("Your input was an invalid character");
            }
        }

        grid = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};

        displayGrid(grid);

        for (int j = 0; j < 9; j++){
            System.out.println("Field: ");
            int input = scanner.nextInt();
            System.out.println("");

            if(!options.contains(input)){
                options.add(input);
                if (j % 2 == 0){
                    grid[input - 1] = player1Object;
                }else{
                    grid[input - 1] = player2Object;
                }
                displayGrid(grid);

                boolean hasWon1v = win(0, 3, grid);
                boolean hasWon2v = win(1, 3, grid);
                boolean hasWon3v = win(2, 3, grid);

                boolean hasWon1h = win(0, 1, grid);
                boolean hasWon2h = win(3, 1, grid);
                boolean hasWon3h = win(6, 1, grid);

                boolean hasWon1d = win(0, 4, grid);
                boolean hasWon2d = win(2, 2, grid);

                if(hasWon1v || hasWon2v || hasWon3v || hasWon1h || hasWon2h || hasWon3h || hasWon1d || hasWon2d){
                    if (j % 2 == 0){
                        System.out.println("First player (" + player1Object + "), you just won the game");
                    }else{
                        System.out.println("Second player (" + player2Object + "), you just won the game");
                    }
                    break;
                }else if (j == 8){
                    System.out.println("Draw");
                }else{
                    continue;
                }
            }else {
                System.out.println("This field has already been occupied");
                j--;
            }
        }
        scanner.close();
    }

    public static void displayGrid(String [] grid){
        for (int i = 0; i < grid.length; i++){
            if (i == 0){
                System.out.print(" " + grid[i] + "| ");
            }
            else if (i % 3 == 2){
                System.out.print(grid[i]);
            } else if (i % 3 == 1) {
            System.out.print(grid[i] + " |");
            }
            else if (i % 3 != 0) {
                System.out.print(grid[i] + "|");
            } else{
                System.out.print("\n--+---+--");
                System.out.print("\n" + " " + grid[i] + "| ");
            }
        }
        System.out.println("\n\n");
    }

    public static boolean win(int startPoint, int multiply, String [] grid){
        String [] set =new String[3];

        for (int i = 0; i < 3; i++){
            set[i] = (String) Array.get(grid, startPoint);
            startPoint+=multiply;
        }

        if(Objects.equals(set[0], set[1]) && Objects.equals(set[1], set[2]) && !set[0].equals(" ")){
            return true;
        }else {
            return false;
        }
    }
}