package rocha.guilherme.jose.model;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ModelTrajeto {

	private ModelLugarPartida inicioTrajeto;
	private ModelLugarDestino fimTrajeto;
	
	public ModelTrajeto(ModelLugarPartida inicioTrajeto, ModelLugarDestino fimTrajeto) {
		this.inicioTrajeto = inicioTrajeto;
		this.fimTrajeto = fimTrajeto;
	}

	public ModelLugarPartida getInicioTrajeto() {
		return inicioTrajeto;
	}

	public void setInicioTrajeto(ModelLugarPartida inicioTrajeto) {
		this.inicioTrajeto = inicioTrajeto;
	}

	public ModelLugarDestino getFimTrajeto() {
		return fimTrajeto;
	}

	public void setFimTrajeto(ModelLugarDestino fimTrajeto) {
		this.fimTrajeto = fimTrajeto;
	}

	public void mostrarRotas() {
		
		String localizacaoAtual = "My+Location";
		String estadoInicio = "";
		
		if(!inicioTrajeto.getNomeCidade().isEmpty()) {
			localizacaoAtual = inicioTrajeto.getNomeCidade().replace(" ", "+");
			estadoInicio = inicioTrajeto.getNomeEstado().replace(" ", "+");
		}
		
		String destino = fimTrajeto.getNomeCidade().replace(" ", "+");
		String estadoFinal = fimTrajeto.getNomeEstado().replace(" ", "+");

		try {
            String url = "https://www.google.com/maps/dir/" + localizacaoAtual + "+" + estadoInicio + "/" + destino + "+" + estadoFinal;
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
		
	}
	
}
