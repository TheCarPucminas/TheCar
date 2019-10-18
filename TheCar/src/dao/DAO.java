package dao;
import java.util.List;

import business.Pessoa;

public interface DAO<T, K> {
	public Pessoa get(K chave);
	public void add(T p);
	public void update(T p);
	public void remove(T p);
	public List<T> getAll();
}