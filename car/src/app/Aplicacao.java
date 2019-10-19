package app;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import business.Pessoa;
import dao.DAO;
import dao.PessoaDAO;
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
			
			// Verifica qual ação está sendo chamada
			if (path.equalsIgnoreCase("/pessoa") && "POST".equals(method)) {
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
		response.setValue("Access-Control-Allow-Origin", "null");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);

		if (json != null)
			body.println(json.toString());
		body.close();
	}

	
	public static void main(String[] args) throws IOException {
		int porta = 880;

		// Configura uma conexão soquete para o servidor HTTP.
		Container container = new Aplicacao();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);

		System.out.println("Tecle ENTER para interromper o servidor...");
		System.in.read();

		conexao.close();
		servidor.stop();
		
		DAO<Pessoa, String> pessoaDAO = new PessoaDAO("pessoa.bin");
		Pessoa p1 = new Pessoa("Dayane", "1234", "379.250.846-00", "24.061.773-3", "91224498844", "(31)3434-9999", "(31)99999-9999",
				"Rua teste", 33, "Bairro teste", "Belo Horizonte", "Minas Gerais");
		
		pessoaDAO.add(p1);
		
		pessoaDAO.add(new Pessoa("Hugo", "1234668", "379.250.846-00", "24.061.773-3", "91224498844", "(31)3434-9999", "(31)99999-9999",
				"Rua teste", 33, "Bairro teste", "Belo Horizonte", "Minas Gerais"));
		
		List<Pessoa> pessoas = pessoaDAO.getAll();

		p1.setNome("CAROLINA");
		pessoaDAO.update(p1);
		
		pessoas = pessoaDAO.getAll();

		System.out.println("---------------- UPDATE -------------------");
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa);
			System.out.println("---------------------------------------------");
		}

		pessoaDAO.remove(p1);
		
		pessoas = pessoaDAO.getAll();

		System.out.println("---------------- REMOVE -------------------");
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa);
			System.out.println("---------------------------------------------");
		}
	}
}