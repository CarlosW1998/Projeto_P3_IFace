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
	
	//Relationships
	public static int[][] relationshipFriendRequest = new int[Max][Max];
	public static int[][] relationshipFriendship = new int[Max][Max];
	
	//Mensager
	public static String[] mensagerData = new String[100000];
	public static int[] mensagerOrigin = new int[100000];
	public static int[] mensagerTarget = new int[100000];
	public static int mensagerSize = 0;
	
	//Community
	public static int[] communityMaster = new int[Max];
	public static String[] communityName = new String[Max];
	public static String[] communityDescription = new String[Max];
	public static int[][] communityMenbers = new int[Max][Max];
	public static int commynitySize = 0;
	
	private static void printBreakLine()
	{
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	public static void load()
	{
		int a, b;
		for(a = 0; a < Max; a++)
		{
			userId[a] = -1;
			communityMaster[a] = -1;
			for(b = 0; b < Max; b++)
			{
				relationshipFriendRequest[a][b] = 0;
				relationshipFriendship[a][b] = 0;
				communityMenbers[a][b] = 0;
			}
		}
		for(a = 0; a < 100000; a++)
		{
			mensagerOrigin[a] = -1;
			mensagerTarget[a] = -1;
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
			scan.nextLine();
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
	public static int FindAndreturnPositions(String Name)
	{
		int a;
		for(a = 0; a < Max; a++)
		{
			if(userId[a] != -1 && userEmail[a].equals(Name))
			{
				return a;
			}
			
		}
		return -1;
	}
	public static void SendFriendRequest()
	{
		System.out.print("Ditite o Email do Usuario que Deseja Enviar um Pedido de Amizade:");
		String Name = scan.nextLine();
		int find = FindAndreturnPositions(Name);
		if(find == -1)
		{
			System.out.println("Usuairo nao Encontrado");
			return ;
		}
		relationshipFriendRequest[find][currentUser] = 1;
		System.out.println("Solicitacao Enviada Com sucesso");
		return ;
	}
	public static void SeeFiendReques()
	{
		int a, k = 0;
		System.out.println("Tecle -1 para sair\nVoce tem as seguintes solicitacoes:");
		for(a = 0; a < Max; a++)
		{
			if(relationshipFriendRequest[currentUser][a] == 1)
			{
				k++;
				System.out.println(userName[a] + " - " + "Tecle " + a + " Para Aceitar a solitacao");
			}
		}
		if(k == 0) System.out.println("Voce nao tem nenhuma solicitacao");
		int acept = scan.nextInt();
		if(acept == -1) return;
		if(relationshipFriendRequest[currentUser][acept] == 1)
		{
			relationshipFriendRequest[currentUser][acept] = 0;
			relationshipFriendRequest[acept][currentUser] = 0;
			relationshipFriendship[currentUser][acept] = 1;
			relationshipFriendship[acept][currentUser] = 1; 
			System.out.println("Agora vc e " + userName[acept] + " sao amigos" );
		}
	}
	public static void PushMensage(int target)
	{
		if(mensagerSize >= 99999)
		{
			System.out.println("O sistema nao pode compotar mais mensagens");
		}
		scan.nextLine();
		int a;
		for(a = 0; a < Max; a++)
		{
			if(mensagerOrigin[a] == -1)
			{
				System.out.println("Digite a mensagem a ser Eviada:");
				mensagerData[a] = scan.nextLine();
				mensagerOrigin[a] = currentUser;
				mensagerTarget[a] = target;
				mensagerSize++;
				return;
			}
		}
		
	}
	public static void SeeMensagerList()
	{
		int a;
		System.out.println("Tecle -1 para sair");
		System.out.println("Usuaios:");
		for(a = 0; a < Max; a++)
		{
			if(a != currentUser && userId[a] != -1)
			{
				System.out.println("Tecle " + a + " Para enviar uma mensagem para:" + userName[a]);
			}
		}
		int opition = scan.nextInt();
		if(opition == -1) return ;
		if(userId[opition] != -1)
		{
			PushMensage(userId[opition]);
			System.out.println("Mensagem Enviada com Sucesso");
		}
	}
	public static void ListMensager()
	{
		int a, k = 0;
		for(a = 0; a < 100000; a++)
		{
			if(mensagerTarget[a] == userId[currentUser])
			{
				k++;
				System.out.println("De: " + userName[mensagerOrigin[a]] + ";\n" + mensagerData[a] + "\n");
			}
		}
		System.out.println("\n\n");
		if(k == 0)System.out.println("Voce Nao Possue Nenhuma Mensagem");
	}
	public static void PushCommunity()
	{
		int a;
		for(a = 0; a < Max; a++)
		{
			if(communityMaster[a] == -1)
			{
				communityMaster[a] = currentUser;
				System.out.print("Digite um Nome Para Sua Comunidade: ");
				communityName[a] = scan.nextLine();
				System.out.print("De uma Descricao Breve da sua Comunidade: ");
				communityDescription[a] = scan.nextLine();
				communityMenbers[a][currentUser] = 1;
				System.out.println("Comunidade Criada com Sucesso");
				commynitySize++;
				return ;
			}
		}
	}
	public static void CreateCommunity()
	{
		if(commynitySize >= Max-1)
		{
			System.out.println("Sistema nao suporta mais comunidades");
			return ;
		}
		PushCommunity();
		
	}
	public static void ListAllCommynity()
	{
		System.out.println("Tecle -1 parar Sair");
		int a;
		for(a = 0; a < Max; a++)
		{
			if(communityMaster[a] != -1) System.out.println(communityName[a] + "Tecle " + a + " Para entrar" +"\n" + communityDescription[a]);
		}
		int opition = scan.nextInt();
		if(opition == -1) return;
		if(communityMenbers[opition][currentUser] == 0)
		{
			communityMenbers[opition][currentUser] = 1;
			System.out.println("Voce Ingressou com sucesso na Comunidade");
		}
		else
		{
			System.out.println("Voce Ja Esta Nessa Comunidade");
		}
	}
	public static void EditCommunity(int community)
	{
		int a;
		System.out.println("Tecle -1 Para sair");
		for(a = 0; a < Max; a++)
		{
			if(communityMenbers[community][a] == 1 && currentUser != communityMaster[a])
			{
				System.out.println("Tecle " + a + " parar excluir " + userName[a]);
			}
		}
		int option = scan.nextInt();
		if(option == -1) return;
		if(communityMenbers[community][option] == 1)
		{
			communityMenbers[community][option] = 0;
			System.out.println("Usuario Removido com sucesso");
		}
		else System.out.println("Esse Usuario nao esta na comunidade");
	}
	public static void ListMyCommynit()
	{
		int a, k = 0;
		System.out.println("Tecle -1 para Sair");
		for(a = 0; a < Max; a++)
		{
			if(communityMenbers[a][currentUser] == 1) 
			{
				k++;
				System.out.print(communityName[a]);
				if(currentUser == communityMaster[a]) System.out.println(" - Dono - tecle " + a + " Para Editar a Comunidade");
			}
		}
		if(k == 0) System.out.println("Voce nao participa de nenhuma comunidade");
		int opition = scan.nextInt();
		if(opition == -1) return ;
		if(currentUser == communityMaster[opition]) EditCommunity(opition);
		else System.out.println("Voce nao e o Dono Dessa Comunidade");
	}
	public static void UserDetail()
	{
		int a;
		System.out.println("Name: " + userName[currentUser] + "\n" + "Email: " + userEmail[currentUser]);
		System.out.println("Comunidades: ");
		for(a = 0; a < Max; a++)
		{
			if(communityMenbers[a][currentUser] == 1)
			{
				System.out.println(communityName[a]);
			}
		}
		System.out.println("Amigos: ");
		for(a = 0; a < Max; a++)
		{
			if(relationshipFriendship[currentUser][a] == 1)
			{
				System.out.println(userName[a]);
			}
		}
	}
	public static void DeleteUser()
	{
		System.out.println("Vc realmente deseja Deletar o usuario? tecle 1 para sim e e 2 para nao");
		int option = scan.nextInt();
		if(option == 2) return;
		int a;
		for(a = 0; a < 100000; a++)
		{
			if(mensagerOrigin[a] == userId[currentUser] || mensagerTarget[a] == userId[currentUser])
			{
				mensagerOrigin[a] = -1;
				mensagerTarget[a] = -1;
				mensagerSize--;
			}
		}
		for(a = 0; a < Max; a++)
		{
			if(communityMaster[a] == currentUser)
			{
				communityMaster[a] = -1;
			}
			if(communityMenbers[a][currentUser] == 1)
			{
				communityMenbers[a][currentUser] = 0;
			}
			if(relationshipFriendship[currentUser][a] == 0)
			{
				relationshipFriendship[currentUser][a] = 0;
				relationshipFriendship[a][currentUser] = 0;				
			}			
		}
		userId[currentUser] = -1;
		userSize--;
		
	}
	public static void iface()
	{
		int opition;
		while(true)
		{
			System.out.println("Seja Bem Vindo ao IFace "+ userName[currentUser]+ " \nTecle 1 Para Editar o Perfil de Usuario\nTecle 2 Para Adicionar um Amigo\nTecle 3 Para Verificar suas Solicitacoes de Amizade\nTecle 4 Para Enviar uma Mensagem para Algum Usuario\nTecle 5 para ver suas mensagens\nTecle 6 para Criar uma comunidade\nTecle 7 para ver a lista de comunidades\nTecle 8 para Ver Suas Comunidades\nTecle 9 Para Obter Informacoes Do Seu Usuario\nTecle 10 para Deletar o Usuario\n Pressione 11 para Sair");
			opition = scan.nextInt();
			scan.nextLine();
			if(opition == 1)
			{
				printBreakLine();
				UpdateUser();
			}
			else if(opition == 2)
			{
				printBreakLine();
				SendFriendRequest();
			}
			else if(opition == 3)
			{
				printBreakLine();
				SeeFiendReques();
			}
			else if(opition == 4)
			{
				printBreakLine();
				SeeMensagerList();
			}
			else if(opition == 5)
			{
				printBreakLine();
				ListMensager();
				
			}
			else if(opition == 6)
			{
				printBreakLine();
				CreateCommunity();
			}
			else if(opition == 7)
			{
				printBreakLine();
				ListAllCommynity();
				
			}
			else if(opition == 8)
			{
				printBreakLine();
				ListMyCommynit();
			}
			else if(opition == 9)
			{
				printBreakLine();
				UserDetail();
			}
			else if(opition == 10)
			{
				printBreakLine();
				DeleteUser();
			}
			else {
				return ;
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
