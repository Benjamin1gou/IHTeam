/*法人顧客が登録している締日の一覧を取得する。*/
SELECT
 DISTINCT
 corporation_customer_master.cut_off_date					/*締日*/
 FROM
 corporation_customer_master
 ORDER BY corporation_customer_master.cut_off_date
 ;