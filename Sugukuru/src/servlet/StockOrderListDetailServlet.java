/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/12
 * 内容　　:倉庫部の受注の詳細を表示するためのサーブレット。
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

import beans.Constants;
import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;

import common.Database;

import dtd.StockOrderList;

/**
 * Servlet implementation class StockOrderListdetailServlet
 */
@WebServlet("/StockOrderListdetailServlet")
public class StockOrderListDetailServlet extends HttpServlet implements Database{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockOrderListDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Constants constants=new Constants(this,request);
		String orderID=(String)request.getParameter("orderId");
		DBManager dbManager=null;
		PreparedStatementByKoki statementByKoki=null;
		//レスポンス用変数
		ArrayList<StockOrderList> list=new ArrayList<StockOrderList>();
		try{
			dbManager=new DBManager(DBName);
			statementByKoki=dbManager.getStatementByKoki(InspectionValue.readSql(this, "StockOrderList.sql"));
			//不要な条件を削除
			statementByKoki.toNull("CUSTOMER_ID");
			statementByKoki.toNull("DELIVERY_DATE");
			//受注IDをセット
			statementByKoki.setInt("ORDER_ID", Integer.parseInt(orderID));
			list=StockOrderListServlet.getOrderList(statementByKoki.select());

		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//リクエスト格納
			request.setAttribute("order_list", list);
		}
		constants.forward(request, response);

	}

}
