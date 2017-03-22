package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DBManager;
import beans.DBManager.PreparedStatementByKoki;
import beans.InspectionValue;
import common.Database;
import dtd.PickingList;
import dtd.ProductNums;
import dtd.Subdivision;
import dtd.SubdivisionDetail;
import net.arnx.jsonic.JSON;;

/**
 * Servlet implementation class PikingListServlet
 */
@WebServlet("/PikingListServlet")
public class PikingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PikingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		
		String SQL = "";
		String value = "";
		String sort = "";
		value = request.getParameter("value");
		String method = request.getParameter("method");
		sort = request.getParameter("sort");
		System.out.println("sort:"+sort);
		System.out.println("method:"+method);
		ArrayList<ArrayList<String>> list;
		HashMap<String, ArrayList<Subdivision>> date = new HashMap<String, ArrayList<Subdivision>>();
		HashMap<String, ArrayList<SubdivisionDetail>> date2 = new HashMap<String, ArrayList<SubdivisionDetail>>();
		HashMap<String, ArrayList<PickingList>> date3 = new HashMap<String, ArrayList<PickingList>>();
		HashMap<String, ArrayList<PickingList>> date4 = new HashMap<String, ArrayList<PickingList>>();
		JSON json = new JSON();
		PrintWriter out=response.getWriter();
		
		switch(method){
			case "picking":
				ArrayList<PickingList> returnPick = new ArrayList<PickingList>();
				PickingList pick = new PickingList();
				try{
					DBManager db = new DBManager(Database.DBName);
					PreparedStatementByKoki statementByKoki=null;
					statementByKoki = db.getStatementByKoki(InspectionValue.readSql(this,"AndroidPickingList.sql"));
					
					statementByKoki.toNull("PRODUCT_ID");
					switch(sort){
					case "0":
						statementByKoki.toNull("STATE");
						break;
					case "3":
						statementByKoki.setString("STATE", "未作業");
						break;
					case "1":
						statementByKoki.setString("STATE", "ピッキング済");
						break;
					case "2":
						statementByKoki.setString("STATE", "検品済");
						break;
					}
					list = statementByKoki.select();
					
					int i = 1;
					for(ArrayList<String> row:list){
						pick = new PickingList();
						pick.pickId = String.valueOf(i);
						pick.productId = row.get(0);
						pick.productName = row.get(1);
						pick.rackNumber = row.get(2);
						pick.needs = row.get(4);
						pick.pickNum = row.get(5);
						pick.inspectedAmount = row.get(6); 
						pick.pickState = row.get(7);
						System.out.println("pick"+pick);
						returnPick.add(pick);
						i++;
					}
					date3.put("date", returnPick);
				}catch(Exception e){
					e.printStackTrace();
				}
				out.println(json.encode(date3));
				break;
			case "order":
				System.out.println(SQL);
				ArrayList<Subdivision> returnSub = new ArrayList<Subdivision>();
				Subdivision sub = new Subdivision();
				date = new HashMap<String, ArrayList<Subdivision>>();
				try{
					DBManager db = new DBManager(Database.DBName);
					PreparedStatementByKoki statementByKoki=null;
					statementByKoki = db.getStatementByKoki(InspectionValue.readSql(this,"AndroidOrderList.sql"));
					
					list = statementByKoki.select();
					for(ArrayList<String> row:list){
						sub = new Subdivision();
						sub.orderId = row.get(0);
						System.out.println(row.get(0));
						sub.customreName = row.get(1);
						System.out.println(row.get(1));
						sub.customerNameKana = row.get(2);
						System.out.println(row.get(2));
						sub.orderDate = row.get(3);
						System.out.println(row.get(3));
						sub.setOrderState("1");
						ArrayList<ArrayList<String>> step = db.runSelect("select step from order_details_table where order_id = "+sub.orderId+"");
						System.out.println(step);
						switch(sort){
						case "0":
							sort = "全件";
							break;
						case "1":
							sort = "出荷準備完了";
							break;
						case "2":
							sort = "未完了";
							break;
						}
						for(ArrayList<String> stepNum: step){
							for(String x: stepNum){
								if(!("3").equals(x)){
									sub.setOrderState("0");
								}
							}
						}
						if("全件".equals(sort) || sub.orderState.equals(sort)){
						returnSub.add(sub);
						}
					}
					date.put("date", returnSub);
				}catch(Exception e){
					e.printStackTrace();
				}
				out.println(json.encode(date));
				break;
			case "orderDetail":
				SQL = "select num, puroduct_id, puroduct_name, amount, step from order_details_table where order_id = "+value+" ";
				if(!("".equals(sort))){
					SQL += "AND step ="+sort+"";
				}
				System.out.println(SQL);
				ArrayList<SubdivisionDetail> returnSubDeti = new ArrayList<SubdivisionDetail>();
				SubdivisionDetail subde = new SubdivisionDetail();
				try{
					DBManager db = new DBManager(Database.DBName);
					list = db.runSelect(SQL);
					for(ArrayList<String> row:list){
						subde = new SubdivisionDetail();
						subde.num = row.get(0);
						subde.productNumber = row.get(1);
						subde.productName = row.get(2);
						subde.amount = row.get(3);
						subde.setStep(row.get(4));
						returnSubDeti.add(subde);
						
					}
					date2.put("date", returnSubDeti);
				}catch(Exception e){
					e.printStackTrace();
				}
				out.println(json.encode(date2));
				break;
				
			case "stepUpdate":
				String num = request.getParameter("num");
				
				SQL = "UPDATE order_details_table SET step = 3 where order_id = "+value+" AND num = "+num+"";
				System.out.println("stepUpdate: "+SQL);
				
				try{
					DBManager db = new DBManager(Database.DBName);
					int x = db.update(SQL);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
				
			case "pickingDetail":
				String productId = request.getParameter("productId");
				ArrayList<PickingList> returnPick2 = new ArrayList<PickingList>();
				PickingList pick2 = new PickingList();
				try{
					DBManager db = new DBManager(Database.DBName);
					PreparedStatementByKoki statementByKoki=null;
					statementByKoki = db.getStatementByKoki(InspectionValue.readSql(this,"AndroidPickingList.sql"));
					statementByKoki.toNull("STATE");
					statementByKoki.setString("PRODUCT_ID", productId);
					list = statementByKoki.select();
					
					for(ArrayList<String> row:list){
						pick2 = new PickingList();
						pick2.productId = row.get(0);
						pick2.productName = row.get(1);
						pick2.rackNumber = row.get(2);
						pick2.stock = row.get(3);
						pick2.needs = row.get(4);
						pick2.pickNum = row.get(5);
						pick2.inspectedAmount = row.get(6); 
						pick2.pickState = row.get(7);
						pick2.unit = row.get(8);
						returnPick2.add(pick2);
						System.out.println(pick2);
					}
					date4.put("date", returnPick2);
				}catch(Exception e){
					e.printStackTrace();
				}
				out.println(json.encode(date4));
				break;
				
			case "pickUpdate":
				String mode = request.getParameter("mode");
				String productId2 = request.getParameter("productId");
				int unit = Integer.parseInt(request.getParameter("unit"));
				ProductNums pn = new ProductNums();
				int x;
				try{
					DBManager db = new DBManager(Database.DBName);
					PreparedStatementByKoki statementByKoki=null;
					statementByKoki = db.getStatementByKoki(InspectionValue.readSql(this,"AndroidProductNum.sql"));
					statementByKoki.setString("PRODUCT_ID", productId2);
					list = statementByKoki.select();
					for(ArrayList<String> row: list){
						pn.needs = Integer.parseInt(row.get(0));
						pn.pickNum = Integer.parseInt(row.get(1));
						pn.inspectedAmount = Integer.parseInt(row.get(2));
						pn.stock = Integer.parseInt(row.get(3));
					}
					if("0".equals(value)){
						switch(mode){
						case "0":
//							pn.needs -= unit;
							pn.pickNum += unit;
							pn.stock -= unit;
							SQL = "UPDATE working_table SET picked_amount = "+pn.pickNum+" WHERE puroduct_id = "+productId2+"";
							x = db.update(SQL);
							System.out.println(x);
							break;
						case "1":
							pn.inspectedAmount += unit;
							SQL = "UPDATE working_table SET inspected_amount = "+pn.inspectedAmount+" WHERE puroduct_id = "+productId2+"";
							x = db.update(SQL);
							break;
						}
					}else{
						switch(mode){
						case "0":
//							pn.needs += unit;
							pn.pickNum -= unit;
							pn.stock += unit;
							SQL = "UPDATE working_table SET picked_amount = "+pn.pickNum+" WHERE puroduct_id = "+productId2+"";
							x = db.update(SQL);
							break;
						case "1":
							pn.inspectedAmount -= unit;
							SQL = "UPDATE working_table set inspected_amount = "+pn.inspectedAmount+" WHERE puroduct_id = "+productId2+"";
							x = db.update(SQL);
							break;
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				stepUp(pn, productId2, mode);
			
		}
		
	
		
		out.flush();
		out.close();
	}

	private void stepUp(ProductNums pn, String productId, String mode) {
		String step = "-1";
		
		switch(mode){
			case "0":
				if(pn.needs <= pn.pickNum){
					step = "1";
				}else{
					step = "0";
				}
				break;
				
			case "1":
				if(pn.pickNum <= pn.inspectedAmount){
					step = "2";
				}else{
					step = "1";
				}
				break;
			default:
				step = "-1";
		}
		if(!("-1".equals(step))){
			try{
				DBManager db = new DBManager(Database.DBName);
				PreparedStatementByKoki statementByKoki=null;
				statementByKoki = db.getStatementByKoki(InspectionValue.readSql(this,"AndroidStepUp.sql"));
				statementByKoki.setString("STEP", step);
				statementByKoki.setString("SHIPPING_DATE", "2016-11-12 08:00:00");
				statementByKoki.setString("PRODUCT_ID", productId);
				System.out.println(statementByKoki.out());
				int x = statementByKoki.update();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
