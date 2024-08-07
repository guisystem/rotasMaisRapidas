package rocha.guilherme.jose.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ModelLugar {

	private String nomeCidade;
	private String nomeEstado;
	
	private final String GEONAMES_USERNAME = "guilhermecode";
	
	public ModelLugar(String nomeCidade, String nomeEstado) {
		this.nomeCidade = nomeCidade;
		this.nomeEstado = nomeEstado;
	}
	
	public String getNomeCidade() {
		return nomeCidade;
	}
	
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	
	public String getNomeEstado() {
		return nomeEstado;
	}
	
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
	
	public boolean lugarExiste() {
		HttpURLConnection conn = null;
        InputStreamReader in = null;
        StringBuilder sb = new StringBuilder();
        
        try {
            String encodedCidade = URLEncoder.encode(nomeCidade, "UTF-8");
            String urlString = "http://api.geonames.org/searchJSON?q=" + encodedCidade + "&country=BR&maxRows=1&username=" + GEONAMES_USERNAME;
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            in = new InputStreamReader(conn.getInputStream());
            try (Scanner scanner = new Scanner(in)) {
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());
                }
            }

            JSONObject json = new JSONObject(sb.toString());
            if (json.has("geonames")) {
                JSONArray geonames = json.getJSONArray("geonames");
                return geonames.length() > 0;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
