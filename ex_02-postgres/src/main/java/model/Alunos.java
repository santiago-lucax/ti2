package model;

public class Alunos {
	
	private int id;
	private String nome;
	private String matricula;
	private String sexo;
	
	public Alunos() {}
	
	public Alunos(String nome, String matricula, String sexo) {
		
		this.nome = nome;
		this.matricula = matricula;
		this.sexo = sexo;
		
	}
	
	public String getNome() {
		
		return nome;
		
	}
	
	public String getMatricula() {
		
		return matricula;
		
	} 
	
	public String getSexo() {
		
		return sexo;
		
	}
	
	public void setNome(String nome) {
		
		this.nome = nome;
		
	}
	
	public void setMatricula(String matricula) {
		
		this.matricula = matricula;
		
	}
	
	public void setSexo(String sexo) {
		
		this.sexo = sexo;
		
	}
	
	@Override
	public String toString() {
		
		return "Aluno [id = " + id + "nome = " + nome + "matricula = " + matricula + "sexo = " + sexo + "]"; 
		
	}

}
