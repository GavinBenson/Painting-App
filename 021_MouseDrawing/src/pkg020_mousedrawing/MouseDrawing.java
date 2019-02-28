/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg020_mousedrawing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BasicStroke;

/**
 *
 * @author compsci
 */
public class MouseDrawing extends JFrame {

    // menu
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    
    // canvas menu
    JMenu canvasMenu = new JMenu("Canvas");
    JMenuItem col0MenuItem = new JMenuItem("Black");
    JMenuItem col1MenuItem = new JMenuItem("Blue");
    JMenuItem col2MenuItem = new JMenuItem("Yellow");
    JMenuItem col3MenuItem = new JMenuItem("Pink");
    JMenuItem col4MenuItem = new JMenuItem("White");
    
    // pen menu
    JMenu penMenu = new JMenu("Pen");
    JMenuItem pen0MenuItem = new JMenuItem("Small");
    JMenuItem pen1MenuItem = new JMenuItem("Medium");
    JMenuItem pen2MenuItem = new JMenuItem("Large");
    
    //colorpanel
    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[16];
    JButton[] backgroundButtons = new JButton[8];
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new MouseDrawing().setVisible(true);
    }
    
    
    public MouseDrawing() {
        
        setTitle("Artistic Creations");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
               //g2D.dispose();
               exitForm(e);
           } 
        });
        getContentPane().setLayout(new GridBagLayout());
        
        // file menu
        setJMenuBar(mainMenuBar);
        fileMenu.setMnemonic('F');
        mainMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newMenuItemActionPerformed(e);
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitMenuItemActionPreformed(e);
            }
        });
        
        // canvas menu
        canvasMenu.setMnemonic('C');
        mainMenuBar.add(canvasMenu);
        canvasMenu.add(col0MenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(col1MenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(col2MenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(col3MenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(col4MenuItem);
        col0MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col0MenuItemActionPerformed(e);
            }
        });
        col1MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col1MenuItemActionPreformed(e);
            }
        });
        col2MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col2MenuItemActionPreformed(e);
            }
        });
        col3MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col3MenuItemActionPreformed(e);
            }
        });
        col4MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col4MenuItemActionPreformed(e);
            }
        });
        
        // pen menu
        penMenu.setMnemonic('P');
        mainMenuBar.add(penMenu);
        penMenu.add(pen0MenuItem);
        penMenu.addSeparator();
        penMenu.add(pen1MenuItem);
        penMenu.addSeparator();
        penMenu.add(pen2MenuItem);
        
        pen0MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pen0MenuItemActionPerformed(e);
            }
        });
        
        pen1MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pen1MenuItemActionPerformed(e);
            }
        });
        
        pen2MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pen2MenuItemActionPerformed(e);
            }
        });
        
        drawPanel.setPreferredSize(new Dimension(800,700));
        drawPanel.setBackground(Color.WHITE);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 2;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(drawPanel, gridConstraints);
        drawPanel.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e) {
                drawPanelMousePressed(e);
                System.out.println("Press");
            }
        });
        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                drawPanelMouseDragged(e);
            }
        });
        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mousereleased(MouseEvent e) {
                drawPanelMouseReleased(e);
            }
        });
        
        
        leftColorLabel.setPreferredSize(new Dimension(40,40));
        leftColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(leftColorLabel, gridConstraints);
        
        rightColorLabel.setPreferredSize(new Dimension(40,40));
        rightColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(rightColorLabel, gridConstraints);
        
               
        colorPanel.setPreferredSize(new Dimension(80,320));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(colorPanel, gridConstraints);

        
        
        colorPanel.setLayout(new GridBagLayout());
        int j = 0;
        for (int i = 0; i < 16; i++) {
            colorLabel[i] = new JLabel();
            colorLabel[i].setPreferredSize(new Dimension(30,30));
            colorLabel[i].setOpaque(true);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = j;
            gridConstraints.gridy = i - j * 8;
            colorPanel.add(colorLabel[i], gridConstraints);
            
            if (i == 7) {
                j++;
            }
            
            colorLabel[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    colorMousePressed(e);
                }
            });
            
        }
        
        Color r1 = new Color(255,102,102);
        Color r2 = new Color(255,0,0);
        Color g1 = new Color(0-153-0);
        Color g2 = new Color(0,255,0);
        Color b1 = new Color(51-204-255);
        Color b2 = new Color(0,0,255);
        Color y1 = new Color(255-255-153);
        Color y2 = new Color(255-255-0);
        Color bl1 = new Color(255,102,102);
        Color bl2 = new Color(255,102,102);
        Color gr1 = new Color(255,102,102);
        Color gr2 = new Color(255,102,102);
        Color p1 = new Color(255,102,102);
        Color p2 = new Color(255,102,102);
        Color o1 = new Color(255,102,102);
        Color o2 = new Color(255,102,102);
        
        //set color labels
        colorLabel[0].setBackground(r2);
        colorLabel[1].setBackground(g1);
        colorLabel[2].setBackground(g2);
        colorLabel[3].setBackground(r1);
        colorLabel[4].setBackground(r1);
        colorLabel[5].setBackground(b2);
        colorLabel[6].setBackground(y1);
        colorLabel[7].setBackground(r1);
        colorLabel[8].setBackground(r1);
        colorLabel[9].setBackground(g2);
        colorLabel[10].setBackground(gr1);
        colorLabel[11].setBackground(gr2);
        colorLabel[12].setBackground(p1);
        colorLabel[13].setBackground(p2);
        colorLabel[14].setBackground(o1);
        colorLabel[15].setBackground(o2);
        leftColor = colorLabel[0].getBackground();
        leftColorLabel.setBackground(leftColor);
        rightColor = colorLabel[7].getBackground();
        rightColorLabel.setBackground(rightColor);
        
        j = 0;
        for (int i = 0; i < 4; i++) {
            //TODO
        }
        
        
        pack();
        //Dimension screenSize = Toolkit.getDefaultToolKit().getScreenSize();
        //setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
        
        g2D = (Graphics2D) drawPanel.getGraphics();
        
        mainMenuBar.setBackground(Color.LIGHT_GRAY);
        this.setBackground(Color.DARK_GRAY);
        
    }
    int strokeSize = 1;
    private void newMenuItemActionPerformed(ActionEvent e) {
        int response;
        response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new drawing?", "New drawing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            g2D.setPaint(drawPanel.getBackground());
            g2D.fill (new Rectangle2D.Double(-4,-4,drawPanel.getWidth(),drawPanel.getHeight()));
        }
    }
    
    private void exitMenuItemActionPreformed(ActionEvent e) {
        int response;
        response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exist?", "Exist", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            return;
        } else {
            exitForm(null);
        }
    }
    
    private void col0MenuItemActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.BLACK);
    }
    
    private void col1MenuItemActionPreformed(ActionEvent e) {
        drawPanel.setBackground(Color.BLUE);
    }
    
    private void col2MenuItemActionPreformed(ActionEvent e) {
        drawPanel.setBackground(Color.YELLOW);
    }
    
    private void col3MenuItemActionPreformed(ActionEvent e) {
        drawPanel.setBackground(Color.PINK);
    }
    
    private void col4MenuItemActionPreformed(ActionEvent e) {
        drawPanel.setBackground(Color.WHITE);
    }
    
    
    private void pen0MenuItemActionPerformed(ActionEvent e) {
        strokeSize = 1;
    }
    
    public void pen1MenuItemActionPerformed(ActionEvent e) {
        strokeSize = 3;
    }
    
    public void pen2MenuItemActionPerformed(ActionEvent e) {
        strokeSize = 5;
    }
    
    private void colorMousePressed(MouseEvent e) {
        
        //which color and which button
        Component clickedColor = e.getComponent();
        
        //make audible tone and set color
        Toolkit.getDefaultToolkit().beep();
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftColor = clickedColor.getBackground();
            leftColorLabel.setBackground(leftColor);
            
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightColor = clickedColor.getBackground();
            rightColorLabel.setBackground(rightColor);
            
        }
    }
    
    private void drawPanelMousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
            xPrevious = e.getX();
            yPrevious = e.getY();
            if (e.getButton() == MouseEvent.BUTTON1) {
                drawColor = leftColor;
            } else {
                drawColor = rightColor;
            }
        }
        System.out.println("Mouse x, " + xPrevious + ". Mouse y, " + yPrevious + ".");
        System.out.println(strokeSize);
    }
    
    private void drawPanelMouseDragged(MouseEvent e) {
        Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
        g2D.setStroke(new BasicStroke(strokeSize));
        g2D.setPaint(drawColor);
        g2D.draw(myLine);
        xPrevious = e.getX();
        yPrevious = e.getY();
    }
    
    private void drawPanelMouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
            Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, getX(), getY());
            g2D.setPaint(drawColor);
            g2D.draw(myLine);
        }
    }
    
    private void exitForm(WindowEvent e) {
        g2D.dispose();
        System.exit(0);
    }
}

