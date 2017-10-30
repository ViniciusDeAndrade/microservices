package abstraction;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.AppsV1beta1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.AppsV1beta1Deployment;
import io.kubernetes.client.models.AppsV1beta1DeploymentList;
import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import uniqueInstance.ConfigGFADS;

public class DeploymentAbstraction {

	private ConfigGFADS gfads ;
	private AppsV1beta1Api api;
	private CoreV1Api core;
	
	public DeploymentAbstraction() throws FileNotFoundException {
		this.gfads = new ConfigGFADS();
	}
	
	@Deprecated
	private V1LabelSelector configLabelSelector(String key, String value) {
		LabelAbstraction label = new LabelAbstraction();
		label.setLabelSelector(key, value);
		V1LabelSelector selector = label.getLabelSelector();
		return selector;		
	}
	@Deprecated
	private V1LabelSelectorRequirement configLabelRequirement(String key, String operator, List<String> values) {
		LabelAbstraction label = new LabelAbstraction();
		label.setLabelRequirement(key, operator, values);
		return label.getLabelSelectorRequirement();		
	}
	
	public void setLabelsToPods(String podName, String namespace, String body, String pretty) throws ApiException {
		this.core = ConfigGFADS.getCoreV1ApiInstance();
		
		V1PodList list = this.core.listPodForAllNamespaces(null, null, null, null, null, null);
		for(V1Pod pod: list.getItems()) {
			
			//pod.getMetadata().setLabels(labels);
		}
			
		
		
		//partially update a pod
		V1Pod pod = core.patchNamespacedPod(podName, namespace, body, pretty);
		
	}
	
	public void patchDeployment() throws ApiException {		
		
		AppsV1beta1DeploymentList list = this.gfads.getAppsV1BetaApi().listDeploymentForAllNamespaces(null, null, null, null, null, null);
		LabelAbstraction label = new LabelAbstraction();
		
		for(AppsV1beta1Deployment item: list.getItems()) {
			//deal label selector
			label.setLabelSelector("key", "value1");
			label.setLabelSelector("key2", "value2");
			V1LabelSelector selector = label.getLabelSelector();
			
		
			//deal label requirement
			/**
			 * the last parameter of the label Requirement is a List of String (see singletonList method)
			 */
			label.setLabelRequirement("key", "in", Collections.singletonList("value1"));
			V1LabelSelectorRequirement requirement = label.getLabelSelectorRequirement();
			
			//deal with deployment object			
			String name = item.getMetadata().getName();
			String namespace = item.getMetadata().getNamespace();
			AppsV1beta1Deployment body = item;
			
			body.getSpec().setSelector(selector);
			
			this.api = this.gfads.getAppsV1BetaApi();
			
			
			this.api.patchNamespacedDeployment(name, namespace, body, null);			
			
		}
	}
}
