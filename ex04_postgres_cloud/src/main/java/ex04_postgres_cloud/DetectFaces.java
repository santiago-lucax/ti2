package ex04_postgres_cloud;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner; 
import org.json.*;

public class DetectFaces {
    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Detector de Rostos");
        System.out.println("------------------------------------");

       
        System.out.print("Por favor, insira sua Chave da API de Face: ");
        String subscriptionKey = scanner.nextLine();

       
        System.out.print("Agora, insira o Endpoint da API de Face: ");
        String endpoint = scanner.nextLine();
        
     
        scanner.close(); 

        
        if (subscriptionKey == null || endpoint == null || subscriptionKey.trim().isEmpty() || endpoint.trim().isEmpty()) {
            System.err.println("\nERRO: A chave da API e o endpoint são obrigatórios. O programa será encerrado.");
            return;
        }

        System.out.println("\nCredenciais recebidas. Iniciando análise...");
        System.out.println("------------------------------------");

        String[] imagens = {
            "https://upload.wikimedia.org/wikipedia/commons/a/a3/Elderly_Gambian_woman_face_portrait.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/US_Navy_070315-N-3642E-063_Secretary_of_the_Navy_%28SECNAV%29%2C_the_Honorable_Donald_C._Winter_discusses_the_Littoral_Combat_Ship_%28LCS%29_acquisition_program_during_a_press_conference_in_the_Pentagon.jpg/800px-thumbnail.jpg",
        };

        for (int i = 0; i < imagens.length; i++) {
            System.out.println("\nAnalisando imagem " + (i + 1) + "...");
            detectarRosto(endpoint, subscriptionKey, imagens[i]);
        }
        
        System.out.println("\n------------------------------------");
        System.out.println("Análise concluída.");
    }

    
    private static void detectarRosto(String endpoint, String key, String imageUrl) {
        try {

            if (!endpoint.endsWith("/")) {
                  endpoint = endpoint + "/";
            }
            
            URL url = new URL(endpoint + "face/v1.0/detect?returnFaceId=false");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Ocp-Apim-Subscription-Key", key);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonBody = "{ \"url\": \"" + imageUrl + "\" }";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                 System.out.println("  Erro na requisição: Código " + responseCode + " " + conn.getResponseMessage());
                 return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) response.append(line);

            JSONArray faces = new JSONArray(response.toString());
            if (faces.length() > 0) {
                System.out.println("  Rostos detectados: " + faces.length());
                for (int i = 0; i < faces.length(); i++) {
                    JSONObject rect = faces.getJSONObject(i).getJSONObject("faceRectangle");
                    System.out.printf("  - Rosto %d → Top: %d, Left: %d, Width: %d, Height: %d%n",
                            i + 1, rect.getInt("top"), rect.getInt("left"),
                            rect.getInt("width"), rect.getInt("height"));
                }
            } else {
                System.out.println("  Nenhum rosto detectado.");
            }

        } catch (IOException | JSONException e) {
            System.out.println("  Erro ao detectar rosto: " + e.getMessage());
        }
    }
}