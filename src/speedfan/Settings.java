
package speedfan;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class Settings extends javax.swing.JFrame {

    private Home home = null;
    private Help help = null;
    private Report report = null;
    
    private boolean homeIsParent = false;
    private boolean helpOpened = false;
    private boolean reportOpened = false; 
    
    private static int BEGINNER_USER = 0;
    private static int ADVANCED_USER = 1;
    private static int EXPERT_USER = 2; 
    private int PROFILE_STATE;
    private int OLD_PROFILE_STATE;
    private int TEMP_PROFILE_STATE;
    
    private static int PROFILE = 0;
    private static int OPTIONS = 1;
    private static int ADVANCED = 2;
    private static int EVENTS = 3;
    private static int MAIL = 4;
    private static int INTERNET = 5;
    private int STATE;
    
    private HashMap<String, String> prefs;
    private HashMap<String, String> mailPrefs;
    private HashMap<String, String> internetPrefs;
    private DefaultTableModel eventsModel;
    private DefaultTableModel advancedModel;
    
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
    
    private int EVENTS_TABLE_ROW = -1;
    
    private boolean firstTimeMail = true;
    private boolean firstTimeEvents = true;
    private boolean firstTimeInternet = true; 
    
    
    public Settings() {
        
        
        
        initComponents();
        colorState = BLUE;
        
        profilesButton.setBackground(setColor("BRIGHTEST"));
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        colorState = BLUE;
        
        PROFILE_STATE = BEGINNER_USER; 
        
        this.setSize(830, 640);
        
        //Options panel
        if(prefs == null)
        {
            prefs = new HashMap<String, String>();
            prefs.put("icon background", "0");
            prefs.put("icon text", "0");
            prefs.put("size", "small");
            prefs.put("language", "0");
            prefs.put("debugMode","enabled");
            prefs.put("temperature","Celsius");
            prefs.put("speedFan access","SMBus");
            prefs.put("start minimized","disabled");
            prefs.put("static icon","disabled");
            prefs.put("dell support","disabled");
            prefs.put("delta value","0");
            prefs.put("summary error log scan","disabled");
            prefs.put("fans 100% on exit","disabled");
        
            //Advanced panel
            prefs.put("set to","0");
            prefs.put("remember it", "disabled");
        
            //Mail panel
            prefs.put("mail profile","0");
            prefs.put("mail from","");
            prefs.put("mail to","");
            prefs.put("smtp server","");
            prefs.put("port 1","25");
            prefs.put("use start tls","disabled");
            prefs.put("username 1","");
            prefs.put("password 1","");
            prefs.put("pop3 server","");
            prefs.put("port 2","25");
            prefs.put("prior pop3 check","disabled");
            prefs.put("username 2","");
            prefs.put("password 2","");
        
            //Internet panel
            prefs.put("use proxy","disabled");
            prefs.put("http proxy", "");
            prefs.put("internet username", "");      
            prefs.put("internet password", "");
        }
        
        if(mailPrefs == null){
            mailPrefs = new HashMap<String, String>();
            mailPrefs.put("from email", fromEmailTextBox.getText());
            mailPrefs.put("to email", toEmailTextBox.getText());
            mailPrefs.put("smtp server", smtpServerTextBox.getText());
            mailPrefs.put("port 1", portTextBox.getText());
                
            if(useStartTLSCheckBox.isEnabled()){
               mailPrefs.put("start tls", "enabled");
            }
            else{
                mailPrefs.put("start tls", "disabled");
            }
        
            mailPrefs.put("username 1", userNameTextBox.getText());
            mailPrefs.put("password 1", passwordTextBox.getText());
        
            mailPrefs.put("pop3 server", pop3ServerTextBox.getText());
            mailPrefs.put("port 2", portTextBox2.getText());

            if(doPriorCheckBox.isEnabled()){
                mailPrefs.put("do prior", "enabled");
            }
            else{
                mailPrefs.put("do prior", "disabled");
            }

        
            mailPrefs.put("username 2", userNameTextBox1.getText());
            mailPrefs.put("password 2", passwordTextBox1.getText());
        }
        
        if(internetPrefs == null){
            internetPrefs = new HashMap<String, String>();
            if(useProxyCheckBox.isEnabled()){
                internetPrefs.put("use proxy", "enabled");
            }
            else{
                internetPrefs.put("use proxy", "disabled");
            }
        
            internetPrefs.put("http proxy",httpProxyTextBox.getText());
            internetPrefs.put("username", userNAmeTextBox3.getText());
            internetPrefs.put("password",passwordTextBox4.getText());
            internetPrefs.put("port", portTextBox3.getText());
        }
        
        setPreferences();
        setMailPreferences();
        setInternetPreferences();
        
        if(eventsModel != null){
            eventsTable.setModel(eventsModel);
        }
        
        if(advancedModel != null){
            jTable1.setModel(advancedModel);
        }
        
        deleteButton.setEnabled(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Images/New Logo.png")));
        
    }
    



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        toolBar = new javax.swing.JPanel();
        helpButton = new javax.swing.JLabel();
        printButton = new javax.swing.JLabel();
        profilesButton = new javax.swing.JPanel();
        profilesText1 = new javax.swing.JLabel();
        profilesIcon = new javax.swing.JLabel();
        optionsButton = new javax.swing.JPanel();
        optionsText = new javax.swing.JLabel();
        optionsIcon = new javax.swing.JLabel();
        advancedButton = new javax.swing.JPanel();
        profilesText2 = new javax.swing.JLabel();
        advancedIcon = new javax.swing.JLabel();
        mailButton = new javax.swing.JPanel();
        profilesText4 = new javax.swing.JLabel();
        mailIcon = new javax.swing.JLabel();
        internetButton = new javax.swing.JPanel();
        profilesText5 = new javax.swing.JLabel();
        internetIcon = new javax.swing.JLabel();
        eventsButton = new javax.swing.JPanel();
        profilesText3 = new javax.swing.JLabel();
        eventIcon = new javax.swing.JLabel();
        settingsCardLayout = new javax.swing.JPanel();
        profile = new javax.swing.JPanel();
        profileBorder = new javax.swing.JPanel();
        profileText2 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        customizationButton = new javax.swing.JLabel();
        colorChoiceBorder = new javax.swing.JPanel();
        selectColorText = new javax.swing.JLabel();
        blue = new keeptoo.KGradientPanel();
        red = new keeptoo.KGradientPanel();
        orange = new keeptoo.KGradientPanel();
        yellow = new keeptoo.KGradientPanel();
        green = new keeptoo.KGradientPanel();
        dark = new keeptoo.KGradientPanel();
        purple = new keeptoo.KGradientPanel();
        options = new javax.swing.JPanel();
        optionsBorder = new javax.swing.JPanel();
        trayBarBorder = new javax.swing.JPanel();
        iconBackgroundText = new java.awt.Label();
        iconTextText = new java.awt.Label();
        iconBackgroundDropBox = new javax.swing.JComboBox<>();
        iconTextDropBox = new javax.swing.JComboBox<>();
        useFontText = new java.awt.Label();
        smallRadioButton = new javax.swing.JRadioButton();
        largeRadioButton = new javax.swing.JRadioButton();
        startMinimizedCheckBox = new java.awt.Checkbox();
        staticIconCheckBox = new java.awt.Checkbox();
        trayBarText = new java.awt.Label();
        languageText = new java.awt.Label();
        languageBorder = new javax.swing.JPanel();
        languageText2 = new java.awt.Label();
        languageDropBox = new javax.swing.JComboBox<>();
        deltaValueText = new java.awt.Label();
        fansBorder = new javax.swing.JPanel();
        deltaValueText2 = new java.awt.Label();
        deltaValueSpinner = new javax.swing.JSpinner();
        setFansCheckBox = new java.awt.Checkbox();
        dellSupportText = new java.awt.Label();
        dellSupportBorder = new javax.swing.JPanel();
        enableDellSupportCheckBox = new java.awt.Checkbox();
        dellSupportText1 = new java.awt.Label();
        sensorsBorder = new javax.swing.JPanel();
        isaBusCheckBox = new java.awt.Checkbox();
        smbusCheckBox = new java.awt.Checkbox();
        debugModeText = new java.awt.Label();
        debugModeBorder = new javax.swing.JPanel();
        debugModeCheckBox = new java.awt.Checkbox();
        temperatureText = new java.awt.Label();
        temperatureBorder = new javax.swing.JPanel();
        celsiusCheckBox = new java.awt.Checkbox();
        fahrenheitCheckBox = new java.awt.Checkbox();
        errorLogCheckBox = new java.awt.Checkbox();
        advanced = new javax.swing.JPanel();
        advancedBorder = new javax.swing.JPanel();
        chipText = new java.awt.Label();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        setToText = new javax.swing.JLabel();
        setToComboBox = new javax.swing.JComboBox<>();
        rememberCheckBox = new java.awt.Checkbox();
        events = new javax.swing.JPanel();
        eventsBorder = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        eventsTable = new javax.swing.JTable();
        eventBox = new javax.swing.JPanel();
        ifText = new java.awt.Label();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        measurementTextBox = new javax.swing.JTextField();
        forText = new javax.swing.JLabel();
        numberSpinner = new javax.swing.JSpinner();
        timesText = new javax.swing.JLabel();
        allowText = new javax.swing.JLabel();
        secondsSpinner = new javax.swing.JSpinner();
        secondsText = new javax.swing.JLabel();
        thenText = new javax.swing.JLabel();
        actionComboBox = new javax.swing.JComboBox<>();
        messageTextBox = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        mail = new javax.swing.JPanel();
        confirmBorder = new javax.swing.JPanel();
        profileText = new java.awt.Label();
        jComboBox4 = new javax.swing.JComboBox<>();
        line1 = new javax.swing.JPanel();
        mailFromText = new java.awt.Label();
        fromEmailTextBox = new javax.swing.JTextField();
        mailToText = new javax.swing.JLabel();
        toEmailTextBox = new javax.swing.JTextField();
        line2 = new javax.swing.JPanel();
        smtpserverText = new javax.swing.JLabel();
        smtpServerTextBox = new javax.swing.JTextField();
        portText = new java.awt.Label();
        portTextBox = new javax.swing.JTextField();
        useStartTLSCheckBox = new java.awt.Checkbox();
        line3 = new javax.swing.JPanel();
        userNameText = new java.awt.Label();
        userNameTextBox = new javax.swing.JTextField();
        passwordText = new java.awt.Label();
        passwordTextBox = new javax.swing.JTextField();
        pop3ServerText = new java.awt.Label();
        pop3ServerTextBox = new javax.swing.JTextField();
        portText1 = new java.awt.Label();
        portTextBox2 = new javax.swing.JTextField();
        doPriorCheckBox = new java.awt.Checkbox();
        userNameText1 = new java.awt.Label();
        userNameTextBox1 = new javax.swing.JTextField();
        passwordText1 = new java.awt.Label();
        passwordTextBox1 = new javax.swing.JTextField();
        line4 = new javax.swing.JPanel();
        sslInterfaceText = new java.awt.Label();
        sslInterfaceTextBox = new javax.swing.JTextField();
        sendTestMailButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        testMailBox = new javax.swing.JTextArea();
        internet = new javax.swing.JPanel();
        internetBorder = new javax.swing.JPanel();
        proxyBorder = new javax.swing.JPanel();
        httpProxyText = new java.awt.Label();
        httpProxyTextBox = new javax.swing.JTextField();
        portText3 = new javax.swing.JLabel();
        portTextBox3 = new javax.swing.JTextField();
        userNameText3 = new javax.swing.JLabel();
        userNAmeTextBox3 = new javax.swing.JTextField();
        passwordText4 = new javax.swing.JLabel();
        passwordTextBox4 = new javax.swing.JTextField();
        useProxyCheckBox = new java.awt.Checkbox();
        confirmBox = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Help icon.png"))); // NOI18N
        helpButton.setToolTipText("Help");
        helpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                helpButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                helpButtonMouseExited(evt);
            }
        });

        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print icon.png"))); // NOI18N
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
                .addGap(29, 29, 29)
                .addComponent(helpButton)
                .addGap(29, 29, 29)
                .addComponent(printButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        toolBarLayout.setVerticalGroup(
            toolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpButton, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
            .addComponent(printButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        profilesButton.setBackground(new java.awt.Color(0, 153, 255));
        profilesButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        profilesButton.setToolTipText("Enter profiles");
        profilesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilesButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profilesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profilesButtonMouseExited(evt);
            }
        });

        profilesText1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        profilesText1.setText("Profiles");

        profilesIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Profile.png"))); // NOI18N

        javax.swing.GroupLayout profilesButtonLayout = new javax.swing.GroupLayout(profilesButton);
        profilesButton.setLayout(profilesButtonLayout);
        profilesButtonLayout.setHorizontalGroup(
            profilesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profilesButtonLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(profilesIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profilesText1)
                .addGap(25, 25, 25))
        );
        profilesButtonLayout.setVerticalGroup(
            profilesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilesButtonLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(profilesButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(profilesText1)
                    .addComponent(profilesIcon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        optionsButton.setBackground(new java.awt.Color(153, 204, 255));
        optionsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        optionsButton.setToolTipText("Enter options");
        optionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optionsButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optionsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionsButtonMouseExited(evt);
            }
        });

        optionsText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        optionsText.setText("Options");

        optionsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Options.png"))); // NOI18N

        javax.swing.GroupLayout optionsButtonLayout = new javax.swing.GroupLayout(optionsButton);
        optionsButton.setLayout(optionsButtonLayout);
        optionsButtonLayout.setHorizontalGroup(
            optionsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionsButtonLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(optionsIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsText)
                .addGap(28, 28, 28))
        );
        optionsButtonLayout.setVerticalGroup(
            optionsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsButtonLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(optionsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(optionsText)
                    .addComponent(optionsIcon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        advancedButton.setBackground(new java.awt.Color(153, 204, 255));
        advancedButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        advancedButton.setToolTipText("Enter advanced");
        advancedButton.setPreferredSize(new java.awt.Dimension(135, 46));
        advancedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                advancedButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                advancedButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                advancedButtonMouseExited(evt);
            }
        });

        profilesText2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        profilesText2.setText("Advanced");

        advancedIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Advanced.png"))); // NOI18N

        javax.swing.GroupLayout advancedButtonLayout = new javax.swing.GroupLayout(advancedButton);
        advancedButton.setLayout(advancedButtonLayout);
        advancedButtonLayout.setHorizontalGroup(
            advancedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advancedButtonLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(advancedIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(profilesText2)
                .addGap(14, 14, 14))
        );
        advancedButtonLayout.setVerticalGroup(
            advancedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedButtonLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(advancedButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(profilesText2)
                    .addComponent(advancedIcon))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        mailButton.setBackground(new java.awt.Color(153, 204, 255));
        mailButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mailButton.setToolTipText("Enter mail");
        mailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mailButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mailButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mailButtonMouseExited(evt);
            }
        });

        profilesText4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        profilesText4.setText("Mail");

        mailIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Mail.png"))); // NOI18N

        javax.swing.GroupLayout mailButtonLayout = new javax.swing.GroupLayout(mailButton);
        mailButton.setLayout(mailButtonLayout);
        mailButtonLayout.setHorizontalGroup(
            mailButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mailButtonLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(mailIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profilesText4)
                .addGap(49, 49, 49))
        );
        mailButtonLayout.setVerticalGroup(
            mailButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mailButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mailButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(profilesText4)
                    .addComponent(mailIcon))
                .addGap(13, 13, 13))
        );

        internetButton.setBackground(new java.awt.Color(153, 204, 255));
        internetButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        internetButton.setToolTipText("Enter Internet");
        internetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                internetButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                internetButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                internetButtonMouseExited(evt);
            }
        });

        profilesText5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        profilesText5.setText("Internet");

        internetIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Internet.png"))); // NOI18N

        javax.swing.GroupLayout internetButtonLayout = new javax.swing.GroupLayout(internetButton);
        internetButton.setLayout(internetButtonLayout);
        internetButtonLayout.setHorizontalGroup(
            internetButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, internetButtonLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(internetIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profilesText5)
                .addGap(21, 21, 21))
        );
        internetButtonLayout.setVerticalGroup(
            internetButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internetButtonLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(internetButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(profilesText5)
                    .addComponent(internetIcon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        eventsButton.setBackground(new java.awt.Color(153, 204, 255));
        eventsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        eventsButton.setToolTipText("Enter events");
        eventsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventsButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eventsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                eventsButtonMouseExited(evt);
            }
        });

        profilesText3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        profilesText3.setText("Events");

        eventIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Event.png"))); // NOI18N

        javax.swing.GroupLayout eventsButtonLayout = new javax.swing.GroupLayout(eventsButton);
        eventsButton.setLayout(eventsButtonLayout);
        eventsButtonLayout.setHorizontalGroup(
            eventsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eventsButtonLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(eventIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profilesText3)
                .addGap(36, 36, 36))
        );
        eventsButtonLayout.setVerticalGroup(
            eventsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventsButtonLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(eventsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(profilesText3)
                    .addComponent(eventIcon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        settingsCardLayout.setLayout(new java.awt.CardLayout());

        profile.setBackground(new java.awt.Color(0, 51, 153));

        profileBorder.setBackground(new java.awt.Color(255, 255, 255));

        profileText2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        profileText2.setText("Profile");

        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Beginner", "Advanced", "Expert" }));
        jComboBox5.setToolTipText("Rate yourself in terms of experience using SpeedFan");
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });

        customizationButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        customizationButton.setText("Customization");

        colorChoiceBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        selectColorText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectColorText.setText("Select a Color Scheme");

        blue.setToolTipText("Blue");
        blue.setkEndColor(new java.awt.Color(0, 51, 204));
        blue.setkGradientFocus(200);
        blue.setkStartColor(new java.awt.Color(153, 204, 255));
        blue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                blueMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout blueLayout = new javax.swing.GroupLayout(blue);
        blue.setLayout(blueLayout);
        blueLayout.setHorizontalGroup(
            blueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        blueLayout.setVerticalGroup(
            blueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        red.setToolTipText("Red");
        red.setkEndColor(new java.awt.Color(255, 0, 51));
        red.setkGradientFocus(200);
        red.setkStartColor(new java.awt.Color(255, 153, 153));
        red.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                redMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout redLayout = new javax.swing.GroupLayout(red);
        red.setLayout(redLayout);
        redLayout.setHorizontalGroup(
            redLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        redLayout.setVerticalGroup(
            redLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        orange.setToolTipText("Orange");
        orange.setkEndColor(new java.awt.Color(255, 102, 0));
        orange.setkGradientFocus(200);
        orange.setkStartColor(new java.awt.Color(255, 204, 153));
        orange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orangeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout orangeLayout = new javax.swing.GroupLayout(orange);
        orange.setLayout(orangeLayout);
        orangeLayout.setHorizontalGroup(
            orangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        orangeLayout.setVerticalGroup(
            orangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        yellow.setToolTipText("Yellow");
        yellow.setkEndColor(new java.awt.Color(255, 204, 0));
        yellow.setkGradientFocus(200);
        yellow.setkStartColor(new java.awt.Color(255, 255, 153));
        yellow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yellowMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout yellowLayout = new javax.swing.GroupLayout(yellow);
        yellow.setLayout(yellowLayout);
        yellowLayout.setHorizontalGroup(
            yellowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        yellowLayout.setVerticalGroup(
            yellowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        green.setToolTipText("Green");
        green.setkEndColor(new java.awt.Color(0, 153, 51));
        green.setkGradientFocus(200);
        green.setkStartColor(new java.awt.Color(0, 255, 51));
        green.setPreferredSize(new java.awt.Dimension(150, 80));
        green.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                greenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout greenLayout = new javax.swing.GroupLayout(green);
        green.setLayout(greenLayout);
        greenLayout.setHorizontalGroup(
            greenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        greenLayout.setVerticalGroup(
            greenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        dark.setToolTipText("Dark");
        dark.setkEndColor(new java.awt.Color(51, 51, 51));
        dark.setkGradientFocus(200);
        dark.setkStartColor(new java.awt.Color(153, 153, 153));
        dark.setPreferredSize(new java.awt.Dimension(150, 80));
        dark.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                darkMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout darkLayout = new javax.swing.GroupLayout(dark);
        dark.setLayout(darkLayout);
        darkLayout.setHorizontalGroup(
            darkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        darkLayout.setVerticalGroup(
            darkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        purple.setToolTipText("Purple");
        purple.setkEndColor(new java.awt.Color(102, 0, 102));
        purple.setkGradientFocus(200);
        purple.setkStartColor(new java.awt.Color(255, 51, 255));
        purple.setPreferredSize(new java.awt.Dimension(150, 80));
        purple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purpleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout purpleLayout = new javax.swing.GroupLayout(purple);
        purple.setLayout(purpleLayout);
        purpleLayout.setHorizontalGroup(
            purpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        purpleLayout.setVerticalGroup(
            purpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout colorChoiceBorderLayout = new javax.swing.GroupLayout(colorChoiceBorder);
        colorChoiceBorder.setLayout(colorChoiceBorderLayout);
        colorChoiceBorderLayout.setHorizontalGroup(
            colorChoiceBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorChoiceBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(colorChoiceBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectColorText)
                    .addGroup(colorChoiceBorderLayout.createSequentialGroup()
                        .addGroup(colorChoiceBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(blue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(green, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(colorChoiceBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(red, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(colorChoiceBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orange, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purple, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(yellow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        colorChoiceBorderLayout.setVerticalGroup(
            colorChoiceBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, colorChoiceBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectColorText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(colorChoiceBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yellow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(red, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orange, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(colorChoiceBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(green, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(purple, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout profileBorderLayout = new javax.swing.GroupLayout(profileBorder);
        profileBorder.setLayout(profileBorderLayout);
        profileBorderLayout.setHorizontalGroup(
            profileBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileBorderLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(profileBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customizationButton)
                    .addGroup(profileBorderLayout.createSequentialGroup()
                        .addComponent(profileText2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(colorChoiceBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        profileBorderLayout.setVerticalGroup(
            profileBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileBorderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(profileBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profileText2)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(customizationButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorChoiceBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout profileLayout = new javax.swing.GroupLayout(profile);
        profile.setLayout(profileLayout);
        profileLayout.setHorizontalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        profileLayout.setVerticalGroup(
            profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        settingsCardLayout.add(profile, "card2");

        options.setBackground(new java.awt.Color(0, 51, 153));

        trayBarBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        iconBackgroundText.setText("Icon Background");

        iconTextText.setText("Icon Text");

        iconBackgroundDropBox.setEditable(true);
        iconBackgroundDropBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Lime", "Yellow", "Blue", "Fuchsia", "Aqua", "White", "None" }));

        iconTextDropBox.setEditable(true);
        iconTextDropBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Lime", "Yellow", "Blue", "Fuchsia", "Aqua", "White", "None" }));

        useFontText.setText("Use Font");

        smallRadioButton.setSelected(true);
        smallRadioButton.setText("Small");
        smallRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smallRadioButtonActionPerformed(evt);
            }
        });

        largeRadioButton.setText("Large");
        largeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                largeRadioButtonActionPerformed(evt);
            }
        });

        startMinimizedCheckBox.setLabel("Start Minimized");

        staticIconCheckBox.setLabel("Static Icon");

        javax.swing.GroupLayout trayBarBorderLayout = new javax.swing.GroupLayout(trayBarBorder);
        trayBarBorder.setLayout(trayBarBorderLayout);
        trayBarBorderLayout.setHorizontalGroup(
            trayBarBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trayBarBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(trayBarBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(trayBarBorderLayout.createSequentialGroup()
                        .addComponent(iconBackgroundText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iconBackgroundDropBox, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(trayBarBorderLayout.createSequentialGroup()
                        .addComponent(iconTextText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconTextDropBox, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addComponent(useFontText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(trayBarBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(smallRadioButton)
                    .addComponent(largeRadioButton))
                .addGap(84, 84, 84)
                .addGroup(trayBarBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staticIconCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startMinimizedCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        trayBarBorderLayout.setVerticalGroup(
            trayBarBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trayBarBorderLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(trayBarBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startMinimizedCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconBackgroundText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconBackgroundDropBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(useFontText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(smallRadioButton))
                .addGroup(trayBarBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(trayBarBorderLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(trayBarBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(iconTextText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iconTextDropBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(trayBarBorderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(trayBarBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(staticIconCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(largeRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        trayBarText.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        trayBarText.setName(""); // NOI18N
        trayBarText.setText("Tray Bar");

        languageText.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        languageText.setName(""); // NOI18N
        languageText.setText("Language");

        languageBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        languageText2.setText("Language");

        languageDropBox.setEditable(true);
        languageDropBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Italiano", "Spanish", "Glacian", "Polish", "Czech", "Russian", "German" }));

        javax.swing.GroupLayout languageBorderLayout = new javax.swing.GroupLayout(languageBorder);
        languageBorder.setLayout(languageBorderLayout);
        languageBorderLayout.setHorizontalGroup(
            languageBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(languageBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(languageText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(languageDropBox, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        languageBorderLayout.setVerticalGroup(
            languageBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(languageBorderLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(languageBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(languageText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(languageDropBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        deltaValueText.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        deltaValueText.setName(""); // NOI18N
        deltaValueText.setText("Fans");

        fansBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        deltaValueText2.setText("Delta Value for fan speeds");

        setFansCheckBox.setLabel("Set Fans to 100% on exit");

        javax.swing.GroupLayout fansBorderLayout = new javax.swing.GroupLayout(fansBorder);
        fansBorder.setLayout(fansBorderLayout);
        fansBorderLayout.setHorizontalGroup(
            fansBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fansBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deltaValueText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deltaValueSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(setFansCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
        );
        fansBorderLayout.setVerticalGroup(
            fansBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fansBorderLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(fansBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(setFansCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deltaValueSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deltaValueText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dellSupportText.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dellSupportText.setName(""); // NOI18N
        dellSupportText.setText("Dell Support");

        dellSupportBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        enableDellSupportCheckBox.setLabel("Enable DELL Support");
        enableDellSupportCheckBox.setName(""); // NOI18N

        javax.swing.GroupLayout dellSupportBorderLayout = new javax.swing.GroupLayout(dellSupportBorder);
        dellSupportBorder.setLayout(dellSupportBorderLayout);
        dellSupportBorderLayout.setHorizontalGroup(
            dellSupportBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dellSupportBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enableDellSupportCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        dellSupportBorderLayout.setVerticalGroup(
            dellSupportBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dellSupportBorderLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(enableDellSupportCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dellSupportText1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dellSupportText1.setName(""); // NOI18N
        dellSupportText1.setText("Let SpeedFan access sensors through");

        sensorsBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        isaBusCheckBox.setLabel("ISA BUS");
        isaBusCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                isaBusCheckBoxItemStateChanged(evt);
            }
        });

        smbusCheckBox.setLabel("SMBus");
        smbusCheckBox.setState(true);
        smbusCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                smbusCheckBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout sensorsBorderLayout = new javax.swing.GroupLayout(sensorsBorder);
        sensorsBorder.setLayout(sensorsBorderLayout);
        sensorsBorderLayout.setHorizontalGroup(
            sensorsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorsBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(isaBusCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(smbusCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );
        sensorsBorderLayout.setVerticalGroup(
            sensorsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sensorsBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sensorsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(smbusCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isaBusCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        debugModeText.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        debugModeText.setName(""); // NOI18N
        debugModeText.setText("Debug Mode");

        debugModeBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        debugModeCheckBox.setLabel("Enabled");

        javax.swing.GroupLayout debugModeBorderLayout = new javax.swing.GroupLayout(debugModeBorder);
        debugModeBorder.setLayout(debugModeBorderLayout);
        debugModeBorderLayout.setHorizontalGroup(
            debugModeBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(debugModeBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(debugModeCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        debugModeBorderLayout.setVerticalGroup(
            debugModeBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(debugModeBorderLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(debugModeCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        temperatureText.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        temperatureText.setName(""); // NOI18N
        temperatureText.setText("Temperature");

        temperatureBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        celsiusCheckBox.setLabel("Celsius");
        celsiusCheckBox.setState(true);
        celsiusCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                celsiusCheckBoxItemStateChanged(evt);
            }
        });

        fahrenheitCheckBox.setLabel("Fahrenheit");
        fahrenheitCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fahrenheitCheckBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout temperatureBorderLayout = new javax.swing.GroupLayout(temperatureBorder);
        temperatureBorder.setLayout(temperatureBorderLayout);
        temperatureBorderLayout.setHorizontalGroup(
            temperatureBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(temperatureBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(celsiusCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(fahrenheitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        temperatureBorderLayout.setVerticalGroup(
            temperatureBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(temperatureBorderLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(temperatureBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fahrenheitCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celsiusCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        errorLogCheckBox.setLabel("Do Summary Error Log scan on startup");

        javax.swing.GroupLayout optionsBorderLayout = new javax.swing.GroupLayout(optionsBorder);
        optionsBorder.setLayout(optionsBorderLayout);
        optionsBorderLayout.setHorizontalGroup(
            optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsBorderLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(optionsBorderLayout.createSequentialGroup()
                        .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dellSupportText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(optionsBorderLayout.createSequentialGroup()
                                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(languageText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(languageBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dellSupportBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errorLogCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(debugModeBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(debugModeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(optionsBorderLayout.createSequentialGroup()
                                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(temperatureText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(temperatureBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deltaValueText, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fansBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(optionsBorderLayout.createSequentialGroup()
                        .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dellSupportText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trayBarText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trayBarBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sensorsBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE))))
        );
        optionsBorderLayout.setVerticalGroup(
            optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(trayBarText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(trayBarBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(languageText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(debugModeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(languageBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(debugModeBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(dellSupportText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dellSupportBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(optionsBorderLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(errorLogCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(temperatureText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deltaValueText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(optionsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(temperatureBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fansBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dellSupportText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sensorsBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout optionsLayout = new javax.swing.GroupLayout(options);
        options.setLayout(optionsLayout);
        optionsLayout.setHorizontalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        optionsLayout.setVerticalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        settingsCardLayout.add(options, "card3");

        advanced.setBackground(new java.awt.Color(0, 51, 153));

        chipText.setText("Chip");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HD0 (128.0GB) at $0 on AdvSMART", "ACPI at $0 on ISA", "INTEL CORE at $0 on ISA", "ITE IT8712F" }));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Temperature sensor diode 1", "Thermistor", null},
                {"Temperature sensor diode 2", "Thermistor", null},
                {"Temperature sensor diode 3", "Thermistor", null},
                {"FAN1 divisor", "2", null},
                {"FAN2 divisor", "2", null},
                {"FAN3 divisor", "2", null},
                {"PWM 1 mode", "ON/OFF", null},
                {"PWM 2 mode", "SmartGuardian", null},
                {"PWM 3 mode", "Software controlled", null},
                {"PWMOUT clock", "48M", null},
                {"Temperature 1 offset", "0", null},
                {"Temperature 2 offset", "0", null},
                {"Temperature 3 offset", "0", null},
                {"FAN1 mult", "1", null},
                {"FAN1 div", "1", null},
                {"FAN2 mult", "1", null},
                {"FAN2 div", "1", null},
                {"FAN3 mult", "1", null},
                {"FAN3 div", "1", null},
                {"Reverse PWM01 logic", "OFF", null},
                {"Reverse PWM02 logic", "OFF", null},
                {"Reverse PWM03 logic", "OFF", null}
            },
            new String [] {
                "Property", "Value", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        setToText.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setToText.setText("Set to");

        setToComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "128", "127", "126", "125", "124", "123", "122", "121", "120", "119", "118", "117", "116", "115", "114", "113", "112", "111", "110", "109", "108", "107" }));

        rememberCheckBox.setLabel("Remember it");

        javax.swing.GroupLayout advancedBorderLayout = new javax.swing.GroupLayout(advancedBorder);
        advancedBorder.setLayout(advancedBorderLayout);
        advancedBorderLayout.setHorizontalGroup(
            advancedBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedBorderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(advancedBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(advancedBorderLayout.createSequentialGroup()
                        .addGroup(advancedBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(advancedBorderLayout.createSequentialGroup()
                                .addComponent(chipText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(advancedBorderLayout.createSequentialGroup()
                        .addComponent(setToText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(setToComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(rememberCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );
        advancedBorderLayout.setVerticalGroup(
            advancedBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(advancedBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chipText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(advancedBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(advancedBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(setToText)
                        .addComponent(setToComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rememberCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout advancedLayout = new javax.swing.GroupLayout(advanced);
        advanced.setLayout(advancedLayout);
        advancedLayout.setHorizontalGroup(
            advancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(advancedBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        advancedLayout.setVerticalGroup(
            advancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(advancedBorder, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        settingsCardLayout.add(advanced, "card4");

        events.setBackground(new java.awt.Color(0, 51, 153));

        eventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reading", "Kind", "Origin", "Check", "Action", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(eventsTable);

        eventBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ifText.setText("If");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Temp1 (temp) from ACPI", "Core0 (temp) from INTEL CORE", "Core1 (temp) from INTEL CORE", "Core2 (temp) from INTEL CORE", "Core3 (temp) from INTEL CORE", "CPU fan (fan) from WinbondW836875", "Power Fan (fan) from WinbondW836875" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<", "<=", ">", ">=" }));

        forText.setText("For");

        timesText.setText("times");

        allowText.setText("Allow every");

        secondsText.setText("seconds");

        thenText.setText("Then");

        actionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "popup message", "execute", "beep", "send mail" }));

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout eventBoxLayout = new javax.swing.GroupLayout(eventBox);
        eventBox.setLayout(eventBoxLayout);
        eventBoxLayout.setHorizontalGroup(
            eventBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eventBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(eventBoxLayout.createSequentialGroup()
                        .addComponent(ifText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(eventBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(eventBoxLayout.createSequentialGroup()
                                .addComponent(forText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timesText)
                                .addGap(51, 51, 51)
                                .addComponent(allowText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secondsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secondsText)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(eventBoxLayout.createSequentialGroup()
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(measurementTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(eventBoxLayout.createSequentialGroup()
                        .addComponent(thenText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(messageTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton)
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        eventBoxLayout.setVerticalGroup(
            eventBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eventBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eventBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(measurementTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ifText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(eventBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forText)
                    .addComponent(numberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timesText)
                    .addComponent(allowText)
                    .addComponent(secondsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondsText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(eventBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thenText)
                    .addComponent(actionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(messageTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(deleteButton))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout eventsBorderLayout = new javax.swing.GroupLayout(eventsBorder);
        eventsBorder.setLayout(eventsBorderLayout);
        eventsBorderLayout.setHorizontalGroup(
            eventsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventsBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eventsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(eventBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        eventsBorderLayout.setVerticalGroup(
            eventsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventsBorderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eventBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout eventsLayout = new javax.swing.GroupLayout(events);
        events.setLayout(eventsLayout);
        eventsLayout.setHorizontalGroup(
            eventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eventsBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        eventsLayout.setVerticalGroup(
            eventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eventsBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        settingsCardLayout.add(events, "card5");

        mail.setBackground(new java.awt.Color(0, 51, 153));

        confirmBorder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        profileText.setText("Profile");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default" }));

        line1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout line1Layout = new javax.swing.GroupLayout(line1);
        line1.setLayout(line1Layout);
        line1Layout.setHorizontalGroup(
            line1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        line1Layout.setVerticalGroup(
            line1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        mailFromText.setText("Mail from");

        mailToText.setText("Mail to");

        line2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout line2Layout = new javax.swing.GroupLayout(line2);
        line2.setLayout(line2Layout);
        line2Layout.setHorizontalGroup(
            line2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        line2Layout.setVerticalGroup(
            line2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        smtpserverText.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        smtpserverText.setText("SMTP Server");

        portText.setText("Port");

        portTextBox.setText("25");

        useStartTLSCheckBox.setLabel("Use StartTLS");

        line3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout line3Layout = new javax.swing.GroupLayout(line3);
        line3.setLayout(line3Layout);
        line3Layout.setHorizontalGroup(
            line3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        line3Layout.setVerticalGroup(
            line3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        userNameText.setText("User Name");

        passwordText.setText("Password");

        pop3ServerText.setText("POP3 Server");

        portText1.setText("Port");

        portTextBox2.setText("25");

        doPriorCheckBox.setLabel("Do a prior POP3 check");

        userNameText1.setText("User Name");

        passwordText1.setText("Password");

        line4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout line4Layout = new javax.swing.GroupLayout(line4);
        line4.setLayout(line4Layout);
        line4Layout.setHorizontalGroup(
            line4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        line4Layout.setVerticalGroup(
            line4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        sslInterfaceText.setText("SSL Interface");

        sslInterfaceTextBox.setBackground(new java.awt.Color(204, 204, 204));
        sslInterfaceTextBox.setText("OpenSSL 1.02d 9 Jul 2015");

        sendTestMailButton.setText("Send Test Mail");
        sendTestMailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendTestMailButtonActionPerformed(evt);
            }
        });

        testMailBox.setColumns(20);
        testMailBox.setRows(5);
        jScrollPane3.setViewportView(testMailBox);

        javax.swing.GroupLayout confirmBorderLayout = new javax.swing.GroupLayout(confirmBorder);
        confirmBorder.setLayout(confirmBorderLayout);
        confirmBorderLayout.setHorizontalGroup(
            confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmBorderLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addGroup(confirmBorderLayout.createSequentialGroup()
                        .addComponent(sslInterfaceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sslInterfaceTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendTestMailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(line4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(line3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(line2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(confirmBorderLayout.createSequentialGroup()
                        .addComponent(mailFromText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(fromEmailTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(mailToText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toEmailTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(line1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(confirmBorderLayout.createSequentialGroup()
                        .addComponent(profileText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(confirmBorderLayout.createSequentialGroup()
                        .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(smtpserverText)
                            .addComponent(userNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(smtpServerTextBox)
                            .addComponent(userNameTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(confirmBorderLayout.createSequentialGroup()
                                .addComponent(portText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(portTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(useStartTLSCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(confirmBorderLayout.createSequentialGroup()
                                .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(confirmBorderLayout.createSequentialGroup()
                        .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pop3ServerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pop3ServerTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(userNameTextBox1))
                        .addGap(29, 29, 29)
                        .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(confirmBorderLayout.createSequentialGroup()
                                .addComponent(portText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(portTextBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(doPriorCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(confirmBorderLayout.createSequentialGroup()
                                .addComponent(passwordText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordTextBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        confirmBorderLayout.setVerticalGroup(
            confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmBorderLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profileText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mailFromText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fromEmailTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mailToText)
                        .addComponent(toEmailTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(smtpserverText)
                        .addComponent(smtpServerTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(portText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(useStartTLSCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(line3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pop3ServerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pop3ServerTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portTextBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doPriorCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userNameText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameTextBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordTextBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(line4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sslInterfaceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(confirmBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sslInterfaceTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sendTestMailButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mailLayout = new javax.swing.GroupLayout(mail);
        mail.setLayout(mailLayout);
        mailLayout.setHorizontalGroup(
            mailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(confirmBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mailLayout.setVerticalGroup(
            mailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(confirmBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        settingsCardLayout.add(mail, "card6");

        internet.setBackground(new java.awt.Color(0, 51, 153));

        internetBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        proxyBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        httpProxyText.setText("HTTP Proxy");

        portText3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        portText3.setText("Port");

        userNameText3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        userNameText3.setText("Username");

        passwordText4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        passwordText4.setText("Password");

        javax.swing.GroupLayout proxyBorderLayout = new javax.swing.GroupLayout(proxyBorder);
        proxyBorder.setLayout(proxyBorderLayout);
        proxyBorderLayout.setHorizontalGroup(
            proxyBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proxyBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proxyBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(httpProxyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameText3)
                    .addComponent(passwordText4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(proxyBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordTextBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(proxyBorderLayout.createSequentialGroup()
                        .addComponent(httpProxyTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(portText3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(portTextBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(userNAmeTextBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        proxyBorderLayout.setVerticalGroup(
            proxyBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proxyBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proxyBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(proxyBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(httpProxyTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(portText3)
                        .addComponent(portTextBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(httpProxyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(proxyBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(proxyBorderLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(userNameText3))
                    .addGroup(proxyBorderLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(userNAmeTextBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(proxyBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordText4)
                    .addComponent(passwordTextBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        useProxyCheckBox.setLabel("Use Proxy");

        javax.swing.GroupLayout internetBorderLayout = new javax.swing.GroupLayout(internetBorder);
        internetBorder.setLayout(internetBorderLayout);
        internetBorderLayout.setHorizontalGroup(
            internetBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internetBorderLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(internetBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useProxyCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proxyBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        internetBorderLayout.setVerticalGroup(
            internetBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internetBorderLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(useProxyCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proxyBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout internetLayout = new javax.swing.GroupLayout(internet);
        internet.setLayout(internetLayout);
        internetLayout.setHorizontalGroup(
            internetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(internetBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
        internetLayout.setVerticalGroup(
            internetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internetLayout.createSequentialGroup()
                .addComponent(internetBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        settingsCardLayout.add(internet, "card7");

        confirmBox.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Cross.png"))); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setToolTipText("Cancel your changes?");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });

        okButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Tick.png"))); // NOI18N
        okButton.setText("Apply");
        okButton.setToolTipText("Save your changes?");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout confirmBoxLayout = new javax.swing.GroupLayout(confirmBox);
        confirmBox.setLayout(confirmBoxLayout);
        confirmBoxLayout.setHorizontalGroup(
            confirmBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, confirmBoxLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        confirmBoxLayout.setVerticalGroup(
            confirmBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmBoxLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(confirmBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(profilesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(optionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(advancedButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(eventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mailButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(internetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(settingsCardLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(confirmBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(optionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(advancedButton, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(mailButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(internetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eventsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(profilesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(settingsCardLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setHome(Home home)
    {
        if(home != null)
        {
            this.home = home;
            home.setSettings(Settings.this);
        }

        
        
    }
    
    public void setHelp(Help help)
    {
        if(help != null)
        {
            helpOpened = true;
            this.help = help;
            this.help.setSettings(Settings.this);
        }
        else
        {
            helpOpened = false;
            this.help = null;
        }
    }
    
    private void helpButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseEntered
        helpButton.addMouseListener(new MouseAdapter()
            {
                public void mouseEntered(MouseEvent e)
                {
                    helpButton.setBorder(new LineBorder(Color.WHITE));
                }
            });
    }//GEN-LAST:event_helpButtonMouseEntered

    private void helpButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseExited
        helpButton.addMouseListener(new MouseAdapter()
            {
                public void mouseExited(MouseEvent e)
                {
                    helpButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                }
            });
    }//GEN-LAST:event_helpButtonMouseExited

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

    private void profilesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilesButtonMouseEntered
        profilesButton.addMouseListener(new MouseAdapter()
        {
           public void mouseEntered(MouseEvent e)
           {
               profilesButton.setBackground(setColor("DARKEST"));
           }
        });
    }//GEN-LAST:event_profilesButtonMouseEntered

    private void profilesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilesButtonMouseExited

        profilesButton.addMouseListener(new MouseAdapter()
        {
           public void mouseExited(MouseEvent e)
           {
               if(STATE != PROFILE)
               {    
                    profilesButton.setBackground(setColor("BUTTON"));
               }
           }
        });
    }//GEN-LAST:event_profilesButtonMouseExited

    private void optionsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsButtonMouseEntered
        optionsButton.addMouseListener(new MouseAdapter()
        {
           public void mouseEntered(MouseEvent e)
           {
               optionsButton.setBackground(setColor("DARKEST"));
           }
        });
    }//GEN-LAST:event_optionsButtonMouseEntered

    private void optionsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsButtonMouseExited
        optionsButton.addMouseListener(new MouseAdapter()
        {
           public void mouseExited(MouseEvent e)
           {
               if(STATE != OPTIONS)
               {
                    optionsButton.setBackground(setColor("BUTTON"));
               }
           }
        });
    }//GEN-LAST:event_optionsButtonMouseExited

    private void advancedButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advancedButtonMouseEntered
        advancedButton.addMouseListener(new MouseAdapter()
        {
           public void mouseEntered(MouseEvent e)
           {
               if(PROFILE_STATE == EXPERT_USER)
               {
                    advancedButton.setBackground(setColor("DARKEST"));
               }
           }
        });
    }//GEN-LAST:event_advancedButtonMouseEntered

    private void advancedButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advancedButtonMouseExited
        advancedButton.addMouseListener(new MouseAdapter()
        {
           public void mouseExited(MouseEvent e)
           {
               if(STATE != ADVANCED)
               {
                    if(PROFILE_STATE == EXPERT_USER)
                    {
                        advancedButton.setBackground(setColor("BUTTON"));
                    }
               }
           }
        });
    }//GEN-LAST:event_advancedButtonMouseExited

    private void eventsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventsButtonMouseEntered
        eventsButton.addMouseListener(new MouseAdapter()
        {
           public void mouseEntered(MouseEvent e)
           {
               if(PROFILE_STATE != BEGINNER_USER)
               {
                    eventsButton.setBackground(setColor("DARKEST"));
               }
           }
        });
    }//GEN-LAST:event_eventsButtonMouseEntered

    private void eventsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventsButtonMouseExited
        eventsButton.addMouseListener(new MouseAdapter()
        {
           public void mouseExited(MouseEvent e)
           {
               if(STATE != EVENTS)
               {    
                    if(PROFILE_STATE != BEGINNER_USER)
                    {
                        eventsButton.setBackground(setColor("BUTTON"));
                    }
               }
           }
        });
    }//GEN-LAST:event_eventsButtonMouseExited

    private void mailButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailButtonMouseEntered
        mailButton.addMouseListener(new MouseAdapter()
        {
           public void mouseEntered(MouseEvent e)
           {
               
               mailButton.setBackground(setColor("DARKEST"));
           }
        });
    }//GEN-LAST:event_mailButtonMouseEntered

    private void mailButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailButtonMouseExited
        mailButton.addMouseListener(new MouseAdapter()
        {
           public void mouseExited(MouseEvent e)
           {
               if(STATE != MAIL)
               {
                    mailButton.setBackground(setColor("BUTTON"));
               }
           }
        });
    }//GEN-LAST:event_mailButtonMouseExited

    private void internetButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_internetButtonMouseEntered
        internetButton.addMouseListener(new MouseAdapter()
        {
           public void mouseEntered(MouseEvent e)
           {
               internetButton.setBackground(setColor("DARKEST"));
           }
        });
    }//GEN-LAST:event_internetButtonMouseEntered

    private void internetButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_internetButtonMouseExited
        internetButton.addMouseListener(new MouseAdapter()
        {
           public void mouseExited(MouseEvent e)
           {
               if(STATE != INTERNET)
               {    
                    internetButton.setBackground(setColor("BUTTON"));
               }
           }
        });
    }//GEN-LAST:event_internetButtonMouseExited

    private void profilesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilesButtonMouseClicked
       setPreferences();
       setMailPreferences();
       setInternetPreferences();
       
        changeColorScheme(colorState);
        settingsCardLayout.removeAll();
       
       //Add profile panel
       settingsCardLayout.add(profile);
       settingsCardLayout.repaint();
       settingsCardLayout.revalidate();
       
       STATE = PROFILE;
       
       profilesButton.setBackground(setColor("DARKEST"));
       optionsButton.setBackground(setColor("BUTTON"));
       
       if(PROFILE_STATE == EXPERT_USER)
       {
            advancedButton.setBackground(setColor("BUTTON"));
       }
       
       if(PROFILE_STATE != BEGINNER_USER)
       {
            eventsButton.setBackground(setColor("BUTTON"));
       }
       
       mailButton.setBackground(setColor("BUTTON"));
       internetButton.setBackground(setColor("BUTTON"));
    }//GEN-LAST:event_profilesButtonMouseClicked

    private void optionsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionsButtonMouseClicked
       setPreferences();
       
       setMailPreferences();
       setInternetPreferences();
        changeColorScheme(colorState);
        settingsCardLayout.removeAll();
       
       settingsCardLayout.add(options);
       settingsCardLayout.repaint();
       settingsCardLayout.revalidate();
       
       STATE = OPTIONS;
       
       profilesButton.setBackground(setColor("BUTTON"));
       optionsButton.setBackground(setColor("DARKEST"));
       if(PROFILE_STATE == EXPERT_USER)
       {
            advancedButton.setBackground(setColor("BUTTON"));
       }
       
       if(PROFILE_STATE != BEGINNER_USER)
       {
            eventsButton.setBackground(setColor("BUTTON"));
       }
       mailButton.setBackground(setColor("BUTTON"));
       internetButton.setBackground(setColor("BUTTON"));
    }//GEN-LAST:event_optionsButtonMouseClicked

    private void advancedButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_advancedButtonMouseClicked
       setPreferences();
       setMailPreferences();
       setInternetPreferences();
        changeColorScheme(colorState);
        if(PROFILE_STATE == EXPERT_USER)
       {
            settingsCardLayout.removeAll();
       
            settingsCardLayout.add(advanced);
            settingsCardLayout.repaint();
            settingsCardLayout.revalidate();
      
            JOptionPane.showMessageDialog(null, "Warning! This feature can change your computer's hardware and could potentially be dangerous. Uee with caustion!", "Warning", JOptionPane.WARNING_MESSAGE);
            
            STATE = ADVANCED;
       
            profilesButton.setBackground(setColor("BUTTON"));
            optionsButton.setBackground(setColor("BUTTON"));
            advancedButton.setBackground(setColor("DARKEST"));

       
            if(PROFILE_STATE != BEGINNER_USER)
            {
                eventsButton.setBackground(setColor("BUTTON"));
            }
            mailButton.setBackground(setColor("BUTTON"));
            internetButton.setBackground(setColor("BUTTON"));
       }
       else
       {
           JOptionPane.showMessageDialog(null, "You do not have permission to use this feature. To gain access, change profile to Expert User mode.");
       }
    }//GEN-LAST:event_advancedButtonMouseClicked

    private void eventsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventsButtonMouseClicked
       setPreferences();
       setMailPreferences();
       setInternetPreferences();
       changeColorScheme(colorState);
        if(PROFILE_STATE != BEGINNER_USER)
       {
            settingsCardLayout.removeAll();
       
            settingsCardLayout.add(events);
            settingsCardLayout.repaint();
            settingsCardLayout.revalidate();

            STATE = EVENTS;

            if(firstTimeEvents){
                JOptionPane.showMessageDialog(null, "This panel allows you to set events if a certain condition occurs. e.g. It can display a message if temperature of a component reaches 40 C.");
                firstTimeEvents = false;
            }
            
            profilesButton.setBackground(setColor("BUTTON"));
            optionsButton.setBackground(setColor("BUTTON"));
            if(PROFILE_STATE == EXPERT_USER)
            {
                advancedButton.setBackground(setColor("BUTTON"));
            }
       
            if(PROFILE_STATE != BEGINNER_USER)
            {
                eventsButton.setBackground(setColor("DARKEST"));
            }
            
            mailButton.setBackground(setColor("BUTTON"));
            internetButton.setBackground(setColor("BUTTON"));
       }
       else
       {
           JOptionPane.showMessageDialog(null, "You do not have permission to use this feature. To gain access, change profile to Advanced or Expert User mode.");
       }
    }//GEN-LAST:event_eventsButtonMouseClicked

    private void mailButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mailButtonMouseClicked
       setPreferences();
       setMailPreferences();
       setInternetPreferences();
       
        changeColorScheme(colorState);
        settingsCardLayout.removeAll();
       
       settingsCardLayout.add(mail);
       settingsCardLayout.repaint();
       settingsCardLayout.revalidate();
       
       STATE = MAIL;
       
       if(firstTimeMail){
           JOptionPane.showMessageDialog(null, "This feature allows you to save email details so that you won't have to re-enter them when sending emails from SpeedFan.");
           firstTimeMail = false;
       }
       
       profilesButton.setBackground(setColor("BUTTON"));
       optionsButton.setBackground(setColor("BUTTON"));
       if(PROFILE_STATE == EXPERT_USER)
       {
            advancedButton.setBackground(setColor("BUTTON"));
       }
       
       if(PROFILE_STATE != BEGINNER_USER)
       {
            eventsButton.setBackground(setColor("BUTTON"));
       }
       mailButton.setBackground(setColor("DARKEST"));
       internetButton.setBackground(setColor("BUTTON"));
    }//GEN-LAST:event_mailButtonMouseClicked

    private void internetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_internetButtonMouseClicked
       setPreferences();
       setMailPreferences();
       setInternetPreferences();
        changeColorScheme(colorState);
        settingsCardLayout.removeAll();
       
       settingsCardLayout.add(internet);
       settingsCardLayout.repaint();
       settingsCardLayout.revalidate();
       
       STATE = INTERNET;
       
       profilesButton.setBackground(setColor("BUTTON"));
       optionsButton.setBackground(setColor("BUTTON"));
       if(PROFILE_STATE == EXPERT_USER)
       {
            advancedButton.setBackground(setColor("BUTTON"));
       }
       
       if(PROFILE_STATE != BEGINNER_USER)
       {
            eventsButton.setBackground(setColor("BUTTON"));
       }
       mailButton.setBackground(setColor("BUTTON"));
       internetButton.setBackground(setColor("DARKEST"));
    }//GEN-LAST:event_internetButtonMouseClicked

    private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
        //Change Color
        colorState = tempColorState;
        oldColorState = 100;
        
        home.setColorScheme(colorState);
      
        if(help != null)
        {
            help.setColorState(colorState);
        }
        
        if(report != null)
        {
            report.changeColorScheme(colorState);
        }
        
        //Change profile
        PROFILE_STATE = TEMP_PROFILE_STATE; 
        OLD_PROFILE_STATE = 100;
        
        home.setProfile(PROFILE_STATE);
        
        changeProfile(PROFILE_STATE);
        
        savePreferences();
        saveMailPreferences();
        saveInternetPreferences();
        eventsModel = (DefaultTableModel)eventsTable.getModel();
        
        home.setMailPreferences(mailPrefs);
        home.setPreferences(prefs);
        home.setInternetPreferences(internetPrefs);
        home.setEventsPreferences(eventsModel);
     
        
    }//GEN-LAST:event_okButtonMouseClicked

    private void savePreferences()
    {
        if(prefs != null)
        {
            //Options panel
            prefs.put("icon background", Integer.toString(iconBackgroundDropBox.getSelectedIndex()));
            prefs.put("icon text", Integer.toString(iconTextDropBox.getSelectedIndex()));
        
            if(smallRadioButton.isSelected())
            {
                prefs.put("size", "small");
            }
            else
            {
                prefs.put("size", "large");
            }
        
            prefs.put("language", Integer.toString(languageDropBox.getSelectedIndex()));
        
            if(debugModeCheckBox.getState())
            {
                prefs.put("debugMode","enabled");
            }
            else if(!debugModeCheckBox.getState())
            {
                prefs.put("debugMode","disabled");
            }
            
            if(celsiusCheckBox.getState())
            {
                prefs.put("temperature","Celsius");
            }
            else
            {
                prefs.put("temperature","Fahrenheit");
            }
        
            if(smbusCheckBox.getState())
            {
                prefs.put("speedFan access","SMBus");
            }
            else if(isaBusCheckBox.getState())
            {
                prefs.put("speedFan access","ISA Bus");
            }
            
            if(enableDellSupportCheckBox.getState())
            {
                prefs.put("dell support","enabled");
            }
            else
            {
                prefs.put("dell support","disabled");
            }
        
            if(startMinimizedCheckBox.getState())
            {
                prefs.put("start minimized", "enabled");
                
            }
            else
            {
                prefs.put("start minimized", "disabled");
            } 
            
            
            if(staticIconCheckBox.getState())
            {
                prefs.put("static icon", "enabled");
                
            }
            else
            {
                prefs.put("static icon", "disabled");
            } 
        
            prefs.put("delta value",deltaValueSpinner.getValue().toString());
        
            if(errorLogCheckBox.getState())
            {
                prefs.put("summary error log scan","enabled");
            }
            else
            {
                prefs.put("summary error log scan","disabled");
            }
        
            if(setFansCheckBox.getState())
            {
                prefs.put("fans 100% on exit","enabled");
            }
            else
            {
                prefs.put("fans 100% on exit","disabled");
            }    
        
        
            //Advanced panel
            prefs.put("set to", Integer.toString(setToComboBox.getSelectedIndex()));
        
            if(rememberCheckBox.getState())
            {
                prefs.put("remember it", "enabled");
            }
            else
            {
                prefs.put("remember it", "disabled");
            }
            
            //Mail panel
        
            prefs.put("mail profile",Integer.toString(jComboBox4.getSelectedIndex()));
        
            prefs.put("mail from",fromEmailTextBox.getText());
        
            prefs.put("mail to",toEmailTextBox.getText());
        
            prefs.put("smtp server",smtpServerTextBox.getText());
        
            prefs.put("port 1",portTextBox.getText());
            
            if(useStartTLSCheckBox.getState())
            {
                prefs.put("use start tls","enabled");
            }
            else
            {
                prefs.put("use start tls","disabled");
            }
        
        
            prefs.put("username 1", userNameTextBox.getText());
        
            prefs.put("password 1", passwordTextBox.getText());
        
            prefs.put("pop3 server", pop3ServerTextBox.getText());
        
            prefs.put("port 2",portTextBox2.getText());
        
            if(doPriorCheckBox.getState())
            {
                prefs.put("prior pop3 check","enabled");
            }
            else
            {
                prefs.put("prior pop3 check","disabled");
            }
        
            prefs.put("username 2",userNameTextBox1.getText());
        
            prefs.put("password 2",passwordTextBox1.getText());
        
            //Internet panel
            if(useProxyCheckBox.getState())
            {
                prefs.put("use proxy","enabled");
            }
            else
            {
                prefs.put("use proxy","disabled");
            }
        
        
            prefs.put("http proxy", httpProxyTextBox.getText());
        
            prefs.put("internet username", userNAmeTextBox3.getText());      
            prefs.put("internet password", passwordTextBox4.getText());
        }
    }
    
    private void saveMailPreferences(){
        mailPrefs.put("from email", fromEmailTextBox.getText());
        mailPrefs.put("to email", toEmailTextBox.getText());
        mailPrefs.put("smtp server", smtpServerTextBox.getText());
        mailPrefs.put("port 1", portTextBox.getText());
                
        if(useStartTLSCheckBox.isEnabled()){
            mailPrefs.put("start tls", "enabled");
        }
        else{
            mailPrefs.put("start tls", "disabled");
        }
        
        mailPrefs.put("username 1", userNameTextBox.getText());
        mailPrefs.put("password 1", passwordTextBox.getText());
        
        mailPrefs.put("pop3 server", pop3ServerTextBox.getText());
        mailPrefs.put("port 2", portTextBox2.getText());

        if(doPriorCheckBox.isEnabled()){
            mailPrefs.put("do prior", "enabled");
        }
        else{
            mailPrefs.put("do prior", "disabled");
        }

        
        mailPrefs.put("username 2", userNameTextBox1.getText());
        mailPrefs.put("password 2", passwordTextBox1.getText());
        
    }
    
    private void saveInternetPreferences(){
        
        if(useProxyCheckBox.isEnabled()){
            internetPrefs.put("use proxy", "enabled");
        }
        else{
            internetPrefs.put("use proxy", "disabled");
        }
        
        internetPrefs.put("http proxy",httpProxyTextBox.getText());
        internetPrefs.put("username", userNAmeTextBox3.getText());
        internetPrefs.put("password",passwordTextBox4.getText());
        
        internetPrefs.put("port", portTextBox3.getText());
       
    }
    
    private void setPreferences()
    {
        if(prefs != null)
        {
            //Options panel
            iconBackgroundDropBox.setSelectedIndex(Integer.valueOf(prefs.get("icon background")));
        
            iconTextDropBox.setSelectedIndex(Integer.valueOf(prefs.get("icon text")));

        
            if(prefs.get("size").equals("small"))
            {
                smallRadioButton.setSelected(true);
                largeRadioButton.setSelected(false);
            }
            else
            {
                smallRadioButton.setSelected(false);
                largeRadioButton.setSelected(true);
            }
        
            languageDropBox.setSelectedIndex(Integer.valueOf(prefs.get("language")));

        
            if(prefs.get("debugMode").equals("enabled"))
            {
                debugModeCheckBox.setState(true);
           
            }
            else
            {
                debugModeCheckBox.setState(false);
            }
        
            if(prefs.get("temperature").equals("Celsius"))
            {
                celsiusCheckBox.setState(true);
                fahrenheitCheckBox.setState(false);
            }
            else
            {
                celsiusCheckBox.setState(false);
                fahrenheitCheckBox.setState(true);
            }
        
            if(prefs.get("speedFan access").equals("SMBus"))
            {
                smbusCheckBox.setState(true);
                isaBusCheckBox.setState(false);
            }
            else
            {
                smbusCheckBox.setState(false);
                isaBusCheckBox.setState(true);
            }
        
            deltaValueSpinner.setValue(Integer.valueOf(prefs.get("delta value")));
        
            if(prefs.get("summary error log scan").equals("enabled"))
            {
                errorLogCheckBox.setState(true);
            }
            else
            {
                errorLogCheckBox.setState(false);
            }
            
            if(prefs.get("dell support").equals("enabled"))
            {
                enableDellSupportCheckBox.setState(true);
            }
            else
            {
                enableDellSupportCheckBox.setState(false);
            }
            
            if(prefs.get("start minimized").equals("enabled"))
            {
                startMinimizedCheckBox.setState(true);
            }
            else
            {
                startMinimizedCheckBox.setState(false);
            } 
            
            
            if(prefs.get("static icon").equals("enabled"))
            {
                staticIconCheckBox.setState(true);
            }
            else
            {
                staticIconCheckBox.setState(false);
            } 
        
            if(prefs.get("fans 100% on exit").equals("enabled"))
            {
                setFansCheckBox.setState(true);
            
            }
            else
            {
                setFansCheckBox.setState(false);
            }    
        
        
            //Advanced panel
            setToComboBox.setSelectedIndex(Integer.valueOf(prefs.get("set to")));

            if(prefs.get("remember it").equals("enabled"))
            {
                rememberCheckBox.setState(true);
            
            }
            else
            {
                rememberCheckBox.setState(false);
            }
            
            //Mail panel
        
            jComboBox4.setSelectedIndex(Integer.valueOf(prefs.get("mail profile")));

            fromEmailTextBox.setText(prefs.get("mail from"));
        
            toEmailTextBox.setText(prefs.get("mail to"));
        
            smtpServerTextBox.setText(prefs.get("smtp server"));
        
            portTextBox.setText(prefs.get("port 1"));
        
        
            if(prefs.get("use start tls").equals("enabled"))
            {
                useStartTLSCheckBox.setState(true);
            
            }
            else
            {
                useStartTLSCheckBox.setState(false);
            }
        
            userNameTextBox.setText(prefs.get("username 1"));
        
            passwordTextBox.setText(prefs.get("password 1"));
        
            pop3ServerTextBox.setText(prefs.get("pop3 server"));
        
            portTextBox2.setText(prefs.get("port 2"));
                
            if(prefs.get("prior pop3 check").equals("enabled"))
            {
                doPriorCheckBox.setState(true);
            
            }
            else if(prefs.get("prior pop3 check").equals("disabled"))
            {
                doPriorCheckBox.setState(false);
            }
        
            userNameTextBox1.setText(prefs.get("username 2"));
        
            passwordTextBox1.setText(prefs.get("password 2"));
        
        
            //Internet panel
            if(prefs.get("use proxy").equals("enabled"))
            {
                useProxyCheckBox.setState(true);
            
            }
            else if(prefs.get("use proxy").equals("disabled"))
            {
                useProxyCheckBox.setState(false);
            }
        
            httpProxyTextBox.setText(prefs.get("http proxy"));
        
            userNAmeTextBox3.setText(prefs.get("internet username"));
        
            passwordTextBox4.setText(prefs.get("internet password"));
        }
    }
   
    
    private void setMailPreferences(){
        fromEmailTextBox.setText(mailPrefs.get("from email"));
        toEmailTextBox.setText(mailPrefs.get("to email"));
        
        smtpServerTextBox.setText(mailPrefs.get("smtp server"));
        portTextBox.setText(mailPrefs.get("port 1"));
        
        if(mailPrefs.get("start tls").equals("enabled"))
        {
            useStartTLSCheckBox.setEnabled(true);
        }
        else{
            useStartTLSCheckBox.setEnabled(false);
        }
        
        userNameTextBox.setText(mailPrefs.get("username 1"));
        passwordTextBox.setText(mailPrefs.get("password 1"));
        
        pop3ServerTextBox.setText(mailPrefs.get("pop3 server"));
        portTextBox2.setText(mailPrefs.get("port 2"));
        
        if(mailPrefs.get("do prior").equals("enabled"))
        {
            doPriorCheckBox.setEnabled(true);
        }
        else{
            doPriorCheckBox.setEnabled(false);
        }
        
        userNameTextBox1.setText(mailPrefs.get("username 2"));
        passwordTextBox1.setText(mailPrefs.get("password 2"));
        
    }
    
    private void setInternetPreferences(){
        
        if(internetPrefs.get("use proxy").equals("enabled")){
            useProxyCheckBox.setEnabled(true);
        }
        else{
            useProxyCheckBox.setEnabled(false);
        }
        
        httpProxyTextBox.setText(internetPrefs.get("http proxy"));
        
        userNAmeTextBox3.setText(internetPrefs.get("username"));
        passwordTextBox4.setText(internetPrefs.get("password"));
        
        portTextBox3.setText(internetPrefs.get("port"));
        
        
    }
    
    public void setSettingsMap(HashMap<String, String> prefs)
    {
        if(prefs != null)
        {
            this.prefs = prefs;
            setPreferences();
        }
        else
        {
            this.prefs = null;
        }
    }
    
    public void setMailPreferencesMap(HashMap<String, String> mailPrefs){
        if(mailPrefs != null){
            this.mailPrefs = mailPrefs;
            setMailPreferences();
        }
        else{
            this.mailPrefs = null;
        }
    }
    
    public void setInternetPreferencesMap(HashMap<String, String> internetPrefs){
        if(internetPrefs != null){
            this.internetPrefs = internetPrefs;
            setInternetPreferences();
        }
        else{
            this.internetPrefs = null;
        }
    }
    
    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        if(oldColorState >= 0 && oldColorState <= 6)
        {
            colorState = oldColorState;
            changeColorScheme(oldColorState);
        }
        
        if(OLD_PROFILE_STATE >= 0 && OLD_PROFILE_STATE <= 2)
        {
            PROFILE_STATE = OLD_PROFILE_STATE;
            changeProfile(PROFILE_STATE);
        }
        
        setPreferences();
        setMailPreferences();
        setInternetPreferences();
        ((DefaultTableModel)eventsTable.getModel()).setRowCount(0);
        setEventsModel(eventsModel);
        
        
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void helpButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseClicked
        if(this.help == null)
        {
            java.awt.EventQueue.invokeLater(new Runnable() {
                Help help;

                public void run() {
                    if(this.help == null)
                    {
                        help = new Help();
                        help.setSettings(Settings.this);
                        help.setHome(home);
                        help.setColorState(colorState);
                        this.help = help;
                        helpOpened = true;
                        help.setVisible(true);
                    }
                }
            
            });
        }
    }//GEN-LAST:event_helpButtonMouseClicked

    private void blueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blueMouseClicked
        oldColorState = colorState;
        changeColorScheme(BLUE);
        tempColorState = BLUE;
    }//GEN-LAST:event_blueMouseClicked

    private void redMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_redMouseClicked
        oldColorState = colorState;
        changeColorScheme(RED);
        tempColorState = RED;
    }//GEN-LAST:event_redMouseClicked

    private void orangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orangeMouseClicked
        oldColorState = colorState;
        changeColorScheme(ORANGE);
        tempColorState = ORANGE;
    }//GEN-LAST:event_orangeMouseClicked

    private void yellowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yellowMouseClicked
        oldColorState = colorState;
        changeColorScheme(YELLOW);
        tempColorState = YELLOW;
    }//GEN-LAST:event_yellowMouseClicked

    private void greenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_greenMouseClicked
        oldColorState = colorState;
        changeColorScheme(GREEN);
        tempColorState = GREEN;
    }//GEN-LAST:event_greenMouseClicked

    private void darkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_darkMouseClicked
        oldColorState = colorState;
        changeColorScheme(DARK);
        tempColorState = DARK;
    }//GEN-LAST:event_darkMouseClicked

    private void purpleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purpleMouseClicked
        oldColorState = colorState;
        changeColorScheme(PURPLE);
        tempColorState = PURPLE;
    }//GEN-LAST:event_purpleMouseClicked

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        JComboBox cb = (JComboBox)evt.getSource();
        Object item = evt.getItem();
        
        if(((String)item).equals("Beginner"))
        {
            OLD_PROFILE_STATE = PROFILE_STATE;
            TEMP_PROFILE_STATE = BEGINNER_USER;
        }
        else if(((String)item).equals("Advanced"))
        {
            OLD_PROFILE_STATE = PROFILE_STATE;
            TEMP_PROFILE_STATE = ADVANCED_USER;
        }
        else if(((String)item).equals("Expert"))
        {
            OLD_PROFILE_STATE = PROFILE_STATE;
            TEMP_PROFILE_STATE = EXPERT_USER;
        }
        
        
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(help != null)
        {
            help.setSettingsClosed();
        }
        
        if(home != null)
        {
            home.setSettingsClosed();
        }
        
        
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(help != null)
        {
            help.setSettingsClosed();
        }
        
        if(home != null)
        {
            home.setSettingsClosed();
        }
        
        if(report != null)
        {
            report.setSettingsClosed();
        }
    }//GEN-LAST:event_formWindowClosed

    private void smallRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smallRadioButtonActionPerformed
        if(largeRadioButton.isSelected())
        {
            smallRadioButton.setSelected(true);
            largeRadioButton.setSelected(false);
        }
    }//GEN-LAST:event_smallRadioButtonActionPerformed

    private void largeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_largeRadioButtonActionPerformed
        if(smallRadioButton.isSelected())
        {
            largeRadioButton.setSelected(true);
            smallRadioButton.setSelected(false);
        }
    }//GEN-LAST:event_largeRadioButtonActionPerformed

    private void fahrenheitCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fahrenheitCheckBoxItemStateChanged
        if(fahrenheitCheckBox.getState())
        {
            celsiusCheckBox.setState(false);
        }
        else
        {
            celsiusCheckBox.setState(true);
        }
    }//GEN-LAST:event_fahrenheitCheckBoxItemStateChanged

    private void celsiusCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_celsiusCheckBoxItemStateChanged
        if(celsiusCheckBox.getState())
        {
            fahrenheitCheckBox.setState(false);
        }
        else
        {
            fahrenheitCheckBox.setState(true);
        }
    }//GEN-LAST:event_celsiusCheckBoxItemStateChanged

    private void isaBusCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_isaBusCheckBoxItemStateChanged
        if(isaBusCheckBox.getState())
        {
            smbusCheckBox.setState(false);
        }
        else
        {
            smbusCheckBox.setState(true);
        }
    }//GEN-LAST:event_isaBusCheckBoxItemStateChanged

    private void smbusCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_smbusCheckBoxItemStateChanged
        if(smbusCheckBox.getState())
        {
            isaBusCheckBox.setState(false);
        }
        else
        {
            isaBusCheckBox.setState(true);
        }
    }//GEN-LAST:event_smbusCheckBoxItemStateChanged

    private void sendTestMailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendTestMailButtonActionPerformed
        testMailBox.setText("Test mail was sent.");
    }//GEN-LAST:event_sendTestMailButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if(STATE == EVENTS){
            if(!measurementTextBox.getText().equals("") && (int)numberSpinner.getValue() > 0 && (int)secondsSpinner.getValue() > 0 && !messageTextBox.getText().equals("")){
                DefaultTableModel model = (DefaultTableModel)eventsTable.getModel();
                String reading = "", kind = "", origin = "", check = "", action = "";
                String temp = (String)jComboBox2.getSelectedItem();
                String[] buffer = temp.split("\\s+");
                boolean kindFound = false;
                boolean fromFound = false;
                
                for(int i=0; i < buffer.length; i++){
                    if(buffer[i].charAt(0) == '('){
                        buffer[i].replaceAll("[a-z][A-Z]", "");
                        kind = buffer[i];
                        kindFound = true;
                    }
                    
                    if(kindFound == false){
                        reading = reading + buffer[i]; 
                    }
                    
                    
                    if(fromFound == true){
                        origin = origin + buffer[i];
                    }
                    
                    if(buffer[i].equals("from")){
                        fromFound = true;
                    }
                   
                }
                
                check = (String)jComboBox3.getSelectedItem() + measurementTextBox.getText();
                action = (String)actionComboBox.getSelectedItem() + "\"" + messageTextBox.getText() + "\"";
                
                model.addRow(new Object[]{(Object)reading, (Object)kind, (Object)origin, (Object)check, (Object)action});

            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void eventsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventsTableMouseClicked
       eventsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int row = eventsTable.rowAtPoint(e.getPoint());
                EVENTS_TABLE_ROW = row; 
                deleteButton.setEnabled(true);
            }
        });
    }//GEN-LAST:event_eventsTableMouseClicked

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if(EVENTS_TABLE_ROW != -1){
            DefaultTableModel model = (DefaultTableModel)eventsTable.getModel();
            model.removeRow(EVENTS_TABLE_ROW);
        }
        
        if(eventsTable.getRowCount() == 0)
        {
            deleteButton.setEnabled(false);
        }
              
    }//GEN-LAST:event_deleteButtonActionPerformed

    public void setEventsModel(DefaultTableModel model){
        eventsTable.setModel(model);
    }
    
    public void setHelpClosed()
    {
        helpOpened = false;
        help = null;
    }
    
    public void changeProfile(int PROFILE_STATE)
    {
        if(PROFILE_STATE == BEGINNER_USER)
        {
            deltaValueSpinner.setEnabled(false);
            setFansCheckBox.setEnabled(false);
            advancedButton.setEnabled(false);
            eventsButton.setEnabled(false);
            advancedButton.setBackground(new Color(105,105,105));
            eventsButton.setBackground(new Color(105,105,105));
            jComboBox5.setSelectedItem(0);
        }
        else if(PROFILE_STATE == ADVANCED_USER)
        {
            deltaValueSpinner.setEnabled(true);
            setFansCheckBox.setEnabled(true);
            advancedButton.setEnabled(false);
            eventsButton.setEnabled(true);
            advancedButton.setBackground(new Color(105,105,105));
            if(STATE == EVENTS){
                eventsButton.setBackground(setColor("MEDIUM"));
            }
            else{
                eventsButton.setBackground(setColor("BUTTON"));
            }
            jComboBox5.setSelectedItem(1);
        }
        else if(PROFILE_STATE == EXPERT_USER)
        {
            deltaValueSpinner.setEnabled(true);
            setFansCheckBox.setEnabled(true);
            advancedButton.setEnabled(true);
            eventsButton.setEnabled(true);
            if(STATE == ADVANCED){
                advancedButton.setBackground(setColor("MEDIUM"));
            }
            else{
                advancedButton.setBackground(setColor("BUTTON"));
            }
            
            if(STATE == EVENTS){
                eventsButton.setBackground(setColor("BUTTON"));
            }
            else
            {
                eventsButton.setBackground(setColor("BUTTON"));
            }
            
            jComboBox5.setSelectedItem(2);
        }
        
        
    }
    
    
    
    //To be called only by other windows
    public void setColorState(int colorState)
    {
        this.colorState = colorState;
        changeColorScheme(colorState);
    }
    
    public void changeColorScheme(int colorState)
    {
        if(colorState == BLUE)
        {
            profilesButton.setBackground(BLUE_ARRAY[3]);
            optionsButton.setBackground(BLUE_ARRAY[3]);
            if(PROFILE_STATE == BEGINNER_USER)
            {
                deltaValueSpinner.setEnabled(false);
                setFansCheckBox.setEnabled(false);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(false);
                eventsButton.setBackground(new Color(105,105,105));
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == ADVANCED_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(BLUE_ARRAY[3]);
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == EXPERT_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(true);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(BLUE_ARRAY[3]);
                advancedButton.setBackground(BLUE_ARRAY[3]);
            }
            mailButton.setBackground(BLUE_ARRAY[3]);
            internetButton.setBackground(BLUE_ARRAY[3]);
            
            profile.setBackground(BLUE_ARRAY[0]);
            options.setBackground(BLUE_ARRAY[0]);
            advanced.setBackground(BLUE_ARRAY[0]);
            events.setBackground(BLUE_ARRAY[0]);
            mail.setBackground(BLUE_ARRAY[0]);
            internet.setBackground(BLUE_ARRAY[0]);
            
            if(STATE== PROFILE){
                profilesButton.setBackground(BLUE_ARRAY[0]);
            }
            else if(STATE == OPTIONS){
                optionsButton.setBackground(BLUE_ARRAY[0]);
            }
            else if(STATE == ADVANCED){
                advancedButton.setBackground(BLUE_ARRAY[0]);
            }
            else if(STATE == EVENTS){
                eventsButton.setBackground(BLUE_ARRAY[0]);
            }
            else if(STATE == MAIL ){
                mailButton.setBackground(BLUE_ARRAY[0]);
            }
            else if(STATE == INTERNET){
                internetButton.setBackground(BLUE_ARRAY[0]);
            }
        }
        else if(colorState == RED)
        {
            profilesButton.setBackground(RED_ARRAY[3]);
            optionsButton.setBackground(RED_ARRAY[3]);
            if(PROFILE_STATE == BEGINNER_USER)
            {
                deltaValueSpinner.setEnabled(false);
                setFansCheckBox.setEnabled(false);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(false);
                eventsButton.setBackground(new Color(105,105,105));
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == ADVANCED_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(RED_ARRAY[3]);
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == EXPERT_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(true);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(RED_ARRAY[3]);
                advancedButton.setBackground(RED_ARRAY[3]);
            }
            mailButton.setBackground(RED_ARRAY[3]);
            internetButton.setBackground(RED_ARRAY[3]);
            
            profile.setBackground(RED_ARRAY[0]);
            options.setBackground(RED_ARRAY[0]);
            advanced.setBackground(RED_ARRAY[0]);
            events.setBackground(RED_ARRAY[0]);
            mail.setBackground(RED_ARRAY[0]);
            internet.setBackground(RED_ARRAY[0]);
            
            if(STATE== PROFILE){
                profilesButton.setBackground(RED_ARRAY[0]);
            }
            else if(STATE == OPTIONS){
                optionsButton.setBackground(RED_ARRAY[0]);
            }
            else if(STATE == ADVANCED){
                advancedButton.setBackground(RED_ARRAY[0]);
            }
            else if(STATE == EVENTS){
                eventsButton.setBackground(RED_ARRAY[0]);
            }
            else if(STATE == MAIL ){
                mailButton.setBackground(RED_ARRAY[0]);
            }
            else if(STATE == INTERNET){
                internetButton.setBackground(RED_ARRAY[0]);
            }
        }
        else if(colorState == GREEN)
        {
            profilesButton.setBackground(GREEN_ARRAY[3]);
            optionsButton.setBackground(GREEN_ARRAY[3]);
            if(PROFILE_STATE == BEGINNER_USER)
            {
                deltaValueSpinner.setEnabled(false);
                setFansCheckBox.setEnabled(false);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(false);
                eventsButton.setBackground(new Color(105,105,105));
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == ADVANCED_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(GREEN_ARRAY[3]);
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == EXPERT_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(true);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(GREEN_ARRAY[3]);
                advancedButton.setBackground(GREEN_ARRAY[3]);
            }
            mailButton.setBackground(GREEN_ARRAY[3]);
            internetButton.setBackground(GREEN_ARRAY[3]);
            
            profile.setBackground(GREEN_ARRAY[0]);
            options.setBackground(GREEN_ARRAY[0]);
            advanced.setBackground(GREEN_ARRAY[0]);
            events.setBackground(GREEN_ARRAY[0]);
            mail.setBackground(GREEN_ARRAY[0]);
            internet.setBackground(GREEN_ARRAY[0]);
            
            if(STATE== PROFILE){
                profilesButton.setBackground(GREEN_ARRAY[0]);
            }
            else if(STATE == OPTIONS){
                optionsButton.setBackground(GREEN_ARRAY[0]);
            }
            else if(STATE == ADVANCED){
                advancedButton.setBackground(GREEN_ARRAY[0]);
            }
            else if(STATE == EVENTS){
                eventsButton.setBackground(GREEN_ARRAY[0]);
            }
            else if(STATE == MAIL ){
                mailButton.setBackground(GREEN_ARRAY[0]);
            }
            else if(STATE == INTERNET){
                internetButton.setBackground(GREEN_ARRAY[0]);
            }
        }
        else if(colorState == YELLOW)
        {
            profilesButton.setBackground(YELLOW_ARRAY[3]);
            optionsButton.setBackground(YELLOW_ARRAY[3]);
            if(PROFILE_STATE == BEGINNER_USER)
            {
                deltaValueSpinner.setEnabled(false);
                setFansCheckBox.setEnabled(false);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(false);
                eventsButton.setBackground(new Color(105,105,105));
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == ADVANCED_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(YELLOW_ARRAY[3]);
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == EXPERT_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(true);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(YELLOW_ARRAY[3]);
                advancedButton.setBackground(YELLOW_ARRAY[3]);
            }
            mailButton.setBackground(YELLOW_ARRAY[3]);
            internetButton.setBackground(YELLOW_ARRAY[3]);
            
            profile.setBackground(YELLOW_ARRAY[0]);
            options.setBackground(YELLOW_ARRAY[0]);
            advanced.setBackground(YELLOW_ARRAY[0]);
            events.setBackground(YELLOW_ARRAY[0]);
            mail.setBackground(YELLOW_ARRAY[0]);
            internet.setBackground(YELLOW_ARRAY[0]);
            
            if(STATE== PROFILE){
                profilesButton.setBackground(YELLOW_ARRAY[0]);
            }
            else if(STATE == OPTIONS){
                optionsButton.setBackground(YELLOW_ARRAY[0]);
            }
            else if(STATE == ADVANCED){
                advancedButton.setBackground(YELLOW_ARRAY[0]);
            }
            else if(STATE == EVENTS){
                eventsButton.setBackground(YELLOW_ARRAY[0]);
            }
            else if(STATE == MAIL ){
                mailButton.setBackground(YELLOW_ARRAY[0]);
            }
            else if(STATE == INTERNET){
                internetButton.setBackground(YELLOW_ARRAY[0]);
            }
        }
        else if(colorState == ORANGE)
        {
            profilesButton.setBackground(ORANGE_ARRAY[3]);
            optionsButton.setBackground(ORANGE_ARRAY[3]);
            if(PROFILE_STATE == BEGINNER_USER)
            {
                deltaValueSpinner.setEnabled(false);
                setFansCheckBox.setEnabled(false);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(false);
                eventsButton.setBackground(new Color(105,105,105));
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == ADVANCED_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(ORANGE_ARRAY[3]);
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == EXPERT_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(true);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(ORANGE_ARRAY[3]);
                advancedButton.setBackground(ORANGE_ARRAY[3]);
            }
            mailButton.setBackground(ORANGE_ARRAY[3]);
            internetButton.setBackground(ORANGE_ARRAY[3]);
            
            profile.setBackground(ORANGE_ARRAY[0]);
            options.setBackground(ORANGE_ARRAY[0]);
            advanced.setBackground(ORANGE_ARRAY[0]);
            events.setBackground(ORANGE_ARRAY[0]);
            mail.setBackground(ORANGE_ARRAY[0]);
            internet.setBackground(ORANGE_ARRAY[0]);
            
            if(STATE== PROFILE){
                profilesButton.setBackground(ORANGE_ARRAY[0]);
            }
            else if(STATE == OPTIONS){
                optionsButton.setBackground(ORANGE_ARRAY[0]);
            }
            else if(STATE == ADVANCED){
                advancedButton.setBackground(ORANGE_ARRAY[0]);
            }
            else if(STATE == EVENTS){
                eventsButton.setBackground(ORANGE_ARRAY[0]);
            }
            else if(STATE == MAIL ){
                mailButton.setBackground(ORANGE_ARRAY[0]);
            }
            else if(STATE == INTERNET){
                internetButton.setBackground(ORANGE_ARRAY[0]);
            }
        }
        else if(colorState == DARK)
        {
            profilesButton.setBackground(DARK_ARRAY[3]);
            optionsButton.setBackground(DARK_ARRAY[3]);
            if(PROFILE_STATE == BEGINNER_USER)
            {
                deltaValueSpinner.setEnabled(false);
                setFansCheckBox.setEnabled(false);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(false);
                eventsButton.setBackground(new Color(105,105,105));
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == ADVANCED_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(DARK_ARRAY[3]);
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == EXPERT_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(true);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(DARK_ARRAY[3]);
                advancedButton.setBackground(DARK_ARRAY[3]);
            }
            mailButton.setBackground(DARK_ARRAY[3]);
            internetButton.setBackground(DARK_ARRAY[3]);
            
            profile.setBackground(DARK_ARRAY[0]);
            options.setBackground(DARK_ARRAY[0]);
            advanced.setBackground(DARK_ARRAY[0]);
            events.setBackground(DARK_ARRAY[0]);
            mail.setBackground(DARK_ARRAY[0]);
            internet.setBackground(DARK_ARRAY[0]);
            
            if(STATE== PROFILE){
                profilesButton.setBackground(DARK_ARRAY[0]);
            }
            else if(STATE == OPTIONS){
                optionsButton.setBackground(DARK_ARRAY[0]);
            }
            else if(STATE == ADVANCED){
                advancedButton.setBackground(DARK_ARRAY[0]);
            }
            else if(STATE == EVENTS){
                eventsButton.setBackground(DARK_ARRAY[0]);
            }
            else if(STATE == MAIL ){
                mailButton.setBackground(DARK_ARRAY[0]);
            }
            else if(STATE == INTERNET){
                internetButton.setBackground(DARK_ARRAY[0]);
            }
        }
        else if(colorState == PURPLE)
        {
            profilesButton.setBackground(PURPLE_ARRAY[3]);
            optionsButton.setBackground(PURPLE_ARRAY[3]);
            if(PROFILE_STATE == BEGINNER_USER)
            {
                deltaValueSpinner.setEnabled(false);
                setFansCheckBox.setEnabled(false);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(false);
                eventsButton.setBackground(new Color(105,105,105));
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == ADVANCED_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(false);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(PURPLE_ARRAY[3]);
                advancedButton.setBackground(new Color(105,105,105));
            }
            else if(PROFILE_STATE == EXPERT_USER)
            {
                deltaValueSpinner.setEnabled(true);
                setFansCheckBox.setEnabled(true);
                advancedButton.setEnabled(true);
                eventsButton.setEnabled(true);
                eventsButton.setBackground(PURPLE_ARRAY[3]);
                advancedButton.setBackground(PURPLE_ARRAY[3]);
            }
            mailButton.setBackground(PURPLE_ARRAY[3]);
            internetButton.setBackground(PURPLE_ARRAY[3]);
            
            profile.setBackground(PURPLE_ARRAY[0]);
            options.setBackground(PURPLE_ARRAY[0]);
            advanced.setBackground(PURPLE_ARRAY[0]);
            events.setBackground(PURPLE_ARRAY[0]);
            mail.setBackground(PURPLE_ARRAY[0]);
            internet.setBackground(PURPLE_ARRAY[0]);
            
            if(STATE== PROFILE){
                profilesButton.setBackground(PURPLE_ARRAY[0]);
            }
            else if(STATE == OPTIONS){
                optionsButton.setBackground(PURPLE_ARRAY[0]);
            }
            else if(STATE == ADVANCED){
                advancedButton.setBackground(PURPLE_ARRAY[0]);
            }
            else if(STATE == EVENTS){
                eventsButton.setBackground(PURPLE_ARRAY[0]);
            }
            else if(STATE == MAIL ){
                mailButton.setBackground(PURPLE_ARRAY[0]);
            }
            else if(STATE == INTERNET){
                internetButton.setBackground(PURPLE_ARRAY[0]);
            }
        }
        
        //changeProfile(PROFILE_STATE);
        
        
    }
    
    
    
    private Color setColor(String prompt)
    {
        Color color = null;
        if(prompt.equals("MEDIUM"))
        {
            color = pickTone(1);
            
        }
        else if(prompt.equals("DARKEST"))
        {
            color = pickTone(0);
        }
        else if(prompt.equals("BRIGHTEST"))
        {
            color = pickTone(2);
        }
        else if(prompt.equals("BUTTON"))
        {
            color = pickTone(3);
        }
        return color;
        
    }
    
    
    private Color pickTone(int index)
    {
        Color color = null;
        if(colorState == BLUE)
            {
                color = BLUE_ARRAY[index];
            }
            else if(colorState == RED)
            {
                color = RED_ARRAY[index];
            }
            else if(colorState == GREEN)
            {
                color = GREEN_ARRAY[index];
            }
            else if(colorState == YELLOW)
            {
                color = YELLOW_ARRAY[index];
            }
            else if(colorState == ORANGE)
            {
                color = ORANGE_ARRAY[index];
            }
            else if(colorState == DARK)
            {
                color = DARK_ARRAY[index];
            }
            else if(colorState == PURPLE)
            {
                color = PURPLE_ARRAY[index];
            }
        return color;
    }
    

    public void setReport(Report report)
    {
        if(report != null)
        {
            this.report = report;
            reportOpened = true;
        }
        else
        {
            this.report = null;
            reportOpened = false; 
        }
    }
    
    public void setReportClosed(){
        this.report = null;
        reportOpened = false; 

    }
           
    public void setProfile(int PROFILE_STATE){
        this.PROFILE_STATE = PROFILE_STATE;
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
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Settings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> actionComboBox;
    private javax.swing.JButton addButton;
    private javax.swing.JPanel advanced;
    private javax.swing.JPanel advancedBorder;
    private javax.swing.JPanel advancedButton;
    private javax.swing.JLabel advancedIcon;
    private javax.swing.JLabel allowText;
    private keeptoo.KGradientPanel blue;
    private javax.swing.JButton cancelButton;
    private java.awt.Checkbox celsiusCheckBox;
    private java.awt.Label chipText;
    private javax.swing.JPanel colorChoiceBorder;
    private javax.swing.JPanel confirmBorder;
    private javax.swing.JPanel confirmBox;
    private javax.swing.JLabel customizationButton;
    private keeptoo.KGradientPanel dark;
    private javax.swing.JPanel debugModeBorder;
    private java.awt.Checkbox debugModeCheckBox;
    private java.awt.Label debugModeText;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel dellSupportBorder;
    private java.awt.Label dellSupportText;
    private java.awt.Label dellSupportText1;
    private javax.swing.JSpinner deltaValueSpinner;
    private java.awt.Label deltaValueText;
    private java.awt.Label deltaValueText2;
    private java.awt.Checkbox doPriorCheckBox;
    private java.awt.Checkbox enableDellSupportCheckBox;
    private java.awt.Checkbox errorLogCheckBox;
    private javax.swing.JPanel eventBox;
    private javax.swing.JLabel eventIcon;
    private javax.swing.JPanel events;
    private javax.swing.JPanel eventsBorder;
    private javax.swing.JPanel eventsButton;
    private javax.swing.JTable eventsTable;
    private java.awt.Checkbox fahrenheitCheckBox;
    private javax.swing.JPanel fansBorder;
    private javax.swing.JLabel forText;
    private javax.swing.JTextField fromEmailTextBox;
    private keeptoo.KGradientPanel green;
    private javax.swing.JLabel helpButton;
    private java.awt.Label httpProxyText;
    private javax.swing.JTextField httpProxyTextBox;
    private javax.swing.JComboBox<String> iconBackgroundDropBox;
    private java.awt.Label iconBackgroundText;
    private javax.swing.JComboBox<String> iconTextDropBox;
    private java.awt.Label iconTextText;
    private java.awt.Label ifText;
    private javax.swing.JPanel internet;
    private javax.swing.JPanel internetBorder;
    private javax.swing.JPanel internetButton;
    private javax.swing.JLabel internetIcon;
    private java.awt.Checkbox isaBusCheckBox;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel languageBorder;
    private javax.swing.JComboBox<String> languageDropBox;
    private java.awt.Label languageText;
    private java.awt.Label languageText2;
    private javax.swing.JRadioButton largeRadioButton;
    private javax.swing.JPanel line1;
    private javax.swing.JPanel line2;
    private javax.swing.JPanel line3;
    private javax.swing.JPanel line4;
    private javax.swing.JPanel mail;
    private javax.swing.JPanel mailButton;
    private java.awt.Label mailFromText;
    private javax.swing.JLabel mailIcon;
    private javax.swing.JLabel mailToText;
    private javax.swing.JTextField measurementTextBox;
    private javax.swing.JTextField messageTextBox;
    private javax.swing.JSpinner numberSpinner;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel options;
    private javax.swing.JPanel optionsBorder;
    private javax.swing.JPanel optionsButton;
    private javax.swing.JLabel optionsIcon;
    private javax.swing.JLabel optionsText;
    private keeptoo.KGradientPanel orange;
    private java.awt.Label passwordText;
    private java.awt.Label passwordText1;
    private javax.swing.JLabel passwordText4;
    private javax.swing.JTextField passwordTextBox;
    private javax.swing.JTextField passwordTextBox1;
    private javax.swing.JTextField passwordTextBox4;
    private java.awt.Label pop3ServerText;
    private javax.swing.JTextField pop3ServerTextBox;
    private java.awt.Label portText;
    private java.awt.Label portText1;
    private javax.swing.JLabel portText3;
    private javax.swing.JTextField portTextBox;
    private javax.swing.JTextField portTextBox2;
    private javax.swing.JTextField portTextBox3;
    private javax.swing.JLabel printButton;
    private javax.swing.JPanel profile;
    private javax.swing.JPanel profileBorder;
    private java.awt.Label profileText;
    private javax.swing.JLabel profileText2;
    private javax.swing.JPanel profilesButton;
    private javax.swing.JLabel profilesIcon;
    private javax.swing.JLabel profilesText1;
    private javax.swing.JLabel profilesText2;
    private javax.swing.JLabel profilesText3;
    private javax.swing.JLabel profilesText4;
    private javax.swing.JLabel profilesText5;
    private javax.swing.JPanel proxyBorder;
    private keeptoo.KGradientPanel purple;
    private keeptoo.KGradientPanel red;
    private java.awt.Checkbox rememberCheckBox;
    private javax.swing.JSpinner secondsSpinner;
    private javax.swing.JLabel secondsText;
    private javax.swing.JLabel selectColorText;
    private javax.swing.JButton sendTestMailButton;
    private javax.swing.JPanel sensorsBorder;
    private java.awt.Checkbox setFansCheckBox;
    private javax.swing.JComboBox<String> setToComboBox;
    private javax.swing.JLabel setToText;
    private javax.swing.JPanel settingsCardLayout;
    private javax.swing.JRadioButton smallRadioButton;
    private java.awt.Checkbox smbusCheckBox;
    private javax.swing.JTextField smtpServerTextBox;
    private javax.swing.JLabel smtpserverText;
    private java.awt.Label sslInterfaceText;
    private javax.swing.JTextField sslInterfaceTextBox;
    private java.awt.Checkbox startMinimizedCheckBox;
    private java.awt.Checkbox staticIconCheckBox;
    private javax.swing.JPanel temperatureBorder;
    private java.awt.Label temperatureText;
    private javax.swing.JTextArea testMailBox;
    private javax.swing.JLabel thenText;
    private javax.swing.JLabel timesText;
    private javax.swing.JTextField toEmailTextBox;
    private javax.swing.JPanel toolBar;
    private javax.swing.JPanel trayBarBorder;
    private java.awt.Label trayBarText;
    private java.awt.Label useFontText;
    private java.awt.Checkbox useProxyCheckBox;
    private java.awt.Checkbox useStartTLSCheckBox;
    private javax.swing.JTextField userNAmeTextBox3;
    private java.awt.Label userNameText;
    private java.awt.Label userNameText1;
    private javax.swing.JLabel userNameText3;
    private javax.swing.JTextField userNameTextBox;
    private javax.swing.JTextField userNameTextBox1;
    private keeptoo.KGradientPanel yellow;
    // End of variables declaration//GEN-END:variables
}
