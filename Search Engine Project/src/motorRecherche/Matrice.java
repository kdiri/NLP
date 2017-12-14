package motorRecherche;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrice {
	List<Integer> tableauL = new ArrayList<Integer>();
	List<Integer> tableauC = new ArrayList<Integer>();
	List<Integer> tableauI = new ArrayList<Integer>();

	Matrice(List<Integer> tableauL, List<Integer> tableauC, List<Integer> tableauI) {
		this.tableauL = tableauL;
		this.tableauC = tableauC;
		this.tableauI = tableauI;
	}

	public static List<Integer> multiple(Matrice mtrc, List<Integer> vect) {
		List<Integer> result = new ArrayList<Integer>();
		int count = 0, sum = 0;
		for (int i = 1; i < mtrc.tableauL.size(); i++) {
			for (int j = 0; j < mtrc.tableauL.get(i) - mtrc.tableauL.get(i - 1); j++) {
				sum += mtrc.tableauC.get(count + j) * vect.get(mtrc.tableauI.get(count + j));
			}
			count += mtrc.tableauL.get(i) - mtrc.tableauL.get(i - 1);
			result.add(sum);
			sum = 0;
		}
		return result;
	}

	public static void main(String[] args) {

		List<Integer> tableauL = Arrays.asList(0, 0, 0, 0, 1);
		List<Integer> tableauC = Arrays.asList(5);
		List<Integer> tableauI = Arrays.asList(3);
		List<Integer> vect = Arrays.asList(1, 2, 3, 4);

		Matrice exemple = new Matrice(tableauL, tableauC, tableauI);
		List<Integer> result = multiple(exemple, vect);
		System.out.println(result);
	}

}
