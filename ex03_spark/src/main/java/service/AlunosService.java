package service;

import dao.DAO;
import model.Alunos;
import spark.Request;
import spark.Response;

import java.util.List;

public class AlunosService {

    private DAO dao = new DAO();
    
    // LISTAR
    public Object listar(Request req, Response res) {

        List<Alunos> lista = dao.listar();

        String html = "<h1>Lista de Alunos</h1>";

        for (Alunos a : lista) {
            html += "<p>" 
                 + a.getNome() + " | "
                 + a.getMatricula() + " | "
                 + a.getSexo()
                 + "</p>";
        }

        return html;
    }

    // INSERIR
    public Object inserir(Request req, Response res) {

        String nome = req.queryParams("nome");
        String matricula = req.queryParams("matricula");
        String sexo = req.queryParams("sexo");
        
        // Validacao
        if(nome == null || nome.isEmpty() ||
           matricula == null || matricula.isEmpty() ||
           sexo == null || sexo.isEmpty()) {
            res.status(400);
            return "Erro: Todos os campos devem ser preenchidos!";
        }

        Alunos aluno = new Alunos();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        aluno.setSexo(sexo);

        boolean status = dao.inserir(aluno);

        if (status) {
            res.status(201);
            return "Aluno inserido com sucesso!";
        } else {
            res.status(500);
            return "Erro ao inserir aluno";
        }
    }

    // ATUALIZAR
    public Object atualizar(Request req, Response res) {

        String nome = req.queryParams("nome");
        String matricula = req.params(":matricula");
        String sexo = req.queryParams("sexo");

        Alunos aluno = new Alunos();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        aluno.setSexo(sexo);

        boolean status = dao.atualizar(aluno);

        if (status) {
            return "Aluno atualizado!";
        } else {
            res.status(404);
            return "Aluno não encontrado";
        }
    }

    // DELETAR
    public Object deletar(Request req, Response res) {

        String matricula = req.queryParams("matricula");

        boolean status = dao.deletar(matricula);

        if (status) {
            return "Aluno deletado!";
        } else {
            res.status(404);
            return "Aluno não encontrado";
        }
    }
    

}