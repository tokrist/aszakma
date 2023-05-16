/*
 * Created by JFormDesigner on Tue May 16 23:52:34 CEST 2023
 */

package toth.kristof;

import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;

import java.awt.*;
import java.sql.*;
import java.util.Objects;

/**
 * @author tokri
 */
public class NewCompetitor extends JFrame {
    public NewCompetitor() throws SQLException, ClassNotFoundException {
        setTitle("Új versenyző felvétele");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(400, 300));

        initComponents();

        getCountries();
        getTech();
    }

    private void submitCompetitorMouseClicked(MouseEvent e) throws SQLException, ClassNotFoundException {
        String contestantName = nameField.getText();
        String tech = Objects.requireNonNull(techBox.getSelectedItem()).toString();
        String country = Objects.requireNonNull(countryBox.getSelectedItem()).toString();
        String points = pointField.getText();

        Statement statement = getStatement();

        statement.addBatch("SET @orszagIdF = (SELECT id FROM orszag WHERE orszagNev = '"+country+"');");
        statement.addBatch("SET @szakmaIdF = (SELECT id FROM szakma WHERE szakmaNev = '"+tech+"');");
        statement.addBatch("INSERT INTO versenyzo (nev, szakmaId, orszagId, pont) VALUES ('"+contestantName+"', @szakmaIdF, @orszagIdF, '"+points+"');");
        statement.executeBatch();
        Object[] option1 = {"Folytatás", "Kilépés"};
        int option = JOptionPane.showOptionDialog(null, "Sikeres hozzáadás!", "Siker", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, option1, null);
        if(option != JOptionPane.YES_OPTION){
            dispose();
        }
    }

    private void getCountries() throws SQLException, ClassNotFoundException {
        Statement statement = getStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM orszag");
        while (result.next()) {
            countryBox.addItem(result.getString("orszagNev"));
        }
    }

    private void getTech() throws SQLException, ClassNotFoundException {
        Statement statement = getStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM szakma");
        while (result.next()) {
            techBox.addItem(result.getString("szakmaNev"));
        }
    }

    /*
     * SQL csatlakozás
     * @return Statement
     */
    private Statement getStatement() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vizsga_szakma?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8", "root", "");
        return connection.createStatement();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Kristof Toth
        nameLabel = new JLabel();
        nameField = new JTextField();
        countryLabel = new JLabel();
        countryBox = new JComboBox<String>();
        techLabel = new JLabel();
        techBox = new JComboBox<>();
        pointLabel = new JLabel();
        pointField = new JTextField();
        submitCompetitor = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "fillx,hidemode 3",
            // columns
            "[right]" +
            "[fill]" +
            "[]" +
            "[]" +
            "[]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- nameLabel ----
        nameLabel.setText("Teljes n\u00e9v");
        nameLabel.setLabelFor(nameField);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(nameLabel, "cell 0 0");
        contentPane.add(nameField, "cell 1 0 4 1");

        //---- countryLabel ----
        countryLabel.setText("Orsz\u00e1g");
        countryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        countryLabel.setLabelFor(countryBox);
        contentPane.add(countryLabel, "cell 0 1");
        contentPane.add(countryBox, "cell 1 1 4 1");

        //---- techLabel ----
        techLabel.setText("Szakma megnevez\u00e9se");
        techLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        techLabel.setLabelFor(techBox);
        contentPane.add(techLabel, "cell 0 2");
        contentPane.add(techBox, "cell 1 2 4 1");

        //---- pointLabel ----
        pointLabel.setText("El\u00e9rt pontsz\u00e1m");
        pointLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pointLabel.setLabelFor(pointField);
        contentPane.add(pointLabel, "cell 0 3");
        contentPane.add(pointField, "cell 1 3 4 1");

        //---- submitCompetitor ----
        submitCompetitor.setText("Versenyz\u0151 felv\u00e9tele");
        submitCompetitor.setBackground(new Color(0x339900));
        submitCompetitor.setForeground(Color.white);
        submitCompetitor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    submitCompetitorMouseClicked(e);
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        contentPane.add(submitCompetitor, "cell 1 5 4 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Kristof Toth
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel countryLabel;
    private JComboBox<String> countryBox;
    private JLabel techLabel;
    private JComboBox<String> techBox;
    private JLabel pointLabel;
    private JTextField pointField;
    private JButton submitCompetitor;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
