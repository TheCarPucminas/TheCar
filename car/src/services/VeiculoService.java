package services;

import java.io.IOException;

import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import business.Veiculo;
import dao.VeiculoDAO;

public class VeiculoService {
	private Veiculo veiculo;
	
	public JSONObject remove(Request request) throws IOException {
		String placa;
		
		Query query = request.getQuery();

	    placa = query.get("placa");
	    
	    
	    VeiculoDAO veiculoDAO = new VeiculoDAO("veiculo.bin");
	    Veiculo veiculo = veiculoDAO.getVeiculo(placa);
		veiculoDAO.remove(veiculo);

		return veiculo.toJson();
	}
	public JSONObject add(Request request) throws IOException {
		String placa;
		String cor;
		int anoFabricacao;
		int anoModelo;
		String chassi;
		String renavam;
		String marca;
		String modelo;
		int numeroPortas;
		int quilometragem;
		String combustivel;

		Query query = request.getQuery();

	    placa = query.get("placa");
	    cor = query.get("cor");
	    anoFabricacao = query.getInteger("anoFabricacao");
	    anoModelo = query.getInteger("anoModelo");
	    chassi = query.get("chassi");
	    renavam = query.get("renavam");
	    marca = query.get("marca");
	    modelo = query.get("modelo");
	    numeroPortas = query.getInteger("numeroPortas");
	    quilometragem = query.getInteger("quilometragem");
	    combustivel = query.get("combustivel");


	    veiculo = new Veiculo (placa, cor, anoFabricacao, anoModelo, chassi, renavam, marca, modelo, numeroPortas, quilometragem, combustivel);

		

		/*if (pessoa != null) {
			pessoa.add(pessoa);
		}*/

		return veiculo.toJson();

	}

	public VeiculoService() {

	
	}
}
