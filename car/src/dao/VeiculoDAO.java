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

public class VeiculoDAO implements DAO<Veiculo, String> {
	private File file;
	private FileOutputStream fos;
	private AppendableObjectOutputStream outputFile;

	public VeiculoDAO(String filename) throws IOException {
		file = new File(filename);
		boolean append = file.exists();
		fos = new FileOutputStream(file, append); 
		outputFile = new AppendableObjectOutputStream(fos, append);
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
		List<Veiculo> veiculos = getAll();
		Stream<Veiculo> stream = veiculos.stream().filter(veiculo -> veiculo.getPlaca().contentEquals(chave));
		return stream.findFirst().get();
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

	public boolean update(Veiculo p) {
		List<Veiculo> veiculos = getAll();
		int index = veiculos.indexOf(p);

		if (index != -1) {
			veiculos.set(index, p);
			saveToFile(veiculos);
			return true;
		}
		
		return false;
	}

	public boolean remove(Veiculo p) throws IOException {
		List<Veiculo> veiculos = getAll();
		
		//int index = veiculos.indexOf(p);
		int index = procuraVeiculoPlaca(veiculos, p.getPlaca());
		
		if (index != -1) {
			veiculos.remove(index);
			close();
			file.delete();
			saveToFile(veiculos);
			return true;
		}
		
		return false;
	}
	
	private int procuraVeiculoPlaca (List<Veiculo> veiculos, String placa) {
		int i = 0;
		for (Veiculo veiculo : veiculos) {
			if (veiculo.getPlaca().contentEquals(placa)) {
				return i;
			}
			i++;	
		}
			
		return -1;
	}

	private void saveToFile(List<Veiculo> veiculos) {
		try {
			close();
			boolean append = file.exists();
			fos = new FileOutputStream(file, append); 
			outputFile = new AppendableObjectOutputStream(fos, append);

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
	public Pessoa get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}