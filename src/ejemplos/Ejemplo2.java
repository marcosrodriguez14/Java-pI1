package ejemplos;

import java.util.stream.Stream;

public class Ejemplo2 {

//---------------------------------------------RECURSIVA  NO FINAL----------------------------------------------
	public static String solucionRecursivaNoFinal(Integer a, Integer b) {
		String ac = null;
		if (a < 5 || b < 5) { // Caso base
			ac = String.format("(%d)", a * b);
		} else {
			ac = String.format("%d", a + b) + solucionRecursivaNoFinal(a / 2, b - 2); // Caso Recursivo
			// + --> operador de combinación
			// String.format("%d", a + b)+ solucionRecursivaNoFinal(a / 2 , b - 2) -->
			// función de combinación
		}
		return ac;
	}

//---------------------------------------------RECURSIVA FINAL-------------------------------------------------

	public static String solucionRecursivaFinal(Integer a, Integer b) {
		// Idea intuitiva de no final a final: arrastramos la función de combinación y
		// el operador de combinación dentro de la llamda recursiva
		// Ej factorial : n * factorial (n-1) == factorial(n-1,acu * n) donde n será
		// inicialmente el valor del elemento neutro de la operación de combinación
		// El valor neutro de la multiplicación es el 1, en la suma el 0, etc.

		// Inicializar secuencia (está inicializada con a y b)
		// Inicializar acumulador
		// acumulador = llamada recursiva
		// Return acumuladorfi

		String ac = "";// elemento neutro --> cadena vacía
		ac = solucionRecursivaFinal(a, b, ac);
		return ac;

	}

	private static String solucionRecursivaFinal(Integer a, Integer b, String ac) {
		// if hay siguiente
		// llamada recursiva (next (e), funcionAcumulacion,l)
		// return acumulador
		if (a < 5 || b < 5) {
			ac = ac + String.format("(%d)", a * b);
		} else {
			ac = solucionRecursivaFinal(a / 2, b - 2, String.format("%s%d", ac, a + b));
		}
		return ac;
	}

//---------------------------------------------ITERATIVA-------------------------------------------------
	public static String solucionIterativa(Integer a, Integer b) {
		String ac = "";
		while (!(a < 5 || b < 5)) {// mientras haya siguiente
			ac = String.format("%s%d", ac, a + b); // función de acumulación
			// Next elemento
			a = a / 2;
			b = b - 2;
		}
		return ac + String.format("(%d)", a * b); // función de retorno
	}

// --------------------------------------------- FUNCIONAL------------------------------------------------

	private static record Tupla(String ac, Integer a, Integer b) {
		public static Tupla of(String ac, Integer a, Integer b) {
			return new Tupla(ac, a, b);
		}

		public static Tupla first(Integer a, Integer b) {
			return of("", a, b); // Valor inicial de la secuencia
		}

		public Tupla next() {// método next de la secuencia
			return of(ac + String.format("%d", a + b), a / 2, b - 2);
			
		}

		public Boolean isCaseBase() {
			return a < 5 || b < 5;
		}
	}

	public static String solucionFuncional(Integer a, Integer b) {
		Tupla elementoFinal = Stream.iterate(Tupla.first(a, b), elem -> elem.next()).filter(elem -> elem.isCaseBase())
				.findFirst().get();
//		Tupla elementoFinal2 = Stream.iterate(Tupla.first(a, b), elem -> elem.next())
//				.dropWhile(elem -> !elem.isCaseBase()).findFirst().get();

		return elementoFinal.ac + String.format("(%d)", elementoFinal.a * elementoFinal.b);
	}

}
