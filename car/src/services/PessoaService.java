package services;

import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import business.Documentacao;
import business.Endereco;
import business.Pessoa;

public class PessoaService {
	private Pessoa pessoa;

	public JSONObject add(Request request) {
		String nome;
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

		Query query = request.getQuery();

		nome = query.get("nome");
		senha = query.get("senha");
		cpf = query.get("cpf");
		rg = query.get("rg");
		cnh = query.get("cnh");
		telefone = query.get("telefone");
		celular = query.get("celular");
		rua = query.get("rua");
		numero = query.getInteger("numero");
		bairro = query.get("bairro");
		cidade = query.get("cidade");
		estado = query.get("estado");

		pessoa = new Pessoa (nome, senha, cpf, rg, cnh, /*File cpfImg, File rgImg, File cnhImg,
				File certificadoBonsAntecedentesImg,*/ telefone, celular, rua, numero, bairro, cidade, estado);

		/*if (pessoa != null) {
			pessoa.add(pessoa);
		}*/

		System.out.println(pessoa.toJson());
		return pessoa.toJson();

	}

	public PessoaService() {

	
	}
}
