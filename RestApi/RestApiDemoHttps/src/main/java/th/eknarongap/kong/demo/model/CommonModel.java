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
public class CommonModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5935397136143902634L;
	private String msg;

}
