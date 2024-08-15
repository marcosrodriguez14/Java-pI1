
package tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ejercicios.Ejercicio4;
import us.lsi.common.Files2;

public class TestEjercicio4 {

	public static void main(String[] args) {
		String file = "ficheros/testsAlumnos/PI1Ej4DatosEntrada.txt";
		List<Tuple> ls = Files2.streamFromFile(file)
				.map(x -> Tuple
						.parse(x))
				.collect(Collectors.toList());

		System.out.println(
				"**********************************************************************************************************************************************************************************************");
		System.out.println("Entrada: " + ls);
		System.out.println(
				"**********************************************************************************************************************************************************************************************"
						+ "\n");

		for (Tuple ex : ls) {
			System.out.println(
					"Recursivo SIN MEMORIA " + ex + " ==> " + Ejercicio4.recursivoSinMemoria(ex.a, ex.b,ex.c));
			System.out.println(
					"Recursivo  CON MEMORIA " + ex + "==> " + Ejercicio4.recursivaConMemoria(ex.a, ex.b, ex.c));
			System.out.println(
					"             ITERATIVO " + ex + "==> " + Ejercicio4.Iterativo(ex.a, ex.b, ex.c)+"\n");
		}
	}

	public record Tuple(Integer a, Integer b, Integer c) {

		public static Tuple of(Integer a, Integer b, Integer c) {
			return new Tuple(a, b, c);
		}

		public static Tuple parse(String st) {
			List<Integer> par = Arrays.stream(st.split(","))
					.map(x -> Integer.parseInt(x.trim()))
					.collect(Collectors.toList());
			return of(par.get(0), par.get(1), par.get(2));
		}

		public String toString() {
			return String.format("(%d, %d , %d)", a, b, c);
		}
	}
}
