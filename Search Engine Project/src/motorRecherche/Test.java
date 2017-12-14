package motorRecherche;

import java.util.Iterator;
import java.util.Vector;

public class Test {

	public static void main(String[] args) {
		Vector<String> ali = new Vector<String>();
		ali.add("asdas");
		ali.add("hasan");
		Iterator<String> it = ali.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
