import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Service {
	private static final CardDAO card=new CardDAO();
	private static final AccountDAO account=new AccountDAO();
	public void open(String password) {
		Connection conn=Data.getConnection();
		if(null==conn) {
			System.out.println("空指针异常");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			if(card.open(password,conn)==1) {
				System.out.println("开户成功");	
			}
			conn.commit();
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
		Data.Remove(conn);
	}
	public void save(int number,String password,double money)  {
		Connection conn=Data.getConnection();
		if(null==conn) {
			System.out.println("空指针异常");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(number,conn);
			if(null==a) {
				System.out.println("账号或者密码不存在");
				Data.Remove(conn);
				return;
			}
			if(!password.equals(a.getPassword())) {
				System.out.println("账号或者密码不存在");
				Data.Remove(conn);
				return;
			}
			if(money<0) {
				System.out.println("金额小于零");
				Data.Remove(conn);
				return;
			}
			double x=a.getMony();
			x+=money;
			if(card.modifyMoney(number, x, conn)!=0) {
				System.out.println("存钱成功");	
			}
			Account e=new Account();
			e.setNumber(number);
			e.setMoney(money);
			e.setType(1);
			e.setDescription("存钱");
			account.add(e);
			System.out.println("流水账产生");
			conn.commit();
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
		Data.Remove(conn);
	}
	
	public void draw(int number,String password,double money) {
		Connection conn=Data.getConnection();
		if(null==conn) {
			System.out.println("空指针异常");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(number,conn);
			if(null==a) {
				System.out.println("账号或者密码不存在");
				Data.Remove(conn);
				return;
			}
			if(!password.equals(a.getPassword())) {
				System.out.println("账号或者密码不存在");
				Data.Remove(conn);
				return;
			}
			if(money<0) {
				System.out.println("金额小于零");
				Data.Remove(conn);
				return;
			}
			if(money>a.getMony()) {
				System.out.println("金额不足");
				Data.Remove(conn);
				return;
			}
			double x=a.getMony();
			x-=money;
			if(card.modifyMoney(number, x, conn)!=0) {
				System.out.println("取钱成功");	
			}
			Account e=new Account();
			e.setNumber(number);
			e.setMoney(money);
			e.setType(2);
			e.setDescription("取钱");
			account.add(e);
			System.out.println("流水账产生");
			conn.commit();
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
		Data.Remove(conn);
	}
	
	public void transfer(int OutNumber,String password,double money,int InNumber) {
		Connection conn=Data.getConnection();
		if(null==conn) {
			System.out.println("空指针异常");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(OutNumber,conn);
			if(null==a) {
				System.out.println("账号或者密码不存在");
				Data.Remove(conn);
				return;
			}
			if(!password.equals(a.getPassword())) {
				System.out.println("账号或者密码不存在");
				Data.Remove(conn);
				return;
			}
			Card b=new Card();
			b=card.GetCard(InNumber,conn);
			if(null==b) {
				System.out.println("账号不存在");
				Data.Remove(conn);
				return;
			}
			if(money>a.getMony()) {
				System.out.println("金额小于零");
				Data.Remove(conn);
				return;
			}
			double x=a.getMony();
			x-=money;
			if(card.modifyMoney(OutNumber, x, conn)!=0) {
				System.out.println("转出成功");	
			}
			Account e=new Account();
			e.setNumber(OutNumber);
			e.setMoney(money);
			e.setType(3);
			e.setDescription("转账");
			account.add(e);
			System.out.println("流水账产生");
			x=b.getMony();
			x+=money;
			if(card.modifyMoney(InNumber, x, conn)!=0) {
				System.out.println("转入成功");	
			}
			e.setNumber(InNumber);
			e.setMoney(money);
			e.setType(3);
			e.setDescription("转账");
			account.add(e);
			System.out.println("流水账产生");
			conn.commit();
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
		Data.Remove(conn);
	}
	
	public void ChangePassword(int number,String password,String changePassword) {
		Connection conn=Data.getConnection();
		if(null==conn) {
			System.out.println("空指针异常");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(number,conn);
			if(null==a) {
				System.out.println("账号或者密码不存在");
				Data.Remove(conn);
				return;
			}
			if(!password.equals(a.getPassword())) {
				System.out.println("账号或者密码不存在");
				Data.Remove(conn);
				return;
			}
			if(card.modifyPassword(number, changePassword, conn)!=0) {
				System.out.println("密码修改成功");
			}
			conn.commit();
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
		Data.Remove(conn);
	}
	
	public void List(int number) {
		Connection conn=Data.getConnection();
		if(null==conn) {
			System.out.println("空指针异常");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(number,conn);
			if(null==a) {
				System.out.println("账号不存在");
				Data.Remove(conn);
				return;
			}
			ArrayList<Account> c=account.List(number, conn);
			if(null==c) {
				System.out.println("无相关信息");
				Data.Remove(conn);
				return;
			}
			else {
				for(Account i:c) {
					System.out.println(i);
				}
			conn.commit();
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
		Data.Remove(conn);
	}
	
}
