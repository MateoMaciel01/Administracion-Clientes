package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Seguro;
import dominio.TipoSeguro;

public class SeguroDao {
    private String host = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String pass = "root";
    private String dbName = "SegurosGroup";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(host + dbName, user, pass);
    }

    public ArrayList<Seguro> obtenerSeguros() {
        return obtenerSegurosFiltrados(0);
    }

    public ArrayList<Seguro> obtenerSegurosFiltrados(int idTipo) {
        ArrayList<Seguro> lista = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            
            String query = "SELECT s.idSeguro, s.descripcion, s.idTipo, s.costoContratacion, " +
                           "s.costoAsegurado, t.descripcion as descTipo " +
                           "FROM seguros s " +
                           "INNER JOIN tipoSeguros t ON s.idTipo = t.idTipo";
   
            if (idTipo > 0) {
                query += " WHERE s.idTipo = " + idTipo;
            }

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Seguro s = new Seguro();
                s.setIdSeguro(rs.getInt("idSeguro"));
                s.setDescripcion(rs.getString("descripcion"));
                s.setCostoContratacion(rs.getDouble("costoContratacion"));
                s.setCostoAsegurado(rs.getDouble("costoAsegurado"));
              
                TipoSeguro ts = new TipoSeguro();
                ts.setIdTipo(rs.getInt("idTipo"));
                ts.setDescripcion(rs.getString("descTipo"));
                s.setTipo(ts);

                lista.add(s);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<TipoSeguro> obtenerTipos() {
        ArrayList<TipoSeguro> lista = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT idTipo, descripcion FROM tipoSeguros");
            
            while (rs.next()) {
                TipoSeguro ts = new TipoSeguro();
                ts.setIdTipo(rs.getInt("idTipo"));
                ts.setDescripcion(rs.getString("descripcion"));
                lista.add(ts);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int obtenerProximoId() {
        int id = 1;
        Connection conn = null;

        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(idSeguro) AS maxId FROM seguros");

            if (rs.next()) {
                id = rs.getInt("maxId") + 1;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public int insertarSeguro(Seguro s) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver cargado OK");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver NO cargado");
            e.printStackTrace();
        }

        int filas = 0;
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            
            String query = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES ('"
                    + s.getDescripcion() + "', "
                    + s.getTipo().getIdTipo() + " ,"
                    + s.getCostoContratacion() + ", "
                    + s.getCostoAsegurado() + ")";
            filas = st.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return filas;
    }

    public int eliminarSeguro(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver cargado OK");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver NO cargado");
            e.printStackTrace();
        }

        int filas = 0;
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            String query = "DELETE from seguros where idSeguro=" + id;
            filas = st.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return filas;
    }
}