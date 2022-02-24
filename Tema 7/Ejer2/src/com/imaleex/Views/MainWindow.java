package com.imaleex.Views;

import com.imaleex.Exceptions.AlumnoNoEncontradoException;
import com.imaleex.Exceptions.DatoNoValidoException;
import com.imaleex.Main;
import com.imaleex.Models.Alumno;
import com.imaleex.Utils.Faker;
import com.imaleex.Utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Alex Cortes
 */
public class MainWindow {
    private JTextField textFieldDni;
    private JTextField textFieldNombre;
    private JTextField textFieldApellidos;
    private JTextField textFieldCurso;
    private JButton salirButton;
    private JButton aceptarButton;
    private JPanel MainPanel;
    private JButton rellenoAleatorioButton;
    private JButton limpiarDatosButton;


    public static void main() {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MainWindow() {

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        textFieldDni.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try{
                    Alumno a = Main.searchAlumno(textFieldDni.getText());
                    if (Utils.inputBoolean("Quieres editar el alumno?")){
                        textFieldNombre.setText(a.getNombre());
                        textFieldApellidos.setText(a.getApellidos());
                        textFieldDni.setText(a.getDni());
                        textFieldCurso.setText(a.getCurso().getCodigo());
                    } else if (Utils.inputBoolean("Quieres borrar el alumno?")){
                       if (Main.deleteAlumno(a))
                           Utils.showInfoMessage("El alumno se ha eliminado con exito");
                    } else{
                        textFieldDni.setText("");
                    }

                } catch ( AlumnoNoEncontradoException ignored){}
            }
        });
        rellenoAleatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldNombre.setText(Faker.firstName());
                textFieldApellidos.setText(Faker.lastName());
                textFieldDni.setText(Faker.getDni());
                textFieldCurso.setText(Main.getRandCurso().getCodigo());
            }
        });
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addAlumno())
                    Utils.showInfoMessage("El alumno ha sido agregado/modificado con exito");
            }
        });
        limpiarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldNombre.setText("");
                textFieldApellidos.setText("");
                textFieldDni.setText("");
                textFieldCurso.setText("");
            }
        });
    }

    private boolean addAlumno(){
        try{
           Alumno a = Main.searchAlumno(textFieldDni.getText());
            editAlumno(a);
        } catch (AlumnoNoEncontradoException ignored){}

        try{
         Alumno a = new Alumno(textFieldNombre.getText(), textFieldApellidos.getText(), textFieldDni.getText(), Main.searchCurso(textFieldCurso.getText()));
         Main.listaAlumnos.add(a);
         return true;
        } catch (DatoNoValidoException e) {
            Utils.showErrorMessage(e.getMessage());
            return false;
        }
    }

    private void editAlumno(Alumno a){
        try {
            a.setNombre(textFieldNombre.getText());
            a.setApellidos(textFieldApellidos.getText());
            a.setDni(textFieldDni.getText());
            a.setCurso(Main.searchCurso(textFieldCurso.getText()));
        } catch (DatoNoValidoException e) {
            Utils.showErrorMessage(e.getMessage());
        }
    }
}
