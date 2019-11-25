package services;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import business.Veiculo;
import collections.ListaVeiculo;
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
	public JSONObject add(Request request) throws Exception {
		int idProprietario;
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

		idProprietario = query.getInteger("idProprietario");
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
	    

	    veiculo = new Veiculo (idProprietario, placa, cor, anoFabricacao, anoModelo, chassi, renavam, marca, modelo, numeroPortas, quilometragem, combustivel);

	    ListaVeiculo listaVeiculos = new ListaVeiculo();
	    listaVeiculos.add(veiculo);
	    
		/*if (pessoa != null) {
			pessoa.add(pessoa);
		}*/

		return veiculo.toJson();

	}

	public JSONObject pesquisa(Request request) throws Exception {
		String bairro;
		Query query = request.getQuery();
	    bairro = query.get("bairro");
	    ListaVeiculo listVeiculo = new ListaVeiculo();
		JSONObject object = new JSONObject();
		JSONArray list = new JSONArray();
		List<Veiculo> veiculos;
	   
	    if (bairro == null) {
	    	veiculos = listVeiculo.getAll();
	    }
	    else {
	    	veiculos = listVeiculo.getVeiculosPorBairro(bairro);
	    	System.out.println("AQUIII");
	    }
	    
	    for (Veiculo veiculo : veiculos) {
	 		list.put(veiculo.toJson());
		}
	    
	    object.accumulate("values", list);
		return object;
	}
	
	public JSONObject pesquisaPorBairro(Request request) throws Exception {
		String bairro;
		Query query = request.getQuery();
	    bairro = query.get("bairro");
		ListaVeiculo listVeiculo = new ListaVeiculo();
		List<Veiculo> veiculos = listVeiculo.getAll();
		JSONObject object = new JSONObject();
		JSONArray list = new JSONArray();

		for (Veiculo veiculo : veiculos) {
	 		list.put(veiculo.toJson());
	 		
		}
		
		object.accumulate("values", list);
	    
		return object;
	}
	
	public VeiculoService() {

	
	}
}
