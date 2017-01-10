package com.chess.ui;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class Constants {
    public static String USER = "user";
    public static String GROUP = "group";
    public static String SUCCESS = "success";
    public static String FAILURE = "failure";
    public static String YES = "yes";
    public static String NO = "no";

    /** 空格代码 */
    public static String SPACE = "\u0008";
    /** 换行代码 */
    public static String NEWLINE = "\n";
    /** 左斜杠 */
    public static String LEFT_SLASH = "/";

    public static String SEARCH_TXT = "搜索：联系人、群组";
    public static String DEFAULT_CATE = "我的好友";
    public static String NONAME_CATE = "未命名";

    // 微软雅黑
    public static Font BASIC_FONT = new Font("微软雅黑", Font.PLAIN, 12);
    public static Font BASIC_FONT2 = new Font("微软雅黑", Font.TYPE1_FONT, 12);
    // 楷体
    public static Font DIALOG_FONT = new Font("楷体", Font.PLAIN, 16);

    public static Border GRAY_BORDER = BorderFactory.createLineBorder(Color.GRAY);
    public static Border ORANGE_BORDER = BorderFactory.createLineBorder(Color.ORANGE);
    public static Border LIGHT_GRAY_BORDER = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

    public static int NO_OPTION = 1;
    public static int YES_OPTION = 0;
}
