package app; // pacote

// bibliotecas e classes
import static spark.Spark.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import service.AlunosService;

public class Principal {

    private static AlunosService alunoService = new AlunosService();

    public static void main(String[] args) {

        port(4567);
        
        staticFiles.location("/public"); // ler arquivos estaticos da pasta

        // carregar pagina inicial
        get("/", (req, res) -> {
            res.type("text/html");
            return renderHTML();
        });

        // rotas (CRUD)
        post("/aluno/inserir", (req, res) -> alunoService.inserir(req, res));
        post("/aluno/listar", (req, res) -> alunoService.listar(req, res));
        post("/aluno/atualizar", (req, res) -> alunoService.atualizar(req, res));
        post("/aluno/deletar", (req, res) -> alunoService.deletar(req, res));
    }

    // ler HTML
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