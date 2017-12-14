package motorRecherche;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Dictionnaire {

	private List<String> Dictionary;
	Hashtable<String, Integer> balance = new Hashtable<String, Integer>();
	private List<String> SupprimeElements = Arrays.asList("le", "la", "les", "un", "une", "à", "en", "sa", "son", "ses",
			"ma", "mon", "mes", "ta", "ton", "tes", "de", "du", "des", "au", "aux", "etc", "ce", "cet", "cette", "ceux",
			"celui", "celle", "ça", "pas", "rien", "auchun", "");

	private String accentControl(String oldStr) {
		String newStr = new String(oldStr);
		newStr = newStr.replace('à', 'a');
		newStr = newStr.replace('è', 'e');
		newStr = newStr.replace('é', 'e');
		newStr = newStr.replace('ù', 'u');
		return newStr;

	}

	private int LireMots() {
		Document doc;
		try {
			// get all words
			for (int cnt = 1; cnt <= 10; cnt++) {
				doc = Jsoup.connect("https://fr.wiktionary.org/wiki/Wiktionnaire:10000-wp-fr-" + cnt + "000").get();
				Elements uls = doc.select("ul");
				for (Element ul : uls) {
					Elements lis = ul.select("li");
					for (Element li : lis) {
						Elements a = li.select("a");
						Dictionary.add(accentControl(a.text()).toLowerCase());
					}
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	private void supprimeElement() {
		Dictionary.removeAll(SupprimeElements);
	}

	private void supprimeRedondance() {
		Set<String> hs = new HashSet<String>();
		hs.addAll(Dictionary);
		Dictionary.clear();
		Dictionary.addAll(hs);
	}

	public static void main(String[] args) {

		Dictionnaire d1 = new Dictionnaire();
		d1.Dictionary = new ArrayList<String>();
		d1.LireMots();

		System.out.println(d1.Dictionary.size());
		d1.supprimeElement();
		System.out.println(d1.Dictionary.size());
		d1.supprimeRedondance();
		System.out.println(d1.Dictionary.size());
		System.out.println(d1.Dictionary.get(1000));
		Collections.sort(d1.Dictionary.subList(0, d1.Dictionary.size()));
		System.out.println(d1.Dictionary.size());
		System.out.println(d1.Dictionary.get(8000));

		for (int i = 0; i < d1.Dictionary.size(); i++) {
			d1.balance.put(d1.Dictionary.get(i), i);
		}

		Enumeration names;
		String str;
		names = d1.balance.keys();
		System.out.println(d1.balance.size());
		while (names.hasMoreElements()) {
			str = (String) names.nextElement();
			// System.out.println(str + ": " + d1.balance.get(str));
		}

		System.out.println(d1.balance.get("deux"));
	}

}
