package motorRecherche;

import static org.junit.Assert.*;
import motorRecherche.XmlParser;

import org.junit.Test;

public class ParanthesisTest {

	@Test
	public void test() {

		XmlParser xmlparser = new XmlParser();

		assertEquals("{Hello must be -1", -1, xmlparser.isParenthesisMatch("{Hello"));
		assertEquals("{Hello must be -1", 6, xmlparser.isParenthesisMatch("{Hello}"));
		assertEquals("{Hello must be -1", 8, xmlparser.isParenthesisMatch("{{Hello}}"));
		assertEquals("{Hello must be -1", -1, xmlparser.isParenthesisMatch("{{Hello{}}"));
		assertEquals("{Hello must be -1", 1,
				xmlparser.isParenthesisMatch("{{Infobox Linguiste" + "|nom                     = Antoine Meillet"
						+ "|nationalité             = {{France}}"
						+ "|date de naissance       = {{Date de naissance|11|novembre|1866}} "
						+ "|lieu de naissance       = [[Moulins (Allier)|Moulins]] ([[Allier (département)|Allier]]) "
						+ "|date de décès           = {{Date de décès|21|septembre|1936|11|novembre|1866}}"
						+ "|lieu de décès           = [[Châteaumeillant]]"
						+ "|région                  = Linguiste occidental"
						+ "|époque                  = {{XXe siècle}}" + "|image                   = Meillet Antoine.jpg"
						+ "|légende                 = " + "|tradition linguistique  = [[Linguistique comparée]]"
						+ "|principaux intérêts     = " + "|influencé par           = " + "|influence de            = "
						+ "|idées remarquables      = [[épithète homérique]]"
						+ "|œuvres principales      = ''Introduction à l'étude comparative des langues indo-européennes'' ([[1903]])<br />"
						+ "''Aperçu d'une histoire de la langue grecque'' ([[1913]])<br />"
						+ "''Dictionnaire étymologique de la langue latine'' ([[1932]])" + "|adjectifs dérivés       = "
						+ "}}"));

	}

}
