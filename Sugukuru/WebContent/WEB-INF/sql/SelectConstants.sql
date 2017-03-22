/* constants_masterからコンスタントを取得するSQL */
SELECT
	 constant_id	/* コンスタントID */
	,value			/* 項目名 */
	,pg_name		/* プログラム名 */
 FROM
	constants_master
 WHERE
	constant_id LIKE CONSTANT_ID
;
