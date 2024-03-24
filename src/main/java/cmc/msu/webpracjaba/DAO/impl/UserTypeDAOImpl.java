package cmc.msu.webpracjaba.DAO.impl;

import cmc.msu.webpracjaba.DAO.UserTypeDAO;
import cmc.msu.webpracjaba.models.UserType;

public class UserTypeDAOImpl extends CommonDAOImpl<UserType, Integer> implements UserTypeDAO {
    public UserTypeDAOImpl() {
        super(UserType.class);
    }
}
