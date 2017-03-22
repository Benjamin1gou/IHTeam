/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/07
 * 内容　　:新規受注書を作成するクラスと与信限度額などを行うクラス。
 * *************************/
package dtd;

import java.util.ArrayList;

import common.Common;

public class CreateOrder extends Order{
	public ArrayList<OrderDetail> orderDetails;
	{
		this.orderDetails=new ArrayList<OrderDetail>();
	}
	/**
	 * 商品詳細を追加します。
	 * super.Orderを設定してから使用してください。
	 * @auther 浩生
	 * 2016/11/07
	 * @param productId
	 * @param productName
	 * @param price
	 * @param amount
	 */
	public void addOrder(String productId,String productName,int price,int amount){
		OrderDetail detail=new OrderDetail();
		detail.productId=productId;
		detail.productName=productName;
		detail.price=price;
		detail.amount=amount;
		detail.num=this.orderDetails.size()+1;
		detail.orderId=super.orderId;
		detail.cunsumntionTax=Common.TAX;
		this.orderDetails.add(detail);
	}
	
}
