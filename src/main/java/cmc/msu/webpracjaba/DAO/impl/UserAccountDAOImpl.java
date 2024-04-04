package cmc.msu.webpracjaba.DAO.impl;

import cmc.msu.webpracjaba.DAO.UserAccountDAO;
import cmc.msu.webpracjaba.models.UserAccount;
import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDAOImpl extends CommonDAOImpl<UserAccount, Integer> implements UserAccountDAO {
    public UserAccountDAOImpl() {
        super(UserAccount.class);
    }
}
