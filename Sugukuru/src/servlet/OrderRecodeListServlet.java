/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/06
 * 内容　　:倉庫部の受注一覧を生成するクラス。
 * *************************/
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

import common.Database;

import dtd.OrderRecodeList;

/**
 * Servlet implementation class OrderRecodeListServlet
 */
@WebServlet("/OrderRecodeListServlet")
public class OrderRecodeListServlet extends HttpServlet implements Database {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderRecodeListServlet() {
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
		try {
			DBManager dbManager = new DBManager(DBName);
			PreparedStatementByKoki statementByKoki = dbManager
					.getStatementByKoki(InspectionValue.readSql(this,
							"OrderRecodeList.sql", ""));
			statementByKoki.toNullAll("CUSTOMER_ID");
			statementByKoki.toNull("ORDER_DATE");
			statementByKoki.cleanSql();
			response.setCharacterEncoding("utf8");
			response.setContentType("text/html");
			response.getWriter().println(statementByKoki.out());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Constants constants = new Constants(this, request);
		OrderRecodeList search = new OrderRecodeList();
		// メッセージの生成
		Message message = new Message(constants);
		// 検索条件の取得
		search = (OrderRecodeList) constants.superDecodeRequest(search);
		DBManager dbManager = null;
		try {
			// //////////////////////////////////////////////////////////////////
			// DB接続
			// -------------------------------------------------------------------
			dbManager = new DBManager(DBName);
			PreparedStatementByKoki statementByKoki = dbManager
					.getStatementByKoki(InspectionValue.readSql(this,
							"OrderRecodeList.sql"));
			// ////////////////////////////////////////////////
			// 入力チェック
			// -------------------------------------------------
			// 顧客IDの数値チェック
			boolean errFlg = false;
			if (search.customerId == null) {
				// 顧客IDが取得不能
				statementByKoki.toNullAll("CUSTOMER_ID");
				statementByKoki.toNull("2");
			} else if (search.customerId.isEmpty()) {
				// 顧客IDが未入力の場合。whereを省く
				statementByKoki.toNullAll("CUSTOMER_ID");
				statementByKoki.toNull("2");
			} else if (!InspectionValue.inspectionInteger(search.customerId)) {
				// エラー
				errFlg = true;
				message.doWarnig("03", "02");
			} else {
				// 成功時パラメータをセットする。
				statementByKoki.setString("CUSTOMER_ID", search.customerId);
			}
			// 作成年月日の数値チェック
			// 空白でない時
			if (search.createYear.isEmpty() == false
					&& search.createMonth.isEmpty() == false
					&& search.createDay.isEmpty() == false) {
				CalendarByKoki shipmentCalendar = CalendarByKoki
						.newInstance(search.createYear, search.createMonth,
								search.createDay);
				if (shipmentCalendar == null) {
					// 出荷日生成エラー
					message.doWarnig("03", "05");
					statementByKoki.toNull("ORDER_DATE");
				} else {
					statementByKoki.setString("ORDER_DATE",
							shipmentCalendar.outSQLDate());
				}
			}else{
				statementByKoki.toNull("ORDER_DATE");
			}
			System.out.println(statementByKoki.out());
			statementByKoki.cleanSql();
			// 作成年月日の入力チェック終了
			// ---------------------------------------------------------------
			ArrayList<ArrayList<String>> list = statementByKoki.select();
			if (list.size() == 0) {
				// 検索結果無し
			} else {
				// リクエスト格納処理
				ArrayList<OrderRecodeList> resultList = new ArrayList<OrderRecodeList>();
				for (ArrayList<String> row : list) {
					OrderRecodeList rowList = new OrderRecodeList();
					rowList.orderId = Integer.parseInt(row.get(0));
					rowList.estimateId = Integer.parseInt(row.get(1));
					rowList.customerId = row.get(2);
					rowList.customerName = row.get(3);
					rowList.orderDate = new CalendarByKoki(row.get(4));
					rowList.shipment.shipmentFlg = Integer.parseInt(row.get(5));
					if (row.get(6) == null) {
						// 未請求状態
						rowList.settlement = null;
					}
					resultList.add(rowList);
				}
				// リクエスト格納
				request.setAttribute("order_recode", resultList);
			}
		} catch (SQLException e) {
			// sqlエラー
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// driverエラー
			e.printStackTrace();
		} finally {
			try {
				dbManager.closeDB();
			} catch (SQLException e) {
				// closeエラー
				e.printStackTrace();
			}
		}

		// メッセージを設定
		request.setAttribute("message", message);

		// 検索DTDリクエスト設定
		request.setAttribute("orderRecodeList", search);
		// フォワード処理
		constants.forward(request, response);

	}

}
