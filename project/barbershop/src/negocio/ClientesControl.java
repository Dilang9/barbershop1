package negocio;

import Datos.clientesDAO;
import entidades.clientes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClientesControl {
    private final clientesDAO DATOS;
    private clientes obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public ClientesControl() {
        this.DATOS = new clientesDAO();
        this.obj = new clientes(0, "", "", "", "", true, "");
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<clientes> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));

        String[] titulos = {"ID", "Nombre", "Apellido", "Teléfono", "Email", "DNI", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[7];

        this.registrosMostrados = 0;
        for (clientes item : lista) {
            estado = item.isActivo() ? "Activo" : "Inactivo";
            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getApellido();
            registro[3] = item.getTelefono();
            registro[4] = item.getEmail();
            registro[5] = item.getDni();
            registro[6] = estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(String nombre, String apellido, String telefono, String email, String dni) {
        if (DATOS.existe(dni)) {
            return "El cliente ya existe.";
        } else {
            obj = new clientes(0, apellido, telefono, email, dni, true, nombre);
            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro.";
            }
        }
    }

    public String actualizar(int id, String nombre, String apellido, String telefono, String email, String dni, String dniAnt) {
        if (dni.equals(dniAnt)) {
            obj = new clientes(id, apellido, telefono, email, dni, true, nombre);
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualización.";
            }
        } else {
            if (DATOS.existe(dni)) {
                return "El cliente ya existe.";
            } else {
                obj = new clientes(id, apellido, telefono, email, dni, true, nombre);
                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "Error en la actualización.";
                }
            }
        }
    }

    public String desactivar(int id) {
        if (DATOS.desactivar(id)) {
            return "OK";
        } else {
            return "No se puede desactivar el cliente.";
        }
    }

    public String activar(int id) {
        if (DATOS.activar(id)) {
            return "OK";
        } else {
            return "No se puede activar el cliente.";
        }
    }

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
}
