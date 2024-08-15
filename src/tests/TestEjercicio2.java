package tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ejercicios.Ejercicio2;
import us.lsi.common.Files2;

public class TestEjercicio2 {

	public static void main(String[] args) {
		String file = "ficheros/testsAlumnos/PI1Ej2DatosEntrada.txt";
		List<Tuple> ls = Files2.streamFromFile(file).map(x -> Tuple.parse(x)).collect(Collectors.toList());

		System.out.println(
				"**********************************************************************************************************************************************************************************************");
		System.out.println("Entrada: " + ls);
		System.out.println(
				"**********************************************************************************************************************************************************************************************"
						+ "\n");

		for (Tuple ex : ls) {
			System.out.println(
					"Recursivo NO Final " + ex + " ==> " + Ejercicio2.ejercicioBRecursivoNoFinal(ex.a, ex.b, ex.s));
			System.out.println(
					"Recursivo  Final " + ex + " ==> " + Ejercicio2.ejercicioBRecursivoFinal(ex.a, ex.b, ex.s));
			System.out.println("Iterativo " + ex + " ==> " + Ejercicio2.ejercicioBIterativo(ex.a, ex.b, ex.s));
			System.out.println("Funcional " + ex + " ==> " + Ejercicio2.ejercicioBFuncional(ex.a, ex.b, ex.s) + "\n");
		}
	}

	public record Tuple(Integer a, Integer b, String s) {

		public static Tuple of(Integer a, Integer b, String s) {
			return new Tuple(a, b, s);
		}

		public static Tuple parse(String st) {
			List<String> par = Arrays.stream(st.split(",")).map(x -> x.trim()).collect(Collectors.toList());
			return of(Integer.parseInt(par.get(0)), Integer.parseInt(par.get(1)), par.get(2));
		}

		public String toString() {
			return String.format("(%d, %d , %s)", a, b, s);
		}
	}
}
