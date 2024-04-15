package org.cst8288.finalproject.userdao;

import java.util.HashMap;
import java.util.Map;

public class UserAuthenticationDAO implements UserAuthenticationDAOInterface {
    private Map<Integer, String> passwordMap; // Maps userID to password
    private Map<String, Integer> emailMap; // Maps email to userID

    public UserAuthenticationDAO() {
        this.passwordMap = new HashMap<>();
        this.emailMap = new HashMap<>();
    }

    @Override
    public void setPassword(int userID, String password) {
        passwordMap.put(userID, password);
    }

    @Override
    public boolean verifyPassword(int userID2, String password) {
        Integer userID = emailMap.get(userID2);
        if (userID != null) {
            String storedPassword = passwordMap.get(userID);
            return storedPassword != null && storedPassword.equals(password);
        }
        return false;
    }


}

