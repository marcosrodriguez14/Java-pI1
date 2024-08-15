package ejemplos;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.IntPair;
import us.lsi.geometria.Punto2D;
import us.lsi.streams.Stream2;

public class TestsEjemplos {

	public static void main(String[] args) {
		Ejemplo1("ficheros/testsProfesores/Ejemplo1DatosEntrada.txt");
		Ejemplo2("ficheros/testsProfesores/Ejemplo2DatosEntrada.txt");
		Ejemplo3("ficheros/testsProfesores/Ejemplo3DatosEntrada.txt");
	}

	private static void Ejemplo1(String fichero) {
		Function<String, Punto2D> parsePunto = s -> {
			String[] v = s.split(",");
			return Punto2D.of(Double.valueOf(v[0]), Double.valueOf(v[1]));
		};

		List<Punto2D> ls = Stream2.file(fichero).map(parsePunto).toList();
		// Otra manera de usar las librerías del departamento para hacer la lectura
		// List<Punto2D>lsOtraForma =
		// Files2.sreamFromFile(fichero).map(parsePunto).toList();
		System.out.println("-- EJEMPLO 1 --\n");
		System.out.println("1) Solucion funcional: --------->" + Ejemplo1.solucionFuncional(ls));
		System.out.println("2) Solucion Iterativa: --------->" + Ejemplo1.solucionIterativa(ls));
		System.out.println("3) Solucion Recursiva Final: --->" + Ejemplo1.solucionRecursivaFina(ls));
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------\n");

	}

	public static void Ejemplo2(String fichero) {
		List<IntPair> ls = Stream2.file(fichero).map(IntPair::parse).toList();
		System.out.println("-- EJEMPLO 2 --\n");
		ls.forEach(par -> {
			Integer a = par.first();
			Integer b = par.second();
			System.out.println("1) Solucion Recursiva No Final: --->" + Ejemplo2.solucionRecursivaNoFinal(a, b));
			System.out.println("2) Solucion Recursiva  Final: ----->" + Ejemplo2.solucionRecursivaFinal(a, b));
			System.out.println("3) Solucion Iterativa: ------------>" + Ejemplo2.solucionIterativa(a, b));
			System.out.println("4) Solucion funcional: ------------>" + Ejemplo2.solucionFuncional(a, b));
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------\n");
		});
	}
	
	private static void Ejemplo3(String fichero) {
		List<IntPair> ls = Stream2.file(fichero).map(IntPair::parse).toList();
		System.out.println("-- EJEMPLO 3 --\n");
		ls.forEach(par ->{
			Integer a = par.first();
			Integer b = par.second();
			System.out.println("1) Solucion Recursiva SIN Memoria: ---->" + Ejemplo3.solucionRecursivaSinMemoria(a, b));
			System.out.println("2) Solucion Recursiva  CON Memoria: --->" + Ejemplo3.solucionRecursivaConMemoria(a, b));
			System.out.println("3) Solucion Iterativa: ---------------->" + Ejemplo3.solucionIterativa(a, b));
			
		});
		
	}


}
