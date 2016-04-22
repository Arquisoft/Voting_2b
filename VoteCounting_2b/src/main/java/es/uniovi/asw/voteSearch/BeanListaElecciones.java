package es.uniovi.asw.voteSearch;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.ClosedList;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.model.OpenList;
import es.uniovi.asw.dbManagement.model.Referendum;
import es.uniovi.asw.dbManagement.persistence.Repository;


@Component("BeanListaElecciones")
@Scope("request")
public class BeanListaElecciones
{
	public List<Election> elecciones;
	public List<Referendum> referendums;
	public List<OpenList> listasAbiertas;
	public List<ClosedList> listasCerradas;
	public List<Election> listaSinRecuento;
	
	
	public List<Election> getListaSinRecuento()
	{
		return listaSinRecuento;
	}
	
	
	public void setListaSinRecuento(List<Election> listaSinRecuento)
	{
		this.listaSinRecuento = listaSinRecuento;
	}
	
	
	@PostConstruct
	public void init()
	{
		this.elecciones = (List<Election>) Repository.electionR.findAll();
		
		referendums = new LinkedList<>();
		listasCerradas= new LinkedList<>();
		listasAbiertas= new LinkedList<>();
		listaSinRecuento= new LinkedList<>();
		
		for(Election e:elecciones)
		{
			if(e instanceof Referendum)
			{
				referendums.add((Referendum) e);
			}
			
			if(e instanceof ClosedList)
			{
				listasCerradas.add((ClosedList) e);
			}
			
			if(e instanceof OpenList)
				listasAbiertas.add((OpenList) e);
			
			if(!e.readyToRecount())
				listaSinRecuento.add(e);
		}
		
	}
	
	
	public List<Election> getElecciones()
	{
		return elecciones;
	}
	
	public void setElecciones(List<Election> elecciones)
	{
		this.elecciones = elecciones;
	}
	
	public List<Referendum> getReferendums()
	{
		return referendums;
	}
	
	public void setReferendums(List<Referendum> referendums)
	{
		this.referendums = referendums;
	}
	
	public List<OpenList> getListasAbiertas()
	{
		return listasAbiertas;
	}
	
	public void setListasAbiertas(List<OpenList> listasAbiertas)
	{
		this.listasAbiertas = listasAbiertas;
	}
	
	public List<ClosedList> getListasCerradas()
	{
		return listasCerradas;
	}
	
	public void setListasCerradas(List<ClosedList> listasCerradas)
	{
		this.listasCerradas = listasCerradas;
	}
}