package voyage.services;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.logging.Logger;

import org.primefaces.model.SortOrder;

import voyage.entities.Destination;

/**
 * Cette classe dérive de la classe {@link org.primefaces.model.SortOrder} et
 * permet de trier les destinations.
 * 
 * Elle sera appelée automatiquement lors de la récupération en base de données
 * des destinations, et permettra de trier 2 destinations en les comparant deux
 * à deux.
 * 
 * @author Pierrik
 *
 */
public class LazySorter implements Comparator<Destination> {
	private static final Logger log = Logger.getLogger(LazySorter.class.getName());
	private String sortField;
	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@Override
	public int compare(Destination ville1, Destination ville2) {
		try {
			// On récupère le champ de tri par introspection
			Field field = Destination.class.getDeclaredField(this.sortField);

			// On le marque temporairement comme visible
			field.setAccessible(true);
			Object o1 = field.get(ville1);
			Object o2 = field.get(ville2);
			int value = ((Comparable) o1).compareTo(o2);
			// On redéclare le champ invisible
			field.setAccessible(false);
			// On retourne le résultat de la comparaison, en fonction de l'ordre
			// de tri désiré
			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;

		} catch (IllegalArgumentException | SecurityException | IllegalAccessException | NoSuchFieldException e) {
			log.warning("Erreur lors du tri des destinations : " + e);
		}

		return 0;
	}

}
