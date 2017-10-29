package uniqueInstance;

import io.kubernetes.client.apis.AppsV1beta1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.AppsV1beta1Deployment;

/**
 * this class ensures that there will be a unique instance of the CoreV1Api
 * I did this because I believe that this this objects deals with the runtime of the k8
 * @author vinicius
 *
 */
public class K8Instance {

	private static CoreV1Api api = null;
	private static AppsV1beta1Api apiBeta = null;


	/**
	 * this method will return the unique instance of the k8 runtime deployment object
	 * @return the runtime object
	 */
	
	public static AppsV1beta1Api getAppsV1BetaApi() {
		if(apiBeta ==null)
			apiBeta = new AppsV1beta1Api();
		return apiBeta;
	}

	/**
	 * this method will return the unique instance of the k8 runtime pod object
	 * @return the runtime object
	 */
	public static CoreV1Api getCoreV1ApiInstance() {
		if(api == null)
			api = new CoreV1Api();
		return api;			
	}

}
