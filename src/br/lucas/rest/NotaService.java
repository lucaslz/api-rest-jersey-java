package br.lucas.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.lucas.dao.NotaDAO;
import br.lucas.entidade.Nota;

@Path("/nota")
public class NotaService {
	
	@SuppressWarnings("unused")
	private static final String CHARSET_UTF8 = ";charset=utf-0";
	private NotaDAO notaDao;
	
	@PostConstruct
	private void init() {
		this.setNotaDao(new NotaDAO());
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Nota> lstNota() {
		List<Nota> lista = null;
		try {
			lista = this.getNotaDao().sltNotas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String insNota(Nota nota) {
		String msg = "";
		
		System.out.println(nota.getTitulo());
		
		try {
			int idGerado = notaDao.insNota(nota);
			msg = String.valueOf(idGerado);
		} catch (Exception e) {
			msg = "Erro ao inserir uma nota no sistema";
		}
		
		return msg;
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Nota getNotaPorId(@PathParam("id") int idNota) {
		NotaDAO dao = new NotaDAO();
		Nota nota = null;
		
		try {
			nota = dao.getNotaPorId(idNota);
			return nota;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nota; 
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String updNotaPorId(Nota nota, @PathParam("id") int idNota) {
		NotaDAO dao = new NotaDAO();
		
		try {
			dao.updNota(nota, idNota);
			return "Nota alterada com sucesso.";
		} catch (Exception e) {
			return "Erro ao alterar uma nota.";
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String dlNotaPorId(@PathParam("id") int idNota) {
		NotaDAO dao = new NotaDAO();
		
		try {
			dao.dlNota(idNota);
			return "Nota deletada com sucesso.";
		} catch (Exception e) {
			return "Erro ao deletar uma nota.";
		}
	}
	
	public NotaDAO getNotaDao() {
		return notaDao;
	}

	public void setNotaDao(NotaDAO notaDao) {
		this.notaDao = notaDao;
	}
}
