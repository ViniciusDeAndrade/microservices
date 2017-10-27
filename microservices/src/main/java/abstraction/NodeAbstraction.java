package abstraction;

import io.kubernetes.client.models.V1NodeSelector;

public class NodeAbstraction {


	/**
	 * A node selector represents the union of the results of one or more label queries over a set of nodes;
	 * that is, it represents the OR of the selectors represented by the node selector terms.	
	 */
	private V1NodeSelector nodeSelector;
}
