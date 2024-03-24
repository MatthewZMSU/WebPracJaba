package cmc.msu.webpracjaba.DAO.impl;

import cmc.msu.webpracjaba.DAO.DutyDAO;
import cmc.msu.webpracjaba.models.Duty;

public class DutyDAOImpl extends CommonDAOImpl<Duty, Integer> implements DutyDAO {
    public DutyDAOImpl() {
        super(Duty.class);
    }
}
