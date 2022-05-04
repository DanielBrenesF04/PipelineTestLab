package com.cenfotec.lab5.lab5.controller;

import com.cenfotec.lab5.lab5.domain.Membresia;
import com.cenfotec.lab5.lab5.domain.Tiquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

@Controller
public class AppControler {

    @Autowired
    private static final Logger log;
    private static Connection connection = null;

    static {

        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(AppControler.class.getName());
    }



    public AppControler() throws SQLException, IOException {

        log.info("Loading application properties");
        Properties properties = new Properties();
        properties.load(AppControler.class.getClassLoader().getResourceAsStream("application.properties"));

        log.info("Connecting to the database");
        connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        log.info("Database connection test: " + connection.getCatalog());

        log.info("Create database schema");
        Scanner scanner = new Scanner(AppControler.class.getClassLoader().getResourceAsStream("schema.sql"));
        Statement statement = connection.createStatement();
        while (scanner.hasNextLine()) {
            statement.execute(scanner.nextLine());
        }
    }

    private static Tiquete readTiquete(Connection connection) throws SQLException {
        log.info("Read data");
        int num = 1;
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM Tiquete;");
        ResultSet resultSet = readStatement.executeQuery();
        if (!resultSet.next()) {
            log.info("La base de datos esta vacia");
            return null;
        }
        log.info("Primer elemento en cola");
        Tiquete tiquete = new Tiquete();
        tiquete.setId(resultSet.getLong("id"));
        tiquete.setNombrePelicula(resultSet.getString("NombrePelicula"));
        tiquete.setSala(resultSet.getString("Sala"));
        tiquete.setAsiento(resultSet.getString("Asiento"));
        log.info("Informacion de la base de datos: " + tiquete.toString());
        return tiquete;
    }

    private static void insertTiquete(Tiquete tiquete, Connection connection) throws SQLException {
        log.info("Insert domain.Tiquete");
        PreparedStatement insertStatement = connection.
                prepareStatement("INSERT INTO Tiquete(id,NombrePelicula,Sala,Asiento)VALUES (?, ?, ?, ?);");

        insertStatement.setLong(1, tiquete.getId());
        insertStatement.setString(2, tiquete.getNombrePelicula());
        insertStatement.setString(3, tiquete.getSala());
        insertStatement.setString(4, tiquete.getAsiento());
        insertStatement.executeUpdate();
    }

    private static Tiquete readTiquetebyId(int num, Connection connection) throws SQLException {
        log.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM Tiquete WHERE id =" + num);
        ResultSet resultSet = readStatement.executeQuery();
        if (!resultSet.next()) {
            log.info("La base de datos esta vacia");
            return null;
        }
        Tiquete tiquete = new Tiquete();
        tiquete.setId(resultSet.getLong("id"));
        tiquete.setNombrePelicula(resultSet.getString("NombrePelicula"));
        tiquete.setSala(resultSet.getString("Sala"));
        tiquete.setAsiento(resultSet.getString("Asiento"));
        log.info("Informacion de la base de datos: " + tiquete.toString());
        return tiquete;
    }

    private static void updateTiquete(Tiquete tiquete, Connection connection) throws SQLException {
        log.info("Update tiquete");
        PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE tiquete SET id= ?,NombrePelicula =?,Sala =? ,Asiento=? ");
        updateStatement.setLong(1, tiquete.getId());
        updateStatement.setString(2, tiquete.getNombrePelicula());
        updateStatement.setString(3, tiquete.getSala());
        updateStatement.setString(4, tiquete.getAsiento());
        updateStatement.executeUpdate();
    }

    private static void deleteTiquete(int num, Connection connection) throws SQLException {
        log.info("Borrar domain.Tiquete");

        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Tiquete WHERE id = "+num);
        deleteStatement.executeUpdate();

    }


    private static void insertarMembresia(Membresia membresia, Connection connection) throws SQLException {
        log.info("Insert domain.Membresia");
        PreparedStatement insertStatement = connection.
                prepareStatement("INSERT INTO Membresia(id,NombreDuenno,Cedula,PorcentajeDescuento,NivelMiembro,Puntos)" +
                        "VALUES (?, ?, ?, ?, ?, ?);");

        insertStatement.setLong(1, membresia.getId());
        insertStatement.setString(2, membresia.getNombreDuenno());
        insertStatement.setString(3, membresia.getCedula());
        insertStatement.setInt(4, membresia.getPorcentajeDescuento());
        insertStatement.setInt(5, membresia.getNivelMiembro());
        insertStatement.setInt(6, membresia.getPuntos());
        insertStatement.executeUpdate();
        readMembresia(connection);
    }

    private static Membresia readMembresia(Connection connection) throws SQLException {
        log.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM Membresia;");
        ResultSet resultSet = readStatement.executeQuery();
        if (!resultSet.next()) {
            log.info("La base de datos esta vacia");
            return null;
        }
        log.info("Primer elemento en cola");

        Membresia membresia = new Membresia();
        membresia.setId(resultSet.getLong("id"));
        membresia.setNombreDuenno(resultSet.getString("NombreDuenno"));
        membresia.setCedula(resultSet.getString("Cedula"));
        membresia.setPorcentajeDescuento(resultSet.getInt("PorcentajeDescuento"));
        membresia.setNivelMiembro(resultSet.getInt("NivelMiembro"));
        membresia.setPuntos(resultSet.getInt("Puntos"));

        log.info("Informacion de la base de datos: " + membresia.toString());

        return membresia;
    }

    private static Membresia readMembresiaId(int num, Connection connection) throws SQLException {
        log.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM Membresia WHERE id =" + num);
        ResultSet resultSet = readStatement.executeQuery();
        if (!resultSet.next()) {
            log.info("La base de datos esta vacia");
            return null;
        }
        log.info("Primer elemento en cola");

        Membresia membresia = new Membresia();
        membresia.setId(resultSet.getLong("id"));
        membresia.setNombreDuenno(resultSet.getString("NombreDuenno"));
        membresia.setCedula(resultSet.getString("Cedula"));
        membresia.setPorcentajeDescuento(resultSet.getInt("PorcentajeDescuento"));
        membresia.setNivelMiembro(resultSet.getInt("NivelMiembro"));
        membresia.setPuntos(resultSet.getInt("Puntos"));

        log.info("Informacion de la base de datos: " + membresia.toString());
        return membresia;
    }

    private static void updateMembresia(Membresia membresia, Connection connection) throws SQLException {
        log.info("Update domain.Membresia");
        PreparedStatement updateMembresia = connection
                .prepareStatement("UPDATE Membresia SET id= ?,NombreDuenno =?,Cedula =? ,PorcentajeDescuento=?," +
                        " NivelMiembro =?,Puntos=?");
        updateMembresia.setLong(1, membresia.getId());
        updateMembresia.setString(2, membresia.getNombreDuenno());
        updateMembresia.setString(3, membresia.getCedula());
        updateMembresia.setInt(4, membresia.getPorcentajeDescuento());
        updateMembresia.setInt(5, membresia.getNivelMiembro());
        updateMembresia.setInt(6, membresia.getPuntos());
        updateMembresia.executeUpdate();
        readMembresia(connection);
    }

    private static void deleteMembresia(int num, Connection connection) throws SQLException {
        log.info("Borrar domain.Membresia");

        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Membresia WHERE id = "+num);
        deleteStatement.executeUpdate();
        readMembresia(connection);

    }


    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "menuTiquetes.html")
    public String menuTiquetes(Model model) throws SQLException {
        model.addAttribute("Tiquete", readTiquete(connection));
        return "menuTiquetes";
    }

    @RequestMapping(value = "/agregarEntradaTiquete", method = RequestMethod.GET)
    public String navegarPaginaInsertarTiquete(Model model) {
        model.addAttribute(new Tiquete());
        return "agregarEntradaTiquete";
    }

    @RequestMapping(value = "/agregarEntradaTiquete", method = RequestMethod.POST)
    public String accionPaginaInsertar(Tiquete tiquete) throws SQLException {
        insertTiquete(tiquete,connection);
        return "exito";
    }

    @RequestMapping(value = "/editarT/{id}")
    public String irAEditarTiquete(Model model, @PathVariable int id) throws SQLException  {
        Optional<Tiquete> TiqueteToEdit = Optional.ofNullable(readTiquetebyId(id, connection));
        if (TiqueteToEdit.isPresent()) {
            model.addAttribute("TiqueteToEdit", TiqueteToEdit);
            return "editFormT";
        } else {
            return "notFound";
        }
    }

    @RequestMapping(value = "/editarT/{id}", method = RequestMethod.POST)
    public String guardarCambiosTiquete(Tiquete tiquete) throws SQLException {
        updateTiquete(tiquete,connection);
        return "exito";
    }

    @RequestMapping(value = "/borrarT/{id}")
    public String borrarTiquete(Model model, @PathVariable int id) throws SQLException {
        deleteTiquete(id,connection);
        return "exito";
    }



    ////

    @RequestMapping(value = "menuMembresia.html")
    public String menuMembresia(Model model) throws SQLException {
        model.addAttribute("Membresia", readMembresia(connection));
        return "menuMembresia";
    }

    @RequestMapping(value = "/agregarEntradaM", method = RequestMethod.GET)
    public String navegarPaginaMembresia(Model model) {
        model.addAttribute(new Membresia());
        return "agregarEntradaM";
    }

    @RequestMapping(value = "/agregarEntradaM", method = RequestMethod.POST)
    public String accionPaginaInsertarMembresia(Membresia membresia) throws SQLException {
        insertarMembresia(membresia,connection);
        return "exito";
    }

    @RequestMapping(value = "/editarM/{id}")
    public String irAEditarMembresia(Model model, @PathVariable int id) throws SQLException  {
        Optional<Membresia> MembresiaToEdit = Optional.ofNullable(readMembresiaId(id, connection));
        if (MembresiaToEdit.isPresent()) {
            model.addAttribute("MembresiaToEdit", MembresiaToEdit);
            return "editFormM";
        } else {
            return "notFound";
        }
    }

    @RequestMapping(value = "/editarM/{id}", method = RequestMethod.POST)
    public String guardarCambiosMembresia(Membresia membresia) throws SQLException {
        updateMembresia(membresia,connection);
        return "exito";
    }

    @RequestMapping(value = "/borrarM/{id}")
    public String borrarMembresia(Model model, @PathVariable int id) throws SQLException {
        deleteMembresia(id,connection);
        return "exito";
    }





}


