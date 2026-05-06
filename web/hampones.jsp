<%@page import="co.edu.udistrital.model.entities.Thug"%>
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

    SimpleList<Thug> listaDesordenada = (SimpleList<Thug>) request.getAttribute("listaDesordenada");
    SimpleList<Thug> listaOrdenada = (SimpleList<Thug>) request.getAttribute("listaOrdenada");
    String currentRow = request.getAttribute("currentRow") != null
            ? request.getAttribute("currentRow").toString() : "5";
    String curretColumn = request.getAttribute("currentColumn") != null
            ? request.getAttribute("currentColumn").toString() : "5";
    String currentAlgo = request.getAttribute("currentAlgo") != null
            ? (String) request.getAttribute("currentAlgo") : "all";
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="JS/apoco.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Analisis de Ordenamiento APOCO - Hampones</title>
        <link rel="stylesheet" type="text/css" href="CSS/hampones.css">
    </head>
    <body>

        <div class="controles">
            <a href="index.jsp" style="text-decoration: none;">
                <div class="grupo">
                    <label>&nbsp;</label>
                    <button type="button">Redirigir a politicos</button>
                </div>
            </a>
            <a href="hampones.jsp" style="text-decoration: none;">
                <div class="grupo">
                    <label>&nbsp;</label>
                    <button type="button" style="background-color: #17a2b8;">Redirigir a hampones</button>
                </div>
            </a>
        </div>
        

        <% if (request.getAttribute("organizacionCompleta") != null) { %>
        <script>imprimirResultado()</script>
        <% }%>
        <!-- Formulario de controles -->
        <form action="ThugServlet" method="POST" class="controles">
            <div class="grupo">
                <label>Dimensiones del Auditorio:</label>
                <div style="display: flex; gap: 10px;">
                    <input type="number" name="row" value="<%= currentRow%>" 
                           min="1" placeholder="Filas" required style="width: 80px;">
                    <input type="number" name="column" value="<%= curretColumn%>" 
                           min="1" placeholder="Columnas" required style="width: 80px;">
                </div>
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

            <% if (listaOrdenada != null) { %>
            <button type="button" class="btn-ver" 
                    onclick="mostrarLista()">Ver Auditorio de Hampones</button>
            <% } %>
        </div>

        <% if (listaDesordenada != null) { %>
        <div id="miLista" class="seccion-matriz">
            <h3 style="text-align:center; color: #333;">Auditorio de Hampones Desordenado (Matriz <%= currentRow %>x<%= curretColumn %>)</h3>
            <table class="tabla-matriz">
                <tr>
                    <%
                        Node<Thug> actual = listaDesordenada.getHead();
                        // Parsear el limite de columnas
                        int colsLimite = Integer.parseInt(curretColumn);
                        int contador = 0;

                        while (actual != null) {
                            Thug t = actual.getData();
                    %>
                    <td class="celda-hampon">
                        <span class="h-nombre"><%= t.getName() %></span>
                        <span class="h-edad">Edad: <%= t.getAge() %> años</span>
                        <span class="h-dinero">💰 $<%= String.format("%,.2f", t.getStolenMoney()) %></span>
                    </td>
                    <%
                            contador++;
                            // Si se alcanza el límite de columnas y aún hay datos, se pasa a la siguiente fila
                            if (contador % colsLimite == 0 && actual.getNext() != null) {
                                out.print("</tr><tr>");
                            }
                            actual = actual.getNext();
                        }
                    %>
                </tr>
            </table>
        </div>
        <% }%>
        
        <% if (listaOrdenada != null) { %>
        <div id="miLista2" class="seccion-matriz">
            <h3 style="text-align:center; color: #333;">Auditorio de Hampones Ordenado (Matriz <%= currentRow %>x<%= curretColumn %>)</h3>
            <table class="tabla-matriz">
                <tr>
                    <%
                        Node<Thug> actual = listaOrdenada.getHead();
                        // Parsear el limite de columnas
                        int colsLimite = Integer.parseInt(curretColumn);
                        int contador = 0;

                        while (actual != null) {
                            Thug t = actual.getData();
                    %>
                    <td class="celda-hampon">
                        <span class="h-nombre"><%= t.getName() %></span>
                        <span class="h-edad">Edad: <%= t.getAge() %> años</span>
                        <span class="h-dinero">💰 $<%= String.format("%,.2f", t.getStolenMoney()) %></span>
                    </td>
                    <%
                            contador++;
                            // Si se alcanza el límite de columnas y aún hay datos, se pasa a la siguiente fila
                            if (contador % colsLimite == 0 && actual.getNext() != null) {
                                out.print("</tr><tr>");
                            }
                            actual = actual.getNext();
                        }
                    %>
                </tr>
            </table>
        </div>
        <% }%>
    </body>
</html>