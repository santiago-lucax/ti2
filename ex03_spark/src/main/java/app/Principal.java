package app;

import static spark.Spark.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import service.AlunosService;

public class Principal {

    private static AlunosService alunoService = new AlunosService();

    public static void main(String[] args) {

        port(4567);

        // Página inicial
        get("/", (request, response) -> {
            response.type("text/html");
            return renderHTML();
        });

        // ROTAS (CRUD)
        post("/aluno/inserir", (req, res) -> alunoService.inserir(req, res));
        post("/aluno/listar", (req, res) -> alunoService.listar(req, res));
        post("/aluno/atualizar", (req, res) -> alunoService.atualizar(req, res));
        post("/aluno/deletar", (req, res) -> alunoService.deletar(req, res));
    }

    // Lê o HTML
    private static String renderHTML() {
        try {
            return new String(Files.readAllBytes(
                Paths.get("src/main/resources/public/index.html")
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao carregar index.html";
        }
    }
}