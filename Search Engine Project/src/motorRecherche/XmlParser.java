package motorRecherche;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import net.java.textilej.parser.MarkupParser;
import net.java.textilej.parser.builder.HtmlDocumentBuilder;
import net.java.textilej.parser.markup.mediawiki.MediaWikiDialect;

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
					if (page.getID().equals("3")) {
						relationMotPage(page.getWikiText().substring(isParenthesisMatch(page.getWikiText()) + 2),
								count);
					}
				}
			});

			wxsp.parse();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	public int isParenthesisMatch(String str) {
		Stack<Character> stack = new Stack<Character>();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c == '{')
				stack.push(c);
			else if (c == '}')
				stack.pop();
			if (stack.empty() && i != 0)
				return i;
		}
		return -1;
	}

	private int relationMotPage(String text, int count) {
		StringWriter writer = new StringWriter();

		HtmlDocumentBuilder builder = new HtmlDocumentBuilder(writer);
		builder.setEmitAsDocument(false);

		MarkupParser parser = new MarkupParser(new MediaWikiDialect());
		parser.setBuilder(builder);
		parser.parse(text);

		final String html = writer.toString();
		final StringBuilder cleaned = new StringBuilder();

		HTMLEditorKit.ParserCallback callback = new HTMLEditorKit.ParserCallback() {
			public void handleText(char[] data, int pos) {
				cleaned.append(new String(data)).append(' ');
			}
		};
		try {
			new ParserDelegator().parse(new StringReader(html), callback, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String words[] = cleaned.toString().split(" ");
		for (int i = 0; i < words.length; i++) {
			if (!words[i].equals(""))
				System.out.print(words[i] + "<<");
		}
		return 0;
	}

	private String processMot(String mot) {
		if (mot.startsWith("{{"))
			mot = mot.substring(2);
		if (mot.endsWith("}}"))
			mot = mot.substring(0, mot.length() - 2);
		if (mot.endsWith("}}\n"))
			mot = mot.substring(0, mot.length() - 2);
		if (mot.startsWith("[["))
			mot = mot.substring(2);
		if (mot.endsWith("]]"))
			mot = mot.substring(0, mot.length() - 2);
		if (mot.endsWith("]]\n"))
			mot = mot.substring(0, mot.length() - 2);
		return mot;
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
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	public static void main(String[] args) {
		final XmlParser main1 = new XmlParser();

		System.out.println("return: " + main1.lirePages());
		System.out.println(main1.pageTable.size());

		// main1.trouverLiens();
		// System.out.println("size: " + main1.liensPage.size());

		/*
		 * for (Entry<Integer, ArrayList<Integer>> entry :
		 * main1.liensPage.entrySet()) System.out.println(entry.getKey() + "/" +
		 * entry.getValue().toString());
		 */

	}

}
