package th.eknarongap.kong.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class TrustStoreConfigBean {

	@Value("${truststore.location}")
	public String trustStoreLocation;
	
	@Value("${truststore.password}")
	public String trustStorePassword;
}
