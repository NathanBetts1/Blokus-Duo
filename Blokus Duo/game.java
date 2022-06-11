
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class game {

    public static Player playerOne = new Player(" ");
    public static Player playerTwo = new Player(" ");
    private static String currentPlayer;


    public static String getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(String currentPlayer) {
        game.currentPlayer = currentPlayer;
    }

    public static char[][] board = new char[14][14];

    //playerSelection by 19205389
    public void playerSelection(char input) {
        int chosen = 0;
        switch (input) {
            case '1':
                setCurrentPlayer(playerOne.getName());
                break;
            case '2':
                setCurrentPlayer(playerTwo.getName());
                break;
            default:
                Random rnd = new Random();
                chosen = (int) Math.floor(Math.random() * (2 - 1 + 1) + 1);
                System.out.println(chosen);
                if (chosen == 1) {
                    setCurrentPlayer(playerOne.getName());
                } else {
                    setCurrentPlayer(playerTwo.getName());
                }
        }
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public game(char[][] board) {
        this.board = board;
    }


    public static void startGame() {
        System.out.println("                   Welcome to:");
        System.out.println("\n" +
                "██████╗░██╗░░░░░░█████╗░██╗░░██╗██╗░░░██╗░██████╗\n" +
                "██╔══██╗██║░░░░░██╔══██╗██║░██╔╝██║░░░██║██╔════╝\n" +
                "██████╦╝██║░░░░░██║░░██║█████═╝░██║░░░██║╚█████╗░\n" +
                "██╔══██╗██║░░░░░██║░░██║██╔═██╗░██║░░░██║░╚═══██╗\n" +
                "██████╦╝███████╗╚█████╔╝██║░╚██╗╚██████╔╝██████╔╝\n" +
                "╚═════╝░╚══════╝░╚════╝░╚═╝░░╚═╝░╚═════╝░╚═════╝░\n" +
                "\n" +
                "            ██████╗ ░██╗░░░██╗ ░█████╗░\n" +
                "            ██╔══██╗ ██║░░░██║ ██╔══██╗\n" +
                "            ██║░░██║ ██║░░░██║ ██║░░██║\n" +
                "            ██║░░██║ ██║░░░██║ ██║░░██║\n" +
                "            ██████╔╝ ╚██████╔╝ ╚█████╔╝\n" +
                "            ╚═════╝░ ░╚═════╝░░ ╚════╝░\n");
        System.out.println("Please Enter Player's One Name:");
        Scanner player1 = new Scanner(System.in);
        String playerOneName = player1.nextLine();
        playerOne.setName(playerOneName);
        System.out.println("Please Enter Player's Two Name:");
        Scanner player2 = new Scanner(System.in);
        String playerTwoName = player2.nextLine();
        playerTwo.setName(playerTwoName);

        System.out.println("Which player will be making the first move?\n Input 1 for player 1, 2 for player 2 and 3 for random choice : ");
        game gm = new game(board);
        Scanner choice = new Scanner(System.in);
        char input = choice.next().charAt(0);
        gm.playerSelection(input);
    }

    public static void initboard(char[][] board) {
        for (int row = 0; row < 14; row++) {
            for (int col = 0; col < 14; col++) {
                if ((row == 4 && col == 4) || (row == 9 && col == 9)) {
                    board[row][col] = '*';
                } else {
                    board[row][col] = ' ';
                }
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("   ---------------------| BLOKUS DUO |----------------------");
        System.out.print("   +---");

        for (int col = 0; col < 13; col++) {

            System.out.print("+---");
        }
        System.out.print("+\n");
        for (int row = 0; row < 14; row++) {
            if (row > 9) {
                System.out.print(row + " ");
            } else {
                System.out.print(row + "  ");

            }
            for (int col = 0; col < 14; col++) {
                System.out.print("| " + board[row][col] + " ");
            }
            System.out.print("|\n");
            System.out.print("  ");
            System.out.print(" +---");

            for (int k = 0; k < 13; k++)
                System.out.print("+---");

            System.out.print("+\n");
        }
        System.out.println("    0   1   2   3   4   5   6   7   8   9   10  11  12  13");


        System.out.println("Player " + playerOne.getName() + "   (X) game pieces: " + playerOne.getPieces());
        System.out.println("Player " + playerTwo.getName() + "   (O) game pieces: " + playerTwo.getPieces());

    }

    public boolean isAvailable(String piece, String currentPlayer) {
        if (currentPlayer.equals(playerOne.getName())) {
            return playerOne.getPieces().contains(piece);
        } else return playerTwo.getPieces().contains(piece);
    }

    //------------------------- Rotate / Flip / Place / IsLegal Implementation 20358446
    public static void askToRotate(char[][] tempBoard,char place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("%2c", tempBoard[i][j]);
            }
            System.out.println();
        }

        System.out.println("Enter 'r' to rotate, 'f' to flip, or 'p' to place the piece");
        Scanner userInput = new Scanner(System.in);
        String orientation = userInput.nextLine();
        if (orientation.equals("r")) {
            rotate(tempBoard,place);
        }
        if (orientation.equals("f")) {
            flip(tempBoard,place);
        }
        if (orientation.equals("p")) {
            place(tempBoard, board,place);
        }

    }

    public static void rotate(char[][] tempBoard,char place) {
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                char temp = tempBoard[i][j];
                tempBoard[i][j] = tempBoard[j][i];
                tempBoard[j][i] = temp;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5 / 2; j++) {
                char temp = tempBoard[i][j];
                tempBoard[i][j] = tempBoard[i][5 - 1 - j];
                tempBoard[i][5 - 1 - j] = temp;
            }
        }


        askToRotate(tempBoard,place);
    }
    public static boolean isLegal(char[][] legalBoard,char place){

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(legalBoard[i][j] == '%'){
                    if(legalBoard[i+1][j]!=(place)&&legalBoard[i-1][j]!=place&&legalBoard[i][j+1]!=place&&legalBoard[i][j-1]!=place){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void place(char[][] tempBoard, char[][] board,char place) {
        char[][] legalBoard = new char[5][5];
        Scanner x = new Scanner(System.in);
        Scanner y = new Scanner(System.in);
        Integer x_cord, y_cord;
        do{
            System.out.println("Please enter the X co-ordinate");
            x_cord = x.nextInt();
        }while(x_cord < 0 || x_cord > 14);
        do{
            System.out.println("Please enter the Y co-ordinate");
            y_cord = y.nextInt();
        }while(y_cord < 0 || y_cord > 14);

        int a, b;
        for (int i = -2; i <= 2; i++) {
            a = i + 2;
            for (int j = -2; j <= 2; j++) {
                b = j + 2;
                if (tempBoard[a][b] == place) {legalBoard[a][b] = '%';}
                else {legalBoard[a][b] = board[x_cord + i][y_cord + j];}
            }
        }
        char oppositePlace;
        if(place =='X'){
            oppositePlace = 'O';
        }else{
            oppositePlace = 'X';
        }
        for (int i = -2; i <= 2; i++) {
            a = i + 2;
            for (int j = -2; j <= 2; j++) {
                b = j + 2;
                if(board[x_cord + i][y_cord + j] == oppositePlace &&tempBoard[a][b]== place){
                    System.out.println("Invalid Move Try Again!");

                    printBoard(board);
                    place(tempBoard,board,place);
                }
            }
        }


if(isLegal(legalBoard,place)) {

    for (int i = -2; i <= 2; i++) {
        a = i + 2;
        for (int j = -2; j <= 2; j++) {
            b = j + 2;
            if (tempBoard[a][b] == place) {
                board[x_cord + i][y_cord + j] = tempBoard[a][b];
            }
        }
    }

}
else{
    System.out.println("Move is Invalid, try again!");
    printBoard(board);
    place(tempBoard,board,place);
}
    }

    public static void flip(char[][] tempBoard,char place) {

        char[][] result = new char[tempBoard.length][tempBoard[0].length];

        for (int i = 0; i < tempBoard.length; i++) {
            for (int y = 0; y < tempBoard[0].length; y++) {
                result[i][y] = tempBoard[tempBoard.length - i - 1][y];
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int y = 0; y < 5; y++) {
                tempBoard[i][y] = result[i][y];
            }
        }
        askToRotate(tempBoard,place);
    }


    public static void makeMove(String piece, char place) {
        char[][] tempBoard = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tempBoard[i][j] = ' ';
            }
        }
        //-------------------- I ------------------

        if (piece.equals("I1")) {
            tempBoard[2][2] = place;
            askToRotate(tempBoard,place);
        }
        if (piece.equals("I2")) {
            tempBoard[2][2] = place;
            tempBoard[1][2] = place;
            askToRotate(tempBoard,place);
        }
        if (piece.equals("I3")) {
            tempBoard[2][2] = place;
            tempBoard[1][2] = place;
            tempBoard[0][2] = place;
            askToRotate(tempBoard,place);
        }
        if (piece.equals("I4")) {
            tempBoard[3][2] = place;
            tempBoard[2][2] = place;
            tempBoard[1][2] = place;
            tempBoard[0][2] = place;
            askToRotate(tempBoard,place);

        }
        if (piece.equals("I5")) {
            tempBoard[4][2] = place;
            tempBoard[3][2] = place;
            tempBoard[2][2] = place;
            tempBoard[1][2] = place;
            tempBoard[0][2] = place;
            askToRotate(tempBoard,place);

        }
        //-------------------- V ------------------

        if (piece.equals("V3")) {
            tempBoard[2][2] = place;
            tempBoard[2][1] = place;
            tempBoard[1][2] = place;
            askToRotate(tempBoard,place);

        }
        if (piece.equals("V5")) {
            tempBoard[2][2] = place;
            tempBoard[2][3] = place;
            tempBoard[2][4] = place;
            tempBoard[1][2] = place;
            tempBoard[0][2] = place;
            askToRotate(tempBoard,place);

        }
        //-------------------- L ------------------
        if (piece.equals("L4")) {
            tempBoard[2][2] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2 - 1][2] = place;
            tempBoard[2 - 2][2] = place;
            askToRotate(tempBoard,place);

        }

        if (piece.equals("L5")) {
            tempBoard[2][1] = place;
            tempBoard[2][2] = place;
            tempBoard[1 ][1] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2][2 + 2] = place;
            askToRotate(tempBoard,place);

        }
        //-------------------- O ------------------


        if (piece.equals("O4")) {
            tempBoard[2][2] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2 + 1][2] = place;
            tempBoard[2 + 1][2 + 1] = place;
            askToRotate(tempBoard,place);

        }
        //-------------------- Z ------------------
        if (piece.equals("Z4")) {
            tempBoard[2][2] = place;
            tempBoard[2 - 1][2] = place;
            tempBoard[2 - 1][2 + 1] = place;
            tempBoard[2][2 - 1] = place;
            askToRotate(tempBoard,place);


        }
        if (piece.equals("Z5")) {
            tempBoard[2][2] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2][2 - 1] = place;
            tempBoard[2 - 1][2 + 1] = place;
            tempBoard[2 + 1][2 - 1] = place;
            askToRotate(tempBoard,place);

        }
        //-------------------- T ------------------


        if (piece.equals("T5")) {
            tempBoard[2][2] = place;
            tempBoard[2 - 1][2] = place;
            tempBoard[2 - 2][2] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2][2 - 1] = place;
            askToRotate(tempBoard,place);


        }
        if (piece.equals("T4")) {
            tempBoard[2][2] = place;
            tempBoard[2 - 1][2] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2][2 - 1] = place;
            askToRotate(tempBoard,place);


        }
        //-------------------- Misc. ------------------

        if (piece.equals("N")) {
            tempBoard[2][2] = place;
            tempBoard[2 + 1][2] = place;
            tempBoard[2 + 1][2 - 1] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2][2 + 2] = place;
            askToRotate(tempBoard,place);

        }
        if (piece.equals("P")) {
            tempBoard[2][2] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2 + 1][2] = place;
            tempBoard[2 + 1][2 + 1] = place;
            tempBoard[2 + 2][2] = place;
            askToRotate(tempBoard,place);

        }
        if (piece.equals("W")) {
            tempBoard[2][2] = place;
            tempBoard[2 - 1][2] = place;
            tempBoard[2 - 1][2 + 1] = place;
            tempBoard[2][2 - 1] = place;
            tempBoard[2 + 1][2 - 1] = place;
            askToRotate(tempBoard,place);
        }
        if (piece.equals("U")) {
            tempBoard[2][2] = place;
            tempBoard[2 - 1][2] = place;
            tempBoard[2 + 1][2] = place;
            tempBoard[2 - 1][2 + 1] = place;
            tempBoard[2 + 1][2 + 1] = place;
            askToRotate(tempBoard,place);

        }
        if (piece.equals("F")) {
            tempBoard[2][2] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2 + 1][2] = place;
            tempBoard[2 + 1][2 - 1] = place;
            tempBoard[2 + 2][2] = place;
            askToRotate(tempBoard,place);
        }

        if (piece.equals("X")) {
            tempBoard[2][2] = place;
            tempBoard[2 + 1][2] = place;
            tempBoard[2 - 1][2] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2][2 - 1] = place;
            askToRotate(tempBoard,place);

        }
        if (piece.equals("Y")) {
            tempBoard[2][2] = place;
            tempBoard[2][2 - 1] = place;
            tempBoard[2 - 1][2] = place;
            tempBoard[2][2 + 1] = place;
            tempBoard[2][2 + 2] = place;
            askToRotate(tempBoard,place);

        }
    }


    public static void main(String[] args) {

        char[][] board = new char[14][14];
        game a = new game(board);
        startGame();
        initboard(board);
        printBoard(board);
        boolean gameOver = false;

        while (!gameOver) {
            if (currentPlayer.equals(playerOne.getName())) {
                String piece = "";
                getPiece:
                {
                    System.out.println("\nPlayer: " + currentPlayer + ", Which piece would you like to use?");

                    Scanner input = new Scanner(System.in);
                    piece = input.nextLine();
                    if (!(a.isAvailable(piece, currentPlayer))) {
                        System.out.println("Piece is not available, Please try again!");
                        break getPiece;
                    }
                    makeMove(piece, 'X');
                }
                playerOne.getPieces().remove(piece);
                printBoard(board);
                setCurrentPlayer(playerTwo.getName());
            }

            if (currentPlayer.equals(playerTwo.getName())) {
                getPiece:
                {
                    System.out.println("\nPlayer: " + currentPlayer + ", Which piece would you like to use?");
                    Scanner input = new Scanner(System.in);
                    String piece = input.nextLine();
                    if (!(a.isAvailable(piece, currentPlayer))) {
                        System.out.println("Piece is not available, Please try again!");
                        break getPiece;
                    }
                    makeMove(piece, 'O');
                    playerTwo.getPieces().remove(piece);
                    printBoard(board);
                    setCurrentPlayer(playerOne.getName());
                }
            }
        }
    }
}



