package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
