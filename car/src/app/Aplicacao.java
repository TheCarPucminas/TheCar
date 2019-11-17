package app;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import business.Aluguel;
import business.Disponibilidade;
import business.Pessoa;
import business.Veiculo;
import collections.ListaPessoa;
import collections.ListaVeiculo;
import dao.AluguelDAO;
import dao.DAO;
import dao.DisponibilidadeDAO;
import dao.PessoaDAO;
import dao.VeiculoDAO;
import services.PessoaService;
import services.VeiculoService;

import java.io.PrintStream;

import org.json.JSONObject;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;

public class Aplicacao  implements Container {
	static PessoaService pessoaService = new PessoaService();
	static VeiculoService veiculoService = new VeiculoService();
	public void handle(Request request, Response response) {
		try {
			// Recupera a URL e o m�todo utilizado.

			String path = request.getPath().getPath();
			String method = request.getMethod();
			JSONObject mensagem;
			
			// Verifica qual a��o est� sendo chamada
			if (path.equalsIgnoreCase("/pessoa") && "GET".equals(method)) {
				mensagem = pessoaService.add(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}	
			
			if (path.equalsIgnoreCase("/veiculo") && "GET".equals(method)) {
				mensagem = veiculoService.add(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}	
			
			if (path.equalsIgnoreCase("/excluir-veiculo") && "GET".equals(method)) {
				mensagem = veiculoService.remove(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}
			
			if (path.equalsIgnoreCase("/login") && "GET".equals(method)) {
				mensagem = pessoaService.login(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void naoEncontrado(Response response, String path) throws Exception {
		JSONObject error = new JSONObject();
		error.put("error", "N�o encontrado.");
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

	
	public static void main(String[] args) throws Exception {
		int porta = 880;

		// Configura uma conex�o soquete para o servidor HTTP.
		Container container = new Aplicacao();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);

		System.out.println("Tecle ENTER para interromper o servidor...");
		
		System.in.read();
		
		conexao.close();
		servidor.stop();
		
		
		System.out.println("PESSOAS CADASTRADAS");
		ListaPessoa listPessoa = new ListaPessoa();
		List<Pessoa> pessoas = listPessoa.getAll();
		
		for (Pessoa pessoa: pessoas) {
			System.out.println(pessoa);
			System.out.println("---------------------------------");
		}
		
		System.out.println("PROCURANDO POR NOME");
		for (Pessoa pessoa : listPessoa.getPessoaNome("Dayane Gabriela Santos Cordeiro")) {
			System.out.println(pessoa);
			System.out.println("-------------  ---------------");
		}
		
		System.out.println("PROCURANDO POR BAIRRO");
		for (Pessoa pessoa : listPessoa.getPessoaBairro("Barreiro")) {
			System.out.println(pessoa);
			System.out.println("-------------  ---------------");
		}
		
		System.out.println("PROCURANDO POR CIDADE");
		for (Pessoa pessoa : listPessoa.getPessoaCidade("Nova Lima")) {
			System.out.println(pessoa);
			System.out.println("-------------  ---------------");
		}
		
		System.out.println("VE�CULOS CADASTRADOS");
		ListaVeiculo listVeiculo = new ListaVeiculo();
		List<Veiculo> veiculos = listVeiculo.getAll();
		
		for (Veiculo veiculo : veiculos) {
	 		System.out.println(veiculo);
			System.out.println("---------------------------------");
		}
		System.out.println("----------------------------------");
		System.out.println();
		
		System.out.println("VE�CULO ENCONTRADO");
		System.out.println(listVeiculo.get("CCC2222"));
		System.out.println("----------------------------------");
		System.out.println();
		
		System.out.println("VE�CULOS COM PLACA CCC");
		for (Veiculo veiculo: listVeiculo.search("CCC")) {
            System.out.println(veiculo);
            System.out.println("-------------  ---------------");
        }
		
		System.out.println("VE�CULOS COM ANO DE FABRICA��O MAIOR OU IGUAL A 2019");
		for (Veiculo veiculo : listVeiculo.getVeiculosAnoFabricacao(2019)) {
			System.out.println(veiculo);
			System.out.println("-------------  ---------------");
		}
		
		
	}
}