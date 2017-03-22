package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import beans.Message.MessageInterface.MODE;

import common.Database;

import dtd.Earnings;
import dtd.EarningsList;

/**
 * Servlet implementation class EarningsListServlet
 */
@WebServlet("/EarningsListServlet")
public class EarningsListServlet extends HttpServlet implements Database {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EarningsListServlet() {
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
		doPost(request, response);
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
		Constants constants = new Constants(this, request);
		// リクエストDTD生成
		EarningsList search = new EarningsList();
		// 検索条件の取得
		search = (EarningsList) constants.superDecodeRequest(search);
		// メッセージの作成開始
		Message message = new Message(constants);
		// SQL作成開始
		DBManager dbManager = null;
		PreparedStatementByKoki statementByKoki = null;
		try {
			dbManager = new DBManager(DBName);
			statementByKoki = dbManager.getStatementByKoki(InspectionValue
					.readSql(this, "EarningsList.sql"));
			// /////////////////
			// 入力チェック
			// --------------------
			// 受注ID
			if (search.rOrderId == null) {
				// 受注ID取得不能
				statementByKoki.toNull("ORDER_ID");
			} else if (search.rOrderId.isEmpty() == false) {
				// 受注ID不正値
				try {
					statementByKoki.setInt("ORDER_ID",
							Integer.parseInt(search.rOrderId));
				} catch (NumberFormatException e) {
					message.doWarnig("02", "08", "08");
					statementByKoki.toNull("ORDER_ID");
				}
			} else {
				// 受注IDが空白
				statementByKoki.toNull("ORDER_ID");
			}
			// 顧客ID
			if (search.customerId == null) {
				// 顧客ID取得ID
				statementByKoki.toNull("CUSTOMER_ID");
			} else if (search.customerId.isEmpty() == false) {
				try {
					Integer.parseInt(search.customerId);
					statementByKoki.setString("CUSTOMER_ID", search.customerId);
				} catch (NumberFormatException e) {
					// 顧客ID不整値
					e.printStackTrace();
					message.doWarnig("04", "02");
					statementByKoki.toNull("CUSTOMER_ID");
				}
			} else {
				// 顧客IDが空白
				statementByKoki.toNull("CUSTOMER_ID");
			}
			// 売上月
			if (search.earningsYear == null || search.earningsMonth == null) {
				// 売り上げ月が取得不能
				statementByKoki.toNull("SHIPMENT_DATE");
			} else if (search.earningsYear.isEmpty() == true
					|| search.earningsMonth.isEmpty() == true) {
				// 売上月のどちらかが空白
				statementByKoki.toNull("SHIPMENT_DATE");
			} else {
				try {
					Integer.parseInt(search.earningsYear);
					Integer.parseInt(search.earningsMonth);
					// 売上月の設定
					statementByKoki.setString("SHIPMENT_DATE",
							search.earningsYear + "-" + search.earningsMonth
									+ "-%");
				} catch (NumberFormatException e) {
					e.printStackTrace();
					message.doWarnig("05", "04");
				}
			}
			// メッセージの確認エラー
			if (message.nowMode() == MODE.WARNIG) {
				// 不正な値あり
				request.setAttribute("message", message);
				constants.forward(request, response);
				return;
			}
			statementByKoki.cleanSql();
			// SQL実行
			ArrayList<Earnings> list = getEarnings(statementByKoki.select());
			if (list.size() == 0) {
				// 検索結果なし
			} else {
				// リクエスト格納
				request.setAttribute("earningsList", list);
			}
		} catch (SQLException e) {
			// SQLエラー
			e.printStackTrace();
			System.out.println(statementByKoki.out());
			// message.doErrer();
		} catch (ClassNotFoundException e) {
			// ロードエラー
			e.printStackTrace();
			// message.doErrer();
		} catch (Exception e) {
			// どうしようもないエラー
			e.printStackTrace();
			// message.doErrer();
		} finally {

		}
		// フォワード処理
		constants.forward(request, response);
	}

	private ArrayList<Earnings> getEarnings(ArrayList<ArrayList<String>> list) {
		ArrayList<Earnings> result = new ArrayList<Earnings>();
		for (ArrayList<String> row : list) {
			Earnings earnings = new Earnings();
			earnings.earningId = Integer.parseInt(row.get(0));
			earnings.orderId = Integer.parseInt(row.get(1));
			earnings.customerId = row.get(2);
			earnings.employmentId = Integer.parseInt(row.get(3));
			earnings.shipmentDate = new CalendarByKoki(row.get(4));
			earnings.noTaxTotalFee = Integer.parseInt(row.get(5));
			earnings.taxFee = Integer.parseInt(row.get(6));
			earnings.note = row.get(7);
			earnings.customerName=row.get(8);
			result.add(earnings);
		}
		return result;
	}
}
