package th.eknarongap.kong.demo.controller;

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

	@GetMapping("get")
	public @ResponseBody String getMethod() {
		return "http v2 get method.";
	}
	
	@PostMapping("post")
	public @ResponseBody CommonModel postMethod(@RequestBody CommonModel req) {
		req.setMsg(req.getMsg() + ", http v2 post method.");
		return req;
	}
}
