package services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import business.Aluguel;
import business.Disponibilidade;
import business.Veiculo;
import collections.ListaAlugueis;
import collections.ListaDisponibilidade;
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
	
	public JSONObject adicionaDisponibilidade (Request request) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		int idVeiculo;
		LocalDateTime dataInicio;
		LocalDateTime dataFinal;
		LocalDateTime date;
		double valorDaDiaria;
		Query query = request.getQuery();
		
		idVeiculo = query.getInteger("idVeiculo");
		dataInicio = LocalDateTime.parse(query.get("dataInicio"),formatter);
		dataFinal = LocalDateTime.parse(query.get("dataFinal"),formatter);
		valorDaDiaria = query.getFloat("valorDaDiaria");
		
		ListaVeiculo listVeiculo = new ListaVeiculo();
		Veiculo veiculo = listVeiculo.getPorId(idVeiculo);
		Disponibilidade disponibilidade = new Disponibilidade(idVeiculo, dataInicio, dataFinal, valorDaDiaria);
	    veiculo.addDisponibilidade(disponibilidade);
	    
	    ListaDisponibilidade listaDisponibilidades = new ListaDisponibilidade();
	    listaDisponibilidades.add(disponibilidade);
	    
		return disponibilidade.toJson();
	}
	
	public JSONObject adicionaAluguel (Request request) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime dataEmprestimo;
		LocalDateTime dataDevolucao;
		double valorAluguel;
		boolean devolvido;
		boolean pago;
		int idVeiculo;
		int idLocatario;
		Query query = request.getQuery();
		
		dataEmprestimo = LocalDateTime.parse(query.get("dataEmprestimo"),formatter);
		dataDevolucao = LocalDateTime.parse(query.get("dataDevolucao"),formatter);
		valorAluguel = query.getFloat("valorAluguel");
		devolvido = query.getBoolean("devolvido");
		pago = query.getBoolean("pago");
		idVeiculo = query.getInteger("idVeiculo");
		idLocatario = query.getInteger("idLocatario");
		
		ListaVeiculo listVeiculo = new ListaVeiculo();
		Veiculo veiculo = listVeiculo.getPorId(idVeiculo);
		Aluguel aluguel = new Aluguel(dataEmprestimo, dataDevolucao, valorAluguel, devolvido, pago, idVeiculo, idLocatario);
	    
		ListaAlugueis listaAlugueis = new ListaAlugueis();
	    listaAlugueis.add(aluguel);
	    
		return aluguel.toJson();
	}
	
	public JSONObject consultaDisponibilidade (Request request) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		Query query = request.getQuery();
		
		LocalDateTime dataInicial =  LocalDateTime.parse(query.get("dataInicial"),formatter);
		LocalDateTime dataFinal =  LocalDateTime.parse(query.get("dataFinal"),formatter);
		
		ListaDisponibilidade listaDisponibilidade = new ListaDisponibilidade();
		List<Veiculo> veiculosDisponiveis = new ArrayList<Veiculo>();
		veiculosDisponiveis = listaDisponibilidade.consultaDisponibilidade(dataInicial, dataFinal);
		
		JSONObject object = new JSONObject();
		JSONArray list = new JSONArray();

		for (Veiculo veiculo : veiculosDisponiveis) {
	 		list.put(veiculo.toJson());	
		}
		
		object.accumulate("values", list);
	    
		return object;
	}
	
	public JSONObject consultaAluguelVeiculo (Request request) throws Exception {
		Query query = request.getQuery();
		
		int idVeiculo = query.getInteger("idVeiculo");
		
		ListaAlugueis listaAlugueis = new ListaAlugueis();
		List<Aluguel> alugueisCadastrados = new ArrayList<Aluguel>();
		
		alugueisCadastrados = listaAlugueis.getAlugueisPorVeiculo(idVeiculo);
		
		JSONObject object = new JSONObject();
		JSONArray list = new JSONArray();

		for (Aluguel aluguel : alugueisCadastrados) {
	 		list.put(aluguel.toJson());	
		}
		
		object.accumulate("values", list);
	    
		return object;
	}
	
	public JSONObject consultaAluguelLocatario (Request request) throws Exception {
		Query query = request.getQuery();
		
		int idLocatario = query.getInteger("idLocatario");
		
		ListaAlugueis listaAlugueis = new ListaAlugueis();
		List<Aluguel> alugueisCadastrados = new ArrayList<Aluguel>();
		
		alugueisCadastrados = listaAlugueis.getAlugueisPorLocatario(idLocatario);
		
		JSONObject object = new JSONObject();
		JSONArray list = new JSONArray();

		for (Aluguel aluguel : alugueisCadastrados) {
	 		list.put(aluguel.toJson());	
		}
		
		object.accumulate("values", list);
	    
		return object;
	}
}
