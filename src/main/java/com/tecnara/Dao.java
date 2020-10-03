package com.tecnara;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Dao {


    Connection conexion = null;
    String url = "jdbc:mysql://localhost:3306/company?serverTimezone=" + TimeZone.getDefault().getID();
    String usuario = "root";
    String clave = "620312786";

    public Dao() {

        try
        {
            conexion = DriverManager.getConnection(url,usuario,clave);
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conexión a la bbdd establecida.");
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            System.out.println("Error la Base de Datos");
            ex.printStackTrace();
        }
    }

    public List<User> loadUsers() {
        List<User> usuarios = new ArrayList<>();

        Statement statement;
        try {
            statement = conexion.createStatement();
            String sql = "SELECT * FROM user;";

            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int id= resultSet.getInt("id");
                String name =resultSet.getString("name");
                int age = resultSet.getInt("age");
                float salary =resultSet.getFloat("salary");
                User user = new User(id, name,age,salary);
                usuarios.add(user);
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            System.out.println("Conexion cerrada");
        }

        return usuarios;
    }

    public User loadUser(int id)  {

        Statement statement;
        User user=null;
        try {
            statement = conexion.createStatement();
            String sql = "SELECT * FROM user WHERE id=" + id +";";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            user=new User(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getFloat("salary"));
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            System.out.println("Conexion cerrada");
        }

        return user;
    }

    public String addUser(User user) {
        Statement s;
        try {
            s = this.conexion.createStatement();
            String sql = "INSERT INTO user(name,age,salary)\n" +
                    "VALUES('" + user.getName() + "','" + user.getAge() + "','" + user.getSalary() + "');";

            int rowAdd = s.executeUpdate(sql);

            if (rowAdd == 1) {
                return "The record has been added to the database";
            } else {
                return "The record has not been added to the database";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            System.out.println("Conexion cerrada");
        }

        return "Error";

    }

    public String modifyUser(User user,String columnModify,String newValueModificar)  {
        Statement s;
        try {
            s = this.conexion.createStatement();
//            if(columnModify.equalsIgnoreCase("name")){
                String sql = "UPDATE user SET " + columnModify + "= '" + newValueModificar + "'" +
                        "WHERE id=" + user.getId() + ";";
//            }
//            else if(columnModify.equalsIgnoreCase("age")){
//                int newAge = Integer.parseInt(newValueModificar);
//                String sql = "UPDATE user SET " + columnModify + "= '" + newValueModificar + "'" +
//                        "WHERE id=" + user.getId() + ";";
//            }

            int rowModified = s.executeUpdate(sql);
            if(rowModified==1){

                return "Se ha eliminado correctamente el registro a la bbdd";

            }else{

                return "No se ha eliminado el registro de la bbdd";

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            System.out.println("Conexion cerrada");
        }

        return "Error";


    }

    public String deleteUser(int id) {
        Statement s;
        try {
            s = this.conexion.createStatement();
            String sql = "DELETE FROM user WHERE id=" + id + ";";
            int rowModified = s.executeUpdate(sql);

            if(rowModified==1){

                return "Se ha eliminado correctamente el registro a la bbdd";

            }else{

                return "No se ha eliminado el registro de la bbdd";

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            System.out.println("Conexion cerrada");
        }

        return "Error";

    }
}
