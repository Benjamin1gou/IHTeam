/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/07
 * 内容　　:倉庫部が受注一覧を表示する際のDTDクラス。
 * *************************/
package dtd;

import java.util.ArrayList;

import beans.Constants.Constant;

public class StockOrderList extends Order {
	/**
	 * 出荷年
	 *
	 * @auther 浩生 2016/11/07
	 * @param shipmentYear
	 *            String
	 */
	@Request
	public String shipmentYear="";
	/**
	 * 出荷月
	 *
	 * @auther 浩生 2016/11/07
	 * @param shipmentMonth
	 *            String
	 */
	@Request
	public String shipmentMonth="";
	/**
	 * 出荷日
	 *
	 * @auther 浩生 2016/11/07
	 * @param shipmentDay
	 *            String
	 */
	@Request
	public String shipmentDay="";
	/**
	 * 受注IDリクエスト用
	 *
	 * @auther 浩生 2016/11/07
	 * @param rOrderId
	 *            String
	 */
	@Request
	public String rOrderId="";

	/**
	 * 受注詳細リスト
	 *
	 * @auther 浩生 2016/11/08
	 * @param orderDetails
	 *            ArrayList<OrderDetail>
	 */
	public ArrayList<OrderDetail> orderDetails;
	{
		this.orderDetails = new ArrayList<OrderDetail>();
	}
	/**
	 * 進捗状態を％データで返す。
	 *
	 * @auther 浩生 2016/11/08
	 * @return
	 */
	public int stepParsent() {
		if (this.shipmentDate != null) {
			//出荷日時がある場合は出荷済みとする。
			return 100;
		}
		int pasent = 0;
		int count = 0;
		for (OrderDetail detail : this.orderDetails) {
			pasent = pasent + detail.stepPar();
			count++;
		}
		return pasent / count;
	}

	/**
	 * 受領状態を取得する。
	 *
	 * @auther 浩生 2016/11/08
	 * @return
	 */
	public String getState() {
		if(getStateInt()==0)return "未発送";
		if(getStateInt()==1)return "<span style=\"color:red\">拒否あり</span>";
		return "<span style=\"color:green\">受領</span>";
	}
	/**
	 * 受領状態をflgで返す。
	 * @auther 浩生
	 * 2016/11/09
	 * @return
	 */
	public int getStateInt(){
		for (OrderDetail detail : this.orderDetails) {
			if (detail.productDeliveredFlg == 1) {
				return 1;
			}
		}
		return 0;
	}
	/**
	 * 納品日の画面用文字列を取得する。
	 *
	 * @auther 浩生 2016/11/08
	 * @return
	 */
	public String getDeliveryDate() {
		if (this.deliveryDate == null) {
			return "未設定";
		}
		return this.deliveryDate.outOfJP();
	}
	/**
	 * 検索条件の受領状態を取得する。
	 * リクエスト用
	 * @auther 浩生
	 * 2016/11/09
	 * @param delFlg String
	 */
	@Request
	public String delFlg;
	private String[] delFlgOption={
			"<option value=\"-1\" selected>未選択</option>"
			,"<option value=\"0\" selected>受領</option>"
			,"<option value=\"1\" selected>拒否あり</option>"
	};
	/**
	 * 画面に表示するセレクトボックスのhtmlを取得する。
	 * @auther 浩生
	 * 2016/11/09
	 * @return
	 */
	public String getStateHtml(Constant constant){
		StringBuffer html=new StringBuffer();
		html.append("<select name=\""+constant.pgName+"\">");
		int count=-1;
		if(delFlg==null){
			html.append("<option value=\"-1\" selected>未選択</option>"
					+"<option value=\"0\">受領</option>"
					+"<option value=\"1\">拒否あり</option>");
			html.append("</select>");
			return html.toString();
		}
		for(String option:delFlgOption){
			if(!delFlg.equals(String.valueOf(count++))){
				option=option.replace("selected", "");
			}
			html.append(option);
		}
		html.append("</select>");
		return html.toString();
	}
	/**
	 * リストの一行ごとに検索結果にそぐわないものかを判断します。
	 * @param search
	 * @return
	 */
	public String ifDelFlgShow(StockOrderList search){
		if(search.delFlg==null)return "yes";
		if(search.delFlg.equals("-1"))return "yes";
		if(search.delFlg.equals(String.valueOf(this.getStateInt())))return "yes";
		return "no";
	}
}
