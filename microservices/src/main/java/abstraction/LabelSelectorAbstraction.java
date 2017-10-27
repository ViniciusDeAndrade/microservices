package abstraction;

import java.util.ArrayList;
import java.util.List;

import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;

public class LabelSelectorAbstraction {

	/** A label selector is a label query over a set of resources.
	The result of matchLabels and matchExpressions are ANDed. An empty label selector matches all objects.
	A null label selector matches no objects.
	 */
	private V1LabelSelector labelSelector;


	/**A label selector requirement is a selector that contains values, a key, and an operator that relates the key and values. */
	private V1LabelSelectorRequirement labelSelectorRequirement = new V1LabelSelectorRequirement();
	
	/**
	 * this key is the label key that the selector applies to (selector requirement)
	 */
	private static final String key = "uma chave";
	
	/**
	 * operator represents a key's relationship to a set of values. Valid operators are In, NotIn, Exists and DoesNotExist.
	 */	
	private static final String operator = "um operador";
	
	/**
	 * values is an array of string values. If the operator is In or NotIn, the values array must be non-empty. 
	 * If the operator is Exists or DoesNotExist, the values array must be empty. 
	 * This array is replaced during a strategic merge patch.
	 */
	private List<String> values = new ArrayList<String>();

	public void init() {
		this.labelSelectorRequirement = new V1LabelSelectorRequirement();
		this.labelSelectorRequirement.setKey(key);
		this.labelSelectorRequirement.setOperator(operator);
		this.labelSelectorRequirement.setValues(values);
	}
}
