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
	ArrayList<Earnings> list=(ArrayList<Earnings>)request.getAttribute("earningsList");
	if(list==null)list=new ArrayList<Earnings>();
	//メッセージを取得
	Message message=(Message)request.getAttribute("message");
	//検索条件の取得
	StockOrderList search=(StockOrderList)request.getAttribute("search");
	if(search==null)search=new StockOrderList();
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
						data-toggle="dropdown"> 河野浩生<span class="caret"></span>
					</a> <!-- 1.メニューの配置 -->
						<ul class="dropdown-menu">
							<li><a href="#">パスワード変更</a></li>
							<li class="divider"></li>
							<li><a
								href="http://localhost:8080/Sugukuru/SalesIndexServlet">ログアウト</a></li>
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
				<h2>売上一覧</h2>
				<ul class="text-pager">
					<li class="text-page texter">1</li>
					<li class="text-par texter">/</li>
					<li class="text-all texter">24</li>
				</ul>
				<ul class="content-paging">
					<li class="paging-prev texter"><<</li>
					<li class="texter"></li>
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
				検索
				<form class="search_form" action="<%=constants.getServletUrl()%>"
					method="post">
					<div class="search_content">
						<span><%=constants.getConstant("08").value%></span> <input
							type="text" name="<%=constants.getConstant("08").pgName%>"
							size="10" class="input-text form-input"
							value="<%=search.rOrderId%>">
					</div>
					<div class="member_id search_content">
						<span class="member_id_text"><%=constants.getConstant("02").value%></span>
						<input type="text" name="<%=constants.getConstant("02").pgName%>"
							size="10" class="input-text form-input"
							value="<%=search.customerId%>">
					</div>
					<div class="create_date search_content">
						<span><%=constants.getConstant("04").value%></span> <input
							type="text" size="4" placeholder="2016"
							value="<%=search.shipmentYear%>"
							name="<%=constants.getConstant("05").pgName%>"
							class="form-input"><span><%=constants.getConstant("05").value%></span>
						<input type="text" value="<%=search.shipmentMonth%>" size="2"
							placeholder="2" name="<%=constants.getConstant("06").pgName%>"
							class="form-input"><span><%=constants.getConstant("06").value%></span>
					</div>

					<div class="search_submit">
						<button class="btn btn-primary">検索</button>
					</div>
				</form>
			</div>
		</div>
		<div class="container div-scroll">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>#</th>
						<th><%=constants.getConstant("08").value%></th>
						<th><%=constants.getConstant("02").value%></th>
						<th><%=constants.getConstant("03").value%></th>
						<th><%=constants.getConstant("09").value%></th>
						<th><%=constants.getConstant("10").value%></th>
						<th><%=constants.getConstant("11").value%></th>
						<th><%=constants.getConstant("12").value%></th>
					</tr>
				</thead>
				<tbody>
					<%
						if (list.size() == 0) {
					%>
					<tr>
						<td colspan="8">検索結果がありませんでした。</td>
					</tr>
					<%
						} else {
					%>
					<%
						int count = 1;
							list: for (Earnings recodeList : list) {
					%>
					<tr>
						<td><%=count++%></td>
						<td><%=recodeList.orderId%></td>
						<td><%=recodeList.customerId%></td>
						<td><%=recodeList.customerName%></td>
						<td><%=recodeList.shipmentDate.outOfJP()%></td>
						<td><%=recodeList.employmentId%></td>
						<td><%=InspectionValue
							.doLocaleJP(recodeList.noTaxTotalFee)%></td>
						<td><%=InspectionValue.doLocaleJP(recodeList.taxFee)%></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>