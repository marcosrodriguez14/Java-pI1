package ejemplos;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.IntPair;

public class Ejemplo3 {

//---------------------------------RECURSIVA SIN MEMORIA-------------------------------------------------------------------

	public static Integer solucionRecursivaSinMemoria(Integer a, Integer b) {
		Integer ac = null;
		if (a < 2 || b < 2) { // caso base
			ac = a * a + b;
		} else { // caso recursivo
			ac = solucionRecursivaSinMemoria(a / 2, b - 1) + solucionRecursivaSinMemoria(a / 3, b - 2);
		}
		return ac;
	}

//---------------------------------RECURSIVA CON MEMORIA-------------------------------------------------------------------

	public static Integer solucionRecursivaConMemoria(Integer a, Integer b) {
		Map<IntPair, Integer> m = new HashMap<>();
		return gRecConMemoria(a, b, m);
	}

	private static Integer gRecConMemoria(Integer a, Integer b, Map<IntPair, Integer> m) {
		Integer ac = null;
		IntPair key = IntPair.of(a, b);

		if (m.containsKey(key)) {
			ac = m.get(key);
		} else {
			if (a < 2 || b < 2) {
				ac = a * a + b;
			} else {
				ac = gRecConMemoria(a / 2, b - 1, m) + gRecConMemoria(a / 3, b - 2, m);
			}
			m.put(IntPair.of(a, b), ac);
		}
		return ac;
	}
//----------------------------------------------ITERATIVA-------------------------------------------------------------------

	public static Integer solucionIterativa(Integer a, Integer b) {
		Map<IntPair, Integer> m = new HashMap<>();
		Integer ac = null;

		for (int i = 0; i <= a; i++) { // Indice para a
			for (int j = 0; j <= b; j++) { // Indice para b
				if (i < 2 || j < 2) {
					ac = i * i + j;
				} else {
					ac = m.get(IntPair.of(i / 2, j - 1)) + m.get(IntPair.of(i / 3, j - 2));
				}
				m.put(IntPair.of(i, j), ac);
			}
		}
		return m.get(IntPair.of(a, b));
	}

}
