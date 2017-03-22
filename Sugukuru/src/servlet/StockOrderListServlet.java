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
import beans.Message.MessageInterface.MODE;

import common.Database;

import dtd.OrderDetail;
import dtd.StockOrderList;

/**
 * Servlet implementation class StockOrderListServlet
 */
@WebServlet("/StockOrderListServlet")
public class StockOrderListServlet extends HttpServlet implements Database {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockOrderListServlet() {
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
		Constants constants = new Constants(this, request);
		Message message = new Message(constants);
		StockOrderList search = new StockOrderList();
		// 検索条件の取得
		search = (StockOrderList) constants
				.superDecodeRequest(search);
		//検索条件の格納
		request.setAttribute("search", search);
		DBManager dbManager = null;
		PreparedStatementByKoki statementByKoki=null;
		try {
			dbManager = new DBManager(DBName);
			statementByKoki = dbManager
					.getStatementByKoki(InspectionValue.readSql(this,
							"StockOrderList.sql"));
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
					message.doWarnig("04", "08");
					statementByKoki.toNull("ORDER_ID");
				}
			}else{
				//受注IDが空白
				statementByKoki.toNull("ORDER_ID");
			}
			// 顧客ID
			if (search.customerId == null) {
				// 顧客ID取得ID
				statementByKoki.toNull("CUSTOMER_ID");
			} else if (search.customerId.isEmpty() == false) {
				try {
					Integer.parseInt(search.customerId);
					statementByKoki.setString("CUSTOMER_ID",
							search.customerId);
				} catch (NumberFormatException e) {
					// 顧客ID不整値
					e.printStackTrace();
					message.doWarnig("04", "02");
					statementByKoki.toNull("CUSTOMER_ID");
				}
			}else{
				//顧客IDが空白
				statementByKoki.toNull("CUSTOMER_ID");
			}
			/*
			 * 顧客名はLIKE　％が日本語に対応出来ないので削除
			if(stockOrderList.customerName==null){
				//顧客名取得不能
				statementByKoki.toNull("USER_NAME");
			}else{
				statementByKoki.setString("USER_NAME", stockOrderList.customerName+"%");
			}
			*/
			// 出荷日時
			if (search.shipmentYear.isEmpty() == false
					&& search.shipmentMonth.isEmpty() == false
					&& search.shipmentYear.isEmpty() == false) {
				CalendarByKoki shipmentCalendar = CalendarByKoki.newInstance(
						search.shipmentYear,
						search.shipmentMonth,
						search.shipmentDay);
				if (shipmentCalendar == null) {
					// 出荷日生成エラー
					message.doWarnig("03", "04");
					statementByKoki.toNull("DELIVERY_DATE");
				} else {
					statementByKoki.setString("DELIVERY_DATE",
							shipmentCalendar.outSQLDate());
				}
			}else{
				statementByKoki.toNull("DELIVERY_DATE");
			}
			if (message.nowMode() == MODE.WARNIG) {
				// 不正な値あり
				request.setAttribute("message", message);
				constants.forward(request, response);
				return;
			}
			// SQLクリーン
			statementByKoki.cleanSql();
			// SQL実行
			ArrayList<ArrayList<String>> list = statementByKoki.select();
			if (list.size() == 0) {
				// 検索結果無し
			} else {
				// レスポンス用リスト変数
				ArrayList<StockOrderList> stockOrderLists = getOrderList(list);
				if (stockOrderLists.size() > 0) {
					// リクエスト格納
					request.setAttribute("stockOrderList", stockOrderLists);
				}
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
	/**
	 * StockOrderList.sqlで取得したリストデータを
	 * StockOrderListリストに変換する。
	 * @auther 浩生
	 * 2016/11/09
	 * @param list
	 * @return
	 */
	protected static ArrayList<StockOrderList> getOrderList(ArrayList<ArrayList<String>> list){
		// レスポンス用リスト変数
		ArrayList<StockOrderList> stockOrderLists = new ArrayList<StockOrderList>();
		// 検索結果を成形格納
			stock: for (int rowCount = 0; rowCount < list.size();) {
				StockOrderList stockOrderList2 = new StockOrderList();
				stockOrderList2.orderId = Integer.parseInt(list.get(
						rowCount).get(0));
				stockOrderList2.customerId = list.get(rowCount).get(1);
				stockOrderList2.customerName = list.get(rowCount)
						.get(2);
				stockOrderList2.orderDate = CalendarByKoki
						.newInstance(list.get(rowCount).get(13));
				stockOrderList2.shipmentDate = CalendarByKoki
						.newInstance(list.get(rowCount).get(14));
				stockOrderList2.deliveryDate = CalendarByKoki
						.newInstance(list.get(rowCount).get(10));
				details: while (stockOrderList2.orderId == Integer
						.parseInt(list.get(rowCount).get(0))) {
					// 詳細を格納
					OrderDetail detail = new OrderDetail();
					detail.orderId = stockOrderList2.orderId;
					detail.num = Integer.parseInt(list.get(rowCount)
							.get(3));
					detail.productId = list.get(rowCount).get(4);
					detail.productName = list.get(rowCount).get(5);
					detail.price = Integer.parseInt(list.get(rowCount)
							.get(6));
					detail.cunsumntionTax = Double.parseDouble(list
							.get(rowCount).get(7));
					detail.amount = Integer.parseInt(list.get(rowCount)
							.get(8));
					detail.step = Integer.parseInt(list.get(rowCount)
							.get(9));
					detail.productDeliveredFlg = Integer.parseInt(list
							.get(rowCount).get(11));
					detail.note = list.get(rowCount).get(12);

					if (rowCount == list.size() - 1) {
						// 最後の行処理
						stockOrderList2.orderDetails.add(detail);
						break details;
					}
					stockOrderList2.orderDetails.add(detail);
					rowCount++;
				}
				stockOrderLists.add(stockOrderList2);
				if (rowCount == list.size() - 1) {
					break stock;
				}
			}
		return stockOrderLists;
	}
}
