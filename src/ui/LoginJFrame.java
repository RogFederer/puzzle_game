package ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {
    JButton register=new JButton();
    JButton login=new JButton();
    static ArrayList<User> userList=new ArrayList<>();
    static {
        userList.add(new User("zhangsan","123456"));
        userList.add(new User("zhaowu","654321"));
    }
    public LoginJFrame() {
        //界面初始化
        initJFrame();
        //在界面中输入内容
        initView();

        this.setVisible(true);//让界面显示出来
    }

    private void initView() {
        //添加用户名
        JLabel userText=new JLabel(new ImageIcon("puzzlegame\\image\\login\\用户名.png"));
        userText.setBounds(116,135,47,17);
        this.getContentPane().add(userText);
        //添加密码
        JLabel passwordText=new JLabel(new ImageIcon("puzzlegame\\image\\login\\密码.png"));
        passwordText.setBounds(130,195,32,16);
        this.getContentPane().add(passwordText);
        //添加用户名输入框
        JTextField userInput=new JTextField();
        userInput.setBounds(195,134,200,30);
        this.getContentPane().add(userInput);
        //添加密码输入框
        JPasswordField passwordInput=new JPasswordField();
        passwordInput.setBounds(195,195,200,30);
        this.getContentPane().add(passwordInput);
        //添加验证码
        JLabel authText=new JLabel(new ImageIcon("puzzlegame\\image\\login\\验证码.png"));
        authText.setBounds(116,255,56,21);
        this.getContentPane().add(authText);
        //添加验证码输入框
        JTextField authInput=new JTextField();
        authInput.setBounds(195,255,120,30);
        this.getContentPane().add(authInput);
        //添加登录按钮

        login.setBounds(123,310,128,47);
        login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        login.addMouseListener(this);

        this.getContentPane().add(login);

        //添加注册按钮，与添加登录按钮类似

        register.setBounds(256,310,128,47);
        register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);

        this.getContentPane().add(register);




        JLabel jLabel=new JLabel(new ImageIcon("puzzlegame\\image\\login\\background.png"));
        jLabel.setBounds(0,0,470,390);
        this.getContentPane().add(jLabel);
    }

    private void initJFrame() {
        this.setTitle("拼图 登录");
        this.setSize(488,430);//设置界面大小
        this.setAlwaysOnTop(true);//设置界面置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj=e.getSource();
        if(obj==login){
            System.out.println("登录按下");
            login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按下.png"));

        } else if(obj==register){
            System.out.println("注册按下");
            register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按下.png"));

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj=e.getSource();
        if(obj==login){
            System.out.println("登录按钮");
            login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按钮.png"));
            this.getContentPane().repaint();
        } else if(obj==register){
            System.out.println("注册按钮");
            register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按钮.png"));
            //this.getContentPane().repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
