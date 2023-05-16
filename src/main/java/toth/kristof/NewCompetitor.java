/*
 * Created by JFormDesigner on Tue May 16 23:52:34 CEST 2023
 */

package toth.kristof;

import javax.swing.*;
import net.miginfocom.swing.*;

import java.awt.*;

/**
 * @author tokri
 */
public class NewCompetitor extends JFrame {
    public NewCompetitor() {
        setTitle("Új versenyző felvétele");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(400, 300));

        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Kristof Toth
        nameLabel = new JLabel();
        nameField = new JTextField();
        countryLabel = new JLabel();
        countryBox = new JComboBox();
        techLabel = new JLabel();
        techBox = new JComboBox();
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
        techLabel.setText("text");
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
    private JComboBox countryBox;
    private JLabel techLabel;
    private JComboBox techBox;
    private JLabel pointLabel;
    private JTextField pointField;
    private JButton submitCompetitor;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
