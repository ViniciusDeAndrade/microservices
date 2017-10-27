package UniqueInstance;

import io.kubernetes.client.apis.CoreV1Api;

/**
 * this class ensures that there will be a unique instance of the CoreV1Api
 * I did this because I believe that this this objects deals with the runtime of the k8
 * @author vinicius
 *
 */
public class K8Instance {
	
	private static CoreV1Api api = null;
			
	/**
	 * this method will return the unique instance of the k8 runtime object
	 * @return the runtime object
	 */
	public static CoreV1Api getInstance() {
		if(api == null)
			api = new CoreV1Api();
		return api;			
	}

}
