package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //创建一个二维数组
    //目的：用来管理数据
    int[][] data=new int[4][4];

    int [][] win={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    //x,y记录空白方块在数组中的位置
    int x=0;
    int y=0;

    //定义变量，用来统计步数
    int step=0;

    String pic_path="puzzlegame\\image\\girl\\girl3\\";

    //创建选项下面的条目对象，因为后面的方法要调用getSource获取条目对象，因此这里需要把条目信息都放在成员变量的位置
    JMenuItem replayItem=new JMenuItem("重新游戏");
    JMenuItem reloginItem=new JMenuItem("重新登录");
    JMenuItem closeItem=new JMenuItem("关闭游戏");

    JMenuItem accountItem=new JMenuItem("公众号");

    JMenuItem personItem=new JMenuItem("美女");
    JMenuItem animalItem=new JMenuItem("动物");
    JMenuItem sportItem=new JMenuItem("运动");
    public GameJFrame(){
        //初始化界面
        initJFrame();

        
        //初始化菜单(共包含三个组件：JMenuBar, JMenu, JMenuItem)
        initJMenuBar();

        //初始化数据（将图片排列的顺序打乱）
        initData();

        //初始化图片
        initImage();


        this.setVisible(true);//让界面显示出来
    }

    private void initData() {
        int[] one_data= {0, 1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14,15};
        Random r=new Random();
        for(int i=0;i<one_data.length;i++){
            int index=r.nextInt(one_data.length);
            int temp=one_data[index];
            one_data[index]=one_data[i];
            one_data[i]=temp;
        }
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++){
                int index=4*i+j;
                if(one_data[index]==0){
                    x=i;
                    y=j;
                }
                data[i][j]=one_data[index];

            }
        }
    }

    private void initImage() {
        //首先清除界面已有的图片
        this.getContentPane().removeAll();

        //放置计步器的位置
        JLabel stepCount=new JLabel("步数："+this.step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        if(this.victory()){
            JLabel jLabel=new JLabel(new ImageIcon("D:\\java_test\\new_test_code\\puzzlegame\\image\\win.png"));
            jLabel.setBounds(203,283,197,73);
            this.getContentPane().add(jLabel);
        }

        for (int i = 0; i < 4; i++) {
            for(int j=0;j<4;j++){


                //使用了相对路径，相对路径是从当前项目的文件夹出发的
                String path=pic_path+data[i][j]+".jpg";
                JLabel jLabel=new JLabel(new ImageIcon(path));
                jLabel.setBounds(j*105+83,i*105+134,105,105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);

            }
        }

        //添加背景图片
        JLabel background=new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
        background.setBounds(40,40,508,560);
        //把背景图片添加到界面中
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }


    private void initJMenuBar() {
        JMenuBar jMenuBar=new JMenuBar();//创建整个菜单对象
        //创建菜单上面两个选项的对象（功能，关于我们）
        JMenu functionjMenu=new JMenu("功能");
        JMenu aboutjMenu=new JMenu("关于我们");

        JMenu changeMenu=new JMenu("更换图片");

        //将美女、动物、运动三个条目加入到更换图片菜单中
        changeMenu.add(personItem);
        changeMenu.add(animalItem);
        changeMenu.add(sportItem);
        //在菜单的功能一栏中，加入更换图片的二级菜单
        functionjMenu.add(changeMenu);

        //将每一个选项下的条目（Item）添加到选项当中
        functionjMenu.add(replayItem);
        functionjMenu.add(reloginItem);
        functionjMenu.add(closeItem);

        aboutjMenu.add(accountItem);

        //给各个条目绑定事件
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);

        accountItem.addActionListener(this);

        personItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);

        //将菜单的两个选项添加到菜单中
        jMenuBar.add(functionjMenu);
        jMenuBar.add(aboutjMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setTitle("拼图单机版 v1.0");
        this.setSize(603,680);//设置界面大小
        this.setAlwaysOnTop(true);//设置界面置顶
        this.setLocationRelativeTo(null);//设置界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式

        //取消默认的居中放置，只有取消后，才会按照XY坐标的位置放置组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }
    public boolean victory(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++)
            {
                if(this.data[i][j]!=this.win[i][j])
                    return false;
            }
        }
        return true;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    //实现按住A键不松时，显示完整图片
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==65){
            //删除界面现有的图片
            this.getContentPane().removeAll();
            //加载完整图片
            JLabel all=new JLabel(new ImageIcon(pic_path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            //添加背景图片
            JLabel background=new JLabel(new ImageIcon("puzzlegame\\image\\background.png"));
            background.setBounds(40,40,508,560);
            //把背景图片添加到界面中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();

        }
    }

    @Override
    //实现上下左右方向键对拼图进行操作
    public void keyReleased(KeyEvent e) {
        //如果胜利，则直接结束，不再进行键盘操作
        if(this.victory())
            return;

        
        int code=e.getKeyCode();
        if(code==37){
            System.out.println("Left");
            if(this.y==3)
                return;
            int tmp_x=this.x;
            int tmp_y=this.y;
            int temp=this.data[tmp_x][tmp_y+1];
            this.y++;
            this.data[tmp_x][tmp_y+1]=0;
            this.data[tmp_x][tmp_y]=temp;

            this.step++;
            //最后重新加载图片
            this.initImage();
        } else if (code==38) {
            System.out.println("Up");
            if(this.x==3)
                return;
            int tmp_x=this.x;
            int tmp_y=this.y;
            int temp=this.data[tmp_x+1][tmp_y];
            this.x++;
            this.data[tmp_x+1][tmp_y]=0;
            this.data[tmp_x][tmp_y]=temp;

            this.step++;
            //最后重新加载图片
            this.initImage();
        } else if (code == 39) {
            System.out.println("Right");
            if(this.y==0)
                return;
            int tmp_x=this.x;
            int tmp_y=this.y;
            int temp=this.data[tmp_x][tmp_y-1];
            this.y--;
            this.data[tmp_x][tmp_y-1]=0;
            this.data[tmp_x][tmp_y]=temp;

            this.step++;
            //最后重新加载图片
            this.initImage();
        } else if (code == 40) {
            System.out.println("Down");
            if(this.x==0)
                return;
            int tmp_x=this.x;
            int tmp_y=this.y;
            int temp=this.data[tmp_x-1][tmp_y];
            this.x--;
            this.data[tmp_x-1][tmp_y]=0;
            this.data[tmp_x][tmp_y]=temp;

            this.step++;
            //最后重新加载图片
            this.initImage();
        } else if (code == 65) {
            this.initImage();//按住A键松开后，需要重新加载图片
        } else if (code == 87) {
            data=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            this.x=3;
            this.y=3;
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj=e.getSource();
        if(obj==this.replayItem){
            System.out.println("重新开始");
            //计步器清零
            this.step=0;
            //再次打乱图片
            this.initData();
            //重新加载图片
            this.initImage();
        } else if (obj == this.reloginItem) {
            System.out.println("重新登录");

            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();
        } else if (obj == this.closeItem) {
            System.out.println("关闭界面");
            //直接关闭虚拟机
            System.exit(0);
        } else if (obj==this.accountItem){
            System.out.println("关注公众号");

            //创建一个弹窗对象
            JDialog jDialog=new JDialog();
            //创建一个管理图片的容器对象
            JLabel jLabel=new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
            //设置位置和宽高
            jLabel.setBounds(0,0,258,258);
            //把图片加入弹框中
            jDialog.getContentPane().add(jLabel);
            //设置弹框大小
            jDialog.setSize(344,344);
            //设置弹框置顶
            jDialog.setAlwaysOnTop(true);
            //设置弹框居中
            jDialog.setLocationRelativeTo(null);
            //让弹框不关闭则无法点击下面的页面
            jDialog.setModal(true);

            jDialog.setVisible(true);
        } else if (obj == personItem) {
            System.out.println("美女");
            //需要随机切换一个路径
            Random r=new Random();
            int num= r.nextInt(11)+1;
            this.pic_path="puzzlegame\\image\\girl\\girl"+num+"\\";
            //将计步器清零，打乱图片，加载图片
            this.step=0;
            this.initData();
            this.initImage();

        } else if (obj == animalItem) {
            System.out.println("动物");
            //切换路径
            Random r=new Random();
            int num= r.nextInt(8)+1;
            this.pic_path="puzzlegame\\image\\animal\\animal"+num+"\\";
            //将计步器清零，打乱图片，加载图片
            this.step=0;
            this.initData();
            this.initImage();
        } else if (obj == sportItem) {
            System.out.println("运动");
            //切换路径
            Random r=new Random();
            int num= r.nextInt(10)+1;
            this.pic_path="puzzlegame\\image\\sport\\sport"+num+"\\";
            //将计步器清零，打乱图片，加载图片
            this.step=0;
            this.initData();
            this.initImage();
        }
    }
}
