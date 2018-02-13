import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDAO {
	public int open(String password,Connection conn) {
		String sql="insert card(number,password,money,createtime,modifytime) values(?,?,?,NOW(),NOW());";
		PreparedStatement pre=null;
		try {
			pre=conn.prepareStatement(sql);
			pre.setInt(1,Data.CreateNumber());
			pre.setString(2, password);
			pre.setDouble(3, 0);
			return pre.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}
	
	public int modifyPassword(int number,String password,Connection conn) {
		String sql="update card set password=?,modifytime=NOW() where number=?;";
		PreparedStatement pre=null;
		try {
			pre=conn.prepareStatement(sql);
			pre.setString(1, password);
			pre.setInt(2,number);
			return pre.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}
	
	public int modifyMoney(int number,double money,Connection conn) {
		String sql="update card set money=?,modifytime=NOW() where number=?;";
		PreparedStatement pre=null;
		try {
			pre=conn.prepareStatement(sql);
			pre.setDouble(1, money);
			pre.setInt(2,number);
			return pre.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}
	
	public Card GetCard(int number,Connection conn) {
		String sql="select * from card where number=? for update;";
		PreparedStatement pre=null;
		try {
			pre=conn.prepareStatement(sql);
			pre.setInt(1, number);
			ResultSet result=pre.executeQuery();
			while(result.next()) {
				Card a=new Card();
				a.setId(result.getInt("id"));
				a.setNumber(result.getInt("number"));
				a.setMoney(result.getDouble("money"));
				a.setCreatetime(result.getDate("createtime"));
				a.setModifytime(result.getDate("modifytime"));
				a.setPassword(result.getString("password"));
				a.setVersion(result.getInt("version"));
				return a;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
}
