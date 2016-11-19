package br.com.headway.etl.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import br.com.headway.etl.model.Filial;
import br.com.headway.etl.repository.FilialRepository;

@ManagedBean
@SessionScoped
public class ArquivoBean implements Serializable {

	private static final Logger log = Logger.getLogger(ArquivoBean.class);
	private static final long serialVersionUID = 1L;
	private Part arquivo;
	private FilialRepository filialDao;
	private int contador;
	
	public ArquivoBean(){
		this.filialDao = new FilialRepository();
	}

	public String importa() {
		setContador(0);
		try {
			InputStream dataInputStream = arquivo.getInputStream();
			filialDao.apagaDados();
			try {
				String line;
				String[] arrayS = new String[3];
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(dataInputStream));
				while ((line = bufferedReader.readLine()) != null) {

					if (!line.substring(0, 6).equals("Filial")) {

						arrayS = line.split("\t");
						String temp = arrayS[2];
						temp = temp.replace(".", "");
						temp = temp.replace(",", ".");
						arrayS[2] = temp;
						Filial filial = new Filial();
						filial.setFilial(arrayS[0]);
						filial.setMes(arrayS[1]);
						if (arrayS[2] != null && !arrayS[2].equals("")) {
							filial.setValor(Double.valueOf(arrayS[2]));
						}

						filialDao.persistir(filial);
						setContador(getContador() + 1);
					}
				}
			} catch (IOException e) {
				log.error("Erro durante processamento de arquivo: " + e);
				return "Erro";
			}

		} catch (IOException e) {
			log.error("Erro durante abertura de arquivo: " + e);
			return "Erro";
		}
		
		
		return "Sucesso";
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

}
