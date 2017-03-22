/***************************
 * 学籍番号:40024
 * 作成者　:t.ueshima
 * 作成日　:2016/11/02
 * 内容　　:決済情報を管理する
 * *************************/
package dtd;

import beans.CalendarByKoki;

import common.Common.RecallManner;

public class Settlement extends CorporationCustomer{
	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param settlementId int 決済ID
	 */
	public int settlementId;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param customerId String 顧客ID
	 */
	@Request
	public String customerId;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param requestDate CalendarByKoki 請求日付
	 */
	public CalendarByKoki requestDate;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param totalFee int 合計金額(税抜)
	 */
	public int totalFee;

	/**
	 * @auther Tester
	 * 2016/11/05
	 * @param consumptionTax int 消費税
	 */
	public int consumptionTax;

	/**
	 * リクエスト用請求年
	 * @auther 浩生
	 * 2016/11/16
	 * @param settleYear String
	 */
	@Request
	public String settleYear;
	/**
	 * リクエスト用請求月
	 * @auther 浩生
	 * 2016/11/16
	 * @param settleMonth String
	 */
	@Request
	public String settleMonth;
	/**
	 * リクエスト用請求日
	 * @auther 浩生
	 * 2016/11/16
	 * @param settleDay String
	 */
	@Request
	public String settleDay;

	public CalendarByKoki paymentDate;
	{
		this.settleYear=new String();
		this.settleMonth=new String();
		this.settleDay=new String();
	}
	/**
	 * 回収方法を表示用で取得する。
	 *
	 * @auther 浩生 2016/11/14
	 * @return
	 */
	public String getRecallManner() {
		RecallManner manner = RecallManner.indexOf(String.valueOf(this.recallManner));
		if (manner == null)
			return "登録されてない回収方法";
		return manner.value;
	}

	/**
	 * 入金日を取得する。
	 * 入金されてない場合、未入金と返す。
	 * @auther 浩生
	 * 2016/11/16
	 * @return
	 */
	public String getPaymentDate(){
		if(this.paymentDate==null)return "未入金";
		return this.paymentDate.outOfJP();
	}

	/**
	 * 選択状態
	 * リクエスト用
	 * @auther 浩生
	 * 2016/11/17
	 * @param settlementIds String[]
	 */
	@RequestArray
	public String[] settlementIds;

	/**
	 * 請求詳細ID
	 * リクエスト用
	 * @auther 浩生
	 * 2016/11/22
	 * @param rSettlementId String
	 */
	@Request
	public String rSettlementId;
}
