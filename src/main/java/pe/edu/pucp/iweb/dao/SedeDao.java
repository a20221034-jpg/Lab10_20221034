package pe.edu.pucp.iweb.dao;

import pe.edu.pucp.iweb.beans.Sede;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SedeDao extends DaoBase {

    public ArrayList<Sede> listarSedes() {
        ArrayList<Sede> lista = new ArrayList<>();
        String sql = "SELECT id_sede, nombre, ciudad FROM sedes ORDER BY id_sede";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sede s = new Sede();
                s.setIdSede(rs.getInt("id_sede"));
                s.setNombre(rs.getString("nombre"));
                s.setCiudad(rs.getString("ciudad"));
                lista.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}