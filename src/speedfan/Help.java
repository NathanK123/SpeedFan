/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedfan;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Nathan
 */
public class Help extends javax.swing.JFrame {

    private Home home = null;
    private Settings settings = null;
    boolean settingsOpened = false;
    
    JScrollPane[] helpPagesPt1 = new JScrollPane[9];
    JScrollPane[] helpPagesPt2 = new JScrollPane[6];
    JScrollPane[] helpPagesPt3 = new JScrollPane[4];
    JScrollPane[] helpPagesPt4 = new JScrollPane[1];
    
    String[] keyWordsHelp1 = {"what","is","speed","fan", "speedfan"};
    String[] keyWordsHelp2 = {"first", "look"};
    String[] keyWordsHelp3 = {"my","position","on","cpu","cooling","and","fan", "speed", "speeds"};
    String[] keyWordsHelp4 = {"logging", "facility"};
    String[] keyWordsHelp5 = {"how", "to", "download", "a", "configuration"};
    String[] keyWordsHelp6 = {"how", "to", "send", "a", "report", "send", "report", "how"};
    String[] keyWordsHelp7 = {"how","to","debug","the","smbus", "debugging"};
    String[] keyWordsHelp8 = {"how","to","set","advanced","options", "option"};
    String[] keyWordsHelp9 = {"how","to","configure","e-mail","support", "email"}; 
    String[] keyWordsHelp10 = {"main","tab"};
    String[] keyWordsHelp11 = {"tab", "configure"};
    String[] keyWordsHelp12 = {"debugging","tab", "debug"};
    String[] keyWordsHelp13 = {"diagnostics","tab", "diagnostics", "diagnostic", "diagnose"};
    String[] keyWordsHelp14 = {"charts","tab", "chart", "charts"};
    String[] keyWordsHelp15 = {"tab", "clock"};
    String[] keyWordsHelp16 = {"configuring","temperature", "temperatures", "configure"};
    String[] keyWordsHelp17 = {"fans", "fan", "configure", "configuring"};
    String[] keyWordsHelp18 = {"configuring","speed","fan" , "configure"};
    String[] keyWordsHelp19 = {"configuring","voltage" , "configure", "configuring"};
    String[] keyWordsHelp20 = {"profile","profiles", "beginner", "advanced", "expert"};
    
    
    private static int BLUE = 0;
    private static int RED = 1;
    private static int GREEN = 2;
    private static int YELLOW = 3; 
    private static int ORANGE = 4;
    private static int DARK = 5;
    private static int PURPLE = 6;
    private int colorState;
    private int oldColorState;
    private int tempColorState;
    
    //Darkest to lightest
    private static Color[] BLUE_ARRAY = {new Color(0, 102, 204), new Color(0, 153, 255), new Color(0, 0, 102), new Color(153,204,255)}; 
    private static Color[] RED_ARRAY = {new Color(204,0,0), new Color(255, 0, 0), new Color(255, 102, 102), new Color(255, 102, 102)};
    private static Color[] GREEN_ARRAY = {new Color(0,102,0), new Color(0,204,0), new Color(102, 255, 102), new Color(102, 255, 102)};
    private static Color[] YELLOW_ARRAY = {new Color(255,204,0), new Color(255, 255, 0), new Color(255, 255, 204), new Color(255, 255, 204)};
    private static Color[] ORANGE_ARRAY = {new Color(255,102,0), new Color(255, 204, 51), new Color(255, 153, 0), new Color(255, 153, 0)};
    private static Color[] DARK_ARRAY = {new Color(51,51,51), new Color(102,102, 102), new Color(204, 204, 204), new Color(204, 204, 204)};
    private static Color[] PURPLE_ARRAY = {new Color(102,0,153), new Color(218, 112, 214), new Color(216, 191, 216), new Color(216, 191, 216)};
    
    
    /**
     * Creates new form Help
     */
    public Help() {
        initComponents();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        helpListPt1.setCellRenderer(new MyListCell());
        helpListPt2.setCellRenderer(new MyListCell());
        helpListPt3.setCellRenderer(new MyListCell());
        helpListPt4.setCellRenderer(new MyListCell());
        searchList.setCellRenderer(new MyListCell());
        
        helpPagesPt1[0] = helpPage1;
        helpPagesPt1[1] = helpPage2;
        helpPagesPt1[2] = helpPage3;
        helpPagesPt1[3] = helpPage4;
        helpPagesPt1[4] = helpPage5;
        helpPagesPt1[5] = helpPage6;
        helpPagesPt1[6] = helpPage7;
        helpPagesPt1[7] = helpPage8;
        helpPagesPt1[8] = helpPage9;
        
        helpPagesPt2[0] = helpPage10;
        helpPagesPt2[1] = helpPage11;
        helpPagesPt2[2] = helpPage12;
        helpPagesPt2[3] = helpPage13;
        helpPagesPt2[4] = helpPage14;
        helpPagesPt2[5] = helpPage15;
        
        helpPagesPt3[0] = helpPage16;
        helpPagesPt3[1] = helpPage17;
        helpPagesPt3[2] = helpPage18;
        helpPagesPt3[3] = helpPage19;
        
        helpPagesPt4[0] = helpPage20;
        
        helpListPt1.setVisible(false);
        helpListPt2.setVisible(false);
        helpListPt3.setVisible(false);
        helpListPt4.setVisible(false);
        
        
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Images/New Logo.png")));
        
        this.setSize(955, 515);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        toolBar = new javax.swing.JPanel();
        settingsButton = new javax.swing.JLabel();
        printButton = new javax.swing.JLabel();
        helpListBorder = new javax.swing.JPanel();
        helpTabs = new javax.swing.JTabbedPane();
        helpListTab = new javax.swing.JPanel();
        contentsScrollPAne = new javax.swing.JScrollPane();
        contentsBorder = new javax.swing.JPanel();
        introductionComboButton = new javax.swing.JToggleButton();
        helpListPt1 = new javax.swing.JList<>();
        configureComboButton = new javax.swing.JToggleButton();
        tabsComboButton = new javax.swing.JToggleButton();
        profilesComboButton = new javax.swing.JToggleButton();
        helpListPt2 = new javax.swing.JList<>();
        helpListPt3 = new javax.swing.JList<>();
        helpListPt4 = new javax.swing.JList<>();
        searchTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        searchList = new javax.swing.JList<>();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        helpPageBorder = new javax.swing.JPanel();
        helpPage1 = new javax.swing.JScrollPane();
        help1 = new javax.swing.JPanel();
        help1Title = new java.awt.Label();
        help1Pic = new javax.swing.JLabel();
        help1Warning = new javax.swing.JLabel();
        help1Para1 = new javax.swing.JLabel();
        help1Para2 = new javax.swing.JLabel();
        help1Para3 = new javax.swing.JLabel();
        helpPage2 = new javax.swing.JScrollPane();
        help2 = new javax.swing.JPanel();
        help2Title = new java.awt.Label();
        help2Pic1 = new javax.swing.JLabel();
        help2Para1 = new javax.swing.JLabel();
        help2Para2 = new javax.swing.JLabel();
        helpPage3 = new javax.swing.JScrollPane();
        help3 = new javax.swing.JPanel();
        help3Title = new javax.swing.JLabel();
        help3Para1 = new javax.swing.JLabel();
        helpPage4 = new javax.swing.JScrollPane();
        help4 = new javax.swing.JPanel();
        help4Title = new java.awt.Label();
        help4Para1 = new javax.swing.JLabel();
        helpPage5 = new javax.swing.JScrollPane();
        help5 = new javax.swing.JPanel();
        help5Title = new javax.swing.JLabel();
        help5Pic = new javax.swing.JLabel();
        help5Pic2 = new javax.swing.JLabel();
        help5Para4 = new javax.swing.JLabel();
        help5Pic3 = new javax.swing.JLabel();
        help5Para5 = new javax.swing.JLabel();
        help5Pic4 = new javax.swing.JLabel();
        help5Para6 = new javax.swing.JLabel();
        help5Para62 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        help5Para7 = new javax.swing.JLabel();
        help5Pic5 = new javax.swing.JLabel();
        help5Para8 = new javax.swing.JLabel();
        help5Para82 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        help5Para1 = new javax.swing.JLabel();
        help5Para2 = new javax.swing.JLabel();
        help5Para3 = new javax.swing.JLabel();
        helpPage6 = new javax.swing.JScrollPane();
        help6 = new javax.swing.JPanel();
        help6Title = new javax.swing.JLabel();
        help6SubTitle1 = new javax.swing.JLabel();
        help6SubTitle2 = new javax.swing.JLabel();
        help6SubTitle3 = new javax.swing.JLabel();
        help6Para1 = new javax.swing.JLabel();
        help6Para2 = new javax.swing.JLabel();
        help6Para3 = new javax.swing.JLabel();
        helpPage7 = new javax.swing.JScrollPane();
        help7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        help7SubTitle1 = new javax.swing.JLabel();
        help7SubTitle2 = new javax.swing.JLabel();
        help7Para1 = new javax.swing.JLabel();
        help7Para2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        helpPage8 = new javax.swing.JScrollPane();
        help8 = new javax.swing.JPanel();
        help8Title = new javax.swing.JLabel();
        help8Para1 = new javax.swing.JLabel();
        help8Para2 = new javax.swing.JLabel();
        help8Para3 = new javax.swing.JLabel();
        help8Pic1 = new javax.swing.JLabel();
        helpPage9 = new javax.swing.JScrollPane();
        help9 = new javax.swing.JPanel();
        help9Title = new java.awt.Label();
        help9Para1 = new javax.swing.JLabel();
        helpPage10 = new javax.swing.JScrollPane();
        help10 = new javax.swing.JPanel();
        help10Title = new javax.swing.JLabel();
        help10Para1 = new javax.swing.JLabel();
        helpPage11 = new javax.swing.JScrollPane();
        help11 = new javax.swing.JPanel();
        help11Title = new java.awt.Label();
        help11Para1 = new javax.swing.JLabel();
        help11Pic1 = new javax.swing.JLabel();
        help11Para2 = new javax.swing.JLabel();
        helpPage12 = new javax.swing.JScrollPane();
        help12 = new javax.swing.JPanel();
        help12Title = new java.awt.Label();
        help12Para1 = new javax.swing.JLabel();
        helpPage13 = new javax.swing.JScrollPane();
        hep13 = new javax.swing.JPanel();
        help13Title = new java.awt.Label();
        help13Para1 = new javax.swing.JLabel();
        help13Para2 = new javax.swing.JLabel();
        help13Para3 = new javax.swing.JLabel();
        helpPage14 = new javax.swing.JScrollPane();
        help14 = new javax.swing.JPanel();
        help14Title = new java.awt.Label();
        help14Para1 = new javax.swing.JLabel();
        help14Para2 = new javax.swing.JLabel();
        helpPage16 = new javax.swing.JScrollPane();
        help16 = new javax.swing.JPanel();
        help16Title = new java.awt.Label();
        help16Para1 = new javax.swing.JLabel();
        help16Para2 = new javax.swing.JLabel();
        help16Para3 = new javax.swing.JLabel();
        help16Para4 = new javax.swing.JLabel();
        help16Para5 = new javax.swing.JLabel();
        help16Para6 = new javax.swing.JLabel();
        help16Para7 = new javax.swing.JLabel();
        help16Para8 = new javax.swing.JLabel();
        helpPage17 = new javax.swing.JScrollPane();
        help17 = new javax.swing.JPanel();
        help17Title = new java.awt.Label();
        help17Para1 = new javax.swing.JLabel();
        help17Para2 = new javax.swing.JLabel();
        help17Para3 = new javax.swing.JLabel();
        helpPage18 = new javax.swing.JScrollPane();
        help18 = new javax.swing.JPanel();
        help18Title = new java.awt.Label();
        help18Para1 = new javax.swing.JLabel();
        help18Para2 = new javax.swing.JLabel();
        help18Para3 = new javax.swing.JLabel();
        help18Para4 = new javax.swing.JLabel();
        helpPage19 = new javax.swing.JScrollPane();
        help19 = new javax.swing.JPanel();
        help19Title = new java.awt.Label();
        help19Para1 = new javax.swing.JLabel();
        help19Para2 = new javax.swing.JLabel();
        help19Para3 = new javax.swing.JLabel();
        helpPage20 = new javax.swing.JScrollPane();
        help20 = new javax.swing.JPanel();
        help20Title = new java.awt.Label();
        help20Para1 = new javax.swing.JLabel();
        help20Para2 = new javax.swing.JLabel();
        helpPage15 = new javax.swing.JScrollPane();
        help15 = new javax.swing.JPanel();
        help5Title1 = new javax.swing.JLabel();
        help15Para1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        toolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        settingsButton.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Settings icon.png")); // NOI18N
        settingsButton.setToolTipText("Settings");
        settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsButtonMouseExited(evt);
            }
        });

        printButton.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Print icon.png")); // NOI18N
        printButton.setToolTipText("Print");
        printButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout toolBarLayout = new javax.swing.GroupLayout(toolBar);
        toolBar.setLayout(toolBarLayout);
        toolBarLayout.setHorizontalGroup(
            toolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolBarLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(settingsButton)
                .addGap(30, 30, 30)
                .addComponent(printButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        toolBarLayout.setVerticalGroup(
            toolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(settingsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
            .addComponent(printButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        helpListBorder.setBackground(new java.awt.Color(0, 102, 204));
        helpListBorder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        contentsScrollPAne.setBackground(new java.awt.Color(255, 255, 255));

        contentsBorder.setBackground(new java.awt.Color(255, 255, 255));

        introductionComboButton.setText("Introduction");
        introductionComboButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                introductionComboButtonMouseClicked(evt);
            }
        });
        introductionComboButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                introductionComboButtonActionPerformed(evt);
            }
        });

        helpListPt1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "1. What is SpeedFan", "2. First Look", "<html>3. My Position on CPU cooling <br>and Fan Speeds</html>", "4. Logging Facility", "<html>5. How to Download a <br>Configuration</html>", "6. How to SEND a REPORT", "7. How to debug the SMBus", "8. How to Set Advanced Options", "9. How to Configure E-mail Support" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        helpListPt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpListPt1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpListPt1MouseEntered(evt);
            }
        });

        configureComboButton.setText("How to Configure");
        configureComboButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                configureComboButtonMouseClicked(evt);
            }
        });

        tabsComboButton.setText("About Tabs");
        tabsComboButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsComboButtonMouseClicked(evt);
            }
        });
        tabsComboButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tabsComboButtonActionPerformed(evt);
            }
        });

        profilesComboButton.setText("Profiles");
        profilesComboButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilesComboButtonMouseClicked(evt);
            }
        });

        helpListPt2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "10. Main", "11. Configure", "12. Debugging", "13. Diagnostics", "14. Charts", "15. Clock" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        helpListPt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpListPt2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpListPt2MouseEntered(evt);
            }
        });

        helpListPt3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "16. Temperature", "17. Fans", "18. Speeds", "19. Voltage" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        helpListPt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpListPt3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpListPt3MouseEntered(evt);
            }
        });

        helpListPt4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "20. Profiles" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        helpListPt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpListPt4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpListPt4MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout contentsBorderLayout = new javax.swing.GroupLayout(contentsBorder);
        contentsBorder.setLayout(contentsBorderLayout);
        contentsBorderLayout.setHorizontalGroup(
            contentsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(configureComboButton, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
            .addComponent(helpListPt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(helpListPt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(helpListPt4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabsComboButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(introductionComboButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(profilesComboButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(helpListPt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentsBorderLayout.setVerticalGroup(
            contentsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentsBorderLayout.createSequentialGroup()
                .addComponent(introductionComboButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(helpListPt1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tabsComboButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(helpListPt2)
                .addGap(0, 0, 0)
                .addComponent(configureComboButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(helpListPt3)
                .addGap(0, 0, 0)
                .addComponent(profilesComboButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(helpListPt4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        contentsScrollPAne.setViewportView(contentsBorder);

        javax.swing.GroupLayout helpListTabLayout = new javax.swing.GroupLayout(helpListTab);
        helpListTab.setLayout(helpListTabLayout);
        helpListTabLayout.setHorizontalGroup(
            helpListTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpListTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentsScrollPAne)
                .addContainerGap())
        );
        helpListTabLayout.setVerticalGroup(
            helpListTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpListTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentsScrollPAne, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
        );

        helpTabs.addTab("Contents", helpListTab);

        searchTab.setToolTipText("");

        searchList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(searchList);

        searchButton.setText("Search");
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout searchTabLayout = new javax.swing.GroupLayout(searchTab);
        searchTab.setLayout(searchTabLayout);
        searchTabLayout.setHorizontalGroup(
            searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(searchTabLayout.createSequentialGroup()
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        searchTabLayout.setVerticalGroup(
            searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchTextField)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        helpTabs.addTab("Search", searchTab);

        javax.swing.GroupLayout helpListBorderLayout = new javax.swing.GroupLayout(helpListBorder);
        helpListBorder.setLayout(helpListBorderLayout);
        helpListBorderLayout.setHorizontalGroup(
            helpListBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpListBorderLayout.createSequentialGroup()
                .addComponent(helpTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        helpListBorderLayout.setVerticalGroup(
            helpListBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpListBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpPageBorder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        helpPageBorder.setLayout(new java.awt.CardLayout());

        helpPage1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        help1.setBackground(new java.awt.Color(255, 255, 255));

        help1Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help1Title.setText("Welcome to SpeedFan");

        help1Pic.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\SpeedFan Icon.png")); // NOI18N

        help1Warning.setFont(new java.awt.Font("Gill Sans MT", 1, 11)); // NOI18N
        help1Warning.setForeground(new java.awt.Color(255, 0, 0));
        help1Warning.setText("WARNING");

        help1Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help1Para1.setText("<html>SpeedFan is a freeware program that monitors \n<br>voltages, fan speeds and temperatures in computers \n<br>with hardware monitor chips. SpeedFan can even \n<br>access S.M.A.R.T. info  for those hard disks that \n<br>support this feature and show  hard disk temperatures \n<br>too, if supported. SpeedFan supports  SCSI disks too. \n<br>SpeedFan can even change the FSB on some  hardware \n<br>(but this should be considered a bonus feature).  At the \n<br>lowest level, SpeedFan is an hardware monitor  \n<br>software that can access temperature sensors, but its \n<br>main  feature is that it can change fan speeds \n<br>(depending on the capabilities of your sensor chip and \n<br>your hardware) according  to the temperatures inside \n<br>your pc, thus reducing noise and  power consumption.</html>");

        help1Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help1Para2.setText("<html>SpeedFan works fine with Win9x, WinME, WinNT, Win2k and WinXP. SpeedFan can \n<br>be minimized to the tray and does its best to be compatible with other hardware \n<br>monitoring softwares. </html>");

        help1Para3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help1Para3.setText("<html>Please note that SpeedFan is an extremely powerful tool. When first installed, it is \n<br>configured only as an hardware monitoring tool. This means it won't try to change the \n<br>speed of your fans. Changing the FSB is the other operation that should be done \n<br>with care. Anyway: changing the FSB should cause no harm to your system but a \n<br>hang up :-) Anyway: this is an advanced option too and you should try it only if \n<br>you know what you're doing.</html>");

        javax.swing.GroupLayout help1Layout = new javax.swing.GroupLayout(help1);
        help1.setLayout(help1Layout);
        help1Layout.setHorizontalGroup(
            help1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help1Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help1Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help1Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(help1Layout.createSequentialGroup()
                        .addComponent(help1Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(help1Pic))
                    .addComponent(help1Warning))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help1Layout.setVerticalGroup(
            help1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help1Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(help1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help1Para1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, help1Layout.createSequentialGroup()
                        .addComponent(help1Pic)
                        .addGap(48, 48, 48)))
                .addComponent(help1Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(help1Warning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help1Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        helpPage1.setViewportView(help1);

        helpPageBorder.add(helpPage1, "card2");

        helpPage2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        help2.setBackground(new java.awt.Color(255, 255, 255));

        help2Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help2Title.setText("How to configure SpeedFan.");

        help2Pic1.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help2ScreenShot.png")); // NOI18N
        help2Pic1.setText("\n");

        help2Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help2Para1.setText("<html>I have got an ABIT BP6. Its SMBus is supported by SpeedFan and it has got a WINBOND  \n<br>W83782D hardware monitoring chip. This W83782D, like several others from Winbond,  \n<br>shows two additional sensors that appear as two LM75. On my motherboard, the  \n<br>W83782D is both accessible through ISA and SMBus. In my configuration I’ve chosen to  \n<br>access it only through SMBus even though this is not the most relialable setting. ISA is,  \n<br>in fact, more stable, but I’ve chosen this setup for debugging purposes.  \n<br>\n<br>So, let's start.   \n<br>\n<br>This is SpeedFan right after the very first run.</html> ");

        help2Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help2Para2.setText("<html>Those flames are not actually a problem. They are due to the fact that SpeedFan \n<br>has been initially configured (by me) to expect lower values. Temperatures have \n<br>been sorted trying to put at the top those that make more sense.</html>");

        javax.swing.GroupLayout help2Layout = new javax.swing.GroupLayout(help2);
        help2.setLayout(help2Layout);
        help2Layout.setHorizontalGroup(
            help2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help2Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help2Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help2Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help2Pic1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help2Layout.setVerticalGroup(
            help2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help2Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help2Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(help2Pic1)
                .addGap(27, 27, 27)
                .addComponent(help2Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1781, Short.MAX_VALUE))
        );

        helpPage2.setViewportView(help2);

        helpPageBorder.add(helpPage2, "card2");

        helpPage3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        help3.setBackground(new java.awt.Color(255, 255, 255));

        help3Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help3Title.setText("My Position on CPU cooling and Fan Speeds");

        help3Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help3Para1.setText("<html>A lot of users are worried about fan speed changing. I agree with them, but not completely. <br>Like every other operation involving a PC and its hardware, not everybody should do it. I mean:  <br>A lot of users are worried about fan speed changing. I agree with them, but not completely. <br>Like every other operation involving a PC and its hardware, not everybody should do it. I mean:  <br>even if I've got some knowledge about my car, I do not spend my time hacking it :-) <br> <br>Anyway: changing fan speeds can't be considered that harmful, because every hardware  <br>manufacturer should have taken into account the chance that a fan will ever fail and most of us  <br>have got a PC with a fan that's no longer running since a long time.  <br>Personally, I'm much more afraid of those heatsinks that require Hulk to set them up, and when  <br>you're done you hardly realize that all that force is held by a couple of really small plastic pieces  <br>that might easily break. What would happen then? <br> <br>Have you ever considered the weight of a performance heatsink? Have you ever considered  <br>what happens if you move your PC for a lan party? A friend of mine broke his CPU  <br>transporting his PC this way.  <br>One final note goes to those temperature sensors built into recent CPUs. Well... if you  <br>search the web you'll find a really interesting movie where a PIII, an Athlon and a P4 are  <br>filmed while the heatsink is removed: the PIII simply hangs, the Athlon lovely burns with  <br>the motherboard itself and the P4 simply slows down working perfectly and resuming normal  <br>clock speed when the heatsink is restored in place.  <br>A second issue is about <r>the chance that a fan will break after having been speed changed. <br> <br>Well... that might happen, but, according to my experience, the chance is very low (0% for  <br>me :-)).  <br>If it was that likely to happen (that a fan could break), why did almost every hardware  <br>monitoring chip manufacturer add that option? And why did motherboard manufacturers  <br>include the circuitry to support it? :-)</html> ");

        javax.swing.GroupLayout help3Layout = new javax.swing.GroupLayout(help3);
        help3.setLayout(help3Layout);
        help3Layout.setHorizontalGroup(
            help3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help3Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help3Title))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help3Layout.setVerticalGroup(
            help3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help3Title)
                .addGap(18, 18, 18)
                .addComponent(help3Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpPage3.setViewportView(help3);

        helpPageBorder.add(helpPage3, "card4");

        helpPage4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        help4.setBackground(new java.awt.Color(255, 255, 255));

        help4Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help4Title.setText("Logging Facility");

        help4Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help4Para1.setText("<html>SpeedFan has full logging capabilities. <br>The options you can set are: <br> <br> <br>Enabled: This option completely enables or disables logging <br> <br>Add header to file: When enabled, a new log file is started with the  <br>\t        names of the fields on the first row. <br> <br>Decimal separator: This option is originally set according to your  <br>\t        international settings, but you may change it to  <br>\t        whatever you prefer. <br>  <br>Max decimals: The logging facility tries to save space on disk. If you  <br>\tset this option to, say, 5, you will never get more than  <br>\t5 decimal digits, but SpeedFan will remove least  <br>\tsignificant ones if they are zeroes. <br> <br>Days to keep: SpeedFan will keep at most this number of days worth  <br>                       of log files. Please note that this is not necessarily the  <br>                       maximum number of log files, as SpeedFan can create  <br>                       more than one log file per day. <br> <br>File format: Log files contain values written in text format using the  <br>                    specified decimal separator. Values are separated by TAB.  <br>                    The first row in the file contains the names of the columns  <br>                    if the add header to file option has been selected. The first  <br>                    number in the row is the number of seconds since midnight. <br> <br>Naming: Log files are named SFLogYYYYMMDD.csv, where YYYY is the  <br>               year (four digits), MM is the month (2 digits, zero padded) and  <br>               DD is the day (2 digits, zero padded). If a file already exists by  <br>               that name, the file that already exists is renamed according to  <br>               the following naming scheme: SFLogYYYYMMDD-CCCC.csv,  <br>               where CCCC is a increasing number. The new file is then  <br>               created with the standard file name scheme. <br> <br> <br>Notes: Whenever you change the options related with logging, SpeedFan starts a new log file. <br> <br>Once you set the global options for logging, you have to choose what to log. Say that you want  <br>to log TEMP1 and TEMP3. Go to the TEMPERATURE tab, then select TEMP1. At the bottom of  <br>the window you will find a checkbox named LOGGED. Check it and TEMP1 will be logged to the  <br>log file. Then select TEMP3 and do the same. That's all. <br> <br> <br>File format: Log files contain values written in text format using the specified decimal  <br>                    separator. Values are separated by TAB. The first row in the file contains  <br>                    the names of the columns if the add header to file option has been  <br>                    selected. The first number in the row is the number of seconds since midnight. <br> <br>Naming: Log files are named SFLogYYYYMMDD.csv, where YYYY is the year (four digits),  <br>               MM is the month (2 digits, zero padded) and DD is the day (2 digits, zero padded).  <br>               If a file already exists by that name, the file that already exists is renamed  <br>               according to the following naming scheme: SFLogYYYYMMDD-CCCC.csv, where  <br>               CCCC is a increasing number. The new file is then created with the standard file  <br>               name scheme. <br> <br>Notes: Whenever you change the options related with logging, SpeedFan starts a new log  <br>            file. <br> <br>Once you set the global options for logging, you have to choose what to log. Say that you  <br>want to log TEMP1 and TEMP3. Go to the TEMPERATURE tab, then select TEMP1. At the  <br>bottom of the window you will find a checkbox named LOGGED. Check it and TEMP1 will be  <br>logged to the log file. Then select TEMP3 and do the same. That's all.</html> ");

        javax.swing.GroupLayout help4Layout = new javax.swing.GroupLayout(help4);
        help4.setLayout(help4Layout);
        help4Layout.setHorizontalGroup(
            help4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help4Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help4Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help4Layout.setVerticalGroup(
            help4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help4Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help4Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1342, Short.MAX_VALUE))
        );

        helpPage4.setViewportView(help4);

        helpPageBorder.add(helpPage4, "card5");

        helpPage5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        help5.setBackground(new java.awt.Color(255, 255, 255));

        help5Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help5Title.setText("How to Download Configuration");

        help5Pic.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help5ScreenShot.png")); // NOI18N
        help5Pic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        help5Pic2.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help5ScreenShot1.png")); // NOI18N
        help5Pic2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        help5Para4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para4.setText("Here we select the motherboard manufacturer.");

        help5Pic3.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help5ScreenShot2.png")); // NOI18N
        help5Pic3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        help5Para5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para5.setText("Here we select the motherboard model.");

        help5Pic4.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help5ScreenShot3.png")); // NOI18N
        help5Pic4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        help5Para6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para6.setText("Currently we can only select english configurations. In the future it will be possible  ");

        help5Para62.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para62.setText("to upload and download configurations in other languages.");

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help5ScreenShot4.png")); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        help5Para7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para7.setText("Now we specify whether we want to download (get) or upload (send) a configuration.");

        help5Pic5.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help5ScreenShot5.png")); // NOI18N
        help5Pic5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        help5Para8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para8.setText("More than one user might have uploaded his own configuration. Select the one you want  ");

        help5Para82.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para82.setText("to download.");

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help5ScreenShot6.png")); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        help5Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para1.setText("<html>SpeedFan strictly follows the datasheets available for each sensor chip. Hardware  <br>manufacturers can connect any sensor to whatever they like (to some extent). This  <br>means that SpeedFan is unable to know what's actually connected to, say, temp1.  <br>A procedure was developed to let end users, that feel confident about the  <br>configuration they set up for their own motherboard, upload it to make it available  <br>to other users.   <br> <br>You can access these configurations by using the GET CONFIG  <br>button on the INFO tab. You will find a link to a web page. By following the  <br>instructions on that web page you will be able, if your motherboard is listed there,  <br>to get a ticket. This ticket is, basically, a number that you will have to write in the  <br>relevant field in the dialog presented by SpeedFan. Fill in proxy info if needed, then  <br>press OK and restart SpeedFan if instructed to do so.   <br> <br>The online procedure requires the following steps: </html>");

        help5Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para2.setText("<html>You can use this web page both to get an already uploaded configuration or to upload\n<br>yours. Since we want to download one,  <br>we will select \"Existing configurations\". </html>");

        help5Para3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help5Para3.setText("<html>We are almost done now. In our example, we got 207 as the ticket number.  <br>We simply have to go back to SpeedFan, enter 207 as the ticket number and  <br>press OK. <br> <br>Disclaimer: Please note that these configurations are uploaded by end users  <br>and there is no overall control from SpeedFan's author. You are encouraged to  <br>disable AUTOMATIC FAN SPEED on the main window of SpeedFan and to  <br>check that everything is working fine. Configurations can only be uploaded by  <br>registered users that properly confirmed their registration by answering an  <br>email sent them. This greatly reduces the chances that something improper is  <br>uploaded.</html? ");

        javax.swing.GroupLayout help5Layout = new javax.swing.GroupLayout(help5);
        help5.setLayout(help5Layout);
        help5Layout.setHorizontalGroup(
            help5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help5Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help5Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help5Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(help5Pic)
                    .addComponent(jLabel2)
                    .addComponent(help5Para82)
                    .addComponent(help5Pic5)
                    .addComponent(help5Para7)
                    .addComponent(help5Para62)
                    .addComponent(help5Para6)
                    .addComponent(help5Pic4)
                    .addComponent(help5Para5)
                    .addComponent(help5Pic3)
                    .addComponent(help5Para4)
                    .addComponent(help5Pic2)
                    .addComponent(help5Title)
                    .addComponent(help5Para8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help5Layout.setVerticalGroup(
            help5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help5Title)
                .addGap(18, 18, 18)
                .addComponent(help5Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(help5Pic)
                .addGap(44, 44, 44)
                .addComponent(help5Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(help5Pic2)
                .addGap(55, 55, 55)
                .addComponent(help5Para4)
                .addGap(18, 18, 18)
                .addComponent(help5Pic3)
                .addGap(50, 50, 50)
                .addComponent(help5Para5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(help5Pic4)
                .addGap(56, 56, 56)
                .addComponent(help5Para6)
                .addGap(0, 0, 0)
                .addComponent(help5Para62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(42, 42, 42)
                .addComponent(help5Para7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(help5Pic5)
                .addGap(42, 42, 42)
                .addComponent(help5Para8)
                .addGap(0, 0, 0)
                .addComponent(help5Para82)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(55, 55, 55)
                .addComponent(help5Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpPage5.setViewportView(help5);

        helpPageBorder.add(helpPage5, "card6");

        helpPage6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        help6.setBackground(new java.awt.Color(255, 255, 255));

        help6Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help6Title.setText("How to SEND and REPORT");

        help6SubTitle1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        help6SubTitle1.setForeground(new java.awt.Color(255, 0, 0));
        help6SubTitle1.setText("What do I expect from your reports?");

        help6SubTitle2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        help6SubTitle2.setForeground(new java.awt.Color(255, 0, 0));
        help6SubTitle2.setText("What might you expect from reporting?");

        help6SubTitle3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        help6SubTitle3.setForeground(new java.awt.Color(255, 0, 0));
        help6SubTitle3.setText("How to report");

        help6Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help6Para1.setText("<html>I'm always eager to get reports from unsupported hardware. The best reports for  <br>me are those where almost nothing has been found. I always work on them ASAP.  <br>Obviously, in order to be able to fix SpeedFan to try to make it work with that  <br>hardware, I need your support. I do have some computers, but not every kind of  <br>hardware :-) If you plan to receive help from me, you should specify a valid email  <br>address. Please note that, in order to add support for new hardware, I need to  <br>send to you a new beta (a beta version is a private version of the software before  <br>the actual, final, new release is ready). In order to do so I need from you a valid  <br>email address that is able to receive an attachment (less than 1MB).</html>");

        help6Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help6Para2.setText("<html>Most of all you should ask for support for your unrecognized hardware monitoring  <br>chips. If this is the case, please give me as many info as you can. For example: you  <br>might tell me if any other hardware monitoring software is able to detect it, which  <br>name it has, where it is located (ISA, SMBus,...), the name that might be written on  <br>your system manual, or, if you can, the names written on the chips on your  <br>motherboard. Usually, hardware monitoring chips have got more than 10 pins and do  <br>not have heatsinks (actually, I've never seen one with an heatsink :-)). <br> <br>If you would like me to add support for your CLOCK (PLL) chip, you should carefully  <br>look at your motherboard and tell me the whole name. The clock chip is easy enough  <br>to find, as it is located near to a 14.xx MHz quartz (a small silver coated object  <br>protruding from the MB).</html>");

        help6Para3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help6Para3.setText("<html>Before reporting, you might try to understand whether your motherboard supports fan  <br>speed changing or not. If you dare changing fan speeds, you might consider reading my  <br>point of view on this topic :-) <br>Please, note that fan speed changing does not require a 3-wire fan. Any fan should work,  <br>if the motherboard supports fan speed changing. <br>You should connect a fan to every fan header on your motherboard. If you do not have that  <br>many fans, keep in mind that not every fan header might be able to change fan speeds. On  <br>my ABIT BP6, for example, only 2 out of 3 fan headers support it. You should try to change  <br>fan header and try again. <br> <br>The first step to check whether your motherboard is able to change fan speeds is trying to  <br>actually STOP fans. Set each speed down to 0%. Do not rely on reported fan speeds:  <br>reported fan speeds are usually wrong when coming to PWM modulation. Simply \"listen\" to  <br>your fans, or, better, look at them :-) If you are able to stop any fan, then it's likely to be  <br>possible to change fan speeds! Fan speed changing depends on some additional circuitry  <br>on the motherboard. Not every hardware manufacturer adds it. Depending on the actual  <br>circuitry, fan speed changing might be possible and might be more or less linear. After  <br>having discovered that you can stop a fan, you should try to understand the linearity  <br>(smoothness) of this variation. Try setting to 50%, 75% and the likes. Some motherboards  <br>do not vary fan speeds until when very low values are reached, like 20% or, even 2%. Try  <br>setting even 1% and check to see if there is any difference in speed. ");

        javax.swing.GroupLayout help6Layout = new javax.swing.GroupLayout(help6);
        help6.setLayout(help6Layout);
        help6Layout.setHorizontalGroup(
            help6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help6Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help6Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help6Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help6SubTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help6SubTitle1)
                    .addComponent(help6Title)
                    .addComponent(help6SubTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help6Layout.setVerticalGroup(
            help6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help6Title)
                .addGap(36, 36, 36)
                .addComponent(help6SubTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(help6Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(help6SubTitle2)
                .addGap(18, 18, 18)
                .addComponent(help6Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(help6SubTitle3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(help6Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1496, Short.MAX_VALUE))
        );

        helpPage6.setViewportView(help6);

        helpPageBorder.add(helpPage6, "card7");

        helpPage7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        help7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("How to Debug the SMBus");

        help7SubTitle1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        help7SubTitle1.setForeground(new java.awt.Color(255, 0, 0));
        help7SubTitle1.setText("/NO SMBSCAN");

        help7SubTitle2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        help7SubTitle2.setForeground(new java.awt.Color(255, 0, 0));
        help7SubTitle2.setText("/SMBDEBUG");

        help7Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help7Para1.setText("<html>If SpeedFan starts and then hangs while detecting your hardware, it might be due  <br>to a problem with your SMBus. Most hardware monitoring chips nowadays are  <br>connected to the SMBus. Anyway: it might be that you need not to access the SMBus  <br>in order to get hardware monitoring info. You can try this by running SpeedFan with  <br>the following command line switch:</html> ");

        help7Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help7Para2.setText("<html>If SpeedFan runs just fine when started using the previous command line switch,  <br>then it means that there is some problem between SpeedFan and your SMBus. I  <br>would like to know about problems and, therefore, please contact me and I will  <br>do my best to solve that issue. <br> <br>In order to simplify the task of solving this incompatibility, I've added a special  <br>command line switch:</html>");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setText("<html>When you start SpeedFan using this command line switch, SpeedFan will avoid  <br>detecting your hardware monitoring chips and will open instead a debug window.</html>");

        javax.swing.GroupLayout help7Layout = new javax.swing.GroupLayout(help7);
        help7.setLayout(help7Layout);
        help7Layout.setHorizontalGroup(
            help7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help7Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help7Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help7SubTitle2)
                    .addComponent(help7SubTitle1)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help7Layout.setVerticalGroup(
            help7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(help7Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(help7SubTitle1)
                .addGap(18, 18, 18)
                .addComponent(help7Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(help7SubTitle2)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2012, Short.MAX_VALUE))
        );

        helpPage7.setViewportView(help7);

        helpPageBorder.add(helpPage7, "card8");

        helpPage8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        help8.setBackground(new java.awt.Color(255, 255, 255));

        help8Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help8Title.setText("How to set Advanced Options");

        help8Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help8Para1.setForeground(new java.awt.Color(255, 0, 0));
        help8Para1.setText("<html>Please note that setting these options might cause your system to become unstable.  <br>You should know what you are doing. </html>");

        help8Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help8Para2.setText("<html>The Advanced Options tab allows you to set some specific options available from  <br>the hardware that SpeedFan found in your computer. After you have selected one  <br>of the available chips, the window will populate with all the options that are  <br>relevant to the chip you selected.</html>");

        help8Para3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help8Para3.setText("<html>In this picture you can see the options related to the ITE IT8712F. Some options, \n<br>like \"FANx mult\", \"FANx div\", \"Temperature x offset\" and \"Reverse PWMx logic\" are \n<br>common to all the chips, when applicable. This means that every chip that is able to \n<br>read any temperature will have a \"Temperature x offset\" for each of them. Some other \n<br>property is specific to some chip.\n<br>\n<br>We will take a closer look at the properties that appeared for the IT8712F since  \n<br>SpeedFan 4.19: \"PWM x mode\" and \"PWMOUT clock\". Up to SpeedFan 4.18, SpeedFan  <br>applied some custom settings when first detecting this chip. These settings allowed  <br>some computers to be able to change fan speeds, overriding some <br>(eventually  <br>missing) settings applied by the BIOS. The fact that those settings were applied only  <br>upon detection caused the same settings to be overriden by the BIOS after a Resume  <br>from Hibernation, forcing you to restart SpeedFan to be able to change fan speeds  <br>again. Now those settings are no longer <br>automatically applied. This means that  <br>some motherboards will no longer be able to change fan speeds. If this is your case,  <br>just enter CONFIG / ADVANCED and set \"PWM x mode\" to Software Controlled. I  <br>suggest you to set \"PWMOUT clock\" to 3M as this seems to be the best choice, but, if  <br>your fan is not too smooth <br>when changing fan speeds or makes odd noises, you  <br>can try different settings. <br> <br>Once you have verified that the new settings allow you to change fan speeds, you can  <br>reenter CONFIG / DIALOG and check \"remember it\" to instruct SpeedFan to set that  <br>option whenever it starts and whenever your computer resumes from suspend or  <br>hibernation. <br> <br>Other interesting options are: <br> <br> <br><b>Temperature x offset</b>  <br>If you know that any one specific temperature reported by SpeedFan is lower or  <br>higher than the real one by a specific offset, you can use this option to fix it <br>  <br><b>FANx divisor </b> <br>Depending on this value, higher or lower fan RPMs can be properly read:  <br>set it to a low value (1 or 2) if you have a high RPM fan (above 4000 RPM) and set it to 4  <br>or 8 if you have a slower fan. If any fan reports its speed as 0 RPM, give this option a try  <br>by setting a higher fan divisor  <br> <br><b>FANx mult + FANx div</b>  <br>Do not confuse these options with \"FANx divisor\". The actual RPM value read by the hardware  <br>monitor chip will be multiplied by \"FANx mult\" and divided by \"FANx div\". If any fan is reporting,  <br>say, twice its real speed, simply set \"FANx div\" to 2.  <br> <br><b>Reverse PWMx logic</b>  <br>Sometimes selecting higher fan speeds causes the fan to slow down. If this is what you are  <br>experiencing, simply set this option to ON and it will be fixed <br> <br> <br>The \"Remember it\" checkbox causes SpeedFan to apply the relevant change to any option  <br>whenever SpeedFan starts and when your computer is resumed from suspend and from hibernation.</html>");

        help8Pic1.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help8Pic1.png")); // NOI18N

        javax.swing.GroupLayout help8Layout = new javax.swing.GroupLayout(help8);
        help8.setLayout(help8Layout);
        help8Layout.setHorizontalGroup(
            help8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help8Pic1)
                    .addComponent(help8Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help8Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help8Title)
                    .addComponent(help8Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help8Layout.setVerticalGroup(
            help8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help8Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(help8Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(help8Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(help8Pic1)
                .addGap(39, 39, 39)
                .addComponent(help8Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(962, Short.MAX_VALUE))
        );

        helpPage8.setViewportView(help8);

        helpPageBorder.add(helpPage8, "card9");

        help9.setBackground(new java.awt.Color(255, 255, 255));

        help9Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help9Title.setText("How to Configure E-mail Support");

        help9Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help9Para1.setText("<html>SpeedFan can send emails. It uses an internal mail server. This server is currently  <br>quite simple. If the email can't be delivered it is not requeued. It is lost. But the mail  <br>server is quite powerful because it uses a multithreaded approach that won't block in  <br>any way SpeedFan's normal operations while trying to send emails. This is the most  <br>important thing for a tool like SpeedFan. Several profiles can be defined (some  <br>releases can only define one named 'default'). Each profile contains the parameters  <br>needed to contact your ISP's mail server. The settings are the same that you need to  <br>enter in your favourite mail client. SpeedFan's email facility supports both SSL  <br>connections and POP3-BEFORE-SMTP. In order to support SSL, SpeedFan must find  <br>OpenSSL free library. The official web site is www.openssl.org and Windows binaries  <br>should be available at www.openssl.org/related/binaries.html. If you don't need SSL  <br>support, you don't need that library. If the SSL interface is found, its version will be  <br>clearly indicated in the configuration page. You are strongly encouraged to send a  <br>test mail to make sure that everything is properly configured and working.</html>");

        javax.swing.GroupLayout help9Layout = new javax.swing.GroupLayout(help9);
        help9.setLayout(help9Layout);
        help9Layout.setHorizontalGroup(
            help9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help9Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help9Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help9Layout.setVerticalGroup(
            help9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help9Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(help9Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2148, Short.MAX_VALUE))
        );

        helpPage9.setViewportView(help9);

        helpPageBorder.add(helpPage9, "card10");

        help10.setBackground(new java.awt.Color(255, 255, 255));

        help10Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help10Title.setText("Main Tab");

        help10Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help10Para1.setText("<html>This is the main part of SpeedFan. Most of the time you will look at this page. Another  <br>page of interest would be the one related to hard disk S.M.A.R.T. values. <br> <br>According to your hardware, on this page you will see: <br> <br><b>CPU usage</b>   <br>If everything works fine, you will see here as many bars as many CPUs you've  <br>got. Each bar shows the CPU usage for each CPU. On my dual processor board, I can see 2  <br>bars.  <br> <br><b>Fan RPMs</b>  <br>Fan RPMs (revolutions per minute) are shown here. If you read 0, it might be due to too  <br>small a FAN DIVISOR. You can enter configuration dialog, on the ADVANCED tab and check  <br>to see if for your hardware monitoring chip SpeedFan support changing its setting. If it is  <br>supported, you might try setting it to a higher value (8 should do the trick for slow fans). <br>Please note that when you slow down fans by using SpeedFan's capabilities, reported RPMs  <br>might be odd. Usually you'll get very high reported speeds. This is mostly due to the fact  <br>that fan speeds changes are achieved by lowering the voltage sent to the fan itself. Most  <br>hardware monitoring chips do this voltage lowering by using PWM (Pulse Width Modulation).  <br>In poor terms, PWM means that the usual voltage is not always sent to the fan, but it is  <br>quickly turned on and off, thus simulating the effect of a lower voltage. This ON/OFF  <br>transition will cause the fan to improperly report its speed. Every fan reacts in a different  <br>way. <br>Consider that SpeedFan never relies on reported fan speeds when controlling them. Only <br>temperatures are relevant and SpeedFan will lose no functionality even if you've got a <br>two-wired fan that doesn't report its speed.  <br> <br><b>Temperatures</b>  <br>Here you will find all the temperature readings that SpeedFan could detect. These include  <br>those from the hard disk (if it supports this feature) and from ghost sensors. Ghost  <br>sensors are those that either are improperly detected by SpeedFan or disconnected (not  <br>actually used by the motherboard to monitor anything). You can go to the TEMPERATURES  <br>tab in the configuration dialog and hide those temperatures you're not interested in. <br>Several users ask why I didn't show more self-explanatory names for each temperature.  <br>The problem is that I don't know which temperature each motherboard manufacturer  <br>actually connected to what :-( <br>As a rule of thumb, the higher (realistic) temperature is the CPU and the lower (realistic)  <br>one is from your CASE. CPU temperature can be recognized even because it's the one that  <br>rises faster when CPU usage is at full load for a few minutes.  <br> <br><b>Fan SPEEDS</b>  <br>Here you can (try to) set a lower speed for your fans. You will find something here only if  <br>your hardware supports this feature. Please note that both your hardware monitoring chip  <br>must support it and your motherboard must include the needed circuitry. Several  <br>motherboards seem not to include this circuitry, even if their hardware monitoring chip  <br>(and, thus, SpeedFan) would be able to change fan speeds. <br>The first test you should consider trying (please read my considerations about fan speed  <br>changing) is setting each speed to 0%. If any fan stops (you should listen to it :-)) then  <br>you're on the right way. Try raising that percentage and try to understand if the speed can  <br>be smoothly changed. Do not rely on reported fan speeds, but \"listen\" to the sound :-) <br>If your motherboard can change fan speeds and it's not listed on my supported hardware  <br>web page, please let me know and tell me if you can change fan speeds, smoothly and if  <br>you can stop fans :-)  <br> <br><b>Voltages</b>  <br>Here you can find detected voltages. Once again, the datasheets for those hardware  <br>monitoring chips report reference circuitries to be used, but several motherboard  <br>manufacturers use different ones. SpeedFan shows voltages' names expected  <br>according to each datasheet. If they are different for your motherboard, feel free to  <br>use the VOLTAGES tab in the configuration dialog to change names. </html> ");

        javax.swing.GroupLayout help10Layout = new javax.swing.GroupLayout(help10);
        help10.setLayout(help10Layout);
        help10Layout.setHorizontalGroup(
            help10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help10Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help10Title))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help10Layout.setVerticalGroup(
            help10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help10Title)
                .addGap(25, 25, 25)
                .addComponent(help10Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpPage10.setViewportView(help10);

        helpPageBorder.add(helpPage10, "card11");

        help11.setBackground(new java.awt.Color(255, 255, 255));

        help11Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help11Title.setText("Configure TAB");

        help11Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help11Para1.setText("Using the Configure tab you can change an attribute of your motherboard.");

        help11Pic1.setIcon(new javax.swing.ImageIcon("C:\\data\\Curtin\\Fourth Year\\Human Interactive Design\\Icons\\Help11Pic1.png")); // NOI18N
        help11Pic1.setToolTipText("");

        help11Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help11Para2.setText("<html>To select an attrubute you wish to change, simply click on one of the tabs above,\n<br>all of which are labelled after an adjustable attribute, such as temperature. For \n<br>instructions to configure a specific attrbute, look no further than the How to \n<br>Configure help pages.  </html>");

        javax.swing.GroupLayout help11Layout = new javax.swing.GroupLayout(help11);
        help11.setLayout(help11Layout);
        help11Layout.setHorizontalGroup(
            help11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help11Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help11Pic1)
                    .addComponent(help11Para1)
                    .addComponent(help11Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help11Layout.setVerticalGroup(
            help11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help11Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(help11Para1)
                .addGap(18, 18, 18)
                .addComponent(help11Pic1)
                .addGap(29, 29, 29)
                .addComponent(help11Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1864, Short.MAX_VALUE))
        );

        helpPage11.setViewportView(help11);

        helpPageBorder.add(helpPage11, "card12");

        help12.setBackground(new java.awt.Color(255, 255, 255));

        help12Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help12Title.setText("Debugging TAB");

        help12Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help12Para1.setText("<html><b>ChipSet</b>\n<br>\n<br>The ChipSet shown is actually the relevant part of the chipset that is used by \n<br>SpeedFan to access the SMBus. Sometimes you will find here something like \n<br>PIIX4E (the good old SouthBridge from Intel BX), some other you'll find here \n<br>something like SiS735.\n<br>\n<br>The main thing is that if you find something here, then you're motherboard is \n<br>better supported than if nothing appears :-)\n<br>\n<br><b>DIMM info</b>\n<br>\n<br>About DIMM INFO. This started as a test to check SMBus functionality. It actually \n<br>reads (or tries to) info about your RAM. If anything can be read, this should \n<br>mean that your SMBus has been properly detected and accessed. But, sometimes, \n<br>you might even see nothing here even if your SMBus is properly accessed. This \n<br>may happen because either your motherboard does not \"publish\" those info \n<br>directly on the SMBus or because your RAM has got a faulty EPROM.\n<br>\n<br><b>SEND REPORT</b>\n<br>\n<br>The SEND REPORT facility is one of the most useful debugging tools that \n<br>SpeedFan has got. If you've got something wrong with your SpeedFan, \n<br>you should use the SEND REPORT facility to send a report. You will be \n<br>asked for your email address and for your motherboard model.\n<br>Your email address is vital if you plan to receive an answer :-)\n<br>\n<br>With the report you'll be able to include any text (question, suggestion, blame \n<br>:-)) you like.\n<br>\n<br><b>GET CONFIG</b>\n<br>\n<br>The GET CONFIG facility allows you to download user supplied configurations \n<br>for your motherboard. \n<br>\n<br>Find SMBus devices\n<br>\n<br>By pressing this button you will be presented a window with another button. \n<br>Press it to start scanning the SMBus. SpeedFan will simply search for available \n<br>devices and will do nothing else. This is useful for me to know whether your \n<br>CLOCK chip or something else is available somewhere on your motherboard.\n<br>If you run this function prior to sending a report, its results will be automatically \n<br>added to the report itself.</html>\n");

        javax.swing.GroupLayout help12Layout = new javax.swing.GroupLayout(help12);
        help12.setLayout(help12Layout);
        help12Layout.setHorizontalGroup(
            help12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help12Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help12Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help12Layout.setVerticalGroup(
            help12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help12Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(help12Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpPage12.setViewportView(help12);

        helpPageBorder.add(helpPage12, "card13");

        hep13.setBackground(new java.awt.Color(255, 255, 255));

        help13Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help13Title.setText("Diagnostics TAB");

        help13Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help13Para1.setText("<html>S.M.A.R.T. technology is really interesting and is useful to address several HD \n<br>related issues. SpeedFan can help you by showing S.M.A.R.T. readings from \n<br>available HDs. Please note that HDs connected to some IDE RAID controllers \n<br>might not show up. Supporting these controllers is not an easy task. If any \n<br>manufacturer would like to be supported, I will be pleased to receive any kind of \n<br>info from them :-)\n<br>\n<br>When looking at the S.M.A.R.T. tab, you will have to select an HD from the \n<br>available list. You will get something like this:</html>\n");

        help13Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help13Para2.setText("<html>For every S.M.A.R.T. attribute you will get the current VALUE, the WORST that has \n<br>ever been registered, the THRESHOLD and the RAW reading.\n<br>\n<br>You will see an icon too:</html>\n");

        help13Para3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help13Para3.setText("<html>Starting with SpeedFan 4.28, fitness and performance overall indicators are computed \n<br>using a more advanced structure. While 0% is still used when any relevant attribute \n<br>reaches its threshold, the full range from 1% up to 100% uses a non-linear, multi-range, \n<br>approach. This is the result of a statistical analysis of thousands hard disks. The owners \n<br>of SEAGATE hard disks now should see less frightening values. Seagate uses an unusual \n<br>strategy when presenting current values for some attributes, like \"seek error rate\", which \n<br>leads to values ranging around 55. The new way used by SpeedFan to compute fitness \n<br>and reliability derives from the <b><i>online in-depth analysis</b></i>. Online in-depth analysis is an \n<br>extremely powerful tool that applies only to EIDE/SATA hard disks and requires an active \n<br>internet connection. You simply need to select one of your hard disks and then press the \n<br>in-depth analysis button. A web page will open containing a full report about the status \n<br>of your hard disk. What you will read is what an expert would say about your hard disk. \n<br>This analysis is supported by a large database containing statistical data computed out \n<br>of real hard disks. Computed statistical data is considered relevant only if enough valid \n<br>hard disk reports were available when computing statistics. New databases will be \n<br>generated often as new relevant data is available. If your hard disk is not in the current \n<br>database, the analysis will still be conducted as if an expert did it. You can think of an \n<br>expert looking at S.M.A.R.T. values from your hard disk and telling you about them. The \n<br>expert can tell you a lot of things. If the expert already saw several hard disks like yours, \n<br>he can say more specific things. The same thing happens with the in-depth analysis tool. \n<br>The tool is currently available only online because it is under continuous development \n<br>and refinement. This way you will get the most up-to-date report whichever SpeedFan \n<br>version you will be using.\n<br>\n<br><b>Note:</b> you might need to enter CONFIGURE / INTERNET and set your HTTP PROXY \n<br>configuration there.</html>\n");

        javax.swing.GroupLayout hep13Layout = new javax.swing.GroupLayout(hep13);
        hep13.setLayout(hep13Layout);
        hep13Layout.setHorizontalGroup(
            hep13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hep13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(hep13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help13Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help13Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help13Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help13Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hep13Layout.setVerticalGroup(
            hep13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hep13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help13Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(help13Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(help13Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(help13Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        helpPage13.setViewportView(hep13);

        helpPageBorder.add(helpPage13, "card14");

        help14.setBackground(new java.awt.Color(255, 255, 255));

        help14Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help14Title.setText("Charts TAB");

        help14Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help14Para1.setText("The CHART tab lets you graphically view the last values from your sensors.");

        help14Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help14Para2.setText("<html><b>Start time</b> and<b> End time</b> show the time interval available for charting.\n<br>\n<br>\n<br>Whenever SpeedFan reads a new sample for any sensor, it stores it its current value \n<br>and timestamp. Some sensors are read less often than others. For example: S.M.A.R.T. \n<br>sensors are read every 60 seconds, whereas other temperatures are collected every 3 \n<br>seconds. Whatever the sampling rate is, SpeedFan will always show the full available \n<br>interval. The samples stored for each sensor will be limited. Currently this limit is set \n<br>to 256 samples, but it might vary in the future. This means that SpeedFan will show, at \n<br>most, the last 256 samples.\n<br>\n<br>You can select which kind of sensors' readings to compare (temperatures, fan speeds, \n<br>voltages) and, within each type, you can choose which sensors to graph at the same \n<br>time. Each sensor will be automatically given a different color.\n<br>\n<br>The graph will be updated in real time.</html>\n");

        javax.swing.GroupLayout help14Layout = new javax.swing.GroupLayout(help14);
        help14.setLayout(help14Layout);
        help14Layout.setHorizontalGroup(
            help14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help14Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help14Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help14Para1)
                    .addComponent(help14Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help14Layout.setVerticalGroup(
            help14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help14Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help14Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help14Para1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2074, Short.MAX_VALUE)
                .addComponent(help14Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        helpPage14.setViewportView(help14);

        helpPageBorder.add(helpPage14, "card15");

        help16.setBackground(new java.awt.Color(255, 255, 255));

        help16Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help16Title.setText("Configuring Temperatures");

        help16Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help16Para1.setText("Let’s start working on them.");

        help16Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help16Para2.setText("<html>We’ve now selected TEMP02. As you can see, we have got all the available \n<br>temperature readings that SpeedFan has detected. In the CHIP column we can see \n<br>whom the sensor belongs to. In this case we have three different chips: one W83782D \n<br>and two LM75. We can distinguish the two LM75 because of the different ADDRESS \n<br>($48 and $49). LM75s, in this case, are actually aliases created by the W83782D itself \n<br>and we won’t need them as all temperatures are available through the W83782D itself. \n<br>This is not always true. Winbonds’ can be configured in a way that actually hides the \n<br>original temperature reading from the main chip and, in such a case, we would need \n<br>to access the aliased LM75.</html>");

        help16Para3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help16Para3.setText("<html>We’re selecting <b>DESIRED</b> and <b>WARNING</b> temperatures according to our system \n<br>and our whishes. Consider that we’re talking about \"whishes\". If we set these \n<br>temperatures as low as 15 degrees, we won’t, most likely, get them at all :-)\n<br>\n<br>As you can see, we first had to select a temperature and then we could select its \n<br>parameters. We can even rename the default name (with either the mouse or by \n<br>pressing F2). The renamed name will therefore be shown in the main window in a \n<br>more descriptive way.</html>");

        help16Para4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help16Para4.setText("<html>We have renamed TEMP1 and TEMP2 to CPU1 and CPU0.</html>");

        help16Para5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help16Para5.setText("<html>We’ve finished renaming and setting parameters for each temperature. Since I’ve \n<br>seen that the highest temperature in my system is CASE temperature, I’ve chosen to \n<br>have it shown in tray too.\n<br>\n<br>Now we need to hide from the main window those temperatures that are unuseful \n<br>for our needs. In our case, they are those from LM75s. Not every system has got \n<br>unuseful sensors, but another case where this might happen is when there are \n<br>unconnected sensors that report obviously wrong values (like 127 or the like).</html>");

        help16Para6.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help16Para6.setText("<html>Unchecking unwanted temperatures will do the trick.\n<br>\n<br>Now we would like to have those temperatures sorted on the main window. All we \n<br>have to do is using drag&drop to move them up and down.</html>");

        help16Para7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help16Para7.setText("<html>So, with a little dragging and dropping, we can achieve the following result.</html>");

        help16Para8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help16Para8.setText("<html>Ok! The first part related to temperatures has been done.</html>");

        javax.swing.GroupLayout help16Layout = new javax.swing.GroupLayout(help16);
        help16.setLayout(help16Layout);
        help16Layout.setHorizontalGroup(
            help16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help16Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(help16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help16Para8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help16Para7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help16Para6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help16Para5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help16Para4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help16Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help16Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help16Para1)
                    .addComponent(help16Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help16Layout.setVerticalGroup(
            help16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help16Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help16Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addComponent(help16Para1)
                .addGap(198, 198, 198)
                .addComponent(help16Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216)
                .addComponent(help16Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(help16Para4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(help16Para5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165)
                .addComponent(help16Para6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 640, Short.MAX_VALUE)
                .addComponent(help16Para7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257)
                .addComponent(help16Para8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        helpPage16.setViewportView(help16);

        helpPageBorder.add(helpPage16, "card16");

        help17.setBackground(new java.awt.Color(255, 255, 255));

        help17Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help17Title.setText("Configuring Fans");

        help17Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help17Para1.setText("Much like what we did with temperatures, we can rename...");

        help17Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help17Para2.setText("...hide unwanteds from the main window (I haven’t got any fan connected to FAN3)...");

        help17Para3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help17Para3.setText("...and sort.");

        javax.swing.GroupLayout help17Layout = new javax.swing.GroupLayout(help17);
        help17.setLayout(help17Layout);
        help17Layout.setHorizontalGroup(
            help17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help17Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help17Para3)
                    .addComponent(help17Para2)
                    .addComponent(help17Para1)
                    .addComponent(help17Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help17Layout.setVerticalGroup(
            help17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help17Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help17Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(help17Para1)
                .addGap(86, 86, 86)
                .addComponent(help17Para2)
                .addGap(88, 88, 88)
                .addComponent(help17Para3)
                .addContainerGap(2096, Short.MAX_VALUE))
        );

        helpPage17.setViewportView(help17);

        helpPageBorder.add(helpPage17, "card17");

        help18.setBackground(new java.awt.Color(255, 255, 255));

        help18Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help18Title.setText("Configuring Speeds");

        help18Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help18Para1.setText("<html>These are the default settings for my system.\n<br>\n<br>Please, note that not every motherboard will show something here. It depends on \n<br>which sensors can be found by SpeedFan. The same happens for temperatures, \n<br>voltages and fans. Not every sensor chip handles all of them. SpeedFan will show \n<br>everything it can read from available sensors.</html>\n");

        help18Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help18Para2.setText("As usual, we can rename...");

        help18Para3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help18Para3.setText("<html>...hide unwanteds from the main window (W83782D has got 4 PWMs, but, for several \n<br>reasons, it’s quite difficult that you will be able to use them all)...</html>");

        help18Para4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help18Para4.setText("...and sort them.");

        javax.swing.GroupLayout help18Layout = new javax.swing.GroupLayout(help18);
        help18.setLayout(help18Layout);
        help18Layout.setHorizontalGroup(
            help18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help18Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help18Para4)
                    .addComponent(help18Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help18Para2)
                    .addComponent(help18Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help18Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help18Layout.setVerticalGroup(
            help18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help18Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help18Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(help18Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(help18Para2)
                .addGap(80, 80, 80)
                .addComponent(help18Para3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(help18Para4)
                .addContainerGap(1939, Short.MAX_VALUE))
        );

        helpPage18.setViewportView(help18);

        helpPageBorder.add(helpPage18, "card18");

        help19.setBackground(new java.awt.Color(255, 255, 255));

        help19Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help19Title.setText("Configuring Voltages");

        help19Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help19Para1.setText("<html>Here we can find only values from the W83782D (the same thing happened with \n<br>FANS and SPEEDS). This is due to the fact that LM75 sensors do not report anything \n<br>else but temperatures. Not all detected chips will show up in each tab. Some chips \n<br>do report fan speeds, others temperatures and voltages and so on.\n<br>\n<br>SpeedFan assigns default names to each voltage reading and does it following the \n<br>specifications from the hardware manufacturer for the sensor chip itself. \n<br>Unfortunately, some motherboard manufacturers do not follow those directions, \n<br>thus leading to improper readings. SpeedFan is coded keeping generality as a goal \n<br>and will, therefore, have some minor problems with some odd decision from some \n<br>manufacturers.\n<br>\n<br>My ABIT BP6 has got a special setting that doesn’t hurt too much: the VBAT reading \n<br>is not actually from the battery itself, but from the CORE VOLTAGE for one of the \n<br>two available CPUs.\n<br>\n<br>We can, with little effort, handle this :-)</html>\n");

        help19Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help19Para2.setText("We can even hide the VINR0 reading as it makes little sense (to me).");

        help19Para3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help19Para3.setText("Since we can, why don’t we sort voltages too? :-)");

        javax.swing.GroupLayout help19Layout = new javax.swing.GroupLayout(help19);
        help19.setLayout(help19Layout);
        help19Layout.setHorizontalGroup(
            help19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help19Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help19Para3)
                    .addComponent(help19Para2)
                    .addComponent(help19Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help19Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help19Layout.setVerticalGroup(
            help19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help19Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help19Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help19Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(help19Para2)
                .addGap(102, 102, 102)
                .addComponent(help19Para3)
                .addContainerGap(1873, Short.MAX_VALUE))
        );

        helpPage19.setViewportView(help19);

        helpPageBorder.add(helpPage19, "card19");

        help20.setBackground(new java.awt.Color(255, 255, 255));

        help20Title.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help20Title.setText("Profiles");

        help20Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help20Para1.setText("<html>Profiles are a new feature in SpeedFan, where users select how they would rank\n<br>themselves in terms of experience using SpeedFan. This, in turn, enables or disables\n<br> certain functions to accomodate for different kinds of users.\n<br>\n<br>The profiles settings can be found in the settings window, under the profiles tab. \n</html>\n");

        help20Para2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help20Para2.setText("<html>\n<br>There are three different levels of profiles that can be selected:\n<br>\n<br>\n<br><b>Beginner</b>\n<br>By default, SpeedFan is on the beginner mode. This mode disables all functions\n<br>that involve configuring the clock, temperature, voltage and fan speeds. \n<br>Beginner users can still view readings, charts and apply basic settings, but they\n<br>do not have permission to access some of the more dangerous features. \n<br>\n<br><b>Advanced</b>\n<br>Advanced mode allows users of advanced experience using SpeedFan and\n<br>a basic understanding of the hardware to configure CPU temperatures, voltage\n<br>and fan speeds. However, they won't have the authority to apply changes that\n<br>exceed the warning figures. They also don't have the authority to change these\n<br>warning figures. \n<br>\n<br><b>Expert</b>\n<br>Expert users are granted unrestricted access to all of SpeedFan's features, \n<br>including the dangerous ones. They can even change warning figures, which act as\n<br>a ceiling to how high certain values of temperature, voltage, etc. can be.  \n<br></html>\n");

        javax.swing.GroupLayout help20Layout = new javax.swing.GroupLayout(help20);
        help20.setLayout(help20Layout);
        help20Layout.setHorizontalGroup(
            help20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help20Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help20Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help20Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help20Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        help20Layout.setVerticalGroup(
            help20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help20Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help20Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help20Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1943, Short.MAX_VALUE)
                .addComponent(help20Para2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        helpPage20.setViewportView(help20);

        helpPageBorder.add(helpPage20, "card20");

        help15.setBackground(new java.awt.Color(255, 255, 255));

        help5Title1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        help5Title1.setText("Clock Tab");

        help15Para1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        help15Para1.setText("<html>FSB is for Front Side BUS. It's the BUS clock speed for your CPU. \n<br>This is not for the faint hearted. Changing the FSB you can make your CPU run faster \n<br>(but even slower, if you like), but, if you don't know what you're doing, it's likely that \n<br>you'll end up hanging your PC :-) This is, usually, not a problem. In the worst case \n<br>you should simply have to power cycle your PC, but I do not know which are all \n<br>possible results of attempting to do so and, therefore, I have to warn you telling you \n<br>that <b>you should change the clock speed only if your really know what you're doing: \n<br>I won't be responsible for any damage</b>\n<br>\n<br>:-)\n<br>\n<br>Prior to changing the clock, you should choose your motherboard model. If it doesn't \n<br>appear amongst known motherboards you might contact me, but, please, be sure to \n<br>know which is the EXACT clock generator you've got on your motherboard. You can \n<br>even directly select your clock generator (your motherboard might have the same \n<br>clock generator as another supported motherboard). If it works for you, feel free to \n<br>let me know.\n<br>\n<br>At the bottom of the window you can find a listing containing all available frequencies \n<br>that can be set. Simply choose one and then press Set Clock. Usually you will start \n<br>with a Read Clock so that you know whether your clock generator is properly handled. \n<br>If it is properly handled, you will see the current frequecy shown right below the \n<br>button.\n<br>\n<br>Starting from version 4.09, I've added an Automatic Clock Control feature.\n<br>\n<br>It, basically, allows you to change clock speed based on current CPU utilization. You \n<br>have to choose two different clock settings. One will be used when CPU utilization \n<br>is low and the other one will be used when CPU utilization is high.\n<br>\n<br>The Status label will inform you about the latest setting applied by the internal logic \n<br>and what the logic is about to do (like the “low power preferred' message you can \n<br>see).\n<br>\n<br>Before activating Automatic Clock Control by checking Use This Clock Generator, you \n<br>should first double check that the clock speeds you selected are working properly \n<br>with your system.\n<br>\n<br>Please, note that current clock generators are extremely powerful and that the kind \n<br>of settings allowed by SpeedFan makes use of built in predefined frequencies. \n<br>Recent motherboards, though, use advanced clock settings and are not based on \n<br>internal tables. It depends on several factor whether it will work or not. I'll be \n<br>happy to know about any success even with unlisted motherboards that have a \n<br>known clock generator :-)</html>\n");

        javax.swing.GroupLayout help15Layout = new javax.swing.GroupLayout(help15);
        help15.setLayout(help15Layout);
        help15Layout.setHorizontalGroup(
            help15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help15Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(help15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(help15Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help5Title1))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        help15Layout.setVerticalGroup(
            help15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help15Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(help5Title1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(help15Para1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        helpPage15.setViewportView(help15);

        helpPageBorder.add(helpPage15, "card21");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpListBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(helpPageBorder, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(helpListBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(helpPageBorder, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void settingsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButtonMouseEntered
        settingsButton.addMouseListener(new MouseAdapter()
            {
                public void mouseEntered(MouseEvent e)
                {
                    settingsButton.setBorder(new LineBorder(Color.WHITE));
                }
            });
    }//GEN-LAST:event_settingsButtonMouseEntered

    private void settingsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButtonMouseExited
        settingsButton.addMouseListener(new MouseAdapter()
            {
                public void mouseExited(MouseEvent e)
                {
                    settingsButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                }
            });
    }//GEN-LAST:event_settingsButtonMouseExited

    private void printButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printButtonMouseEntered
        printButton.addMouseListener(new MouseAdapter()
            {
                public void mouseEntered(MouseEvent e)
                {
                    printButton.setBorder(new LineBorder(Color.WHITE));
                }
            });
    }//GEN-LAST:event_printButtonMouseEntered

    private void printButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printButtonMouseExited
        printButton.addMouseListener(new MouseAdapter()
            {
                public void mouseExited(MouseEvent e)
                {
                    printButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                }
            });
    }//GEN-LAST:event_printButtonMouseExited

    private void helpListPt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpListPt1MouseClicked
        helpListPt1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JList list = (JList)e.getSource();
                if(e.getClickCount() == 1){
                    int index = list.locationToIndex(e.getPoint());
                    //Do stuff
                    helpPageBorder.removeAll();
       
                    helpPageBorder.add(helpPagesPt1[index]);
                    
                    
                    helpPageBorder.repaint();
                    helpPageBorder.revalidate();
                    
                }
            }
        });
    }//GEN-LAST:event_helpListPt1MouseClicked

    private void helpListPt1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpListPt1MouseEntered
        helpListPt1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                JList list = (JList)e.getSource();
                    //Color cell
                    
            }
        });
    }//GEN-LAST:event_helpListPt1MouseEntered

    private void settingsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButtonMouseClicked
        if(this.settings == null)
        {
        
            java.awt.EventQueue.invokeLater(new Runnable() {
                Settings settings;
                
                public void run() 
                {
                    if(this.settings == null)
                    {
                        settings = new Settings();
                        settings.setHelp(Help.this);
                        settings.setHome(home);
                        settings.setColorState(colorState);
                        this.settings = settings;
                        settings.setVisible(true);
                    }    
                }
            });
        }
    }//GEN-LAST:event_settingsButtonMouseClicked

    private void helpListPt2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpListPt2MouseClicked
        helpListPt2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JList list = (JList)e.getSource();
                if(e.getClickCount() == 1){
                    int index = list.locationToIndex(e.getPoint());
                    //Do stuff
                    helpPageBorder.removeAll();
       
                    helpPageBorder.add(helpPagesPt2[index]);
                    
                    
                    helpPageBorder.repaint();
                    helpPageBorder.revalidate();
                    
                }
            }
        });
    }//GEN-LAST:event_helpListPt2MouseClicked

    private void helpListPt2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpListPt2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_helpListPt2MouseEntered

    private void helpListPt3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpListPt3MouseClicked
        helpListPt3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JList list = (JList)e.getSource();
                if(e.getClickCount() == 1){
                    int index = list.locationToIndex(e.getPoint());
                    //Do stuff
                    helpPageBorder.removeAll();
       
                    helpPageBorder.add(helpPagesPt3[index]);
                    
                    
                    helpPageBorder.repaint();
                    helpPageBorder.revalidate();
                    
                }
            }
        });
    }//GEN-LAST:event_helpListPt3MouseClicked

    private void helpListPt3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpListPt3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_helpListPt3MouseEntered

    private void helpListPt4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpListPt4MouseClicked
        helpListPt4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JList list = (JList)e.getSource();
                if(e.getClickCount() == 1){
                    int index = list.locationToIndex(e.getPoint());
                    //Do stuff
                    helpPageBorder.removeAll();
       
                    helpPageBorder.add(helpPagesPt4[index]);
                    
                    
                    helpPageBorder.repaint();
                    helpPageBorder.revalidate();
                    
                }
            }
        });
    }//GEN-LAST:event_helpListPt4MouseClicked

    private void helpListPt4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpListPt4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_helpListPt4MouseEntered

    private void tabsComboButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tabsComboButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabsComboButtonActionPerformed

    private void introductionComboButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_introductionComboButtonMouseClicked
        if(!helpListPt1.isVisible())
        {
            helpListPt1.setVisible(true);
        }
        else
        {
            helpListPt1.setVisible(false);
        }
    }//GEN-LAST:event_introductionComboButtonMouseClicked

    private void tabsComboButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsComboButtonMouseClicked
        if(!helpListPt2.isVisible())
        {
            helpListPt2.setVisible(true);
        }
        else
        {
            helpListPt2.setVisible(false);
        }
    }//GEN-LAST:event_tabsComboButtonMouseClicked

    private void configureComboButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configureComboButtonMouseClicked
        if(!helpListPt3.isVisible())
        {
            helpListPt3.setVisible(true);
        }
        else
        {
            helpListPt3.setVisible(false);
        }
    }//GEN-LAST:event_configureComboButtonMouseClicked

    private void profilesComboButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilesComboButtonMouseClicked
        if(!helpListPt4.isVisible())
        {
            helpListPt4.setVisible(true);
        }
        else
        {
            helpListPt4.setVisible(false);
        }
    }//GEN-LAST:event_profilesComboButtonMouseClicked

    private void introductionComboButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_introductionComboButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_introductionComboButtonActionPerformed

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
        String[] searchWords = searchTextField.getText().toLowerCase().split("\\s+");
        int i, j;
        boolean found = false;
        
        //searchList.clear();
        DefaultListModel<String> model = new DefaultListModel<>();
        
        
        for(i=0; i < keyWordsHelp1.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp1[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("1. Introduction to SpeedFan");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp2.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp2[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("2. First Look");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp3.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp3[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("3. My Position on CPU Cooling and Fan Speeds");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp4.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp4[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("4. Logging Facility");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp5.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp5[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("5. How to Download a Configuration");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp6.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp6[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("6. How to Send a Report");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp7.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp7[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("7. How to Debug the SMBus");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp8.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp8[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("8. How to Set Advanced Options");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp9.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp9[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("9. Hot to Configure E-mail Support");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp10.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp10[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("10. Main Tab");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp11.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp11[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("11. Configure Tab");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp12.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp12[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("12. Debugging Tab");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp13.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp13[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("13. Diagnostics Tab");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp14.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp14[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("14. Charts Tab");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp15.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp15[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("15. Clock Tab");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp16.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                
                if(keyWordsHelp16[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("16. Configuring Temperatures");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp17.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                
                if(keyWordsHelp17[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("17. Configuring Fans");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp18.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                
                if(keyWordsHelp18[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("18. Configuring Speeds");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp19.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                if(keyWordsHelp19[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("19. Configuring Voltages");
                }
            }
        }
        
        found = false;
        for(i=0; i < keyWordsHelp20.length; i++)
        {
            for(j=0; j < searchWords.length; j++)
            {
                
                if(keyWordsHelp20[i].equals(searchWords[j]) && !found)
                {
                    found = true;
                    model.addElement("20. Profiles");
                }
            }
        }
       
        //searchList = new JList<>(model);
        searchList.setCellRenderer(new MyListCell());
        searchList.setModel(model);
        
    }//GEN-LAST:event_searchButtonMouseClicked

    private void searchListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchListMouseClicked
        searchList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JList list = (JList)e.getSource();
                if(e.getClickCount() == 1){
                    int index = list.locationToIndex(e.getPoint());
                    String title = (String)list.getSelectedValue();

                    String[] titleToken = title.split("\\.");
                    int num = Integer.valueOf(titleToken[0]);
                    
                    if(num >= 1 && num <= 9)
                    {
                        helpPageBorder.removeAll();
       
                        helpPageBorder.add(helpPagesPt1[num-1]);
                    
                    
                        helpPageBorder.repaint();
                        helpPageBorder.revalidate();
                    }
                    else if(num >= 10 && num <= 15)
                    {
                        helpPageBorder.removeAll();
       
                        helpPageBorder.add(helpPagesPt2[num-10]);
                    
                    
                        helpPageBorder.repaint();
                        helpPageBorder.revalidate();
                    }
                    else if(num >= 16 && num <= 19)
                    {
                        helpPageBorder.removeAll();
       
                        helpPageBorder.add(helpPagesPt3[num-16]);
                    
                    
                        helpPageBorder.repaint();
                        helpPageBorder.revalidate();
                    }
                    else if(num == 20)
                    {
                        helpPageBorder.removeAll();
       
                        helpPageBorder.add(helpPagesPt4[num-20]);
                    
                    
                        helpPageBorder.repaint();
                        helpPageBorder.revalidate();
                    }
                    
                    
                }
            }
        });
    }//GEN-LAST:event_searchListMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(settings != null)
        {
            settings.setHelpClosed();
        }
        
        if(home != null)
        {
            home.setHelpClosed();
            
        }
        
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(settings != null)
        {
            settings.setHelpClosed();
        }
        
        if(home != null)
        {
            home.setHelpClosed();
            
        }
    }//GEN-LAST:event_formWindowClosed

    //To be called only by other windows
    public void setColorState(int colorState)
    {
        this.colorState = colorState;
        changeColorScheme(colorState);
    }
    
    public void changeColorScheme(int colorState){
        if(colorState == BLUE)
        {
            helpListBorder.setBackground(BLUE_ARRAY[0]);
            
        }
        else if(colorState == RED)
        {
            helpListBorder.setBackground(RED_ARRAY[0]);
        }
        else if(colorState == GREEN)
        {
            helpListBorder.setBackground(GREEN_ARRAY[0]);
        }
        else if(colorState == YELLOW)
        {
            helpListBorder.setBackground(YELLOW_ARRAY[0]);
        }
        else if(colorState == ORANGE)
        {
            helpListBorder.setBackground(ORANGE_ARRAY[0]);
        }
        else if(colorState == DARK)
        {
            helpListBorder.setBackground(DARK_ARRAY[0]);
        }
        else if(colorState == PURPLE)
        {
            helpListBorder.setBackground(PURPLE_ARRAY[0]);

        }
    }
    
    public void setHome(Home home)
    {
        
        this.home = home;
        this.home.setHelp(Help.this);

    }
    
    public void setSettings(Settings settings)
    {
        if(settings != null)
        {
            if(!settingsOpened)
            {
                settingsOpened = true;
                this.settings = settings;
                this.settings.setHelp(Help.this);
        
            }
        }
    }
    
    public void setSettingsClosed()
    {
        settingsOpened = false;
        settings = null;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Help().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton configureComboButton;
    private javax.swing.JPanel contentsBorder;
    private javax.swing.JScrollPane contentsScrollPAne;
    private javax.swing.JPanel help1;
    private javax.swing.JPanel help10;
    private javax.swing.JLabel help10Para1;
    private javax.swing.JLabel help10Title;
    private javax.swing.JPanel help11;
    private javax.swing.JLabel help11Para1;
    private javax.swing.JLabel help11Para2;
    private javax.swing.JLabel help11Pic1;
    private java.awt.Label help11Title;
    private javax.swing.JPanel help12;
    private javax.swing.JLabel help12Para1;
    private java.awt.Label help12Title;
    private javax.swing.JLabel help13Para1;
    private javax.swing.JLabel help13Para2;
    private javax.swing.JLabel help13Para3;
    private java.awt.Label help13Title;
    private javax.swing.JPanel help14;
    private javax.swing.JLabel help14Para1;
    private javax.swing.JLabel help14Para2;
    private java.awt.Label help14Title;
    private javax.swing.JPanel help15;
    private javax.swing.JLabel help15Para1;
    private javax.swing.JPanel help16;
    private javax.swing.JLabel help16Para1;
    private javax.swing.JLabel help16Para2;
    private javax.swing.JLabel help16Para3;
    private javax.swing.JLabel help16Para4;
    private javax.swing.JLabel help16Para5;
    private javax.swing.JLabel help16Para6;
    private javax.swing.JLabel help16Para7;
    private javax.swing.JLabel help16Para8;
    private java.awt.Label help16Title;
    private javax.swing.JPanel help17;
    private javax.swing.JLabel help17Para1;
    private javax.swing.JLabel help17Para2;
    private javax.swing.JLabel help17Para3;
    private java.awt.Label help17Title;
    private javax.swing.JPanel help18;
    private javax.swing.JLabel help18Para1;
    private javax.swing.JLabel help18Para2;
    private javax.swing.JLabel help18Para3;
    private javax.swing.JLabel help18Para4;
    private java.awt.Label help18Title;
    private javax.swing.JPanel help19;
    private javax.swing.JLabel help19Para1;
    private javax.swing.JLabel help19Para2;
    private javax.swing.JLabel help19Para3;
    private java.awt.Label help19Title;
    private javax.swing.JLabel help1Para1;
    private javax.swing.JLabel help1Para2;
    private javax.swing.JLabel help1Para3;
    private javax.swing.JLabel help1Pic;
    private java.awt.Label help1Title;
    private javax.swing.JLabel help1Warning;
    private javax.swing.JPanel help2;
    private javax.swing.JPanel help20;
    private javax.swing.JLabel help20Para1;
    private javax.swing.JLabel help20Para2;
    private java.awt.Label help20Title;
    private javax.swing.JLabel help2Para1;
    private javax.swing.JLabel help2Para2;
    private javax.swing.JLabel help2Pic1;
    private java.awt.Label help2Title;
    private javax.swing.JPanel help3;
    private javax.swing.JLabel help3Para1;
    private javax.swing.JLabel help3Title;
    private javax.swing.JPanel help4;
    private javax.swing.JLabel help4Para1;
    private java.awt.Label help4Title;
    private javax.swing.JPanel help5;
    private javax.swing.JLabel help5Para1;
    private javax.swing.JLabel help5Para2;
    private javax.swing.JLabel help5Para3;
    private javax.swing.JLabel help5Para4;
    private javax.swing.JLabel help5Para5;
    private javax.swing.JLabel help5Para6;
    private javax.swing.JLabel help5Para62;
    private javax.swing.JLabel help5Para7;
    private javax.swing.JLabel help5Para8;
    private javax.swing.JLabel help5Para82;
    private javax.swing.JLabel help5Pic;
    private javax.swing.JLabel help5Pic2;
    private javax.swing.JLabel help5Pic3;
    private javax.swing.JLabel help5Pic4;
    private javax.swing.JLabel help5Pic5;
    private javax.swing.JLabel help5Title;
    private javax.swing.JLabel help5Title1;
    private javax.swing.JPanel help6;
    private javax.swing.JLabel help6Para1;
    private javax.swing.JLabel help6Para2;
    private javax.swing.JLabel help6Para3;
    private javax.swing.JLabel help6SubTitle1;
    private javax.swing.JLabel help6SubTitle2;
    private javax.swing.JLabel help6SubTitle3;
    private javax.swing.JLabel help6Title;
    private javax.swing.JPanel help7;
    private javax.swing.JLabel help7Para1;
    private javax.swing.JLabel help7Para2;
    private javax.swing.JLabel help7SubTitle1;
    private javax.swing.JLabel help7SubTitle2;
    private javax.swing.JPanel help8;
    private javax.swing.JLabel help8Para1;
    private javax.swing.JLabel help8Para2;
    private javax.swing.JLabel help8Para3;
    private javax.swing.JLabel help8Pic1;
    private javax.swing.JLabel help8Title;
    private javax.swing.JPanel help9;
    private javax.swing.JLabel help9Para1;
    private java.awt.Label help9Title;
    private javax.swing.JPanel helpListBorder;
    private javax.swing.JList<String> helpListPt1;
    private javax.swing.JList<String> helpListPt2;
    private javax.swing.JList<String> helpListPt3;
    private javax.swing.JList<String> helpListPt4;
    private javax.swing.JPanel helpListTab;
    private javax.swing.JScrollPane helpPage1;
    private javax.swing.JScrollPane helpPage10;
    private javax.swing.JScrollPane helpPage11;
    private javax.swing.JScrollPane helpPage12;
    private javax.swing.JScrollPane helpPage13;
    private javax.swing.JScrollPane helpPage14;
    private javax.swing.JScrollPane helpPage15;
    private javax.swing.JScrollPane helpPage16;
    private javax.swing.JScrollPane helpPage17;
    private javax.swing.JScrollPane helpPage18;
    private javax.swing.JScrollPane helpPage19;
    private javax.swing.JScrollPane helpPage2;
    private javax.swing.JScrollPane helpPage20;
    private javax.swing.JScrollPane helpPage3;
    private javax.swing.JScrollPane helpPage4;
    private javax.swing.JScrollPane helpPage5;
    private javax.swing.JScrollPane helpPage6;
    private javax.swing.JScrollPane helpPage7;
    private javax.swing.JScrollPane helpPage8;
    private javax.swing.JScrollPane helpPage9;
    private javax.swing.JPanel helpPageBorder;
    private javax.swing.JTabbedPane helpTabs;
    private javax.swing.JPanel hep13;
    private javax.swing.JToggleButton introductionComboButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel printButton;
    private javax.swing.JToggleButton profilesComboButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JList<String> searchList;
    private javax.swing.JPanel searchTab;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel settingsButton;
    private javax.swing.JToggleButton tabsComboButton;
    private javax.swing.JPanel toolBar;
    // End of variables declaration//GEN-END:variables
}
