package th.eknarongap.kong.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.eknarongap.kong.demo.model.CommonModel;
import th.eknarongap.kong.demo.model.ExampleModel;
import th.eknarongap.kong.demo.utils.RestTemplateUtil;

@RestController
public class Controller {
	
	@Autowired
	private RestTemplateUtil restTemplateUtil;

	@PostMapping("example/rest/api")
	public @ResponseBody CommonModel postMethod(@RequestBody ExampleModel req) throws Exception {
		String message = null;
		if ("GET".equals(req.getMethod())) {
			message = restTemplateUtil.get(req.getUrl(), String.class);
		} else if ("POST".equals(req.getMethod())) {
			CommonModel resp = restTemplateUtil.post(req.getUrl(), CommonModel.builder().msg(req.getMsg()).build(), CommonModel.class);
			message = resp.getMsg();
		} else {
			message = "method invalid.";
		}
		return CommonModel.builder().msg(message).build();
	}
}
