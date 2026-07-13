package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeguroDao;
import dominio.Seguro;
import dominio.TipoSeguro;


@WebServlet("/ServletSeguro")
public class ServletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ServletSeguro() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("menu");
		SeguroDao sdao = new SeguroDao();
		
		if(opcion != null) {
			switch(opcion) {
				case "agregar":
					int proximoId = sdao.obtenerProximoId();
				    request.setAttribute("proximoId", proximoId);
				    
				    ArrayList<TipoSeguro> listaTiposAgregar = sdao.obtenerTipos(); 
				    request.setAttribute("listaT", listaTiposAgregar); 

				    RequestDispatcher rdAgregar = request.getRequestDispatcher("/AgregarSeguro.jsp");
				    rdAgregar.forward(request, response);
				    break;
					
				case "listar":
				  
				    ArrayList<TipoSeguro> listaTipos = sdao.obtenerTipos(); 
				    request.setAttribute("listaT", listaTipos);

				    ArrayList<Seguro> listaSeguros;
				    if (request.getParameter("btnFiltrar") != null) {
				        int id = Integer.parseInt(request.getParameter("ddlTipo"));
				        listaSeguros = sdao.obtenerSegurosFiltrados(id);
				    } else {
				        
				        listaSeguros = sdao.obtenerSeguros();
				    }
				    
				    request.setAttribute("listaS", listaSeguros);
				    request.getRequestDispatcher("/ListarSeguros.jsp").forward(request, response);
				    break;
				    
				case "insertar":
					Seguro s= new Seguro();
					s.setDescripcion(request.getParameter("txtDescripcion"));
					
					TipoSeguro ts= new TipoSeguro();
					ts.setIdTipo(Integer.parseInt(request.getParameter("tipoSeguro")));
					s.setTipo(ts);
					
				
					String costo = request.getParameter("txtCostoContratacion");
					String costoMax = request.getParameter("txtCostoMaximo");

					if (costo == null || costo.isEmpty() || costoMax == null || costoMax.isEmpty()) {
					    throw new RuntimeException("Campos numéricos vacíos");
					}

					s.setCostoContratacion(Double.parseDouble(costo));
					s.setCostoAsegurado(Double.parseDouble(costoMax));
	
			        
			        int filas = sdao.insertarSeguro(s);
			        System.out.println("Filas insertadas: " + filas);
			        
			        
	
			        response.sendRedirect("ServletSeguro?menu=listar");
			        break;
			        
				case "eliminar":
					
					int idSeg = Integer.parseInt(request.getParameter("idSeguro"));
			        SeguroDao seguroDao = new SeguroDao();
			        seguroDao.eliminarSeguro(idSeg);
			        
			       
			        ArrayList<Seguro> listaSeguros1;
			        listaSeguros1 = sdao.obtenerSeguros();
			        request.setAttribute("listaS", listaSeguros1);
			        
			        RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
			        rd.forward(request, response);
					break;
					
					
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doGet(request, response);
		
		
	}

}
