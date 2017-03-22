/*決済テーブルのIDを取得する。*/
SELECT
 MAX(settlement_table.settlement_id)
 FROM settlement_table;