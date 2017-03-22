/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/06
 * 内容　　:未請求のリストを表示（検索）と請求書の印刷画面と請求済み登録をするサーブレット。
 * *************************/
package servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import common.Database;

import dtd.CutDay;
import dtd.DoClaim;
import dtd.EarningsDetail;

/**
 * Servlet implementation class DoClaimServlet
 */
@WebServlet("/DoClaimServlet")
public class DoClaimServlet extends HttpServlet implements Database {
	private static final long serialVersionUID = 1L;

	/**
	 * このサーブレット内で処理分岐で共通した処理時に使用するグローバルコンスタント。
	 *
	 * @auther 浩生 2016/11/14
	 * @param constants
	 *            Constants
	 */
	private Constants constants;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoClaimServlet() {
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
		// jspに表示する締日を送信する。
		this.constants = new Constants(this, request);
		setCutDay();
		this.constants.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// コンスタントの生成
		this.constants = new Constants(this, request);
		// 現在のモードを取得
		Action action = this.constants.getMode();
		switch (action) {
		case Search:
			// セレクトボックスの設定
			setCutDay();
			actionSearch(response);
			break;
		case Insert:
			actionInsert(response);
			break;
		default:
			break;
		}
	}

	/**
	 * 検索モードでの 検索処理
	 * ここでは特にメッセージ出力する内容がないため
	 * メッセージ生成は行っていない。
	 * @auther 浩生 2016/11/14
	 *
	 */
	private void actionSearch(HttpServletResponse response) {
		DBManager dbManager = null;
		PreparedStatementByKoki statementByKoki = null;
		// 検索条件の取得
		DoClaim search = new DoClaim();
		search = (DoClaim) this.constants.superDecodeRequest(search);
		try {
			dbManager = new DBManager(DBName);
			statementByKoki = dbManager.getStatementByKoki(InspectionValue
					.readSql(this.constants.getServlet(), "SearchDoClaim.sql"));
			// ------------入力チェック-----------
			// 顧客IDのチェック
			if (search.customerId == null) {
				// 顧客IDが取得不能
				statementByKoki.toNull("CUSTOMER_ID");
			} else if (search.customerId.isEmpty() == true) {
				// 顧客IDが空白
				statementByKoki.toNull("CUSTOMER_ID");
			} else {
				statementByKoki.setString("CUSTOMER_ID", search.customerId);
			}
			// 締日
			if (search.cutOfDay == null) {
				// 締日が取得不能
				statementByKoki.toNull("CUT_DATE");
			} else if (search.cutOfDay.isEmpty() == true) {
				// 締日が未選択
				statementByKoki.toNull("CUT_DATE");
			} else {
				statementByKoki.setString("CUT_DATE", search.cutOfDay);
			}

			statementByKoki.cleanSql();
			System.out.println(statementByKoki.out());
			// SQLデータ
			ArrayList<ArrayList<String>> list = statementByKoki.select();
			// レスポンス用変数
			ArrayList<DoClaim> claims = new ArrayList<DoClaim>();
			// SQLデータをレスポンス用に加工
			for (ArrayList<String> row : list) {
				DoClaim claim = new DoClaim();
				claim.customerId = row.get(0);
				claim.noTaxTotalFee = Integer.parseInt(row.get(1));
				claim.taxFee = Integer.parseInt(row.get(2));
				claim.customerName = row.get(3);
				claim.cutDay.value = row.get(4);
				claim.recallManner = row.get(5);
				claim.overPrice = Integer.parseInt(row.get(6));
				claims.add(claim);
			}
			HttpServletRequest request = this.constants.getRequest();
			request.setAttribute("claimList", claims);
			// フォワード処理
			this.constants.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 締日のセレクトボックスデータをリクエストに保存するメソッド このメソッドを呼ぶ前にコンスタントをnewして下さい。
	 *
	 * @auther 浩生 2016/11/14
	 * @param servlet
	 * @param request
	 * @param response
	 * @see DoClaimServlet#constants
	 */
	private void setCutDay() {
		HttpServletRequest request = this.constants.getRequest();
		try {
			DBManager dbManager = new DBManager(DBName);
			ArrayList<CutDay> cutDays = new ArrayList<CutDay>();
			for (ArrayList<String> row : dbManager.runSelect(InspectionValue
					.readSql(this, "SelectCutDay.sql"))) {
				CutDay cutDay = new CutDay();
				cutDay.value = row.get(0);
				cutDays.add(cutDay);
			}
			request.setAttribute("cutDays", cutDays);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 印刷ボタンをおされた時の処理
	 *
	 * @throws IOException
	 * @throws ServletException
	 * @auther 浩生 2016/11/15
	 *
	 */
	/**
	 * @auther 浩生 2016/11/15
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void actionInsert(HttpServletResponse response)
			throws ServletException, IOException {
		DoClaim search = new DoClaim();
		// 印刷対象顧客IDを取得
		search = (DoClaim) this.constants.superDecodeRequest(search);
		Message message = new Message(this.constants);
		HttpServletRequest request = this.constants.getRequest();
		if(search.rCorporationIds!=null)
		if (search.rCorporationIds.length == 0) {
			// 印刷選択なし
			message.doWarnig("02", "05");
			// メッセージ設定
			request.setAttribute("message", message);
			this.constants.forward(request, response);
			return;
		}
		// 印刷データ取得と決済データ登録処理開始
		// 印刷データ変数
		ArrayList<DoClaim> printList = new ArrayList<DoClaim>();
		DBManager dbManager = null;
		for (String customerId : search.rCorporationIds) {
			// この顧客の請求データ変数
			DoClaim claim = new DoClaim();
			try {
				dbManager = new DBManager(DBName);
				dbManager.setautoCommit(false);
				// 顧客の合計金額類を取得
				PreparedStatementByKoki selectDoClaimSQL = dbManager
						.getStatementByKoki(InspectionValue.readSql(this,
								"SearchDoClaim.sql"));
				selectDoClaimSQL.setString("CUSTOMER_ID", customerId);
				selectDoClaimSQL.toNull("CUT_DATE");
				// SQL開始
				ArrayList<ArrayList<String>> dbResult = selectDoClaimSQL
						.select();
				if (dbResult.isEmpty()) {
					// データ取得失敗。
				} else {
					claim.customerId = dbResult.get(0).get(0);
					claim.noTaxTotalFee = Integer.parseInt(dbResult.get(0).get(
							1));
					claim.taxFee = Integer.parseInt(dbResult.get(0).get(2));
					claim.customerName = dbResult.get(0).get(7);
					claim.cutDay.value = dbResult.get(0).get(4);
					claim.recallManner = dbResult.get(0).get(5);
					claim.overPrice = Integer.parseInt(dbResult.get(0).get(6));
				}
				// 前回請求額、入金額を取得
				PreparedStatementByKoki selectSettlementBefore = dbManager
						.getStatementByKoki(InspectionValue.readSql(this,
								"SelectSettleBefore.sql"));
				// 顧客IDを設定
				selectSettlementBefore.setString("CUSTOMER_ID", customerId);
				int settlementBefore=0;
				for (ArrayList<String> row : selectSettlementBefore.select()) {
					claim.settlementBefore = Integer.parseInt(row.get(0));
					claim.payment = Integer.parseInt(row.get(1));
					settlementBefore=Integer.parseInt(row.get(2));
				}
				// 請求データを決済テーブルに保存
				PreparedStatementByKoki insertClaim = dbManager
						.getStatementByKoki(InspectionValue.readSql(this,
								"DoClaimInsert.sql"));
				insertClaim.setString("CUSTOMER_ID", customerId);
				insertClaim.setString("REQUEST_DATE",
						new CalendarByKoki().outSQLDate());
				insertClaim.setInt("TOTAL_FEE", claim.noTaxTotalFee);
				insertClaim.setInt("TAX", claim.taxFee);
				insertClaim.setInt("OVER_PRICE", claim.overPrice+claim.overTax);
				if(settlementBefore>0){
					insertClaim.setInt("PREV_SETTLEMENT_ID", settlementBefore);
				}else{
					insertClaim.setNull("PREV_SETTLEMENT_ID");
				}
				// 決済テーブルに登録
				insertClaim.update();


				// 登録された決済テーブルIDを設定
				PreparedStatementByKoki selectSettlementId = dbManager
						.getStatementByKoki(InspectionValue.readSql(this,
								"SelectSettlementId.sql"));
				claim.settlementId = Integer.parseInt(selectSettlementId
						.select().get(0).get(0));
				// 今回請求する請求明細を取得
				PreparedStatementByKoki selectClaimDetailSQL = dbManager
						.getStatementByKoki(InspectionValue.readSql(this,
								"SelectDoClaim.sql"));
				selectClaimDetailSQL.setString("CUSTOMER_ID", customerId);
				// SQL実行
				dbResult = selectClaimDetailSQL.select();
				// 登録する売上IDを一時格納する変数。
				ArrayList<Integer> earningsIds = new ArrayList<Integer>();
				// 明細変数
				for (ArrayList<String> row : dbResult) {
					EarningsDetail detail = new EarningsDetail();
					detail.earningId = Integer.parseInt(row.get(0));
					// 売上ID一時格納されていない場合。
					if (earningsIds.contains(detail.earningId) == false)
						earningsIds.add(detail.earningId);
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
				// 今回決済テーブルに保存する売上テーブルに決算IDを設定する。
				for (int earnigId : earningsIds) {
					PreparedStatementByKoki updateEarning = dbManager
							.getStatementByKoki(InspectionValue.readSql(this,
									"UpdateDoClaim.sql"));
					updateEarning.setInt("SETTLTMENT_ID", claim.settlementId);
					updateEarning.setInt("EARNIG_ID", earnigId);
					// 更新
					updateEarning.update();
				}
				// トランザクション処理終了
				dbManager.runCommit();
				dbManager.setautoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					dbManager.rollback();
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			} finally {
				try {
					dbManager.closeDB();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
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
