/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/22
 * 内容　　:請求詳細画面に表示する箱クラス。
 * *************************/
package dtd;

import java.util.ArrayList;

import beans.CalendarByKoki;

public class PaymentDetail {
	/**
	 * 請求ID
	 * @auther 浩生
	 * 2016/11/22
	 * @param settlemntId int
	 */
	public int settlemntId;
	/**
	 * 顧客ID
	 * @auther 浩生
	 * 2016/11/22
	 * @param customerId String
	 */
	public String customerId;
	/**
	 * 顧客名
	 * @auther 浩生
	 * 2016/11/22
	 * @param customerName String
	 */
	public String customerName;
	/**
	 * 請求日
	 * @auther 浩生
	 * 2016/11/22
	 * @param requestDate CalendarByKoki
	 */
	public CalendarByKoki requestDate;
	/**
	 * 繰り越し金
	 * @auther 浩生
	 * 2016/11/22
	 * @param overPrice int
	 */
	public int overPrice;
	/**
	 * 税抜合計金額
	 * @auther 浩生
	 * 2016/11/22
	 * @param totalFee int
	 */
	public int totalFee;
	/**
	 * 消費税
	 * @auther 浩生
	 * 2016/11/22
	 * @param tax int
	 */
	public int tax;
	/**
	 * 入金リスト
	 * @auther 浩生
	 * 2016/11/22
	 * @param payedList ArrayList<Payment>
	 */
	public ArrayList<Payment> payedList;

	/**
	 * 総合計金額
	 * @auther 浩生
	 * 2016/11/22
	 * @return
	 */
	public int allFee(){
		return this.overPrice+this.totalFee+this.tax;
	}
	{
		this.settlemntId=0;
		this.customerId=new String();
		this.customerName=new String();
		this.payedList=new ArrayList<Payment>();
		this.requestDate=new CalendarByKoki();
	}

}
