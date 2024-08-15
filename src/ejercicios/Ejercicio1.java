package ejercicios;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class Ejercicio1 {

	// --------------------ejercicio1----------------------------------------------------------------------------

	record EnteroCadena(Integer a, String s) {
		public static EnteroCadena of(Integer a, String s) {
			return new EnteroCadena(a, s);
		}
	}

	public static Map<Integer, List<String>> ejercicioA(Integer varA, String varB, Integer varC, String varD,
			Integer varE) {

		UnaryOperator<EnteroCadena> nx = elem -> {
			return EnteroCadena.of(elem.a() + 2, elem.a() % 3 == 0 ? elem.s() + elem.a().toString()
					: elem.s().substring(elem.a() % elem.s().length()));
		};

		return Stream.iterate(EnteroCadena.of(varA, varB), elem -> elem.a() < varC, nx)
				.map(elem -> elem.s() + varD)
				.filter(nom -> nom.length() < varE)
				.collect(Collectors.groupingBy(String::length));
	}

	// --------------------Ejercicio1 iterativo ----------------------------------------------------------------------------
	
	public static Map<Integer, List<String>> ejercicioAiter(Integer varA, String varB, Integer varC, String varD,
			Integer varE) {

		Map<Integer, List<String>> res = Map2.empty();
		EnteroCadena tupla = EnteroCadena.of(varA, varB);
		Integer a = tupla.a;
		String b = tupla.s;
		
		while (a < varC) {
			String b0 = b;
			b = (b + varD);
			if (b.length() < varE) {
				if(!res.containsKey(b.length())) {res.put(b.length(), List.of(b));}
				else {
					List<String> lista2 = List2.empty();
					for (String e:res.get(b.length())) {
						lista2.add(e);
					}
					lista2.add(b);
					res.put(b.length(), lista2);}	
			}
			b = a% 3 == 0 ? b0 + a.toString() : b0.substring(a % b0.length());
			a = a + 2;
		}
		return res;
	}
	// --------------------Ejercicio1 recursivo ----------------------------------------------------------------------------
	
	
	public static Map<Integer, List<String>> ejercicioArecur(Integer varA, String varB, Integer varC, String varD,
			Integer varE) {
		
		EnteroCadena tupla = EnteroCadena.of(varA, varB);
		Integer a = tupla.a;
		String b = tupla.s;
		Map<Integer, List<String>> res = Map2.empty();
	
		res = ejercicioArecur2(a,b, varC,varD,varE,res);

		return res;

	}
	public static Map<Integer, List<String>> ejercicioArecur2(Integer a, String b, Integer varC, String varD,
			Integer varE, Map<Integer, List<String>>res){
		
		if (a < varC) {
			String b0 = b;
			b = (b + varD);
			if (b.length() < varE) {
				if(!res.containsKey(b.length())) {res.put(b.length(), List.of(b));}
				else {
					List<String> lista2 = List2.empty();
					for (String e:res.get(b.length())) {
						lista2.add(e);
					}
					lista2.add(b);
					res.put(b.length(), lista2);}	
			}
			
			ejercicioArecur2(a + 2,a% 3 == 0 ? b0 + a.toString() : b0.substring(a % b0.length()), varC,varD,varE,res);
		}
				return res;
	}
}
