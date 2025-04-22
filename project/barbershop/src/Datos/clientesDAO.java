package Datos;

import Crud.crud;
import Database.conexion;
import entidades.clientes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class clientesDAO implements crud<clientes> {

    private final conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public clientesDAO() {
        CON = conexion.getInstancia();
    }

    public boolean insertar(clientes obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                "INSERT INTO citas (apellido, telefono, id, email, activo, Nombre) VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, obj.getapellido());
            ps.setInt(2, obj.gettelefono());
            ps.setInt(3, obj.getId());
            ps.setInt(4, obj.getemail());
            ps.setBoolean(5, obj.isActivo());
            ps.setString(6, obj.getNombre());
            ps.setString(7, obj.getdni());
            
            

            if (ps.executeUpdate() > 0) {
                resp = true;
            }

            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    // Métodos vacíos para completar la interfaz
    
    public java.util.List<clientes> listar(String texto) {
        throw new UnsupportedOperationException("No implementado aun.");
    }

    
    public boolean actualizar(clientes obj) {
        throw new UnsupportedOperationException("No implementado aun.");
    }

    
    public boolean desactivar(int id) {
        throw new UnsupportedOperationException("No implementado aun.");
    }

    
    public boolean activar(int id) {
        throw new UnsupportedOperationException("No implementado aun.");
    }

    
    public int total() {
        throw new UnsupportedOperationException("No implementado aun.");
    }

    
    public boolean existe(String texto) {
        throw new UnsupportedOperationException("No implementado aun.");
    }
}
