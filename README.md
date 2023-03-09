# Sudoku
A command-line Java program that lets you play Sudoku. It allows you to enter a digit at a position and solves the Sudoku puzzle by iteratively updating the game board.

The game board is printed in the console with different colors for initial system entries and user inputs. When the game is solved, it prints the solved board.

### Requirements
Java 8 or later.
#### How to Run
- Clone the repository or download the source code.
- Navigate to the project directory.
Compile the program by running the command javac sudoco.java.
- Run the program by running the command java sudoco.  

### How to Play
- The game board is displayed in the console, with positions numbered 0-8 for each row and column.
- To enter a digit, input the position where you want to enter the digit in the format "row column". For example, to enter a digit in the first row and second column, type "0 1" and press Enter.
- When prompted, enter the digit you want to place in the selected position.
- If the digit you entered is not valid (i.e., outside the range of 1-9), you will be prompted to enter the digit again.
- Continue entering digits until the game is solved.

### Implementation Details
The game board is implemented as a two-dimensional array of integers. The program iteratively updates the board until the game is solved.

The ANSI codes for colors are used to print the game board in different colors.

The program includes the following functions:

__main__: The main function that runs the game loop.
__print__: Prints the game board in the console.
is_solved: Returns true if the game is solved.
__is_correct_index__: Returns true if the given index is valid for the game board.
__is_correct_value__: Returns true if the given value is valid for the given index.
