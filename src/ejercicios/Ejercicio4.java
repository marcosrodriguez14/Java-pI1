package ejercicios;

import java.util.HashMap;
import java.util.Map;
import tests.TestEjercicio4.Tuple;


public class Ejercicio4 {
	
//---------------------------------RECURSIVA SIN MEMORIA-------------------------------------------------------------------
	
	public static String recursivoSinMemoria (Integer a, Integer b , Integer c) {
		String ac= "";
		if(a < 2 && b <= 2 || c < 2) {
			ac = String.format("(%d+%d+%d)", a,b,c);
		}else if(a<3 || b<3 && c<3) {
			ac = String.format("(%d-%d-%d)", a,b,c);
			}else if(b%a== 0 && (a%2==0 || b%3 ==0)) {
				ac = String.format("(%s*%s)", recursivoSinMemoria(a-1,b/a,c-1),recursivoSinMemoria(a-2,b/2,c/2));
			}else {
				ac = String.format("(%s/%s)", recursivoSinMemoria(a/2,b-2,c/2),recursivoSinMemoria(a/3,b-1,c/3));
			}
		return ac;
	}
//---------------------------------RECURSIVA CON MEMORIA-------------------------------------------------------------------
	
	public static String recursivaConMemoria(Integer a, Integer b,Integer c) {
		Map<Tuple, String> m = new HashMap<>();
		return gRecConMemoria(a, b,c, m);
	}

	private static String gRecConMemoria(Integer a, Integer b,Integer c, Map<Tuple, String> m) {
		String ac = null;
		Tuple key = tupla.of(a, b,c);

		if (m.containsKey(key)) {
			ac = m.get(key);
		} else if(a < 2 && b <= 2 || c < 2) {
			ac = String.format("(%d+%d+%d)", a,b,c);
		}else if(a<3 || b<3 && c<3) {
			ac = String.format("(%d-%d-%d)", a,b,c);
			}else if(b%a== 0 && (a%2==0 || b%3 ==0)) {
				ac = String.format("(%s*%s)", recursivoSinMemoria(a-1,b/a,c-1),recursivoSinMemoria(a-2,b/2,c/2),m);
			}else {
				ac = String.format("(%s/%s)", recursivoSinMemoria(a/2,b-2,c/2),recursivoSinMemoria(a/3,b-1,c/3),m);
			}
		return ac;
	}
	public record tupla (Integer a,Integer b,Integer c) {
		public static Tuple of(Integer a, Integer b, Integer c) {
			return new Tuple(a, b, c);
		}
	}
	
	//---------------------------------ITERATIVA-------------------------------------------------------------------	
	public static String Iterativo(Integer a, Integer b, Integer c ) {
		Map<Tuple, String> m = new HashMap<>();
		String ac = null;
		
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= b; j++) {
				for (int k = 0; k <= c; k++) {
				  if(i < 2 && j <= 2 || k < 2) {
						ac = String.format("(%d+%d+%d)", i,j,k);
					}else if(i<3 || j<3 && k<3) {
						ac = String.format("(%d-%d-%d)", i,j,k);
						}else if(j%i== 0 && (i%2==0 || j%3 ==0)) {
							ac = String.format("(%s*%s)", m.get(Tuple.of(i-1,j/i,k-1)),m.get(Tuple.of(i-2,j/2,k/2)));
						}
						else {
							ac = String.format("(%s/%s)", m.get(Tuple.of(i/2,j-2,k/2)),	m.get(Tuple.of(i/3,j-1,k/3)));
						}
				  m.put(Tuple.of(i, j,k), ac);
				}
			}
		}
		return ac;
	}
}

