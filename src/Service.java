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
			System.out.println("��ָ���쳣");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			if(card.open(password,conn)==1) {
				System.out.println("�����ɹ�");	
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
			System.out.println("��ָ���쳣");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(number,conn);
			if(null==a) {
				System.out.println("�˺Ż������벻����");
				Data.Remove(conn);
				return;
			}
			if(!password.equals(a.getPassword())) {
				System.out.println("�˺Ż������벻����");
				Data.Remove(conn);
				return;
			}
			if(money<0) {
				System.out.println("���С����");
				Data.Remove(conn);
				return;
			}
			double x=a.getMony();
			x+=money;
			if(card.modifyMoney(number, x, conn)!=0) {
				System.out.println("��Ǯ�ɹ�");	
			}
			Account e=new Account();
			e.setNumber(number);
			e.setMoney(money);
			e.setType(1);
			e.setDescription("��Ǯ");
			account.add(e);
			System.out.println("��ˮ�˲���");
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
			System.out.println("��ָ���쳣");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(number,conn);
			if(null==a) {
				System.out.println("�˺Ż������벻����");
				Data.Remove(conn);
				return;
			}
			if(!password.equals(a.getPassword())) {
				System.out.println("�˺Ż������벻����");
				Data.Remove(conn);
				return;
			}
			if(money<0) {
				System.out.println("���С����");
				Data.Remove(conn);
				return;
			}
			if(money>a.getMony()) {
				System.out.println("����");
				Data.Remove(conn);
				return;
			}
			double x=a.getMony();
			x-=money;
			if(card.modifyMoney(number, x, conn)!=0) {
				System.out.println("ȡǮ�ɹ�");	
			}
			Account e=new Account();
			e.setNumber(number);
			e.setMoney(money);
			e.setType(2);
			e.setDescription("ȡǮ");
			account.add(e);
			System.out.println("��ˮ�˲���");
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
			System.out.println("��ָ���쳣");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(OutNumber,conn);
			if(null==a) {
				System.out.println("�˺Ż������벻����");
				Data.Remove(conn);
				return;
			}
			if(!password.equals(a.getPassword())) {
				System.out.println("�˺Ż������벻����");
				Data.Remove(conn);
				return;
			}
			Card b=new Card();
			b=card.GetCard(InNumber,conn);
			if(null==b) {
				System.out.println("�˺Ų�����");
				Data.Remove(conn);
				return;
			}
			if(money>a.getMony()) {
				System.out.println("���С����");
				Data.Remove(conn);
				return;
			}
			double x=a.getMony();
			x-=money;
			if(card.modifyMoney(OutNumber, x, conn)!=0) {
				System.out.println("ת���ɹ�");	
			}
			Account e=new Account();
			e.setNumber(OutNumber);
			e.setMoney(money);
			e.setType(3);
			e.setDescription("ת��");
			account.add(e);
			System.out.println("��ˮ�˲���");
			x=b.getMony();
			x+=money;
			if(card.modifyMoney(InNumber, x, conn)!=0) {
				System.out.println("ת��ɹ�");	
			}
			e.setNumber(InNumber);
			e.setMoney(money);
			e.setType(3);
			e.setDescription("ת��");
			account.add(e);
			System.out.println("��ˮ�˲���");
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
			System.out.println("��ָ���쳣");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(number,conn);
			if(null==a) {
				System.out.println("�˺Ż������벻����");
				Data.Remove(conn);
				return;
			}
			if(!password.equals(a.getPassword())) {
				System.out.println("�˺Ż������벻����");
				Data.Remove(conn);
				return;
			}
			if(card.modifyPassword(number, changePassword, conn)!=0) {
				System.out.println("�����޸ĳɹ�");
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
			System.out.println("��ָ���쳣");
			Data.Remove(conn);
			return;
		}
		try {
			conn.setAutoCommit(false);
			Card a=card.GetCard(number,conn);
			if(null==a) {
				System.out.println("�˺Ų�����");
				Data.Remove(conn);
				return;
			}
			ArrayList<Account> c=account.List(number, conn);
			if(null==c) {
				System.out.println("�������Ϣ");
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
