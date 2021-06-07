package th.eknarongap.kong.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.eknarongap.kong.demo.model.CommonModel;

@RestController
@RequestMapping("v2")
public class ApiV2 {
	
	@Value("${appName}")
	private String appName;

	@GetMapping("get")
	public @ResponseBody String getMethod() {
		return "https v2 get method. : " + appName;
	}
	
	@PostMapping("post")
	public @ResponseBody CommonModel postMethod(@RequestBody CommonModel req) {
		req.setMsg(req.getMsg() + ", https v2 post method. : " + appName);
		return req;
	}
}
