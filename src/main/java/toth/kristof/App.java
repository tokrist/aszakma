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
    public App() {
        setTitle("A Szakma");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(750, 400));

        initComponents();

        getMainTableData();

        searchFieldPlaceholder();
    }

    private void getMainTableData() {
        emptyTable((DefaultTableModel) MainTable.getModel());

        Statement statement = getStatement();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM versenyzo INNER JOIN szakma ON versenyzo.szakmaId = szakma.id INNER JOIN orszag ON versenyzo.orszagId = orszag.id ORDER BY szakmaNev, nev");
            String[] temp = new String[result.getMetaData().getColumnCount()];

            while (result.next()) {
                temp[0] = result.getString("versenyzo.id");
                temp[1] = result.getString("nev");
                temp[2] = result.getString("szakmaNev");
                temp[3] = result.getString("orszagNev");
                temp[4] = result.getString("pont");

                ((DefaultTableModel) MainTable.getModel()).addRow(temp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchFieldKeyReleased(KeyEvent e) {
        Statement statement = getStatement();
        if(searchField.getText().trim().length() >= 3) {
            emptyTable((DefaultTableModel) MainTable.getModel());

            try {
                ResultSet result = statement.executeQuery("SELECT * FROM versenyzo INNER JOIN szakma ON versenyzo.szakmaId = szakma.id INNER JOIN orszag ON versenyzo.orszagId = orszag.id WHERE nev LIKE '%"+searchField.getText()+"%' OR szakma.szakmaNev LIKE '%"+searchField.getText()+"%' OR orszag.orszagNev LIKE '%"+searchField.getText()+"%'");
                String[] temp = new String[result.getMetaData().getColumnCount()];

                while (result.next()) {
                    temp[0] = result.getString("versenyzo.id");
                    temp[1] = result.getString("nev");
                    temp[2] = result.getString("szakmaNev");
                    temp[3] = result.getString("orszagNev");
                    temp[4] = result.getString("pont");

                    ((DefaultTableModel) MainTable.getModel()).addRow(temp);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            getMainTableData();
        }
    }

    public void searchFieldPlaceholder() {
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
    private Statement getStatement() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vizsga_szakma?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8", "root", "");
            return connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Tábla oszlopok megadása
     */
    private void defineTableColumns(DefaultTableModel table, String[] columns) {
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


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Kristof Toth
        panel2 = new JPanel();
        searchField = new JTextField();
        MainPanel = new JScrollPane();
        MainTable = new JTable();
        panel1 = new JPanel();
        exportPDFButton = new JButton();
        fiterButton = new JButton();
        newCompetitorButton = new JButton();

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

        //======== panel2 ========
        {
            panel2.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dialo\u0067", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel2. getBorder () ) )
            ; panel2. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "borde\u0072" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
            panel2.setLayout(new MigLayout(
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
                    searchFieldKeyReleased(e);
                }
            });
            panel2.add(searchField, "cell 0 0,wmax 100%");
        }
        contentPane.add(panel2, "cell 0 0,alignx center,growx 0,width 100%");

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

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "fillx,hidemode 3,align center top",
                // columns
                "[fill]" +
                "[fill]" +
                "[fill]",
                // rows
                "[]"));

            //---- exportPDFButton ----
            exportPDFButton.setText("Export\u00e1l\u00e1s PDF-be");
            panel1.add(exportPDFButton, "cell 0 0");

            //---- fiterButton ----
            fiterButton.setText("Sz\u0171r\u00e9s");
            panel1.add(fiterButton, "cell 1 0");

            //---- newCompetitorButton ----
            newCompetitorButton.setText("\u00daj Versenyz\u0151 felv\u00e9tele");
            panel1.add(newCompetitorButton, "cell 2 0");
        }
        contentPane.add(panel1, "cell 0 3");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Kristof Toth
    private JPanel panel2;
    private JTextField searchField;
    private JScrollPane MainPanel;
    private JTable MainTable;
    private JPanel panel1;
    private JButton exportPDFButton;
    private JButton fiterButton;
    private JButton newCompetitorButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}