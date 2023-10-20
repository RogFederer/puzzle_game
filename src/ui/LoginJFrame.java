package ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class LoginJFrame extends JFrame implements MouseListener {
    JButton register=new JButton();
    JButton login=new JButton();
    JTextField userInput=new JTextField();
    JPasswordField passwordInput=new JPasswordField();
    JTextField authInput=new JTextField();
    JLabel auth=new JLabel();
    StringBuilder authentification=new StringBuilder();
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

        userInput.setBounds(195,134,200,30);
        this.getContentPane().add(userInput);
        //添加密码输入框

        passwordInput.setBounds(195,195,200,30);
        this.getContentPane().add(passwordInput);
        //添加验证码
        JLabel authText=new JLabel(new ImageIcon("puzzlegame\\image\\login\\验证码.png"));
        authText.setBounds(116,255,56,21);
        this.getContentPane().add(authText);
        //添加验证码输入框

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

        //添加验证码界面
        StringBuilder first_auth=authGen();
        this.authentification=first_auth;
        auth.setText(String.valueOf(first_auth));
        auth.setBounds(330,255,60,30);
        //给验证码界面绑定鼠标事件
        auth.addMouseListener(this);

        this.getContentPane().add(auth);


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
        Object obj=e.getSource();
        if(obj==auth){
            //采用随机数生成验证码，验证码为一个长度为5的包含数字和大小写字母的字符串
            System.out.println(this.authentification);
            System.out.println("刷新验证码");
            StringBuilder nextAuth=authGen();
            auth.setText(String.valueOf(nextAuth));
            this.authentification=nextAuth;
            System.out.println(this.authentification);
        } else if (obj==login) {
            System.out.println("点击登录按钮");
            String user=this.userInput.getText();
            String password=this.passwordInput.getText();
            String auth=this.authInput.getText();
            if(password.isEmpty() || user.isEmpty()){
                System.out.println("用户名或密码不能为空");
                showJDialog("用户名或密码不能为空");
            }
            if(auth.isEmpty()){
                System.out.println("验证码不能为空");
                showJDialog("验证码不能为空");
            }
            //判断用户名和密码是否匹配
            boolean userExist=false;
            for (User u:userList
                 ) {
                if(u.getUsername().equals(user)){
                    userExist=true;
                    if(u.getPassword().equals(password)){
                        System.out.println("密码正确");
                        if(auth.equals(String.valueOf(this.authentification))){
                            System.out.println("验证码也正确");
                            //登录信息均正确，进入游戏界面
                            this.setVisible(false);
                            new GameJFrame();
                        }
                        else{
                            System.out.println("验证码错误");
                            showJDialog("验证码错误，请重试");
                        }
                    }
                    else{
                        System.out.println("密码错误");
                        showJDialog("密码错误，请重试");
                    }
                }

            }
            if(!userExist){
                System.out.println("用户名不存在");
                showJDialog("用户名不存在，请重新输入");
            }


        } else if (obj==register) {
            //进入注册界面
            new RegisterJFrame();
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

    private StringBuilder authGen() {
        StringBuilder newAuth=new StringBuilder();
        Random r=new Random();
        while(newAuth.length()<5){
            int n=r.nextInt(75)+48;
            while(!((n>=97&&n<=122)||(n>=65&&n<=90)||(n>=48&&n<=57)))
                n=r.nextInt(75)+48;
            char c= (char) n;
            newAuth.append(c);
        }


        return newAuth;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj=e.getSource();
        if(obj==login){

            login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按下.png"));

        } else if(obj==register){

            register.setIcon(new ImageIcon("puzzlegame\\image\\login\\注册按下.png"));

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj=e.getSource();
        if(obj==login){

            login.setIcon(new ImageIcon("puzzlegame\\image\\login\\登录按钮.png"));
            this.getContentPane().repaint();
        } else if(obj==register){

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
