import java.util.ArrayList;

import com.example.demo.model.Citta;
import com.example.demo.src.Convertitore;

public class Prova {
	
	public static void main(String[] args) {
		
		Convertitore conv = new Convertitore();
		ArrayList<Citta> c = conv.JsonToCitta();
		for(Citta x : c)
			System.out.println(x.getNome() + " " + x.getData());
		System.out.println(c.get(0).getNome() + " " + c.get(0).getData());
		System.out.println(c.get(c.size()-1).getNome() + " " + c.get(c.size()-1).getData());
	}

}
