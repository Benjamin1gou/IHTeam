/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/06
 * 内容　　:受注一覧画面で使用する箱テーブル
 * *************************/
package dtd;




public class OrderRecodeList extends Order{
	/**
	 * 発送状態
	 * @auther 浩生
	 * 2016/11/06
	 * @param dispatchState String
	 */
	@Request
	public String dispatchState;
	/**
	 * 作成年リクエスト用
	 * @auther 浩生
	 * 2016/11/06
	 * @param createYear String
	 */
	@Request
	public String createYear;
	/**
	 * 作成月リクエスト用
	 * @auther 浩生
	 * 2016/11/06
	 * @param createMonth String
	 */
	@Request
	public String createMonth;
	/**
	 * 作成日リクエスト用
	 * @auther 浩生
	 * 2016/11/06
	 * @param createDay String
	 */
	@Request
	public String createDay;

	/**
	 * 請求状態リクエスト用
	 * @auther 浩生
	 * 2016/11/06
	 * @param claimState String
	 */
	@Request
	public String claimState;

	/**
	 * 請求状態を管理する。
	 * @auther 浩生
	 * 2016/11/07
	 * @param settlement Settlement
	 */
	public Settlement settlement;
	/**
	 * 発送状態を管理する。
	 * @auther 浩生
	 * 2016/11/07
	 * @param shipment Shipment
	 */
	public Shipment shipment;
	{
		this.shipment=new Shipment();
		this.settlement=new Settlement();
	}
	/**
	 * 発送状態の画面用文字列を取得する。
	 * @auther 浩生
	 * 2016/11/07
	 * @return
	 */
	public String getShipmentState(){
		if(this.shipment==null)return "未完了";
		if(this.shipment.shipmentFlg==shipment.AFTER)return "完了";
		return "未完了";
	}
	/**
	 * 請求状態の画面用文字列を取得する。
	 * @auther 浩生
	 * 2016/11/07
	 * @return
	 */
	public String getSettlemntState(){
		if(this.settlement==null)return "未完了";
		return "完了";
	}



}
