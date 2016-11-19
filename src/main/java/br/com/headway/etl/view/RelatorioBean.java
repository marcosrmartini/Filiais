package br.com.headway.etl.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.headway.etl.model.Filial;
import br.com.headway.etl.model.FilialPorMes;
import br.com.headway.etl.repository.FilialRepository;

@ManagedBean
@ViewScoped
public class RelatorioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<FilialPorMes> listaFilialPorMes;
	private List<Filial> listaFilialMaisVendeu;
	private List<Filial> listaFilialMaiorCrescimento;
	private List<Filial> listaFilialMaiorQueda;
	private FilialRepository filialDao;
	
	public RelatorioBean(){
		this.filialDao = new FilialRepository();
	}
	
	public void relatorioFilialPorMes(){
		this.listaFilialPorMes = this.filialDao.getFiliaisPorMes();
	}
	
	public void relatorioFilialMaisVendeu(){
		this.listaFilialMaisVendeu = this.filialDao.getFilialMaisVendeu();
	}

	public void relatorioFilialMaiorQueda(){
		this.listaFilialMaiorQueda = this.filialDao.getFilialMaiorQueda();
	}
	
	public void relatorioFilialMaiorCrescimento(){
		this.listaFilialMaiorCrescimento = this.filialDao.getFilialMaiorCrescimento();
	}

	
	
	public List<FilialPorMes> getListaFilialPorMes() {
		return listaFilialPorMes;
	}

	public void setListaFilialPorMes(List<FilialPorMes> listaFilialPorMes) {
		this.listaFilialPorMes = listaFilialPorMes;
	}

	public FilialRepository getFilialDao() {
		return filialDao;
	}

	public void setFilialDao(FilialRepository filialDao) {
		this.filialDao = filialDao;
	}

	public List<Filial> getListaFilialMaisVendeu() {
		return listaFilialMaisVendeu;
	}

	public void setListaFilialMaisVendeu(List<Filial> listaFilialMaisVendeu) {
		this.listaFilialMaisVendeu = listaFilialMaisVendeu;
	}

	public List<Filial> getListaFilialMaiorCrescimento() {
		return listaFilialMaiorCrescimento;
	}

	public void setListaFilialMaiorCrescimento(
			List<Filial> listaFilialMaiorCrescimento) {
		this.listaFilialMaiorCrescimento = listaFilialMaiorCrescimento;
	}

	public List<Filial> getListaFilialMaiorQueda() {
		return listaFilialMaiorQueda;
	}

	public void setListaFilialMaiorQueda(List<Filial> listaFilialMaiorQueda) {
		this.listaFilialMaiorQueda = listaFilialMaiorQueda;
	}
	
	
}
