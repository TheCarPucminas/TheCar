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

public class VeiculoDAO implements DAO<Veiculo, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public VeiculoDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}

	public void add(Veiculo veiculo) {
		try {
			outputFile.writeObject(veiculo);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar a pessoa '" + veiculo.getPlaca() + "' no disco!");
			e.printStackTrace();
		}
	}

	public Veiculo getVeiculo(String chave) {
		Veiculo veiculo = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				veiculo = (Veiculo) inputFile.readObject();

				if (chave.contentEquals(chave)) {
					return veiculo;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o produto '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public List<Veiculo> getAll() {
		List<Veiculo> veiculos = new ArrayList<Veiculo>();
		Veiculo veiculo = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				veiculo = (Veiculo) inputFile.readObject();
				veiculos.add(veiculo);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar pessoa no disco!");
			e.printStackTrace();
		}
		return veiculos;
	}

	public void update(Veiculo p) {
		List<Veiculo> veiculos = getAll();
		int index = veiculos.indexOf(p);

		if (index != -1) {
			veiculos.set(index, p);
		}
		saveToFile(veiculos);
	}

	public void remove(Veiculo p) {
		List<Veiculo> veiculos = getAll();
		int index = veiculos.indexOf(p);
		if (index != -1) {
			veiculos.remove(index);
		}
		saveToFile(veiculos);
	}

	private void saveToFile(List<Veiculo> veiculos) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Veiculo veiculo : veiculos) {
				outputFile.writeObject(veiculo);
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
	public Pessoa get(String chave) {
		// TODO Auto-generated method stub
		return null;
	}
}