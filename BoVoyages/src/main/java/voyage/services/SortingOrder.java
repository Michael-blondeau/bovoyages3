package voyage.services;

/**
 * Énumération simple qui permet la conversion d'une énumération d'un autre
 * package vers celui de l'application. L'énumération s'utilise en cas de
 * demande de tri, et permet de préciser si l'on souhaite que le tri se fasse de
 * manière ascendante, descendante, ou non-triée.
 * 
 * @author Pierrik
 *
 */
public enum SortingOrder {
	ASCENDING, DESCENDING, UNSORTED
}
