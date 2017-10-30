package mainTests;

import java.io.FileNotFoundException;
import java.util.Collections;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.AppsV1beta1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.AppsV1beta1Deployment;
import io.kubernetes.client.models.AppsV1beta1DeploymentList;
import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;
import uniqueInstance.ConfigGFADS;

public class AdalbertoAdaptado {

	public static void setLabelToPods() throws ApiException { 
		// Use the CoreV1Api to get the pods and set their labels
				
	}
	
	public static void main(String[] args) throws ApiException, FileNotFoundException {
		ConfigGFADS gfads = new ConfigGFADS();
		
		
		//ApiClient client = Config.fromUrl("");
		//Configuration.setDefaultApiClient(client);

		//client.setConnectTimeout(10);
		ConfigGFADS.getApiClient().setConnectTimeout(10);
		
		// set pod's labels
		setLabelToPods();
		
		// update the deployment config
		AppsV1beta1Api api = new AppsV1beta1Api(ConfigGFADS.getApiClient()); 

		AppsV1beta1DeploymentList list;

		list = api.listDeploymentForAllNamespaces(null, null, null, null, null, null);
		
		//AppsV1beta1Deployment dep;
		
		for(AppsV1beta1Deployment item : list.getItems()) {
			
			String name = item.getMetadata().getName();
			String namespace = item.getMetadata().getNamespace();
			AppsV1beta1Deployment body = item;
			
			V1LabelSelector selector = new V1LabelSelector();
			selector.putMatchLabelsItem("key1", "value1");
			selector.putMatchLabelsItem("key2", "value2");
			
			// documentation of Selector Requirement
			// https://github.com/kubernetes-client/java/blob/1db144d9bc4690f6ce0137497cac7af0e1bb3f3c/kubernetes/docs/V1LabelSelectorRequirement.md
			/**
			 * o requirement aqui criado por Adalberto não é usado em canto algum!
			 */
			V1LabelSelectorRequirement requirement = new V1LabelSelectorRequirement();
			requirement.setKey("key1");
			requirement.setOperator("In"); 
			requirement.setValues(Collections.singletonList("value1"));
			
			body.getSpec().setSelector(selector);
			
			api.patchNamespacedDeployment(name, namespace, body, null); // it should move re-deploy the pods and moving them
			
		}
				
	}
	
}