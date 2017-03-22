/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/02
 * 内容　　:受注詳細を管理するファイル。
 * *************************
 * 更新日　:2016/11/08
 * 更新者　:k.koki
 * 内容　　:DB変更に伴い、変数の追加。
 * *************************/
package dtd;

import common.Common;



public class OrderDetail implements Common{
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param orderId int 受注ID
	 */
	public int orderId;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param num int 行番号
	 */
	public int num;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param productName String 商品ID
	 */
	public String productId;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param productName String 商品名
	 */
	public String productName;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param price int 商品単価
	 */
	public int price;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param cunsumntionTax int 消費税
	 */
	public /*int*/double cunsumntionTax;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param amount int 商品数量
	 */
	public int amount;

	/**
	 * 作業ステップ
	 * 	0…未作業
	 *	1…ピッキング済
	 *	2…検品済み
	 *	3…小分け済み
	 * @auther 浩生
	 * 2016/11/08
	 * @param step int
	 */
	public int step;
	/**
	 * 納品受領フラグ
	 * @auther 浩生
	 * 2016/11/08
	 * @param productDeliveredFlg int
	 */
	public int productDeliveredFlg;
	/**
	 * 備考（納品不可の理由など）
	 * @auther 浩生
	 * 2016/11/08
	 * @param note String
	 */
	public String note;

	/**
	 * 表示用作業ステップを取得する。
	 * @auther 浩生
	 * 2016/11/08
	 * @return
	 */
	public String stepStr(){
		switch(this.step){
			case STEP0:
				return "未作業";
			case STEP1:
				return "ピッキング作業";
			case STEP2:
				return "検品済み";
			case STEP3:
				return "小分け済み";
			default:
				return "";
		}
	}
	/**
	 * 納品日
	 * @auther 浩生
	 * 2016/11/08
	 * @param deliveredDate CalendarByKoki
	 */
	//public CalendarByKoki deliveredDate;

	/**
	 * この受注詳細の進捗状態を％データで取得する。
	 * @auther 浩生
	 * 2016/11/08
	 * @return
	 */
	public int stepPar(){
		return this.step*30;
	}



}
