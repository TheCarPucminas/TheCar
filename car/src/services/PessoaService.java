package services;

import java.io.IOException;

import error.ExcecaoGeral;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import business.Documentacao;
import business.Endereco;
import business.Pessoa;
import dao.PessoaDAO;

public class PessoaService {
	private Pessoa pessoa;

	public JSONObject login (Request request) throws IOException {
		String email;
		String senha;
		
		Query query = request.getQuery();
		
		senha = query.get("senha");
		email = query.get("email");
		
		PessoaDAO pessoaDAO = new PessoaDAO("pessoa.bin");
		
		Pessoa p1 = pessoaDAO.getEmail(email);
		if (p1 != null) {
			p1 = pessoaDAO.getSenha(senha);
			if (p1 != null) {
				return p1.toJson();
			}
				
		}
		
		return null;
	}
	
	public JSONObject add(Request request) throws Exception {
		String nome;
		String email;
		String senha;
		String cpf;
		String rg;
		String cnh;
		String telefone;
		String celular;
		String rua;
		int numero;
		String bairro;
		String cidade;
		String estado;
		String cep;

		Query query = request.getQuery();

		telefone = query.get("telefone");
		nome = query.get("nome");
		email = query.get("email");
		cpf = query.get("cpf");
		rg = query.get("rg");
		cnh = query.get("cnh");
		senha = query.get("senha");
		cep = query.get("cep");
		rua = query.get("rua");
		try {
			numero = query.getInteger("numero");
		} catch (Exception e) {
			throw new ExcecaoGeral("O numero de endereço digitado e invalido");
		}
		bairro = query.get("bairro");
		cidade = query.get("cidade");
		estado = query.get("estado");
		celular = query.get("celular");

		pessoa = new Pessoa (nome, email, cpf, rg, cnh, senha, cep, rua, numero, bairro, cidade, estado, telefone, celular);

		PessoaDAO pessoaDAO = new PessoaDAO("pessoa.bin");
		pessoaDAO.add(pessoa);

		/*if (pessoa != null) {
			pessoa.add(pessoa);
		}*/

		return pessoa.toJson();

	}

	public PessoaService() {

	
	}
}
