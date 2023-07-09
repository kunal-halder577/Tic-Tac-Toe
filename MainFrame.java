import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This program will display a board and take user inputs to maintain a
 * game of tic tac toe for the user. The game can be played by 1 or 2 players
 * based on user input.
 */
public class MainFrame {

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> board = new ArrayList<>(3);           //ArrayList used to represent the game board
        char[][] board1 = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};

        // Initialising the arraylist
        for (int i = 0; i < 3; i++) {
            board.add(new ArrayList<>());
        }
        //Adding values
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.get(i).add(count);
                ++count;
            }
        }
        //Printing the First Board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2)
                    System.out.print(board.get(i).get(j));
                else
                    System.out.print(board.get(i).get(j) + " | ");
            }
            System.out.println();
        }
        int playernumber;                      //playernumber: variable to choose player number.
        while (true) {                          //Using infinite loop to choose the right output (1p/2p), else repeat until given
            System.out.print("Choose Number of Players (1/2): ");
            playernumber = scan.nextInt();
            if (playernumber == 1 || playernumber == 2)
                break;
            else
                System.out.println("Please give a valid input!!");
        }
        char player;                           //player: variable to choose x or o.
        while (true) {                          //Using infinite loop to choose the right output (x/o), else repeat until given
            System.out.print("Choose Player (X/O): ");
            player = scan.next().charAt(0);
            if (player == 'X' || player == 'O' || player == 'x' || player == 'o') {
                if (player == 'x') {
                    player = 'X';
                }
                if (player == 'o') {
                    player = 'O';
                }
                break;
            } else
                System.out.println("Please give a valid input!!");
        }
        if (playernumber == 2) {                 //Begin game using the 2 player method
            piecePlacer_2P(board1, player);
        }
        if (playernumber == 1) {                 //Begin game using the 1 player method
            piecePlacer_1P(board1, player);
        }
    }

    /**
     * Method that represents a 2 Player board for the game
     * @param board1 The current board being played on using a 2d array
     * @param player The curent player that is taking there turn using a char
     */
    static void piecePlacer_2P(char[][] board1, char player) {

        Scanner scan = new Scanner(System.in);
        int movesNumber = 1;                        //movesNumber: number of total moves (max = 9)


        while (movesNumber <= 9) {
            char position;                         //position: variable to store position.
            while (true) {                         //Using infinite loop to choose the right position, else repeat until given
                System.out.print("Enter Position: ");
                position = scan.next().charAt(0);
                if (position == '1' || position == '2' || position == '3' || position == '4' || position == '5' || position == '6' || position == '7' || position == '8' || position == '9')
                    break;
                else
                    System.out.println("Please give a valid input!!");
            }
            //Checking players move and placing the correct X or O based on position
            for (int rowIndex = 0; rowIndex < board1.length; rowIndex++) {
                for (int colIndex = 0; colIndex < board1[rowIndex].length; colIndex++) {
                    if (board1[rowIndex][colIndex] == position) {
                        if (movesNumber % 2 != 0) {
                            board1[rowIndex][colIndex] = player;
                        } else {
                            if (player == 'X') {
                                board1[rowIndex][colIndex] = 'O';
                            } else if (player == 'O') {
                                board1[rowIndex][colIndex] = 'X';
                            }
                        }
                        movesNumber++;
                    }
                }
            }
            for (int rowIndex = 0; rowIndex < board1.length; rowIndex++) {
                for (int colIndex = 0; colIndex == 0; colIndex++) {
                    System.out.println(board1[rowIndex][colIndex] + " | " + board1[rowIndex][colIndex + 1] + " | " + board1[rowIndex][colIndex + 2]);
                }
            }
            //Variables to represent each players moves to calculate the winner based on position
            int XmoveCounter_Row = 0;
            int XmoveCounter_Col = 0;
            int XmoveCounter_D1 = 0;
            int XmoveCounter_D2 = 0;
            int OmoveCounter_Row = 0;
            int OmoveCounter_Col = 0;
            int OmoveCounter_D1 = 0;
            int OmoveCounter_D2 = 0;
            if (movesNumber >= 5) {
                for (int rowIndex = 0; rowIndex < board1.length; rowIndex++) {
                    for (int colIndex = 0; colIndex < board1[rowIndex].length; colIndex++) {
                        if (board1[rowIndex][colIndex] == 'X') {
                            XmoveCounter_Row++;
                        } else if (board1[rowIndex][colIndex] == 'O') {
                            OmoveCounter_Row++;
                        }
                    }
                    if (XmoveCounter_Row == 3)
                        break;
                    else {
                        XmoveCounter_Row = 0;
                    }
                    if (OmoveCounter_Row == 3)
                        break;
                    else {
                        OmoveCounter_Row = 0;
                    }
                }
                //Updating the moveCounter for column based on what was placed in the position given by the user
                int rowIndex = 0;
                for (int colIndex = 0; colIndex < board1[rowIndex].length; colIndex++) {

                    for (rowIndex = 0; rowIndex < board1.length; rowIndex++) {
                        if (board1[rowIndex][colIndex] == 'X') {
                            XmoveCounter_Col++;
                        } else if (board1[rowIndex][colIndex] == 'O') {
                            OmoveCounter_Col++;
                        }
                    }
                    rowIndex--;
                    if (XmoveCounter_Col == 3)
                        break;
                    else {
                        XmoveCounter_Col = 0;
                    }
                    if (OmoveCounter_Col == 3)
                        break;
                    else {
                        OmoveCounter_Col = 0;
                    }
                }
                //Updating the moveCounter for D1 based on what was placed in the position given by the user
                int colIndex = 0;
                for (int rowIndex1 = 0; rowIndex1 < board1.length && colIndex < board1[rowIndex1].length; rowIndex1++, colIndex++) {
                    if (board1[rowIndex1][colIndex] == 'X') {
                        XmoveCounter_D1++;
                    } else if (board1[rowIndex1][colIndex] == 'O') {
                        OmoveCounter_D1++;
                    }
                }
                //Updating the moveCounter for D2 based on what was placed in the position given by the user
                int colIndex1 = board1[rowIndex].length - 1;
                for (int rowIndex1 = 0; rowIndex1 < board1.length && colIndex1 >= 0; rowIndex1++, colIndex1--) {
                    if (board1[rowIndex1][colIndex1] == 'X') {
                        XmoveCounter_D2++;
                    } else if (board1[rowIndex1][colIndex1] == 'O') {
                        OmoveCounter_D2++;
                    }
                }
            }
            //Logic for the completion of the game for X winning O winning or a draw
            if (XmoveCounter_Row == 3 || XmoveCounter_Col == 3 || XmoveCounter_D1 == 3 || XmoveCounter_D2 == 3) {
                System.out.println("PLayer X Won.");
                break;
            } else if (OmoveCounter_Row == 3 || OmoveCounter_Col == 3 || OmoveCounter_D1 == 3 || OmoveCounter_D2 == 3) {
                System.out.println("Player O Won.");
                break;
            }
            if (movesNumber > 9) {
                if (XmoveCounter_Row != 3 || XmoveCounter_Col != 3 || XmoveCounter_D1 != 3 || XmoveCounter_D2 != 3 || OmoveCounter_Row != 3 || OmoveCounter_Col != 3 || OmoveCounter_D1 != 3 || OmoveCounter_D2 != 3)
                    System.out.println("This match is a Draw.");
            }
        }
    }
    /**
     * Method that respresents a 1 player game against the computer
     * @param board1 The current board being played on using a 2d array
     * @param player The curent player that is taking there turn using a char
     */
    static void piecePlacer_1P(char[][] board1, char player) {

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int movesNumber = 1;                        //movesNumber: number of total moves (max = 9)


        while (movesNumber <= 9) {
            char position;                         //position: variable to store position.
            while (true) {                         //Using infinite loop to choose the right position, else repeat until given
                if (movesNumber % 2 != 0) {
                    System.out.print("Enter Position: ");
                    position = scan.next().charAt(0);
                    if (position == '1' || position == '2' || position == '3' || position == '4' || position == '5' || position == '6' || position == '7' || position == '8' || position == '9')
                        break;
                    else
                        System.out.println("Please give a valid input!!");
                } else {
                    System.out.println("Computer's Turn");
                    char[] position1 = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
                    int aiIndex = rand.nextInt(0, 9);
                    position = position1[aiIndex];
                    break;
                }
            }
            //Checking players move and placing the correct X or O based on position
            for (int rowIndex = 0; rowIndex < board1.length; rowIndex++) {
                for (int colIndex = 0; colIndex < board1[rowIndex].length; colIndex++) {
                    if (board1[rowIndex][colIndex] == position) {
                        if (movesNumber % 2 != 0) {
                            board1[rowIndex][colIndex] = player;
                        } else {
                            if (player == 'X') {
                                board1[rowIndex][colIndex] = 'O';
                            } else if (player == 'O') {
                                board1[rowIndex][colIndex] = 'X';
                            }
                        }
                        movesNumber++;
                    }
//                    else if (board1[rowIndex][colIndex] == 'X' || board1[rowIndex][colIndex] == 'O') {
//                        System.out.println("This Move is already played.");
//                    }
                }
            }
            for (int rowIndex = 0; rowIndex < board1.length; rowIndex++) {
                for (int colIndex = 0; colIndex == 0; colIndex++) {
                    System.out.println(board1[rowIndex][colIndex] + " | " + board1[rowIndex][colIndex + 1] + " | " + board1[rowIndex][colIndex + 2]);
                }
            }
            //Variables to represent each players moves to calculate the winner based on position
            int XmoveCounter_Row = 0;
            int XmoveCounter_Col = 0;
            int XmoveCounter_D1 = 0;
            int XmoveCounter_D2 = 0;
            int OmoveCounter_Row = 0;
            int OmoveCounter_Col = 0;
            int OmoveCounter_D1 = 0;
            int OmoveCounter_D2 = 0;

            if (movesNumber >= 5) {
                //Updating the moveCounter for Row based on what was placed in the position given by the user
                for (int rowIndex = 0; rowIndex < board1.length; rowIndex++) {
                    for (int colIndex = 0; colIndex < board1[rowIndex].length; colIndex++) {
                        if (board1[rowIndex][colIndex] == 'X') {
                            XmoveCounter_Row++;
                        } else if (board1[rowIndex][colIndex] == 'O') {
                            OmoveCounter_Row++;
                        }
                    }
                    if (XmoveCounter_Row == 3)
                        break;
                    else {
                        XmoveCounter_Row = 0;
                    }
                    if (OmoveCounter_Row == 3)
                        break;
                    else {
                        OmoveCounter_Row = 0;
                    }
                }
                //Updating the moveCounter for column based on what was placed in the position given by the user
                int rowIndex = 0;
                for (int colIndex = 0; colIndex < board1[rowIndex].length; colIndex++) {

                    for (rowIndex = 0; rowIndex < board1.length; rowIndex++) {
                        if (board1[rowIndex][colIndex] == 'X') {
                            XmoveCounter_Col++;
                        } else if (board1[rowIndex][colIndex] == 'O') {
                            OmoveCounter_Col++;
                        }
                    }
                    rowIndex--;
                    if (XmoveCounter_Col == 3)
                        break;
                    else {
                        XmoveCounter_Col = 0;
                    }
                    if (OmoveCounter_Col == 3)
                        break;
                    else {
                        OmoveCounter_Col = 0;
                    }
                }
                //Updating the moveCounter for D1 based on what was placed in the position given by the user
                int colIndex = 0;
                for (int rowIndex1 = 0; rowIndex1 < board1.length && colIndex < board1[rowIndex1].length; rowIndex1++, colIndex++) {
                    if (board1[rowIndex1][colIndex] == 'X') {
                        XmoveCounter_D1++;
                    } else if (board1[rowIndex1][colIndex] == 'O') {
                        OmoveCounter_D1++;
                    }
                }
                //Updating the moveCounter for D2 based on what was placed in the position given by the user
                int colIndex1 = board1[rowIndex].length - 1;
                for (int rowIndex1 = 0; rowIndex1 < board1.length && colIndex1 >= 0; rowIndex1++, colIndex1--) {
                    if (board1[rowIndex1][colIndex1] == 'X') {
                        XmoveCounter_D2++;
                    } else if (board1[rowIndex1][colIndex1] == 'O') {
                        OmoveCounter_D2++;
                    }
                }
            }
            //Logic for the completion of the game for X winning O winning or a draw
            if (XmoveCounter_Row == 3 || XmoveCounter_Col == 3 || XmoveCounter_D1 == 3 || XmoveCounter_D2 == 3) {
                System.out.println("PLayer X Won.");
                break;
            } else if (OmoveCounter_Row == 3 || OmoveCounter_Col == 3 || OmoveCounter_D1 == 3 || OmoveCounter_D2 == 3) {
                System.out.println("Player O Won.");
                break;
            }
            if (movesNumber > 9) {
                if (XmoveCounter_Row != 3 || XmoveCounter_Col != 3 || XmoveCounter_D1 != 3 || XmoveCounter_D2 != 3 || OmoveCounter_Row != 3 || OmoveCounter_Col != 3 || OmoveCounter_D1 != 3 || OmoveCounter_D2 != 3)
                    System.out.println("This match is a Draw.");
            }
        }
    }
}


