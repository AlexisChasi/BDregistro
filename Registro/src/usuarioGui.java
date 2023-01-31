import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class usuarioGui {
    PreparedStatement ps;
    private JPanel principal;
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtCelular;
    private JTextField txtCorreo;
    private JButton ingresar;

    public usuarioGui() {
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                try {
                    con = getConection();
                    ps = con.prepareStatement("INSERT INTO datos (id, Nombre, Celular, Correo) VALUES(?,?,?,?) ");
                    ps.setString(1, txtID.getText());
                    ps.setString(2, txtNombre.getText());
                    ps.setString(3, txtCelular.getText());
                    ps.setString(4, txtCorreo.getText());
                    System.out.println(ps);//imprimo en consola para verificación

                    int res = ps.executeUpdate();

                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "Persona Guardada");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Guardar persona");
                    }
                    //limpiartxt();
                    //txtId.setText("");
                    //txtNombre.setText("");
                    //txtCelular.setText("");
                    //txtCorreo.setText("");
                    con.close();//importante!!!!

                } catch (HeadlessException | SQLException f) {
                    System.err.println(f);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("usuarioGui");
        frame.setContentPane(new usuarioGui().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static Connection getConection() {
        Connection con = null;
        String base = "bdregistro"; //Nombre de la base de datos
        String url = "jdbc:mysql://localhost:3306/" + base; //Direccion, puerto y nombre de la Base de Datos
        String user = "root"; //Usuario de Acceso a MySQL
        String password = "alexis16"; //Password del usuario

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }

}


