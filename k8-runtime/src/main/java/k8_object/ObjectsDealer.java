package k8_object;

import java.util.List;

import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;

public class ObjectsDealer {

	public V1Pod findPod(CoreV1Api core, String podName) throws ApiException {
		V1PodList list = core.listPodForAllNamespaces(null, null, null, null, null, null);
		for (V1Pod item : list.getItems()) 
			if(item.getMetadata().getName().equalsIgnoreCase(podName))
				return item;
		return null;
	}

	public V1Pod setLabelsToPods(CoreV1Api core, String podName, String key, String matchLabelsItem, String operator, List<String> values) throws ApiException {
		//setting label selector and label requirement. I still do not know what is a requirement of their difference
		V1LabelSelector selector = new V1LabelSelector();
		V1LabelSelectorRequirement requirement = new V1LabelSelectorRequirement();
		selector.putMatchLabelsItem(key, matchLabelsItem);
		requirement.setKey(key);
		requirement.setOperator(operator);
		requirement.setValues(values);
		
		V1Pod pod = setLabels(core, podName, selector, requirement);		
		return pod;
	}
	
	private V1Pod setLabels(CoreV1Api core, String podName, V1LabelSelector selector, V1LabelSelectorRequirement requirement) throws ApiException {
		V1Pod pod = this.findPod(core, podName);
		pod.getMetadata().setLabels(selector.getMatchLabels());
		//I Still don't know why the requirement is here. It is useless.
		return pod;
	}
	
	


}
