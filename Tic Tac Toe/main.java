import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        //Create a 3 by 3 array as the board
        char[][] board = new char[3][3];

        //Initialize board with spaces
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        //Create scanner to input player names
        Scanner in = new Scanner(System.in);
        System.out.println("TIME TO PLAY SOME TIC! TAC! TOE!");
        System.out.println("What is your name Player 1? ");
        String p1 = in.nextLine();
        System.out.println("What is your name Player 2? ");
        String p2 = in.nextLine();

        //Create boolean for whose turn it is
        boolean player1 = true;

        //Create boolean for game end
        boolean gameEnd = false;
        while (!gameEnd) {
            //Draw board
            drawBoard(board);

            //Print whose turn
            if(player1) {
                System.out.println(p1 + "'s turn (x):");
            } else {
                System.out.println(p2 + "'s turn (o):");
            }

            //Create variable to store current player's "piece"
            char c = ' ';
            if(player1) {
                c = 'x';
            } else {
                c = 'o';
            }

            //Create variables for columns and rows
            int row = 0;
            int col = 0;

            while (true) {
                //Ask player what position they want to play
                System.out.println("Enter a row number, 0, 1 or 2: ");
                row = in.nextInt();
                System.out.println("Enter a column number, 0, 1 or 2: ");
                col = in.nextInt();

                //Check for validity
                if(row < 0 || col < 0 || row > 2 || col > 2) {
                    System.out.println("Invalid input, please enter a different number.");
                }
                //Check if space is empty or taken
                else if(board[row][col] != ' ') {
                    System.out.println("There is already a piece in this spot, choose another.");
                }
                //Valid response so leave the loop
                else {
                    break;
                }
            }

            //Set board position to entered position
            board[row][col] = c;

            //Check if victory
            if(playerHasWon(board) == 'x') {
                System.out.println(p1 + " has won!");
                gameEnd = true;
            }
            else if(playerHasWon(board) == 'o') {
                System.out.println(p2 + " has won!");
                gameEnd = true;
            }
            //If no victory, check if tie (if board full)
            else {
                if(boardIsFull(board)) {
                    System.out.println("Tie Game!");
                    gameEnd = true;
                }
                else {
                    //Set alternate players for turns
                    player1 = !player1;
                }
            }
        }
        //Draw final board
        drawBoard(board);
    }

    //Function to draw board
    public static void drawBoard(char[][] board) {
        System.out.println("Board:");;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.println(board[i][j]);
            }
            System.out.println();
        }
    }

    //Function to see if there is a winner
    public static char playerHasWon(char[][] board) {
        //Check rows
        for(int i = 0; i < 3; i++) {
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][0];
            }
        }

        //Check columns
        for(int j = 0; j < 3; j++) {
            if(board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
                return board[0][j];
            }
        }

        //Check diagonals
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }
        if(board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '-') {
            return board[2][0];
        }
        //If no winner
        return ' ';
    }

    //Function to check if board is full
    public static boolean boardIsFull(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][i] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
