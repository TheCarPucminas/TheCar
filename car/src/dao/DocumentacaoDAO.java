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

public class DocumentacaoDAO implements DAO<Documentacao, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public DocumentacaoDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}

	public void add(Documentacao documentacao) {
		try {
			outputFile.writeObject(documentacao);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar a pessoa '" + documentacao.getId() + "' no disco!");
			e.printStackTrace();
		}
	}

	public Documentacao getDocumentacao(String chave) {
		Documentacao documentacao = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				documentacao = (Documentacao) inputFile.readObject();

				if (chave.contentEquals(chave)) {
					return documentacao;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o aluguel '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	public List<Documentacao> getAll() {
		List<Documentacao> documentacoes = new ArrayList<Documentacao>();
		Documentacao documentacao = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				documentacao = (Documentacao) inputFile.readObject();
				documentacoes.add(documentacao);
				
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar pessoa no disco!");
			e.printStackTrace();
		}
		return documentacoes;
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
	public Disponibilidade getDisponibilidade(String chave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Documentacao p) {
		// TODO Auto-generated method stub
		return true;
		
	}

	@Override
	public boolean remove(Documentacao p) {
		return true;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Aluguel getAluguel(String chave) {
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
