package test;

import javax.swing.*;
import java.awt.event.*;

public class EventTest implements MouseListener, KeyListener {
    public EventTest() {
        JFrame jFrame=new JFrame("Button Click Test");
        jFrame.setSize(603,680);//设置界面大小
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton jButton=new JButton("Click me");
        jButton.setBounds(100,200,105,105);

        /*jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button has been clicked!");
            }
        });//使用了匿名内部类*/

        jButton.addMouseListener(this);
        jButton.addKeyListener(this);

        jFrame.getContentPane().add(jButton);

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        EventTest myTest=new EventTest();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse has been Clicked!");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse Released!");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered!");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Exited!");
    }


    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key Typed!");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released!");
    }
}
