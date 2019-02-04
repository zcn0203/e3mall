package cn.e3mall.portal.controller;
/*
 * 首页展示Controller
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	@Value("${CONTENT_LUNBO_ID}")
	private Long CONTENT_LUNBO_ID;
	
	@RequestMapping("/index")
	public String showIndex(Model model){
		//查询内容列表
		List<TbContent> ad1List = contentService.getContentListByCid(89);
		//把结果传递给页面
		model.addAttribute("ad1List",ad1List);
		return "index";
	}

}
