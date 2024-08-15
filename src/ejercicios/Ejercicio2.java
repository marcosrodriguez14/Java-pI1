package ejercicios;

import java.util.stream.Stream;

public class Ejercicio2 {

//------------------------------RECURSIVO NO FINAL----------------------------------------------------------	

	public static Integer ejercicioBRecursivoNoFinal(Integer a, Integer b, String s) {
		int ac = 0;
		if (s.length() == 0) {
			ac = a * a + b * b;
		} else if (a < 2 || b < 2) {
			ac = s.length() + a + b;
		} else if (a % s.length() < b % s.length()) {
			ac = a + b + ejercicioBRecursivoNoFinal(a - 1, b / 2, s.substring(a % s.length(), b % s.length()));
		} else {
			ac = a * b + ejercicioBRecursivoNoFinal(a / 2, b - 1, s.substring(b % s.length(), a % s.length()));
		}
		return ac;
	}
//------------------------------RECURSIVO FINAL----------------------------------------------------------	

	public static Integer ejercicioBRecursivoFinal(Integer a, Integer b, String s) {
		int ac = 0;
		ac = ejercicioBRecursivoFinal(a, b, s, ac);
		return ac;
	}

	public static Integer ejercicioBRecursivoFinal(Integer a, Integer b, String s, Integer ac) {

		if (s.length() == 0) {
			ac = ac + a * a + b * b;
		} else if (a < 2 || b < 2) {
			ac = ac + s.length() + a + b;
		} else if (a % s.length() < b % s.length()) {
			ac = ejercicioBRecursivoFinal(a - 1, b / 2, s.substring(a % s.length(), b % s.length()), a + b + ac);
		} else {
			ac = ejercicioBRecursivoFinal(a / 2, b - 1, s.substring(b % s.length(), a % s.length()), a * b + ac);
		}
		return ac;
	}

//------------------------------RECURSIVO ITERATIVO----------------------------------------------------------	

	public static Integer ejercicioBIterativo(Integer a, Integer b, String s) {

		int ac = 0;
		while (!(s.length() == 0 || a < 2 || b < 2)) {

			if ((a % s.length() < b % s.length())) {
				s = s.substring(a % s.length(), b % s.length());
				ac = a + b + ac;
				a = a - 1;
				b = b / 2;
			} else {
				s = s.substring(b % s.length(), a % s.length());
				ac = a * b + ac;
				a = a / 2;
				b = b - 1;
			}
		}
		if ((s.length() == 0)) {
			return ac + a * a + b * b;
		} else {
			return ac + s.length() + a + b;
		}
	}
	// ------------------------------FUNCIONAL--------------------------------------------------------------------

	private static record Tupla(Integer a, Integer b, String s, Integer ac) {
		public static Tupla of(Integer a, Integer b, String s, Integer ac) {
			return new Tupla(a, b, s, ac);
		}

		public static Tupla first(Integer a, Integer b, String s) {
			return of(a, b, s, 0); // Valor inicial de la secuencia
		}

		public Tupla next() {// método next de la secuencia
			if (a % s.length() < b % s.length()) {
				return of(a - 1, b / 2, s.substring(a % s.length(), b % s.length()), a + b + ac);
			} else {
				return of(a / 2, b - 1, s.substring(b % s.length(), a % s.length()), a * b + ac);
			}
		}

		public Boolean isCaseBase() {
			if (s.length() == 0) {
				return s.length() == 0;
			} else {
				return a < 2 || b < 2;
			}
		}
	}

	public static Integer ejercicioBFuncional(Integer a, Integer b, String s) {
		Tupla elementoFinal = Stream.iterate(Tupla.first(a, b, s), elem -> elem.next())
				.filter(elem -> elem.isCaseBase()).findFirst().get();

		if (!(s.length() == 0)) {
			return elementoFinal.ac + elementoFinal.a * elementoFinal.a + elementoFinal.b * elementoFinal.b;
		} else {
			return elementoFinal.ac + elementoFinal.s.length() + elementoFinal.a + elementoFinal.b;
		}
	}

}
