package abstraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Affinity;
import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;
import io.kubernetes.client.models.V1NodeSelector;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodAffinityTerm;
import io.kubernetes.client.models.V1PodTemplate;
import io.kubernetes.client.models.V1PodTemplateSpec;
import io.kubernetes.client.proto.V1.NodeSelector;
import uniqueInstance.ConfigGFADS;

public class PodAbstraction {	
	
		
	/**
	 * não sei bem o que seria, mas é para povoar o map nodeSelector
	 */
	private String key,value;
	private Map<String, String> nodeSelector = new HashMap<String, String>();
	
	/**
	 * lets try to use the key as the pod name and the value as the node name
	 * @param key
	 * @param value
	 */
	public PodAbstraction(String key, String value) {
		this.key = key;
		this.value = value;
		this.nodeSelector.put(key, value);
	}
	
	public void updateNodeSelector(V1Pod pod) throws ApiException {
		V1PodTemplate template = this.copyPodTemplate(pod);
		V1Pod newPod = new V1Pod();
		
		newPod.setApiVersion(template.getApiVersion());
		newPod.setKind(template.getKind());
		newPod.setMetadata(template.getMetadata());
		newPod.setSpec(template.getTemplate().getSpec());
		//newpod can set status. But the template do not deal with this, apparently
		
		pod.getSpec().setNodeSelector(this.nodeSelector);
		pod.getSpec().setNodeName("swarm5");
		
		String podName = pod.getMetadata().getName();
		String podNamespace = pod.getMetadata().getNamespace();		
		
		//ConfigGFADS.getCoreV1ApiInstance().patchNamespacedPod(podName, podNamespace, pod, null);
		System.out.println("and now it is on node: "+pod.getSpec().getNodeName());
		
	}
	
	/**
	 * V1PodTemplete is discribed as : "a template for creating copies of a predefined pod".
	 * So, we want to deal with it before starting moving a pod from a node to another one
	 * @param pod is the pod you want to copy
	 * @return is the pod template you want to catch for deploy on another node
	 */
	private V1PodTemplate copyPodTemplate(V1Pod pod) {
		//this method copy a pod. I really believe it is all right.
		//you can see that there is a lot of redundance in this api.
		V1PodTemplateSpec spec = new V1PodTemplateSpec();
		V1PodTemplate template = new V1PodTemplate();
		
		spec.setMetadata(pod.getMetadata());
		spec.setSpec(pod.getSpec());
		
		template.setApiVersion(pod.getApiVersion());
		template.setKind(pod.getKind());
		template.setMetadata(pod.getMetadata());
		template.setTemplate(spec);	
		
		return template;
	}
	
}
