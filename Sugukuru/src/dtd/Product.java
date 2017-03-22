/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/02
 * 内容　　:商品情報を管理するマスタ。
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class Product {
	/**
	 * @auther 浩生
	 * 2016/11/02
	 * @param productId int　商品ID
	 */
	public int productId;
	/**
	 * @auther 浩生
	 * 2016/11/02
	 * @param productName String　商品名
	 */
	public String productName;
	/**
	 * @auther 浩生
	 * 2016/11/02
	 * @param price int　商品価格
	 */
	public int price;
	/**
	 * @auther 浩生
	 * 2016/11/02
	 * @param categoryId int　カテゴリID
	 */
	public int categoryId;
	/**
	 * @auther 浩生
	 * 2016/11/02
	 * @param stock int　在庫数
	 */
	public int stock;
	/**
	 * @auther 浩生
	 * 2016/11/02
	 * @param secyrityStock int　引き当て在庫数
	 */
	public int secyrityStock;
	/**
	 * @auther 浩生
	 * 2016/11/02
	 * @param nextArriveDate CalendarByKoki　次回入荷日
	 */
	public CalendarByKoki nextArriveDate;
	/**
	 * @auther 浩生
	 * 2016/11/02
	 * @param makerDirectFlg int　メーカー直送可否
	 */
	public int makerDirectFlg=999;
	/**
	 * @auther 浩生
	 * 2016/11/02
	 * @param stoageSpace String　保管場所
	 */
	public String stoageSpace;

	public boolean getMakerDirectFlg() throws Exception{
		if(this.makerDirectFlg==999)throw new Exception("メーカー直送可否が設定されていません");
		if(this.makerDirectFlg==0)return false;
		if(this.makerDirectFlg==1)return true;
		return false;
	}
}
