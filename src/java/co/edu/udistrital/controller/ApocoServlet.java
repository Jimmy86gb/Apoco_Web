package co.edu.udistrital.controller;

import co.edu.udistrital.model.usecases.ApocoDTO;
import co.edu.udistrital.model.usecases.ApocoUseCase;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet principal que funciona como el controlador de la pagina web. Recibe
 * las peticiones del formulario, ejecuta los ordenamientos y devuelve los
 * datos.
 *
 * @author Jimmy86gb
 */
@WebServlet(name = "ApocoServlet", urlPatterns = {"/ApocoServlet"})
public class ApocoServlet extends HttpServlet {

    // Instancia única del Caso de Uso
    private final ApocoUseCase useCase = new ApocoUseCase();

    /**
     * Metodo que procesa la peticion POST enviada desde el formulario
     * index.jsp. Se encarga de leer la configuracion, ejecutar uno o todos los
     * algoritmos y preparar la informacion que se va a pintar en la tabla de
     * resultados.
     *
     * @param request la peticion HTTP que contiene lo que el usuario digito
     * @param response la respuesta HTTP que mandaremos de vuelta al navegador
     * @throws ServletException si ocurre un error interno manejando el servlet
     * @throws IOException si hay un error de entrada o salida de datos
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. RECIBIR los datos raw de la petición
        String sizeParam = request.getParameter("size");
        String algorithmParam = request.getParameter("algorithm");

        // 2. DELEGAR la lógica al caso de uso
        ApocoDTO resultado = useCase.processPetition(sizeParam, algorithmParam);

        // 3. RETORNAR los resultados a la vista
        request.setAttribute("iteraciones", resultado.getIterations());
        request.setAttribute("listaDesordenada", resultado.getUnorderedList());
        request.setAttribute("listaOrdenada", resultado.getOrderedList());
        request.setAttribute("currentSize", resultado.getUsedSize());
        request.setAttribute("currentAlgo", algorithmParam);

        // Disparador para la funcionalidad de PDF en el navegador
        request.setAttribute("organizacionCompleta", true);

        // 4. REDIRIGIR al JSP
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
