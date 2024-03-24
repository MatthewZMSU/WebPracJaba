package cmc.msu.webpracjaba.DAO.impl;

import cmc.msu.webpracjaba.DAO.DutyDAO;
import cmc.msu.webpracjaba.models.Duty;
import org.springframework.stereotype.Repository;

@Repository
public class DutyDAOImpl extends CommonDAOImpl<Duty, Integer> implements DutyDAO {
    public DutyDAOImpl() {
        super(Duty.class);
    }
}
