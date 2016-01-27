package motorRecherche;

import java.util.Enumeration;
import java.util.Hashtable;

import edu.jhu.nlp.wikipedia.*;

public class XmlParser {

	private int count = 0;
	private Hashtable<String, Integer> pageTable = new Hashtable<String, Integer>();

	private int lirePages() {

		WikiXMLParser wxsp = WikiXMLParserFactory
				.getSAXParser("C://Users//Mahmut//Desktop//frwiki-20151226-pages-articles-multistream.xml");
		try {

			wxsp.setPageCallback(new PageCallbackHandler() {
				public void process(WikiPage page) {
					count++;
					pageTable.put(page.getTitle(), count);
				}
			});

			wxsp.parse();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	public static void main(String[] args) {
		final XmlParser main1 = new XmlParser();
		// TODO Auto-generated method stub
		// frwiki-20151226-pages-articles-multistream
		System.out.println("return: " + main1.lirePages());
		System.out.println(main1.pageTable.size());

		Enumeration names;
		String str;
		names = main1.pageTable.keys();
		System.out.println(main1.pageTable.size());
		while (names.hasMoreElements()) {
			str = (String) names.nextElement(); //
			System.out.println(str + ": " + main1.pageTable.get(str));
		}

	}

}
