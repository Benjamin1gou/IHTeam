<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>営業部</title>
<%@ include file="../ultimateKokiBaseCssLink.jsp" %>
  	<link href="css/order_drafting.css" rel="stylesheet">
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
		<div class="center-content">
			<div class="center-content-header">
				<div class="center-content-title">
					<h2>受注書作成</h2>
				</div>
				<div class="content-message content">
					メッセージ<br>
					N-20-N、N-20-N、N-20-N、N-20-N、N-20-N<br>
				</div>
			</div>
			<div class="container">
				<table class="table table-striped table-bordered table-up">
					<thead>
						<tr>
							<th colspan="8" class="table-header">見積書</th>
						</tr>
					</thead>
						<tbody>
							<tr>
								<th>略称</th>
								<td>N-10-N</td>
								<th class="width-100">住所</th>
								<td>N-50-N</td>
								<th>TEL</th>
								<td>999-9999-9999</td>
								<th>有効期限</th>
								<td>9999年99月99日</td>
							</tr>
							<tr>
								<th>#</th>
								<th>商品番号</th>
								<th>名称</th>
								<th>メーカー</th>
								<th>発送元</th>
								<th>数量</th>
								<th>単価</th>
								<th>金額</th>
							</tr>
							<tr>
								<td>1</td>
								<td>9-8-9</td>
								<td>N-50-N</td>
								<td>N-20-N</td>
								<td>NNNN</td>
								<td>999</td>
								<td class="price">9-8-9</td>
								<td class="price">9,999,999,999</td>
							</tr>
							<tr>
								<td>2</td>
								<td>9-8-9</td>
								<td>N-50-N</td>
								<td>N-20-N</td>
								<td>NNNN</td>
								<td>999</td>
								<td class="price">9-8-9</td>
								<td class="price">9,999,999,999</td>
							</tr>
							<tr>
								<th>備考</th>
								<td colspan="7">N-100-N</td>
							</tr>
						</tbody>
						<tbody>
							<tr>
								<td colspan="8" class="table-midlle">↓</td>
							</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="8" class="table-header">新規受注書</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>正式名称</th>
								<td>N-30-N</td>
								<th>納品住所</th>
								<td>N-50-N</td>
								<th>納期</th>
								<td>9999年99月99日</td>
								<th>請求予定日</th>
								<td>9999年99月99日</td>
							</tr>
							<tr>
								<th>#</th>
								<th>商品番号</th>
								<th>名称</th>
								<th>メーカー</th>
								<th>発送元</th>
								<th>数量</th>
								<th>単価</th>
								<th>金額</th>
							</tr>
							<tr>
								<td>1</td>
								<td>9-8-9</td>
								<td>N-50-N</td>
								<td>N-20-N</td>
								<td>NNNN</td>
								<td>999</td>
								<td class="price">9-8-9</td>
								<td class="price">9,999,999,999</td>
							</tr>
							<tr>
								<td>2</td>
								<td>9-8-9</td>
								<td>N-50-N</td>
								<td>N-20-N</td>
								<td>NNNN</td>
								<td>999</td>
								<td class="price">9-8-9</td>
								<td class="price">9,999,999,999</td>
							</tr>
							<tr>
								<td>3</td>
								<td><button class="btn btn-primary success-btn">選択</button></td>
								<td>N-50-N</td>
								<td>N-20-N</td>
								<td>NNNN</td>
								<td><input type="text" placeholder="商品数" ></td>
								<td class="price">9-8-9</td>
								<td class="price">9,999,999,999</td>
							</tr>
							<tr>
								<td colspan="8"><button class="btn btn-primary btn-place">＋</button></td>
							</tr>
							<tr>
								<td colspan="6"></td>
								<th>小計</th>
								<td class="price">999,999,999</td>
							</tr>
							<tr>
								<td colspan="6"></td>
								<th>消費税（8%）</th>
								<td class="price">9,999,999</td>
							</tr>
							<tr>
								<td colspan="6"></td>
								<th>合計</th>
								<td class="price">9,999,999,999</td>
							</tr>
						</tbody>
					</table>
					<div class="button">
						<button type="button" class="btn btn-danger cancel-btn">キャンセル</button>
						<button type="button" class="btn btn-primary success-btn">確認</button>
					</div>
			</div>
		</div>
  </body>
</html>