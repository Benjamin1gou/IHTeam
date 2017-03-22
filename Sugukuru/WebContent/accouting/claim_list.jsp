
<%@page import="beans.Constants.Action"%>
<%@page import="dtd.Settlement"%>
<%
	//法人顧客の請求を一覧する画面
%>
<%@page import="beans.InspectionValue"%>
<%@page import="dtd.Earnings"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	//検索結果の取得
	ArrayList<Settlement> list=(ArrayList<Settlement>)request.getAttribute("claimList");
	if(list==null)list=new ArrayList<Settlement>();
	//メッセージの取得
	Message message=(Message)request.getAttribute("message");
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
		$("[name='all_check']").click(function(){
			$("[name='<%=constants.getConstant("07").pgName%>
	']")
													.each(
															function() {
																if ($(this)
																		.prop(

																		"checked") == true) {
																	$(this)
																			.prop(
																					"checked",
																					false);
																} else {
																	$(this)
																			.prop(
																					"checked",
																					true);
																}
															});
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
	<%@ include file="../ultimateTakahiroMenu.jsp" %>
	<div class="center-content">
		<div class="center-content-header">
			<div class="center-content-title">
				<h2><%=constants.getConstant("01").value%></h2>
				<ul class="text-pager">
					<li class="text-page texter">1</li>
					<li class="text-par texter">/</li>
					<li class="text-all texter">24</li>
				</ul>
				<ul class="content-paging">
					<li class="paging-prev texter"><<</li>
					<li class="texter"><</li>
					<li class="texter">1</li>
					<li class="texter">2</li>
					<li class="texter">3</li>
					<li class="texter">></li>
					<li class="paging-next texter">>></li>
				</ul>

			</div>
			<div class="content-message content">
				メッセージ
				<%if (message != null) {%>
				<%=message.getMessageArrayToStr("<span style=\"color:red\">", "</span>")%>
				<%}%>
			</div>
			<div class="content-search content">
				<form class="navbar-form navbar-left" role="search"
					action="<%=constants.getServletUrl()%>" method="post">
					<%=constants.getAction(Action.Search)%>
					<div class="form-group">
						<%=constants.getConstant("02").value%><input type="text"
							class="form-control class1" placeholder="キーワード"
							name="<%=constants.getConstant("02").pgName%>">
						<%=constants.getConstant("03").value%><input type="text"
							class="form-control class3" placeholder="年"
							name="<%=constants.getConstant("04").pgName%>"><%=constants.getConstant("04").value%>
						<input type="text" class="form-control class2" placeholder="月"
							name="<%=constants.getConstant("05").pgName%>"><%=constants.getConstant("05").value%>
						<input type="text" class="form-control class2" placeholder="日"
							name="<%=constants.getConstant("06").pgName%>"><%=constants.getConstant("06").value%>
					</div>
					<button type="submit" class="btn btn-default">検索</button>
				</form>
			</div>
		</div>
		<div class="container">
			<form action="<%=constants.getServletUrl()%>" method="post">
				<%=Constants.getAction(Action.Insert)%>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th><input type="checkbox" name="all_check" value=""></th>
							<th><%=constants.getConstant("08").value%></th>
							<th><%=constants.getConstant("09").value%></th>
							<th><%=constants.getConstant("10").value%></th>
							<th><%=constants.getConstant("11").value%></th>
							<th><%=constants.getConstant("12").value%></th>
							<th><%=constants.getConstant("13").value%></th>
							<th><%=constants.getConstant("14").value%></th>

						</tr>
					</thead>
					<tbody>
						<%
							if (list.size() == 0) {
						%>
						<tr>
							<td colspan="10"><%=constants.getConstant("15").value%></td>
						</tr>
						<%
							} else {
						%>
						<%
							for (Settlement recodeList : list) {
						%>
						<tr>
							<td><input type="checkbox"
								name="<%=constants.getConstant("07").pgName%>"
								value="<%=recodeList.settlementId%>"></td>
							<td><a
								href="<%=constants.getPageToUrl(Page.PaymentDetail_jsp,
							"rSettlementId",
							String.valueOf(recodeList.settlementId))%>"><%=recodeList.customerId%></a></td>
							<td><%=recodeList.customerName%></td>
							<td><%=recodeList.getRecallManner()%></td>
							<td><%=recodeList.requestDate.outOfJP()%></td>
							<td><%=recodeList.getPaymentDate()%></td>
							<td class="price"><%=InspectionValue.doLocaleJP(recodeList.totalFee)%>円</td>
							<td class="price"><%=InspectionValue
							.doLocaleJP(recodeList.consumptionTax)%>円</td>
						</tr>
						<%
							}
							}
						%>

					</tbody>
				</table>
				<input type="submit" value="<%=constants.getConstant("16").value%>"
					class="print">
			</form>
		</div>
	</div>

</body>
</html>
