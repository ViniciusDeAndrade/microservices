package mainTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.AppsV1beta1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import k8_object.ObjectsDealer;

public class Verify {
	public static void main(String[] args) throws FileNotFoundException, ApiException {
		//setting the environment
		final String pathToGfadsConfigFile = "/home/vinicius/Documentos/gfads-test.config";
		FileReader gfadsConfigFile = new FileReader(pathToGfadsConfigFile);
		KubeConfig kc = KubeConfig.loadKubeConfig(gfadsConfigFile);
		ApiClient client = Config.fromConfig(kc);
		Configuration.setDefaultApiClient(client);
		
		//getting the environment
		CoreV1Api core = new CoreV1Api();
		AppsV1beta1Api beta = new AppsV1beta1Api();
		
		//dealing with pods		
		ObjectsDealer k8objects = new ObjectsDealer();
		String podName = "kube-proxy-8ktf4";
		V1Pod pod = k8objects.findPod(core,podName);
		
		//setting labels to pods
		String key = "key";
		String operator = "In";
		String value = "value";
		pod = k8objects.setLabelsToPods(core, podName, key, podName, operator, Collections.singletonList(value));
		System.out.println("got here");
		
		core.patchNamespacedPod(podName, pod.getMetadata().getNamespace(), pod, null);
		
		System.out.println("it works");		
	}
}
