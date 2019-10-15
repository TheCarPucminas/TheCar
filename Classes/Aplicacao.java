package theCar;

import java.time.LocalDateTime;

public class Aplicacao {
	public static void main(String[] args) {
		Pessoa p1 = new Pessoa("Dayane", "1234", "379.250.846-00", "24.061.773-3", "91224498844", "(31)3434-9999", "(31)99999-9999",
				"Rua teste", 33, "Bairro teste", "Belo Horizonte", "Minas Gerais");
		
		System.out.println("Nome: " + p1.getNome());
		System.out.println("Senha: " + p1.getSenha());
		System.out.println("Telefone: " + p1.getTelefone());
		System.out.println("Celular: " + p1.getCelular());
		System.out.println("Rua: " + p1.getEndereco().getRua());
		System.out.println("Numero: " + p1.getEndereco().getNumero());
		System.out.println("Bairro: " + p1.getEndereco().getBairro());
		System.out.println("Cidade: " + p1.getEndereco().getCidade());
		System.out.println("Estado: " + p1.getEndereco().getEstado());
		
		System.out.println("\n===== Veículo =====");
		Veiculo v1 = new Veiculo("HMK1234", "Vermelho", 2018, 2019, "9BWZZZ377VT00425", "54377915156", "HB20", "Pointer GLi 2.0",
				4, 33000, "75%");
		System.out.println("Chassi: " + v1.getChassi());
		
		System.out.println("\n===== Disponibilidade =====");
		Disponibilidade d1 = new Disponibilidade();
		
		//LocalDateTime ORDEM: Ano, mês, dia, hora, minuto
		d1.setDataInicio(LocalDateTime.of(2019, 10, 1, 12, 0));
		d1.setDataFinal(LocalDateTime.of(2019, 10, 31, 12, 0));
		LocalDateTime agora = LocalDateTime.now();
		System.out.println(d1.consultaDisponibilidade(agora));
		LocalDateTime hora = LocalDateTime.of(2019, 11, 1, 12, 0);
		System.out.println(d1.consultaDisponibilidade(hora));
		
		System.out.println("\n===== Documentação =====");
		Documentacao doc = new Documentacao();
		System.out.println(doc.eCpfValido(p1.getDocumentacao().getCpf()));
		System.out.println(doc.eCnhValida(p1.getDocumentacao().getCnh()));
	}
}
