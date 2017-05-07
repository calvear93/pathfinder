import java.util.Arrays;
import java.util.LinkedList;

/** Clase SubsetSum (Subconjunto que suma) que encuentra los subconjuntos que suman M desde un conjunto (array) W.
 * Los elementos de W son positivos, y W esta ordenada.
 * @author Cristopher Alvear.
 * @version 1.9
 */
class SubsetSum {
	/** Constructor privado. */
	private SubsetSum() {}
	
	/** Metodo envoltorio del algoritmo que busca encuentra los subonjuntos que sumen M en un conjunto W.
	 * Precondicion : w <> null.
	 * @param w : int[] - Conjunto (array) de elementos. w[i] = x, x e N.
	 * @param m : int - Suma (total que deben sumar los elementos del conjunto).
	 * @return LinkedList<int[]> - Lista de los subonjuntos encontrados.
	 */ 
	static LinkedList<int[]> getSubsets( int[] w, int m ) {
		LinkedList<int[]> list = new LinkedList<>();
		for( int k = binarySearch( m, w, 0, w.length - 1 ); k > -1; k-- )
			subsetsToList( w, new int[] {}, m, 0, k, list );
		return list;
	}
	
	/** Algoritmo que guarda los subconjunto desde un conjunto W que suman M en una lista. 
	 * Precondicion : w, subset, list <> null; i = 0; k = w.length - 1.
	 * @param w : int[] - Conjunto (array) de elementos. w[i] = x, x e N.
	 * @param subset : int[] - Array vacio que almacenara un subconjunto.
	 * @param m : int - Suma (total que deben sumar los elementos del conjunto).
	 * @param i : int - Indice del primer elemento de w (0).
	 * @param k : int - Indice del ultimo elemento de w (w.length - 1).
	 * @param list : LinkedList<int[]> - Lista que almacenara los subconjuntos encontrados.
	 */
	private static void subsetsToList( int[] w, int[] subset , int m, int i, int k, LinkedList<int[]> list ) {
		if ( k < 0 ) return; // Si no existe, termina el metodo.
		subset = concatenateArrays( subset, new int[] { w[k] } ); // Concatena el elemento al subconjunto.
		// Si el elemento encontrado es exactamente m, lo agrega como subconjunto, y sigue buscando mas.
		if ( sum( subset ) == m ) list.add( subset );
		while ( k > -1 ) // Se bucan las combinaciones restantes.
			subsetsToList( w, Arrays.copyOf( subset, subset.length ), m, i, --k, list );
	}
	
	/** Metodo que retorna la suma de los elementos de un arreglo de enteros.
	 * Precondicion : w <> null.
	 * @param w : int[] - Conjunto (array) de elementos enteros.
	 * @return int - Suma de los elementos de w.
	 */ 
	private static int sum( int[] w ) {
		int sum = 0; // Variable auxiliar que contendra el total.
		for( int n : w )
			sum += n; // Se suma cada elemento en w.
		return sum;
	}
	
	/** Metodo que concatena (union) 2 arrays de enteros. 
	 * @param array1 : int[] - Primer arreglo de enteros.
	 * @param array2 : int[] - Segundo arreglo de enteros.
	 * @return int[] - Arreglo union de los 2 arreglos argumentos.
	 */
	private static int[] concatenateArrays( int[] array1, int[] array2 ) {
		int i = 0; // Indice que permite avanzar en el arreglo union.
		// Se crea el arreglo con longitud igual a la suma de las 2 longitudes de los arreglos argumento.
		int[] newArray = new int[ array1.length + array2.length ];
		// Se traspasan los elementos del primer arreglo.
		for ( int n : array1 )
			newArray[i++] = n;
		// Se traspasan los elementos del segundo arreglo.
		for ( int n : array2 )
			newArray[i++] = n;
		return newArray;
	}
	
	/** Busqueda binaria especial, en un arreglo ordenado.
	 * * Es especial debido a que si no encuentra el elemento en la posicion
	 * calculada, retorna la posicion del elemento menor.
	 * @param x : int - Elemento buscado.
	 * @param array : int[] - Arreglo en el cual buscar.
	 * @param i : int - Indice del primer elemento del arreglo (0).
	 * @param k : int - Indice del ultimo elemento del arreglo (array.length - 1).
	 * @return int - posicion en array del elemento buscado, o el menor siguiente. -1 si no existe ninguno.
	 */
	private static int binarySearch( int x, int[] array, int i, int k ) {
		int pivot = i + ( k - i ) / 2; // Pivote o indice donde se buscara el elemento, la mitad del arreglo.
		// Si la longitud es vacia, retorna la posicion - 1 (trata de retornar el menor al buscado).
		if ( ( k - i ) < 0 ) return pivot - 1;
		// Si se encuentra el elemento, se retorna el pivot o la posicion actual.
		if ( array[ pivot ] == x ) return pivot;
		// Busca por el lado izquierdo.
		if ( array[ pivot ] > x )
			return binarySearch(x, array, i, pivot - 1 );
		// Busca por el lado derecho.
		return binarySearch( x, array, pivot + 1, k );
	}	
}