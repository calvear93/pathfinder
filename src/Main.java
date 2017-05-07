import java.util.Arrays;
import java.util.LinkedList;

/** Clase Main.
 * @author Cristopher Alvear.
 * @version 1.0
 */

class Main {
	/* Metodo de ejecucion principal */
	public static void main( String... args ) {
		System.out.println( " \t ==| PATHFINDER |==\n");
		runPathFinder( 2, 4, 8 );
		System.out.println( " \t==| SUBSET SUM |==\n");
		runSubsetSum( 4 );
		System.out.println( " \t [i] FIN DEL PROGRAMA\n");
	}
	
	/* Metodo que ejecuta el PathFinder */
	private static void runPathFinder( int samples, int min, int max ) {
		int[][] matrix, solvedMatrix;
		while ( samples-- > 0 ) {
			matrix = Randomizer.randomLabyrinthMatrix( Randomizer.randomInt( min, max ), Randomizer.randomInt( min, max ) );
			solvedMatrix = copyOfMatrix( matrix, matrix.length, matrix[0].length );
			System.out.printf( "%" + ( 3 * matrix[0].length ) + "s %" + ( 3 * matrix[0].length + 10 ) + "s\n", "Matriz Aleatoria", "Matriz Recorrida" );
			PathFinder.findAPath( solvedMatrix );
			printTwoMatrix( matrix, solvedMatrix, matrix.length );
			
			matrix = Randomizer.randomLabyrinthMatrixWithOpenPath( Randomizer.randomInt( min, max ), Randomizer.randomInt( min, max ) );
			solvedMatrix = copyOfMatrix( matrix, matrix.length, matrix[0].length );
			System.out.printf( "%" + ( 3 * matrix[0].length ) + "s %" + ( 3 * matrix[0].length + 10 ) + "s\n", "Matriz Aleatoria", "Matriz Recorrida" );
			PathFinder.findAPath( solvedMatrix );
			printTwoMatrix( matrix, solvedMatrix, matrix.length );
		}
		System.out.println();
	}
	
	/* Metodo que ejecuta el SubsetSum */
	private static void runSubsetSum( int samples ) {
		testSubsetSum( 15, 2, 3, 5, 10, 20 );
		testSubsetSum( 10, 2, 3, 5, 10, 20 );
		testSubsetSum( 10, 1, 2, 3, 5, 9, 10, 20 );
		testSubsetSum( 127, 2, 3, 5, 10, 20, 40, 41, 42, 80 );
		testSubsetSum( 160, 2, 3, 5, 10, 20, 40, 41, 42, 80 );
		testSubsetSum( 560, 2, 3, 5, 10, 20, 40, 41, 42, 80 );
	}
	
	/* Testea e imprime lso resultados de los subconjuntos de SubsetSum */
	private static void testSubsetSum( int m, int... w ) {
		System.out.println( "M : " + m );
		System.out.print( "W : " );
		printVector( w );			
		System.out.println();
		System.out.print( "Subsets : " );
		printSubsets( SubsetSum.getSubsets( w, m ) );
		System.out.println();
	}
	
	// Crea una copia en memoria de una matriz
	private static int[][] copyOfMatrix( int[][] matrix, int n, int m ) {
		int[][] newMatrix = new int[n][];
		for( int i = 0; i < n; i++ )
			newMatrix[i] = Arrays.copyOf( matrix[i], m );
		return newMatrix;
	}
	
	/* Imprime un vector */
	private static void printVector( int[] vector ) {
		System.out.print( "( ");
		for( int e : vector )
			System.out.print( e + " " );
		System.out.print( ")" );
	}
	
	/* Imprime todos los vectores (subconjuntos) de una lista. */
	private static void printSubsets( LinkedList<int[]> list ) {
		if ( list.isEmpty() ) { System.out.println( "{ No existen subconjuntos }"); return; }
		System.out.print( "{ ");
		for ( int[] subset : list )
			{ printVector( subset ); System.out.print( " " ); }
		System.out.println( "}" );
	}
	
	/* Imprime dos matrices laberinto para compararlas */
	private static void printTwoMatrix( int[][] matrix1, int[][] matrix2, int n ) {
		for ( int i = 0; i < n; i++ )
			printRows( matrix1[i], matrix2[i] );
		System.out.println();
	}
	
	/* Imprime dos filas (de dos matrices) una al lado de otra */
	private static void printRows( int[] row1, int[] row2 ) {
		for ( int e : row1 )
			System.out.printf( "%3d ", e );
		System.out.print( "     " );
		for ( int e : row2 )
			System.out.printf( "%3d ", e );
		System.out.println();
	}
}