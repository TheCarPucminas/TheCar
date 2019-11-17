package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import business.Aluguel;
import business.Disponibilidade;
import business.Documentacao;
import business.Endereco;
import business.Pessoa;
import business.Veiculo;

public class EnderecoDAO  implements DAO<Endereco, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public EnderecoDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}

	public void add(Endereco endereco) {
		try {
			outputFile.writeObject(endereco);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar a pessoa '" + endereco.getRua() + "' no disco!");
			e.printStackTrace();
		}
	}

	public Endereco getEndereco(String chave) {
		Endereco endereco = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				endereco = (Endereco) inputFile.readObject();

				if (chave.contentEquals(chave)) {
					return endereco;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o produto '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public List<Endereco> getAll() {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		Endereco endereco = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				endereco = (Endereco) inputFile.readObject();
				enderecos.add(endereco);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar pessoa no disco!");
			e.printStackTrace();
		}
		return enderecos;
	}

	public boolean update(Endereco p) {
		List<Endereco> endereco = getAll();
		int index = endereco.indexOf(p);

		if (index != -1) {
			endereco.set(index, p);
			return true;
		}
		saveToFile(endereco);
		return false;
	}

	public boolean remove(Endereco p) {
		List<Endereco> endereco = getAll();
		int index = endereco.indexOf(p);
		if (index != -1) {
			endereco.remove(index);
			return true;
		}
		saveToFile(endereco);
		return false;
	}

	private void saveToFile(List<Endereco> enderecos) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Endereco endereco : enderecos) {
				outputFile.writeObject(endereco);
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
	public Veiculo getVeiculo(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
