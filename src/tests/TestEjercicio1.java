package tests;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ejercicios.Ejercicio1;

import us.lsi.common.Files2;


public class TestEjercicio1 {

	public static void main(String[] args) {

		;

		//System.out.println(Ejercicio1.ejercicioA(5, "pera", 10, "pina", 20));
		// System.out.println(Ejercicio1.ejercicioA_iter(5,"pera",10,"pina",20));

		
		String file = "ficheros/testsAlumnos/PI1Ej1DatosEntrada.txt";
		List<Tuple> ls = Files2.streamFromFile(file)
				.map(x-> Tuple.parse(x))
				.collect(Collectors.toList());
		System.out.println("**********************************************************************************************************************************************************************************************");
		System.out.println("Entrada: "+ls );
		System.out.println("**********************************************************************************************************************************************************************************************" + "\n");
		
		for (Tuple ex : ls) {
			System.out.println("Funcional" + ex + " ==> " + Ejercicio1.ejercicioA(ex.a, ex.b, ex.c, ex.d, ex.e()));
			System.out.println("Iterativo" + ex + " ==> " + Ejercicio1.ejercicioAiter(ex.a, ex.b, ex.c, ex.d, ex.e()));
			System.out.println("Recursivo" + ex + " ==> " + Ejercicio1.ejercicioArecur(ex.a, ex.b, ex.c, ex.d, ex.e())+ "\n");
		}
		
		
		
//		System.out.println( Ejercicio1.ejercicioA(ls.get(0).a(), ls.get(0).b, ls.get(0).c, ls.get(0).d, ls.get(0).e) + "\n");
//		System.out.println(Ejercicio1.ejercicioAiter(ls.get(0).a(), ls.get(0).b, ls.get(0).c, ls.get(0).d, ls.get(0).e) + "\n");
//		System.out.println(Ejercicio1.ejercicioArecur(ls.get(0).a(), ls.get(0).b, ls.get(0).c, ls.get(0).d, ls.get(0).e) + "\n");
//		System.out.println( Ejercicio1.ejercicioA(ls.get(1).a(), ls.get(1).b, ls.get(1).c, ls.get(1).d, ls.get(1).e) + "\n");
//		System.out.println(Ejercicio1.ejercicioAiter(ls.get(1).a(), ls.get(1).b, ls.get(1).c, ls.get(1).d, ls.get(1).e) + "\n");


	}
	
	//(Integer varA, String varB, Integer varC, String  varD, Integer varE)
	
	public record Tuple(Integer a, String b, Integer c, String d, Integer e){
		
		public static Tuple of(Integer a, String b, Integer c, String d, Integer e) {
			return new Tuple(a, b,c,d,e);
		}
		
		public static Tuple parse(String st) {
			List<String> par = Arrays.stream(st.split(","))
					
					//.map(x->Integer.parseInt(x.trim()))
					.collect(Collectors.toList());
			return of(Integer.parseInt(par.get(0)), par.get(1),Integer.parseInt(par.get(2)),par.get(3),Integer.parseInt(par.get(4)));
		}
		
		public String toString() {
			return String.format("(%d, %s , %d, %s, %d )", a, b ,c, d, e);
		}
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

		
	}
	

