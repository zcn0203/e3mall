package cn.e3mall.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrCloud {
	
	@Test
	public void testAddDocument() throws Exception, IOException{
		//创建一个集群的连接 使用CloudSolrServer
		CloudSolrServer solrServer = 
				new CloudSolrServer("192.168.25.132:2181,192.168.25.132:2182,192.168.25.132:2183");
		//需要,zkHost:参数zookeeper的地址列表
		//设置一个默认的collection(defaultCollection)
		solrServer.setDefaultCollection("collection2");
		//创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		//向文档中添加域
		document.setField("id", "solrCoud01");
		document.setField("item_title", "测试商品01");
		document.setField("item_price", "100000");
		//把文件写入索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}

}
