package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD

import bean.Card;

public class CardDao extends Dao{

	public Card get(String cardId) throws Exception {

		//ユーザインスタンスを初期化
		Card card =new Card();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;


		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("SELECT * FROM CARD WHERE card_number = ?");
			//各部分に値を設定
			statement.setString(1,cardId );

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();


			if (rSet.next()){
				card.setCardNumber(rSet.getString("card_number"));
				card.setCardExpiryDate(rSet.getString("card_day"));
				card.setCardCvc(rSet.getString("card_cvc"));
				card.setCardName(rSet.getString("tecard_namel"));
			}else {
				//リザルトセットが存在しない場合
				//ユーザインスタンスにnullをセット
				card = null;
			}

		}catch (Exception e){
			throw e;
		}finally {
			//プリペアステートメントを閉じる
			if (statement != null){
				try {
					statement.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return card;
	}
=======
>>>>>>> branch 'master' of https://github.com/riamuzzz/CafeConnect.git

import bean.Card;

public class CardDao extends Dao {

	private String baseSql = "select * from card where card_number=? ";

	//ゲット
	public Card get(String cardNumber)throws Exception{

		Card card=new Card();
		Connection connection=getConnection();
		PreparedStatement statement=null;

		try{   //科目コード別に作成
			statement=connection.prepareStatement(baseSql);
			statement.setString(1, cardNumber);

			ResultSet rSet=statement.executeQuery();

			if (rSet.next()) {
				card.setCardNumber(rSet.getString("card_number"));

			} else {
				//リザルトセットが存在しない場合
				//科目インスタンスにnullをセット
				card= null;
			} }catch (Exception e) {
				throw e;
			} finally {
				if(statement != null) {
					try {
						statement.close();
					} catch (SQLException sqle) {
						throw sqle;
					}
				}if(connection != null) {
					try {
						connection.close();
					} catch (SQLException sqle) {
						throw sqle;
					}
				}
			}

		return card;
	}
}
