package app;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.util.List;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import business.Aluguel;
import business.Disponibilidade;
import business.Pessoa;
import business.Veiculo;
import dao.AluguelDAO;
import dao.DAO;
import dao.DisponibilidadeDAO;
import dao.PessoaDAO;
import dao.VeiculoDAO;
import services.PessoaService;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.json.JSONObject;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class Aplicacao  implements Container {
	static PessoaService pessoaService = new PessoaService();
	public void handle(Request request, Response response) {
		try {
			// Recupera a URL e o método utilizado.

			String path = request.getPath().getPath();
			String method = request.getMethod();
			JSONObject mensagem;

			System.out.println("Path: " +  path + "  method: " + method);
			System.out.println("Query:" + request.getQuery().toString());
			
			// Verifica qual ação está sendo chamada
			if (path.equalsIgnoreCase("/pessoa/todos") && "POST".equals(method)) {
				// http://127.0.0.1:880/adicionarProduto?descricao=leite&preco=3.59&quant=10&tipo=2&dataFabricacao=2017-01-01
				mensagem = pessoaService.add(request);
				this.enviaResposta(Status.CREATED, response, mensagem);

			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void naoEncontrado(Response response, String path) throws Exception {
		JSONObject error = new JSONObject();
		error.put("error", "Não encontrado.");
		error.put("path", path);
		enviaResposta(Status.NOT_FOUND, response, error);
	}
	
	private void enviaResposta(Status status, Response response, JSONObject json) throws Exception {

		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();

		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "Controle de Estoque Service (1.0)");
		response.setValue("Access-Control-Allow-Origin", "*");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);

		if (json != null)
			body.println(json.toString());
		body.close();
	}

	
	public static void main(String[] args) throws IOException {
		LocalDateTime agora = LocalDateTime.now();
		int porta = 880;

		// Configura uma conexão soquete para o servidor HTTP.
		/*Container container = new Aplicacao();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);

		System.out.println("Tecle ENTER para interromper o servidor...");
		System.in.read();

		conexao.close();
		servidor.stop();*/
		
		DAO<Pessoa, String> pessoaDAO = new PessoaDAO("pessoa.bin");
		Pessoa p1 = new Pessoa("Dayane", "1234", "379.250.846-00", "24.061.773-3", "91224498844", "(31)3434-9999", "(31)99999-9999",
				"Rua teste", 33, "Bairro teste", "Belo Horizonte", "Minas Gerais");
		
		DAO<Aluguel, String> aluguelDAO = new AluguelDAO("aluguel.bin");
		Aluguel a1 = new Aluguel(agora, agora, 100, false, false);
		aluguelDAO.add(a1);
		List<Aluguel> alugueis = aluguelDAO.getAll();
		
		DAO<Disponibilidade, String> disponibilidadeDAO = new DisponibilidadeDAO("disponibilidade.bin");
		Disponibilidade d1 = new Disponibilidade(agora, agora, 100);
		disponibilidadeDAO.add(d1);
		List<Disponibilidade> disponibilidades = disponibilidadeDAO.getAll();
		System.out.println("jshkjhskdjhkjash");
		for (Disponibilidade disponibilidade : disponibilidades) {
			System.out.println(disponibilidade.getValorDaDiaria());
			System.out.println("---------------------------------------------");
			
		}
		
		DAO<Veiculo, String> veiculoDAO = new VeiculoDAO("veiculo.bin");
		Veiculo v1 = new Veiculo("CCC1111", "Azul", 2019, 2019, "CHSHHD87872", "hsgahsa",
				"FIAT", "Palio", 4, 1000, "Gasolina");
		veiculoDAO.add(v1);
		List<Veiculo> veiculos = veiculoDAO.getAll();
		
		for (Veiculo veiculo : veiculos) {
			System.out.println(veiculo.getPlaca());
			System.out.println("---------------------------------------------");
			
		}
	}
}