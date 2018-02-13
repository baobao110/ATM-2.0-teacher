
public class Test {
	private static final Service service=new Service();
	
	public static void main(String[] args) {
		
		//save(945246,"222222",100);
		//ChangePassword(945246,"222222","223333333");
	}
	
	public static void open(String password) {
		String pass="[0-9a-zA-Z]{6,12}";
		if(!password.matches(pass)) {
			System.out.println("密码格式不正确");
		}
		else {
			service.open(password);
		}
	}
	
	public static void save(int number,String password,double money) {
		service.save(number, password, money);
	}
	
	public static void draw(int number,String password,double money) {
		service.draw(number, password, money);
	}
	
	public static void transfer(int OutNumber,String password,double money,int InNumber) {
		service.transfer(OutNumber, password, money, InNumber);
	}
	
	public static void List(int number) {
		service.List(number);
	}
	
	public static void ChangePassword(int number,String password,String changePassword) {
		String pass="[0-9a-zA-Z]{6,12}";
		if(!password.matches(pass)) {
			System.out.println("密码格式不正确");
		}
		else {
			service.ChangePassword(number, password, changePassword);
		}
	}
}
