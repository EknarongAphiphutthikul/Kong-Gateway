package th.eknarongap.kong.demo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleModel implements Serializable {
	
	private static final long serialVersionUID = -2923071986661862255L;
	private String method;
	private String url;
	private String msg;

}
