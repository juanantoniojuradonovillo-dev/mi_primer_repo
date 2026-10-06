package ProyectoFinal;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

// Ventana principal del gestor. Muestra la lista de vehículos y permite añadir, eliminar y ver reparaciones.
public class VentanaPpal extends JFrame {

    public VentanaPpal(GestionVehiculos gestion) {
        setTitle("Gestor de Vehículos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        DefaultListModel<String> modelo = new DefaultListModel<>();

        for (Vehiculo v : gestion.mostrarTodos()) {
            modelo.addElement(v.toString());
        }

        JList<String> lista = new JList<>(modelo);
        add(new JScrollPane(lista));
        JButton btnAnadir = new JButton("Añadir vehículo");
        JButton btnEliminar = new JButton("Eliminar vehículo");
        JButton btnReparaciones = new JButton("Ver reparaciones");
        JButton btnActualizarKm = new JButton("Actualizar km");

        JPanel panel = new JPanel();
        panel.add(btnAnadir);
        panel.add(btnEliminar);
        panel.add(btnReparaciones);
        panel.add(btnActualizarKm);
        add(panel, BorderLayout.SOUTH);
        // Recoge los datos del nuevo vehículo y lo añade a la lista
        btnAnadir.addActionListener(e -> {
            String marca = JOptionPane.showInputDialog("Introduce la marca:");
            String modelo2 = JOptionPane.showInputDialog("Introduce el modelo:");
            String matricula = JOptionPane.showInputDialog("Introduce la matrícula:");
            String kilometros = JOptionPane.showInputDialog("Introduce los kilómetros:");
            String tipo = JOptionPane.showInputDialog("Introduce el tipo (COCHE, MOTO, FURGONETA, CAMION):");

            try {
                int km = Integer.parseInt(kilometros);
                TipoVehiculo tipoV = TipoVehiculo.valueOf(tipo.toUpperCase());
                Vehiculo nuevo = new Vehiculo(km, marca, tipoV, matricula, modelo2);
                gestion.agregarVehiculo(nuevo);
                modelo.addElement(nuevo.toString());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Los kilómetros deben ser un número.");
            } catch (IllegalArgumentException e2) {
                JOptionPane.showMessageDialog(null, "Tipo de vehículo incorrecto.");
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                gestion.guardarVehiculos("vehiculos.txt");
                System.exit(0);
            }
        });
        // Elimina el vehículo seleccionado de la lista
        btnEliminar.addActionListener(e -> {
            int indice = lista.getSelectedIndex();
            if (indice != -1) {
                Vehiculo v = gestion.mostrarTodos().get(indice);
                gestion.eliminarVehiculo(v.getMatricula());
                modelo.remove(indice);
            }
        });
        // Abre la ventana de reparaciones del vehículo seleccionado
        btnReparaciones.addActionListener(e -> {
            int indice = lista.getSelectedIndex();
            if (indice != -1) {
                Vehiculo v = gestion.mostrarTodos().get(indice);
                new VentanaReparaciones(v);
            }
        });
        btnActualizarKm.addActionListener(e -> {
            int indice = lista.getSelectedIndex();
            if (indice != -1) {
                Vehiculo v = gestion.mostrarTodos().get(indice);
                String kmStr = JOptionPane.showInputDialog("Introduce los km actuales:");
                try {
                    int km = Integer.parseInt(kmStr);
                    v.setKilometros(km);
                    modelo.set(indice, v.toString()); // actualiza la lista visual
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Los kilómetros deben ser un número.");
                }
            }
        });
        setVisible(true);
    }
}
