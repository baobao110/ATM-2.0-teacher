import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO {
	public int add(Account a) {
		String sql="insert account(number,money,type,createtime,description) values(?,?,?,NOW(),?);";
		Connection conn=Data.getConnection();
		PreparedStatement pre=null;
		try {
			pre=conn.prepareStatement(sql);
			pre.setInt(1, a.getNumber());
			pre.setDouble(2,a.getMoney());
			pre.setInt(3,a.getType());
			pre.setString(4, a.getDescription());
			return pre.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public ArrayList<Account> List(int number,Connection conn){
		ArrayList<Account> a=new ArrayList<Account>();
		String sql="select * from account where number=? for update;";
		PreparedStatement pre=null;
		try {
			pre=conn.prepareStatement(sql);
			pre.setInt(1, number);
			ResultSet result=pre.executeQuery();
			while(result.next()) {
				Account b=new Account();
				b.setId(result.getInt("id"));
				b.setNumber(result.getInt("number"));
				b.setMoney(result.getDouble("money"));
				b.setType(result.getInt("type"));
				b.setCreatetime(result.getDate("createtime"));
				b.setDescription(result.getString("description"));
				a.add(b);
			}
			return a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
}
