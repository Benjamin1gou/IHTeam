/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/16
 * 内容　　:請求済みを一覧する。
 * *************************/
package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CalendarByKoki;
import beans.Constants;
import beans.Constants.Action;
import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;
import beans.Message;
import beans.Message.MessageInterface.MODE;

import common.Database;

import dtd.DoClaim;
import dtd.EarningsDetail;
import dtd.Settlement;

/**
 * Servlet implementation class ClaimListServlet
 */
@WebServlet("/ClaimListServlet")
public class ClaimListServlet extends HttpServlet implements Database {
	private static final long serialVersionUID = 1L;

	private Constants constants;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClaimListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.constants = new Constants(this, request);

		// モードの取得
		Action action = constants.getMode();
		switch (action) {
		case Search:
			actionSearch(response);
			break;
		case Insert:
			actionPrint(response);
			break;
		case Detail:
			break;

		}

	}

	private void actionSearch(HttpServletResponse response)
			throws ServletException, IOException {
		// 検索条件の取得
		Settlement search = new Settlement();
		search = (Settlement) constants.superDecodeRequest(search);
		HttpServletRequest request = constants.getRequest();
		DBManager dbManager = null;
		//メッセージの作成
		Message message=new Message(this.constants);
		// 検索条件チェック
		try {
			dbManager = new DBManager(DBName);
			PreparedStatementByKoki statementByKoki = dbManager
					.getStatementByKoki(InspectionValue.readSql(this,
							"SearchSettlement.sql"));
			// 顧客IDのチェック
			if (search.customerId == null) {
				// 顧客IDが取得不能
				statementByKoki.toNull("CUSTOMER_ID");
				message.doErrer("02", "");
			} else if (search.customerId.isEmpty() == true) {
				// 顧客IDが空白
				statementByKoki.toNull("CUSTOMER_ID");
			} else {
				statementByKoki.setString("CUSTOMER_ID", search.customerId);
			}
			// 請求日のチェック
			if (search.settleYear.isEmpty() == false
					&& search.settleMonth.isEmpty() == false
					&& search.settleDay.isEmpty() == false) {
				CalendarByKoki shipmentCalendar = CalendarByKoki
						.newInstance(search.settleYear, search.settleMonth,
								search.settleDay);
				if (shipmentCalendar == null) {
					// 請求日生成エラー
					statementByKoki.toNull("REQUEST_DATE");
					message.doWarnig("03", "11");
				} else {
					statementByKoki.setString("REQUEST_DATE",
							shipmentCalendar.outSQLDate());
				}
			} else {
				statementByKoki.toNull("REQUEST_DATE");
			}

			//メッセージが警告モードの時、
			//メッセージを設定して処理を終了させる。
			if(message.nowMode()==MODE.WARNIG){
				request.setAttribute("message", message);
				this.constants.forward(request, response);
				return;
			}

			// SQLクリーン
			statementByKoki.cleanSql();
			System.out.println(statementByKoki.out());
			// SQL実行
			ArrayList<ArrayList<String>> list = statementByKoki.select();
			if (list.size() == 0) {
				// 検索結果なし
			} else {
				// レスポンス用変数
				ArrayList<Settlement> settlements = new ArrayList<Settlement>();
				for (ArrayList<String> row : list) {
					Settlement settlement = new Settlement();
					settlement.settlementId = Integer.parseInt(row.get(0));
					settlement.customerId = row.get(1);
					settlement.totalFee = Integer.parseInt(row.get(3));
					settlement.consumptionTax = Integer.parseInt(row.get(4));
					settlement.requestDate = new CalendarByKoki(row.get(5));
					settlement.paymentDate = CalendarByKoki.newInstance(row
							.get(6));
					settlement.customerName = row.get(7);
					settlement.recallManner = Integer.parseInt(row.get(8));
					settlements.add(settlement);
				}
				request.setAttribute("claimList", settlements);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		constants.forward(request, response);
	}
	private void actionPrint(HttpServletResponse response)throws IOException, ServletException{
		//印刷物取得
		Settlement settlement=new Settlement();
		settlement=(Settlement)this.constants.superDecodeRequest(settlement);
		//メッセージ作成
		Message message=new Message(this.constants);
		HttpServletRequest request=this.constants.getRequest();
		// 印刷データ変数
		ArrayList<DoClaim> printList = new ArrayList<DoClaim>();
		//選択のチェック
		if(settlement.settlementIds==null){
			//選択取得不能：異常モードにしてclaim_list.jspに返す。
			message.doErrer("02", "");
		}else if(settlement.settlementIds.length==0){
			//選択なし：警告モードにしてclaim_list.jspに返す。
			message.doWarnig("02", "07");
			request.setAttribute("message", message);
			this.constants.forward(request, response);
			return;
		}else{
			DBManager dbManager=null;
			for(String settlementId:settlement.settlementIds){
				DoClaim claim=new DoClaim();
				try{
					dbManager=new DBManager(DBName);
					//請求情報取得開始
					PreparedStatementByKoki selectSettle=dbManager.getStatementByKoki(InspectionValue.readSql(this, "SelectClaimList.sql"));
					selectSettle.setInt("SETTLEMENT_ID", Integer.parseInt(settlementId));
					for(ArrayList<String> row:selectSettle.select()){
						//請求情報格納開始
						claim.settlementId=Integer.parseInt(row.get(0));
						claim.printDate=new CalendarByKoki(row.get(2));
						claim.customerId=row.get(3);
						claim.customerName=row.get(4);
						claim.settlementBefore=Integer.parseInt(row.get(5));
						claim.payment=Integer.parseInt(row.get(6));
						claim.overPrice=Integer.parseInt(row.get(7));
						claim.noTaxTotalFee=Integer.parseInt(row.get(8));
						claim.taxFee=Integer.parseInt(row.get(9));
					}
					//明細取得
					PreparedStatementByKoki seelctClaimDetail=dbManager.getStatementByKoki(InspectionValue.readSql(this, "SelectClaimEarning.sql"));
					seelctClaimDetail.setInt("SETTLEMENT_ID", Integer.parseInt(settlementId));
					for(ArrayList<String> row:seelctClaimDetail.select()){
						EarningsDetail detail=new EarningsDetail();
						detail.earningId = Integer.parseInt(row.get(0));
						detail.num = Integer.parseInt(row.get(1));
						detail.puroductId = row.get(2);
						detail.puroductName = row.get(3);
						detail.price = Integer.parseInt(row.get(4));
						detail.taxFee = Integer.parseInt(row.get(5));
						detail.soldAmount = Integer.parseInt(row.get(6));
						detail.buyDate = new CalendarByKoki(row.get(7));
						detail.orderId = Integer.parseInt(row.get(8));
						claim.details.add(detail);
					}
					// 印刷データに追加
					printList.add(claim);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			// 印刷データ格納終了
			// リクエストに印刷データ格納
			request.setAttribute("printList", printList);
			// フォワード処理
			RequestDispatcher rd = request
					.getRequestDispatcher("accouting/do_claim_detail.jsp");
			rd.forward(request, response);
		}

	}
	private void actionDetail(HttpServletResponse response){
		//検索条件の取得
		//データ取得
		//リクエストに詳細情報を格納
		//フォワード処理
	}

}
