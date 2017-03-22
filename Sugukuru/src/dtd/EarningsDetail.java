/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:売上詳細情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

public class EarningsDetail {
	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param earningId int 売上ID
	 */
	public int earningId;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param num int 行番号
	 */
	public int num;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param puroductId String 商品ID
	 */
	public String puroductId;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param puroductName String 商品名
	 */
	public String puroductName;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param price int　商品単価
	 */
	public int price;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param taxFee int 消費税
	 */
	public int taxFee;

	/**
	 * @auther Tester
	 * 2016/11/02
	 * @param soldAmount int 売上数
	 */
	public int soldAmount;

	/**
	 * 請求印刷時用データ
	 * @auther 浩生
	 * 2016/11/15
	 * @param buyDate CalendarByKoki
	 */
	public CalendarByKoki buyDate;

	/**
	 * 受注ID
	 * @auther 浩生
	 * 2016/11/15
	 * @param orderId int
	 */
	public int orderId;

	/**
	 * 税抜合計金額を取得する。
	 * @auther 浩生
	 * 2016/11/15
	 * @return
	 */
	public int getNoTaxTotalFee(){
		return this.price*this.soldAmount;
	}

	/**
	 * 消費税合計額を取得する。
	 * @auther 浩生
	 * 2016/11/15
	 * @return
	 */
	public int getTaxTotal(){
		return this.taxFee*this.soldAmount;
	}
	/**
	 * 税込み合計金額を取得する。
	 * @auther 浩生
	 * 2016/11/15
	 * @return
	 */
	public int getTotalFee(){
		return getNoTaxTotalFee()+getTaxTotal();
	}
}
