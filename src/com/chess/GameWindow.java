package com.chess;

import com.chess.game.*;
import com.chess.game.Point;
import com.chess.ui.CloseActionListener;
import com.chess.ui.MinActionListener;
import com.chess.ui.MouseDragListener;
import com.chess.ui.MyButton;
import com.chess.ui.MyOptionPane;
import com.chess.util.ChessSave;
import com.chess.util.MP3Player;
import com.chess.util.ResourceUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 五子棋游戏主窗口
 */
public class GameWindow extends JFrame {
    private static final long serialVersionUID = -7023551768027623265L;

    private JPanel panel;


    private static final int x0 = 23, y0 = 48;//第一个点的坐标
    private static final int dis = 35;//两个点的距离
    private static final int floor = 16;//误差
    private boolean black;//玩家是黑棋还是白棋
    private int diff;

    private IChessboard chessBoard;
    private IPlayer aiPlayer;
    private IPlayer human;
    private boolean isWin;  //是否已经有人赢了


    private BufferedImage image;
    private JPopupMenu menu;    //右键菜单

    private MP3Player player;


    public GameWindow(boolean black, int diff) {
        this.black = black;
        this.diff = diff;

        this.player = new MP3Player(ResourceUtil.getStream("chess.mp3"));

        setUndecorated(true);
        setResizable(false);
        setTitle("五子棋Chess");
        setSize(530, 560);

        image = (BufferedImage) ResourceUtil.getImage("chess.jpg");
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            }

        };

        JButton btn_min = new MyButton();
        btn_min.setBounds(480, 4, 22, 22);
        btn_min.addActionListener(new MinActionListener(this));
        panel.add(btn_min);

        JButton btn_close = new MyButton();
        btn_close.setBounds(510, 5, 22, 22);
        btn_close.addActionListener(new CloseActionListener(this));
        panel.add(btn_close);

        JButton btn_back = new MyButton();
        btn_back.setBounds(450, 3, 22, 22);
        btn_back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON1){
                    menu.show(e.getComponent(),e.getX(),e.getY());
                }
            }
        });
        panel.add(btn_back);

        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new MouseAdapter() {

            private int x15 = x0 + 15 * dis;
            private int y15 = y0 + 15 * dis;

            @Override
            public void mouseReleased(MouseEvent e) {   //下棋落子，根据x和y坐标，得到点击的是第几个棋子
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX(), y = e.getY();
                    if ((x >= x0 - floor) && (x <= x15 + floor) && (y >= y0 - floor) && (y <= y15 + floor)) {
                        int p = -1, q = -1;
                        for (int i = 0; i < 15; i++) {
                            int xi = x0 + dis * i;
                            if (x >= xi - floor && x <= xi + floor) {
                                p = i;
                                break;
                            }
                        }
                        for (int i = 0; i < 15; i++) {
                            int yi = y0 + dis * i;
                            if (y >= yi - floor && y <= yi + floor) {
                                q = i;
                                break;
                            }
                        }
                        if (p >= 0 && q >= 0) {
                            onChessClick(p, q);
                        }
                    }
                }
            }
        });

        menu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("重新开始");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backPoint();
            }
        });
        menu.add(item1);
        menu.addSeparator();
        JMenuItem item2 = new JMenuItem("保存棋谱");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser() {
                    @Override
                    public void approveSelection() {
                        File savedFile = getSelectedFile();
                        if (savedFile.exists()) {
                            if (MyOptionPane.showConfirmDialog(GameWindow.this, "询问", "文件已存在，是否覆盖？", "是", "否") == JOptionPane.OK_OPTION) {
                                super.approveSelection();
                            }
                        } else {
                            super.approveSelection();
                        }
                    }
                };
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileFilter fileFilter = new FileNameExtensionFilter("棋谱文件（.chess）", "chess");
                chooser.setFileFilter(fileFilter);
                chooser.setMultiSelectionEnabled(false);
                if (chooser.showSaveDialog(GameWindow.this) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    String fname = chooser.getName(file);
                    if (!fname.contains(".chess")) {
                        file = new File(chooser.getCurrentDirectory(), fname = fname + ".chess");
                    }
                    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                        ChessSave chessSave = new ChessSave(human, aiPlayer, black, isWin);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(chessSave);
                        MyOptionPane.showMessageDialog(GameWindow.this, "成功保存棋谱到" + fname, "提示");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        MyOptionPane.showMessageDialog(GameWindow.this, "保存棋谱失败！", "错误");
                    }
                }
            }
        });
        menu.add(item2);
        JMenuItem item3 = new JMenuItem("加载棋谱");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                FileFilter fileFilter = new FileNameExtensionFilter("棋谱文件（.chess）", "chess");
                chooser.setFileFilter(fileFilter);
                chooser.setMultiSelectionEnabled(false);
                if (chooser.showOpenDialog(GameWindow.this) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try (FileInputStream fileInputStream = new FileInputStream(file)) {
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        ChessSave chessSave = (ChessSave) objectInputStream.readObject();
                        loadChessSave(chessSave);
                        MyOptionPane.showMessageDialog(GameWindow.this, "加载棋谱成功！", "提示");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        MyOptionPane.showMessageDialog(GameWindow.this, "加载棋谱失败！", "错误");
                    }
                }
            }
        });
        menu.add(item3);
        menu.addSeparator();
        JMenuItem item4 = new JMenuItem("悔棋");
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (human.getMyPoints().isEmpty() || aiPlayer.getMyPoints().isEmpty()) {
                    MyOptionPane.showMessageDialog(GameWindow.this,"现在不能悔棋!","提示");
                    return;
                }
                if (MyOptionPane.showConfirmDialog(GameWindow.this, "询问", "你是否要悔棋？", "是", "否") == JOptionPane.OK_OPTION) {

                    Point p1 = human.getMyPoints().remove(human.getMyPoints().size() - 1);
                    Point p2 = aiPlayer.getMyPoints().remove(aiPlayer.getMyPoints().size() - 1);
                    chessBoard.getFreePoints().add(p1);
                    chessBoard.getFreePoints().add(p2);
                    repaintPanel();
                    if(isWin){
                        isWin = false;
                    }
                }

            }
        });
        menu.add(item4);
        JMenuItem menu5 = new JMenuItem("获取源码...");

        menu5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler https://github.com/xcr1234/chess");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        menu.addSeparator();
        menu.add(menu5);

        panel.setLayout(new BorderLayout());
        panel.addMouseMotionListener(new MouseDragListener(panel, this));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        startGame();
        getContentPane().add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


    }

    private static final Image BLACK = ResourceUtil.getImage("black.gif");
    private static final Image WHITE = ResourceUtil.getImage("white.gif");

    private void clear() {
        image = (BufferedImage) ResourceUtil.getImage("chess.jpg");
        startGame();
    }

    public void loadChessSave(ChessSave chessSave) {
        this.human = chessSave.getHumanPlayer();
        this.aiPlayer = chessSave.getBaseComputerAi();
        this.chessBoard = chessSave.getChessBoard();
        this.black = chessSave.isBlack();
        this.isWin = chessSave.isIswin();
        repaintPanel();
    }

    private void repaintPanel() {
        image = (BufferedImage) ResourceUtil.getImage("chess.jpg");
        for (Point point : human.getMyPoints()) {
            chessBoard.getFreePoints().remove(point);
            drawImage(image, point.getX(), point.getY(), black);
        }
        for (Point point : aiPlayer.getMyPoints()) {
            chessBoard.getFreePoints().remove(point);
            drawImage(image, point.getX(), point.getY(), !black);
        }
        panel.repaint();
    }


    private void backPoint() {

        if (MyOptionPane.showConfirmDialog(this, "重新开始", "你是否要重新开始？", "是", "否") == JOptionPane.OK_OPTION) {
            clear();
        }
    }

    public void startGame() {

        panel.repaint();

        isWin = false;

        chessBoard = new ChessBoard();
        human = new HumanPlayer();
        aiPlayer = new BaseComputerAi();
        human.setChessboard(chessBoard);
        aiPlayer.setChessboard(chessBoard);

        if (!black) {     //我方是白棋，电脑第一个棋子一定下在正中心
            drawPoint(7, 7, true);
            Point point = new Point(7, 7);
            aiPlayer.getMyPoints().add(point);
            chessBoard.getFreePoints().remove(point);
        }


    }

    private void onChessClick(int x, int y) {
        //以左上角第一个点为（0,0）右下角最后一个点为（15，15）；当鼠标点击其中一个点时，触发该事件。
        if (isWin) {
            return;
        }
        Point point = new Point(x, y);
        if (chessBoard.getFreePoints().contains(point)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    player.play();
                }
            }).start(); //异步播放落子音效，否则会阻塞主线程的执行
            human.run(aiPlayer.getMyPoints(), point);
            drawPoint(x, y, black);
            if (checkWin(true)) {
                return;
            }
            aiPlayer.run(human.getMyPoints(), null);
            Point aiPoint = aiPlayer.getMyPoints().get(aiPlayer.getMyPoints().size() - 1);
            drawPoint(aiPoint.getX(), aiPoint.getY(), !black);
            checkWin(false);
        }

    }


    /**
     * 落子
     *
     * @param x                    0-15，横坐标
     * @param y                    0-15，纵坐标
     * @param black，true黑棋；false白旗
     */
    private void drawPoint(int x, int y, boolean black) {
        drawImage(image, x, y, black);
        panel.repaint();
    }

    private void drawImage(BufferedImage image, int x, int y, boolean black) {
        int px = x0 + dis * x - 20;
        int py = y0 + dis * y - 20;
        Graphics2D g = image.createGraphics();
        if (black) {
            g.drawImage(BLACK, px, py, 40, 40, null);
        } else {
            g.drawImage(WHITE, px, py, 40, 40, null);
        }
        g.dispose();
    }

    private final MP3Player WIN = new MP3Player(ResourceUtil.getStream("win.mp3"));
    private final MP3Player DEFEAT = new MP3Player(ResourceUtil.getStream("defeat.mp3"));


    private boolean checkWin(boolean player) {
        if (player && human.hasWin()) {
            isWin = true;
            WIN.playSync();
            if (MyOptionPane.showConfirmDialog(this, "比赛结束—获胜", "你赢了！是否继续？", "是", "否") == JOptionPane.OK_OPTION) {
                clear();
            }
            return true;
        }

        if (!player && aiPlayer.hasWin()) {
            isWin = true;
            DEFEAT.playSync();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (MyOptionPane.showConfirmDialog(this, "比赛结束—战败", "你输了！是否继续？", "是", "否") == JOptionPane.OK_OPTION) {
                clear();
            }
            return true;
        }

        if (chessBoard.getFreePoints().isEmpty()) {
            isWin = true;
            if (MyOptionPane.showConfirmDialog(this, "比赛结束", "和棋！是否继续？", "是", "否") == JOptionPane.OK_OPTION) {
                clear();
            }
            return true;
        }

        return false;
    }


}
