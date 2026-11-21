package pe.edu.pucp.iweb.dao;

import pe.edu.pucp.iweb.beans.EquipoRed;
import pe.edu.pucp.iweb.dto.EquipoListadoDTO;
import pe.edu.pucp.iweb.dto.ResumenSedeDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipoDao extends DaoBase {

    public ArrayList<EquipoListadoDTO> listarEquipos() {
        ArrayList<EquipoListadoDTO> lista = new ArrayList<>();
        String sql =
            "SELECT e.id_equipo, e.hostname, e.tipo, e.ip_gestion, e.estado, " +
            "       s.nombre AS sede_nombre, s.ciudad, " +
            "       TIMESTAMPDIFF(MONTH, e.fecha_alta, CURDATE()) AS meses_operativos " +
            "FROM equipos_red e " +
            "INNER JOIN sedes s ON e.id_sede = s.id_sede " +
            "ORDER BY e.id_equipo";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EquipoListadoDTO dto = new EquipoListadoDTO();
                dto.setId(rs.getInt("id_equipo"));
                dto.setHostname(rs.getString("hostname"));
                dto.setTipo(rs.getString("tipo"));
                dto.setIpGestion(rs.getString("ip_gestion"));
                dto.setEstado(rs.getString("estado"));
                dto.setSedeNombre(rs.getString("sede_nombre"));
                dto.setCiudad(rs.getString("ciudad"));
                dto.setMesesOperativos(rs.getInt("meses_operativos"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertarEquipo(EquipoRed equipo) {
        String sql =
            "INSERT INTO equipos_red (hostname, tipo, ip_gestion, estado, fecha_alta, id_sede) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, equipo.getHostname());
            ps.setString(2, equipo.getTipo());
            ps.setString(3, equipo.getIpGestion());
            ps.setString(4, equipo.getEstado());
            ps.setDate(5, Date.valueOf(equipo.getFechaAlta())); // yyyy-MM-dd
            ps.setInt(6, equipo.getIdSede());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ResumenSedeDTO> resumenPorSede() {
        ArrayList<ResumenSedeDTO> lista = new ArrayList<>();
        String sql =
            "SELECT s.nombre AS sede_nombre, " +
            "       COUNT(e.id_equipo) AS total_equipos, " +
            "       SUM(CASE WHEN e.estado = 'operativo' THEN 1 ELSE 0 END) AS operativos, " +
            "       SUM(CASE WHEN e.estado IN ('en mantenimiento','fuera de servicio') THEN 1 ELSE 0 END) AS caidos " +
            "FROM sedes s " +
            "LEFT JOIN equipos_red e ON s.id_sede = e.id_sede " +
            "GROUP BY s.id_sede, s.nombre " +
            "ORDER BY s.id_sede";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ResumenSedeDTO dto = new ResumenSedeDTO();
                dto.setSedeNombre(rs.getString("sede_nombre"));
                dto.setTotalEquipos(rs.getInt("total_equipos"));
                dto.setOperativos(rs.getInt("operativos"));
                dto.setCaidos(rs.getInt("caidos"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
