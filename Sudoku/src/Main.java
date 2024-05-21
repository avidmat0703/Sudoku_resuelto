public class Main {
    public static void main(String[] args) {
        Sudoku sudokuSolver = new Sudoku();

        // Establecer el tamaño del tablero y el número de pistas
        sudokuSolver.setGridSize(9);
        sudokuSolver.setNumClues(30);

        // Llenar el tablero con un Sudoku válido y con las pistas que hemos puesto antes
        sudokuSolver.fillBoardRandomly();

        // Imprimir el tablero generado con pistas
        System.out.println("Tablero inicial:");
        sudokuSolver.printBoard();

        // Intentar resolver el tablero
        System.out.println("Pensando.....");
        if (sudokuSolver.solveBoard()) {
            System.out.println("¡Resuelto con éxito!");
        }
        else {
            System.out.println("Tablero no resolvible :(");
        }

        // Imprimir el tablero después de intentar resolverlo
        System.out.println("Tablero final:");
        sudokuSolver.printBoard();
    }
}