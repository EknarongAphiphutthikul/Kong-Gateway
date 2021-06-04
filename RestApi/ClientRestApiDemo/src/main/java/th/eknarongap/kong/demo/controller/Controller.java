package th.eknarongap.kong.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import th.eknarongap.kong.demo.model.CommonModel;
import th.eknarongap.kong.demo.model.ExampleModel;
import th.eknarongap.kong.demo.utils.RestTemplateUtil;

@RestController
public class Controller {
	
	@Autowired
	private RestTemplateUtil restTemplateUtil;

	@PostMapping("example/rest/api")
	public ResponseEntity<CommonModel> postMethod(@RequestBody ExampleModel req, @RequestHeader MultiValueMap<String, String> headers) throws Exception {
		try {
			String message = null;
			HttpHeaders respHeader = null;
			if ("GET".equals(req.getMethod())) {
				ResponseEntity<String> resEntity = restTemplateUtil.get(req.getUrl(), headers, String.class);
				respHeader = resEntity.getHeaders();
				message = resEntity.getBody();
			} else if ("POST".equals(req.getMethod())) {
				ResponseEntity<CommonModel> resEntity = restTemplateUtil.post(req.getUrl(), CommonModel.builder().msg(req.getMsg()).build(), headers, CommonModel.class);
				respHeader = resEntity.getHeaders();
				CommonModel resp = resEntity.getBody();
				message = resp.getMsg();
			} else {
				message = "method invalid.";
			}
			return new ResponseEntity<>(CommonModel.builder().msg(message).header(respHeader.toString()).build(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
