package th.eknarongap.kong.demo.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.cryptacular.bean.KeyStoreFactoryBean;
import org.cryptacular.io.FileResource;
import org.cryptacular.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import th.eknarongap.kong.demo.config.TrustStoreConfigBean;

@Component
public class RestTemplateUtil {

	private static RestTemplate restTemplate;
	private static String trustLocation = null;
	private static String trustPassword = null;

	@Autowired
	public synchronized void init(TrustStoreConfigBean trustConfig) {
		RestTemplateUtil.trustLocation = trustConfig.trustStoreLocation;
		RestTemplateUtil.trustPassword = trustConfig.trustStorePassword;
	}

	@PostConstruct
	public void initRestTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		synchronized (this) {
			final int pool = 50;
			final int timeout = 1500;

			final Resource fileResource = new FileResource(new File(RestTemplateUtil.trustLocation));
			final KeyStore trustStore = new KeyStoreFactoryBean(fileResource, "JKS", RestTemplateUtil.trustPassword).newInstance();
			final SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStore, (x509Certificates, s) -> false).setProtocol("TLSv1.2").build();
			final SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
		            sslContext,
		            new String[] { "TLSv1.2" },
		            null,
		            SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
	            .register("http", PlainConnectionSocketFactory.getSocketFactory())
	            .register("https", sslSocketFactory)
	            .build();
	        
	        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
			connectionManager.setMaxTotal(pool);
			connectionManager.setDefaultMaxPerRoute(pool);
			
			final HttpClient httpClient = HttpClients.custom()
					.setSSLSocketFactory(sslSocketFactory)
		            .setConnectionManager(connectionManager)
		            .build();

			HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
			clientHttpRequestFactory.setReadTimeout(Integer.valueOf(timeout));
			clientHttpRequestFactory.setConnectTimeout(Integer.valueOf(timeout));

			restTemplate = new RestTemplate(clientHttpRequestFactory);
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		}
	}

	public <T> T get(String url, Class<T> responseClass) throws Exception {
		return restTemplate.getForObject(url, responseClass);
	}

	public <T> T post(String url, Object request, Class<T> responseClass) throws Exception {
		HttpEntity<Object> requestEntity = new HttpEntity<>(request);
		return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseClass).getBody();
	}

}
