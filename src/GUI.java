import BibliotecaAdmin.BibliotecaAdmin;
import BibliotecaAdmin.Cobranzas.MetodosPago;
import BibliotecaAdmin.Ediciones.*;
import BibliotecaAdmin.Excepciones.*;
import BibliotecaAdmin.Persona;
import BibliotecaAdmin.GeneroPersona;
import Utilidades.Utilidades;
import Utilidades.Validator;
import BibliotecaAdmin.Socio;
import BibliotecaAdmin.Cliente;
import BibliotecaAdmin.Venta;
import BibliotecaAdmin.Prestamo;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI {

private static final int ANCHO = 1280;
private static final int ALTO = 720;

        public static void logeo(BibliotecaAdmin b) {
            // Crear el marco (JFrame)
            try {
                UIManager.setLookAndFeel(new FlatMacDarkLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }

            // Crear el marco (JFrame)
            JFrame frame = new JFrame("Inicio de Sesión - Biblioteca");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null); // Centrar la ventana

            // Crear el panel principal
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(10, 10, 10, 10);
            constraints.fill = GridBagConstraints.HORIZONTAL;

            // Crear y añadir los componentes
            JLabel userLabel = new JLabel("Nombre de Usuario:");
            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(userLabel, constraints);

            JTextField userText = new JTextField(20);
            constraints.gridx = 1;
            panel.add(userText, constraints);

            JLabel passwordLabel = new JLabel("Contraseña:");
            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(passwordLabel, constraints);

            JPasswordField passwordText = new JPasswordField(20);
            constraints.gridx = 1;
            panel.add(passwordText, constraints);

            JButton loginButton = new JButton("Iniciar Sesión");
            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 2;
            constraints.anchor = GridBagConstraints.CENTER;
            panel.add(loginButton, constraints);

            // Añadir el panel al marco
            frame.add(panel);

            // Mostrar la ventana
            frame.setVisible(true);

            InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = panel.getActionMap();

            String enterKey = "ENTER_KEY";
            inputMap.put(KeyStroke.getKeyStroke("ENTER"), enterKey);
            actionMap.put(enterKey, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginButton.doClick();
                }
            });
            // Añadir el ActionListener al botón de inicio de sesión
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = userText.getText();
                    String password = new String(passwordText.getPassword());

                    // Aquí puedes añadir la lógica de autenticación
                    if (autenticarUsuario(username, password,b)) {
                        JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso");
                        frame.dispose(); ///Cierra una vez logeado

                        ///Abre el siguiente menu una vez logeado
                        GUI.menuLogeado(b);


                    } else {
                        JOptionPane.showMessageDialog(frame, "Nombre de usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Hacer visible la ventana
            frame.setVisible(true);
        }

        // Método de ejemplo para la autenticación del usuario
        private static boolean autenticarUsuario(String username, String password,BibliotecaAdmin b) {
            // En una aplicación real, deberías verificar las credenciales en una base de datos o un sistema de autenticación
            return b.getUsuario().equals(username) && b.getContrasenia().equals(password);
        }

        public static void menuLogeado(BibliotecaAdmin b) {

            try {
                UIManager.setLookAndFeel(new FlatMacDarkLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }

            ImageIcon logo = new ImageIcon("Iconos/LibreriaLogo.png");

            // Crear el marco (JFrame) principal
            JFrame frame = new JFrame("Biblioteca - Gestión");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(ANCHO, ALTO);
            frame.setLocationRelativeTo(null); // Centrar la ventana

            // Crear el panel principal
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            // Crear y añadir los componentes al panel superior (navegación)
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton menuLibrosyRevistas = new JButton("Libros y Revistas");
            JButton menuVentas = new JButton("Ventas");
            JButton menuPrestamos = new JButton("Prestamos");
            JButton menuMiembros = new JButton("Miembros");
            JButton modificarUsuario = new JButton("Modificar usuario");

            topPanel.add(menuLibrosyRevistas);
            topPanel.add(menuVentas);
            topPanel.add(menuPrestamos);
            topPanel.add(menuMiembros);
            topPanel.add(modificarUsuario);

            panel.add(topPanel, BorderLayout.NORTH);

            JPanel botPanel = new JPanel();
            topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton logoutButton = new JButton("Cerrar Sesión");


            botPanel.add(logoutButton);

            panel.add(botPanel, BorderLayout.SOUTH);

            // Crear el panel central para mostrar el contenido dinámico
            JPanel centerPanel = new JPanel();


            JLabel imgLabel = new JLabel(logo);
            centerPanel.add(imgLabel);

            panel.add(centerPanel, BorderLayout.CENTER);

            // Añadir el panel principal al marco
            frame.add(panel);

            menuLibrosyRevistas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    frame.dispose();
                    GUI.menuLibrosRevistas(b);
                }
            });
            menuVentas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    frame.dispose();
                    GUI.menuVentas(b);
                }
            });

            menuPrestamos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    frame.dispose();
                    GUI.menuPrestamos(b);
                }
            });

            menuMiembros.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    frame.dispose();
                    GUI.menuMiembros(b);
                }
            });

            // Acción para el botón "Cerrar Sesión"
            modificarUsuario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    GUI.modificarUsuario(b); // Volver a la pantalla de inicio de sesión
                }
            });

            // Acción para el botón "Cerrar Sesión"
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    GUI.logeo(b); // Volver a la pantalla de inicio de sesión
                }
            });
            frame.setVisible(true);
            Utilidades.escribir(b);
        }

    public static void menuLibrosRevistas(BibliotecaAdmin b) {

        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        ImageIcon logo = new ImageIcon("Iconos/LibreriaLogo.png");

        // Crear el marco (JFrame) principal
        JFrame frame = new JFrame("Biblioteca - Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear y añadir los componentes al panel superior (navegación)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton verLibrosButton = new JButton("Ver Libros y Revistas");
        JButton buscarLibroButton = new JButton("Buscar Libro o Revista");
        JButton buscarAutorButton = new JButton("Buscar libros por autor");
        JButton agregarLibroButton = new JButton("Agregar Libro");
        JButton agregarRevistaButton = new JButton("Agregar Revista");
        JButton modificarEdicionButton = new JButton("Modificar Edicion");

        JButton regresarButton = new JButton("Regresar");


        topPanel.add(verLibrosButton);
        topPanel.add(buscarLibroButton);
        topPanel.add(buscarAutorButton);
        topPanel.add(agregarLibroButton);
        topPanel.add(agregarRevistaButton);
        topPanel.add(modificarEdicionButton);
        topPanel.add(regresarButton);

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel botPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Cerrar Sesión");


        botPanel.add(logoutButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        // Crear el panel central para mostrar el contenido dinámico
        JPanel centerPanel = new JPanel();

        JLabel imgLabel = new JLabel(logo);
        centerPanel.add(imgLabel);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Añadir el panel principal al marco
        frame.add(panel);
        // Acción para el botón "Ver Libros"
        verLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                JTextArea booksArea = new JTextArea(20, 50);

                LinkedHashSet<Edicion> ediciones = b.getEdiciones();
                for (Edicion dato : ediciones) {
                    booksArea.append(dato.toString() + "\n\n");
                }
                booksArea.setEditable(false);
                centerPanel.add(new JScrollPane(booksArea));
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        // Acción para el botón "Buscar Libro"
        buscarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel bookNameLabel = new JLabel("Nombre del Libro:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(bookNameLabel, constraints);

                JTextField bookNameText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(bookNameText, constraints);

                JButton saveButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(saveButton, constraints);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        centerPanel.removeAll();
                        JTextArea booksArea = new JTextArea(20, 50);
                        booksArea.repaint();

                        String bookName = bookNameText.getText();
                        centerPanel.add(booksArea);
                        Edicion encontrada = b.buscarEdicion(bookName);
                        if (encontrada!=null)
                        {
                            booksArea.append(encontrada.toString());
                        }
                        else
                        {
                            booksArea.append("No se encuentra el libro en nuestro inventario");
                        }
                    }
                });

                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        buscarAutorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel bookNameLabel = new JLabel("Autor:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(bookNameLabel, constraints);

                JTextField autorText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(autorText, constraints);

                JButton saveButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(saveButton, constraints);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {



                        String autor = autorText.getText();

                        ArrayList<Edicion> resultados = b.buscarAutor(autor);
                        if (!resultados.isEmpty())
                        {
                            centerPanel.removeAll();
                            JTextArea booksArea = new JTextArea(20, 50);
                            booksArea.repaint();
                            centerPanel.add(booksArea);
                            JScrollPane scrollPane = new JScrollPane(booksArea);
                            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                            centerPanel.add(scrollPane);
                            centerPanel.revalidate();
                            centerPanel.repaint();

                            for (Edicion ed: resultados)
                            {
                                booksArea.append(ed.toString());
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se ha encontrado ningun libro con ese autor.");
                        }
                    }
                });

                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        // Acción para el botón "agregarLibro"
        agregarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                centerPanel.removeAll();

                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel tituloLabel = new JLabel("Titulo:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(tituloLabel, constraints);

                JTextField tituloText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(tituloText, constraints);

                JLabel autorLabel = new JLabel("Autor (Opcional):");
                constraints.gridx = 0;
                constraints.gridy = 1;
                centerPanel.add(autorLabel, constraints);

                JTextField autorText = new JTextField(20);
                constraints.gridx = 1;
                constraints.gridy = 1;
                centerPanel.add(autorText, constraints);

                JButton buscarButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 2;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(buscarButton, constraints);

                Buscador buscador = new Buscador();


                // Acción para el botón "Buscar"
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<Edicion> resultados = null;

                        if (tituloText.getText().isEmpty())
                        {
                            JOptionPane.showMessageDialog(null, "El campo del titulo no puede estar incompleto.");
                        }
                        else
                        {
                            if (autorText.getText().isEmpty())
                            {
                                try {
                                    buscador.buscarLibro(tituloText.getText());
                                    resultados=buscador.getResultados();
                                } catch (BusquedaSinResultadosException ex) {
                                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun libro con ese titulo.");
                                }
                            }
                            else
                            {
                                try {
                                    buscador.buscarLibro(tituloText.getText(),autorText.getText());
                                    resultados=buscador.getResultados();
                                } catch (BusquedaSinResultadosException ex) {
                                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun libro con ese titulo.");
                                }
                            }
                            if ((resultados!=null))
                            {
                                centerPanel.removeAll();


                                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                                centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                                JPanel bookContainerPanel = new JPanel(new BorderLayout());
                                bookContainerPanel.setLayout(new BoxLayout(bookContainerPanel, BoxLayout.Y_AXIS));

                                for (Edicion dato : resultados) {

                                    bookContainerPanel.add(crearLibroPanel(b,dato,frame));
                                    bookContainerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                                }

                                JScrollPane scrollPane = new JScrollPane(bookContainerPanel);
                                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                                // Añadir el JScrollPane al marco principal
                                centerPanel.add(scrollPane);


                                centerPanel.revalidate();
                                centerPanel.repaint();
                            }
                        }

                    }
                });


                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        // Acción para el botón "agregar Revista"
        agregarRevistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                centerPanel.removeAll();

                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel tituloLabel = new JLabel("Titulo:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(tituloLabel, constraints);

                JTextField tituloText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(tituloText, constraints);

                JButton buscarButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(buscarButton, constraints);

                Buscador buscador = new Buscador();


                // Acción para el botón "Buscar"
                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<Edicion> resultados = null;

                        if (tituloText.getText().isEmpty())
                        {
                            JOptionPane.showMessageDialog(null, "El campo del titulo no puede estar incompleto.");
                        }
                        else
                        {
                                try {
                                    buscador.buscarRevista(tituloText.getText());
                                    resultados=buscador.getResultados();
                                } catch (BusquedaSinResultadosException ex) {
                                    JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna revista con ese titulo.");
                                }

                            if (resultados!=null)
                            {
                                centerPanel.removeAll();


                                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                                centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                                JPanel bookContainerPanel = new JPanel(new BorderLayout());
                                bookContainerPanel.setLayout(new BoxLayout(bookContainerPanel, BoxLayout.Y_AXIS));

                                for (Edicion dato : resultados) {

                                    bookContainerPanel.add(crearRevistaPanel(b,dato,frame));
                                    bookContainerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                                }

                                JScrollPane scrollPane = new JScrollPane(bookContainerPanel);
                                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                                // Añadir el JScrollPane al marco principal
                                centerPanel.add(scrollPane);


                                centerPanel.revalidate();
                                centerPanel.repaint();
                            }
                        }

                    }
                });


                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        // Acción para el botón "Modificar Edicion"
        modificarEdicionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                centerPanel.removeAll();
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel bookNameLabel = new JLabel("Nombre del Libro:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(bookNameLabel, constraints);

                JTextField bookNameText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(bookNameText, constraints);

                JButton saveButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(saveButton, constraints);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        String bookName = bookNameText.getText();
                        Edicion ed = b.buscarEdicion(bookName);
                        JOptionPane.showMessageDialog(null, "Usted modificara la siguiente edicion: \n" + ed);
                        if (ed!=null)
                        {
                            centerPanel.removeAll();

                            centerPanel.setLayout(new GridBagLayout());
                            GridBagConstraints constraints = new GridBagConstraints();
                            constraints.insets = new Insets(10, 10, 10, 10);
                            constraints.fill = GridBagConstraints.HORIZONTAL;


                            JLabel stockLabel = new JLabel("Stock:");
                            constraints.gridx = 0;
                            constraints.gridy = 0;
                            centerPanel.add(stockLabel, constraints);

                            JTextField stockText = new JTextField(20);
                            constraints.gridx = 1;
                            centerPanel.add(stockText, constraints);

                            JButton modificarStockButton = new JButton("Modificar");
                            constraints.gridx = 2;
                            constraints.gridwidth = 2;
                            constraints.anchor = GridBagConstraints.CENTER;
                            centerPanel.add(modificarStockButton, constraints);

                            JLabel precioLabel = new JLabel("Precio:");
                            constraints.gridx = 0;
                            constraints.gridy = 1;
                            centerPanel.add(precioLabel, constraints);


                            JTextField precioText = new JTextField(20);
                            constraints.gridx = 1;
                            centerPanel.add(precioText, constraints);

                            JButton modificarPrecioButton = new JButton("Modificar");
                            constraints.gridx = 2;
                            constraints.gridwidth = 2;
                            constraints.anchor = GridBagConstraints.CENTER;
                            centerPanel.add(modificarPrecioButton, constraints);


                            centerPanel.revalidate();
                            centerPanel.repaint();

                            // Acción para el botón "Modificar Stock"
                            modificarStockButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        int num = Integer.parseInt(stockText.getText());
                                        ed.setStock(num);
                                        JOptionPane.showMessageDialog(null, "Modificado con exito.");
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(null, "Error. Solo se permiten numeros enteros.");
                                    }
                                }
                            });

                            // Acción para el botón "modificar Precio"
                            modificarPrecioButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        float num = Float.parseFloat(precioText.getText());
                                        ed.setPrecio(num);
                                        JOptionPane.showMessageDialog(null, "Modificado con exito.");
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(null, "Error. Debe ser un numero entero o con coma. Utilizar punto.");
                                    }
                                }
                            });

                        } else{JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna edicion con ese titulo"); }

                    }
                });

                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });
        // Acción para el botón "Regresar"
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.menuLogeado(b);
            }
        });

        // Acción para el botón "Cerrar Sesión"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.logeo(b); // Volver a la pantalla de inicio de sesión
            }
        });
        frame.setVisible(true);
        Utilidades.escribir(b);
    }

    private static JPanel crearRevistaPanel(BibliotecaAdmin b, Edicion e, JFrame frame) {
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        Revista r = (Revista) e;

        ImageIcon imgIcon = new ImageIcon(e.getImagen());

        JLabel imgLabel = new JLabel(imgIcon);
        JLabel idLabel = new JLabel("ID: " + r.getId());
        JLabel tituloLabel = new JLabel("Titulo: " + r.getTitulo());
        JLabel fechaLabel = new JLabel("Fecha de publicacion: " + r.getFechaPublicacion());
        JLabel descLabel = new JLabel("Edicion digital: " + r.isEdicionDigital());

        JButton selectButton = new JButton("Seleccionar");

        // Acción al pulsar el botón
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (!b.contieneID(e.getId()))
                {
                    JOptionPane.showMessageDialog(null, "Se agregara la revista:" + e);
                    frame.dispose();
                    GUI.revistaSeleccionada(b,e);
                }else{
                    JOptionPane.showMessageDialog(null, "Error. La revista ya se encuentra en el inventario.");
                }

            }
        });

        //bookPanel.add(imgLabel); No muestra la imagen por algun motivo?
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(idLabel);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(tituloLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(fechaLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(descLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(selectButton);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        bookPanel.revalidate();
        bookPanel.repaint();

        return bookPanel;
    }

    public static void revistaSeleccionada(BibliotecaAdmin b, Edicion e)
    {
// Crear el marco (JFrame) principal
        JFrame frame = new JFrame("Biblioteca - Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear y añadir los componentes al panel superior (navegación)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton regresarButton = new JButton("Regresar");
        topPanel.add(regresarButton);
        panel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel botPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Cerrar Sesión");

        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;


        JLabel stockLabel = new JLabel("Stock:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(stockLabel, constraints);

        JTextField stockText = new JTextField(20);
        constraints.gridx = 1;
        centerPanel.add(stockText, constraints);

        JLabel precioLabel = new JLabel("Precio:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        centerPanel.add(precioLabel, constraints);


        JTextField precioText = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        centerPanel.add(precioText, constraints);


        JButton agregarButton = new JButton("Agregar");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        centerPanel.add(agregarButton, constraints);



        botPanel.add(logoutButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        // Crear el panel central para mostrar el contenido dinámico


        // Añadir el panel principal al marco
        frame.add(panel);

        // Acción para el botón "Agregar"
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (precioText.getText().isEmpty() || stockText.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Por favor asegurese de completar todos los datos");
                }
                else
                {
                    try{
                        int stock = Integer.parseInt(stockText.getText());
                        float precio = Float.parseFloat(precioText.getText());
                        if (stock>=0 && precio>=0)
                        {
                            e.setStock(stock);
                            e.setPrecio(precio);
                            b.addEdicion(e);
                            JOptionPane.showMessageDialog(null, "Libro agregado exitosamente");
                            frame.dispose();
                            GUI.menuLogeado(b);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Dato ingresado no valido. No se permiten numeros negativos.");
                        }


                    }catch (NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "Dato ingresado no valido. En el precio asegurese de utilizar punto, no coma.");
                    }
                }
            }
        });

        // Acción para el botón "Regresar"
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.menuLogeado(b);
            }
        });

        // Acción para el botón "Cerrar Sesión"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.logeo(b); // Volver a la pantalla de inicio de sesión
            }
        });
        frame.setVisible(true);

        Utilidades.escribir(b);
    }

    private static JPanel crearLibroPanel(BibliotecaAdmin b, Edicion e, JFrame frame) {
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        Libro l = (Libro)e;

        ImageIcon imgIcon = new ImageIcon(e.getImagen());

        JLabel imgLabel = new JLabel(imgIcon);
        JLabel idLabel = new JLabel("ID: " + l.getId());
        JLabel tituloLabel = new JLabel("Titulo: " + l.getTitulo());
        JLabel autorLabel = new JLabel("Autor: " + l.getAutor());
        JLabel editorialLabel = new JLabel("Editorial: " + l.getEditorial());
        JLabel fechaLabel = new JLabel("Fecha de publicacion: " + e.getFechaPublicacion());
        JLabel isbnLabel = new JLabel("ISBN: " + l.getIsbn());
        JLabel descLabel = new JLabel("Descripcion: " + l.getDescripcion());

        JButton selectButton = new JButton("Seleccionar");

        // Acción al pulsar el botón
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!b.contieneID(e.getId()))
                {
                    JOptionPane.showMessageDialog(null, "Se agregara el libro:" + e);
                    frame.dispose();
                    GUI.libroSeleccionado(b,e);
                }else{
                    JOptionPane.showMessageDialog(null, "Error. El libro ya se encuentra en el inventario.");
                }

            }
        });

        //bookPanel.add(imgLabel); No muestra la imagen por algun motivo?
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(idLabel);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(tituloLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(autorLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(editorialLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(fechaLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(isbnLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(descLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(selectButton);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        bookPanel.revalidate();
        bookPanel.repaint();

        return bookPanel;
    }

    public static void libroSeleccionado(BibliotecaAdmin b, Edicion e)
    {
// Crear el marco (JFrame) principal
        JFrame frame = new JFrame("Biblioteca - Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear y añadir los componentes al panel superior (navegación)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton regresarButton = new JButton("Regresar");
        topPanel.add(regresarButton);
        panel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel botPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Cerrar Sesión");

        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;


        JLabel stockLabel = new JLabel("Stock:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(stockLabel, constraints);

        JTextField stockText = new JTextField(20);
        constraints.gridx = 1;
        centerPanel.add(stockText, constraints);

        JLabel precioLabel = new JLabel("Precio:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        centerPanel.add(precioLabel, constraints);


        JTextField precioText = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        centerPanel.add(precioText, constraints);

        JLabel generoLabel = new JLabel("Genero:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        centerPanel.add(generoLabel, constraints);

        JComboBox cmbGenero = new JComboBox<>(GeneroEdicion.values());
        cmbGenero.setMaximumSize(new Dimension(250,30));
        constraints.gridx = 1;
        centerPanel.add(cmbGenero,constraints);

        JButton agregarButton = new JButton("Agregar");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        centerPanel.add(agregarButton, constraints);



        botPanel.add(logoutButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        // Crear el panel central para mostrar el contenido dinámico


        // Añadir el panel principal al marco
        frame.add(panel);

        // Acción para el botón "Agregar"
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (precioText.getText().isEmpty() || stockText.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Por favor asegurese de completar todos los datos");
                }
                else
                {
                    try{
                        int stock = Integer.parseInt(stockText.getText());
                        float precio = Float.parseFloat(precioText.getText());

                        if (stock>=0 && precio>=0)
                        {
                            e.setStock(stock);
                            e.setPrecio(precio);
                            ((Libro)e).setGenero((GeneroEdicion) cmbGenero.getSelectedItem());
                            b.addEdicion(e);
                            JOptionPane.showMessageDialog(null, "Libro agregado exitosamente");
                            frame.dispose();
                            GUI.menuLogeado(b);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Dato ingresado no valido. No se permiten numeros negativos.");
                        }


                    }catch (NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "Dato ingresado no valido. En el precio asegurese de utilizar punto, no coma.");
                    }
                }
            }
        });

        // Acción para el botón "Regresar"
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.menuLogeado(b);
            }
        });

        // Acción para el botón "Cerrar Sesión"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.logeo(b); // Volver a la pantalla de inicio de sesión
            }
        });
        frame.setVisible(true);
        Utilidades.escribir(b);
    }

    public static void menuVentas(BibliotecaAdmin b) {

        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }


        ImageIcon logo = new ImageIcon("Iconos/LibreriaLogo.png");

        // Crear el marco (JFrame) principal
        JFrame frame = new JFrame("Biblioteca - Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear y añadir los componentes al panel superior (navegación)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton verVentasButton = new JButton("Ver historial de ventas");
        JButton generarVentaButton = new JButton("Generar venta");
        JButton buscarMiembroVentasButton = new JButton("Buscar por miembro");

        JButton regresarButton = new JButton("Regresar");


        topPanel.add(verVentasButton);
        topPanel.add(generarVentaButton);
        topPanel.add(buscarMiembroVentasButton);
        topPanel.add(regresarButton);

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel botPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Cerrar Sesión");


        botPanel.add(logoutButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        // Crear el panel central para mostrar el contenido dinámico
        JPanel centerPanel = new JPanel();

        JLabel imgLabel = new JLabel(logo);
        centerPanel.add(imgLabel);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Añadir el panel principal al marco
        frame.add(panel);

        // Acción para el botón "ver Ventas"
        verVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                centerPanel.removeAll();


                JTextArea booksArea = new JTextArea(20, 50);

                LinkedList<Venta> ventas = b.getVentas();
                for (Venta dato : ventas) {
                    booksArea.append(dato.toString() + "\n--------------------------------------------------------------\n");
                }
                booksArea.setEditable(false);
                centerPanel.add(new JScrollPane(booksArea));

                centerPanel.revalidate();
                centerPanel.repaint();

            }
        });



       generarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                centerPanel.removeAll();
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                ArrayList<Edicion> carrito = new ArrayList<>();
                //Edicion encontrada = null;

                JLabel bookNameLabel = new JLabel("Nombre del Libro:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(bookNameLabel, constraints);

                JTextField bookNameText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(bookNameText, constraints);

                JButton saveButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(saveButton, constraints);


                JButton agregarAlCarritoButton = new JButton("Agregar al carrito");
                constraints.gridy = 3;
                constraints.gridwidth = 1;
                centerPanel.add(agregarAlCarritoButton, constraints);
                agregarAlCarritoButton.setVisible(true);

                JButton finalizarCompraButton = new JButton("Finalizar Compra");
                constraints.gridx = 1;
                centerPanel.add(finalizarCompraButton, constraints);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String bookName = bookNameText.getText();

                        Edicion encontrada = b.buscarEdicion(bookName);
                        if (encontrada!=null)
                        {
                            JOptionPane.showMessageDialog(null, encontrada);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se ha encontrado la edicion solicitada");
                        }
                    }
                });

                agregarAlCarritoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String bookName = bookNameText.getText();

                        Edicion encontrada = b.buscarEdicion(bookName);
                        if (encontrada!=null)
                        {

                            if (encontrada.getStock()!=0)
                            {
                                JOptionPane.showMessageDialog(null, "Agregado correctamente al carrito");
                                carrito.add(encontrada);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "No se posee stock del libro ingresado");
                            }

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error. Libro inexistente");
                        }

                    }
                });

                finalizarCompraButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (carrito.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El carrito está vacío.");
                        }
                        else
                        {
                            centerPanel.removeAll();

                            JTextArea booksArea = new JTextArea(20, 50);
                            centerPanel.add(booksArea);
                            booksArea.setText("Usted comprara los siguientes items");
                            booksArea.append("\n" + carrito.toString());
                            centerPanel.add(new JScrollPane(booksArea));



                            JButton continuarButton = new JButton("Continuar");
                            constraints.gridx = 1;
                            constraints.gridy = 0;
                            constraints.gridwidth = 1;
                            constraints.anchor = GridBagConstraints.CENTER;
                            centerPanel.add(continuarButton, constraints);

                            centerPanel.revalidate();
                            centerPanel.repaint();

                            continuarButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    frame.dispose();
                                    GUI.finalizarVenta(b,carrito);
                                }
                            });
                        }

                    }
                });

                centerPanel.revalidate();
                centerPanel.repaint();

            }
        });


        // Acción para el botón "buscar por DNI"
        buscarMiembroVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel dniLabel = new JLabel("DNI:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(dniLabel, constraints);

                JTextField dniText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(dniText, constraints);

                JButton saveButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(saveButton, constraints);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {



                        String dni = dniText.getText();

                        ArrayList<Venta> resultados = b.buscarVentasPorDNI(dni);
                        if (!resultados.isEmpty())
                        {
                            centerPanel.removeAll();
                            JTextArea booksArea = new JTextArea(20, 50);
                            booksArea.repaint();
                            centerPanel.add(booksArea);
                            JScrollPane scrollPane = new JScrollPane(booksArea);
                            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                            centerPanel.add(scrollPane);
                            centerPanel.revalidate();
                            centerPanel.repaint();

                            for (Venta v: resultados)
                            {
                                booksArea.append(v.toString());
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna venta con ese DNI.");
                        }
                    }
                });

                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        // Acción para el botón "Regresar"
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.menuLogeado(b);
            }
        });

        // Acción para el botón "Cerrar Sesión"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.logeo(b); // Volver a la pantalla de inicio de sesión
            }
        });


        frame.setVisible(true);
        Utilidades.escribir(b);
    }

    public static void finalizarVenta(BibliotecaAdmin b, ArrayList<Edicion> carrito){


        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        // Crear el marco (JFrame) principal
        JFrame frame = new JFrame("Biblioteca - Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear y añadir los componentes al panel superior (navegación)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel botPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Cerrar Sesión");


        botPanel.add(logoutButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        // Crear el panel central para mostrar el contenido dinámico
        JPanel centerPanel = new JPanel();

        panel.add(centerPanel, BorderLayout.CENTER);

        // Añadir el panel principal al marco
        frame.add(panel);

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel bookNameLabel = new JLabel("DNI del comprador:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        topPanel.add(bookNameLabel, constraints);

        JTextField dniText = new JTextField(20);
        constraints.gridx = 1;
        topPanel.add(dniText, constraints);

        JLabel medioPagoLabel = new JLabel("Medio de Pago:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        JComboBox cmbGenero = new JComboBox<>(MetodosPago.values());
        cmbGenero.setMaximumSize(new Dimension(250,30));
        topPanel.add(medioPagoLabel,constraints);
        topPanel.add(cmbGenero,constraints);

        ImageIcon logo = new ImageIcon("Iconos/LibreriaLogo.png");
        JLabel imgLabel = new JLabel(logo);
        centerPanel.add(imgLabel);

        JButton cobranzaButton = new JButton("Ir a pagar");
        centerPanel.add(cobranzaButton);

        JButton regresarButton = new JButton("Regresar");
        centerPanel.add(regresarButton);


        // Acción para el botón "Ir a Pagar"
        cobranzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Persona p = b.buscarMiembro(dniText.getText());
                if (p!=null)
                {
                    if (p.isActivo())
                    {
                        b.generaVenta(carrito,p,(MetodosPago) cmbGenero.getSelectedItem());
                        Venta v = b.getVentas().getLast();

                        if (v.getMedioPago()==MetodosPago.EFECTIVO)
                        {
                            JOptionPane.showMessageDialog(null, v.generaTicket());
                            frame.dispose();
                            GUI.menuLogeado(b);
                        }
                        else if (v.getMedioPago()==MetodosPago.TARJETA_DEBITO)
                        {
                            topPanel.removeAll();
                            topPanel.add(regresarButton);

                            centerPanel.removeAll();

                            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                            centerPanel.setBorder(BorderFactory.createEmptyBorder(120, 20, 20, 20));


                            JLabel nroDebitoLabel = new JLabel("Numero de tarjeta de Debito:");
                            nroDebitoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(nroDebitoLabel);

                            JTextField nroDebitoText = new JTextField(20);
                            nroDebitoText.setMaximumSize(new Dimension(300, nroDebitoText.getPreferredSize().height));
                            nroDebitoText.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(nroDebitoText);

                            centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

                            // JLabel 2, JText 2 y Boton 1
                            JLabel nroSeguridadLabel = new JLabel("Codigo de seguridad:");
                            nroSeguridadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(nroSeguridadLabel);

                            JTextField nroSeguridadText = new JTextField(20);
                            nroSeguridadText.setMaximumSize(new Dimension(300, nroSeguridadText.getPreferredSize().height));
                            nroSeguridadText.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(nroSeguridadText);

                            centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

                            // JLabel 3 y JText 3
                            JLabel fechaLabel = new JLabel("Ingrese la fecha de vencimiento (MM/YYYY):");
                            fechaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(fechaLabel);

                            JTextField fechaTextField = new JTextField(20);
                            fechaTextField.setMaximumSize(new Dimension(300, fechaTextField.getPreferredSize().height));
                            fechaTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(fechaTextField);

                            JButton pagarButton = new JButton("Pagar");
                            pagarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(pagarButton);

                            // Acción para el botón "Pagar"
                            pagarButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    try {
                                        Validator.isNumericE(nroDebitoText.getText());
                                        Validator.isNumericE(nroSeguridadText.getText());
                                        Validator.nroTarjetaValidoE(nroDebitoText.getText().length());
                                        Validator.codSegValidoE(nroSeguridadText.getText().length());
                                        Validator.validarFecha(fechaTextField.getText());
                                        JOptionPane.showMessageDialog(null, "Pago realizado correctamente.\n" + v.generaTicket());
                                        frame.dispose();
                                        GUI.menuLogeado(b);
                                    } catch (StringNoNumericoException ex) {
                                        JOptionPane.showMessageDialog(null, "Error: Solo se permiten numeros.");
                                    } catch (CantDigitosIncorrectoException ex) {
                                        JOptionPane.showMessageDialog(null, "Error: Cantidad de digitos invalidos.");
                                    } catch (TarjetaVencidaException ex) {
                                        JOptionPane.showMessageDialog(null, "Error: Tarjeta Vencida.");
                                    }catch (DateTimeParseException ex) {
                                        JOptionPane.showMessageDialog(null, "Error: Fecha invalida");

                                }
                                }
                            });

                            topPanel.revalidate();
                            topPanel.repaint();
                            centerPanel.revalidate();
                            centerPanel.repaint();
                        }
                        else if (v.getMedioPago()==MetodosPago.TARJETA_CREDITO)
                        {
                            topPanel.removeAll();
                            topPanel.add(regresarButton);

                            centerPanel.removeAll();

                            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                            centerPanel.setBorder(BorderFactory.createEmptyBorder(120, 20, 20, 20));


                            JLabel nroDebitoLabel = new JLabel("Numero de tarjeta de Credito:");
                            nroDebitoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(nroDebitoLabel);

                            JTextField nroCreditoText = new JTextField(20);
                            nroCreditoText.setMaximumSize(new Dimension(300, nroCreditoText.getPreferredSize().height));
                            nroCreditoText.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(nroCreditoText);

                            centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

                            // JLabel 2, JText 2 y Boton 1
                            JLabel nroSeguridadLabel = new JLabel("Codigo de seguridad:");
                            nroSeguridadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(nroSeguridadLabel);

                            JTextField nroSeguridadText = new JTextField(20);
                            nroSeguridadText.setMaximumSize(new Dimension(300, nroSeguridadText.getPreferredSize().height));
                            nroSeguridadText.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(nroSeguridadText);

                            centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre componentes

                            // JLabel 3 y JText 3
                            JLabel fechaLabel = new JLabel("Ingrese la fecha de vencimiento (MM/YYYY):");
                            fechaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(fechaLabel);

                            JTextField fechaTextField = new JTextField(20);
                            fechaTextField.setMaximumSize(new Dimension(300, fechaTextField.getPreferredSize().height));
                            fechaTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(fechaTextField);

                            JButton pagarButton = new JButton("Pagar");
                            pagarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                            centerPanel.add(pagarButton);

                            // Acción para el botón "Pagar"
                            pagarButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    try {
                                        Validator.isNumericE(nroCreditoText.getText());
                                        Validator.isNumericE(nroSeguridadText.getText());
                                        Validator.nroTarjetaValidoE(nroCreditoText.getText().length());
                                        Validator.codSegValidoE(nroSeguridadText.getText().length());
                                        Validator.validarFecha(fechaTextField.getText());
                                        JOptionPane.showMessageDialog(null, "Pago realizado correctamente.\n" + v.generaTicket());
                                        frame.dispose();
                                        GUI.menuLogeado(b);
                                    } catch (StringNoNumericoException ex) {
                                        JOptionPane.showMessageDialog(null, "Error: Solo se permiten numeros.");
                                    } catch (CantDigitosIncorrectoException ex) {
                                        JOptionPane.showMessageDialog(null, "Error: Cantidad de digitos invalidos.");
                                    } catch (TarjetaVencidaException ex) {
                                        JOptionPane.showMessageDialog(null, "Error: Tarjeta Vencida.");
                                    }catch (DateTimeParseException ex) {
                                        JOptionPane.showMessageDialog(null, "Error: Fecha invalida");
                                    }


                                }
                            });

                            topPanel.revalidate();
                            topPanel.repaint();
                            centerPanel.revalidate();
                            centerPanel.repaint();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error: El miembro se encuentra inactivo.");
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: No existe ningun miembro con ese DNI.");
                }
            }
        });

    // Acción para el botón "RegresarButton"
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.menuLogeado(b);
            }
        });


        // Acción para el botón "Cerrar Sesión"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.logeo(b); // Volver a la pantalla de inicio de sesión
            }
        });


        frame.setVisible(true);
        Utilidades.escribir(b);
    }

    public static void menuPrestamos(BibliotecaAdmin b) {

        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        ImageIcon logo = new ImageIcon("Iconos/LibreriaLogo.png");

        // Crear el marco (JFrame) principal
        JFrame frame = new JFrame("Biblioteca - Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear y añadir los componentes al panel superior (navegación)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton verPrestamosButton = new JButton("Ver Prestamos");
        JButton buscarSocioPrestamosButton = new JButton("Buscar por Socio");
        JButton generaPrestamoButton = new JButton("Generar Prestamo");
        JButton devuelvePrestamoButton = new JButton("Generar Devolucion");

        JButton regresarButton = new JButton("Regresar");


        topPanel.add(verPrestamosButton);
        topPanel.add(buscarSocioPrestamosButton);
        topPanel.add(generaPrestamoButton);
        topPanel.add(devuelvePrestamoButton);
        topPanel.add(regresarButton);

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel botPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Cerrar Sesión");


        botPanel.add(logoutButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        // Crear el panel central para mostrar el contenido dinámico
        JPanel centerPanel = new JPanel();

        JLabel imgLabel = new JLabel(logo);
        centerPanel.add(imgLabel);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Añadir el panel principal al marco
        frame.add(panel);



        // Acción para el botón "verPrestamosButton"
        verPrestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new BorderLayout());
                JTextArea booksArea = new JTextArea(20, 50);

                LinkedList<Prestamo> prestamos = b.getPrestamos();
                for (Prestamo dato : prestamos) {
                    booksArea.append(dato.toString() + "\n\n");
                }
                booksArea.setEditable(false);
                centerPanel.add(new JScrollPane(booksArea));
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        // Acción para el botón "buscar por DNI"
        buscarSocioPrestamosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel dniLabel = new JLabel("DNI:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(dniLabel, constraints);

                JTextField dniText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(dniText, constraints);

                JButton saveButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(saveButton, constraints);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {



                        String dni = dniText.getText();

                        ArrayList<Prestamo> resultados = b.buscarPrestamosPorDNI(dni);
                        if (!resultados.isEmpty())
                        {
                            centerPanel.removeAll();
                            JTextArea booksArea = new JTextArea(20, 50);
                            booksArea.repaint();
                            centerPanel.add(booksArea);
                            JScrollPane scrollPane = new JScrollPane(booksArea);
                            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                            centerPanel.add(scrollPane);
                            centerPanel.revalidate();
                            centerPanel.repaint();

                            for (Prestamo p: resultados)
                            {
                                booksArea.append(p.toString());
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se ha encontrado ningun prestamo bajo ese DNI");
                        }
                    }
                });

                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        generaPrestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                centerPanel.removeAll();

                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                centerPanel.revalidate();
                centerPanel.repaint();

                JLabel bookNameLabel = new JLabel("Nombre del Libro:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(bookNameLabel, constraints);

                JTextField bookNameText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(bookNameText, constraints);


                JTextArea booksArea = new JTextArea(20, 50);
                booksArea.setText("");
                booksArea.setMaximumSize(new Dimension(400,400));
                JScrollPane scrollPane = new JScrollPane(booksArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                centerPanel.add(scrollPane);
                scrollPane.setVisible(false);

                JButton buscarLibroButton = new JButton("Buscar Libro");
                centerPanel.add(buscarLibroButton);



                buscarLibroButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scrollPane.setVisible(true);
                        scrollPane.setMaximumSize(new Dimension(400,400));
                        scrollPane.repaint();

                        centerPanel.remove(bookNameText);
                        centerPanel.remove(buscarLibroButton);
                        centerPanel.remove(bookNameLabel);


                        centerPanel.validate();
                        centerPanel.repaint();

                        String bookName = bookNameText.getText();
                        Edicion encontrada = b.buscarEdicion(bookName);

                        if (encontrada!=null && encontrada instanceof Libro)
                        {

                            booksArea.append(encontrada.toString());

                            JButton confirmarButton = new JButton("Confirmar Seleccion");
                            centerPanel.add(confirmarButton);

                            // Acción para el botón "Confirmar Seleccion"
                            confirmarButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    if (encontrada.getStock()!=0)
                                    {
                                        frame.dispose();
                                        GUI.finalizarPrestamo(b, (Libro) encontrada);
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(null, "No se posee stock del libro ingresado");
                                    }

                                }
                            });
                            centerPanel.validate();
                            centerPanel.repaint();
                        }
                        if (encontrada==null)
                        {
                            booksArea.append("El libro solicitado no se encuentra en nuestro inventario");

                        }
                    }
                });
            }
        });

        // Acción para el botón "devuelvePrestamo"
        devuelvePrestamoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();


                LinkedList<Prestamo> prestamos = b.getPrestamos();

                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
                centerPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                JPanel bookContainerPanel = new JPanel(new BorderLayout());
                bookContainerPanel.setLayout(new BoxLayout(bookContainerPanel, BoxLayout.Y_AXIS));

                for (Prestamo dato : prestamos) {

                    if (dato.getFechaDevolucion()==null)
                    {

                        bookContainerPanel.add(crearPrestamoPanel(b,dato));
                        bookContainerPanel.add(Box.createRigidArea(new Dimension(0, 25)));

                    }
                }

                JScrollPane scrollPane = new JScrollPane(bookContainerPanel);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                // Añadir el JScrollPane al marco principal
                centerPanel.add(scrollPane);


                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        // Acción para el botón "RegresarButton"
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.menuLogeado(b);
            }
        });
        // Acción para el botón "Cerrar Sesión"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.logeo(b); // Volver a la pantalla de inicio de sesión
            }
        });

        frame.setVisible(true);
        Utilidades.escribir(b);
    }

    private static JPanel crearPrestamoPanel(BibliotecaAdmin b, Prestamo p) {
        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        JLabel fechaLabel = new JLabel("Fecha de prestamo: " + p.getFechaInicio());
        JLabel dniLabel = new JLabel("DNI Socio: " + p.getDniSocio());
        JLabel libroLabel = new JLabel("Libro prestado: ");
        JLabel tituloLabel = new JLabel("Titulo: " + p.getLibro().getTitulo());
        JLabel autorLabel = new JLabel("Autor: " + p.getLibro().getAutor());
        JButton selectButton = new JButton("Seleccionar");

        // Acción al pulsar el botón
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.generaDevolucion(p);
                JOptionPane.showMessageDialog(null, "Se ha devuelto: " + p.toString());
            }
        });

        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(fechaLabel);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(dniLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(libroLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(tituloLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(autorLabel,BorderLayout.WEST);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.add(selectButton);
        bookPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return bookPanel;
    }

    public static void finalizarPrestamo (BibliotecaAdmin b,Libro encontrada) {
        JFrame frame = new JFrame("Biblioteca - Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear y añadir los componentes al panel superior (navegación)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        panel.add(topPanel, BorderLayout.NORTH);

        JPanel botPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Cerrar Sesión");


        botPanel.add(logoutButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        // Crear el panel central para mostrar el contenido dinámico
        JPanel centerPanel = new JPanel();

        ImageIcon logo = new ImageIcon("Iconos/LibreriaLogo.png");
        JLabel imgLabel = new JLabel(logo);
        centerPanel.add(imgLabel);

        panel.add(centerPanel, BorderLayout.CENTER);


        // Añadir el panel principal al marco
        frame.add(panel);


        JLabel dniLabel = new JLabel("DNI del socio:");

        topPanel.add(dniLabel);

        JTextField dniText = new JTextField(20);
        topPanel.add(dniText);


        JButton prestarButton = new JButton("Prestar");
        topPanel.add(prestarButton);
        JButton regresarButton = new JButton("Regresar");
        topPanel.add(regresarButton);




        // Acción para el botón "Prestar"
        prestarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Persona p = b.buscarMiembro(dniText.getText());

                if (p != null && p instanceof Socio)
                {
                    if (p.isActivo())
                    {
                        boolean prestado = b.generaPrestamo(encontrada,(Socio)p);
                        if (prestado)
                        {
                            JOptionPane.showMessageDialog(null, "Prestamo generado con exito.");
                            frame.dispose();
                            GUI.menuPrestamos(b);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error: El socio ha alcanzado su maximo de prestamos. Realice una devolucion.");
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error: El socio se encuentra inactivo.");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Error: No existe ningun socio con ese DNI.");
                }
            }
        });

// Acción para el botón "RegresarButton"
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.menuLogeado(b);
            }
        });

        // Acción para el botón "Cerrar Sesión"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.logeo(b); // Volver a la pantalla de inicio de sesión
            }
        });

        frame.setVisible(true);
        Utilidades.escribir(b);
    }

    public static void menuMiembros(BibliotecaAdmin b) {
// Crear el marco (JFrame) principal
        JFrame frame = new JFrame("Biblioteca - Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ImageIcon logo = new ImageIcon("Iconos/LibreriaLogo.png");
        JLabel imgLabel = new JLabel(logo);
        // Crear y añadir los componentes al panel superior (navegación)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton viewMembersButton = new JButton("Ver Miembros");
        JButton registrarSocioButton = new JButton("Registrar Socio");
        JButton registrarClienteButton = new JButton("Registrar Cliente");
        JButton modificarSocioButton = new JButton("Modificar Socio");
        JButton modificarClienteButton = new JButton("Modificar Cliente");
        JButton regresar = new JButton("Regresar");
        JButton searchMemberButton = new JButton("Buscar");

        topPanel.add(viewMembersButton);
        topPanel.add(searchMemberButton);
        topPanel.add(registrarSocioButton);
        topPanel.add(registrarClienteButton);
        topPanel.add(modificarSocioButton);
        topPanel.add(modificarClienteButton);
        topPanel.add(regresar);

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel botPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Cerrar Sesión");


        botPanel.add(logoutButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        // Crear el panel central para mostrar el contenido dinámico
        JPanel centerPanel = new JPanel();

        centerPanel.add(imgLabel);
        panel.add(centerPanel, BorderLayout.CENTER);

        // Añadir el panel principal al marco
        frame.add(panel);

        // Acción para el botón "Ver Miembros"
        viewMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                JTextArea miembrosArea = new JTextArea(20, 50);

                TreeMap<String,Persona> miembros = b.getMiembros();
                for (Map.Entry<String, Persona> entry : miembros.entrySet()) {
                    miembrosArea.append(entry.getValue().toString());
                }

                miembrosArea.setEditable(false);
                centerPanel.add(new JScrollPane(miembrosArea));
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        searchMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel dniMiembroLabel = new JLabel("DNI del miembro:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(dniMiembroLabel, constraints);

                JTextField dniMiembroText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(dniMiembroText, constraints);

                JButton buscarButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(buscarButton, constraints);

                centerPanel.revalidate();
                centerPanel.repaint();

                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        centerPanel.removeAll();
                        JTextArea personaArea = new JTextArea(20, 50);
                        personaArea.repaint();

                        String key = dniMiembroText.getText();
                        JScrollPane scrollPane = new JScrollPane(personaArea);
                        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                        centerPanel.add(scrollPane);

                        Persona encontrada = b.buscarMiembro(key);

                        centerPanel.revalidate();
                        centerPanel.repaint();
                        if (encontrada!=null)
                        {
                            personaArea.append(encontrada.toString());
                            if (encontrada instanceof Socio)
                            {
                                if (!((Socio) encontrada).getHistorialCompras().isEmpty())
                                {
                                    personaArea.append("Compras: \n");
                                    personaArea.append(((Socio) encontrada).getHistorialCompras().toString());
                                }
                                if (!((Socio) encontrada).getHistorialAlquilados().isEmpty())
                                {
                                    personaArea.append("Prestamos: \n");
                                    personaArea.append(((Socio) encontrada).getHistorialAlquilados().toString());
                                }


                            }
                            else
                            {
                                if (!((Cliente) encontrada).getHistorialCompras().isEmpty())
                                {
                                    personaArea.append("Compras: \n");
                                    personaArea.append(((Cliente) encontrada).getHistorialCompras().toString());
                                }

                            }
                        }
                        else
                        {
                            personaArea.append("No se ha encontrado al miembro solicitado");
                        }
                        centerPanel.revalidate();
                        centerPanel.repaint();
                    }
                });

            }


        });

        //Accion para el boton Registrar Socio
        registrarSocioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

                centerPanel.add(new JLabel("ID / DNI:"));
                JTextField txtIdDni = new JTextField();
                txtIdDni.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtIdDni);

                centerPanel.add(new JLabel("Apellido:"));
                JTextField txtApellido = new JTextField();
                txtApellido.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtApellido);

                centerPanel.add(new JLabel("Nombre:"));
                JTextField txtNombre = new JTextField();
                txtNombre.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtNombre);


                centerPanel.add(new JLabel("Edad:"));
                JTextField txtEdad = new JTextField();
                txtEdad.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtEdad);

                centerPanel.add(new JLabel("Teléfono:"));
                JTextField txtTel = new JTextField();
                txtTel.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtTel);

                centerPanel.add(new JLabel("Género:"));
                JComboBox cmbGenero = new JComboBox<>(GeneroPersona.values());
                cmbGenero.setMaximumSize(new Dimension(250,30));
                centerPanel.add(cmbGenero);

                centerPanel.add(new JLabel("Dirección:"));
                JTextField txtDireccion = new JTextField();
                txtDireccion.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtDireccion);

                JButton cargarButton = new JButton("Cargar");
                centerPanel.add(cargarButton);

                centerPanel.revalidate();
                centerPanel.repaint();

                cargarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int edad = 0;
                        try {
                            edad = Integer.parseInt(txtEdad.getText());
                            Validator.validarEdad(edad);
                            Validator.isNumericE(txtIdDni.getText());
                            Validator.isNumericE(txtTel.getText());
                            Validator.existe(txtApellido.getText());
                            Validator.existe(txtNombre.getText());
                            Validator.existe(txtDireccion.getText());
                            Validator.isAlfabeticoE(txtApellido.getText());
                            Validator.isAlfabeticoE(txtNombre.getText());
                        }catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Error: Ingrese un número entero válido para la edad.");
                        } catch (EdadNoDentrodeRangoPermitidoException ex) {
                            JOptionPane.showMessageDialog(null, "Error: Los miembros deben tener entre 13 y 100 años.");
                        } catch (StringNoNumericoException ex) {
                            JOptionPane.showMessageDialog(null, "Error: El DNI y el telefono solo permiten numeros.");
                        } catch (DatoIncompletoException ex) {
                            JOptionPane.showMessageDialog(null, "Error: Asegurese de completar todos los campos.");
                        } catch (StringNoAlfabeticoException ex) {
                            JOptionPane.showMessageDialog(null, "Error: El nombre y apellido solo permiten letras.");
                        }

                        if (Validator.validarEdadBool(edad) && Validator.isNumeric(txtIdDni.getText()) && Validator.isNumeric(txtIdDni.getText()) && !txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty() && !txtDireccion.getText().isEmpty() && Validator.isAlfabetico(txtApellido.getText()) && Validator.isAlfabetico(txtNombre.getText()))
                        {
                            Socio nuevoSocio = new Socio(txtIdDni.getText(),txtApellido.getText(),txtNombre.getText(),edad,txtTel.getText(),(GeneroPersona)cmbGenero.getSelectedItem(),txtDireccion.getText());



                            if (b.ifMiembroExist(txtIdDni.getText()))
                            {
                                JOptionPane.showMessageDialog(null, "Miembro ya existente");
                            }
                            else
                            {
                                b.addMiembros(nuevoSocio.getIdDni(),nuevoSocio);
                                JOptionPane.showMessageDialog(null, "Miembro cargado exitosamente");
                            }

                        }


                    }
                });

            }
        });

        registrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

                centerPanel.add(new JLabel("ID / DNI:"));
                JTextField txtIdDni = new JTextField();
                txtIdDni.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtIdDni);

                centerPanel.add(new JLabel("Apellido:"));
                JTextField txtApellido = new JTextField();
                txtApellido.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtApellido);

                centerPanel.add(new JLabel("Nombre:"));
                JTextField txtNombre = new JTextField();
                txtNombre.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtNombre);


                centerPanel.add(new JLabel("Edad:"));
                JTextField txtEdad = new JTextField();
                txtEdad.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtEdad);

                centerPanel.add(new JLabel("Teléfono:"));
                JTextField txtTel = new JTextField();
                txtTel.setMaximumSize(new Dimension(250,30));
                centerPanel.add(txtTel);

                centerPanel.add(new JLabel("Género:"));
                JComboBox cmbGenero = new JComboBox<>(GeneroPersona.values());
                cmbGenero.setMaximumSize(new Dimension(250,30));
                centerPanel.add(cmbGenero);

                JButton cargarButton = new JButton("Cargar");
                centerPanel.add(cargarButton);

                centerPanel.revalidate();
                centerPanel.repaint();

                cargarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int edad = 0;
                        try {
                            edad = Integer.parseInt(txtEdad.getText());
                            Validator.validarEdad(edad);
                            Validator.isNumericE(txtIdDni.getText());
                            Validator.isNumericE(txtTel.getText());
                            Validator.existe(txtApellido.getText());
                            Validator.existe(txtNombre.getText());
                            Validator.isAlfabeticoE(txtApellido.getText());
                            Validator.isAlfabeticoE(txtNombre.getText());
                        }catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Error: Ingrese un número entero válido para la edad.");
                        } catch (EdadNoDentrodeRangoPermitidoException ex) {
                            JOptionPane.showMessageDialog(null, "Error: Los miembros deben tener entre 13 y 100 años.");
                        } catch (StringNoNumericoException ex) {
                            JOptionPane.showMessageDialog(null, "Error: El DNI y el telefono solo permiten numeros.");
                        } catch (DatoIncompletoException ex) {
                            JOptionPane.showMessageDialog(null, "Error: Asegurese de completar todos los campos.");
                        } catch (StringNoAlfabeticoException ex) {
                            JOptionPane.showMessageDialog(null, "Error: El nombre y apellido solo aceptan letras.");
                        }

                        if (Validator.validarEdadBool(edad) && Validator.isNumeric(txtIdDni.getText()) && Validator.isNumeric(txtIdDni.getText()) && !txtNombre.getText().isEmpty() &&!txtApellido.getText().isEmpty() && Validator.isAlfabetico(txtApellido.getText()) && Validator.isAlfabetico(txtNombre.getText()))
                        {
                            Cliente nuevoCliente = new Cliente(txtIdDni.getText(),txtApellido.getText(),txtNombre.getText(),edad,txtTel.getText(),(GeneroPersona)cmbGenero.getSelectedItem());


                            if (b.ifMiembroExist(txtIdDni.getText()))
                            {
                                JOptionPane.showMessageDialog(null, "Miembro ya existente");
                            }
                            else
                            {
                                b.addMiembros(nuevoCliente.getIdDni(),nuevoCliente);
                                JOptionPane.showMessageDialog(null, "Miembro cargado exitosamente");
                            }
                        }


                    }
                });

            }
        });

        modificarSocioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel dniMiembroLabel = new JLabel("DNI del socio a modificar:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(dniMiembroLabel, constraints);

                JTextField dniMiembroText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(dniMiembroText, constraints);

                JButton buscarButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(buscarButton, constraints);


                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //centerPanel.removeAll();
                        JTextArea personaArea = new JTextArea(20, 50);
                        personaArea.repaint();

                        String key = dniMiembroText.getText();
                        //centerPanel.add(personaArea);

                        JButton confirmarButton = new JButton("Confirmar");
                        //centerPanel.add(confirmarButton);


                        Persona encontrada = b.buscarMiembro(key);

                        if (encontrada!=null && encontrada instanceof Socio)
                        {
                            centerPanel.removeAll();
                            centerPanel.add(personaArea);
                            centerPanel.add(confirmarButton);
                            personaArea.append(encontrada.toString());
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se ha encontrado al socio solicitado.");
                        }

                        // Acción para el botón "Confirmar"
                        confirmarButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                if (encontrada instanceof Socio)
                                {
                                centerPanel.removeAll();


                                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


                                centerPanel.add(new JLabel("Apellido:"));
                                JTextField txtApellido = new JTextField();
                                txtApellido.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(txtApellido);

                                centerPanel.add(new JLabel("Nombre:"));
                                JTextField txtNombre = new JTextField();
                                txtNombre.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(txtNombre);


                                centerPanel.add(new JLabel("Edad:"));
                                JTextField txtEdad = new JTextField();
                                txtEdad.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(txtEdad);

                                centerPanel.add(new JLabel("Teléfono:"));
                                JTextField txtTel = new JTextField();
                                txtTel.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(txtTel);

                                centerPanel.add(new JLabel("Direccion:"));
                                JTextField txtDireccion = new JTextField();
                                txtDireccion.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(txtDireccion);

                                centerPanel.add(new JLabel("Activo:"));
                                String[] options = {"No modificar","Activo", "Inactivo"};
                                JComboBox cmbActivo = new JComboBox<>(options);
                                cmbActivo.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(cmbActivo);

                                JButton modificarButton = new JButton("Modificar");
                                centerPanel.add(modificarButton);

                                // Acción para el botón "Modificar"
                                modificarButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        boolean flag1=false;
                                        boolean flag2=false;
                                        boolean flag3=false;
                                        boolean flag4=false;
                                            if (!txtApellido.getText().isEmpty())
                                            {
                                                try {
                                                    Validator.isAlfabeticoE(txtApellido.getText());
                                                    encontrada.setApellido(txtApellido.getText());
                                                    flag1 = true;
                                                } catch (StringNoAlfabeticoException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Apellido invalido. Solo se permiten letras");
                                                }
                                            }else{flag1=true;}
                                            if (!txtNombre.getText().isEmpty())
                                            {
                                                try {
                                                    Validator.isAlfabeticoE(txtNombre.getText());
                                                    encontrada.setNombre(txtNombre.getText());
                                                    flag2=true;
                                                } catch (StringNoAlfabeticoException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Nombre invalido. Solo se permiten letras");
                                                }
                                            }else{flag2=true;}
                                            if (!txtEdad.getText().isEmpty())
                                            {
                                                try {
                                                    int edad = Integer.parseInt(txtEdad.getText());
                                                    Validator.validarEdad(edad);
                                                    encontrada.setEdad(edad);
                                                    flag3=true;
                                                } catch (NumberFormatException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Edad Invalida. Solo se permiten numeros");
                                                } catch (EdadNoDentrodeRangoPermitidoException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Edad Invalida. Rango valido entre 13 y 100");
                                                }
                                            }else{flag3=true;}
                                            if (!txtTel.getText().isEmpty())
                                            {

                                                try {
                                                    Validator.isNumericE(txtTel.getText());
                                                    encontrada.setTel(txtTel.getText());
                                                    flag4=true;
                                                } catch (StringNoNumericoException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Telefono Invalido. Solo se permiten numeros");
                                                }
                                            }else{flag4=true;}
                                            if (cmbActivo.getSelectedItem().equals("Activo"))
                                            {
                                                encontrada.setActivo(true);
                                            }
                                            if (cmbActivo.getSelectedItem().equals("Inactivo"))
                                            {
                                                encontrada.setActivo(false);
                                            }
                                            if (!txtDireccion.getText().isEmpty())
                                            {


                                                ((Socio)encontrada).setDireccion(txtDireccion.getText());

                                            }

                                        if (flag1 && flag2 && flag3 && flag4)
                                        {
                                            JOptionPane.showMessageDialog(null, "El cliente ha sido modificado exitosamente.");
                                        }

                                    }
                                });

                                centerPanel.revalidate();
                                centerPanel.repaint();
                            } else {
                                JOptionPane.showMessageDialog(null, "Error. El DNI ingresado no corresponde a un socio");
                            }
                            }
                        });
                    }


                });

                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        modificarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.setLayout(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(10, 10, 10, 10);
                constraints.fill = GridBagConstraints.HORIZONTAL;

                JLabel dniMiembroLabel = new JLabel("DNI del cliente a modificar:");
                constraints.gridx = 0;
                constraints.gridy = 0;
                centerPanel.add(dniMiembroLabel, constraints);

                JTextField dniMiembroText = new JTextField(20);
                constraints.gridx = 1;
                centerPanel.add(dniMiembroText, constraints);

                JButton buscarButton = new JButton("Buscar");
                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.gridwidth = 2;
                constraints.anchor = GridBagConstraints.CENTER;
                centerPanel.add(buscarButton, constraints);


                buscarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        JTextArea personaArea = new JTextArea(20, 50);
                        personaArea.repaint();

                        String key = dniMiembroText.getText();

                        JButton confirmarButton = new JButton("Confirmar");

                        Persona encontrada = b.buscarMiembro(key);

                        if (encontrada!=null && encontrada instanceof Cliente)
                        {
                            centerPanel.removeAll();
                            centerPanel.add(personaArea);
                            centerPanel.add(confirmarButton);
                            personaArea.append(encontrada.toString());
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No se ha encontrado al cliente solicitado.");
                        }

                        // Acción para el botón "Confirmar"
                        confirmarButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (encontrada instanceof Cliente)
                                {

                                centerPanel.removeAll();


                                centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


                                centerPanel.add(new JLabel("Apellido:"));
                                JTextField txtApellido = new JTextField();
                                txtApellido.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(txtApellido);

                                centerPanel.add(new JLabel("Nombre:"));
                                JTextField txtNombre = new JTextField();
                                txtNombre.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(txtNombre);


                                centerPanel.add(new JLabel("Edad:"));
                                JTextField txtEdad = new JTextField();
                                txtEdad.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(txtEdad);

                                centerPanel.add(new JLabel("Teléfono:"));
                                JTextField txtTel = new JTextField();
                                txtTel.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(txtTel);

                                centerPanel.add(new JLabel("Activo:"));
                                String[] options = {"No modificar","Activo", "Inactivo"};
                                JComboBox cmbActivo = new JComboBox<>(options);
                                cmbActivo.setMaximumSize(new Dimension(250,30));
                                centerPanel.add(cmbActivo);

                                JButton modificarButton = new JButton("Modificar");
                                centerPanel.add(modificarButton);

                                // Acción para el botón "Modificar"
                                modificarButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        boolean flag1 = false;
                                        boolean flag2 = false;
                                        boolean flag3 = false;
                                        boolean flag4 = false;

                                            if (!txtApellido.getText().isEmpty())
                                            {
                                                try {
                                                    Validator.isAlfabeticoE(txtApellido.getText());
                                                    encontrada.setApellido(txtApellido.getText());
                                                    flag1=true;
                                                } catch (StringNoAlfabeticoException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Apellido invalido. Solo se permiten letras");
                                                }
                                            } else{flag1=true;}
                                            if (!txtNombre.getText().isEmpty())
                                            {
                                                try {
                                                    Validator.isAlfabeticoE(txtNombre.getText());
                                                    encontrada.setNombre(txtNombre.getText());
                                                    flag2=true;
                                                } catch (StringNoAlfabeticoException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Nombre invalido. Solo se permiten letras");
                                                }
                                            }else{flag2=true;}
                                            if (!txtEdad.getText().isEmpty())
                                            {

                                                try {
                                                    int edad = Integer.parseInt(txtEdad.getText());
                                                    Validator.validarEdad(edad);
                                                    encontrada.setEdad(edad);
                                                    flag3=true;
                                                } catch (NumberFormatException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Edad Invalida. Solo se permiten numeros");
                                                } catch (EdadNoDentrodeRangoPermitidoException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Edad Invalida. Rango valido entre 13 y 100");
                                                }
                                            }else{flag3=true;}
                                            if (!txtTel.getText().isEmpty())
                                            {

                                                try {
                                                    Validator.isNumericE(txtTel.getText());
                                                    encontrada.setTel(txtTel.getText());
                                                    flag4=true;
                                                } catch (StringNoNumericoException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error: Telefono Invalido. Solo se permiten numeros");
                                                }
                                            }else{flag4=true;}
                                            if (cmbActivo.getSelectedItem().equals("Activo"))
                                            {
                                                encontrada.setActivo(true);

                                            }
                                            if (cmbActivo.getSelectedItem().equals("Inactivo"))
                                            {
                                                encontrada.setActivo(false);

                                            }

                                            if (flag1 && flag2 && flag3 && flag4)
                                            {
                                                JOptionPane.showMessageDialog(null, "El cliente ha sido modificado exitosamente.");
                                            }
                                    }
                                });

                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Error. El DNI ingresado no corresponde a un Cliente");
                                }

                                centerPanel.revalidate();
                                centerPanel.repaint();
                            }
                        });
                    }



                });
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });


        // Acción para el botón "Regresar"
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.menuLogeado(b);
            }
        });

        // Acción para el botón "Cerrar Sesión"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.logeo(b); // Volver a la pantalla de inicio de sesión
            }
        });
        frame.setVisible(true);
        Utilidades.escribir(b);
    }

    public static void modificarUsuario(BibliotecaAdmin b)
    {

        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        ImageIcon logo = new ImageIcon("Iconos/LibreriaLogo.png");

        // Crear el marco (JFrame) principal
        JFrame frame = new JFrame("Biblioteca - Gestión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear y añadir los componentes al panel superior (navegación)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton regresar = new JButton("Regresar");

        topPanel.add(regresar);

        panel.add(topPanel, BorderLayout.NORTH);

        JPanel botPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton logoutButton = new JButton("Cerrar Sesión");


        botPanel.add(logoutButton);

        panel.add(botPanel, BorderLayout.SOUTH);

        // Crear el panel central para mostrar el contenido dinámico
        JPanel centerPanel = new JPanel();

        panel.add(centerPanel, BorderLayout.CENTER);

        // Añadir el panel principal al marco
        frame.add(panel);
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;


        JLabel usuarioLabel = new JLabel("Usuario:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(usuarioLabel, constraints);

        JTextField usuarioText = new JTextField(20);
        constraints.gridx = 1;
        centerPanel.add(usuarioText, constraints);

        JButton modificarUsuarioButton = new JButton("Modificar");
        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        centerPanel.add(modificarUsuarioButton, constraints);

        JLabel contraseniaLabel = new JLabel("Contraseña:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        centerPanel.add(contraseniaLabel, constraints);


        JPasswordField contraseniaText = new JPasswordField(20);
        constraints.gridx = 1;
        centerPanel.add(contraseniaText, constraints);

        JButton modificarContraseniaButton = new JButton("Modificar");
        constraints.gridx = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        centerPanel.add(modificarContraseniaButton, constraints);


        centerPanel.revalidate();
        centerPanel.repaint();

        // Acción para el botón "Modificar Usuario"
        modificarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuarioText.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Error. No ha ingresado un nuevo usuario.");
                }else{
                    b.setUsuario(usuarioText.getText());
                    JOptionPane.showMessageDialog(null, "Usuario modificado con exito.");
                    frame.dispose();
                    GUI.logeo(b);
                }
            }
        });

        // Acción para el botón "modificar Contrasenia"
        modificarContraseniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contraseniaText.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Error. No ha ingresado una nueva contraseña.");

                }else{
                    b.setContrasenia(contraseniaText.getText());
                    JOptionPane.showMessageDialog(null, "Contraseña modificada con exito.");
                    frame.dispose();
                    GUI.logeo(b);
                }
            }
        });


        // Acción para el botón "Regresar"
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.menuLogeado(b);
            }
        });

        // Acción para el botón "Cerrar Sesión"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GUI.logeo(b); // Volver a la pantalla de inicio de sesión
            }
        });

        frame.setVisible(true);
        Utilidades.escribir(b);
    }
    }
