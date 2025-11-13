package campeonatobrasileirorest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CampeonatoBrasileiroREST {
    private static final String API_TABLE_URL = "https://flashlive-sports.p.rapidapi.com/v1/tournaments/standings?tournament_season_id=214FgOUJ&locale=pt_BR&tournament_stage_id=Yi0LvPEj&standing_type=overall";
    private static final String API_HOST = "flashlive-sports.p.rapidapi.com";
    private static String API_KEY;

    public static void main(String[] args) {
        /* 
        List<Equipe> ListaTimes = List.of(
            new Equipe("Palmeiras", 1, 68, 32, 21, "Copa Libertadores: Fase de Grupos", "palmeiras.png"),
            new Equipe("Flamengo", 2, 65, 32, 20, "Copa Libertadores: Fase de Grupos", "flamengo.png")
        );
        CampeonatoView campeonatoView = new CampeonatoView(ListaTimes);*/
        
       
        List<Equipe> ListaTimes = new ArrayList<>();
        try {
            API_KEY = Files.readString(Paths.get("src/api_key.txt")).trim();
        } catch (IOException ex) {
            System.err.println("Erro ao ler o arquivo de chave API: " + ex.getMessage());
            return;
        }
        
        HttpResponse<String> resposta = null;
        try {
            HttpRequest requisicao = HttpRequest.newBuilder()
                   .uri(URI.create(API_TABLE_URL))
                    .header("x-rapidapi-key", API_KEY)
                    .header("x-rapidapi-host", "flashlive-sports.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            resposta = HttpClient.newHttpClient().send(requisicao, HttpResponse.BodyHandlers.ofString());
            System.out.println(resposta.body());
        } catch (IOException ex) {
            Logger.getLogger(CampeonatoBrasileiroREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(CampeonatoBrasileiroREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (resposta != null){
            try {
                JSONObject corpoJson = new JSONObject(resposta.body());
                JSONArray dataArray = corpoJson.getJSONArray("DATA");
                JSONObject groupObject = dataArray.getJSONObject(0);
                JSONArray tabela = groupObject.getJSONArray("ROWS");
                int quantidadeTimes = tabela.length();
                String qualificacao;
                
                for (int i = 0; i < quantidadeTimes; i++){
                    JSONObject times = tabela.getJSONObject(i);
                    
                    int posicao = times.getInt("RANKING");
                    String nomeTime = times.getString("TEAM_NAME");
                    int pontos = times.getInt("POINTS");
                    int partidasJogadas = times.getInt("MATCHES_PLAYED");
                    int vitorias = times.getInt("WINS");
                    String escudoPath = times.getString("TEAM_IMAGE_PATH");
                    
                    if (times.has("TEAM_QUALIFICATION")){
                            qualificacao = switch (times.getString("TEAM_QUALIFICATION")) {
                            case "q1" -> "Fase de Grupos: Copa Libertadores";
                            case "q2" -> "Qualificado: Copa Libertadores";
                            case "q3" -> "Qualificado: Copa Sul-Americana";
                            case "r1" -> "Rebaixamento";
                            default -> "";
                        };
                    }
                    else {
                        qualificacao = "-";
                    }
                    
                    ListaTimes.add(new Equipe(nomeTime, posicao, pontos, partidasJogadas, vitorias, qualificacao, escudoPath));
                    System.out.println(posicao + "|" + nomeTime + "|" + pontos);
                }
        
                CampeonatoView campeonatoView = new CampeonatoView(ListaTimes);
                
            } catch (JSONException ex) {
                Logger.getLogger(CampeonatoBrasileiroREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
