package pt.iul.ista.poo.farm.objects;


public enum Vegetables {
	
	small_tomato, tomato , bad_tomato, small_cabbage, cabbage, bad_cabbage;
		
	public static boolean isAnVegetal(String vegetal) {
		for(Vegetables v: values()) {
			if(v.toString().equals(vegetal)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isAnTomato(String vegetal) {
		Vegetables[] v = Vegetables.values();
		for(int i = 0; i<2; i++) {
			if(v[i].toString().equals(vegetal)) {
				return true;
			}
		}
		return false;
	}

	public static String nextVegetal(String vegetal) {
		Vegetables[] v = Vegetables.values();
		for(int i = 0; i<v.length; i++) {
			if(v[i].name().equals(vegetal) && !vegetal.equals("bad_cabbage") && !vegetal.equals("bad_tomato")) { 
				return v[i+1].name();
			}
		}
		return vegetal;
	}
	
	
	public static boolean isDone(String vegetal) {
		if(vegetal.equals(tomato.toString()) || vegetal.equals(bad_tomato.toString()) || vegetal.equals(cabbage.toString()) || vegetal.equals(bad_cabbage.toString())) {
			return true;
		}
		return false;
	}

	public static String isAnCabbage(String s) {
		if(s.equals("small_cabbage")) 
			return s;
		if(s.equals("cabbage"))
			return s;
		if(s.equals("bad_cabbage"))
			return s;
		return null;
	}
	
}
