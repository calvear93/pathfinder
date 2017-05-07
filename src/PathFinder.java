
/** Clase PathFinder (Buscador de camino) que busca un camino inmediato dentro de una matriz nxm que representa un laberinto.
 * Los elementos de laa matriz pueden tomar los valores : { 0, -1 } = { casilla libre, casilla bloqueada }.
 * ==============================================================================================================
 * El algoritmo de busqueda de un camino, consiste en posicionarse en la casilla (0,0), luego tomar una de las
 * 4 direcciones { abajo, derecha, arriba, izquierda } en ese orden, marcando la nueva casilla con el paso k
 * correspondiente, verificar si existe casilla transitable, si no, lo desmarca y vuelve a la casilla anterior.
 * Para cada casilla, mientras avanza, verifica, en el orden ya mencionado, si existe una solucion.
 * Cabe destacar que el algoritmo no es exhaustivo, ya que se queda con la primera solucion que encuentre.
 * ==============================================================================================================
 * @author Cristopher Alvear.
 * @version 1.1
 */
class PathFinder {
	/** Constructor privado. */
	private PathFinder() {}
	
	/** Metodo envoltorio del algoritmo que busca un camino inmediato para una matriz.
	 * Precondicion : matrix <> null.
	 * @param matrix : int[][] - Matriz de mxn. matrix[i][j] = { 0, -1 } { casilla libre, casilla bloqueada }.
	 */ 
	static void findAPath( int[][] matrix ) {
		if ( findAPath( matrix, matrix.length, matrix[0].length, 0, 0, 1 ) < 0 )
			System.out.println( " [!] No existe camino." );
		else
			System.out.println( " [i] Camino encontrado satisfactoriamente." );
	}
	
	/** Algoritmo que busca un camino inmediato para una matriz. 
	 * Precondicion : matrix <> null; k = 1; n = filas, n = columnas; i, j = 0. - Para busqueda en la matriz completa.
	 * @param matrix : int[][] - Matriz de mxn. matrix[i][j] = { 0, -1 } { casilla libre, casilla bloqueada }.
	 * @param n : int - Numero de filas de la matriz (matrix.length o longitud vertical deseada menor).
	 * @param m : int - Numero de columnas de la matriz (matrix[i].length o longitud horizontal deseada menor).
	 * @param i : int - Indice de fila de la primera casilla (0 o inicio deseado).
	 * @param j : int - Indice de columna de la primera casilla (0 o inicio deseado).
	 * @param k : int - Numero de paso (1).
	 * @return int - Retorna k, el numero de pasos hasta el final, o -1 si no hay camino.
	 */
	private static int findAPath( int[][] matrix, int n, int m, int i, int j, int k ) {
		// Verifica si la casilla es transitable, si no, retorna -1.
		if ( !isWalkable( i, j, n, m ) || matrix[i][j] != 0 ) return -1;
		// Marca la casilla con el paso k correspondiente.
		matrix[i][j] = k;
		// Si es la ultima casilla, finaliza y retorna k.
		if ( isLastBox( i, j, n, m ) ) return k;
		int right, left, up, down;
		// Calcula todos los posibles caminos, comenzando por el inferior, luego derecho, superior y finalmente izquierdo.
		down = findAPath( matrix, n, m, i + 1, j, k + 1 );
		right =  findAPath( matrix, n, m, i, j + 1, k + 1 );
		up =  findAPath( matrix, n, m, i - 1, j, k + 1 );
		left = findAPath( matrix, n, m, i, j - 1, k + 1 );
		// Si es un "Dead End" o callejon sin salida, desmarca la casilla y retorna -1.
		if ( down < 0 && right < 0 && up < 0 && left < 0 ) { matrix[i][j] = 0; return -1; }
		return 0;
	}
	
	/** Metodo que verifica si los indices i,j pertenencen a la ultima casilla de una matriz nxm. 
	 * @param n : int - Numero de filas de la matriz (matrix.length).
	 * @param m : int - Numero de columnas de la matriz (matrix[i].length).
	 * @param i : int - Indice de fila de la primera casilla (0).
	 * @param j : int - Indice de columna de la primera casilla (0).
	 * @return boolean - Retorna True si es la ultima casilla, False en caso contrario.
	 */
	private static boolean isLastBox( int i, int j, int n, int m ) {
		return ( i == n - 1 ) && ( j == m - 1 );
	}
	
	/** Metodo que verifica si los indices i,j pertenecen a una casilla transitable de una matriz nxm. 
	 * @param n : int - Numero de filas de la matriz (matrix.length).
	 * @param m : int - Numero de columnas de la matriz (matrix[i].length).
	 * @param i : int - Indice de fila de la primera casilla (0).
	 * @param j : int - Indice de columna de la primera casilla (0).
	 * @return boolean - Retorna True si la casilla es transitable, False en caso contrario.
	 */
	private static boolean isWalkable( int i, int j, int n, int m ) {
		return ( i > -1 && j > -1 ) && ( i < n && j < m );
	}
}