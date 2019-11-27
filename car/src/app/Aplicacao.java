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
import collections.ListaDisponibilidade;
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

			if (path.equalsIgnoreCase("/pesquisa") && "GET".equals(method)) {
				mensagem = veiculoService.pesquisa(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}	
			
			if (path.equalsIgnoreCase("/adiciona-disponibilidade") && "GET".equals(method)) {
				mensagem = veiculoService.adicionaDisponibilidade(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}	
			
			// CONSULTA DISPONIBILIDADE COM DATA DE IN�CIO E DATA DE FINAL
			if (path.equalsIgnoreCase("/consulta-disponibilidade") && "GET".equals(method)) {
				mensagem = veiculoService.consultaDisponibilidade(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}
			
			// ADICIONA ALUGUEL
			if (path.equalsIgnoreCase("/adiciona-aluguel") && "GET".equals(method)) {
				mensagem = veiculoService.adicionaAluguel(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}
			
			// CONSULTA ALUGUEL COM ID DO VE�CULO
			if (path.equalsIgnoreCase("/consulta-aluguel-veiculo") && "GET".equals(method)) {
				mensagem = veiculoService.consultaAluguelVeiculo(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}
			
			// CONSULTA ALUGUEL COM ID DO LOCAT�RIO
			if (path.equalsIgnoreCase("/consulta-aluguel-locatario") && "GET".equals(method)) {
				mensagem = veiculoService.consultaAluguelLocatario(request);
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
		int porta = 8080;

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
//		
//		System.out.println("PROCURANDO POR NOME");
//		for (Pessoa pessoa : listPessoa.getPessoaNome("Dayane Gabriela Santos Cordeiro")) {
//			System.out.println(pessoa);
//			System.out.println("-------------  ---------------");
//		}
//		
//		System.out.println("PROCURANDO POR BAIRRO");
//		for (Pessoa pessoa : listPessoa.getPessoaBairro("Barreiro")) {
//			System.out.println(pessoa);
//			System.out.println("-------------  ---------------");
//		}
//		
//		System.out.println("PROCURANDO POR CIDADE");
//		for (Pessoa pessoa : listPessoa.getPessoaCidade("Nova Lima")) {
//			System.out.println(pessoa);
//			System.out.println("-------------  ---------------");
//		}
		
		System.out.println("VE�CULOS CADASTRADOS");
		ListaVeiculo listVeiculo = new ListaVeiculo();
		List<Veiculo> veiculos = listVeiculo.getAll();
		
		for (Veiculo veiculo : veiculos) {
	 		System.out.println(veiculo);
			System.out.println("---------------------------------");
		}
		System.out.println("----------------------------------");
		System.out.println();
		
		
		System.out.println("DISPONIBILIDADES DO VE�CULO 0");
		ListaDisponibilidade listDisponibilidade = new ListaDisponibilidade();
		List<Disponibilidade> disponibilidades = listDisponibilidade.getAll();
		for (Disponibilidade d : disponibilidades) {
			System.out.println(d);
			System.out.println("---");
		}
			
//		System.out.println("VE�CULOS POR PROPRIET�RIO");
//		System.out.println();
//		for (Veiculo veiculo: listVeiculo.getVeiculosPorProprietario(0)) {
//          System.out.println(veiculo);
//          System.out.println("-------------  ---------------");
//      }
//		
//		System.out.println("VE�CULO ENCONTRADO");
//		System.out.println(listVeiculo.get("CCC2222"));
//		System.out.println("----------------------------------");
//		System.out.println();
//		
//		System.out.println("VE�CULOS COM PLACA CCC");
//		for (Veiculo veiculo: listVeiculo.search("CCC")) {
//            System.out.println(veiculo);
//            System.out.println("-------------  ---------------");
//        }
//		
//		System.out.println("VE�CULOS COM ANO DE FABRICA��O MAIOR OU IGUAL A 2019");
//		for (Veiculo veiculo : listVeiculo.getVeiculosAnoFabricacao(2019)) {
//			System.out.println(veiculo);
//			System.out.println("-------------  ---------------");
//		}
		
		
	}
}