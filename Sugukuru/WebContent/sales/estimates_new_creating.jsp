<%@page import="beans.Constants"%>
<%@page import="beans.Constants.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//コンスタント
	Constants constants = new Constants(this,request);
	System.out.println("0301="+constants.getConstant("01").value);
%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>営業部</title>
<%@ include file="../ultimateKokiBaseCssLink.jsp" %>
    </head>
  <body style="font-size: 16px;">
    <nav class="navbar navbar-default" role="navigation">
  		<div class="container-fluid">
   		 <!-- スマートフォンサイズで表示されるメニューボタンとテキスト -->
    		<div class="navbar-header">
      		<!--
        メニューボタン
        data-toggle : ボタンを押したときにNavbarを開かせるために必要
        data-target : 複数navbarを作成する場合、ボタンとナビを紐づけるために必要
		     -->
      			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav-menu-1">
        			<span class="sr-only">Toggle navigation</span>
        			<span class="icon-bar"></span>
        			<span class="icon-bar"></span>
       				<span class="icon-bar"></span>
      			</button>

      			<!-- タイトルなどのテキスト -->
      			<a class="navbar-brand" href="#">営業部</a>
    		</div>

		    <!-- グローバルナビの中身 -->
		    <div class="collapse navbar-collapse" id="nav-menu-1">

		      <!-- 各ナビゲーションメニュー -->
		      <ul class="nav navbar-nav">

		        <!-- 通常のリンク -->
		        <li class="active"><a href="#">経理部からのお知らせ</a></li>
		        <li><a href="#">倉庫部からのお知らせ</a></li>
		        <!-- ドロップダウンのメニューも配置可能 -->
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown">その他<b class="caret"></b></a>
		          <ul class="dropdown-menu">
		            <li><a href="#">その他１</a></li>
		            <li><a href="#">その他２</a></li>
		            <li><a href="#">その他３</a></li>
		            <li class="divider"></li>
		            <li><a href="#">それとも？</a></li>
		            <li class="divider"></li>
		            <li><a href="#">モンスター？</a></li>
		          </ul>
		        </li>
		      </ul>
		      <!-- ログイン中のユーザー情報 -->
		      <ul class="nav navbar-nav navbar-right">
		    		<li class="dropdown">
		        		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
		            	河野浩生<span class="caret"></span>
		        		</a>
		        		<!-- 1.メニューの配置 -->
		        		<ul class="dropdown-menu">
		            		<li><a href="#">パスワード変更</a></li>
		            		<li class="divider"></li>
		            		<li><a href="#">ログアウト</a></li>
		        		</ul>
		    		</li>
				</ul>
			</div>
		  </div>
		</nav>
		<!-- 左側メニューのコンテンツ -->
	<%@ include file="../ultimateTakahiroMenu.jsp" %>
	<div class="left-menu">
		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse"
					href="#estimates_1">見積書作成</a>
			</div>
			<div id="estimates_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li>検索</li>
					<li class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								href="#estimates_1_1">管理機能</a>
						</div>
						<div class="accordion-body collaose" id="estimates_1_1">
							<ul class="accordion-inner">
								<li>登録</li>
								<li>変更</li>
								<li>削除</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<div class="accordion-heading">
				<a class="accordion-toggle" data-toggle="collapse" href="#orders_1">受注書作成</a>
			</div>
			<div id="orders_1" class="accordion-body collapse">
				<ul class="accordion-inner">
					<li>検索</li>
					<li class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse"
								data-parent"#post" href="#orders_1_1">管理機能</a>
						</div>
						<div class="accordion-body collaose" id="orders_1_1">
							<ul class="accordion-inner">
								<li>登録</li>
								<li>変更</li>
								<li>削除</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="center-content">
			<div class="center-content-header">
				<div class="center-content-title">
					<h2><%=constants.getConstant("01").value %></h2>
				</div>
				<div class="content-message content">
					メッセージ<br>
					N-20-N、N-20-N、N-20-N、N-20-N、N-20-N<br>
				</div>
			</div>
			<div class="container">
				<table class="table table-striped table-bordered table-up table-first">
						<tbody>
							<tr>
								<th>顧客ID</th>
								<td style="padding:0px; width: 100px"><input type="text" maxlength="6" class="form-control input-sm table-textbox-big"></td>
								<th>顧客名</th>
								<td colspan="5" style="padding:0px; width: 500px"></td>
								<th>希望納品日</th>
								<td style="width: 160px">9999/99/99</td>
							</tr>
							<tr>
								<th rowspan="2">備考</th>
								<td rowspan="2"  colspan="7" style="padding:0px;"><textarea name="kanso" rows="3" cols="90" class="note"></textarea></td>
								<th>回収方法</th>
								<td style="width: 150px">あいうえお</td>
							</tr>
							<tr>
								<th>与信オーバー額</th>
								<td class="price" style="width: 160px">999,999,999,999</td>
							</tr>
							<tr>
								<th>手数料</th>
								<td class="price">99,999</td>
								<th>商品代金</th>
								<td class="price" style="width: 160px">99,999</td>
								<th>税抜合計</th>
								<td class="price" style="width: 160px">9,999,999,999</td>
								<th>税</th>
								<td class="price" style="width: 160px">99,999</td>
								<th>税込合計</th>
								<td class="price">999,999,999,999</td>
							</tr>
						</tbody>
				</table>
				<!-- 入力テーブルの子項目　※ヘッダーはこの下 -->
				<div  class="table-overflow">
					<table class="table table-bordered table-up maintable" >
						<tbody>
							<tr>
								<td style="padding:0px; width: 70px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px; width: 80px;"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price" style="width: 80px;">999</td>
								<td class="price" style="width: 130px;">99,999,999</td>
								<td style="padding:0px;" style="width: 130px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price" style="width: 130px;">9,999,999,999</td>
								<td class="price" style="width: 130px;">99,999,999</td>
								<td class="price" style="width: 160px;">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
							<tr>
								<td style="padding:0px;"><input type="text" maxlength="7" class="form-control input-sm table-textbox-small"></td>
								<td style="padding:0px;"><select class="table-selectbox">
									<option>あいうえおあいうえお</option>
									<option>aiueokak</option>
								</select></td>
								<td style="padding:0px"><input type="text" maxlength="3" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">999</td>
								<td class="price">99,999,999</td>
								<td style="padding:0px;"><input type="text" maxlength="12" class="form-control input-sm table-textbox-small-num"></td>
								<td class="price">9,999,999,999</td>
								<td class="price">99,999,999</td>
								<td class="price">999,999,999,999</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 入力テーブルのヘッダー -->
				<table class="table table-bordered table-up table-second-header" >
						<thead>
							<tr>
								<th style="width: 70px">ID</th>
								<th>単位</th>
								<th style="width: 80px">数量</th>
								<th style="width: 80px">在庫</th>
								<th style="width: 130px">単価</th>
								<th style="width: 130px">販売単価</th>
								<th style="width: 130px">税抜き小計</th>
								<th style="width: 130px">税</th>
								<th style="width: 160px">税込み小計</th>
							</tr>
						</thead>
				</table>



					<div class="button">
						<button type="button" class="btn btn-danger cancel-btn">クリア</button>
						<button type="button" class="btn btn-primary success-btn">確認</button>
					</div>
			</div>
		</div>
  </body>
</html>