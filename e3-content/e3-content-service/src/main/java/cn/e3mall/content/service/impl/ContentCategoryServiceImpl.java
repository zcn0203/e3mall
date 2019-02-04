package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;
/*
 * 内容分类管理service
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		//根据parentId 查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> catList = tbContentCategoryMapper.selectByExample(example);
		//转换成easyTreeNode列表
		List<EasyUITreeNode> nodeList = new ArrayList();
		for (TbContentCategory tbContentCategory : catList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			//添加到返回列表
			nodeList.add(node);
		}
		return nodeList;
	}

	@Override
	public E3Result addContentCategory(long parentId, String name) {
		//创建一个tb_content_category 对应的pojo对象
		TbContentCategory category = new TbContentCategory();
		//设置pojo的属性
		category.setParentId(parentId);
		category.setName(name);
		//1正常 2 删除
		category.setStatus(1);
		category.setSortOrder(1);
		//新添加的节点一定是叶子节点
		category.setIsParent(false);
		category.setUpdated(new Date());
		category.setCreated(new Date());
		//插入到数据库中
		tbContentCategoryMapper.insert(category);
		//主键就在pojo的id属性中
		//判断父节点的isparent属性, 如果不是true,改为true
		//根据parentid来查询父节点
		TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()){
			parent.setIsParent(true);
			//更新到数据库
			tbContentCategoryMapper.updateByPrimaryKey(parent);
		}
		//返回结果,返回e3result,其中包含pojo
		return E3Result.ok();
	}

}
