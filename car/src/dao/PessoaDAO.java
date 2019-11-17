package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import business.Aluguel;
import business.Disponibilidade;
import business.Documentacao;
import business.Endereco;
import business.Pessoa;
import business.Veiculo;

public class PessoaDAO implements DAO<Pessoa, String> {
	private File file;
	private FileOutputStream fos;
	private AppendableObjectOutputStream outputFile;

	public PessoaDAO(String filename) throws IOException {
		file = new File(filename);
		boolean append = file.exists();
		fos = new FileOutputStream(file, append); 
		outputFile = new AppendableObjectOutputStream(fos, append);
	}

	public void add(Pessoa pessoa) {
		try {
			outputFile.writeObject(pessoa);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar a pessoa '" + pessoa.getNome() + "' no disco!");
			e.printStackTrace();
		}
	}
	
	public Pessoa get(int chave) {				
		List<Pessoa> pessoas = getAll();
		Stream<Pessoa> stream = pessoas.stream().filter(pessoa -> pessoa.getId() == chave);
		return stream.findFirst().get();
	}
	
	public Pessoa getEmail(String chave) {
		Pessoa pessoa = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				pessoa = (Pessoa) inputFile.readObject();
				if (pessoa.getEmail().contentEquals(chave)) {
					return pessoa;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o produto '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}
	
	public Pessoa getSenha(String chave) {
		Pessoa pessoa = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				pessoa = (Pessoa) inputFile.readObject();
				if (pessoa.getSenha().contentEquals(chave)) {
					return pessoa;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o produto '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public List<Pessoa> getAll() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa pessoa = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				pessoa = (Pessoa) inputFile.readObject();
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar pessoa no disco!");
			e.printStackTrace();
		}
		return pessoas;
	}

	public boolean update(Pessoa p) {
		List<Pessoa> pessoas = getAll();
		int index = pessoas.indexOf(p);

		if (index != -1) {
			pessoas.set(index, p);
			return true;
		}
		saveToFile(pessoas);
		return false;
	}

	public boolean remove(Pessoa p) {
		List<Pessoa> pessoas = getAll();
		int index = pessoas.indexOf(p);
		if (index != -1) {
			pessoas.remove(index);
			return true;
		}
		saveToFile(pessoas);
		return false;
	}

	private void saveToFile(List<Pessoa> pessoas) {
		try {
			close();
			boolean append = file.exists();
			fos = new FileOutputStream(file, append); 
			outputFile = new AppendableObjectOutputStream(fos, append);

			for (Pessoa pessoa : pessoas) {
				outputFile.writeObject(pessoa);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar produto no disco!");
			e.printStackTrace();
		}
	}
	
	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		this.close();
	}

	@Override
	public Aluguel getAluguel(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Disponibilidade getDisponibilidade(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documentacao getDocumentacao(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Endereco getEndereco(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Veiculo getVeiculo(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

}
