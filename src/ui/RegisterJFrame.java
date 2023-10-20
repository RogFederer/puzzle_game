package ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterJFrame extends JFrame implements MouseListener {
    JButton register=new JButton();
    JButton reset=new JButton();
    JTextField userInput=new JTextField();
    JPasswordField passwordInput=new JPasswordField();
    JPasswordField passwordReInput=new JPasswordField();
    public RegisterJFrame(){
        //初始化界面
        initJFrame();
        //在界面进行输入
        initView();

        this.setVisible(true);//让界面显示出来
    }

    private void initView() {
        //添加用户名
        JLabel userText=new JLabel(new ImageIcon("puzzlegame\\image\\register\\注册用户名.png"));
        userText.setBounds(100,135,80,17);
        this.getContentPane().add(userText);
        //添加密码
        JLabel passwordText=new JLabel(new ImageIcon("puzzlegame\\image\\register\\注册密码.png"));
        passwordText.setBounds(100,195,64,16);
        this.getContentPane().add(passwordText);
        //添加用户名输入框

        userInput.setBounds(195,134,200,30);
        this.getContentPane().add(userInput);
        //添加密码输入框

        passwordInput.setBounds(195,195,200,30);
        this.getContentPane().add(passwordInput);
        //添加再次输入密码
        JLabel passwordReInputText=new JLabel(new ImageIcon("puzzlegame\\image\\register\\再次输入密码.png"));
        passwordReInputText.setBounds(90,255,96,17);
        this.getContentPane().add(passwordReInputText);
        //添加验证码输入框

        passwordReInput.setBounds(195,255,120,30);
        this.getContentPane().add(passwordReInput);
        //添加登录按钮

        register.setBounds(123,310,128,47);
        register.setIcon(new ImageIcon("puzzlegame\\image\\register\\注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        register.addMouseListener(this);

        this.getContentPane().add(register);

        //添加注册按钮，与添加登录按钮类似

        reset.setBounds(256,310,128,47);
        reset.setIcon(new ImageIcon("puzzlegame\\image\\register\\重置按钮.png"));
        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        reset.addMouseListener(this);

        this.getContentPane().add(reset);




        JLabel jLabel=new JLabel(new ImageIcon("puzzlegame\\image\\register\\background.png"));
        jLabel.setBounds(0,0,470,390);
        this.getContentPane().add(jLabel);
    }

    private void initJFrame() {
        this.setTitle("拼图 注册");
        this.setSize(488,430);//设置界面大小
        this.setAlwaysOnTop(true);//设置界面置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭模式
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj=e.getSource();
        if(obj==register){
            String username=userInput.getText();
            String password=passwordInput.getText();
            String repassword=passwordReInput.getText();
            if(!password.equals(repassword)){
                System.out.println("两次输入密码不一致，请重新输入");
                showJDialog("两次输入密码不一致，请重新输入");
            }
            else{

            }
        }
    }

    private void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj=e.getSource();
        if(obj==reset){

            reset.setIcon(new ImageIcon("puzzlegame\\image\\register\\重置按下.png"));

        } else if(obj==register){

            register.setIcon(new ImageIcon("puzzlegame\\image\\register\\注册按下.png"));

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj=e.getSource();
        if(obj==reset){

            reset.setIcon(new ImageIcon("puzzlegame\\image\\register\\重置按钮.png"));

        } else if(obj==register){

            register.setIcon(new ImageIcon("puzzlegame\\image\\register\\注册按钮.png"));

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
