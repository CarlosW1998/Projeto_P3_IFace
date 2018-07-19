package Iface;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
public class iface {
	
	static Scanner scan;
	static int Max = 999;
	
	//Users
	public static int[] userId = new int[Max];
	public static String[] userEmail = new String[Max];
	public static String[] userName = new String[Max];
	public static String[] userPassword = new String[Max];
	public static int userSize = 0;
	public static AtomicInteger idControl = new AtomicInteger(10000);
	public static int currentUser = -1;
	
	private static void printBreakLine()
	{
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	public static void load()
	{
		int a;
		for(a = 0; a < Max; a++)
		{
			userId[a] = -1;
		}
	}
	/////////////////////////////////////////// LOGIN
	public static boolean seting_User(String email, String password)
	{
		int a;
		for(a = 0; a < Max; a++)
		{
			if(userId[a] != -1 && email.equals(userEmail[a]) && password.equals(userPassword[a])) 
			{
				currentUser = a;
				return true;
			}
		}
		return false;
	}
	
	public static boolean Login()
	{
		scan.nextLine();
		System.out.println("Digite seu Email e a senha: ");
		System.out.print("Email:");
		String user = scan.nextLine();
		System.out.print("Senha:");
		String password = scan.nextLine();
		return seting_User(user, password);
	}
	
	public static boolean ExistUser(String email)
	{
		int a;
		for(a = 0; a < Max; a++)
		{
			if(userId[a] != -1 && userEmail[a] == email) {
				return true;
			}
		}
		return false;
	}
	public static boolean push_user(String email, String name, String password)
	{
		int a;
		for(a = 0; a < Max; a++)
		{
			if(userId[a] == -1) {
				userId[a] = idControl.incrementAndGet();
				userEmail[a] = email;
				userName[a] = name;
				userPassword[a] = password;
				userSize++;
				return true;
			}
		}
		return false;
	}
	public static boolean add_user()
	{
		if(userSize >= Max)
		{
			System.out.println("Nao e possivel cadastrar novos usuarios");
			return false;
		}
		scan.nextLine();
		System.out.print("Email: ");
		String newEmail = scan.nextLine();
		
		System.out.print("Nome de Usuario: ");
		String newName = scan.nextLine();
		
		System.out.print("Senha: ");
		String newPassword = scan.nextLine();
		
		if(ExistUser(newEmail))
		{
			System.out.println("Email ja existe");
			return false;
		}
		return push_user(newEmail, newName, newPassword); 
	}
	
	private static boolean DisplayLogin()
	{
		int option;
		while(true) {
			System.out.println("IFACE \nTecle 1 para Entrar no Sistema \nTecle 2 para Criar um Novo Usuario\nTecle 3 Para Sair");
			option = scan.nextInt();
			if(option == 1)
			{
				if(Login())
				{
					printBreakLine();
					return true;
				}
				printBreakLine();
				System.out.println("Usuario ou Senha Invalidos");
			}
			else if(option == 2) 
			{
				if(add_user())
				{
					printBreakLine();
					System.out.println("Usuario criado com sucesso");
				}
			}
			else if(option == 3) return false;
			else {
				printBreakLine();
				System.out.println("Entrada Invalida");
			}
			
		}
	}
	
	/////////////////////////////////////////// FIM DE LOGIN
	
	/////////////////////////////////////////// DENTRO DO SISTEMA
	public static void UpdateUser()
	{
		System.out.println("Tecle 1 para Alterar o Email \nTecle 2 para Alterar o Nome de Usuario\nTecle 3 para alterar a senha\nTecle 4 para sair");
		int opition = scan.nextInt();
		String newatribute;
		scan.nextLine();
		if(opition == 1)
		{
			System.out.println("Email: ");
			newatribute = scan.nextLine();
			userEmail[currentUser] = newatribute;
			System.out.println("Email Alterado com Sucesso");
		}
		else if(opition == 2)
		{
			System.out.println("Name: ");
			newatribute = scan.nextLine();
			userName[currentUser] = newatribute;
			System.out.println("Nome Alterado com Sucesso");
		}
		else if(opition == 3)
		{
			System.out.println("Password: ");
			newatribute = scan.nextLine();
			userPassword[currentUser] = newatribute;
			System.out.println("Password Alterado com Sucesso");
		}
		else if(opition == 4) return;
		System.out.println("Entrada invalida");
		
	}
	
	public static void iface()
	{
		int opition;
		while(true)
		{
			System.out.println("Seja Bem Bindo ao IFace "+ userName[currentUser]+ " \nTecle 1 Para Editar o Perfil de Usuario");
			opition = scan.nextInt();
			if(opition == 1)
			{
				printBreakLine();
				UpdateUser();
			}
		}
	}
	
	public static void main(String [] args) {
		scan = new Scanner(System.in);
		load();
		boolean exit = true;
		while(exit) {
			exit = DisplayLogin();
			if(exit)
			{
				printBreakLine();
				iface();
			}			
		}
		
		
	}
}
