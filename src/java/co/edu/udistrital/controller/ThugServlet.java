package co.edu.udistrital.controller;

import co.edu.udistrital.model.usecases.ThugDTO;
import co.edu.udistrital.model.usecases.ThugUserCase;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet principal que funciona como el controlador del sistema de informacion
 * web. Recibe las peticiones del formulario, ejecuta los ordenamientos y
 * devuelve los datos.
 *
 * @author Juan David Diaz Perez
 */
@WebServlet(name = "ThugServlet", urlPatterns = {"/ThugServlet"})
public class ThugServlet extends HttpServlet {

    // Instanciacion del caso de uso
    private final ThugUserCase useCase = new ThugUserCase();
    
    /**
     * Metodo que procesa la peticion POST enviada desde el formulario
     * de hampones.jsp. Se encarga de leer la configuracion, ejecutar uno o todos los
     * algoritmos y preparar la informacion que se va a pintar en la tabla de
     * resultados.
     *
     * @param request la peticion HTTP que contiene lo que el usuario digito
     * @param response la respuesta HTTP que mandaremos de vuelta al navegador
     * @throws ServletException si ocurre un error interno manejando el servlet
     * @throws IOException si hay un error de entrada o salida de datos
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Recibir los datos del formulario
        String rowParam = request.getParameter("row");
        String columnParam = request.getParameter("column");
        String sortParam = request.getParameter("algorithm");

        // Implementar el caso de uso
        ThugDTO resultado = useCase.processRequest(rowParam, columnParam, sortParam);

        // Retornar las coas a la vista
        request.setAttribute("iteraciones", resultado.getIterations());
        request.setAttribute("listaDesordenada", resultado.getUnorderedList());
        request.setAttribute("listaOrdenada", resultado.getOrderedList());
        request.setAttribute("currentRow", resultado.getRows());
        request.setAttribute("currentColumn", resultado.getColumns());
        request.setAttribute("currentAlgo", sortParam);

        // Respuesta mostrada en forma de pdf en el navegador
        request.setAttribute("organizacionCompleta", true);

        // Redoreccion
        request.getRequestDispatcher("hampones.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
