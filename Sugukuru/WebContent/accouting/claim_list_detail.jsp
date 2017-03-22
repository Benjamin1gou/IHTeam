<%@page import="common.Common"%>
<%@page import="dtd.Payment"%>
<%@page import="dtd.PaymentDetail"%>
<%@page import="dtd.Settlement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="beans.InspectionValue"%>
<%@page import="dtd.Earnings"%>
<%@page import="beans.Message"%>
<%@page import="dtd.StockOrderList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtd.OrderRecodeList"%>
<%@page import="beans.Constants.Page"%>
<%@page import="java.io.File"%>
<%@page import="beans.Constants"%>
<%@page import="common.ActionInterface"%>
<%@page import="beans.LoginEmployment"%>
<%
	//コンスタント作成。
	Constants constants=new Constants(this,request);
	//表示オブジェクトの設定
	PaymentDetail detail=(PaymentDetail)request.getAttribute("paymentDetail");
	if(detail==null){
		detail=new PaymentDetail();
		//決済リストに飛ぶ
		//Message message=new Message(constants);
	}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=constants.getConstant("01").value%></title>
<%@ include file="../ultimateKokiBaseCssLink.jsp" %>
<link href="<%=request.getContextPath()%>/stock/css/index.css"
	rel="stylesheet">
<script>
	$(document).ready(function() {
		$(".dropdown-menu").click(function(e) {
			e.stopPropagation();
		});
	});
</script>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<!-- スマートフォンサイズで表示されるメニューボタンとテキスト -->
			<div class="navbar-header">
				<!--
        メニューボタン
        data-toggle : ボタンを押したときにNavbarを開かせるために必要
        data-target : 複数navbarを作成する場合、ボタンとナビを紐づけるために必要
		     -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#nav-menu-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<!-- タイトルなどのテキスト -->
				<a class="navbar-brand" href="#"><%=Constants.getCommon("02").value%></a>
			</div>

			<!-- グローバルナビの中身 -->
			<div class="collapse navbar-collapse" id="nav-menu-1">

				<!-- 各ナビゲーションメニュー -->
				<ul class="nav navbar-nav">

					<!-- 通常のリンク -->
					<li class="active"><a href="#">経理部からのお知らせ</a></li>
					<li><a href="#">倉庫部からのお知らせ</a></li>
					<!-- ドロップダウンのメニューも配置可能 -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">その他<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">その他１</a></li>
							<li><a href="#">その他２</a></li>
							<li><a href="#">その他３</a></li>
							<li class="divider"></li>
							<li><a href="#">それとも？</a></li>
							<li class="divider"></li>
							<li><a href="#">モンスター？</a></li>
						</ul></li>
				</ul>
				<!-- ログイン中のユーザー情報 -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 上島崇寛<span class="caret"></span>
					</a> <!-- 1.メニューの配置 -->
						<ul class="dropdown-menu">
							<li><a href="#">パスワード変更</a></li>
							<li class="divider"></li>
							<li><a href="#">ログアウト</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 左側メニューのコンテンツ -->
	<%@ include file="../ultimateTakahiroMenu.jsp"%>
	<div class="center-content">
		<div class="center-content-title">
			<h2><%=constants.getConstant("03").value%></h2>
		</div>
		<div class="container">
			<table class="table table-striped table-bordered">
				<tr>
					<th><%=constants.getConstant("04").value%></th>
					<th colspan="2"><%=constants.getConstant("05").value%></th>
					<th><%=constants.getConstant("02").value%></th>
					<th colspan="2"><%=constants.getConstant("06").value%></th>
				</tr>
				<tr>
					<td><%=detail.customerId%></td>
					<td colspan="2"><%=detail.customerName%></td>
					<td><%=detail.settlemntId%></td>
					<td colspan="2"><%=detail.requestDate.outOfJP()%></td>
				</tr>
				<tr>
					<th><%=constants.getConstant("07").value%></th>
					<td class="price"><%=InspectionValue.doLocaleJP(detail.totalFee)%></td>
					<th><%=constants.getConstant("08").value%></th>
					<td class="price"><%=InspectionValue.doLocaleJP(detail.overPrice)%></td>
					<th><%=constants.getConstant("09").value%></th>
					<td class="price"><%=InspectionValue.doLocaleJP(detail.tax)%></td>
				</tr>
				<tr>
					<th><%=constants.getConstant("10").value%></th>
					<td class="price" colspan="5"><%=detail.allFee()%></td>
				</tr>

			</table>
			<table class="table table-striped table-bordered">
				<tr>
					<td colspan="4"><%=constants.getConstant("11").value%></td>
				</tr>
				<tr>
					<th><%=constants.getConstant("12").value%></th>
					<th><%=constants.getConstant("13").value%></th>
					<th><%=constants.getConstant("14").value%></th>
					<th><%=constants.getConstant("15").value%></th>
				</tr>
				<%
					for (Payment payment : detail.payedList) {
				%>
				<tr>
					<td><%=payment.num%></td>
					<td><%=payment.paymentDate.outOfJP()%></td>
					<td><%=payment.getPaymentWay()%></td>
					<td class="price"><%=InspectionValue.doLocaleJP(payment.paidPrice)%></td>
				</tr>
				<%
					}
				%>
				<%
					if (detail.payedList.size() == 0) {
				%>
				<tr>
					<td colspan="4"><%=constants.getConstant("16").value%></td>
				</tr>
				<%
					}
				%>
			</table>

		</div>
	</div>

</body>
</html>
