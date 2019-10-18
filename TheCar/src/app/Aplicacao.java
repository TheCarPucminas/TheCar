package app;
import java.io.IOException;
import java.util.List;
import business.Pessoa;
import dao.DAO;
import dao.PessoaDAO;

public class Aplicacao {
	public static void main(String[] args) throws IOException {
		DAO<Pessoa, String> pessoaDAO = new PessoaDAO("pessoa.bin");
		Pessoa p1 = new Pessoa("Dayane", "1234", "379.250.846-00", "24.061.773-3", "91224498844", "(31)3434-9999", "(31)99999-9999",
				"Rua teste", 33, "Bairro teste", "Belo Horizonte", "Minas Gerais");
		
		pessoaDAO.add(p1);
		
		pessoaDAO.add(new Pessoa("Dayane", "1234668", "379.250.846-00", "24.061.773-3", "91224498844", "(31)3434-9999", "(31)99999-9999",
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