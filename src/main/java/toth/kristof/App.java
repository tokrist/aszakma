/*
 * Created by JFormDesigner on Tue May 16 20:28:06 CEST 2023
 */

package toth.kristof;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.util.stream.IntStream;

/**
 * @author tokri
 */
public class App extends JFrame {
    String[] mainTableColumns = new String[]{"Azonosító", "Teljes név", "Szakma", "Ország", "Pont"};
    public App() throws SQLException, ClassNotFoundException {
        setTitle("A Szakma");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(750, 400));

        initComponents();

        defineTableColumns((DefaultTableModel) MainTable.getModel(), mainTableColumns);
        getMainTableData();

        searchFieldPlaceholder();
    }

    private void getMainTableData() throws SQLException, ClassNotFoundException {
        emptyTable((DefaultTableModel) MainTable.getModel());

        Statement statement = getStatement();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM versenyzo INNER JOIN szakma ON versenyzo.szakmaId = szakma.id INNER JOIN orszag ON versenyzo.orszagId = orszag.id ORDER BY szakmaNev, nev");
            fillMainTable(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchFieldKeyReleased(KeyEvent e) throws SQLException, ClassNotFoundException {
        Statement statement = getStatement();
        if(searchField.getText().trim().length() >= 3) {
            emptyTable((DefaultTableModel) MainTable.getModel());

            try {
                ResultSet result = statement.executeQuery("SELECT * FROM versenyzo INNER JOIN szakma ON versenyzo.szakmaId = szakma.id INNER JOIN orszag ON versenyzo.orszagId = orszag.id WHERE nev LIKE '%"+searchField.getText()+"%' OR szakma.szakmaNev LIKE '%"+searchField.getText()+"%' OR orszag.orszagNev LIKE '%"+searchField.getText()+"%'");
                fillMainTable(result);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            getMainTableData();
        }
    }

    private void deleteButtonMouseClicked(MouseEvent e) throws SQLException, ClassNotFoundException {
        Statement statement = getStatement();

        int deleteId = Integer.parseInt((String) MainTable.getValueAt(MainTable.getSelectedRow(), 0));
        statement.execute("DELETE FROM versenyzo WHERE id = " + deleteId);

        getMainTableData();
    }

    private void fillMainTable(ResultSet result) throws SQLException {
        result.last();
        String[][] data = new String[result.getRow() - 1][result.getMetaData().getColumnCount()];

        result.first();
        int i = 0;
        while(result.next()) {
            String[] temp = new String[mainTableColumns.length];
            temp[0] = result.getString("versenyzo.id");
            temp[1] = result.getString("nev");
            temp[2] = result.getString("szakmaNev");
            temp[3] = result.getString("orszagNev");
            temp[4] = result.getString("pont");
            data[i] = temp;
            i++;
        }

        fillTable((DefaultTableModel) MainTable.getModel(), data);
    }

    private void newCompetitorButtonMouseClicked(MouseEvent e) throws SQLException, ClassNotFoundException {
        JFrame newCompetitor = new NewCompetitor();
        newCompetitor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                try {
                    getMainTableData();
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(true);
            }
        });
    }

    /*
     *  Keresőmező placeholderje
     */
    private void searchFieldPlaceholder() {
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Keresés (minimum 3 karakter)")) {
                    searchField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Keresés (minimum 3 karakter)");
                }
            }
        });
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
     * Tábla oszlopok megadása
     */
    private void defineTableColumns(DefaultTableModel table, String[] columns) {
        table.setColumnCount(0);
        for (String column : columns) {
            table.addColumn(column);
        }
    }

    /*
     * Tábla kiürítése
     */
    private void emptyTable(DefaultTableModel table) {
        IntStream.range(0, table.getRowCount()).map(i -> 0).forEach(table::removeRow);
    }

    /*
     * Tábla feltöltése adattal
     */
    private void fillTable(DefaultTableModel table, String[][] data) {
        for (String[] d : data) {
            table.addRow(d);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Kristof Toth
        topPanel = new JPanel();
        searchField = new JTextField();
        MainPanel = new JScrollPane();
        MainTable = new JTable();
        bottomPanel = new JPanel();
        exportPDFButton = new JButton();
        fiterButton = new JButton();
        newCompetitorButton = new JButton();
        deleteButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //======== topPanel ========
        {
            topPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
            .border.EmptyBorder(0,0,0,0), ".",javax.swing.border.TitledBorder
            .CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069al\u006fg",java.
            awt.Font.BOLD,0),java.awt.Color.red),topPanel. getBorder()))
            ;topPanel. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("\u0062or\u0064er".equals(e.getPropertyName()))throw new RuntimeException();}})
            ;
            topPanel.setLayout(new MigLayout(
                "fillx,hidemode 3,align center top",
                // columns
                "[fill]",
                // rows
                "[]"));

            //---- searchField ----
            searchField.setText("Keres\u00e9s (minimum 3 karakter)");
            searchField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        searchFieldKeyReleased(e);
                    } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            topPanel.add(searchField, "cell 0 0,wmax 100%");
        }
        contentPane.add(topPanel, "cell 0 0,alignx center,growx 0,width 100%");

        //======== MainPanel ========
        {
            MainPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //---- MainTable ----
            MainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            MainTable.setFillsViewportHeight(true);
            MainTable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null},
                },
                new String[] {
                    "Sorsz\u00e1m", "Teljes N\u00e9v", "Szakma", "Orsz\u00e1g", "El\u00e9rt Pontsz\u00e1m"
                }
            ) {
                Class<?>[] columnTypes = new Class<?>[] {
                    String.class, String.class, String.class, String.class, String.class
                };
                boolean[] columnEditable = new boolean[] {
                    false, false, false, false, false
                };
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            MainTable.setAutoCreateRowSorter(true);
            MainPanel.setViewportView(MainTable);
        }
        contentPane.add(MainPanel, "cell 0 1,aligny center,grow 100 0,width :100%:100%");

        //======== bottomPanel ========
        {
            bottomPanel.setLayout(new MigLayout(
                "fillx,hidemode 3,align center top",
                // columns
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]",
                // rows
                "[]"));

            //---- exportPDFButton ----
            exportPDFButton.setText("Export\u00e1l\u00e1s PDF-be");
            exportPDFButton.setBackground(new Color(0x000066));
            exportPDFButton.setForeground(Color.white);
            bottomPanel.add(exportPDFButton, "cell 0 0");

            //---- fiterButton ----
            fiterButton.setText("Sz\u0171r\u00e9s");
            fiterButton.setBackground(new Color(0x006600));
            fiterButton.setForeground(Color.white);
            bottomPanel.add(fiterButton, "cell 1 0");

            //---- newCompetitorButton ----
            newCompetitorButton.setText("\u00daj Versenyz\u0151 felv\u00e9tele");
            newCompetitorButton.setBackground(new Color(0x660066));
            newCompetitorButton.setForeground(Color.white);
            newCompetitorButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        newCompetitorButtonMouseClicked(e);
                    } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            bottomPanel.add(newCompetitorButton, "cell 2 0");

            //---- deleteButton ----
            deleteButton.setText("Kijel\u00f6lt t\u00f6rl\u00e9se");
            deleteButton.setBackground(new Color(0xcc0000));
            deleteButton.setForeground(Color.white);
            deleteButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        deleteButtonMouseClicked(e);
                    } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            bottomPanel.add(deleteButton, "cell 3 0");
        }
        contentPane.add(bottomPanel, "cell 0 3");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Kristof Toth
    private JPanel topPanel;
    private JTextField searchField;
    private JScrollPane MainPanel;
    private JTable MainTable;
    private JPanel bottomPanel;
    private JButton exportPDFButton;
    private JButton fiterButton;
    private JButton newCompetitorButton;
    private JButton deleteButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
