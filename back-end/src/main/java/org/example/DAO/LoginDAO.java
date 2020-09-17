package org.example.DAO;

import org.example.config.PasswordCrypter;
import org.example.model.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LoginDAO {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public LoginDAO(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    public User login(String email, String password) throws Exception {
        password = PasswordCrypter.crypter(password);
        System.out.println(password);
        System.out.println(email);
        try {
            //TODO we can altough set column logged for user_detail to 1
            ResultSet result = daoGetSet.getDataSet(
                    String.format(
                            "SELECT ud.id, r.name AS role FROM user_details ud LEFT JOIN roles r ON ud.role_id=r.id " +
                                    "WHERE ud.email='%s' AND ud.password='%s';",
                            email,
                            password
                    ));
            while (result.next()) {
                UUID id = UUID.fromString(result.getString("id"));
                final  String role = result.getString("role");

                return getUser(id, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new Exception("User not found");
    }

    private User getUser(UUID id, String role) throws Exception {
        switch (role) {
            case "mentor" :
                MentorDAO mentorDAO = new MentorDAO(dbConnection);
                return mentorDAO.get(id);
            case "student" :
                StudentDAO studentDAOImp = new StudentDAO(dbConnection, daoGetSet);
                return studentDAOImp.get(id);
            case "creep" :
                CreepDAO creepDAO = new CreepDAO(dbConnection, daoGetSet);
                return creepDAO.get(id);
            default :
                throw new Exception("Can not create user with " + role + " type");
        }
    }

}
