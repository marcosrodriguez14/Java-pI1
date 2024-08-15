package ejemplos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;

public class Ejemplo1 {
	
//---------------------------------------------FUNCIONAL-------------------------------------------------
	
	public static Map<Cuadrante, Double> solucionFuncional(List<Punto2D> ls) {
		// gropingBy(clasificador, dowstream collector)
		// clasificador --> variable por la que agrego --> getCuadrante
		// downstream collector --> reduce los datos aplicando una operación -->
		// reducing

		// reducing(identity, function mapper, binaryoperator combiner)
		// identity --> servirá como valor por defecto. es un valor que aplicado a si
		// mismo, retorna lo mismo.
		// mapper --> la función que traduce a otro valor (tipo lambda map)
		// combiner --> combina los resultados tomado dos operadores del mismo tipo y
		// retornando un resultado también del mismo tipo

		// Objetivo: partiendo de una lista de puntos, para cada cuadrante, obtener la
		// suma de la cordenada x de todos los puntos del cuadrante.

		// Secuencia: lista de puntos
		// Acumulador: (0, x -> x.x(), (x,y)-> x + y)

		return ls.stream().collect(Collectors.groupingBy(Punto2D::getCuadrante,
				Collectors.<Punto2D, Double>reducing(0., x -> x.x(), (x, y) -> x + y)));

	}
	
//---------------------------------------------ITERATIVA-------------------------------------------------
	
	public static Map<Cuadrante, Double> solucionIterativa(List<Punto2D> ls) {
		// Inicializar secuencia.
		// Inicializar acumulador
		// Abrir bucle while
		// Función de acumulación
		// Next elemento
		// Return acumulador

		Integer e = 0;
		Map<Cuadrante, Double> ac = new HashMap<>();
		while (e < ls.size()) {
			Punto2D p = ls.get(e);
			Cuadrante key = p.getCuadrante();
			if (ac.containsKey(key)) {
				ac.put(key, ac.get(key) + p.x());
			} else {
				ac.put(key, p.x());
			}
			e++;
		}
		return ac;
	}
	
//---------------------------------------------RECURSIVA FINAL-------------------------------------------------
	
	public static Map<Cuadrante, Double> solucionRecursivaFina(List<Punto2D> ls) {
		// Iniciar secuencia
		// Iniciar acumulador
		// acumulador = llamada recursiva
		// Return acumulador

		Integer e = 0;
		Map<Cuadrante, Double> ac = new HashMap<>();
		ac = solucionRecursivaFinal(e, ac, ls);
		return ac;

	}

	private static Map<Cuadrante, Double> solucionRecursivaFinal(Integer e, Map<Cuadrante, Double> ac,
			List<Punto2D> ls) {
		// if hay siguiente
		// llamada recursiva (next (e),funcionAcumulación,l)
		// return acumulador

		if (e < ls.size()) {
			Punto2D p = ls.get(e);
			Cuadrante key = p.getCuadrante();
			if (ac.containsKey(key)) {
				ac.put(key, ac.get(key) + p.x());
			} else {
				ac.put(key, p.x());
			}
			ac = solucionRecursivaFinal(e + 1, ac, ls);
		}
		return ac;
	}
}
