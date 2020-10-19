package personas;

import utils.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAO implements IDAO<Persona, Integer>{

    @Override
    public Persona insert(Persona entidad) {
        String sql = "INSERT INTO empleados(id, nombre,direccion,telefono) VALUES(" +
                entidad.getId() + ",'" +
                entidad.getNombre() + "','" +
                entidad.getDireccion() + "','" +
                entidad.getTelefono() + "') ";
        return ConnectionDB.getInstance().execute(sql) ? entidad : null;
    }

    @Override
    public Persona update(Persona entidad) {
        String sql = "UPDATE empleados SET "
                + "nombre = '" + entidad.getNombre() + "', "
                + "direccion = '" + entidad.getDireccion() + "', "
                + "telefono = '" + entidad.getTelefono() + "' WHERE id = " + entidad.getId();
        return ConnectionDB.getInstance().execute(sql) ? entidad : null;
    }

    @Override
    public boolean delete(Integer id) {
        return ConnectionDB.getInstance().execute("DELETE FROM empleados WHERE id = '" + id + "'");
    }

    @Override
    public Persona find(Integer id) {
        Persona persona = null;
        try{
            ResultSet rs = ConnectionDB.getInstance().executeQuery("SELECT * FROM empleados WHERE id = '" + id + "'");
            if(rs.next()){
                persona = new Persona(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persona;
    }

    @Override
    public List<Persona> findAll() {
        List<Persona> personas = new ArrayList<>();
        try{
            ResultSet rs = ConnectionDB.getInstance().executeQuery("SELECT * FROM empleados");
            while(rs.next()){
                personas.add(new Persona(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch(SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personas;
    }
    
}
