package ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame(){
        this.setTitle("拼图 注册");
        this.setSize(488,500);//设置界面大小
        this.setAlwaysOnTop(true);//设置界面置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式

        this.setVisible(true);//让界面显示出来
    }
}
