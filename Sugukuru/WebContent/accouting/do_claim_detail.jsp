<%@page import="beans.Constants"%>
<%@page import="dtd.EarningsDetail"%>
<%@page import="beans.InspectionValue"%>
<%@page import="dtd.DoClaim"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<DoClaim> printList=(ArrayList<DoClaim>)request.getAttribute("printList");
	Constants constants=new Constants(this,request);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../ultimateKokiBaseCssLink.jsp" %>
<link href="<%=request.getContextPath()%>/stock/css/index.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/accouting/css/do_claim_detail.css"
	rel="stylesheet">
<title><%=constants.getConstant("24").value%>
</title>
<script src="<%=request.getContextPath()%>/accouting/js/printThis.js"></script>
<script type="text/javascript">
	$(function() {
		//印刷ボタンを押した時
		$('input[name=print]').on('click', function() {
			//プリントエリア取得
			$('.sett').printThis();
		});
	});
</script>
</head>
<body>
	<!-- 顧客別請求書 -->
	<%
		for (DoClaim print : printList) {
	%>
	<article class="sett">
		<section class="claim_parent">
			<table class="table table-bordered">
				<tr>
					<th colspan="5"><%=constants.getConstant("01").value%></th>
					<td rowspan="2"><%=constants.getConstant("02").value%>：<%=print.printDate.outOfJP()%><br>
						<%=constants.getConstant("03").value%> ：<%=print.settlementId%><br>
						スグクル株式会社<br> 経理部 河野 浩生固定<br></td>
				</tr>
				<tr>
					<td class="title"><%=print.customerName%><%=constants.getConstant("04").value%>
					</td>
					<td colspan="4" class="title"></td>
				</tr>
				<tr>
					<th><%=constants.getConstant("05").value%></th>
					<th><%=constants.getConstant("06").value%></th>
					<th><%=constants.getConstant("07").value%></th>
					<th><%=constants.getConstant("08").value%></th>
					<th><%=constants.getConstant("09").value%></th>
					<th><%=constants.getConstant("10").value%></th>
				<tr>
				<tr>
					<td class="price"><%=InspectionValue.doLocaleJP(print.settlementBefore)%></td>
					<td class="price"><%=InspectionValue.doLocaleJP(print.payment)%></td>
					<td class="price"><%=InspectionValue.doLocaleJP(print.overPrice)%></td>
					<td class="price"><%=InspectionValue.doLocaleJP(print.noTaxTotalFee)%></td>
					<td class="price"><%=InspectionValue.doLocaleJP(print.taxFee)%></td>
					<td class="price"><%=InspectionValue.doLocaleJP(print.getAllTotal())%></td>
				</tr>
			</table>
		</section>
		<!-- 顧客別請求書明細 -->
		<section class="claim_detail">
			<table class="table table-bordered">
				<tr>
					<th colspan="4"><%=constants.getConstant("11").value%></th>
					<th colspan="3"><%=constants.getConstant("12").value%>
					</th>
					<td colspan="2"><%=constants.getConstant("13").value%>
					<%=print.printDate.outOfJP()%><br>
						<%=constants.getConstant("14").value%>
						<%=print.settlementId%><br>
					</td>
				</tr>
				<tr>
					<th><%=constants.getConstant("15").value%></th>
					<th><%=constants.getConstant("16").value%></th>
					<th><%=constants.getConstant("17").value%></th>
					<th><%=constants.getConstant("18").value%></th>
					<th><%=constants.getConstant("19").value%></th>
					<th><%=constants.getConstant("20").value%></th>
					<th><%=constants.getConstant("21").value%></th>
					<th><%=constants.getConstant("22").value%></th>
					<th><%=constants.getConstant("23").value%></th>
				</tr>
				<%
					for (EarningsDetail detail : print.details) {
				%>
				<tr>
					<td><%=detail.buyDate.getMonth()%></td>
					<td><%=detail.buyDate.getDay()%></td>
					<td><%=detail.puroductName%></td>
					<td><%=detail.orderId%></td>
					<td><%=detail.soldAmount%></td>
					<td class="price"><%=detail.price%></td>
					<td class="price"><%=detail.getNoTaxTotalFee()%></td>
					<td class="price"><%=detail.getTaxTotal()%></td>
					<td class="price"><%=detail.getTotalFee()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</section>
	</article>
	<%
		}
	%>
	<div class="print_button">
		<input type="button" name="print" class="btn btn-primary" value="印刷">
	</div>
</body>
</html>