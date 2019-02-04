package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;

/*
 * 内容分类管理Controller
 */

@Controller
public class ContentCatController {
	
	@Autowired
	private ContentCategoryService categoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0")Long parentId){
		List<EasyUITreeNode> list = categoryService.getContentCatList(parentId);
		return list;
	}
	
	/*
	 * 添加分类节点
	 */
	@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
	@ResponseBody
	public E3Result createContentCategory(Long parentId, String name){
		//调用服务来添加节点
		E3Result e3Result = categoryService.addContentCategory(parentId, name);
		
		return e3Result;
	}

}
