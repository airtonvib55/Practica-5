package com.mycompany.controller;

import com.google.protobuf.TextFormat.ParseException;
import com.mycompany.bean.BeanEstudiante;
import com.mycompany.entidades.Estudiante;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Estamos en el SERVLET");

        Estudiante e = new Estudiante();
        int id;
        List<Estudiante> lista_estudiantes = null;

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":

                request.setAttribute("lista_productos", lista_estudiantes);
                request.setAttribute("lista_clientes", lista_estudiantes);

                request.setAttribute("estudiantes", e);
                request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                BeanEstudiante dao = new BeanEstudiante();
                e = dao.buscar(id);

                request.setAttribute("estudiante", e);
                request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                eliminar(id);
                response.sendRedirect("MainController");
                break;
            case "view":
                List<Estudiante> lista_est = mostrar();

                request.setAttribute("estudiantes", lista_est);
                request.getRequestDispatcher("estudiante.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        
        if(request.getParameter("id")==""){
            id = 0;
        }else{
            id = Integer.parseInt(request.getParameter("id"));
        }
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String fechaN = request.getParameter("fechaN");

        Estudiante est = new Estudiante();

        est.setId(id);
        est.setNombre(nombre);
        est.setApellidos(apellidos);
        est.setEmail(email);

        java.util.Date utilDate = convierteFecha(fechaN); 
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        est.setFechaNacimiento(sqlDate);

        if (id == 0) {

            try {
                nuevo(est);
            } catch (Exception ex) {
                System.out.println("Error al insertar " + ex.getMessage());
            }
        } else {
            try {
                editar(est);
            } catch (Exception ex) {
                System.out.println("Error al editar " + ex.getMessage());
            }
        }
        response.sendRedirect("MainController");
    }

    private void nuevo(Estudiante e) {
        BeanEstudiante dao = new BeanEstudiante();

        dao.insertar(e);
    }

    private void editar(Estudiante e) {
        BeanEstudiante dao = new BeanEstudiante();
        dao.editar(e);
    }

    private void eliminar(int id) {
        BeanEstudiante dao = new BeanEstudiante();

        dao.eliminar(id);
    }

    private List<Estudiante> mostrar() {
        BeanEstudiante dao = new BeanEstudiante();
        List<Estudiante> lista = dao.listarTodos();

        for (Estudiante item : lista) {

            System.out.println(item.getEmail());
        }

        return lista;
    }

    public Date convierteFecha(String fecha) {
        Date fechaBD = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaTMP;
        try {

            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());

        } catch (java.text.ParseException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaBD;
    }

}
