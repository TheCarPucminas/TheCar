package dao;
import java.util.List;

import business.Aluguel;
import business.Disponibilidade;
import business.Documentacao;
import business.Endereco;
import business.Pessoa;
import business.Veiculo;

public interface DAO<T, K> {
	public Pessoa get(K chave);
	public Aluguel getAluguel(K chave);
	public Disponibilidade getDisponibilidade(K chave);
	public Documentacao getDocumentacao(K chave);
	public Endereco getEndereco(K chave);
	public Veiculo getVeiculo(K chave);
	public void add(T p);
	public void update(T p);
	public void remove(T p);
	public List<T> getAll();
}