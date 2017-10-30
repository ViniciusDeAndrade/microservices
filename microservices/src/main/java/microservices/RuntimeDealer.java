package microservices;

import java.io.FileNotFoundException;
import java.io.FileReader;

import abstraction.PodAbstraction;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Affinity;
import io.kubernetes.client.models.V1LabelSelector;
import io.kubernetes.client.models.V1LabelSelectorRequirement;
import io.kubernetes.client.models.V1Node;
import io.kubernetes.client.models.V1NodeList;
import io.kubernetes.client.models.V1NodeSelector;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodAffinityTerm;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.proto.Meta.LabelSelector;
import io.kubernetes.client.proto.V1.PodAffinity;
import io.kubernetes.client.proto.V1.PodAffinityTerm;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import uniqueInstance.ConfigGFADS;

public class RuntimeDealer {
/*
	private static final String pathToGfadsConfigFile = 
			"/home/vinicius/Documentos/gfads-test.config";
	private FileReader gfadsConfigFile;
	private KubeConfig kc;
	private CoreV1Api api;
	private ApiClient client;

	//será que usarei?
	private V1LabelSelector labelSelector;
	private V1PodAffinityTerm podAffinityTerm;
	private V1Affinity affinity;
	private V1NodeSelector nodeSelector;
	private V1LabelSelectorRequirement labelSelectorRequirement = new V1LabelSelectorRequirement();


	public RuntimeDealer() throws FileNotFoundException {
		this.gfadsConfigFile = new FileReader(pathToGfadsConfigFile );
		this.kc = KubeConfig.loadKubeConfig(gfadsConfigFile);
		this.client = Config.fromConfig(kc);
		Configuration.setDefaultApiClient(client);
		this.api = new CoreV1Api();
	}
*/
	
	
	private ConfigGFADS gfads ;
	private CoreV1Api api = gfads.getCoreV1ApiInstance();
	
	
	public RuntimeDealer() throws FileNotFoundException {
		this.gfads = new ConfigGFADS();
		
	}
	
	
	/**
	 * this method should be used when you want to migrate a microservice (a pod) 
	 * to another node 
	 * @param microserviceName is the name of the microservice that you want to use to move. 
	 * For now, it is the pod name
	 * This parameter is used as a ID
	 * @param sourceHost is the place  where the microservice is running
	 * @param targetHost is the place where you want the microservice to run
	 * @return it will return true if this operation occurs without any erros
	 * @throws ApiException 
	 */
	public boolean move(String microserviceName, String sourceHost, String targetHost) throws ApiException {
		V1Pod pod = this.searchPod(microserviceName);
		V1Node node = this.searchNode(targetHost);

		//falta implementar aqui. CRÍTICO
		this.deployPodOnNode(pod, node);

		//falta implementar aqui. CRÍTICO
		//this.updateNode(pod, sourceHost);

		return true;

	}

	//aqui eu parei
	public void deployPodOnNode(V1Pod pod, V1Node node) throws ApiException {
		String podToMove = pod.getMetadata().getName();
		String nodeWhereToMove = node.getMetadata().getName();
				
		PodAbstraction podAbstraction = new PodAbstraction(podToMove, nodeWhereToMove);
		//operação crítica
		podAbstraction.updateNodeSelector(pod);
	}

	public void updateNode(V1Pod pod, String oldHost) {
		pod.setSpec(null);

	}


	/**
	 * this method looks for a node on a k8s application by the node name
	 * @param nodeName is the node you want to look for
	 * @return when this method find the node, it will be returned.
	 * @throws ApiException
	 */
	private V1Node searchNode(String nodeName) throws ApiException {
		V1NodeList list = this.api.listNode(null, null, null, null, null, null);
		for(V1Node node: list.getItems())
			if(node.getMetadata().getName().equals(nodeName))
				return node;
		return null;
	}

	/**
	 * this method looks for the pod on a k8s application by the pod name
	 * @param podName is the pod you want to look for
	 * @return when this method find the pod, it will be returned.
	 * @throws ApiException
	 */
	private V1Pod searchPod(String podName) throws ApiException {
		V1PodList list = this.api.listPodForAllNamespaces(null, null, null, null, null , null);
		for(V1Pod pod: list.getItems())
			if(pod.getMetadata().getName().equals(podName))
				return pod;

		return null;
	}

}
