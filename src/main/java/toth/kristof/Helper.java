package toth.kristof;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.IntStream;

public class Helper {
    /*
     * Tábla oszlopok megadása
     */
    public static void defineTableColumns(DefaultTableModel table, String[] columns) {
        table.setColumnCount(0);
        for (String column : columns) {
            table.addColumn(column);
        }
    }

    /*
     * SQL csatlakozás
     * @return Statement
     */
    public static Statement getStatement() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vizsga_szakma?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8", "root", "");
        return connection.createStatement();
    }

    /*
     * Tábla kiürítése
     */
    public static void emptyTable(DefaultTableModel table) {
        IntStream.range(0, table.getRowCount()).map(i -> 0).forEach(table::removeRow);
    }

    /*
     * Tábla feltöltése adattal
     */
    public static void fillTable(DefaultTableModel table, String[][] data) {
        for (String[] d : data) {
            table.addRow(d);
        }
    }
}
