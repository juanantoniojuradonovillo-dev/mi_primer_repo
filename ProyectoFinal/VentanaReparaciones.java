package ProyectoFinal;

import java.awt.BorderLayout;
import java.time.LocalDate;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//Ventana que nos permiter controlar las reparacines 
public class VentanaReparaciones extends JFrame {

    public VentanaReparaciones(Vehiculo vehiculo) {
        setTitle("Reparaciones - " + vehiculo.toString());
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DefaultListModel<String> modelo = new DefaultListModel<>();

        for (Reparacion r : vehiculo.getReparaciones()) {
            modelo.addElement(r.toString());
        }

        JList<String> lista = new JList<>(modelo);
        add(new JScrollPane(lista));
        JButton btnAnadir = new JButton("Añadir reparación");

        JPanel panel = new JPanel();
        panel.add(btnAnadir);
        add(panel, BorderLayout.SOUTH);
        // Recoge los datos de la reparación, la crea y la añade al vehículo y a la lista visual.
        // Gestiona errores si el usuario introduce datos incorrectos.
        btnAnadir.addActionListener(e -> {
            
            String kmStr = JOptionPane.showInputDialog("Kilómetros:");
            String fechaStr = JOptionPane.showInputDialog("Fecha (YYYY-MM-DD):");
            String costeStr = JOptionPane.showInputDialog("Coste:");
            String tipoStr = JOptionPane.showInputDialog("Tipo (CAMBIO_ACEITE, CAMBIO_FILTRO_AIRE, PASTILLAS_FRENOS, OTROS):");
            
            try {
                int km = Integer.parseInt(kmStr);
                double coste = Double.parseDouble(costeStr);
                LocalDate fecha = LocalDate.parse(fechaStr);
                TipoReparacion tipo = TipoReparacion.valueOf(tipoStr.toUpperCase());
                Reparacion r = new Reparacion(km, fecha, coste, tipo);
                vehiculo.agregarReparacion(r);
                modelo.addElement(r.toString());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Los kilómetros y el coste deben ser números.");
            } catch (IllegalArgumentException e2) {
                JOptionPane.showMessageDialog(null, "Tipo de reparación incorrecto.");
            } catch (Exception e3) {
                JOptionPane.showMessageDialog(null, "Fecha incorrecta. Usa el formato YYYY-MM-DD.");
            }

        });
        setVisible(true);
    }
}
