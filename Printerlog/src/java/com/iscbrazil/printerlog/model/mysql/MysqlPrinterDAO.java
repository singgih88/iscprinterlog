package model.mysql;

import model.connectionPool.ConnectionPool;
import model.interfaces.PrinterDAO;
import controller.pojo.Printer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class MysqlPrinterDAO extends ConnectionPool implements PrinterDAO{

    @Override
    public void save(Printer printer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Printer printer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Printer> listAll() {
        try {

            String SQL1 = "SELECT `printer_id`, `printer_name`, `printer_place`, `printer_printCounter` FROM `printer`";

            ResultSet res = super.getResultSet(SQL1);
            List<Printer> printers = new ArrayList<Printer>();

            while (res.next()) {
                Printer p = new Printer();
                p.setPrinterID(Integer.parseInt(res.getString("printer_id")));
                p.setPrinterPrintCounter(Integer.parseInt(res.getString("printer_printCounter")));
                p.setPrinterName(res.getString("printer_name"));
                p.setPrinterPlace(res.getString("printer_place"));
                printers.add(p);
            }
            return printers;

        } catch (SQLException ex) {
            return null; //Message reporting that user doesn't exist
        } catch (NamingException ex) {
            return null; //Message reporting that user doesn't exist
        } catch (ClassNotFoundException ex) {
            return null; //Message reporting that user doesn't exist
        } finally {
            try {
                super.closeConnection();
            } catch (SQLException ex) {
                System.out.println("Error closing Mysql connection");
            }
        }
    }

    @Override
    public Printer find(String printerName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
