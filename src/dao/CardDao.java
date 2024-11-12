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

		// Cardをインスタンス化
		Card card=new Card();
		Connection connection=getConnection();
		PreparedStatement statement=null;

		try{
			statement=connection.prepareStatement(baseSql);
			statement.setString(1, cardNumber);

			ResultSet rSet=statement.executeQuery();

			if (rSet.next()) {
				card.setCardNumber(rSet.getString("card_number"));

			} else {
				//リザルトセットが存在しない場合
				//cardインスタンスにnullをセット
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

		// Card型で返す
		return card;
	}

	//セーブ（更新＆作成用）
	public boolean save(Card card) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//データベースからcardを取得
			Card old = get(card.getCardNumber());
			if (old == null) {
				//card（card_number)が存在しなかった場合
				//プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into card (card_number, card_day, card_cvc, card_name) values(?, ?, ?, ?) ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, card.getCardNumber());
				statement.setString(2,card.getCardExpiryDate());
				statement.setString(3,card.getCardCvc());
				statement.setString(4,card.getCardName());
			} else {
				//学生が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update card set card_number=?, card_day=?, card_cvc=?, card_name=? where card_number=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, card.getCardNumber());
				statement.setString(2,card.getCardExpiryDate());
				statement.setString(3,card.getCardCvc());
				statement.setString(4,card.getCardName());
				statement.setString(5, card.getCardNumber());
			}

			//プリペアードステートメントを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			//
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		if (count > 0) {
			//実行件数が1以上ある場合
			return true;

		} else {
			//実行件数が0件の場合
			return false;
		}

	}
}
