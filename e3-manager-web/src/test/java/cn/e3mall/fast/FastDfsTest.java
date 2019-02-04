package cn.e3mall.fast;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDfsTest {
	
	@Test
	public void testUpload() throws Exception, IOException, MyException{
		//创建一个配置文件,文件名任意 内容就是tracker服务器的地址
		//使用全局对象来加载配置文件
		ClientGlobal.init("F:/e3mall/e3-manager-web/src/main/resources/conf/client.conf");
		//创建一个trackerClient对象 
		TrackerClient trackerClient = new TrackerClient();
		//通过trackerClient来获得一个trackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//创建一个StorageServer的引用,可以是null
		StorageServer storageServer = null;
		//创建一个StorageClient,参数需要trackerServer和storageServer
		StorageClient storageClient = new StorageClient(trackerServer,storageServer);
		//使用StorageClient来上传文件
		//‪C:/Users/春宁/Pictures/20130428101328_ivasE.jpeg
		String[] upload_file = storageClient.upload_file("C:/Users/春宁/Pictures/20130428101328_ivasE.jpeg", "jpeg", null);
		for (String string : upload_file) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastDfsClient()throws Exception{
		
		FastDFSClient fastDFSClient = new FastDFSClient("F:/e3mall/e3-manager-web/src/main/resources/conf/client.conf");
		String string = fastDFSClient.uploadFile("C:/Users/春宁/Pictures/2015062409554099.jpg");
		System.out.println(string);
		
	}

}
