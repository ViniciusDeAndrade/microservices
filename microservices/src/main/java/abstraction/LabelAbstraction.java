package abstraction;

import java.util.List;

import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;

public class LabelAbstraction {

	private V1LabelSelector selector ;
	private V1LabelSelectorRequirement requirement ;
	
	public LabelAbstraction() {
		this.selector = new V1LabelSelector();
		this.requirement = new V1LabelSelectorRequirement();
		
	}

	public void setLabelSelector(String key, String matchLabelsItem) {
		this.selector.putMatchLabelsItem(key, matchLabelsItem);
	}		

	/**
	 * this method gets a label selector
	 * @return is the entire label selector
	 */
	public V1LabelSelector getLabelSelector() {
		return selector;
	}	

	/**
	 * this method just create  a label selector requirement
	 * @param key is the ID of this tuple
	 * @param operator represents a key's relationship to a set of values. Valid operators are In, NotIn, Exists and DoesNotExist
	 */
	public void setLabelRequirement(String key, String operator, List<String> values) {
		requirement.setKey(key);
		requirement.setOperator(operator);
		requirement.setValues(values);
	}

	public V1LabelSelectorRequirement getLabelSelectorRequirement() {
		return this.requirement;
	}
}
