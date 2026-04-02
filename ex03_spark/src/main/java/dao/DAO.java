package dao;

// Classes necessarias
import dao.Connect;
import model.Alunos;

// Bibliotecas necessarias
import java.sql.*;
import java.util.*;

public class DAO {
	// CREATE
	public boolean inserir(Alunos aluno) {
		Connect db = new Connect(); // Objeto para conexao com BD
		boolean status = false; // Status da conexao
		
		// Tente
		try {
			db.conectar(); // Conectar
			String sql = "INSERT INTO alunos (nome, matricula, sexo) VALUES (?, ?, ?)"; // Linha de comando SQL
			PreparedStatement st = db.getConexao().prepareStatement(sql); // Utiliza conexao e compila comando SQL
			
			// Atribuir valores ao objeto
			st.setString(1, aluno.getNome());
			st.setString(2, aluno.getMatricula());
			st.setString(3, String.valueOf(aluno.getSexo()));
			
			st.executeUpdate(); // Aplicar 
			st.close(); // Fechar
			
			status = true; 
		} catch (Exception e) {
			System.out.println("Erro ao inserir: " + e.getMessage()); // Mensagem de erro
		}
		
		db.close(); // Desligar conexao
		return status; // Retornar status da conexao
	}
	
	// READ
	public List<Alunos> listar() {
		List<Alunos> alunos = new ArrayList<>(); // List<tipo_generico>
		Connect db = new Connect(); // Objeto de conexao

		try {
			db.conectar(); // Tentar conexao
			
			String sql = "SELECT * FROM alunos"; // Mensagem SQL
			
			Statement st = db.getConexao().createStatement(); // Utiliza conexao e compila comando SQL
			ResultSet rs = st.executeQuery(sql); // Guardar query
			
			while (rs.next()) {
				Alunos a = new Alunos(); // Objeto
			
				a.setNome(rs.getString("nome")); // Mostrar nome
				a.setMatricula(rs.getString("matricula")); // Mostrar matricula
				a.setSexo(rs.getString("sexo")); // Mostrar sexo

				alunos.add(a); // Adicionar um objeto Alunos dentro da lista alunos
			}
			
			rs.close(); // Fechar
			st.close(); // Fechar
		} catch (Exception e) {
			System.out.println("Erro ao listar: " + e.getMessage()); // Mensagem de erro
		}
		
		db.close(); // Fechar conexao
		return alunos; // Retornar lista de alunos
	}
	
	// UPDATE
    public boolean atualizar(Alunos aluno) {
        Connect db = new Connect(); // Objeto para conexao
        boolean status = false; // Status de conexao
        
        try {
            db.conectar(); // Tentar conectar ao banco

            String sql = "UPDATE alunos SET nome=?, sexo=? WHERE matricula=?"; // Comando SQL

            PreparedStatement st = db.getConexao().prepareStatement(sql); // Utiliza conexao e compila comando SQL

            st.setString(1, aluno.getNome()); // Atualizar nome
            st.setString(2, aluno.getSexo()); // Atualizar sexo
            st.setString(3, aluno.getMatricula()); // Matricula

            int linhasAfetadas = st.executeUpdate(); // Aplicar
            
            if (linhasAfetadas == 0) {
                System.out.println("Erro: matrícula não encontrada.");
            } else {
                System.out.println("Aluno atualizado com sucesso.");
                status = true;
            }
            
            st.close(); // Fechar

            status = true; // Mudar status
        } catch (Exception e) {
            System.err.println("Erro ao atualizar: " + e.getMessage()); // Mensagem de erro

        }

        db.close(); // Fechar conexao
        return status; // Retornar status
    }

    // DELETE
    public boolean deletar(String matricula) {
        Connect db = new Connect(); // Objeto para conexao
        boolean status = false; // Status de conexao

        try {
            db.conectar(); // Tentar conectar ao banco

            String sql = "DELETE FROM alunos WHERE matricula=?"; // Comando SQL

            PreparedStatement st = db.getConexao().prepareStatement(sql); // Utiliza conexao e compila comando SQL

            st.setString(1, matricula); // Deletar aluno da matricula x
            
            int linhasAfetadas = st.executeUpdate(); // Aplicar

            if (linhasAfetadas == 0) {
                System.out.println("Erro: matrícula não encontrada.");
            } else {
                System.out.println("Aluno removido com sucesso.");
                status = true;
            }
            
            st.close(); // Fechar 

            status = true; // Atualizar status

        } catch (Exception e) {
            System.err.println("Erro ao deletar: " + e.getMessage()); // Mensagem de erro
        }

        db.close(); // Fechar conexao
        return status; // Retornar status
    }
}

