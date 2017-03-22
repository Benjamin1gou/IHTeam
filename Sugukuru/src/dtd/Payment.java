/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:入金情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

import common.Common;

public class Payment {
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param settlementId int 決済ID
	 */
	public int settlementId;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param num int 行番号
	 */
	public int num;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param paymentDeadline CalendarByKoki 入金期限
	 */
	public CalendarByKoki paymentDeadline;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param paymentDate CalendarByKoki 入金日
	 */
	public CalendarByKoki paymentDate;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param paymentWay int　入金方法
	 */
	public int paymentWay;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param paidPrice int　入金額
	 */
	public int paidPrice;

	/**
	 * 入金方法を文字列で取得する。
	 * @auther 浩生
	 * 2016/11/22
	 * @return
	 */
	public String getPaymentWay(){
		return Common.RecallManner.indexOf(String.valueOf(this.paymentWay)).value;
	}
}
