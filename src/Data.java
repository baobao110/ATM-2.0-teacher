import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;

public class Data {
	private static LinkedList<Connection> conn=new LinkedList<Connection>();
	static {
		Properties a=new Properties();
		try {
			a.load(Data.class.getResourceAsStream("/1.properties"));
			Class.forName(a.getProperty("Driver"));
			for(int i=0;i<10;i++) {
				conn.add(DriverManager.getConnection(a.getProperty("url"),a.getProperty("user"),a.getProperty("password")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static Connection getConnection() {
		return conn.pollLast();
	}
	public static void Remove(Connection e) {
		if(null==e) {
			System.out.println("¿ÕÖ¸ÕëÒì³£");
		}
		else {
			conn.add(e);
		}
	}
	public static int CreateNumber() {
		String a="";
		Random b=new Random();
		a+=(b.nextInt(9)+1);
		for(int i=0;i<5;i++) {
			Random c=new Random();
			a+=c.nextInt(10);
		}
		return Integer.parseInt(a);
	}
}
