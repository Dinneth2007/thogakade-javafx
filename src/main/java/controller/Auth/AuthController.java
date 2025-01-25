package controller.Auth;

import db.DBConnection;
import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.*;

public class AuthController {
    Connection connection;
    BasicTextEncryptor basicTextEncryptor;
   String key="#567A";
    public AuthController() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getDBconnection().getConnection();
        basicTextEncryptor = new BasicTextEncryptor();

    }
    public boolean addregister(String uname, String email, String pwd, String repwd) throws SQLException {

        connection.setAutoCommit(false);
        String SQL = "Insert into  users (username,email,password) Values(?,?,?)";
        PreparedStatement stm = connection.prepareStatement(SQL);
        if (pwd.equals(repwd)) {
            stm.setObject(1, uname);
            stm.setObject(2, email);
            stm.setObject(3, pwd);

            int res = stm.executeUpdate();
            if (res > 0) {
                connection.setAutoCommit(true);
                return true;
            }else{
                connection.rollback();
            }


        }
        return false;
    }
//    private String encrypt(String password){
//
//        basicTextEncryptor.setPassword(this.key);
//        String encrypt = basicTextEncryptor.encrypt(password);
//        return encrypt;
//    }
//    private  String decrypt(String encrypted){
//
//        basicTextEncryptor.setPassword(this.key);
//        String decrypt = basicTextEncryptor.decrypt(encrypted);
//        return decrypt;
//    }
    public boolean login(String email, String pwd) throws SQLException {
        String SQL = "Select email,password From Users";
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        while (rst.next()) {
            String mail = rst.getString("email");
            String password = rst.getString("password");


//            System.out.println("Encrpted "+password);
//            System.out.println("Decrypted "+(password));
            if(email.equals(mail)&&pwd.equals((password))){
                return true;
            }
        }
        return false;

    }
}
