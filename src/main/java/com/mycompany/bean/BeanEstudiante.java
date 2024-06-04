package com.mycompany.bean;

import com.mycompany.entidades.Estudiante;
import com.mycompany.jpa.EstudianteJpaController;
import com.mycompany.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanEstudiante {
    private EntityManagerFactory emf;
    private EstudianteJpaController jpaEstudiante;

    public BeanEstudiante() {
        emf = Persistence.createEntityManagerFactory("UPpractica5");
        jpaEstudiante = new EstudianteJpaController(emf);
    }
    
    
    public List<Estudiante> listarTodos(){
        return jpaEstudiante.findEstudianteEntities();
    }
    
    public void insertar(Estudiante c){
        jpaEstudiante.create(c);
    }
    
    public void eliminar(Integer id){
        try {
            jpaEstudiante.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editar(Estudiante e){
        try {
            jpaEstudiante.edit(e);
        } catch (Exception ex) {
            Logger.getLogger(BeanEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Estudiante buscar(Integer id){
        Estudiante est = new Estudiante();
        
        est = jpaEstudiante.findEstudiante(id);
        
        return est;
    }
    
}
