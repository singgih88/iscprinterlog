package model.mysql;

import controller.pojo.Admin;
import java.util.List;
import model.connectionPool.ConnectionPool;
import model.interfaces.AdminDAO;

public class MysqlAdminDAO extends ConnectionPool implements AdminDAO{

    @Override
    public void save(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Admin> listAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Admin find(String adminName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
