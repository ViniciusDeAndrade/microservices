package uniqueInstance;

import java.io.FileNotFoundException;
import java.io.FileReader;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;

public class ConfigGFADS {
	
	private static final String pathToGfadsConfigFile = "/home/vinicius/Documentos/gfads-test.config";
	private static FileReader gfadsConfigFile;
	private static KubeConfig kc;
	private static ApiClient client;
	

	public static void setGfads() throws FileNotFoundException {
		gfadsConfigFile = new FileReader(pathToGfadsConfigFile );
		kc = KubeConfig.loadKubeConfig(gfadsConfigFile);
		client = Config.fromConfig(kc);
		Configuration.setDefaultApiClient(client);		
	}
	
	public static ApiClient getApiClient() {
		return client;
	}

}