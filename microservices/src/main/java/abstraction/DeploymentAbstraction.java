package abstraction;

import java.io.FileNotFoundException;
import java.util.Collections;

import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.AppsV1beta1Deployment;
import io.kubernetes.client.models.AppsV1beta1DeploymentList;
import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;
import uniqueInstance.ConfigGFADS;

public class DeploymentAbstraction {

	private ConfigGFADS gfads ;
	
	public DeploymentAbstraction() throws FileNotFoundException {
		this.gfads = new ConfigGFADS();
	}
	
	
	public void patchDeployment() throws ApiException {		
		
		AppsV1beta1DeploymentList list = this.gfads.getAppsV1BetaApi().listDeploymentForAllNamespaces(null, null, null, null, null, null);
		LabelAbstraction label = new LabelAbstraction();
		
		for(AppsV1beta1Deployment item: list.getItems()) {
			String name = item.getMetadata().getName();
			String namespace = item.getMetadata().getNamespace();
			AppsV1beta1Deployment body = item;
		
			V1LabelSelectorRequirement requirement = 
					label.dealLabelRequirement("key", "in", Collections.singletonList("List values of strings"));
			
			V1LabelSelector selector = label.dealLabelSelector(key, matchLabelsItem)
			
			
		}
	}
}
