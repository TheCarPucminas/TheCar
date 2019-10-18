package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import business.Pessoa;

public class PessoaDAO implements DAO<Pessoa, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public PessoaDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}

	public void add(Pessoa pessoa) {
		try {
			outputFile.writeObject(pessoa);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar a pessoa '" + pessoa.getNome() + "' no disco!");
			e.printStackTrace();
		}
	}

	public Pessoa get(String chave) {
		Pessoa pessoa = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				pessoa = (Pessoa) inputFile.readObject();

				if (chave.contentEquals(chave)) {
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

	public void update(Pessoa p) {
		List<Pessoa> pessoas = getAll();
		int index = pessoas.indexOf(p);

		if (index != -1) {
			pessoas.set(index, p);
		}
		saveToFile(pessoas);
	}

	public void remove(Pessoa p) {
		List<Pessoa> pessoas = getAll();
		int index = pessoas.indexOf(p);
		if (index != -1) {
			pessoas.remove(index);
		}
		saveToFile(pessoas);
	}

	private void saveToFile(List<Pessoa> pessoas) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

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
}
