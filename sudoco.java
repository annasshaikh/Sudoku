
import java.util.Scanner;

import javax.swing.SpringLayout;

public class sudoco {

    //Source: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
  
    // Declaring the color
    // Custom declaration
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] board = {
            {5,3,0, 0,7,0, 0,0,0},
            {6,0,0, 1,9,5, 0,0,0},
            {0,9,8, 0,0,0, 0,6,0},

            {8,0,0, 0,6,0, 0,0,3}, 
            {4,0,0, 8,0,3, 0,0,1},
            {7,0,0, 0,2,0, 0,0,6},

            {0,6,0, 0,0,0, 0,0,0},
            {0,0,0, 4,1,9, 0,0,5},
            {0,0,0, 0,8,0, 0,7,9}
        };        

        int[][] iniboard = new int[9][9]; 
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                iniboard[r][c] = board[r][c];
        
        while (!is_solved(board)){
            print(board, iniboard);
            int index;
            do{
                System.out.print("Input The Place(" + ANSI_YELLOW +"Row"+ ANSI_GREEN + "Coloum "+ ANSI_RESET + ") : ");
                index = input.nextInt();
            }while(!is_correct_index(iniboard, index(index)));
            
            int value;
            do{
                System.out.print("Enter Digit: ");
                value = input.nextInt();
            }while(!is_correct_value(board, index(index), value));
            int r = index(index)[0];
            int c = index(index)[1];
            board[r][c] = value;
            System.out.println();
        }
        //System.out.println(is_correct_value(board, index(3) , 7));
    }

    public static void print(int board[][], int iniboard[][]){
        System.out.println(ANSI_GREEN + "    0  1  2     3  4  5     6  7  8" + ANSI_RESET);
        System.out.println("  ------------------------------------");

        for (int i = 0; i < 9; i++){
            System.out.print(ANSI_YELLOW + i + ANSI_RESET+ " "  );
            System.out.print("|");
            for (int j = 0; j < 9; j++){
                //Red For Initisal System , Blue For User Input
                String colour =  ( (iniboard[i][j] != 0) ? ANSI_RED : ((board[i][j] != 0) ? ANSI_BLUE : "") ) ;
                System.out.print(" " + colour + board[i][j]+ ANSI_RESET + " ");
                if (j % 3 == 2)
                    System.out.print (" | ");
            }
            System.out.println("\b");
            if (i % 3 == 2)
                System.out.println("  ------------------------------------");
            
        }

        
    }
    
    public static boolean is_correct_value(int[][] board, int[] index, int value){
        if (value > 9 || value < 1){
            System.out.println("The Value Can be Only Between 1 - 9");
            return false;
        }
        int r = index[0];
        int c = index[1];      
        for (int i = 0; i < 9; i++){
            if (board[r][i] == value ||  board[i][c] == value){
                System.out.println("Same Value Exsist In Colums or Row");
                return false;
            }
        }
        int center = box_centernum(r*10 + c);
        for (int i = -10; i < 20; i+=10){
            for (int j = -1; j < 2; j++){
                int x = center + i + j;
                int cr = index(x)[0];
                int cc = index(x)[1];
                if (board[cr][cc] == value){
                    //System.out.println("r: "+ cr +" C: " + cc + "center: " + center+ " value: " + value + " x: " + x);        
                    System.out.println("Same Value Exsist In the Box");
                    return false;
                }       
            }
        }
        return true; 
    }
    
    public static boolean is_correct_index(int[][] board, int[] index){
        if (index[0] > 8 || index[1] > 8){
            System.out.println("Index is not On the Board");
            return false;
        }
        if (board[index[0]][index[1]] != 0){
            System.out.println("The Value At Index Was Initize By System");
            System.out.println("You can only edit Which where 0 In Start");
            return false;
        }
        return true;
    }
    //Return Center Box Num.
    public static int box_centernum(int index){
        // If first row
        if (index < 3)
            return 11;
        if (index < 6)
            return 14;
        if (index < 9)
            return 17;
        // If First Coloum
        if (index % 10 == 0){
            if (index  < 30)
                return 11;
            else if (index < 60)
                return 42;
            else 
                return 72;
        }
        // Scan the adjecent Numbers
        for (int i = -10; i < 20; i+=10){
            for (int j = -1; j < 2; j++){
                int x = index + i + j;
                int r = index(x)[0];
                int c = index(x)[1];
                // If center box found return it.
                if (r % 3 == 1 && c % 3 == 1)
                    return x;        
            }
        }
        return -1;
    }
    
    public static int[] index(int index){
        int[] arr = new int[2];
        arr[1] = (index % 10) ;
        arr[0] = (index / 10) ;
        return arr;  
    }

    public static boolean is_solved(int[][] board){
        for (int[] x: board){
            for (int y: x){
                if (y == 0){
                    return false;
                }
            }
        }
        return true;
    }

}
