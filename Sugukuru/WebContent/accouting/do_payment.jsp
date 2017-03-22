<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入金ファイル作成デモ</title>
</head>
<body>
<form action="" method="get">
	<table>
		<tr>
			<th>ヘッダーレコード</th>
		</tr>
		<tr>
			<th>データ区分</th>
			<td>
				<input type="hidden" name="header_deta_type" value="1">
				1
			</td>
		</tr>
		<tr>
			<th>振込顧客ID</th>
			<td>
				<select name="header_huri_id">

				</select>
			</td>
		</tr>
	</table>
</form>
</body>
</html>