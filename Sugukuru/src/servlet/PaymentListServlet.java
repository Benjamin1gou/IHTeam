/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/16
 * 内容　　:入金ファイルを読み込みそれをリスト化、読み込む
 * 			結局、作りません。
 * *************************/
package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CalendarByKoki;
import beans.Constants;
import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;
import beans.Message;
import beans.Recode;

import common.Database;

import dtd.Payment;
import dtd.PaymentDetail;
import dtd.Settlement;

/**
 * Servlet implementation class PaymentListServlet
 */
@WebServlet("/PaymentListServlet")
public class PaymentListServlet extends HttpServlet implements Database{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//コンスタント生成
		Constants constants=new Constants(this, request);
		//入金ファイルを読み込む。
		Recode.readRecode(this);
		//請求IDを取得する。
		Settlement search=new Settlement();
		search=(Settlement) constants.decodeRequest(search);
		//リクエスト変数
		PaymentDetail paymentDetail=new PaymentDetail();
		if(search.rSettlementId==null){
			//詳細指定なし：警告モードにしてpayment_list.jspに戻る
			Message message=new Message(constants);
			//message.doWarnig("", constantId);
		}else if(search.rSettlementId.isEmpty()){
			//詳細指定なし
		}else{
			//検索SQL生成。
			DBManager dbManager=null;
			try{
				dbManager=new DBManager(DBName);
				//請求情報を取得する。
				PreparedStatementByKoki selectSettlement=dbManager.getStatementByKoki(InspectionValue.readSql(this, "SelectClaimList.sql"));
				selectSettlement.setInt("SETTLEMENT_ID", Integer.parseInt(search.rSettlementId));
				for(ArrayList<String> row:selectSettlement.select()){
					paymentDetail.customerId=row.get(3);
					paymentDetail.customerName=row.get(4);
					paymentDetail.settlemntId=Integer.parseInt(row.get(0));
					paymentDetail.requestDate=new CalendarByKoki(row.get(2));
					paymentDetail.overPrice=Integer.parseInt(row.get(7));
					paymentDetail.totalFee=Integer.parseInt(row.get(8));
					paymentDetail.tax=Integer.parseInt(row.get(9));
				}
				//入金情報を取得する。
				PreparedStatementByKoki selectPayment=dbManager.getStatementByKoki(InspectionValue.readSql(this, "SelectPayment.sql"));
				selectPayment.setInt("SETTLEMENT_ID", paymentDetail.settlemntId);
				for(ArrayList<String> row:selectPayment.select()){
					Payment payment=new Payment();
					payment.settlementId=paymentDetail.settlemntId;
					payment.num=Integer.parseInt(row.get(0));
					payment.paymentDate=new CalendarByKoki(row.get(1));
					payment.paymentWay=Integer.parseInt(row.get(2));
					payment.paidPrice=Integer.parseInt(row.get(3));
					paymentDetail.payedList.add(payment);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//リクエスト設定
			request.setAttribute("paymentDetail",paymentDetail);
			constants.forward(request, response);
		}
	}



}
