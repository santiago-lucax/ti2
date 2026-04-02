package app;

// Bibliotecas e classes necessarias
import java.util.*;
import dao.DAO;
import model.Alunos;

public class Principal {
	
	static Scanner sc = new Scanner(System.in);
	DAO dao = new DAO();
	
	// Metodo para criar o objeto de insercao
	public void inserir() {
		
		System.out.print("Digite um nome: ");
		String nome = sc.next();
		
		System.out.print("\nDigite a matricula: ");
		String matricula = sc.next();
		
		System.out.print("\nDigite o sexo: ");
		String sexo = sc.next();
		
		System.out.println("");
		
		dao.inserir(new Alunos(nome, matricula, sexo));
		
	}
	
	// Metodo para criar o objeto de listagem
	public void listar() {
		
		dao.listar().forEach(a -> {
			
			System.out.println("-------------------------------------");
			System.out.printf("%-15s %-10s %-10s\n", a.getNome(), a.getMatricula(), a.getSexo());
			
		});
		
	}
	
	// Metodo para criar o objeto de atualizacao
	public void atualizar() {
		
		System.out.print("Digite a matricula que deseja atualizar: ");
		String matricula = sc.next();
		
		System.out.print("\nDigite o novo nome: ");
		String nome = sc.next();
		
		System.out.print("\nDigite o novo sexo: ");
		String sexo = sc.next();
		
		System.out.println("");
		
		dao.atualizar(new Alunos(nome, matricula, sexo));
		
	}
	
	// Metodo para criar o objeto de exclusao
	public void excluir() {
		
		System.out.print("Digite a matricula que deseja excluir: ");
		String matricula = sc.next();
		
		dao.deletar(matricula);
		
	}
	
	public static void main(String args[]) {
		
		Principal p = new Principal(); // Para chamar metodos e funções da classe Principal
		
		boolean OK = false;
		while (OK != true) {
		
			// Menu
			System.out.println("\n--- Menu ---\n\n"
							 + "1) Inserir\n"
							 + "2) Listar\n"
							 + "3) Atualizar\n"
							 + "4) Excluir\n"
							 + "5) Sair\n\n"
							 + "Opcao: ");
			
			// Ler opção
			int x = sc.nextInt();
			
			// Escolher opção
			switch (x) {
			
				case 1: p.inserir(); break; // Metodo de insercao
				case 2: p.listar(); break; // Metodo de listagem
				case 3: p.atualizar(); break; // Metodo de atualizacao
				case 4: p.excluir(); break; // Metodo de exclusao
				case 5: OK = true; break; // Parar repeticao
			
			}
		
		}
		
		System.out.println("Fim do programa.");
	}

}
