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

public class DisponibilidadeDAO implements DAO<Disponibilidade, String>{
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public DisponibilidadeDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}

	public void add(Disponibilidade disponibilidade) {
		try {
			outputFile.writeObject(disponibilidade);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar a pessoa '" + disponibilidade.getId() + "' no disco!");
			e.printStackTrace();
		}
	}

	public Disponibilidade getDisponibilidade(String chave) {
		Disponibilidade disponibilidade = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				disponibilidade = (Disponibilidade) inputFile.readObject();

				if (chave.contentEquals(chave)) {
					return disponibilidade;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o aluguel '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public List<Disponibilidade> getAll() {
		List<Disponibilidade> disponibilidades = new ArrayList<Disponibilidade>();
		Disponibilidade disponibilidade = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				disponibilidade = (Disponibilidade) inputFile.readObject();
				disponibilidades.add(disponibilidade);
				
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar pessoa no disco!");
			e.printStackTrace();
		}
		return disponibilidades;
	}

	public boolean update(Disponibilidade a) {
		List<Disponibilidade> disponibilidades = getAll();
		int index = disponibilidades.indexOf(a);

		if (index != -1) {
			disponibilidades.set(index, a);
			return true;
		}
		saveToFile(disponibilidades);
		return false;
	}

	public boolean remove(Disponibilidade a) {
		List<Disponibilidade> disponibilidades = getAll();
		int index = disponibilidades.indexOf(a);
		if (index != -1) {
			disponibilidades.remove(index);
			return true;
		}
		saveToFile(disponibilidades);
		return false;
	}

	private void saveToFile(List<Disponibilidade> disponibilidades) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Disponibilidade disponibilidade : disponibilidades) {
				outputFile.writeObject(disponibilidade);
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

	public Pessoa get(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aluguel getAluguel(String chave) {
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

	@Override
	public Pessoa get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
