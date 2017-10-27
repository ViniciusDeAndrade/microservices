package abstraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Affinity;
import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;
import io.kubernetes.client.models.V1NodeSelector;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodAffinityTerm;
import io.kubernetes.client.proto.V1.NodeSelector;
import uniqueInstance.K8Instance;

public class PodAbstraction {

	
	
	/**
	 * Defines a set of pods (namely those matching the labelSelector relative to the given namespace(s)) 
	 * that this pod should be co-located (affinity) or not co-located (anti-affinity) with, 
	 * where co-located is defined as running on a node whose value of the label with key <topologyKey>
	 * tches that of any node on which a pod of the set of pods is running
	 */
	private V1PodAffinityTerm podAffinityTerm;
	
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
	
	public void updateNodeSelector(V1Pod pod) {	
		//System.out.println("o pod está no nó: "+"\n"+pod.getSpec().toString());
		pod.getSpec().setNodeSelector(this.nodeSelector);
		//pod.getSpec().setNodeName("swarm5");
		System.out.println("e agora está no nó: "+pod.getSpec().getNodeName());
		
	}	
	
}
