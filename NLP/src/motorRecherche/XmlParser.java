package motorRecherche;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.jhu.nlp.wikipedia.*;

public class XmlParser {

	private int count = 0;
	private Hashtable<String, Integer> pageTable = new Hashtable<String, Integer>();
	private Map<Integer, ArrayList> motPage = new HashMap<Integer, ArrayList>();
	private Map<Integer, ArrayList<Integer>> liensPage = new HashMap<Integer, ArrayList<Integer>>();

	private int lirePages() {

		WikiXMLParser wxsp = WikiXMLParserFactory
				.getSAXParser("C://Users//Mahmut//Desktop//frwiki-20151226-pages-articles-multistream.xml");
		try {

			wxsp.setPageCallback(new PageCallbackHandler() {
				public void process(WikiPage page) {
					count++;
					pageTable.put(page.getTitle().trim(), count);
				}
			});

			wxsp.parse();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	private int relationMotPage(String text, int count) {

		String words[] = text.split(" ");
		for (int i = 0; i < words.length; i++) {
			System.out.print(words[i] + "<<");
		}
		// System.out.println(text);
		return 0;

	}

	private int trouverLiens() {

		WikiXMLParser wxsp2 = WikiXMLParserFactory
				.getSAXParser("C://Users//Mahmut//Desktop//frwiki-20151226-pages-articles-multistream.xml");
		try {

			wxsp2.setPageCallback(new PageCallbackHandler() {
				public void process(WikiPage page2) {
					Integer pagenum = pageTable.get(page2.getTitle().trim());
					ArrayList<Integer> temp = new ArrayList<Integer>();
					for (String element : page2.getLinks()) {
						try {
							temp.add(pageTable.get(element));
						} catch (NullPointerException e) {
							System.out.println("bos");
						}
					}
					liensPage.put(pagenum, temp);
				}
			});

			wxsp2.parse();
		} catch (

		Exception e)

		{
			e.printStackTrace();
			return -1;
		}
		return 0;

	}

	public static void main(String[] args) {
		final XmlParser main1 = new XmlParser();

		System.out.println("return: " + main1.lirePages());
		System.out.println(main1.pageTable.size());

		main1.trouverLiens();
		System.out.println("size: " + main1.liensPage.size());

		for (Entry<Integer, ArrayList<Integer>> entry : main1.liensPage.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue().toString());
		}

	}

}
