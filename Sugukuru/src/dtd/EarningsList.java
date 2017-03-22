/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/12
 * 内容　　:売上一覧画面から検索条件を受け取る箱クラス。
 * *************************/
package dtd;



public class EarningsList extends Earnings{
	/**
	 * 売上年
	 *リクエスト用
	 * @auther 浩生
	 * 2016/11/12
	 * @param earningsYear String
	 */
	@Request
	public String earningsYear;
	/**
	 * 売上月
	 * リクエスト用
	 * @auther 浩生
	 * 2016/11/12
	 * @param earningsMonth String
	 */
	public String earningsMonth;
	/**
	 * 受注ID
	 * リクエスト用
	 * @auther 浩生
	 * 2016/11/12
	 * @param rOrderId String
	 */
	@Request
	public String rOrderId;
	{
		this.earningsYear=new String();
		this.earningsMonth=new String();
		this.rOrderId=new String();
	}

}
