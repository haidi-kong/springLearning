package com.ilearning.pay.httpUtil;

import com.alibaba.fastjson.JSONObject;
import com.ilearning.pay.dal.dataobject.parent.ParentDO;
import com.ilearning.pay.dal.dataobject.parentItem.ParentItemDO;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserPaymentUtil {

	private static void createOrder(int count) throws Exception {
		//生成订单
		for(int i=0;i<count;i++) {
			int userId = (int) (Math.random()*100);
			ParentDO parentDO = new ParentDO();
			ParentItemDO parentItemDO = new ParentItemDO();
			parentDO.setUserId(userId);
			parentDO.setStatus("0");
			String res =  CloseableHttpClientUtil.doPost("http://localhost:48080/pay/parent/create", (JSONObject) JSONObject.toJSON(parentDO));
			JSONObject resJson = JSONObject.parseObject(res);
			long parentId = Long.parseLong(resJson.getString("data"));
			parentItemDO.setOrderId(parentId);
			parentItemDO.setUserId(userId);
			parentItemDO.setStatus("0");
			String res2 =  CloseableHttpClientUtil.doPost("http://localhost:48080/pay/parent-item/create", (JSONObject) JSONObject.toJSON(parentItemDO));
			long parentItemId = Long.parseLong(resJson.getString("data"));
		}

//		//插入数据库
//		Connection conn = DBUtil.getConn();
//		String sql = "insert into miaosha_account(balance_amount, user_id, create_time, update_time)values(?,?,?,?)";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		for(int i=0;i<users.size();i++) {
//			MiaoshaUserAccount user = users.get(i);
//			pstmt.setBigDecimal(1, user.getBalanceAmount());
//			pstmt.setLong(2, user.getUserId());
//			pstmt.setTimestamp(3, new Timestamp(user.getCreateTime().getTime()));
//			pstmt.setTimestamp(4, new Timestamp(user.getCreateTime().getTime()));
//			pstmt.addBatch();
//		}
//		pstmt.executeBatch();
//		pstmt.close();
//		conn.close();
//		System.out.println("insert to db");
		//登录，生成token

//		File file = new File("D:/tokens2.txt");
//		if(file.exists()) {
//			file.delete();
//		}
//		RandomAccessFile raf = new RandomAccessFile(file, "rw");
//		file.createNewFile();
//		raf.seek(0);
//		for(int i=0;i<users.size();i++) {
//			MiaoshaUserAccount user = users.get(i);
//
////			JSONObject jo = JSON.parseObject(response);
////			String token = jo.getString("data");
//			System.out.println("create orderId : " + user.getUserId());
//			String orderId = getOrderId(user.getUserId().toString());
//			String token = UserUtil.getToken(user.getUserId().toString());
//			String row = user.getUserId()+","+token+","+orderId;
//			raf.seek(raf.length());
//			raf.write(row.getBytes());
//			raf.write("\r\n".getBytes());
//			System.out.println("write to file : " + user.getId());
//		}
//		raf.close();
//
//		System.out.println("over");
	}




	public static void main(String[] args)throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for (int i = 0 ; i < 100; i++) {
			executorService.execute(() -> {
				try {
					createOrder(10000);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			});
		}


		//createOrder(1000);

	}
}
