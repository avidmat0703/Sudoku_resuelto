import java.util.Random;

public class Sudoku {
    private int gridSize = 9;
    private int numClues = 30;
    private int[][] board;

    public int getNumClues() {
        return numClues;
    }

    public void setNumClues(int numClues) {
        this.numClues = numClues;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    // Método para llenar el tablero con un Sudoku válido
    public void fillBoardRandomly() {
        board = new int[gridSize][gridSize];
        fillBoard(0, 0);
        removeNumbersToMatchClues();
    }

    // Método para llenar el tablero de manera válida
    private boolean fillBoard(int row, int col) {
        if (row == gridSize) {
            return true; // Tablero completo
        }
        if (col == gridSize) {
            return fillBoard(row + 1, 0); // Siguiente fila
        }
        Random random = new Random();
        int[] numbers = random.ints(1, gridSize + 1).distinct().limit(gridSize).toArray();
        for (int num : numbers) {
            if (isValidPlacement(num, row, col)) {
                board[row][col] = num;
                if (fillBoard(row, col + 1)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false; // No se puede colocar un número válido en esta posición
    }

    // Método para eliminar números del tablero hasta que queden solo numClues pistas
    private void removeNumbersToMatchClues() {
        int cellsToRemove = gridSize * gridSize - numClues;
        Random random = new Random();
        while (cellsToRemove > 0) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                cellsToRemove--;
            }
        }
    }

    // Métodos auxiliares para verificar las reglas del Sudoku
    //Método para comprobar que existe ese número en la fila
    public boolean isNumberInRow(int number, int row) {
        for (int i = 0; i < gridSize; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    //Método para comprobar que existe ese número en la columna
    public boolean isNumberInColumn(int number, int column) {
        for (int i = 0; i < gridSize; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    //Método para comprobar que existe ese número en la caja
    public boolean isNumberInBox(int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    //Método para comprobar que es un lugar válido para colocar el número
    public boolean isValidPlacement(int number, int row, int column) {
        return !isNumberInRow(number, row) &&
                !isNumberInColumn(number, column) &&
                !isNumberInBox(number, row, column);
    }

    //Método para intentar resolver el sudoku
    public boolean solveBoard() {
        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= gridSize; numberToTry++) {
                        if (isValidPlacement(numberToTry, row, column)) {
                            board[row][column] = numberToTry;
                            if (solveBoard()) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    //Metodo para pintar el tablero
    public void printBoard() {
        for (int row = 0; row < gridSize; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < gridSize; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column] == 0 ? "." : board[row][column]);
            }
            System.out.println();
        }
    }
}