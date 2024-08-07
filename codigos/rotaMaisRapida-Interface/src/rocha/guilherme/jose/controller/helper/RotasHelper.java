package rocha.guilherme.jose.controller.helper;

import javax.swing.DefaultComboBoxModel;

import rocha.guilherme.jose.model.ModelLugarDestino;
import rocha.guilherme.jose.model.ModelLugarPartida;
import rocha.guilherme.jose.view.RotasView;

public class RotasHelper {

	private final RotasView rotasView;

	public RotasHelper(RotasView rotasView) {
		this.rotasView = rotasView;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void preencherComboBox() {
		
		String[] ufs = new String[] {"UF", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", 
				"MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
		
		rotasView.getComboBoxUFPartida().setModel(new DefaultComboBoxModel(ufs));
		rotasView.getComboBoxUFDestino().setModel(new DefaultComboBoxModel(ufs));

	
	}

	public ModelLugarPartida obterLugarPartida() {
		
		ModelLugarPartida lugarPartida = new ModelLugarPartida("", "");
		
		if(rotasView.getRdbtnAdicionarLocalizao().isSelected()) {
			
			String nomeCidade = rotasView.getTextFieldLocalPartida().getText();
			String ufEstado = rotasView.getComboBoxUFPartida().getSelectedItem().toString();
			
			String nomeEstado = converterUfParaEstado(ufEstado);
			
			lugarPartida.setNomeCidade(nomeCidade);
			lugarPartida.setNomeEstado(nomeEstado);
		}
		
		return lugarPartida;
	}

	public ModelLugarDestino obterLugarDestino() {
		String nomeCidade = rotasView.getTextFieldLocalDestino().getText();
		String ufEstado = rotasView.getComboBoxUFDestino().getSelectedItem().toString();
		
		String nomeEstado = converterUfParaEstado(ufEstado);
		
		ModelLugarDestino lugarDestino = new ModelLugarDestino(nomeCidade, nomeEstado);
		return lugarDestino;
	}
	
	private String converterUfParaEstado(String ufEstado) {
		if(ufEstado.equals("AC")) return "Acre";
		if(ufEstado.equals("AL")) return "Alagoas";
		if(ufEstado.equals("AP")) return "Amapá";
		if(ufEstado.equals("AM")) return "Amazonas";
		if(ufEstado.equals("BA")) return "Bahia";
		if(ufEstado.equals("CE")) return "Ceará";
		if(ufEstado.equals("DF")) return "Distrito Federal";
		if(ufEstado.equals("ES")) return "Espírito Santo";
		if(ufEstado.equals("GO")) return "Goias";
		if(ufEstado.equals("MA")) return "Maranhão";
		if(ufEstado.equals("MT")) return "Mato Grosso";
		if(ufEstado.equals("MS")) return "Mato Grosso do Sul";
		if(ufEstado.equals("MG")) return "Minas Gerais";
		if(ufEstado.equals("PA")) return "Pará";
		if(ufEstado.equals("PB")) return "Paraíba";
		if(ufEstado.equals("PR")) return "Paraná";
		if(ufEstado.equals("PE")) return "Pernambuco";
		if(ufEstado.equals("PI")) return "Piauí";
		if(ufEstado.equals("RJ")) return "Rio de Janeiro";
		if(ufEstado.equals("RN")) return "Rio Grande do Norte";
		if(ufEstado.equals("RS")) return "Rio Grande do Sul";
		if(ufEstado.equals("RO")) return "Rondônia";
		if(ufEstado.equals("RR")) return "Roraima";
		if(ufEstado.equals("SC")) return "Santa Catarina";
		if(ufEstado.equals("SP")) return "São Paulo";
		if(ufEstado.equals("SE")) return "Sergipe";
		if(ufEstado.equals("TO")) return "Tocantins";
		
		return "";
	}

	public boolean verificarCampos() {
		if(rotasView.getTextFieldLocalDestino().getText().trim().isEmpty() || 
				rotasView.getRdbtnAdicionarLocalizao().isSelected() &&
				rotasView.getTextFieldLocalPartida().getText().trim().isEmpty()) {
			return false;			
		}
		
		return true;
	}

	public boolean validarCampos() {
		String partida = rotasView.getTextFieldLocalPartida().getText();
		String destino = rotasView.getTextFieldLocalDestino().getText();
		
		if(rotasView.getRdbtnAdicionarLocalizao().isSelected() && !partida.matches("[\\p{L} ]+")) {
			return false;			
		}
		
		if(!destino.matches("[\\p{L} ]+")) {
			return false;			
		}
		
		return true;
	}

	public boolean verificarUf() {
		if(rotasView.getComboBoxUFDestino().getSelectedItem().equals("UF") ||
				rotasView.getRdbtnAdicionarLocalizao().isSelected() &&
				rotasView.getComboBoxUFPartida().getSelectedItem().equals("UF")) {
			return false;			
		}
		
		return true;
	}
	
}
