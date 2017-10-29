package abstraction;

import java.util.List;

import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;

public class LabelAbstraction {

	/**
	 * this method just create and return a label selector
	 * @param key is the ID of this tuple
	 * @param matchLabelsItem is the value of the tuple
	 * @return is the entire label selector
	 */
	public V1LabelSelector setLabel(String key, String matchLabelsItem) {
		V1LabelSelector selector = new V1LabelSelector();
		selector.putMatchLabelsItem(key, matchLabelsItem);
		return selector;
	}
	
	/**
	 * this method just create and return a label selector requirement
	 * @param key is the ID of this tuple
	 * @param operator represents a key's relationship to a set of values. Valid operators are In, NotIn, Exists and DoesNotExist
	 * @return is the entire label selector
	 */
	public V1LabelSelectorRequirement setLabelRequirement(String key, String operator, List<String> values) {
		V1LabelSelectorRequirement requirement = new V1LabelSelectorRequirement();
		requirement.setKey(key);
		requirement.setOperator(operator);
		requirement.setValues(values);
		return requirement;
	}
}
