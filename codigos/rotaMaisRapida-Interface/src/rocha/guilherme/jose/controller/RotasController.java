package rocha.guilherme.jose.controller;

import javax.swing.JOptionPane;

import rocha.guilherme.jose.controller.helper.RotasHelper;
import rocha.guilherme.jose.model.ModelLugarDestino;
import rocha.guilherme.jose.model.ModelLugarPartida;
import rocha.guilherme.jose.model.ModelTrajeto;
import rocha.guilherme.jose.view.RotasView;

public class RotasController {

	private final RotasView rotasView;
	private final RotasHelper helper;
	
	public RotasController(RotasView rotasView) {
		this.rotasView = rotasView;
		this.helper = new RotasHelper(rotasView);
	}

	public void preencherComboBox() {
		helper.preencherComboBox();
		
	}

	public void buscarTrajetoNoGoogleMaps() {
		ModelLugarPartida inicio = helper.obterLugarPartida();
		ModelLugarDestino fim = helper.obterLugarDestino();
		ModelTrajeto trajeto = new ModelTrajeto(inicio, fim);

		if(helper.verificarCampos()) {
			if(helper.validarCampos()) {
				if(inicio.lugarExiste()) {
					if(fim.lugarExiste()) {
						if(helper.verificarUf()) {
							trajeto.mostrarRotas();						
						}else if(rotasView.exibeMensagemDecisao("Se você não informar a UF, o programa ficará encarregado de "
								+ "buscar o estado do local de partida e de destino. \n\n"
								+ "Pode haver problemas se existir cidade com o mesmo nome em estados diferentes. O que deseja fazer?") == JOptionPane.YES_OPTION) {
							trajeto.mostrarRotas();							
						}
					}else {
						rotasView.exibeMensagemInformativa("O local de destino não existe!");
					}
				}else {
					rotasView.exibeMensagemInformativa("O local de partida não existe!");
				}
			}else {
				rotasView.exibeMensagemInformativa("O local de destino/partida só podem ter letras.");
			}
		}else {
			rotasView.exibeMensagemInformativa("Preencha todos os campos!");
		}
	}
}
