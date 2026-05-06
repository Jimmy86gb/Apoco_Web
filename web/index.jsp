<%@page import="co.edu.udistrital.model.entities.Politician"%>
<%@page import="co.edu.udistrital.model.structures.Node"%>
<%@page import="co.edu.udistrital.model.structures.SimpleList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Recuperar el arreglo de iteraciones (si es la primera vez, crea uno 
    // de ceros)
    int[] iteraciones = (int[]) request.getAttribute("iteraciones");
    if (iteraciones == null) {
        iteraciones = new int[8];
    }

    SimpleList<Politician> listaDesordenada = (SimpleList<Politician>) request.getAttribute("listaDesordenada");
    SimpleList<Politician> listaOrdenada = (SimpleList<Politician>) request.getAttribute("listaOrdenada");
    String currentSize = request.getAttribute("currentSize") != null
            ? request.getAttribute("currentSize").toString() : "50";
    String currentAlgo = request.getAttribute("currentAlgo") != null
            ? (String) request.getAttribute("currentAlgo") : "all";
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="JS/apoco.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Analisis de Ordenamiento APOCO</title>
        <link rel="stylesheet" type="text/css" href="CSS/style.css">
    </head>
    <body>

        <div class="controles">
            <a href="index.jsp" style="text-decoration: none">
                <div class="grupo">
                    <label>&nbsp;</label>
                    <button type="submit" style="background-color: #17a2b8;">Redirigir a politicos</button>
                </div>
            </a>
            <a href="hampones.jsp" style="text-decoration: none">
                <div class="grupo">
                    <label>&nbsp;</label>
                    <button type="submit">Redirigir a hampones</button>
                </div>
            </a>
        </div>

        <% if (request.getAttribute("organizacionCompleta") != null) { %>
        <script>imprimirResultado()</script>
        <% }%>
        <!-- Formulario de controles -->
        <form action="ApocoServlet" method="POST" class="controles">
            <div class="grupo">
                <label>Número de corruptos:</label>
                <input type="number" name="size" value="<%= currentSize%>" 
                       min="1" required>
            </div>

            <div class="grupo">
                <label>Algoritmo a evaluar:</label>
                <select name="algorithm">
                        <option value="all" <%= currentAlgo.equals("all")
                        ? "selected" : ""%>>Todos (Comparativa)</option>
                            <option value="0" <%= currentAlgo.equals("0")
                        ? "selected" : ""%>>Insertion Sort</option>
                            <option value="1" <%= currentAlgo.equals("1")
                        ? "selected" : ""%>>Selection Sort</option>
                            <option value="2" <%= currentAlgo.equals("2")
                        ? "selected" : ""%>>Bubble Sort</option>
                            <option value="3" <%= currentAlgo.equals("3")
                        ? "selected" : ""%>>Cocktail Sort</option>
                            <option value="4" <%= currentAlgo.equals("4")
                        ? "selected" : ""%>>Comb Sort</option>
                            <option value="5" <%= currentAlgo.equals("5")
                        ? "selected" : ""%>>Shell Sort</option>
                            <option value="6" <%= currentAlgo.equals("6")
                        ? "selected" : ""%>>Merge Sort</option>
                            <option value="7" <%= currentAlgo.equals("7")
                        ? "selected" : ""%>>Quick Sort</option>
                </select>
            </div>

            <div class="grupo">
                <label>&nbsp;</label>
                <button type="submit">Generar y Ordenar</button>
            </div>
        </form>

        <div class="tabla-container">
            <table>
                <tr>
                    <th>Algoritmo de Ordenamiento</th>
                    <th>Número de Iteraciones</th>
                </tr>
                <tr>
                    <td class="nombre-algo">Insertion Sort</td>
                    <td><%= iteraciones[0]%></td>
                </tr>
                <tr class="fila-gris">
                    <td class="nombre-algo">Selection Sort</td>
                    <td><%= iteraciones[1]%></td>
                </tr>
                <tr>
                    <td class="nombre-algo">Bubble Sort</td>
                    <td><%= iteraciones[2]%></td>
                </tr>
                <tr class="fila-gris">
                    <td class="nombre-algo">Cocktail Sort</td>
                    <td><%= iteraciones[3]%></td>
                </tr>
                <tr>
                    <td class="nombre-algo">Comb Sort</td>
                    <td><%= iteraciones[4]%></td>
                </tr>
                <tr class="fila-gris">
                    <td class="nombre-algo">Shell Sort</td>
                    <td><%= iteraciones[5]%></td>
                </tr>
                <tr>
                    <td class="nombre-algo">Merge Sort</td>
                    <td><%= iteraciones[6]%></td>
                </tr>
                <tr class="fila-gris">
                    <td class="nombre-algo">Quick Sort</td>
                    <td><%= iteraciones[7]%></td>
                </tr>
            </table>

            <!-- Boton para ver la lista -->
            <% if (listaOrdenada != null) { %>
            <button type="button" class="btn-ver" 
                    onclick="mostrarLista()">Ver Lista Ordenada Resultante</button>
            <% } %>
        </div>

        <% if (listaDesordenada != null) { %>
        <div id="miLista" class="seccion-lista">
            <h3 style="text-align:center;">Lista de Políticos Desordenada</h3>
            <table style="width: 100%;">
                <tr><th>Nombre</th><th>Dinero a Robar</th></tr>
                        <%
                            Node<Politician> actual = listaDesordenada.getHead();
                            while (actual != null) {
                                Politician p = actual.getData();
                        %>
                <tr>
                    <td><%= p.getName()%></td>
                    <td>$ <%= String.format("%,.2f", p.getMoneyToSteal())%></td>
                </tr>
                <%
                        actual = actual.getNext();
                    }
                %>
            </table>
        </div>
        <% }%>
        <!-- Div oculto que tiene la lista final iterada -->
        <% if (listaOrdenada != null) { %>
        <div id="miLista2" class="seccion-lista">
            <h3 style="text-align:center;">Lista de Políticos Ordenada</h3>
            <table style="width: 100%;">
                <tr><th>Nombre</th><th>Dinero a Robar</th></tr>
                        <%
                            Node<Politician> actual = listaOrdenada.getHead();
                            while (actual != null) {
                                Politician p = actual.getData();
                        %>
                <tr>
                    <td><%= p.getName()%></td>
                    <td>$ <%= String.format("%,.2f", p.getMoneyToSteal())%></td>
                </tr>
                <%
                        actual = actual.getNext();
                    }
                %>
            </table>
        </div>
        <% }%>
    </body>
</html>