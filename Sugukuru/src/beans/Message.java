/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/09
 * 内容　　:画面に表示するメッセージ一覧を制御するクラス。
 * *************************/
package beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.DBManager.PreparedStatementByKoki;
import beans.Message.MessageInterface.MODE;

import common.Database;

public class Message implements Database{

	private Constants constants;
	private MessageInterface messageInterface;
	/**
	 * 各モードのメッセージキーを管理するクラス。
	 * @auther 浩生
	 * 2016/11/09
	 * @param keys ArrayList<String>
	 */
	private ArrayList<ArrayList<String>> keys;
	{
		this.keys=new ArrayList<ArrayList<String>>();
		this.messageInterface=new Information();
	}
	/**
	 * コンスタント情報を持ったメッセージクラスを取得します。
	 * @auther 浩生
	 * 2016/11/09
	 * @param constants
	 */
	public Message(Constants constants) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.constants=constants;
	}
	/**
	 * 指定コンスタントインスタンスからメッセージインスタンスを生成します。
	 * @auther 浩生
	 * 2016/11/09
	 * @param constants
	 * @return
	 */
	public static Message newInstance(Constants constants){
		return new Message(constants);
	}
	/**
	 * 各モードの情報を制約するインターフェース
	 * @author 浩生
	 *
	 */
	public interface MessageInterface extends Database{
		/**
		 * 各モードでのメッセージを取得する。
		 * @auther 浩生
		 * 2016/11/09
		 * @return
		 */
		public ArrayList<MessageBox> message();
		/**
		 * 各モードの状態を取得する。
		 * @author 浩生
		 *
		 */
		public enum MODE{
			WARNIG,
			ERRER,
			INFORMATION;
		}
		/**
		 * 現在のモード状態を取得する。
		 * @auther 浩生
		 * 2016/11/09
		 * @return
		 */
		public MODE nowMode();
	}

	/**
	 * 警告モードを保持するクラス。
	 * @author 浩生
	 *
	 */
	public class Warning implements MessageInterface{
		@Override
		public ArrayList<MessageBox> message() {
			// TODO 自動生成されたメソッド・スタブ
			return getMessage(constants, "W");
		}
		@Override
		public MODE nowMode() {
			// TODO 自動生成されたメソッド・スタブ
			return MODE.WARNIG;
		}
	}
	/**
	 * 異常モードを保持するクラス。
	 * @author 浩生
	 *
	 */
	public class Errer implements MessageInterface{
		@Override
		public ArrayList<MessageBox> message() {
			// TODO 自動生成されたメソッド・スタブ
			return getMessage(constants, "E");
		}
		@Override
		public MODE nowMode() {
			// TODO 自動生成されたメソッド・スタブ
			return MODE.ERRER;
		}
	}
	/**
	 * お知らせモードを保持るクラス。
	 * @author 浩生
	 *
	 */
	public class Information implements MessageInterface{
		@Override
		public ArrayList<MessageBox> message() {
			// TODO 自動生成されたメソッド・スタブ
			return getMessage(constants, "I");
		}
		@Override
		public MODE nowMode() {
			// TODO 自動生成されたメソッド・スタブ
			return MODE.INFORMATION;
		}
	}
	/**
	 * メッセージの箱クラス
	 * @author 浩生
	 *
	 */
	public class MessageBox{
		public String messageID;
		public String message;
	}
	/**
	 * 現在のモードで指定されたキーでメッセージ文字列を取得できます。
	 * @auther 浩生
	 * 2016/11/09
	 * @param constants
	 * @param messageKey
	 * @return
	 */
	protected ArrayList<MessageBox> getMessage(Constants constants,String messageKey){
		DBManager dbManager = null;
		try{
			dbManager=new DBManager(DBName);
			PreparedStatementByKoki statementByKoki=dbManager.getStatementByKoki(InspectionValue.readSql(constants.getServlet(), "SelectMessage.sql"));
			statementByKoki.setString("MESSAGE_ID", messageKey+"%");
			ArrayList<ArrayList<String>> list=statementByKoki.select();
			ArrayList<MessageBox> messageBoxs=new ArrayList<Message.MessageBox>();
			for(ArrayList<String> row:list){
				MessageBox box=new MessageBox();
				box.messageID=row.get(0);
				box.message=row.get(1);
				messageBoxs.add(box);
			}
			return messageBoxs;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbManager.closeDB();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Messageクラスを警告モードにします。
	 * このとき、このモードで取得できるメッセージ文字列のキーを
	 * 登録します。
	 * この登録リストは他のモードに変更した場合、リセットされます。
	 * コンスタントIDがない場合は空白を指定してください。
	 * @auther 浩生
	 * 2016/11/09
	 * @param constantId
	 * @param messageId
	 * 登録したキーのメッセージリストを取得するには?
	 * @see Message#getMessageArray(String, String)
	 */
	public void doWarnig(String messageId,String... constantId){
		if(nowMode()!=MODE.WARNIG){
			this.keys=new ArrayList<ArrayList<String>>();
		}
		setKeys(messageId,constantId);
		this.messageInterface=new Warning();
	}
	/**
	 * Messageクラスをお知らせモードにします。
	 * このとき、このモードで取得できるメッセージ文字列のキーを
	 * 登録します。
	 * この登録リストは他のモードに変更した場合、リセットされます。
	 * コンスタントIDがない場合は空白を指定してください。
	 * @auther 浩生
	 * 2016/11/09
	 * 登録したキーのメッセージリストを取得するには?
	 * @see Message#getMessageArray(String, String)
	 */
	public void doInfomation(String messageId,String... constantId){
		if(nowMode()!=MODE.INFORMATION){
			this.keys=new ArrayList<ArrayList<String>>();
		}
		setKeys(messageId, constantId);
		this.messageInterface=new Information();
	}
	/**
	 * Messageクラスを異常モードにします。
	 * このとき、このモードで取得できるメッセージ文字列のキーを
	 * 登録します。
	 * この登録リストは他のモードに変更した場合、リセットされます。
	 * コンスタントIDがない場合は空白を指定してください。
	 * @auther 浩生
	 * 2016/11/09
	 * 登録したキーのメッセージリストを取得するには?
	 * @see Message#getMessageArray(String, String)
	 */
	public void doErrer(String messageId,String... constantId){
		if(nowMode()!=MODE.INFORMATION){
			this.keys=new ArrayList<ArrayList<String>>();
		}
		setKeys(messageId,constantId);
		this.messageInterface=new Information();
	}
	/**
	 * 現行モードで任意のメッセージを取得します。
	 * @auther 浩生
	 * 2016/11/09
	 * @param constantId コンスタントテーブルの下2桁
	 * @param messageId メッセージテーブルの下２桁
	 * @return メッセージボックス化されたメッセージ情報
	 */
	public String getMassage(String messageId,String... constantId){
		if(messageInterface==null)return null;//現行モードが定義されていない
		MessageBox messageBox=findMessage(messageId);
		return shaping(messageBox, constantList(constantId));
	}
	/**
	 * 現行モードで該当メッセージIDに一致するメッセージボックスを取得する。
	 * @auther 浩生
	 * 2016/11/09
	 * @param messageId
	 * @return
	 */
	private MessageBox findMessage(String messageId){
		for(MessageBox box:this.messageInterface.message()){
			String t=box.messageID.substring(2, 4);
			if(box.messageID.substring(2, 4).equals(messageId)){
				return box;
			}
		}
		return null;
	}
	/**
	 * メッセージ内容をメッセージID（二桁目）に対応した変形を行います。
	 * -------------メッセージID（２桁目）の種別---------------------
	 *    A　→未整形で返す。
	 *    P　→先頭にconstantを付与して返す。
	 *    R　→○文字をconstantに変換して返します。
	 *    S　→　-文字をそれぞれ順番に置換します。
	 *    V　→Pと同様の処理をした後にSを行います。なのでconstantsは０番目に先頭文字が来るようにしてください。
	 *    D　→Pを行った後、constantsで置換します。
	 * ---------------------------注意---------------------------------
	 * 第二引数constantsは各種別によって使われ方が違います。
	 * 例：S種別の時にVと同じconstantsでも問題はありませんが
	 *     出力結果は大きく異なります。
	 * @auther 浩生
	 * 2016/11/09
	 * @param box
	 * @param constants
	 * @return
	 */
	private String shaping(MessageBox box,String... constants){
		if(box == null)return null;
		//種別取得
		String patt=String.valueOf(box.messageID.charAt(1));
		switch(patt){
			case "A":
					return box.message;
			case "P":
				if(constants==null)return null;
				System.out.println(constants[0]+box.message);
				return constants[0]+box.message;
			case "R":
				if(constants==null)return null;
				return box.message.replace("○", constants[0]);
			case "S":
				if(constants==null)return null;
				if(constants.length<2)return null;
				box.message=box.message.replaceFirst("-", constants[0]);
				box.message=box.message.replaceFirst("-", constants[1]);
				return box.message;
			case "V":
				if(constants==null)return null;
				if(constants.length<3)return null;
				box.message=constants[0]+box.message;
				box.message=box.message.replaceFirst("-", constants[0]);
				box.message=box.message.replaceFirst("-", constants[1]);
				return box.message;
			case "D":
				if(constants==null)return null;
				if(constants.length<2)return null;
				box.message=constants[0]+box.message;
				box.message=box.message.replaceFirst("○", constants[1]);
			default:
				return null;
		}
	}
	/**
	 * 指定キーのConstant.valueリストを取得する。
	 * @auther 浩生
	 * 2016/11/09
	 * @param constants
	 * @return
	 */
	private String[] constantList(String... constants){
		if(constants.length>this.constants.size())return null;
		String[] result=new String[this.constants.size()];
		int count=0;
		for(String index:constants){
			result[count++]=this.constants.getConstant(index).value;
		}
		return result;
	}
	/**
	 * 現在のモードを取得します。
	 * @auther 浩生
	 * 2016/11/09
	 * @return
	 */
	public MODE nowMode(){
		return this.messageInterface.nowMode();
	}
	/**
	 * 表示するキーをフィールド用に変換し格納する。
	 * @auther 浩生
	 * 2016/11/09
	 * @param keys
	 */
	private void setKeys(String messageId,String...constantId){
		if(constantId==null){

		}
		List list=Arrays.asList(constantId);
		ArrayList<String> row=new ArrayList<String>(list);
		row.add(0, messageId);
		this.keys.add(row);
	}
	/**
	 * 現行モードで登録したキーに対応するメッセージを一覧を取得します。
	 * @auther 浩生
	 * 2016/11/09
	 * @param left 文字列の左側（先頭）に付加します。
	 * @param right 文字列の右側（末尾）に付加します。
	 * @return 登録したキーがない場合はnull
	 * 登録したキーとは?
	 * @see Message#doWarnig(String, String)
	 * @see Message#doErrer(String, String)
	 * @see Message#doInfomation(String, String)
	 *
	 */
	public ArrayList<String> getMessageArray(String left,String right){
		if(this.keys.isEmpty()==true)return new ArrayList<String>();
		ArrayList<String> list=new ArrayList<String>();
		for(ArrayList<String> row:this.keys){
			String messageId=row.get(0);
			row.remove(0);
			list.add(left+getMassage(messageId, (String[])row.toArray(new String[0]))+right);
		}
		return list;
	}
	/**
	 * 現行モードで登録したキーに対応するメッセージを一行で取得します。
	 * 引数の説明は下記を参照
	 * @auther 浩生
	 * 2016/11/25
	 * @param left
	 * @param right
	 * @return
	 * @see Message#getMessageArray(String, String)引数の説明
	 */
	public String getMessageArrayToStr(String left,String right){
		StringBuffer buffer=new StringBuffer();
		for(String str:getMessageArray(left, right)){
			buffer.append(str);
		}
		return buffer.toString();
	}
}
