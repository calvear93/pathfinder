/** Clase Randomizer (Aleatorizador) que retorna variables y estructuras con valores aleatorios.
 * @author Cristopher Alvear Candia.
 * @version 1.1.8 para PathFinder y SubsetSum.
 */
class Randomizer {
	// CONSTRUCTOR.
	/** Constructor privado (Clase util, estatica). */
	private Randomizer() {}
	
	/** Metodo que retorna una matriz laberinto (para PathFinder) de 0 o -1.
	 * @param n : int - Numero de filas de la matriz laberinto.
	 * @param m : int - Numero de columnas de la matriz laberinto.
	 * @return int[][] - Matriz de mxn. matrix[i][j] = { 0, -1 } { casilla libre, casilla bloqueada }.
	 */
	static int[][] randomLabyrinthMatrix( int n, int m ) {
		int[][] matrix = new int[n][m];
		for ( int i = 0; i < n; i++ )
			for( int j = 0; j < m; j++ )
				matrix[i][j] = randomInt( 0, 1 ) - 1;
		return matrix;
	}
	
	/** Metodo que retorna una matriz laberinto (para PathFinder) de 0 o -1.
	 * @param n : int - Numero de filas de la matriz laberinto.
	 * @param m : int - Numero de columnas de la matriz laberinto.
	 * @return int[][] - Matriz de mxn. matrix[i][j] = { 0, -1 } { casilla libre, casilla bloqueada }.
	 */
	static int[][] randomLabyrinthMatrixWithOpenPath( int n, int m ) {
		int[][] matrix = randomLabyrinthMatrix( n, m );
		openPath( matrix, n, m, 0, 0 );
		return matrix;
	}
	
	/** Metodo que abre un camino aleatorio para una matriz laberinto (puede que no lo abra).
	 * @param matrix : int[][] - Matriz laberinto.
	 * @param n : int - Numero de filas de la matriz (matrix.length o longitud vertical deseada menor).
	 * @param m : int - Numero de columnas de la matriz (matrix[i].length o longitud horizontal deseada menor).
	 * @param i : int - Indice de fila de la primera casilla (0 o inicio deseado).
	 * @param j : int - Indice de columna de la primera casilla (0 o inicio deseado).
	 */
	private static void openPath( int[][] matrix, int n, int m, int i, int j ) {
		matrix[i][j] = 0;
		if ( ( i == n - 1 ) && ( j == m - 1 ) ) return;
		int ni, nj;
		ni = i + Randomizer.randomInt( 0, 1 ) - Randomizer.randomInt( 0, 1 );
		nj = j + Randomizer.randomInt( 0, 1 );
		while ( ( ni < 0 || nj < 0 ) || ( ni > n - 1 || nj > m - 1 ) ) {
			ni = i + Randomizer.randomInt( 0, 1 ) - Randomizer.randomInt( 0, 1 );
			nj = j + Randomizer.randomInt( 0, 1 );
		}
		openPath( matrix, n, m, ni, nj );
	}
	
	/** Metodo que retorna un entero aleatorio dentro de un rango definido. 
	 * @param a : int - Limite izquierdo/inferior inclusivo del rango.
	 * @param b : int - Limite derecho/superior inclusivo del rango.
	 * @return int - Numero aleatorio dentro del rango [a,b].
	 */
	static int randomInt( int a, int b ) {
		 return ( int ) ( Math.random() * ( b - a + 1 ) + a );
	}
}