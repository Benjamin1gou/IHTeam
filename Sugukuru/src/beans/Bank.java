/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/09
 * 内容　　:入金、振り込みなどの銀行処理を管理するスーパークラス
 * *************************/
package beans;

import java.util.ArrayList;


public interface Bank {
	/**
	 * 全銀行を列挙する。
	 *
	 * @author 浩生
	 *
	 */
	public enum Banks {
		MIZUHO("みずほ銀行","ﾐｽﾞﾎｷﾞﾝｺｳ","00001"),
		UFJ("三菱東京UFJ銀行", "ﾐﾂﾋﾞｼﾄｳｷｮｳUFJ", "0005");

		public String name;
		public String kana;
		public String code;

		Banks(String name, String kana, String code) {
			this.name = name;
			this.kana = kana;
			this.code = code;
		}
		public static Banks indexOf(String code){
			for(Banks banks:Banks.values()){
				if(banks.code.equals(code))return banks;
			}
			return null;
		}
	}

	public enum Store {
		MIZU_TOKYO("東京営業部","ﾄｳｷﾖｳｴｲｷﾞｮｳﾌﾞ","001",Banks.MIZUHO),
		UFJ_HONTEN("本店", "ﾎﾝﾃﾝ", "001",Banks.UFJ);
		public String name;
		public String kana;
		public String code;
		public Banks bank;

		Store(String name, String kana, String code,Banks banks) {
			this.name = name;
			this.kana = kana;
			this.code = code;
			this.bank=banks;
		}
		public static Store indexOf(String code){
			for(Store store:Store.values()){
				if(store.code.equals(code))return store;
			}
			return null;
		}
	}

	/**
	 * 種別コードを列挙する。
	 *
	 * @author 浩生
	 *
	 */
	public enum TypeCode {

		/**
		 * 一括支払
		 *
		 * @auther 浩生 2016/11/11
		 * @param IKATUSIHA
		 *            TypeCode
		 */
		IKATUSIHA("24", "一括支払", "ikatusiha"),
		/**
		 * 預金口座振込
		 *
		 * @auther 浩生 2016/11/11
		 * @param YOKINKOZAHURI
		 *            TypeCode
		 */
		YOKINKOZAHURI("91", "預金口座振込", "yokinkozahuri"),
		/**
		 * 総合振込
		 *
		 * @auther 浩生 2016/11/11
		 * @param SOGOHURI
		 *            TypeCode
		 */
		SOGOHURI("21", "総合振込", "sogohuri"),
		/**
		 * 賞与振込
		 *
		 * @auther 浩生 2016/11/11
		 * @param SHOYOHURI
		 *            TypeCode
		 */
		SHOYOHURI("12", "賞与振込", "shoyohuri"),
		/**
		 * 給与紹介
		 *
		 * @auther 浩生 2016/11/11
		 * @param KYUYOSHO
		 *            TypeCode
		 */
		KYUYOSHO("11", "給与紹介", "kyuyosho"),
		/**
		 * 残高照会
		 *
		 * @auther 浩生 2016/11/11
		 * @param ZANDAKASHO
		 *            TypeCode
		 */
		ZANDAKASHO("04", "残高照会", "zandakasho"),
		/**
		 * 入出金取引明細紹介
		 *
		 * @auther 浩生 2016/11/11
		 * @param NYUTORIMEISHO
		 *            TypeCode
		 */
		NYUSHUTORIMEISHO("03", "入出金取引明細照会", "nyushutorimeisho"),
		/**
		 * 振り込み入金紹介
		 *
		 * @auther 浩生 2016/11/11
		 * @param HURINYUSHO
		 *            TypeCode
		 */
		HURINYUSHO("01", "振込入金紹介", "hurinyusho");

		/**
		 * 一意のコード
		 *
		 * @auther 浩生 2016/11/11
		 * @param code
		 *            String
		 */
		public String code;
		/**
		 * 表示用文字列
		 *
		 * @auther 浩生 2016/11/11
		 * @param value
		 *            String
		 */
		public String value;
		/**
		 * 識別文字列
		 *
		 * @auther 浩生 2016/11/11
		 * @param pgName
		 *            String
		 */
		public String pgName;

		private TypeCode(String code, String value, String pgName) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.code = code;
			this.value = value;
			this.pgName = pgName;
		}

		/**
		 * codeと同等のTypeCodeを返す。
		 *
		 * @auther 浩生 2016/11/11
		 * @param code
		 * @return
		 */
		public static TypeCode indexOf(String code) {
			for (TypeCode typeCode : TypeCode.values()) {
				if (code.equals(typeCode.code))
					return typeCode;
			}
			return null;
		}
	}

	/**
	 * 預金種目
	 *
	 * @author 浩生
	 *
	 */
	public enum YokinCode {

		/**
		 * 定期積金
		 *
		 * @auther 浩生 2016/11/11
		 * @param TEISEKIKIN
		 *            YokinCode
		 */
		TEISEKIKIN("8", "定期積金", "teisekikin"),
		/**
		 * 積立定期預金
		 *
		 * @auther 浩生 2016/11/11
		 * @param TUMITEIYOKIN
		 *            YokinCode
		 */
		TUMITEIYOKIN("7", "積立定期預金", "tumiteiyokin"),
		/**
		 * 定期預金
		 *
		 * @auther 浩生 2016/11/11
		 * @param TEKIYOKIN
		 *            YokinCode
		 */
		TEKIYOKIN("6", "定期預金", "tekiyokin"),
		/**
		 * 通知預金
		 *
		 * @auther 浩生 2016/11/11
		 * @param TUTIYOKIN
		 *            YokinCode
		 */
		TUTIYOKIN("5", "通知預金", "tutiyokin"),
		/**
		 * 貯蓄預金
		 *
		 * @auther 浩生 2016/11/11
		 * @param TYOTIYOKIN
		 *            YokinCode
		 */
		TYOTIYOKIN("4", "貯蓄預金", "tyotiyokin"),
		/**
		 * 納税準備預金
		 *
		 * @auther 浩生 2016/11/11
		 * @param NOUJUNYOKIN
		 *            YokinCode
		 */
		NOUJUNYOKIN("3", "納税準備預金", "nojunyokin"),
		/**
		 * 当座預金
		 *
		 * @auther 浩生 2016/11/11
		 * @param TOUZAYOKIN
		 *            YokinCode
		 */
		TOUZAYOKIN("2", "当座預金", "touzayokin"),
		/**
		 * 普通預金
		 *
		 * @auther 浩生 2016/11/11
		 * @param HUTUYOKIN
		 *            YokinCode
		 */
		HUTUYOKIN("1", "普通預金", "hutuyokin");
		/**
		 * 一意のコード
		 *
		 * @auther 浩生 2016/11/11
		 * @param code
		 *            String
		 */
		public String code;
		/**
		 * 表示用文字列
		 *
		 * @auther 浩生 2016/11/11
		 * @param value
		 *            String
		 */
		public String value;
		/**
		 * 識別文字列
		 *
		 * @auther 浩生 2016/11/11
		 * @param pgName
		 *            String
		 */
		public String pgName;

		private YokinCode(String code, String value, String pgName) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.code = code;
			this.value = value;
			this.pgName = pgName;
		}

		/**
		 * codeと同等のYokinCodeを返す。
		 *
		 * @auther 浩生 2016/11/11
		 * @param code
		 * @return
		 */
		public static YokinCode indexOf(String code) {
			for (YokinCode yokinCode : YokinCode.values()) {
				if (code.equals(yokinCode.code))
					return yokinCode;
			}
			return null;
		}
	}



	public enum Ryaku {
		/**
		 * 特別養護老人ホーム
		 * @auther 浩生
		 * 2016/11/11
		 * @param TOKUYO Ryaku
		 */
		TOKUYO("222","ﾄｸﾖｳ","特別養護老人ホーム","ﾄｸﾍﾞﾂﾖｳｺﾞﾛｳｼﾞﾝﾎｰﾑ"),
		/**
		 * 社会福祉協議会
		 * @auther 浩生
		 * 2016/11/11
		 * @param KIYAKIYOU Ryaku
		 */
		KIYAKIYOU("221","ｷﾔｷﾖｳ","社会福祉協議会","ｼﾔｶｲﾌｸｼｷﾖｳｷﾞｶｲ"),
		/**
		 * 公共職業安定所
		 * @auther 浩生
		 * 2016/11/11
		 * @param SHOKUAIN Ryaku
		 */
		SHOKUAIN("220","ｼﾖｸｱﾝ","公共職業安定所","ｺｳｷﾖｳｼﾖｸｷﾞﾖｳｱﾝﾃｲｼﾞﾖ"),
		/**
		 * 農業協同組合連合会
		 * @auther 浩生
		 * 2016/11/11
		 * @param GIYOREN Ryaku
		 */
		GIYOREN("219","ｷﾞﾖﾚﾝ","漁業協同組合連合会","ｷﾞﾖｷﾞﾖｳｷﾖｳﾄﾞｳｸﾐｱｲﾚﾝｺﾞｳｶｲ"),
		/**
		 * 漁業協同組合
		 * @auther 浩生
		 * 2016/11/11
		 * @param GIKIYOU Ryaku
		 */
		GIKIYOU("218","ｷﾞﾖｷﾖｳ","漁業共同組合","ｷﾞﾖｳｷﾖｳﾄﾞｳｸﾐｱｲ"),
		/**
		 * 共済農業協同組合連合会
		 * @auther 浩生
		 * 2016/11/11
		 * @param KYOSAIREN Ryaku
		 */
		KYOSAIREN("217","ｷﾖｳｻｲﾚﾝ","共済農業協同組合連合会","ｷﾖｳｻｲﾉｳｷﾞﾖｳｷﾖｳﾄﾞｳｸﾐｱｲﾚﾝｺﾞｳｶｲ"),
		/**
		 * 経済農業協同組合連合会
		 * @auther 浩生
		 * 2016/11/11
		 * @param KEIZAIREN Ryaku
		 */
		KEIZAIREN("216","ｹｲｻﾞｲﾚﾝ","経済農業協同組合連合会","ｹｲｻﾞｲﾉｳｷﾞﾖｳｷﾖｳﾄﾞｳｸﾐｱｲﾚﾝｺﾞｳｶｲ"),
		/**
		 * 農業組合連合会
		 * @auther 浩生
		 * 2016/11/11
		 * @param NOKYOREN Ryaku
		 */
		NOKYOREN("215","ﾉｳｷﾖｳﾚﾝ","農業協同組合連合会","ﾉｳｷﾞﾖｳｷﾖｳﾄﾞｳｸﾐｱｲﾚﾝｺﾞｳｶｲ"),
		/**
		 * 食糧販売協同組合
		 * @auther 浩生
		 * 2016/11/11
		 * @param SHOHANKYO Ryaku
		 */
		SHOHANKYO("214","ｼﾖｸﾊﾝｷﾖｳ","食糧販売共同組合","ｼﾖｸﾘﾖｳﾊﾝﾊﾞｲｷﾖｳﾄﾞｳｸﾐｱｲ"),
		/**
		 * 労働組合
		 * @auther 浩生
		 * 2016/11/11
		 * @param JUKUMI Ryaku
		 */
		JUKUMI("213","ﾛｳｸﾐ","労働組合","ﾛｳﾄﾞｳｸﾐｱｲ"),
		/**
		 * 厚生年金基金
		 * @auther 浩生
		 * 2016/11/11
		 * @param KOUNEN Ryaku
		 */
		KOUNEN("212","ｺｳﾎﾝ","厚生年金基金","ｺｳｾｲﾈﾝｷﾝｷｷﾝ"),
		/**
		 * 社会保険診療報酬支払基金
		 * @auther 浩生
		 * 2016/11/11
		 * @param SIYAHO Ryaku
		 */
		SIYAHO("211","ｼﾔﾎ","社会保険診療報酬支払基金","ｼﾔｶｲﾎｹﾝｼﾝﾘﾖｳﾎｳｼｳｳｼﾊﾗｲｷｷﾝ"),
		/**
		 * 国家公務員共済組合連合会
		 * @auther 浩生
		 * 2016/11/11
		 * @param KOKUKYOREN Ryaku
		 */
		KOKUKYOREN("210","ｺｸｷﾖｳﾚﾝ","国家公務員共済組合連合会","ｺﾂｶｺｳﾑｲﾝｷﾖｳｻｲｸﾐｱｲﾚﾝｺﾞｳｶｲ"),
		/**
		 * 国民健康保険組合連合会
		 * @auther 浩生
		 * 2016/11/11
		 * @param KOHOREN Ryaku
		 */
		KOHOREN("209","ｺｸﾎﾚﾝ","国民健康保険組合連合会","ｺｸﾐﾝｹﾝｺｳﾎｹﾝｸﾐｱｲﾚﾝｺﾞｳｶｲ"),
		/**
		 * 国民健康保険
		 * @auther 浩生
		 * 2016/11/11
		 * @param KOKUHO Ryaku
		 */
		KOKUHO("208","ｺｸﾎ","国民健康保険","ｺｸﾐﾝｹﾝｺｳﾎｹﾝ"),
		/**
		 * 健康保険組合
		 * @auther 浩生
		 * 2016/11/11
		 * @param KENBO Ryaku
		 */
		KENBO("207","ｹﾝﾎﾞ","健康保険組合","ｹﾝｺｳﾎｹﾝｸﾐｱｲ"),
		/**
		 * 火災海上保険
		 * @auther 浩生
		 * 2016/11/11
		 * @param KASAIKAIJO Ryaku
		 */
		KASAIKAIJO("206","ｶｻｲ","火災海上保険","ｶｲｻｲｶｲｼﾞﾖｳﾎｹﾝ"),
		/**
		 * 海上火災保険
		 * @auther 浩生
		 * 2016/11/11
		 * @param KAIHO Ryaku
		 */
		KAIHO("205","ｶｲｼﾞﾖｳ","海上火災保険","ｶｲｼﾞﾖｳｶｻｲﾎｹﾝ"),
		/**
		 * 生命保険
		 * @auther 浩生
		 * 2016/11/11
		 * @param SEIMEIHO Ryaku
		 */
		SEIMEIHO("204","ｾｲﾒｲ","生命保険","ｾｲﾒｲﾎｹﾝ"),
		/**
		 * 協同組合
		 * @auther 浩生
		 * 2016/11/11
		 * @param KYODAUKUMI Ryaku
		 */
		KYODAUKUMI("203","ｷﾖｳｸﾐ","共同組合","ｷﾖｳﾄﾞｳｸﾐｱｲ"),
		/**
		 * 共済組合
		 * @auther 浩生
		 * 2016/11/11
		 * @param KYOSAIKUMI Ryaku
		 */
		KYOSAIKUMI("202","ｷﾖｳｻｲ","共済組合","ｷﾖｳｻｲｸﾐｱｲ"),
		/**
		 * 連合会
		 * @auther 浩生
		 * 2016/11/11
		 * @param RENGOUKAI Ryaku
		 */
		RENGOUKAI("201","ﾚﾝ","連合会","ﾚﾝｺﾞｳｶｲ"),
		/**
		 * 出張所
		 * @auther 浩生
		 * 2016/11/11
		 * @param SHUTYOUSHO Ryaku
		 */
		SHUTYOUSHO("102","ｼﾕﾂ","出張所","ｼﾕﾂﾁﾖｳｼﾞﾖ"),
		/**
		 * 営業所
		 * @auther 浩生
		 * 2016/11/11
		 * @param EIGYOSHO Ryaku
		 */
		EIGYOSHO("101","ｴｲ","営業所","ｴｲｷﾞﾖｳｼﾖ"),
		/**
		 * 特別非営利活動法人
		 * @auther 浩生
		 * 2016/11/11
		 * @param TOKUHIEI Ryaku
		 */
		TOKUHIEI("015","ﾄｸﾋ","特別非営利活動法人","ﾄｸﾍﾞﾂﾋｴｲﾘｶﾂﾄﾞｳﾎｳｼﾞﾝ"),
		/**
		 * 構成保護法人
		 * @auther 浩生
		 * 2016/11/11
		 * @param KOSEHOJI Ryaku
		 */
		KOSEHOJI("014","ﾎｺﾞ","更正保護法人","ｺｳｾｲﾎｺﾞﾎｳｼﾞﾝ"),
		/**
		 * 相互会社
		 * @auther 浩生
		 * 2016/11/11
		 * @param SOGOKAI Ryaku
		 */
		SOGOKAI("013","ｿﾞ","相互会社","ｿｳｺﾞｶｲｼﾔ"),
		/**
		 * 社会福祉法人
		 * @auther 浩生
		 * 2016/11/11
		 * @param SHAKAIHUKU Ryaku
		 */
		SHAKAIHUKU("012","ﾌｸ","社会福祉法人","ｼﾔｶｲﾌｸｼﾎｳｼﾞﾝ"),
		/**
		 * 学校法人
		 * @auther 浩生
		 * 2016/11/11
		 * @param GAKUKOU Ryaku
		 */
		GAKUKOU("011", "ｶﾞｸ", "学校法人", "ｶﾞﾂｺｳﾎｳｼﾞﾝ"),
		/**
		 * 宗教法人
		 * @auther 浩生
		 * 2016/11/11
		 * @param SHUKYO Ryaku
		 */
		SHUKYO("010", "ｼﾕｳ","宗教法人", "ｼﾕｳｷﾖｳﾎｳｼﾞﾝ"),
		/**
		 * 社団法人
		 * @auther 浩生
		 * 2016/11/11
		 * @param SHADANHO Ryaku
		 */
		SHADANHO("009", "ｼﾔ", "社団法人","ｼﾔﾀﾞﾝﾎｳｼﾞﾝ"),
		/**
		 * 財団法人
		 * @auther 浩生
		 * 2016/11/11
		 * @param ZAIDAHO Ryaku
		 */
		ZAIDAHO("008", "ｻﾞｲ", "財団法人", "ｻﾞｲﾀﾞﾝﾎｳｼﾞﾝ"),
		/**
		 * 医療法人財団
		 * @auther 浩生
		 * 2016/11/11
		 * @param IRYOZAIDA Ryaku
		 */
		IRYOZAIDA("007", "ｲ", "医療法人財団", "ｲﾘﾖｳﾎｳｼﾞﾝｻﾞｲﾀﾞﾝ"),
		/**
		 * 医療法人社団
		 * @auther 浩生
		 * 2016/11/11
		 * @param IRYOSHADA Ryaku
		 */
		IRYOSHADA("006", "ｲ","医療法人社団", "ｲﾘﾖｳﾎｳｼﾞﾝｼﾔﾀﾞﾝ"),
		/**
		 * 医療法人
		 * @auther 浩生
		 * 2016/11/11
		 * @param IRYO Ryaku
		 */
		IRYO("005", "ｲ", "医療法人","ｲﾘﾖｳﾎｳｼﾞﾝ"),
		/**
		 * 合資会社
		 * @auther 浩生
		 * 2016/11/11
		 * @param GOUSHI Ryaku
		 */
		GOUSHI("004", "ｼ", "合資会社", "ｺﾞｳｼｶﾞｲｼﾔ"),
		/**
		 * 合名会社
		 * @auther 浩生
		 * 2016/11/11
		 * @param GOUMEI Ryaku
		 */
		GOUMEI("003", "ﾒ", "合名会社", "ｺﾞｳﾒｲｶﾞｲｼﾔ"),
		/**
		 * 有限会社
		 *
		 * @auther 浩生 2016/11/11
		 * @param YUGEN
		 *            Hojin
		 */
		YUGEN("002", "ﾕ", "有限会社", "ﾕｳｹﾞﾝｶﾞｲｼﾔ"),
		/**
		 * 法人会社
		 *
		 * @auther 浩生 2016/11/11
		 * @param KABU
		 *            Hojin
		 */
		KABU("001", "ｶ", "株式会社", "ｶﾌﾞｼｷｶﾞｲｼﾔ");

		/**
		 * 略称コード
		 *
		 * @auther 浩生 2016/11/11
		 * @param code
		 *            String
		 */
		public String code;
		/**
		 * 略称文字（半角カナ）
		 *
		 * @auther 浩生 2016/11/11
		 * @param hankana
		 *            String
		 */
		public String hankana;
		/**
		 * 略称前漢字
		 *
		 * @auther 浩生 2016/11/11
		 * @param kanji
		 *            String
		 */
		public String kanji;
		/**
		 * 略称前半角カナ文字
		 *
		 * @auther 浩生 2016/11/11
		 * @param kana
		 *            String
		 */
		public String kana;

		private Ryaku(String code, String ryaku, String kanji, String kana) {
			// TODO 自動生成されたコンストラクター・スタブ
			this.code = code;
			this.hankana = ryaku;
			this.kanji = kanji;
			this.kana = kana;
		}

		/**
		 * codeと同等のHojinを返す。
		 *
		 * @auther 浩生 2016/11/11
		 * @param code
		 * @return
		 */
		public Ryaku indexOf(String code) {
			for (Ryaku hojin : Ryaku.values()) {
				if (code.equals(hojin.code)) {
					return hojin;
				}
			}
			return null;
		}

		/**
		 * 略語の中でもカテゴリ分けされるモノを定義します。
		 * この列挙されたモノが保持しているのは
		 * RYAKUで列挙されている略語リストのcode（先頭１文字）
		 * に対応します。
		 * @author 浩生
		 *
		 */
		public enum RyakuType{
			/**
			 * 事業略語
			 * @auther 浩生
			 * 2016/11/11
			 * @param JIGYOU RyakuType
			 */
			JIGYOU("2","事業"),
			/**
			 * 営業所略語
			 * @auther 浩生
			 * 2016/11/11
			 * @param EIGYO RyakuType
			 */
			EIGYO("1","営業所"),
			/**
			 * 法人略語
			 * @auther 浩生
			 * 2016/11/11
			 * @param HOUJIN RyakuType
			 */
			HOUJIN("0","法人");
			/**
			 * 略語コードの先頭１文字
			 * @auther 浩生
			 * 2016/11/11
			 * @param headerCode String
			 * @see Ryaku#code
			 */
			public String headerCode;
			/**
			 * 略語カテゴリー文字
			 * @auther 浩生
			 * 2016/11/11
			 * @param val String
			 */
			public String val;
			private RyakuType(String headerCode,String val) {
				// TODO 自動生成されたコンストラクター・スタブ
				this.headerCode=headerCode;
				this.val=val;
			}
		}
		/**
		 * カテゴライズされた略語を取得します。
		 * @auther 浩生
		 * 2016/11/11
		 * @param ryakuType
		 * @return
		 */
		public ArrayList<Ryaku> indexOfs(RyakuType ryakuType){
			ArrayList<Ryaku> ryakus=new ArrayList<Bank.Ryaku>();
			for(Ryaku ryaku:Ryaku.values()){
				if(ryakuType.headerCode.equals(ryaku.code.charAt(0))){
					ryakus.add(ryaku);
				}
			}
			return ryakus;
		}
		/**
		 * 半角略カナで略文字を検索します。
		 * @auther 浩生
		 * 2016/11/11
		 * @param hankana
		 * @return　未発見でnull
		 */
		public Ryaku indexOfHanKana(String hankana){
			for(Ryaku ryaku:Ryaku.values()){
				if(hankana.equals(ryaku.hankana)){
					return ryaku;
				}
			}
			return null;
		}
		/**
		 * 略カナ文字で検索します。
		 * @auther 浩生
		 * 2016/11/11
		 * @param kana
		 * @return　未発見でnull
		 */
		public Ryaku indexOfKana(String kana){
			for(Ryaku ryaku:Ryaku.values()){
				if(kana.equals(ryaku.kana)){
					return ryaku;
				}
			}
			return null;
		}
		/**
		 * 略カナ、カナの全てで検索します。
		 * @auther 浩生
		 * 2016/11/11
		 * @param allkana
		 * @return 未発見でnull
		 */
		public Ryaku indexOfAllKana(String allkana){
			Ryaku ryaku;
			if((ryaku=indexOfHanKana(allkana))!=null)return ryaku;
			if((ryaku=indexOfKana(allkana))!=null)return ryaku;
			return null;
		}
	}



}
