package br.com.headway.etl.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import br.com.headway.etl.model.Filial;
import br.com.headway.etl.model.FilialPivot;
import br.com.headway.etl.model.FilialPorMes;

public class FilialRepository implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(FilialRepository.class);
	protected EntityManager manager = JPAUtil.getEntityManager();

	public Object porId(Long id){
		return manager.find(Filial.class, id);
	}
	
	public void persistir(Filial f){
		manager.getTransaction().begin();
		manager.persist(f);
		manager.getTransaction().commit();
	}
	
	public void remove(Filial f) {
		manager.getTransaction().begin();
		manager.remove(f);
		manager.getTransaction().commit();
	}
	
	public void altera(Filial f) {
		manager.getTransaction().begin();
		manager.merge(f);
		manager.getTransaction().commit();
	}

	public void apagaDados(){
		manager.getTransaction().begin();
		Query query = manager.createQuery("delete from Filial");
		query.executeUpdate();
		manager.getTransaction().commit();
	}
	
	public List<FilialPorMes> getFiliaisPorMes(){
		Query query = manager.createQuery("select sum(f.valor) as valor, f.mes from Filial f group by f.mes order by f.valor desc");
		List<FilialPorMes> filiais = query.getResultList();
		return filiais;
	}

	public List<FilialPivot> getFiliaisPivot(){
		Query query = manager.createQuery(
						"select " +
						"	filial, " +
						"    sum( " +
						"    Case " +
						"		when mes = 'Janeiro' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"    as Janeiro, " +
						"    sum(Case " +
						"		when mes = 'Fevereiro' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"    as Fevereiro, " +
						"    sum(     " +
						"        Case " +
						"		when mes = 'Marco' " +
						"        then valor " +
						"        else null " +
						"	end) as Marco, " +
						"    	Sum(	 " +
						"	Case " +
						"		when mes = 'Abril' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"    as Abril, " +
						"    Sum(     " +
						"	Case " +
						"		when mes = 'Maio' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"    as Maio, " +
						"    Sum( " +
						"	Case " +
						"		when mes = 'Junho' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"	as Julho, " +
						"    Sum( " +
						"	Case " +
						"		when mes = 'Agosto' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"    as Agosto, " +
						"    Sum( " +
						"	Case " +
						"		when mes = 'Setembro' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"    as Setembro, " +
						"    Sum( " +
						"	Case " +
						"		when mes = 'Outubro' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"    as Outubro, " +
						"	Sum( " +
						"	Case " +
						"		when mes = 'Novembro' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"    as Novembro, " +
						"	Sum( " +
						"	Case " +
						"		when mes = 'Dezembro' " +
						"        then valor " +
						"        else null " +
						"	end) " +
						"    as Dezembro " +
						" from Filial " +
						" group by filial "
				);
		List<FilialPivot> filiais = query.getResultList();
		return filiais;
	}

	
	
	public List<Filial> getFilialMaisVendeu() {
		TypedQuery<Filial> query = manager.createNamedQuery("Filial.filialMaisVendeu", Filial.class);
		return query.getResultList();
	}

	public List<Filial> getFilialMaiorQueda() {
		//TypedQuery<Filial> query = manager.createNamedQuery("Filial.filialMaiorQueda", Filial.class);
		Query query = manager.createQuery("select f1 from Filial f1 where valor = (select min(f2.valor) from Filial f2 where f2.mes = f1.mes)");
		List<Filial> filiais = query.getResultList();				
		return filiais;
	}

	public List<Filial> getFilialMaiorCrescimento() {
		//TypedQuery<Filial> query = manager.createNamedQuery("Filial.filialMaiorCrescimento", Filial.class);
		Query query = manager.createQuery("select f1 from Filial f1 where valor = (select max(f2.valor) from Filial f2 where f2.mes = f1.mes)");
		List<Filial> filiais = query.getResultList();		
		return filiais;
	}

}
