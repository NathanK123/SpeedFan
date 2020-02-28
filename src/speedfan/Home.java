/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedfan;

import java.awt.*;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;
import java.util.HashMap;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import static org.jfree.chart.ChartFactory.createXYLineChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;


public class Home extends javax.swing.JFrame {

    /* Constants to define which page is made visible */
    private static int BEGINNER_USER = 0;
    private static int ADVANCED_USER = 1;
    private static int EXPERT_USER = 2; 
    private int PROFILE_STATE;
    
    private HashMap<String, String> prefs;
    private HashMap<String, String> advancedPrefs;
    private HashMap<String, String> eventsPrefs;
    private HashMap<String, String> mailPrefs;
    private HashMap<String, String> internetPrefs;
    private DefaultTableModel eventsModel;
    
    private static int HOME = 0;
    private static int CONFIGURE = 1;
    private static int DEBUGGING = 2;
    private static int DIAGNOSTICS = 3;
    private static int CHARTS = 4;
    private static int SETTINGS = 5;
    private static int HELP = 6;
    private static int CLOCK = 7;
    private static int state;
    private static int oldState;
    
    private static int BLUE = 0;
    private static int RED = 1;
    private static int GREEN = 2;
    private static int YELLOW = 3; 
    private static int ORANGE = 4;
    private static int DARK = 5;
    private static int PURPLE = 6;
    private int colorState;
    
    //Darkest to lightest
    private static Color[] BLUE_ARRAY = {new Color(0, 0, 102), new Color(0, 153, 255), new Color(153,204,255), new Color(153,204,255)}; 
    private static Color[] RED_ARRAY = {new Color(204,0,0), new Color(255, 0, 0), new Color(255, 102, 102)};
    private static Color[] GREEN_ARRAY = {new Color(0,102,0), new Color(0,204,0), new Color(102, 255, 102)};
    private static Color[] YELLOW_ARRAY = {new Color(255,204,0), new Color(255, 255, 0), new Color(255, 255, 204)};
    private static Color[] ORANGE_ARRAY = {new Color(255,102,0), new Color(255, 204, 51), new Color(255, 153, 0)};
    private static Color[] DARK_ARRAY = {new Color(51,51,51), new Color(102,102, 102), new Color(204, 204, 204)};
    private static Color[] PURPLE_ARRAY = {new Color(102,0,153), new Color(218, 112, 214), new Color(216, 191, 216)};
    
    private Settings settings = null;
    private Help help = null;
    private Report report = null;
    
    private boolean settingsOpened = false;
    private boolean helpOpened = false;
    private boolean reportOpened = false;
    
    
    private String chartTitle = "HD0 (V)";
    private String chartAttribute = "VOLTAGE";
    
    private int CONFIGURE_TEMPERATURE_ROW;
    private int CONFIGURE_FAN_SPEED_ROW;
    private int CONFIGURE_VOLTAGE_ROW;
    
    private int FAN_CONTROLLER_ROW = -1;
    private int TEMP_LIST_ROW = -1;
    
    private DefaultListModel fanControllerModel = null;
    private DefaultListModel tempModel = null;
    
    private boolean firstTimeTemperatureTab;
    private boolean firstTimeFanTab;
    private boolean firstTimeSpeedTab;
    private boolean firstTimeVoltageTab;
    private boolean firstTimeAdvancedFanTab;
    
    private boolean firstTimeConfigure;
    
    
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        
        
       state = HOME;
       
       PROFILE_STATE = BEGINNER_USER;
       
       setProfile(PROFILE_STATE);
       colorState = BLUE;
       
       UIDefaults defaults = UIManager.getLookAndFeelDefaults();
       if(defaults.get("Table.alternateRowColor") == null)
       {
           defaults.put("Table.alternateRowColor", new Color(0, 153, 255));
       }
       
       
       //Remove panels
       pages.removeAll();
       
       //Add basic readings panel
       pages.add(basicReadings);
       pages.repaint();
       pages.revalidate();
       
       this.setSize(920, 580);
       
       
       //Button changes color if mouse moves over it
       homeButton.addMouseListener(new MouseAdapter()
       {
           public void mouseEntered(MouseEvent e)
           {
                   homeButton.setBorder(new LineBorder(Color.WHITE));
           }
       });
       
       //Button removes border when mouse hovers away from button
       homeButton.addMouseListener(new MouseAdapter()
       {
           public void mouseExited(MouseEvent e)
           {
                   homeButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
           }
       });
       
       configureButton.addMouseListener(new MouseAdapter()
       {
           public void mouseEntered(MouseEvent e)
           {
                   configureButton.setBorder(new LineBorder(Color.WHITE));
           }
       });
       
       configureButton.addMouseListener(new MouseAdapter()
       {
           public void mouseExited(MouseEvent e)
           {
                   configureButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
           }
       });
       
       debuggingButton.addMouseListener(new MouseAdapter()
       {
           public void mouseEntered(MouseEvent e)
           {
                   debuggingButton.setBorder(new LineBorder(Color.WHITE));
           }
       });
       
       debuggingButton.addMouseListener(new MouseAdapter()
       {
           public void mouseExited(MouseEvent e)
           {
                   debuggingButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
           }
       });
       
       diagnosticsButton.addMouseListener(new MouseAdapter()
       {
           public void mouseEntered(MouseEvent e)
           {
                   diagnosticsButton.setBorder(new LineBorder(Color.WHITE));
           }
       });
       
       diagnosticsButton.addMouseListener(new MouseAdapter()
       {
           public void mouseExited(MouseEvent e)
           {
                   diagnosticsButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
           }
       });
       
       chartsButton.addMouseListener(new MouseAdapter()
       {
           public void mouseEntered(MouseEvent e)
           {
                   chartsButton.setBorder(new LineBorder(Color.WHITE));
           }
       });
       
       chartsButton.addMouseListener(new MouseAdapter()
       {
           public void mouseExited(MouseEvent e)
           {
                   chartsButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
           }
       });
       
      clockButton.addMouseListener(new MouseAdapter()
       {
           public void mouseExited(MouseEvent e)
           {
                   clockButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
           }
       });
      
      clockButton.addMouseListener(new MouseAdapter()
       {
           public void mouseEntered(MouseEvent e)
           {
                   clockButton.setBorder(new LineBorder(Color.WHITE));
           }
       });
       
      
       this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Images/New Logo.png")));
       
       ImageIcon tickIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Images/Tick.png")));
       ImageIcon crossIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Images/Cross.png")));
       
       
       Object[][] data = {
                {"Reallocated Sector Count", "100", "100", "10", "00000000000", ""},
                {"Powers on Hours Count", "99", "76", "0", "00000000000", tickIcon},
                {"Power Cycle Count", "98", "98", "0", "00000000000", tickIcon},
                {"(Unknown Attribute)", "100", "89", "10", "00000000000", tickIcon},
                {"(Unknown Attribute)", "100", "87", "10", "00000000000", crossIcon},
                {"(Unknown Attribute)", "100", "91", "5", "00000000000", tickIcon},
                {"(Unknown Attribute)", "100", "96", "10", "00000000000", tickIcon},
                {"(Unknown Attribute)", "100", "87", "10", "00000000000", crossIcon},
                {"(Unknown Attribute)", "100", "81", "10", "00000000000", tickIcon},
                {"(Unknown Attribute)", "100", "94", "10", "00000000000", tickIcon},
                {"(Unknown Attribute)", "98", "90", "10", "00000000000", tickIcon},
                {"Reported Uncorrectable Error", "100", "94", "0", "00000000000", tickIcon},
                {"Temperature", "65", "52", "0", "00000000000", tickIcon},
                {"Hardware ECC Recovered", "99", "79", "0", "00000000000", tickIcon},
                {"UntraATA CRC Error Rate", "100", "87", "0", "00000000000", tickIcon},
                {null, null, null, null, null, null},
                {"", null, null, null, null, null}
            };
       
       String[] columnNames = {"Attribute", "Value", "Worst", "Wam", "Raw", "Status"};
       
       
       DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

       
       diagnosticsTable = new JTable(tableModel);
       diagnosticsTable.setPreferredScrollableViewportSize(diagnosticsTable.getPreferredSize());
       JScrollPane scrollPane = new JScrollPane(diagnosticsTable);
       add(scrollPane);
       
       
       
       dimTextArea.setLineWrap(true);
       dimTextArea.setWrapStyleWord(true);
       
       smBusScanTextArea.setLineWrap(true);
       smBusScanTextArea.setWrapStyleWord(true);
               
       JFreeChart chart = null;
       XYDataset ds;
       
                    
       ds = createDataset((String)voltageListCharts.getModel().getValueAt(0, 0));
        
       if(tabsPaneCharts.getSelectedIndex() == 0) //Voltage
       {
            chart = ChartFactory.createXYLineChart((String)voltageListCharts.getModel().getValueAt(0, 0)  + " (V)", "time", "voltage", ds, PlotOrientation.VERTICAL, true, true, false);
       
       }
       else if(tabsPaneCharts.getSelectedIndex() == 1)
       {
           chart = ChartFactory.createXYLineChart((String)voltageListCharts.getModel().getValueAt(0, 0)  + " (C)", "time", "temperature", ds, PlotOrientation.VERTICAL, true, true, false);
       }
       else
       {
           chart = ChartFactory.createXYLineChart((String)voltageListCharts.getModel().getValueAt(0, 0)  + " (RPM)", "time", "fan speed", ds, PlotOrientation.VERTICAL, true, true, false);
       }
        
       chartsBorder.setLayout(new java.awt.BorderLayout());
        
       ChartPanel cp = new ChartPanel(chart);
       cp.setPreferredSize(new Dimension(524, 379));

                    
       XYPlot plot = chart.getXYPlot();
       XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
       renderer.setSeriesPaint(0, Color.GREEN);
       renderer.setSeriesStroke(0, new BasicStroke(2.0f));
                    
       plot.setRenderer(renderer);
       plot.setBackgroundPaint(Color.BLACK);
                   
       plot.setRangeGridlinesVisible(true);
       plot.setRangeGridlinePaint(Color.GREEN);
                    
       plot.setDomainGridlinesVisible(true);
       plot.setDomainGridlinePaint(Color.GREEN);

       //cp.setSize(chartsBorder.getSize());

       chartsBorder.removeAll();
       chartsBorder.add(cp, BorderLayout.CENTER);
       chartsBorder.repaint();
       chartsBorder.revalidate();
       
       fitnessBar.setValue(80);
       performanceBar.setValue(80);
       
       desiredSpinner.setEnabled(false);
       warningSpinner.setEnabled(false);
       desiredSpinner5.setEnabled(false);
       warningSpinner5.setEnabled(false);
       desiredSpinner7.setEnabled(false);
       warningSpinner7.setEnabled(false);
       
       fanControllerModel = new DefaultListModel();
       fanControllerList.setModel(fanControllerModel);

       tempModel = new DefaultListModel();
       jList1.setModel(tempModel);
       
       fanAddButton.setEnabled(false);
       fanDeleteButton.setEnabled(false);
       
       firstTimeTemperatureTab = true;
       firstTimeFanTab = true;
       firstTimeSpeedTab = true;
       firstTimeVoltageTab = true;
       firstTimeAdvancedFanTab = true;
    
       firstTimeConfigure = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jDialog1 = new javax.swing.JDialog();
        background = new javax.swing.JPanel();
        toolBar = new javax.swing.JPanel();
        settingsButton = new javax.swing.JLabel();
        helpButton = new javax.swing.JLabel();
        sideMenu = new javax.swing.JPanel();
        homeButton = new keeptoo.KGradientPanel();
        homeText = new javax.swing.JLabel();
        homePic = new javax.swing.JLabel();
        configureButton = new keeptoo.KGradientPanel();
        configurePic = new javax.swing.JLabel();
        configureText = new javax.swing.JLabel();
        debuggingButton = new keeptoo.KGradientPanel();
        debuggingIcon = new javax.swing.JLabel();
        debuggingText = new javax.swing.JLabel();
        diagnosticsButton = new keeptoo.KGradientPanel();
        diagnosticsIcon = new javax.swing.JLabel();
        diagnosticsText = new javax.swing.JLabel();
        chartsButton = new keeptoo.KGradientPanel();
        chartsPic = new javax.swing.JLabel();
        chartsText = new javax.swing.JLabel();
        clockButton = new keeptoo.KGradientPanel();
        clockPic = new javax.swing.JLabel();
        clockText = new javax.swing.JLabel();
        pages = new javax.swing.JPanel();
        basicReadings = new javax.swing.JPanel();
        readingBanner3 = new keeptoo.KGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        readingTable6 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        readingBanner2 = new keeptoo.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        readingTable3 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        readingBanner4 = new keeptoo.KGradientPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        readingTable7 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        readingBanner5 = new keeptoo.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        readingTable8 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        textArea1 = new java.awt.TextArea();
        label1 = new java.awt.Label();
        configure = new javax.swing.JPanel();
        temperatureTab = new javax.swing.JTabbedPane();
        tabsMenu = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tempTab = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        warningText = new javax.swing.JLabel();
        warningSpinner = new javax.swing.JSpinner();
        warningMeasure = new javax.swing.JLabel();
        desiredText = new javax.swing.JLabel();
        desiredSpinner = new javax.swing.JSpinner();
        desiredMeasure = new javax.swing.JLabel();
        fanTab = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        speedTab = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        warningText7 = new javax.swing.JLabel();
        warningSpinner7 = new javax.swing.JSpinner();
        warningMeasure7 = new javax.swing.JLabel();
        desiredText7 = new javax.swing.JLabel();
        desiredSpinner7 = new javax.swing.JSpinner();
        desiredMeasure7 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        voltageTab = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        warningText5 = new javax.swing.JLabel();
        warningSpinner5 = new javax.swing.JSpinner();
        warningMeasure5 = new javax.swing.JLabel();
        desiredText5 = new javax.swing.JLabel();
        desiredSpinner5 = new javax.swing.JSpinner();
        desiredMeasure5 = new javax.swing.JLabel();
        advancedFanControl = new javax.swing.JPanel();
        fanControlPanel = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        fanAddButton = new javax.swing.JButton();
        fanDeleteButton = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        fanChartPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        fanControllerList = new javax.swing.JList<>();
        addFanButton = new javax.swing.JButton();
        removeFanButton = new javax.swing.JButton();
        debugging = new javax.swing.JPanel();
        upperWindowDebugging = new javax.swing.JPanel();
        chipsetText = new javax.swing.JLabel();
        comboBoxDebugging = new javax.swing.JComboBox<>();
        smbusaddressText = new javax.swing.JLabel();
        smbusaddressTextField = new javax.swing.JTextField();
        smbusaddressText1 = new javax.swing.JLabel();
        revisionTextBox = new javax.swing.JTextField();
        ioCheckBox = new javax.swing.JCheckBox();
        dimInfoBorder = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        dimTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        readInfoButton = new javax.swing.JButton();
        scanDMBusButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        noteBox = new javax.swing.JTextArea();
        sendReportButton = new javax.swing.JButton();
        dmBusSearchBorder = new javax.swing.JPanel();
        dmbusDeviceTextBox = new javax.swing.JScrollPane();
        smBusScanTextArea = new javax.swing.JTextArea();
        diagnostics = new javax.swing.JPanel();
        upperWindowDebugging1 = new javax.swing.JPanel();
        comboBoxDiagnostics = new javax.swing.JComboBox<>();
        dimInfoBorder1 = new javax.swing.JPanel();
        modelText = new javax.swing.JLabel();
        modelTextField = new javax.swing.JTextField();
        firmWareText = new javax.swing.JLabel();
        firmwareTextField = new javax.swing.JTextField();
        hardDiskText = new javax.swing.JLabel();
        inDepthScanButton = new javax.swing.JButton();
        extendedTestButton = new javax.swing.JButton();
        shortTestButton = new javax.swing.JButton();
        statusText = new javax.swing.JLabel();
        statusTextField = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        diagnosticsTable = new javax.swing.JTable();
        fitnessText = new javax.swing.JLabel();
        performanceText = new javax.swing.JLabel();
        fitnessBar = new javax.swing.JProgressBar();
        performanceBar = new javax.swing.JProgressBar();
        fitnessPercentage = new javax.swing.JLabel();
        fitnessPercentage1 = new javax.swing.JLabel();
        charts = new javax.swing.JPanel();
        upperWindowCharts = new javax.swing.JPanel();
        tabsPaneCharts = new javax.swing.JTabbedPane();
        voltageChartsPanel = new javax.swing.JPanel();
        voltageChartsTable = new javax.swing.JScrollPane();
        voltageListCharts = new javax.swing.JTable();
        temperatureChartsPanel = new javax.swing.JPanel();
        voltageChartsTable1 = new javax.swing.JScrollPane();
        temperatureListCharts = new javax.swing.JTable();
        fanSpeedsChartsPanel = new javax.swing.JPanel();
        voltageChartsTable2 = new javax.swing.JScrollPane();
        fanSpeedsListCharts = new javax.swing.JTable();
        chartsBorder = new javax.swing.JPanel();
        startTimeText = new javax.swing.JLabel();
        endTimeText = new javax.swing.JLabel();
        startTimeBox = new javax.swing.JComboBox<>();
        endTimeBox = new javax.swing.JComboBox<>();
        clockTab = new javax.swing.JPanel();
        javax.swing.JPanel clockBorder = new javax.swing.JPanel();
        motherBoardText = new javax.swing.JLabel();
        motherboard1ComboBox = new javax.swing.JComboBox<>();
        motherboard2ComboBox = new javax.swing.JComboBox<>();
        clockText1 = new javax.swing.JLabel();
        clockComboBox = new javax.swing.JComboBox<>();
        clockCheckBox = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        setToText1 = new javax.swing.JLabel();
        setToText2 = new javax.swing.JLabel();
        afterRunning1 = new javax.swing.JLabel();
        afterRunning2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        runningBelowSpinner = new javax.swing.JSpinner();
        runningBelowSpinner1 = new javax.swing.JSpinner();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        statusTextField2 = new javax.swing.JTextField();
        afterRunning3 = new javax.swing.JLabel();
        afterRunning4 = new javax.swing.JLabel();
        cpuUsageSpinner = new javax.swing.JSpinner();
        cpuUsageSpinner1 = new javax.swing.JSpinner();
        lowPowerPReferredText = new javax.swing.JLabel();
        automaticClockControlText = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        computedText = new javax.swing.JLabel();
        computedClockTextField = new javax.swing.JTextField();
        readClockButton = new javax.swing.JButton();
        setClockButton = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable6);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(255, 255, 255));

        toolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        settingsButton.setBackground(new java.awt.Color(204, 204, 204));
        settingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Settings icon.png"))); // NOI18N
        settingsButton.setToolTipText("Settings");
        settingsButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                settingsButtonMouseMoved(evt);
            }
        });
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

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Help icon.png"))); // NOI18N
        helpButton.setToolTipText("Help");
        helpButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                helpButtonMouseMoved(evt);
            }
        });
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

        javax.swing.GroupLayout toolBarLayout = new javax.swing.GroupLayout(toolBar);
        toolBar.setLayout(toolBarLayout);
        toolBarLayout.setHorizontalGroup(
            toolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toolBarLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(settingsButton)
                .addGap(31, 31, 31)
                .addComponent(helpButton)
                .addContainerGap(794, Short.MAX_VALUE))
        );
        toolBarLayout.setVerticalGroup(
            toolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(settingsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        sideMenu.setBackground(new java.awt.Color(153, 204, 255));

        homeButton.setToolTipText("Enter Home page");
        homeButton.setkEndColor(new java.awt.Color(0, 0, 102));
        homeButton.setkGradientFocus(300);
        homeButton.setkStartColor(new java.awt.Color(0, 102, 204));
        homeButton.setPreferredSize(new java.awt.Dimension(117, 61));
        homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeButtonMouseExited(evt);
            }
        });

        homeText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        homeText.setText("Home");

        homePic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Home icon.png"))); // NOI18N

        javax.swing.GroupLayout homeButtonLayout = new javax.swing.GroupLayout(homeButton);
        homeButton.setLayout(homeButtonLayout);
        homeButtonLayout.setHorizontalGroup(
            homeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(homePic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(homeText)
                .addGap(35, 35, 35))
        );
        homeButtonLayout.setVerticalGroup(
            homeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeButtonLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(homeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homeText)
                    .addComponent(homePic))
                .addGap(17, 17, 17))
        );

        configureButton.setToolTipText("Enter Configure Page");
        configureButton.setkEndColor(new java.awt.Color(0, 0, 102));
        configureButton.setkGradientFocus(300);
        configureButton.setkStartColor(new java.awt.Color(153, 204, 255));
        configureButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                configureButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                configureButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                configureButtonMouseExited(evt);
            }
        });

        configurePic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Configure icon.png"))); // NOI18N

        configureText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        configureText.setText("Configure");

        javax.swing.GroupLayout configureButtonLayout = new javax.swing.GroupLayout(configureButton);
        configureButton.setLayout(configureButtonLayout);
        configureButtonLayout.setHorizontalGroup(
            configureButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configureButtonLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(configurePic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configureText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        configureButtonLayout.setVerticalGroup(
            configureButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configureButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(configureButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(configureText)
                    .addComponent(configurePic))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        debuggingButton.setToolTipText("Enter Debugging Page");
        debuggingButton.setkEndColor(new java.awt.Color(0, 0, 102));
        debuggingButton.setkGradientFocus(300);
        debuggingButton.setkStartColor(new java.awt.Color(153, 204, 255));
        debuggingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                debuggingButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                debuggingButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                debuggingButtonMouseExited(evt);
            }
        });

        debuggingIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Debugging icon.png"))); // NOI18N

        debuggingText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        debuggingText.setText("Debugging");

        javax.swing.GroupLayout debuggingButtonLayout = new javax.swing.GroupLayout(debuggingButton);
        debuggingButton.setLayout(debuggingButtonLayout);
        debuggingButtonLayout.setHorizontalGroup(
            debuggingButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(debuggingButtonLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(debuggingIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debuggingText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        debuggingButtonLayout.setVerticalGroup(
            debuggingButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(debuggingButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(debuggingButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(debuggingText)
                    .addComponent(debuggingIcon))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        diagnosticsButton.setToolTipText("Enter Diagnostics Page");
        diagnosticsButton.setkEndColor(new java.awt.Color(0, 0, 102));
        diagnosticsButton.setkGradientFocus(300);
        diagnosticsButton.setkStartColor(new java.awt.Color(153, 204, 255));
        diagnosticsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diagnosticsButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                diagnosticsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                diagnosticsButtonMouseExited(evt);
            }
        });

        diagnosticsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Disgnostics icon.png"))); // NOI18N

        diagnosticsText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        diagnosticsText.setText("Diagnostics");

        javax.swing.GroupLayout diagnosticsButtonLayout = new javax.swing.GroupLayout(diagnosticsButton);
        diagnosticsButton.setLayout(diagnosticsButtonLayout);
        diagnosticsButtonLayout.setHorizontalGroup(
            diagnosticsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagnosticsButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(diagnosticsIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diagnosticsText)
                .addContainerGap())
        );
        diagnosticsButtonLayout.setVerticalGroup(
            diagnosticsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagnosticsButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(diagnosticsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(diagnosticsText)
                    .addComponent(diagnosticsIcon))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        chartsButton.setToolTipText("Enter Charts Page");
        chartsButton.setkEndColor(new java.awt.Color(0, 0, 102));
        chartsButton.setkGradientFocus(300);
        chartsButton.setkStartColor(new java.awt.Color(153, 204, 255));
        chartsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chartsButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chartsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chartsButtonMouseExited(evt);
            }
        });

        chartsPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Charts icon.png"))); // NOI18N

        chartsText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        chartsText.setText("Charts");
        chartsText.setInheritsPopupMenu(false);

        javax.swing.GroupLayout chartsButtonLayout = new javax.swing.GroupLayout(chartsButton);
        chartsButton.setLayout(chartsButtonLayout);
        chartsButtonLayout.setHorizontalGroup(
            chartsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chartsButtonLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(chartsPic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chartsText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        chartsButtonLayout.setVerticalGroup(
            chartsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chartsButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(chartsButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chartsText)
                    .addComponent(chartsPic))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        clockButton.setToolTipText("Enter Charts Page");
        clockButton.setkEndColor(new java.awt.Color(105, 105, 105));
        clockButton.setkGradientFocus(300);
        clockButton.setkStartColor(new java.awt.Color(105, 105, 105));
        clockButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clockButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clockButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clockButtonMouseExited(evt);
            }
        });

        clockPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clock icon.png"))); // NOI18N

        clockText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        clockText.setText("Clock");

        javax.swing.GroupLayout clockButtonLayout = new javax.swing.GroupLayout(clockButton);
        clockButton.setLayout(clockButtonLayout);
        clockButtonLayout.setHorizontalGroup(
            clockButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clockButtonLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(clockPic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clockText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        clockButtonLayout.setVerticalGroup(
            clockButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clockButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(clockButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clockText)
                    .addComponent(clockPic))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sideMenuLayout = new javax.swing.GroupLayout(sideMenu);
        sideMenu.setLayout(sideMenuLayout);
        sideMenuLayout.setHorizontalGroup(
            sideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chartsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(diagnosticsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(debuggingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(configureButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addComponent(clockButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sideMenuLayout.setVerticalGroup(
            sideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(configureButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(debuggingButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(diagnosticsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(chartsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(clockButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pages.setBackground(new java.awt.Color(204, 204, 204));
        pages.setLayout(new java.awt.CardLayout());

        readingBanner3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        readingBanner3.setkEndColor(new java.awt.Color(153, 204, 255));
        readingBanner3.setkGradientFocus(200);
        readingBanner3.setkStartColor(new java.awt.Color(0, 153, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Temperature");

        jScrollPane14.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane14.setBorder(null);
        jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane14.setToolTipText("");

        readingTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Core1",  new Float(61.0)},
                {"Core2",  new Float(59.0)},
                {"Core3",  new Float(56.0)},
                {"Core4",  new Float(60.0)}
            },
            new String [] {
                "Label", "Temperature (C)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        readingTable6.setSelectionForeground(new java.awt.Color(153, 255, 255));
        jScrollPane14.setViewportView(readingTable6);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Temperature.png"))); // NOI18N

        javax.swing.GroupLayout readingBanner3Layout = new javax.swing.GroupLayout(readingBanner3);
        readingBanner3.setLayout(readingBanner3Layout);
        readingBanner3Layout.setHorizontalGroup(
            readingBanner3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, readingBanner3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(96, 96, 96))
        );
        readingBanner3Layout.setVerticalGroup(
            readingBanner3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readingBanner3Layout.createSequentialGroup()
                .addGroup(readingBanner3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        readingBanner2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        readingBanner2.setkEndColor(new java.awt.Color(153, 204, 255));
        readingBanner2.setkGradientFocus(200);
        readingBanner2.setkStartColor(new java.awt.Color(0, 153, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Voltage");

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setToolTipText("");

        readingTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Vcore",  new Float(1.63)},
                {"Vcc",  new Float(2.54)},
                {"Vio",  new Float(3.38)},
                {"+5V",  new Float(5.05)}
            },
            new String [] {
                "Label", "Voltage (V)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        readingTable3.setSelectionForeground(new java.awt.Color(153, 255, 255));
        jScrollPane4.setViewportView(readingTable3);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Voltage.png"))); // NOI18N

        javax.swing.GroupLayout readingBanner2Layout = new javax.swing.GroupLayout(readingBanner2);
        readingBanner2.setLayout(readingBanner2Layout);
        readingBanner2Layout.setHorizontalGroup(
            readingBanner2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readingBanner2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, readingBanner2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(107, 107, 107))
        );
        readingBanner2Layout.setVerticalGroup(
            readingBanner2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readingBanner2Layout.createSequentialGroup()
                .addGroup(readingBanner2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        readingBanner4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        readingBanner4.setkEndColor(new java.awt.Color(153, 204, 255));
        readingBanner4.setkGradientFocus(200);
        readingBanner4.setkStartColor(new java.awt.Color(0, 153, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Fan Speed");

        jScrollPane15.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane15.setBorder(null);
        jScrollPane15.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane15.setToolTipText("");

        readingTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"CPU Fan",  new Float(3309.0)},
                {"Chassis Fan",  new Float(2246.0)},
                {"Power Fan",  new Float(1592.0)}
            },
            new String [] {
                "Label", "Speed (RPM)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        readingTable7.setSelectionForeground(new java.awt.Color(153, 255, 255));
        jScrollPane15.setViewportView(readingTable7);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fan.png"))); // NOI18N

        javax.swing.GroupLayout readingBanner4Layout = new javax.swing.GroupLayout(readingBanner4);
        readingBanner4.setLayout(readingBanner4Layout);
        readingBanner4Layout.setHorizontalGroup(
            readingBanner4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readingBanner4Layout.createSequentialGroup()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(readingBanner4Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        readingBanner4Layout.setVerticalGroup(
            readingBanner4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readingBanner4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(readingBanner4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        readingBanner5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        readingBanner5.setkEndColor(new java.awt.Color(153, 204, 255));
        readingBanner5.setkGradientFocus(200);
        readingBanner5.setkStartColor(new java.awt.Color(0, 153, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("CPU Usage");

        jScrollPane16.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane16.setBorder(null);
        jScrollPane16.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane16.setToolTipText("");

        readingTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"CPU1",  new Float(81.0)},
                {"CPU2",  new Float(79.0)},
                {"CPU3",  new Float(74.0)},
                {"CPU4",  new Float(78.0)}
            },
            new String [] {
                "Label", "Voltage (V)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        readingTable8.setSelectionForeground(new java.awt.Color(153, 255, 255));
        jScrollPane16.setViewportView(readingTable8);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/CPU.png"))); // NOI18N

        javax.swing.GroupLayout readingBanner5Layout = new javax.swing.GroupLayout(readingBanner5);
        readingBanner5.setLayout(readingBanner5Layout);
        readingBanner5Layout.setHorizontalGroup(
            readingBanner5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readingBanner5Layout.createSequentialGroup()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(readingBanner5Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        readingBanner5Layout.setVerticalGroup(
            readingBanner5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(readingBanner5Layout.createSequentialGroup()
                .addGroup(readingBanner5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        textArea1.setText("I/O properly initialized");

        label1.setText("Log");

        javax.swing.GroupLayout basicReadingsLayout = new javax.swing.GroupLayout(basicReadings);
        basicReadings.setLayout(basicReadingsLayout);
        basicReadingsLayout.setHorizontalGroup(
            basicReadingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicReadingsLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(basicReadingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(basicReadingsLayout.createSequentialGroup()
                        .addGroup(basicReadingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(readingBanner3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(readingBanner2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(56, 56, 56)
                        .addGroup(basicReadingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(readingBanner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(readingBanner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 80, Short.MAX_VALUE))
        );
        basicReadingsLayout.setVerticalGroup(
            basicReadingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicReadingsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(basicReadingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readingBanner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readingBanner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(basicReadingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(readingBanner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readingBanner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pages.add(basicReadings, "card2");

        temperatureTab.setBackground(new java.awt.Color(0, 102, 204));
        temperatureTab.setToolTipText("Select one to apply changes to one of these attributes");
        temperatureTab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                temperatureTabStateChanged(evt);
            }
        });

        tabsMenu.setBackground(new java.awt.Color(0, 0, 102));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"<html><b>HD0</b></html>", "HD0 (128.0GB)", "HD0", "35C", "AdvS...", "$0",  new Integer(40),  new Integer(50)},
                {"<html><b>Temp1</b></html>", "ACPI", "Temp1", "66C", "ISA", "$0",  new Integer(40),  new Integer(50)},
                {"<html><b>Core0</b></html>", "INTEL CORE", "Core0", "70C", "ISA", "$0",  new Integer(40),  new Integer(50)},
                {"<html><b>Core1</b></html>", "INTEL CORE", "Core1", "70C", "ISA", "$0",  new Integer(40),  new Integer(50)},
                {"<html><b>Core2</b></html>", "INTEL CORE", "Core2", "70C", "ISA", "$0",  new Integer(40),  new Integer(50)},
                {"<html><b>Core3</b></html>", "INTEL CORE", "Core3", "70C", "ISA", "$0",  new Integer(40),  new Integer(50)},
                {"<html><b>Core4</b></html>", "INTEL CORE", "Core4", "70C", "ISA", "$0",  new Integer(40),  new Integer(50)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Label", "Chip", "Sensor", "Sample", "BUS", "Address", "Desired Temperature", "Warning Temperature"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        tempTab.setBackground(new java.awt.Color(153, 153, 153));

        warningText.setText("Warning");

        warningSpinner.setToolTipText("A warning will appear if temperatures exceed this value");
        warningSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                warningSpinnerStateChanged(evt);
            }
        });
        warningSpinner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                warningSpinnerMouseClicked(evt);
            }
        });

        warningMeasure.setText("C ");

        desiredText.setText("Desired");

        desiredSpinner.setToolTipText("Enter ");
        desiredSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                desiredSpinnerStateChanged(evt);
            }
        });
        desiredSpinner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desiredSpinnerMouseClicked(evt);
            }
        });

        desiredMeasure.setText("C ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(desiredText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(desiredSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(desiredMeasure)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(warningText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningMeasure)
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warningMeasure)
                    .addComponent(warningSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(warningText)
                    .addComponent(desiredText)
                    .addComponent(desiredSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desiredMeasure))
                .addContainerGap())
        );

        javax.swing.GroupLayout tempTabLayout = new javax.swing.GroupLayout(tempTab);
        tempTab.setLayout(tempTabLayout);
        tempTabLayout.setHorizontalGroup(
            tempTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tempTabLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        tempTabLayout.setVerticalGroup(
            tempTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tempTabLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabsMenuLayout = new javax.swing.GroupLayout(tabsMenu);
        tabsMenu.setLayout(tabsMenuLayout);
        tabsMenuLayout.setHorizontalGroup(
            tabsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabsMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .addComponent(tempTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabsMenuLayout.setVerticalGroup(
            tabsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabsMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tempTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        temperatureTab.addTab("Temperature", tabsMenu);

        fanTab.setBackground(new java.awt.Color(0, 0, 102));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Fan01", "WinbondW83782D", "Fan1", "5037", "SMBus", "0000000000"},
                {"Fan02", "WinbondW83782D", "Fan2", "5769", "SMBus", "0000000000"},
                {"Fan03", "WinbondW83782D", "Fan3", "0", "SMBus", "0000000000"},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Label", "Chip", "Sensor", "Sample", "BUS", "Address"
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable2);

        javax.swing.GroupLayout fanTabLayout = new javax.swing.GroupLayout(fanTab);
        fanTab.setLayout(fanTabLayout);
        fanTabLayout.setHorizontalGroup(
            fanTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fanTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        fanTabLayout.setVerticalGroup(
            fanTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fanTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        temperatureTab.addTab("Fans", fanTab);

        speedTab.setBackground(new java.awt.Color(0, 0, 102));

        jPanel21.setBackground(new java.awt.Color(153, 153, 153));

        warningText7.setText("Maximum value");

        warningSpinner7.setToolTipText("Enter ");
        warningSpinner7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                warningSpinner7StateChanged(evt);
            }
        });
        warningSpinner7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                warningSpinner7MouseClicked(evt);
            }
        });

        warningMeasure7.setText("%");

        desiredText7.setText("Minimum value");

        desiredSpinner7.setToolTipText("Enter ");
        desiredSpinner7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                desiredSpinner7StateChanged(evt);
            }
        });
        desiredSpinner7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desiredSpinner7MouseClicked(evt);
            }
        });

        desiredMeasure7.setText("%");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desiredText7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desiredSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(desiredMeasure7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(warningText7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningMeasure7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warningMeasure7)
                    .addComponent(warningSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(warningText7)
                    .addComponent(desiredText7)
                    .addComponent(desiredSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desiredMeasure7))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"<html><b>Speed01</b></html>", "Winbond83782D", "Pwn1", "SMBus", "$2D",  new Integer(100),  new Integer(100)},
                {"<html><b>Speed02</b></html>", "Winbond83782D", "Pwn2", "SMBus", "$2D",  new Integer(100),  new Integer(100)},
                {"<html><b>Speed03</b></html>", "Winbond83782D", "Pwn3", "SMBus", "$2D",  new Integer(100),  new Integer(100)},
                {"<html><b>Speed04</b></html>", "Winbond83782D", "Pwn4", "SMBus", "$2D",  new Integer(100),  new Integer(100)},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Label", "Chip", "Sensor", "BUS", "Address", "Desired Speed", "Warning Speed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable4);

        javax.swing.GroupLayout speedTabLayout = new javax.swing.GroupLayout(speedTab);
        speedTab.setLayout(speedTabLayout);
        speedTabLayout.setHorizontalGroup(
            speedTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(speedTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(speedTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        speedTabLayout.setVerticalGroup(
            speedTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, speedTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        temperatureTab.addTab("Speed", speedTab);

        voltageTab.setBackground(new java.awt.Color(0, 0, 102));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"HD0", "HD0 (128.0GB)", "HD0Temp1", "5V", "AdvS...", "$0",  new Integer(5),  new Integer(10)},
                {"Temp1", "ACPI", "Temp1", "20V", "ISA", "$0",  new Integer(20),  new Integer(25)},
                {"Core0", "INTEL CORE", "Core0", "15V", "ISA", "$0",  new Integer(15),  new Integer(20)},
                {"Core1", "INTEL CORE", "Core1", "15V", "ISA", "$0",  new Integer(15),  new Integer(20)},
                {"Core2", "INTEL CORE", "Core2", "15V", "ISA", "$0",  new Integer(15),  new Integer(20)},
                {"Core3", "INTEL CORE", "Core3", "10V", "ISA", "$0",  new Integer(10),  new Integer(15)},
                {"Core4", "INTEL CORE", "Core4", "15V", "ISA", "$0",  new Integer(15),  new Integer(20)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Label", "Chip", "Sensor", "Sample", "BUS", "Address", "Desired Voltage", "Warning Voltage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable3);

        jPanel17.setBackground(new java.awt.Color(153, 153, 153));

        jPanel18.setToolTipText("");

        warningText5.setText("Warning");

        warningSpinner5.setToolTipText("Enter ");
        warningSpinner5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                warningSpinner5StateChanged(evt);
            }
        });
        warningSpinner5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                warningSpinner5MouseClicked(evt);
            }
        });

        warningMeasure5.setText("V");

        desiredText5.setText("Desired");
        desiredText5.setToolTipText("");

        desiredSpinner5.setToolTipText("Enter ");
        desiredSpinner5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                desiredSpinner5StateChanged(evt);
            }
        });
        desiredSpinner5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desiredSpinner5MouseClicked(evt);
            }
        });

        desiredMeasure5.setText("V");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(desiredText5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(desiredSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(desiredMeasure5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(warningText5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningMeasure5)
                .addGap(69, 69, 69))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(warningMeasure5)
                    .addComponent(warningSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(warningText5)
                    .addComponent(desiredText5)
                    .addComponent(desiredSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desiredMeasure5))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout voltageTabLayout = new javax.swing.GroupLayout(voltageTab);
        voltageTab.setLayout(voltageTabLayout);
        voltageTabLayout.setHorizontalGroup(
            voltageTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(voltageTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(voltageTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        voltageTabLayout.setVerticalGroup(
            voltageTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(voltageTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        temperatureTab.addTab("Voltage", voltageTab);

        advancedFanControl.setBackground(new java.awt.Color(0, 0, 102));

        fanControlPanel.setBackground(new java.awt.Color(255, 255, 255));
        fanControlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Controlled Speed");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pwm -Pwm1 from F213123 @ $2131 on ISA", "Case Fans - Pwm2 from F123123 @ 132 on ISA", "SP120 - Pwm3 from F712321 @ 3123 on ISA", "Aerocool Rad - Pwm1 from F72312 @ 0212 on ISA" }));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel11.setText("Temperatures");

        fanAddButton.setText("Add");
        fanAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fanAddButtonActionPerformed(evt);
            }
        });

        fanDeleteButton.setText("Delete");
        fanDeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fanDeleteButtonMouseClicked(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MAX of Speeds", "SUM of Speeds" }));

        jLabel13.setText("Method");

        javax.swing.GroupLayout fanControlPanelLayout = new javax.swing.GroupLayout(fanControlPanel);
        fanControlPanel.setLayout(fanControlPanelLayout);
        fanControlPanelLayout.setHorizontalGroup(
            fanControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fanControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fanControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(fanControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(fanControlPanelLayout.createSequentialGroup()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(fanControlPanelLayout.createSequentialGroup()
                            .addComponent(fanAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(fanDeleteButton))
                        .addGroup(fanControlPanelLayout.createSequentialGroup()
                            .addComponent(jCheckBox1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        fanControlPanelLayout.setVerticalGroup(
            fanControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fanControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fanControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(fanControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fanControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fanAddButton)
                    .addComponent(fanDeleteButton))
                .addContainerGap())
        );

        fanChartPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fanChartPanel.setMinimumSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout fanChartPanelLayout = new javax.swing.GroupLayout(fanChartPanel);
        fanChartPanel.setLayout(fanChartPanelLayout);
        fanChartPanelLayout.setHorizontalGroup(
            fanChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        fanChartPanelLayout.setVerticalGroup(
            fanChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("Fan Controllers");

        fanControllerList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fanControllerList.setToolTipText("List of cutom fan controllers");
        fanControllerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fanControllerListMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(fanControllerList);

        addFanButton.setText("Add");
        addFanButton.setToolTipText("Click here to add a custom fan controller");
        addFanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFanButtonActionPerformed(evt);
            }
        });

        removeFanButton.setText("Remove");
        removeFanButton.setToolTipText("Click here to remove a custom fan controller if any is selected.");
        removeFanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFanButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addFanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeFanButton, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(addFanButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeFanButton)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout advancedFanControlLayout = new javax.swing.GroupLayout(advancedFanControl);
        advancedFanControl.setLayout(advancedFanControlLayout);
        advancedFanControlLayout.setHorizontalGroup(
            advancedFanControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advancedFanControlLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(advancedFanControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(advancedFanControlLayout.createSequentialGroup()
                        .addComponent(fanControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fanChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        advancedFanControlLayout.setVerticalGroup(
            advancedFanControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advancedFanControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(advancedFanControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fanControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fanChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        temperatureTab.addTab("Advanced Fan Control", advancedFanControl);

        javax.swing.GroupLayout configureLayout = new javax.swing.GroupLayout(configure);
        configure.setLayout(configureLayout);
        configureLayout.setHorizontalGroup(
            configureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configureLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(temperatureTab, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );
        configureLayout.setVerticalGroup(
            configureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configureLayout.createSequentialGroup()
                .addComponent(temperatureTab, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        pages.add(configure, "card3");

        debugging.setBackground(new java.awt.Color(255, 255, 255));
        debugging.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                debuggingMouseClicked(evt);
            }
        });

        upperWindowDebugging.setBackground(new java.awt.Color(204, 204, 255));

        chipsetText.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chipsetText.setText("Chipset");

        comboBoxDebugging.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chipset 1", "Chipset 2", "Chipset 3" }));
        comboBoxDebugging.setToolTipText("Select a chipset");
        comboBoxDebugging.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDebuggingActionPerformed(evt);
            }
        });

        smbusaddressText.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        smbusaddressText.setText("SMBus base address");

        smbusaddressTextField.setText("$0");
        smbusaddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smbusaddressTextFieldActionPerformed(evt);
            }
        });

        smbusaddressText1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        smbusaddressText1.setText("Revision");

        revisionTextBox.setText("1.1");
        revisionTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revisionTextBoxActionPerformed(evt);
            }
        });

        ioCheckBox.setBackground(new java.awt.Color(204, 204, 255));
        ioCheckBox.setText("I/O enabled");

        dimInfoBorder.setBackground(new java.awt.Color(204, 204, 255));
        dimInfoBorder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dimTextArea.setColumns(20);
        dimTextArea.setRows(5);
        jScrollPane13.setViewportView(dimTextArea);

        javax.swing.GroupLayout dimInfoBorderLayout = new javax.swing.GroupLayout(dimInfoBorder);
        dimInfoBorder.setLayout(dimInfoBorderLayout);
        dimInfoBorderLayout.setHorizontalGroup(
            dimInfoBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dimInfoBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dimInfoBorderLayout.setVerticalGroup(
            dimInfoBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dimInfoBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setText("DIM info");

        readInfoButton.setText("Read Info");
        readInfoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                readInfoButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout upperWindowDebuggingLayout = new javax.swing.GroupLayout(upperWindowDebugging);
        upperWindowDebugging.setLayout(upperWindowDebuggingLayout);
        upperWindowDebuggingLayout.setHorizontalGroup(
            upperWindowDebuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperWindowDebuggingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperWindowDebuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dimInfoBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(upperWindowDebuggingLayout.createSequentialGroup()
                        .addGroup(upperWindowDebuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upperWindowDebuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ioCheckBox)
                                .addGroup(upperWindowDebuggingLayout.createSequentialGroup()
                                    .addComponent(smbusaddressText1)
                                    .addGap(85, 85, 85)
                                    .addComponent(revisionTextBox))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperWindowDebuggingLayout.createSequentialGroup()
                                    .addComponent(smbusaddressText)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(smbusaddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperWindowDebuggingLayout.createSequentialGroup()
                                    .addComponent(chipsetText)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comboBoxDebugging, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4)
                            .addComponent(readInfoButton))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        upperWindowDebuggingLayout.setVerticalGroup(
            upperWindowDebuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperWindowDebuggingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperWindowDebuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chipsetText)
                    .addComponent(comboBoxDebugging, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperWindowDebuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(smbusaddressText)
                    .addComponent(smbusaddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperWindowDebuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(smbusaddressText1)
                    .addComponent(revisionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ioCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dimInfoBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(readInfoButton)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        scanDMBusButton.setText("Scan for SMBus Devices");
        scanDMBusButton.setToolTipText("");
        scanDMBusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scanDMBusButtonMouseClicked(evt);
            }
        });
        scanDMBusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanDMBusButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        noteBox.setColumns(20);
        noteBox.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        noteBox.setRows(5);
        noteBox.setText("OK! I've added an option to easily send debug info to me without having to cut and\n paste here and there :-) Include your email address if you would like me to contact\nyou if needed. You can even add some notes to report. Please, use this option to let\nme know everything went fine for you :-)\n\nPlease include your Motherboard MODEL :-)");
        jScrollPane11.setViewportView(noteBox);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sendReportButton.setText("Send Report");
        sendReportButton.setToolTipText("");
        sendReportButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendReportButtonMouseClicked(evt);
            }
        });

        dmBusSearchBorder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        smBusScanTextArea.setColumns(20);
        smBusScanTextArea.setRows(5);
        dmbusDeviceTextBox.setViewportView(smBusScanTextArea);

        javax.swing.GroupLayout dmBusSearchBorderLayout = new javax.swing.GroupLayout(dmBusSearchBorder);
        dmBusSearchBorder.setLayout(dmBusSearchBorderLayout);
        dmBusSearchBorderLayout.setHorizontalGroup(
            dmBusSearchBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dmBusSearchBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dmbusDeviceTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        dmBusSearchBorderLayout.setVerticalGroup(
            dmBusSearchBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dmBusSearchBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dmbusDeviceTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout debuggingLayout = new javax.swing.GroupLayout(debugging);
        debugging.setLayout(debuggingLayout);
        debuggingLayout.setHorizontalGroup(
            debuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(debuggingLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(upperWindowDebugging, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(debuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(debuggingLayout.createSequentialGroup()
                        .addGap(431, 431, 431)
                        .addComponent(sendReportButton))
                    .addGroup(debuggingLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(debuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dmBusSearchBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scanDMBusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(debuggingLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 58, Short.MAX_VALUE))
        );
        debuggingLayout.setVerticalGroup(
            debuggingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(debuggingLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(scanDMBusButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dmBusSearchBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sendReportButton)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(debuggingLayout.createSequentialGroup()
                .addComponent(upperWindowDebugging, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pages.add(debugging, "card4");

        upperWindowDebugging1.setBackground(new java.awt.Color(204, 204, 255));

        comboBoxDiagnostics.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HD0 - 128.0GB - SAMSUNG..." }));
        comboBoxDiagnostics.setToolTipText("Select a hard disk");
        comboBoxDiagnostics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxDiagnosticsActionPerformed(evt);
            }
        });

        dimInfoBorder1.setBackground(new java.awt.Color(204, 204, 255));
        dimInfoBorder1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        modelText.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        modelText.setText("Model");

        modelTextField.setText("SAMSUNG SSD...");

        firmWareText.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        firmWareText.setText("Firmware");

        firmwareTextField.setText("MA341EV");

        javax.swing.GroupLayout dimInfoBorder1Layout = new javax.swing.GroupLayout(dimInfoBorder1);
        dimInfoBorder1.setLayout(dimInfoBorder1Layout);
        dimInfoBorder1Layout.setHorizontalGroup(
            dimInfoBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dimInfoBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dimInfoBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dimInfoBorder1Layout.createSequentialGroup()
                        .addComponent(modelText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dimInfoBorder1Layout.createSequentialGroup()
                        .addComponent(firmWareText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(firmwareTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dimInfoBorder1Layout.setVerticalGroup(
            dimInfoBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dimInfoBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dimInfoBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelText)
                    .addComponent(modelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dimInfoBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firmWareText)
                    .addComponent(firmwareTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(269, Short.MAX_VALUE))
        );

        hardDiskText.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        hardDiskText.setText("Hard Disk");

        javax.swing.GroupLayout upperWindowDebugging1Layout = new javax.swing.GroupLayout(upperWindowDebugging1);
        upperWindowDebugging1.setLayout(upperWindowDebugging1Layout);
        upperWindowDebugging1Layout.setHorizontalGroup(
            upperWindowDebugging1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperWindowDebugging1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboBoxDiagnostics, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(upperWindowDebugging1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperWindowDebugging1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dimInfoBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(upperWindowDebugging1Layout.createSequentialGroup()
                        .addComponent(hardDiskText)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        upperWindowDebugging1Layout.setVerticalGroup(
            upperWindowDebugging1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperWindowDebugging1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(hardDiskText)
                .addGap(4, 4, 4)
                .addComponent(comboBoxDiagnostics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dimInfoBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        inDepthScanButton.setText("In-depth online analysis of Hard Disk");
        inDepthScanButton.setToolTipText("You will need Internet connection for this analysis");
        inDepthScanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inDepthScanButtonMouseClicked(evt);
            }
        });

        extendedTestButton.setText("Extended Test");
        extendedTestButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                extendedTestButtonMouseClicked(evt);
            }
        });

        shortTestButton.setText("Short Test");
        shortTestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortTestButtonActionPerformed(evt);
            }
        });

        statusText.setText("Status");

        statusTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        diagnosticsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Reallocated Sector Count", "100", "100", "10", "00000000000", ""},
                {"Powers on Hours Count", "99", "76", "0", "00000000000", null},
                {"Power Cycle Count", "98", "98", "0", "00000000000", null},
                {"(Unknown Attribute)", "100", "89", "10", "00000000000", null},
                {"(Unknown Attribute)", "100", "87", "10", "00000000000", null},
                {"(Unknown Attribute)", "100", "91", "5", "00000000000", null},
                {"(Unknown Attribute)", "100", "96", "10", "00000000000", null},
                {"(Unknown Attribute)", "100", "87", "10", "00000000000", null},
                {"(Unknown Attribute)", "100", "81", "10", "00000000000", null},
                {"(Unknown Attribute)", "100", "94", "10", "00000000000", null},
                {"(Unknown Attribute)", "98", "90", "10", "00000000000", null},
                {"Reported Uncorrectable Error", "100", "94", "0", "00000000000", null},
                {"Temperature", "65", "52", "0", "00000000000", null},
                {"Hardware ECC Recovered", "99", "79", "0", "00000000000", null},
                {"UntraATA CRC Error Rate", "100", "87", "0", "00000000000", null},
                {null, null, null, null, null, null},
                {"", null, null, null, null, null}
            },
            new String [] {
                "Attribute", "Value", "Worst", "Wam", "Raw", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        diagnosticsTable.setToolTipText("");
        jScrollPane12.setViewportView(diagnosticsTable);

        fitnessText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fitnessText.setText("Fitness");

        performanceText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        performanceText.setText("Performance");

        fitnessPercentage.setText("80%");

        fitnessPercentage1.setText("80%");

        javax.swing.GroupLayout diagnosticsLayout = new javax.swing.GroupLayout(diagnostics);
        diagnostics.setLayout(diagnosticsLayout);
        diagnosticsLayout.setHorizontalGroup(
            diagnosticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagnosticsLayout.createSequentialGroup()
                .addComponent(upperWindowDebugging1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(diagnosticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(diagnosticsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(diagnosticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(diagnosticsLayout.createSequentialGroup()
                                .addComponent(statusText)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(statusTextField))
                            .addComponent(inDepthScanButton, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, diagnosticsLayout.createSequentialGroup()
                                .addComponent(extendedTestButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(shortTestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(diagnosticsLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(fitnessText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fitnessBar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fitnessPercentage)
                        .addGap(48, 48, 48)
                        .addComponent(performanceText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(performanceBar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fitnessPercentage1)))
                .addGap(0, 38, Short.MAX_VALUE))
        );
        diagnosticsLayout.setVerticalGroup(
            diagnosticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diagnosticsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(diagnosticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(diagnosticsLayout.createSequentialGroup()
                        .addComponent(inDepthScanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(diagnosticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(extendedTestButton)
                            .addComponent(shortTestButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(diagnosticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(diagnosticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(diagnosticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fitnessText)
                                .addComponent(fitnessBar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(performanceText)
                                .addComponent(performanceBar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fitnessPercentage1)))
                    .addGroup(diagnosticsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(fitnessPercentage)))
                .addGap(116, 116, 116))
            .addGroup(diagnosticsLayout.createSequentialGroup()
                .addComponent(upperWindowDebugging1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pages.add(diagnostics, "card5");

        upperWindowCharts.setBackground(new java.awt.Color(204, 204, 255));

        tabsPaneCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabsPaneChartsMouseClicked(evt);
            }
        });

        voltageChartsPanel.setBackground(new java.awt.Color(255, 255, 255));

        voltageListCharts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"HD0"},
                {"Temp1"},
                {"Core0"},
                {"Core1"},
                {"Core2"},
                {"Core3"},
                {"Core4"}
            },
            new String [] {
                "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        voltageListCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                voltageListChartsMouseClicked(evt);
            }
        });
        voltageChartsTable.setViewportView(voltageListCharts);

        javax.swing.GroupLayout voltageChartsPanelLayout = new javax.swing.GroupLayout(voltageChartsPanel);
        voltageChartsPanel.setLayout(voltageChartsPanelLayout);
        voltageChartsPanelLayout.setHorizontalGroup(
            voltageChartsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voltageChartsTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        voltageChartsPanelLayout.setVerticalGroup(
            voltageChartsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voltageChartsTable, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
        );

        tabsPaneCharts.addTab("Voltage", voltageChartsPanel);

        temperatureListCharts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"HD0"},
                {"Temp1"},
                {"Core0"},
                {"Core1"},
                {"Core2"},
                {"Core3"},
                {"Core4"}
            },
            new String [] {
                "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        temperatureListCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                temperatureListChartsMouseClicked(evt);
            }
        });
        voltageChartsTable1.setViewportView(temperatureListCharts);

        javax.swing.GroupLayout temperatureChartsPanelLayout = new javax.swing.GroupLayout(temperatureChartsPanel);
        temperatureChartsPanel.setLayout(temperatureChartsPanelLayout);
        temperatureChartsPanelLayout.setHorizontalGroup(
            temperatureChartsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voltageChartsTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        temperatureChartsPanelLayout.setVerticalGroup(
            temperatureChartsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voltageChartsTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
        );

        tabsPaneCharts.addTab("Temperature", temperatureChartsPanel);

        fanSpeedsListCharts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Speed01"},
                {"Speed02"},
                {"Speed03"},
                {"Speed04"}
            },
            new String [] {
                "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fanSpeedsListCharts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fanSpeedsListChartsMouseClicked(evt);
            }
        });
        voltageChartsTable2.setViewportView(fanSpeedsListCharts);

        javax.swing.GroupLayout fanSpeedsChartsPanelLayout = new javax.swing.GroupLayout(fanSpeedsChartsPanel);
        fanSpeedsChartsPanel.setLayout(fanSpeedsChartsPanelLayout);
        fanSpeedsChartsPanelLayout.setHorizontalGroup(
            fanSpeedsChartsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voltageChartsTable2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        fanSpeedsChartsPanelLayout.setVerticalGroup(
            fanSpeedsChartsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(voltageChartsTable2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
        );

        tabsPaneCharts.addTab("Fan Speeds", fanSpeedsChartsPanel);

        javax.swing.GroupLayout upperWindowChartsLayout = new javax.swing.GroupLayout(upperWindowCharts);
        upperWindowCharts.setLayout(upperWindowChartsLayout);
        upperWindowChartsLayout.setHorizontalGroup(
            upperWindowChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperWindowChartsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabsPaneCharts)
                .addContainerGap())
        );
        upperWindowChartsLayout.setVerticalGroup(
            upperWindowChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperWindowChartsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tabsPaneCharts, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chartsBorder.setBackground(new java.awt.Color(204, 204, 204));
        chartsBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        chartsBorder.setToolTipText("");

        javax.swing.GroupLayout chartsBorderLayout = new javax.swing.GroupLayout(chartsBorder);
        chartsBorder.setLayout(chartsBorderLayout);
        chartsBorderLayout.setHorizontalGroup(
            chartsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        chartsBorderLayout.setVerticalGroup(
            chartsBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );

        startTimeText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        startTimeText.setText("Start time");

        endTimeText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        endTimeText.setText("End time");

        startTimeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1/1/2009 00:00am", "1/1/2009 00:15am", "1/1/2009 00:30am", "1/1/2009 00:45am", "1/1/2009 01:00am" }));
        startTimeBox.setToolTipText("The leftmost value in the x-axis");
        startTimeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startTimeBoxActionPerformed(evt);
            }
        });

        endTimeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2/1/2009 00:00am", "2/1/2009 00:15am", "2/1/2009 00:30am", "2/1/2009 00:45am", "2/1/2009 01:00am" }));
        endTimeBox.setToolTipText("THe right-most value on the x-axis");
        endTimeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endTimeBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chartsLayout = new javax.swing.GroupLayout(charts);
        charts.setLayout(chartsLayout);
        chartsLayout.setHorizontalGroup(
            chartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chartsLayout.createSequentialGroup()
                .addComponent(upperWindowCharts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(chartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chartsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(startTimeText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startTimeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(endTimeText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(endTimeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(chartsBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        chartsLayout.setVerticalGroup(
            chartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chartsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartsBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(chartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTimeText)
                    .addComponent(startTimeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endTimeText)
                    .addComponent(endTimeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 92, Short.MAX_VALUE))
            .addComponent(upperWindowCharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pages.add(charts, "card6");

        clockTab.setBackground(new java.awt.Color(0, 0, 102));

        clockBorder.setBackground(new java.awt.Color(255, 255, 255));

        motherBoardText.setText("Motherboard");

        motherboard1ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ABIT" }));

        motherboard2ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BP6" }));

        clockText1.setText("Clock");

        clockComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ICS 9248-90" }));

        clockCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        clockCheckBox.setText("Use this Clock Generator");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        setToText1.setText("Set to");

        setToText2.setText("Set to");

        afterRunning1.setText("after running below");

        afterRunning2.setText("after running above");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPU: 66,8MHz PCI: 33,4MHz" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPU: 92,0MHz PCI: 30,7MHz" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Status");

        afterRunning3.setText("% CPU usage for");

        afterRunning4.setText("% CPU usage for");

        lowPowerPReferredText.setText("Low power preferred");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(setToText1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(statusTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(afterRunning2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(runningBelowSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lowPowerPReferredText)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(afterRunning4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cpuUsageSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(afterRunning1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(runningBelowSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(afterRunning3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cpuUsageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(setToText2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setToText1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(afterRunning1)
                    .addComponent(runningBelowSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afterRunning3)
                    .addComponent(cpuUsageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setToText2))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(runningBelowSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(afterRunning4)
                        .addComponent(cpuUsageSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(afterRunning2))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lowPowerPReferredText)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        automaticClockControlText.setText("Automatic CLOCK Control");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane9.setViewportView(jTextArea1);

        computedText.setText("Computed: ");

        readClockButton.setText("Read Clock");

        setClockButton.setText("Set Clock");

        javax.swing.GroupLayout clockBorderLayout = new javax.swing.GroupLayout(clockBorder);
        clockBorder.setLayout(clockBorderLayout);
        clockBorderLayout.setHorizontalGroup(
            clockBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clockBorderLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(clockBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, clockBorderLayout.createSequentialGroup()
                        .addGroup(clockBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clockBorderLayout.createSequentialGroup()
                                .addComponent(computedText, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(computedClockTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(readClockButton)
                                .addGap(18, 18, 18)
                                .addComponent(setClockButton))
                            .addGroup(clockBorderLayout.createSequentialGroup()
                                .addGroup(clockBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(clockBorderLayout.createSequentialGroup()
                                        .addComponent(clockText1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(clockComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(clockBorderLayout.createSequentialGroup()
                                        .addComponent(motherBoardText)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(motherboard1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(automaticClockControlText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(34, 34, 34)
                                .addGroup(clockBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(motherboard2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clockCheckBox))))
                        .addGap(0, 127, Short.MAX_VALUE)))
                .addGap(79, 79, 79))
        );
        clockBorderLayout.setVerticalGroup(
            clockBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clockBorderLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(clockBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(motherBoardText)
                    .addComponent(motherboard1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(motherboard2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clockBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clockText1)
                    .addComponent(clockComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clockCheckBox))
                .addGap(18, 18, 18)
                .addComponent(automaticClockControlText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(clockBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(computedText)
                    .addComponent(computedClockTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readClockButton)
                    .addComponent(setClockButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout clockTabLayout = new javax.swing.GroupLayout(clockTab);
        clockTab.setLayout(clockTabLayout);
        clockTabLayout.setHorizontalGroup(
            clockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clockTabLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(clockBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        clockTabLayout.setVerticalGroup(
            clockTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clockTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clockBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pages.add(clockTab, "card7");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(sideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pages, javax.swing.GroupLayout.PREFERRED_SIZE, 513, Short.MAX_VALUE)
                    .addComponent(sideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configureButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configureButtonMouseClicked
       //if(PROFILE_STATE == ADVANCED_USER || PROFILE_STATE == EXPERT_USER)
       //{
            //Remove panels
            pages.removeAll();
       
            //Add basic readings panel
            pages.add(configure);
            pages.repaint();
            pages.revalidate();
       
            oldState = state;
            state = CONFIGURE;
       
            resetButtonColor();
       
            configureButton.setkEndColor(setColor("DARK"));
            configureButton.setkStartColor(setColor("MEDIUM"));
            
            if(PROFILE_STATE == BEGINNER_USER){
                JOptionPane.showMessageDialog(null, "This tab is used to configure readings. Since you are a beginner user, all functions to change anything are disabled. It is safe to explore :)");
            }
            else if(PROFILE_STATE == ADVANCED_USER){
                JOptionPane.showMessageDialog(null, "This tab is used to configure readings. Since you are an advanced user, some functions are enabled for you to configure, but not the more dangerous features.");
            }
            else{
                JOptionPane.showMessageDialog(null, "This tab is used to configure readings. Since you are an expert user, all functions are enabled. Use with caustion!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            
            firstTimeConfigure = false;
       //}
       //else
       //{
           //JOptionPane.showMessageDialog(null, "You do not have permission to use this feature. To gain access, change profile to Advanced or Expert User mode in the settings.");
       //}
       
    }//GEN-LAST:event_configureButtonMouseClicked

    private void homeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeButtonMouseClicked
        //Remove panels
       pages.removeAll();
       
       //Add basic readings panel
       pages.add(basicReadings);
       pages.repaint();
       pages.revalidate();
       
       oldState = state;
       state = HOME;
       
       resetButtonColor();
       
       homeButton.setkEndColor(setColor("DARK"));
       homeButton.setkStartColor(setColor("MEDIUM"));
       
       
    }//GEN-LAST:event_homeButtonMouseClicked

    private void debuggingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_debuggingMouseClicked
        //Remove panels
       pages.removeAll();
       
       //Add basic readings panel
       pages.add(debugging);
       pages.repaint();
       pages.revalidate();
       
       oldState = state;
       state = DEBUGGING;
       
       resetButtonColor();
       
       debuggingButton.setkEndColor(setColor("DARK"));
       debuggingButton.setkStartColor(setColor("MEDIUM"));
       
       
    }//GEN-LAST:event_debuggingMouseClicked

    private void comboBoxDebuggingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDebuggingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDebuggingActionPerformed

    private void smbusaddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smbusaddressTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_smbusaddressTextFieldActionPerformed

    private void revisionTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revisionTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_revisionTextBoxActionPerformed

    private void scanDMBusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanDMBusButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scanDMBusButtonActionPerformed

    private void comboBoxDiagnosticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxDiagnosticsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDiagnosticsActionPerformed

    private void debuggingButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_debuggingButtonMouseClicked
         //Remove panels
       pages.removeAll();
       
       //Add basic readings panel
       pages.add(debugging);
       pages.repaint();
       pages.revalidate();
       
       oldState = state;
       state = DEBUGGING;
       
       resetButtonColor();
       
       debuggingButton.setkEndColor(setColor("DARK"));
       debuggingButton.setkStartColor(setColor("MEDIUM"));
       
       
    }//GEN-LAST:event_debuggingButtonMouseClicked

    private void diagnosticsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diagnosticsButtonMouseClicked
         //Remove panels
       pages.removeAll();
       
       //Add basic readings panel
       pages.add(diagnostics);
       pages.repaint();
       pages.revalidate();
       
       oldState = state;
       state = DIAGNOSTICS;
       resetButtonColor();
       
       diagnosticsButton.setkEndColor(setColor("DARK"));
       diagnosticsButton.setkStartColor(setColor("MEDIUM"));
       
       
    }//GEN-LAST:event_diagnosticsButtonMouseClicked

    private void chartsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chartsButtonMouseClicked
          //Remove panels
       pages.removeAll();
       
       //Add basic readings panel
       pages.add(charts);
       pages.repaint();
       pages.revalidate();
       
       oldState = state;
       state = CHARTS;    
       resetButtonColor();


       chartsButton.setkEndColor(setColor("DARK"));
       chartsButton.setkStartColor(setColor("MEDIUM"));
       
       
    }//GEN-LAST:event_chartsButtonMouseClicked

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

    private void settingsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButtonMouseClicked
        if(!settingsOpened)
        {
        
            java.awt.EventQueue.invokeLater(new Runnable() {
                Settings settings = new Settings();
                
                public void run() {
                    settings.setHome(Home.this);
                    settings.setProfile(PROFILE_STATE);
                    settings.changeProfile(PROFILE_STATE);
                    settings.setColorState(colorState);
                    settings.changeColorScheme(colorState);
                    
                    settingsOpened = true;
                    if(help != null)
                    {
                        settings.setHelp(help);
                    }
                    
                    if(report != null)
                    {
                        settings.setReport(report);
                    }
                    
                    settings.setVisible(true);
                    
                    if(prefs != null)
                    {
                        settings.setSettingsMap(prefs);
                    }
                    
                    if(mailPrefs != null){
                        settings.setMailPreferencesMap(mailPrefs);
                    }
                    
                    if(internetPrefs != null){
                        settings.setInternetPreferencesMap(internetPrefs);
                    }
                    
                    if(eventsModel != null){
                        settings.setEventsModel(eventsModel);
                    }
                }
            });
        }
    }//GEN-LAST:event_settingsButtonMouseClicked

    private void helpButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseClicked
       if(!helpOpened)
       {
            java.awt.EventQueue.invokeLater(new Runnable() {
           
                Help help = new Help();
                
                public void run() {
                    
                    help.setHome(Home.this);
                    help.setColorState(colorState);
                    
                    helpOpened = true; 

                    help.setVisible(true);
                }
            });
       }
    }//GEN-LAST:event_helpButtonMouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void clockButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clockButtonMouseClicked
       if(PROFILE_STATE == EXPERT_USER)
       {
            pages.removeAll();
       
            //Add basic readings panel
            pages.add(clockTab);
            pages.repaint();
            pages.revalidate();
       
            oldState = state;
            state = CLOCK;

            resetButtonColor();
       
            clockButton.setkEndColor(setColor("DARK"));
            clockButton.setkStartColor(setColor("MEDIUM"));
       }
       else
       {
           JOptionPane.showMessageDialog(null, "You do not have permission to use this feature. To gain access, change profile to Expert User mode in the settings.");
       }
               
       
    }//GEN-LAST:event_clockButtonMouseClicked

    private void settingsButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsButtonMouseMoved
         settingsButton.addMouseListener(new MouseAdapter()
       {
           public void mouseEntered(MouseEvent e)
           {
                   settingsButton.setBorder(new LineBorder(Color.WHITE));
           }
       });
    }//GEN-LAST:event_settingsButtonMouseMoved

    private void helpButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpButtonMouseMoved
          helpButton.addMouseListener(new MouseAdapter()
       {
           public void mouseEntered(MouseEvent e)
           {
                   helpButton.setBorder(new LineBorder(Color.WHITE));
           }
       });
    }//GEN-LAST:event_helpButtonMouseMoved

    private void readInfoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readInfoButtonMouseClicked
        dimTextArea.setText("Unable to gather information on the DIM");
    }//GEN-LAST:event_readInfoButtonMouseClicked

    private void scanDMBusButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scanDMBusButtonMouseClicked
        try
        {
            smBusScanTextArea.setText("");
            TimeUnit.SECONDS.sleep(4);
            smBusScanTextArea.setText("Unable to gather data.");
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_scanDMBusButtonMouseClicked

    private void configureButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configureButtonMouseExited
        //if(state != CONFIGURE)
        //{
            //if(PROFILE_STATE != BEGINNER_USER)
            //{
                configureButton.setkStartColor(setColor("BRIGHT"));
                configureButton.setkEndColor(setColor("DARK"));
            //}
        //}
        //else
        //{
            //configureButton.setkStartColor(setColor("DARK"));
            //configureButton.setkEndColor(setColor("DARK"));
        //}
    }//GEN-LAST:event_configureButtonMouseExited

    private void configureButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configureButtonMouseEntered

        //if(PROFILE_STATE != BEGINNER_USER)
        //{
            configureButton.setkEndColor(setColor("DARK"));
            configureButton.setkStartColor(setColor("MEDIUM"));
        //}
        //else
        //{
            //configureButton.setkEndColor(new Color(105,105,105));
            //configureButton.setkStartColor(new Color(105,105,105));
        //}

    }//GEN-LAST:event_configureButtonMouseEntered

    private void homeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeButtonMouseEntered

        homeButton.setkStartColor(setColor("MEDIUM"));
        homeButton.setkEndColor(setColor("DARK"));
    }//GEN-LAST:event_homeButtonMouseEntered

    private void homeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeButtonMouseExited
        if(state != HOME)
        {
            homeButton.setkStartColor(setColor("BRIGHT"));
            homeButton.setkEndColor(setColor("DARK"));
        }
        else
        {
            homeButton.setkStartColor(setColor("MEDIUM"));
            homeButton.setkEndColor(setColor("DARK"));
        }
    }//GEN-LAST:event_homeButtonMouseExited

    private void debuggingButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_debuggingButtonMouseEntered

        debuggingButton.setkStartColor(setColor("MEDIUM"));
        debuggingButton.setkEndColor(setColor("DARK"));
    }//GEN-LAST:event_debuggingButtonMouseEntered

    private void debuggingButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_debuggingButtonMouseExited
        if(state != DEBUGGING)
        {
            debuggingButton.setkStartColor(setColor("BRIGHT"));
            debuggingButton.setkEndColor(setColor("DARK"));
        }
        else
        {
            debuggingButton.setkStartColor(setColor("MEDIUM"));
            debuggingButton.setkEndColor(setColor("DARK"));
        }
    }//GEN-LAST:event_debuggingButtonMouseExited

    private void diagnosticsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diagnosticsButtonMouseEntered

        diagnosticsButton.setkStartColor(setColor("MEDIUM"));
        diagnosticsButton.setkEndColor(setColor("DARK"));
    }//GEN-LAST:event_diagnosticsButtonMouseEntered

    private void diagnosticsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diagnosticsButtonMouseExited
        if(state != DIAGNOSTICS)
        {
            diagnosticsButton.setkStartColor(setColor("BRIGHT"));
            diagnosticsButton.setkEndColor(setColor("DARK"));
        }
        else
        {
            diagnosticsButton.setkStartColor(setColor("MEDIUM"));
            diagnosticsButton.setkEndColor(setColor("DARK"));
        }
    }//GEN-LAST:event_diagnosticsButtonMouseExited

    private void chartsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chartsButtonMouseEntered

        chartsButton.setkStartColor(setColor("MEDIUM"));
        chartsButton.setkEndColor(setColor("DARK"));
        
        
    }//GEN-LAST:event_chartsButtonMouseEntered

    private void chartsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chartsButtonMouseExited
        if(state != CHARTS)
        {
            chartsButton.setkStartColor(setColor("BRIGHT"));
            chartsButton.setkEndColor(setColor("DARK"));
        }
        else
        {
            chartsButton.setkStartColor(setColor("MEDIUM"));
            chartsButton.setkEndColor(setColor("DARK"));
        }
    }//GEN-LAST:event_chartsButtonMouseExited

    private void clockButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clockButtonMouseEntered
        if(PROFILE_STATE == EXPERT_USER)
         {
            clockButton.setkEndColor(setColor("DARK"));
            clockButton.setkStartColor(setColor("MEDIUM"));
         }
         else
         {
            clockButton.setkEndColor(new Color(105,105,105));
            clockButton.setkStartColor(new Color(105,105,105));
         }
    }//GEN-LAST:event_clockButtonMouseEntered

    private void clockButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clockButtonMouseExited
        if(state != CLOCK)
        {
            if(PROFILE_STATE == EXPERT_USER)
            {
                clockButton.setkStartColor(setColor("BRIGHT"));
                clockButton.setkEndColor(setColor("DARK"));
            }
        }
        else
        {
            clockButton.setkStartColor(setColor("MEDIUM"));
            clockButton.setkEndColor(setColor("DARK"));
        }
    }//GEN-LAST:event_clockButtonMouseExited

    private void sendReportButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendReportButtonMouseClicked
        if(!reportOpened)
        {
        
            java.awt.EventQueue.invokeLater(new Runnable() {
                Report report = new Report();
                
                public void run() {
                    report.setHome(Home.this);
                    report.changeColorScheme(colorState);
                    reportOpened = true;
                    
                    if(settings != null)
                    {
                        settings.setReport(report);
                    }
                    
                    report.setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_sendReportButtonMouseClicked

    private void voltageListChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_voltageListChartsMouseClicked
        voltageListCharts.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JFreeChart chart = null;
                XYDataset ds;
                int row, col;

                    row = voltageListCharts.rowAtPoint(evt.getPoint());
                    col = voltageListCharts.columnAtPoint(evt.getPoint());
                    
                    ds = createDataset((String)voltageListCharts.getModel().getValueAt(row, col));
                    chartTitle = (String)voltageListCharts.getModel().getValueAt(row, col) + " (V)";
                    chartAttribute = "VOLTAGE";
                    
                    chart = ChartFactory.createXYLineChart((String)voltageListCharts.getModel().getValueAt(row, col)  + " (V)", "time", "voltage", ds, PlotOrientation.VERTICAL, true, true, false);

        
                    chartsBorder.setLayout(new java.awt.BorderLayout());
        
                    ChartPanel cp = new ChartPanel(chart);
                    cp.setPreferredSize(new Dimension(524, 379));
                    
                    XYPlot plot = chart.getXYPlot();
                    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
                    renderer.setSeriesPaint(0, Color.GREEN);
                    renderer.setSeriesStroke(0, new BasicStroke(2.0f));
                    
                    plot.setRenderer(renderer);
                    plot.setBackgroundPaint(Color.BLACK);
                    
                    plot.setRangeGridlinesVisible(true);
                    plot.setRangeGridlinePaint(Color.GREEN);
                    
                    plot.setDomainGridlinesVisible(true);
                    plot.setDomainGridlinePaint(Color.GREEN);

                    //cp.setSize(chartsBorder.getSize());

                    chartsBorder.removeAll();

                    chartsBorder.add(cp, BorderLayout.CENTER);
                    chartsBorder.repaint();
                    chartsBorder.revalidate();

            }
        });
    }//GEN-LAST:event_voltageListChartsMouseClicked

    private void tabsPaneChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabsPaneChartsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabsPaneChartsMouseClicked

    private void fanSpeedsListChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fanSpeedsListChartsMouseClicked
        fanSpeedsListCharts.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JFreeChart chart = null;
                XYDataset ds;
                int row, col;
                    row = fanSpeedsListCharts.rowAtPoint(evt.getPoint());
                    col = fanSpeedsListCharts.columnAtPoint(evt.getPoint());
                    
                    ds = createDataset((String)fanSpeedsListCharts.getModel().getValueAt(row, col));
                    chartTitle = (String)fanSpeedsListCharts.getModel().getValueAt(row, col) + " (RPM)";
                    chartAttribute = "FANSPEED";
                    chart = ChartFactory.createXYLineChart((String)fanSpeedsListCharts.getModel().getValueAt(row, col)  + " (RPM)", "time", "fan speed", ds, PlotOrientation.VERTICAL, true, true, false);
                    
        
                    chartsBorder.setLayout(new java.awt.BorderLayout());
        
                    ChartPanel cp = new ChartPanel(chart);
                    cp.setPreferredSize(new Dimension(524, 379));
                    
                    XYPlot plot = chart.getXYPlot();
                    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
                    renderer.setSeriesPaint(0, Color.GREEN);
                    renderer.setSeriesStroke(0, new BasicStroke(2.0f));
                    
                    plot.setRenderer(renderer);
                    plot.setBackgroundPaint(Color.BLACK);
                    
                    plot.setRangeGridlinesVisible(true);
                    plot.setRangeGridlinePaint(Color.GREEN);
                    
                    plot.setDomainGridlinesVisible(true);
                    plot.setDomainGridlinePaint(Color.GREEN);

                    //cp.setSize(chartsBorder.getSize());

                    chartsBorder.removeAll();

                    chartsBorder.add(cp, BorderLayout.CENTER);
                    chartsBorder.repaint();
                    chartsBorder.revalidate();

            }
        });
    }//GEN-LAST:event_fanSpeedsListChartsMouseClicked

    private void temperatureListChartsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_temperatureListChartsMouseClicked
        
            temperatureListCharts.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JFreeChart chart = null;
                XYDataset ds;
                int row, col;

                row = temperatureListCharts.rowAtPoint(evt.getPoint());
                col = temperatureListCharts.columnAtPoint(evt.getPoint());
                    
                ds = createDataset((String)temperatureListCharts.getModel().getValueAt(row, col));
                chartTitle = (String)temperatureListCharts.getModel().getValueAt(row, col) + " (C)";
                chartAttribute = "TEMPERATURE";
                    
                chart = ChartFactory.createXYLineChart((String)temperatureListCharts.getModel().getValueAt(row, col)  + " (C)", "time", "temperature", ds, PlotOrientation.VERTICAL, true, true, false);

        
                chartsBorder.setLayout(new java.awt.BorderLayout());
        
                ChartPanel cp = new ChartPanel(chart);
                    
                cp.setPreferredSize(new Dimension(524, 379));
                    
                XYPlot plot = chart.getXYPlot();
                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
                renderer.setSeriesPaint(0, Color.GREEN);
                renderer.setSeriesStroke(0, new BasicStroke(2.0f));
                    
                plot.setRenderer(renderer);
                plot.setBackgroundPaint(Color.BLACK);
                    
                plot.setRangeGridlinesVisible(true);
                plot.setRangeGridlinePaint(Color.GREEN);
                    
                plot.setDomainGridlinesVisible(true);
                plot.setDomainGridlinePaint(Color.GREEN);

                    //cp.setSize(chartsBorder.getSize());

                chartsBorder.removeAll();

                chartsBorder.add(cp, BorderLayout.CENTER);
                chartsBorder.repaint();
                chartsBorder.revalidate();
                 
            }
        });
                
    }//GEN-LAST:event_temperatureListChartsMouseClicked

    private void inDepthScanButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inDepthScanButtonMouseClicked
        JOptionPane.showMessageDialog(null, "You will need Internet connection to perform this. Will you proceed with the scan?");
    }//GEN-LAST:event_inDepthScanButtonMouseClicked

    private void extendedTestButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_extendedTestButtonMouseClicked
        statusTextField.setText("Scanning...");
        try{
            statusTextField.setText("Scanning...");
            
            Thread.sleep(1000);
            statusTextField.setText("Healthy");
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
            statusTextField.setText("");
        }
        
        
    }//GEN-LAST:event_extendedTestButtonMouseClicked

    private void shortTestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortTestButtonActionPerformed
        statusTextField.setText("Scanning...");
        try
        {
            statusTextField.setText("Scanning...");
            Thread.sleep(1000);
            statusTextField.setText("Healthy");
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
            statusTextField.setText("");
        }
    }//GEN-LAST:event_shortTestButtonActionPerformed

    private void startTimeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startTimeBoxActionPerformed
        XYDataset ds = createDataset(chartTitle);
       
        JFreeChart chart = null;
        
        if(chartAttribute.equals("TEMPERATURE")){
            chart = ChartFactory.createXYLineChart(chartTitle, "time", "temperature", ds, PlotOrientation.VERTICAL, true, true, false);
        }
        else if(chartAttribute.equals("VOLTAGE")){
            chart = ChartFactory.createXYLineChart(chartTitle, "time", "voltage", ds, PlotOrientation.VERTICAL, true, true, false);
        }
        else{
            chart = ChartFactory.createXYLineChart(chartTitle, "time", "fan speed", ds, PlotOrientation.VERTICAL, true, true, false);
        }
        
        chartsBorder.setLayout(new java.awt.BorderLayout());
        
        ChartPanel cp = new ChartPanel(chart);
                 
        cp.setPreferredSize(new Dimension(524, 379));      
        
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
                    
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.BLACK);
                    
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.GREEN);
                    
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.GREEN);

        //cp.setSize(chartsBorder.getSize());

        chartsBorder.removeAll();

        chartsBorder.add(cp, BorderLayout.CENTER);
        chartsBorder.repaint();
        chartsBorder.revalidate();
        

    }//GEN-LAST:event_startTimeBoxActionPerformed

    private void endTimeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endTimeBoxActionPerformed
        XYDataset ds = createDataset(chartTitle);
       
        JFreeChart chart = null;
        
        if(chartAttribute.equals("TEMPERATURE")){
            chart = ChartFactory.createXYLineChart(chartTitle, "time", "temperature", ds, PlotOrientation.VERTICAL, true, true, false);
        }
        else if(chartAttribute.equals("VOLTAGE")){
            chart = ChartFactory.createXYLineChart(chartTitle, "time", "voltage", ds, PlotOrientation.VERTICAL, true, true, false);
        }
        else{
            chart = ChartFactory.createXYLineChart(chartTitle, "time", "fan speed", ds, PlotOrientation.VERTICAL, true, true, false);
        }
        
        chartsBorder.setLayout(new java.awt.BorderLayout());
        
        ChartPanel cp = new ChartPanel(chart);
                    
        cp.setSize(524, 371);
            
        
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
                    
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.BLACK);
                    
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.GREEN);
                    
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.GREEN);

        //cp.setSize(chartsBorder.getSize());

        chartsBorder.removeAll();

        chartsBorder.add(cp, BorderLayout.CENTER);
        chartsBorder.repaint();
        chartsBorder.revalidate();
        
    }//GEN-LAST:event_endTimeBoxActionPerformed

    private void desiredSpinnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desiredSpinnerMouseClicked
        if(PROFILE_STATE == BEGINNER_USER){
            JOptionPane.showMessageDialog(null, "As a beginner user, you do not have the privileges to perform this action. To gain privileges, you must switch to Advanced or Expert mode in settings.");
        }
    }//GEN-LAST:event_desiredSpinnerMouseClicked

    private void warningSpinnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_warningSpinnerMouseClicked
        if(PROFILE_STATE != EXPERT_USER){
            JOptionPane.showMessageDialog(null, "As a beginner or advanced user, you do not have the privileges to perform this action. To gain privileges, you must switch to Expert mode in settings.");
        }
    }//GEN-LAST:event_warningSpinnerMouseClicked

    private void desiredSpinner7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desiredSpinner7MouseClicked
        if(PROFILE_STATE == BEGINNER_USER){
            JOptionPane.showMessageDialog(null, "As a beginner user, you do not have the privileges to perform this action. To gain privileges, you must switch to Advanced or Expert mode in settings.");
        }
    }//GEN-LAST:event_desiredSpinner7MouseClicked

    private void warningSpinner7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_warningSpinner7MouseClicked
        if(PROFILE_STATE != EXPERT_USER){
            JOptionPane.showMessageDialog(null, "As a beginner or advanced user, you do not have the privileges to perform this action. To gain privileges, you must switch to Expert mode in settings.");
        }
    }//GEN-LAST:event_warningSpinner7MouseClicked

    private void desiredSpinner5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desiredSpinner5MouseClicked
        if(PROFILE_STATE == BEGINNER_USER){
            JOptionPane.showMessageDialog(null, "As a beginner user, you do not have the privileges to perform this action. To gain privileges, you must switch to Advanced or Expert mode in settings.");
        }
    }//GEN-LAST:event_desiredSpinner5MouseClicked

    private void warningSpinner5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_warningSpinner5MouseClicked
        if(PROFILE_STATE != EXPERT_USER){
            JOptionPane.showMessageDialog(null, "As a beginner or advanced user, you do not have the privileges to perform this action. To gain privileges, you must switch to Expert mode in settings.");
        }
    }//GEN-LAST:event_warningSpinner5MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int row = jTable1.rowAtPoint(e.getPoint());
                CONFIGURE_TEMPERATURE_ROW = row;
                    
                if(PROFILE_STATE != BEGINNER_USER){
                    desiredSpinner.setEnabled(true);
                    desiredSpinner.setValue(jTable1.getValueAt(row, 6));
                }
                    
                if(PROFILE_STATE == EXPERT_USER){
                    warningSpinner.setEnabled(true);
                    warningSpinner.setValue(jTable1.getValueAt(row, 7));
                }
            }
        });
    }//GEN-LAST:event_jTable1MouseClicked

    private void desiredSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_desiredSpinnerStateChanged
        if((int)desiredSpinner.getValue() >= (int)jTable1.getValueAt(CONFIGURE_TEMPERATURE_ROW, 7))
        {
            JOptionPane.showMessageDialog(null, "Desired temperature can't exceed the warning temperature.", "Warning", JOptionPane.WARNING_MESSAGE);
            desiredSpinner.setValue((int)jTable1.getValueAt(CONFIGURE_TEMPERATURE_ROW, 7)-1);
            
        }
        else if((int)desiredSpinner.getValue() >= 80)
        {
            JOptionPane.showMessageDialog(null, "Warning! This is too hot!", "Warning!",JOptionPane.WARNING_MESSAGE);
            desiredSpinner.setValue(79);
        }
        else if((int)desiredSpinner.getValue() <= 0){
            JOptionPane.showMessageDialog(null, "Warning! This is too cold!", "Warning!",JOptionPane.WARNING_MESSAGE);
            desiredSpinner.setValue(1);
        }
        else
        {
            jTable1.setValueAt(desiredSpinner.getValue(), CONFIGURE_TEMPERATURE_ROW, 6);
        }
    }//GEN-LAST:event_desiredSpinnerStateChanged

    private void warningSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_warningSpinnerStateChanged
        if((int)desiredSpinner.getValue() >= (int)warningSpinner.getValue())
        {
            JOptionPane.showMessageDialog(null, "Desired temperature can't exceed the warning temperature.");
            warningSpinner.setValue((int)jTable1.getValueAt(CONFIGURE_TEMPERATURE_ROW, 6)+1);
            
        }
        else{
            jTable1.setValueAt(warningSpinner.getValue(), CONFIGURE_TEMPERATURE_ROW, 7);
        }
    }//GEN-LAST:event_warningSpinnerStateChanged

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        jTable4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int row = jTable4.rowAtPoint(e.getPoint());
                CONFIGURE_FAN_SPEED_ROW = row;
                    
                if(PROFILE_STATE != BEGINNER_USER){
                    desiredSpinner7.setEnabled(true);
                    desiredSpinner7.setValue(jTable4.getValueAt(row, 5));
                }
                    
                if(PROFILE_STATE == EXPERT_USER){
                    warningSpinner7.setEnabled(true);
                    warningSpinner7.setValue(jTable4.getValueAt(row, 6));
                }
            }
        });
    }//GEN-LAST:event_jTable4MouseClicked

    private void desiredSpinner7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_desiredSpinner7StateChanged
        if((int)desiredSpinner7.getValue() > (int)jTable4.getValueAt(CONFIGURE_FAN_SPEED_ROW, 6))
        {
            JOptionPane.showMessageDialog(null, "Desired fan speed % can't exceed the warning speed %.", "Warning", JOptionPane.WARNING_MESSAGE);
            desiredSpinner7.setValue((int)jTable4.getValueAt(CONFIGURE_FAN_SPEED_ROW, 6));
            
        }
        else if((int)desiredSpinner7.getValue() > 100)
        {
            JOptionPane.showMessageDialog(null, "You cannot exceed 100%!", "Warning!",JOptionPane.WARNING_MESSAGE);
            desiredSpinner7.setValue(100);
        }
        else if((int)desiredSpinner7.getValue() < 0){
            JOptionPane.showMessageDialog(null, "You cannot go below 0%!", "Warning!",JOptionPane.WARNING_MESSAGE);
            desiredSpinner7.setValue(0);
        }
        else
        {
            jTable4.setValueAt(desiredSpinner7.getValue(), CONFIGURE_FAN_SPEED_ROW, 5);
        }
    }//GEN-LAST:event_desiredSpinner7StateChanged

    private void warningSpinner7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_warningSpinner7StateChanged
        if((int)desiredSpinner7.getValue() > (int)jTable3.getValueAt(CONFIGURE_FAN_SPEED_ROW, 6))
        {
            JOptionPane.showMessageDialog(null, "Desired fan speed% can't exceed the warning fan speed%.");
            warningSpinner7.setValue((int)jTable4.getValueAt(CONFIGURE_FAN_SPEED_ROW, 5));
            
        }
        else{
            jTable4.setValueAt(warningSpinner7.getValue(), CONFIGURE_FAN_SPEED_ROW, 6);
        }
    }//GEN-LAST:event_warningSpinner7StateChanged

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        jTable3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int row = jTable3.rowAtPoint(e.getPoint());
                CONFIGURE_FAN_SPEED_ROW = row;
                    
                if(PROFILE_STATE != BEGINNER_USER){
                    desiredSpinner5.setEnabled(true);
                    desiredSpinner5.setValue(jTable3.getValueAt(row, 6));
                }
                    
                if(PROFILE_STATE == EXPERT_USER){
                    warningSpinner5.setEnabled(true);
                    warningSpinner5.setValue(jTable3.getValueAt(row, 7));
                }
            }
        });
    }//GEN-LAST:event_jTable3MouseClicked

    private void desiredSpinner5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_desiredSpinner5StateChanged
        if((int)desiredSpinner5.getValue() >= (int)jTable3.getValueAt(CONFIGURE_VOLTAGE_ROW, 7))
        {
            JOptionPane.showMessageDialog(null, "Desired voltage can't exceed the warning voltage.", "Warning", JOptionPane.WARNING_MESSAGE);
            desiredSpinner5.setValue((int)jTable3.getValueAt(CONFIGURE_VOLTAGE_ROW, 7)-1);
            
        }
        else if((int)desiredSpinner5.getValue() >= 100)
        {
            JOptionPane.showMessageDialog(null, "You cannot exceed 30V!", "Warning!",JOptionPane.WARNING_MESSAGE);
            desiredSpinner5.setValue(99);
        }
        else if((int)desiredSpinner5.getValue() <= 0){
            JOptionPane.showMessageDialog(null, "You cannot go below 0V!", "Warning!",JOptionPane.WARNING_MESSAGE);
            desiredSpinner5.setValue(1);
        }
        else
        {
            jTable3.setValueAt(desiredSpinner5.getValue(), CONFIGURE_VOLTAGE_ROW, 6);
        }
    }//GEN-LAST:event_desiredSpinner5StateChanged

    private void warningSpinner5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_warningSpinner5StateChanged
        if((int)desiredSpinner5.getValue() >= (int)jTable3.getValueAt(CONFIGURE_VOLTAGE_ROW, 7))
        {
            JOptionPane.showMessageDialog(null, "Desired voltage can't exceed the warning voltage.");
            warningSpinner5.setValue((int)jTable3.getValueAt(CONFIGURE_VOLTAGE_ROW, 6) +1);
            
        }
        else{
            jTable3.setValueAt(warningSpinner5.getValue(), CONFIGURE_VOLTAGE_ROW, 7);
        }
    }//GEN-LAST:event_warningSpinner5StateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        
    }//GEN-LAST:event_jTable2MouseClicked

    private void removeFanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFanButtonActionPerformed

        if(FAN_CONTROLLER_ROW >= 0 && FAN_CONTROLLER_ROW <= fanControllerModel.getSize()-1){
            fanControllerModel.removeElementAt(FAN_CONTROLLER_ROW);
        }
        
        if(fanControllerList.getModel().getSize() == 0){
            FAN_CONTROLLER_ROW = -1;
            
            fanAddButton.setEnabled(false);
            fanDeleteButton.setEnabled(false);
        }
    }//GEN-LAST:event_removeFanButtonActionPerformed

    private void addFanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFanButtonActionPerformed
        String fanController = null;
        fanController = JOptionPane.showInputDialog("Enter a name for this new controller");
        if(fanControllerModel == null){
            fanControllerModel = new DefaultListModel();
            fanControllerList.setModel(fanControllerModel);
        }
        
        if(fanController != null && !fanController.equals("")){
            fanControllerModel.addElement(fanController);
            
            
            
            
        }
    }//GEN-LAST:event_addFanButtonActionPerformed

    private void fanControllerListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fanControllerListMouseClicked
                
        fanControllerList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JList list = (JList)e.getSource();
                    int index = list.locationToIndex(e.getPoint());
                    //Do stuff
                    FAN_CONTROLLER_ROW = index; 
                    fanAddButton.setEnabled(true);
                    fanDeleteButton.setEnabled(true);
            }
        });
    }//GEN-LAST:event_fanControllerListMouseClicked

    private void fanAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fanAddButtonActionPerformed
        String[] options = {"CPU - System from SONY rfefddasc", "System - Temp2 from SONY asklhd", "System - Temp1 from SONY 13dwq", "System - Temp0 from SONY eqwasd"};
        String temp = null;
        temp = (String)JOptionPane.showInputDialog(null, "Choose:", "Choose", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        if(tempModel == null){
            tempModel = new DefaultListModel();
            jList1.setModel(tempModel);
        }
        
        
        if(temp != null && !temp.equals("")){
            tempModel.addElement(temp);
        }
    }//GEN-LAST:event_fanAddButtonActionPerformed

    private void fanDeleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fanDeleteButtonMouseClicked
        if(TEMP_LIST_ROW >= 0 && TEMP_LIST_ROW <= tempModel.getSize()-1){
            tempModel.removeElementAt(TEMP_LIST_ROW);
        }
        
        if(jList1.getModel().getSize() == 0){
            TEMP_LIST_ROW = -1;
            
        }
    }//GEN-LAST:event_fanDeleteButtonMouseClicked

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        jList1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                JList list = (JList)e.getSource();
                    int index = list.locationToIndex(e.getPoint());
                    //Do stuff
                   TEMP_LIST_ROW = index; 
                   
                   int row = jTable2.rowAtPoint(e.getPoint());

                    JFreeChart fanChart = null;
                    XYDataset fanDs= createFanDataset("");
        
                    fanChart = ChartFactory.createXYLineChart("", "Temp (C)", "%", fanDs, PlotOrientation.VERTICAL, true, true, false);
        
        
                    fanChartPanel.setLayout(new java.awt.BorderLayout());
        
                    ChartPanel fanCp = new ChartPanel(fanChart);
                    fanCp.setPreferredSize(new Dimension(300, 150));

                    
                    XYPlot fanPlot = fanChart.getXYPlot();
                    XYLineAndShapeRenderer fanRenderer = new XYLineAndShapeRenderer();
                    fanRenderer.setSeriesPaint(0, Color.RED);
                    fanRenderer.setSeriesStroke(0, new BasicStroke(1.0f));
                    
                    fanPlot.setRenderer(fanRenderer);
                    fanPlot.setBackgroundPaint(Color.WHITE);
                   
                    fanPlot.setRangeGridlinesVisible(true);
                    fanPlot.setRangeGridlinePaint(Color.BLACK);
                    
       
                    fanChartPanel.removeAll();
                    fanChartPanel.add(fanCp, BorderLayout.CENTER);
                    fanChartPanel.repaint();
                    fanChartPanel.revalidate();
            }
        });
    }//GEN-LAST:event_jList1MouseClicked

    private void temperatureTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_temperatureTabStateChanged
        JTabbedPane pane = (JTabbedPane)evt.getSource();
        int index = pane.getSelectedIndex();
        
        if(index == 0){
            if(firstTimeTemperatureTab){
                JOptionPane.showMessageDialog(null, "This tab is used to configure the hardware's temperatures");
                firstTimeTemperatureTab = false; 
                
            }
        }
        else if(index == 1){
            if(firstTimeFanTab){
                JOptionPane.showMessageDialog(null, "This tab is used to configure the hardware's fans");
                firstTimeFanTab = false; 
            }
            
        }
        else if(index == 2){
            if(firstTimeSpeedTab){
                JOptionPane.showMessageDialog(null, "This tab is used to configure the hardware's fan speeds");
                firstTimeSpeedTab = false; 
            }
        }
        else if(index == 3){
            if(firstTimeVoltageTab){
                JOptionPane.showMessageDialog(null, "This tab is used to configure the hardware's voltages");
                firstTimeVoltageTab = false; 
            }
        }
        else if(index == 4){
            if(firstTimeAdvancedFanTab){
                if(PROFILE_STATE == EXPERT_USER){
                    JOptionPane.showMessageDialog(null, "Warning! This uses powerful tools that can cotrol your fans. Use with caution!", "Warning", JOptionPane.WARNING_MESSAGE);
                    firstTimeAdvancedFanTab = false; 
                }
                else{
                    JOptionPane.showMessageDialog(null, "Warning! You do not have the privileges to access this! To gain access, you must set the program to expert mode in settings.", "Warning", JOptionPane.WARNING_MESSAGE);
                    temperatureTab.setSelectedIndex(0);
                }
                
            }
        }
    }//GEN-LAST:event_temperatureTabStateChanged

    
    private XYDataset createFanDataset(String series){
        DefaultXYDataset chartData = new DefaultXYDataset();
        Random rand = new Random();
        int x;
        
        int size = rand.nextInt(80)+20;
        double[][] data = new double[2][size];
        
        for(int i=0; i< size; i++){
            data[0][i] = i;
            x = rand.nextInt(100);
            data[1][i] = x;

        }
        
        chartData.addSeries(series, data);
        
        return chartData;
    }
    
    
    
    private XYDataset createDataset(String series){
        DefaultXYDataset chartData = new DefaultXYDataset();
        Random rand = new Random();
        int x;
        
        int size = rand.nextInt(15)+15;
        double[][] data = new double[2][size];
        
        for(int i=0; i< size; i++){
            data[0][i] = i;
            x = rand.nextInt(15) + 50;
            data[1][i] = x;

        }
        
        chartData.addSeries(series, data);
        
        return chartData;
    }
    
    
    
    
    
    
    
    
    
    
    private void resetButtonColor()
    {
        if(oldState == HOME)
        {
            homeButton.setkEndColor(setColor("DARK"));
            homeButton.setkStartColor(setColor("BRIGHT"));  
        }
        else if(oldState == CONFIGURE)
        {
            
            //if(PROFILE_STATE != BEGINNER_USER)
            //{
                configureButton.setkEndColor(setColor("DARK"));
                configureButton.setkStartColor(setColor("BRIGHT"));
            //}
            //else
            //{
                //configureButton.setkEndColor(new Color(105,105,105));
                //configureButton.setkStartColor(new Color(105,105,105));
            //}
        }
        else if(oldState == DEBUGGING)
        {
            debuggingButton.setkEndColor(setColor("DARK"));
            debuggingButton.setkStartColor(setColor("BRIGHT"));
        }
        else if(oldState == DIAGNOSTICS)
        {
            diagnosticsButton.setkEndColor(setColor("DARK"));
            diagnosticsButton.setkStartColor(setColor("BRIGHT"));
        }
        else if(oldState == CHARTS)
        {
            chartsButton.setkEndColor(setColor("DARK"));
            chartsButton.setkStartColor(setColor("BRIGHT"));
        }
        else if(oldState == CLOCK)
        {
            if(PROFILE_STATE == EXPERT_USER)
            {
                clockButton.setkEndColor(setColor("DARK"));
                clockButton.setkStartColor(setColor("BRIGHT"));
            }
            else
            {
                clockButton.setkEndColor(new Color(105,105,105));
                clockButton.setkStartColor(new Color(105,105,105));
            }
        }
        
        oldState = 100;
    }
    
    public void setColorScheme(int color)
    {
         colorState = color;
         
         homeButton.setkEndColor(setColor("DARK"));
         homeButton.setkStartColor(setColor("BRIGHT"));
         
         //if(PROFILE_STATE != BEGINNER_USER)
         //{
            configureButton.setkEndColor(setColor("DARK"));
            configureButton.setkStartColor(setColor("BRIGHT"));
         //}
         //else
         //{
            //configureButton.setkEndColor(new Color(105,105,105));
            //configureButton.setkStartColor(new Color(105,105,105));
         //}
         
         debuggingButton.setkEndColor(setColor("DARK"));
         debuggingButton.setkStartColor(setColor("BRIGHT"));
         
         diagnosticsButton.setkEndColor(setColor("DARK"));
         diagnosticsButton.setkStartColor(setColor("BRIGHT"));
         
         chartsButton.setkEndColor(setColor("DARK"));
         chartsButton.setkStartColor(setColor("BRIGHT"));
         
         if(PROFILE_STATE == EXPERT_USER)
         {
            clockButton.setkEndColor(setColor("DARK"));
            clockButton.setkStartColor(setColor("BRIGHT"));
         }
         else
         {
            clockButton.setkEndColor(new Color(105,105,105));
            clockButton.setkStartColor(new Color(105,105,105));
         }
         
         sideMenu.setBackground(setColor("BRIGHT"));
         
         readingBanner3.setkEndColor(setColor("BRIGHT"));
         readingBanner3.setkStartColor(setColor("MEDIUM"));
         
         readingBanner2.setkEndColor(setColor("BRIGHT"));
         readingBanner2.setkStartColor(setColor("MEDIUM"));
         
         readingBanner5.setkEndColor(setColor("BRIGHT"));
         readingBanner5.setkStartColor(setColor("MEDIUM"));
         
         readingBanner4.setkEndColor(setColor("BRIGHT"));
         readingBanner4.setkStartColor(setColor("MEDIUM"));
         
         tabsMenu.setBackground(setColor("DARK"));
         fanTab.setBackground(setColor("DARK"));
         voltageTab.setBackground(setColor("DARK"));
         speedTab.setBackground(setColor("DARK"));
         advancedFanControl.setBackground(setColor("DARK"));
         
         upperWindowDebugging.setBackground(setColor("BRIGHT"));
         dimInfoBorder.setBackground(setColor("BRIGHT"));
         
         upperWindowDebugging1.setBackground(setColor("BRIGHT"));
         dimInfoBorder1.setBackground(setColor("BRIGHT"));
         
         upperWindowCharts.setBackground(setColor("BRIGHT"));
         
         
         clockTab.setBackground(setColor("DARK"));
         
         if(helpOpened)
         {

             help.setColorState(colorState);
         }
         
    }
    
    private Color setColor(String prompt)
    {
        Color color = null;
        if(prompt.equals("MEDIUM"))
        {
            color = pickTone(1);
            
        }
        else if(prompt.equals("DARK"))
        {
            color = pickTone(0);
        }
        else if(prompt.equals("BRIGHT"))
        {
            color = pickTone(2);
        }
        else if(prompt.equals("OTHER"))
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
    
    public void setColorState(String color)
    {
        if(color.equals("RED"))
        {
            colorState = RED;
        }
        else if(color.equals("BLUE"))
        {
            colorState = BLUE;
        }
        else if(color.equals("GREEN"))
        {
            colorState = GREEN;
        }
        else if(color.equals("YELLOW"))
        {
            colorState = YELLOW;
        }
        else if(color.equals("ORANGE"))
        {
            colorState = ORANGE;
        }
        else if(color.equals("DARK"))
        {
            colorState = DARK;
        }
        else if(color.equals("PURPLE"))
        {
            colorState = PURPLE;
        }
    }        
    
    public void setProfile(int PROFILE_STATE)
    {
        this.PROFILE_STATE = PROFILE_STATE;
        returnToHome();
        
        firstTimeConfigure = true;
        
        if(PROFILE_STATE == BEGINNER_USER)
        {
            
            //configureButton.setkStartColor(new Color(105,105,105));
            //configureButton.setkEndColor(new Color(105,105,105));
            //configureButton.setEnabled(false);
            clockButton.setkStartColor(new Color(105,105,105));
            clockButton.setkEndColor(new Color(105,105,105));
            clockButton.setEnabled(false);
            
            
            
        }
        else if(PROFILE_STATE == ADVANCED_USER)
        {
            
            //configureButton.setkStartColor(setColor("BRIGHT"));
            //configureButton.setkEndColor(setColor("DARK"));
            //configureButton.setEnabled(true);
            clockButton.setkStartColor(new Color(105,105,105));
            clockButton.setkEndColor(new Color(105,105,105));
            clockButton.setEnabled(false);
            
            
        }
        else if(PROFILE_STATE == EXPERT_USER)
        {
            
            //configureButton.setkStartColor(setColor("BRIGHT"));
            //configureButton.setkEndColor(setColor("DARK"));
            //configureButton.setEnabled(true);
            clockButton.setkStartColor(setColor("BRIGHT"));
            clockButton.setkEndColor(setColor("DARK"));
            clockButton.setEnabled(true);
            
                        
        }
    }
    
    
    private void returnToHome()
    {
        pages.removeAll();
       
       //Add basic readings panel
       pages.add(basicReadings);
       pages.repaint();
       pages.revalidate();
       
       oldState = state;
       state = HOME;    
       resetButtonColor();
       
       homeButton.setkStartColor(setColor("MEDIUM"));
       homeButton.setkEndColor(setColor("DARK"));
    }
    
    public void setReport(Report report)
    {
        if(report != null)
        {
            if(!reportOpened)
            {
                this.report = report;
                reportOpened = true;
            }
        }
    
    }
    
    
    public void setReportClosed()
    {
        report = null;
        reportOpened = false;
    }
    
    public void setSettings(Settings settings)
    {
        if(settings != null)
        {
            if(!settingsOpened)
            {
                this.settings = settings;
                settingsOpened = true;
            }
        }
    }
    
    
    
    public void setHelp(Help help)
    {
        if(help != null)
        {
            if(!helpOpened)
            {
                helpOpened = true; 
                this.help = help;
            }
        }
    }
    
    public void setSettingsClosed()
    {
        settingsOpened = false;
        settings = null;
    }
    
    public void setHelpClosed()
    {
        helpOpened = false;
        help = null;
    }
    
    public void setPreferences(HashMap<String,String> prefs)
    {
        this.prefs = prefs;
    }
    
    public void setMailPreferences(HashMap<String,String> mailPrefs)
    {
        this.mailPrefs = mailPrefs;
    }
    
    public void setInternetPreferences(HashMap<String, String> internetPrefs){
        this.internetPrefs = internetPrefs;
    }
    
    public void setEventsPreferences(DefaultTableModel eventsModel){
        this.eventsModel = eventsModel;
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFanButton;
    private javax.swing.JPanel advancedFanControl;
    private javax.swing.JLabel afterRunning1;
    private javax.swing.JLabel afterRunning2;
    private javax.swing.JLabel afterRunning3;
    private javax.swing.JLabel afterRunning4;
    private javax.swing.JLabel automaticClockControlText;
    private javax.swing.JPanel background;
    private javax.swing.JPanel basicReadings;
    private javax.swing.JPanel charts;
    private javax.swing.JPanel chartsBorder;
    private keeptoo.KGradientPanel chartsButton;
    private javax.swing.JLabel chartsPic;
    private javax.swing.JLabel chartsText;
    private javax.swing.JLabel chipsetText;
    private keeptoo.KGradientPanel clockButton;
    private javax.swing.JCheckBox clockCheckBox;
    private javax.swing.JComboBox<String> clockComboBox;
    private javax.swing.JLabel clockPic;
    private javax.swing.JPanel clockTab;
    private javax.swing.JLabel clockText;
    private javax.swing.JLabel clockText1;
    private javax.swing.JComboBox<String> comboBoxDebugging;
    private javax.swing.JComboBox<String> comboBoxDiagnostics;
    private javax.swing.JTextField computedClockTextField;
    private javax.swing.JLabel computedText;
    private javax.swing.JPanel configure;
    private keeptoo.KGradientPanel configureButton;
    private javax.swing.JLabel configurePic;
    private javax.swing.JLabel configureText;
    private javax.swing.JSpinner cpuUsageSpinner;
    private javax.swing.JSpinner cpuUsageSpinner1;
    private javax.swing.JPanel debugging;
    private keeptoo.KGradientPanel debuggingButton;
    private javax.swing.JLabel debuggingIcon;
    private javax.swing.JLabel debuggingText;
    private javax.swing.JLabel desiredMeasure;
    private javax.swing.JLabel desiredMeasure5;
    private javax.swing.JLabel desiredMeasure7;
    private javax.swing.JSpinner desiredSpinner;
    private javax.swing.JSpinner desiredSpinner5;
    private javax.swing.JSpinner desiredSpinner7;
    private javax.swing.JLabel desiredText;
    private javax.swing.JLabel desiredText5;
    private javax.swing.JLabel desiredText7;
    private javax.swing.JPanel diagnostics;
    private keeptoo.KGradientPanel diagnosticsButton;
    private javax.swing.JLabel diagnosticsIcon;
    private javax.swing.JTable diagnosticsTable;
    private javax.swing.JLabel diagnosticsText;
    private javax.swing.JPanel dimInfoBorder;
    private javax.swing.JPanel dimInfoBorder1;
    private javax.swing.JTextArea dimTextArea;
    private javax.swing.JPanel dmBusSearchBorder;
    private javax.swing.JScrollPane dmbusDeviceTextBox;
    private javax.swing.JComboBox<String> endTimeBox;
    private javax.swing.JLabel endTimeText;
    private javax.swing.JButton extendedTestButton;
    private javax.swing.JButton fanAddButton;
    private javax.swing.JPanel fanChartPanel;
    private javax.swing.JPanel fanControlPanel;
    private javax.swing.JList<String> fanControllerList;
    private javax.swing.JButton fanDeleteButton;
    private javax.swing.JPanel fanSpeedsChartsPanel;
    private javax.swing.JTable fanSpeedsListCharts;
    private javax.swing.JPanel fanTab;
    private javax.swing.JLabel firmWareText;
    private javax.swing.JTextField firmwareTextField;
    private javax.swing.JProgressBar fitnessBar;
    private javax.swing.JLabel fitnessPercentage;
    private javax.swing.JLabel fitnessPercentage1;
    private javax.swing.JLabel fitnessText;
    private javax.swing.JLabel hardDiskText;
    private javax.swing.JLabel helpButton;
    private keeptoo.KGradientPanel homeButton;
    private javax.swing.JLabel homePic;
    private javax.swing.JLabel homeText;
    private javax.swing.JButton inDepthScanButton;
    private javax.swing.JCheckBox ioCheckBox;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextArea jTextArea1;
    private java.awt.Label label1;
    private javax.swing.JLabel lowPowerPReferredText;
    private javax.swing.JLabel modelText;
    private javax.swing.JTextField modelTextField;
    private javax.swing.JLabel motherBoardText;
    private javax.swing.JComboBox<String> motherboard1ComboBox;
    private javax.swing.JComboBox<String> motherboard2ComboBox;
    private javax.swing.JTextArea noteBox;
    private javax.swing.JPanel pages;
    private javax.swing.JProgressBar performanceBar;
    private javax.swing.JLabel performanceText;
    private javax.swing.JButton readClockButton;
    private javax.swing.JButton readInfoButton;
    private keeptoo.KGradientPanel readingBanner2;
    private keeptoo.KGradientPanel readingBanner3;
    private keeptoo.KGradientPanel readingBanner4;
    private keeptoo.KGradientPanel readingBanner5;
    private javax.swing.JTable readingTable3;
    private javax.swing.JTable readingTable6;
    private javax.swing.JTable readingTable7;
    private javax.swing.JTable readingTable8;
    private javax.swing.JButton removeFanButton;
    private javax.swing.JTextField revisionTextBox;
    private javax.swing.JSpinner runningBelowSpinner;
    private javax.swing.JSpinner runningBelowSpinner1;
    private javax.swing.JButton scanDMBusButton;
    private javax.swing.JButton sendReportButton;
    private javax.swing.JButton setClockButton;
    private javax.swing.JLabel setToText1;
    private javax.swing.JLabel setToText2;
    private javax.swing.JLabel settingsButton;
    private javax.swing.JButton shortTestButton;
    private javax.swing.JPanel sideMenu;
    private javax.swing.JTextArea smBusScanTextArea;
    private javax.swing.JLabel smbusaddressText;
    private javax.swing.JLabel smbusaddressText1;
    private javax.swing.JTextField smbusaddressTextField;
    private javax.swing.JPanel speedTab;
    private javax.swing.JComboBox<String> startTimeBox;
    private javax.swing.JLabel startTimeText;
    private javax.swing.JLabel statusText;
    private javax.swing.JTextField statusTextField;
    private javax.swing.JTextField statusTextField2;
    private javax.swing.JPanel tabsMenu;
    private javax.swing.JTabbedPane tabsPaneCharts;
    private javax.swing.JPanel tempTab;
    private javax.swing.JPanel temperatureChartsPanel;
    private javax.swing.JTable temperatureListCharts;
    private javax.swing.JTabbedPane temperatureTab;
    private java.awt.TextArea textArea1;
    private javax.swing.JPanel toolBar;
    private javax.swing.JPanel upperWindowCharts;
    private javax.swing.JPanel upperWindowDebugging;
    private javax.swing.JPanel upperWindowDebugging1;
    private javax.swing.JPanel voltageChartsPanel;
    private javax.swing.JScrollPane voltageChartsTable;
    private javax.swing.JScrollPane voltageChartsTable1;
    private javax.swing.JScrollPane voltageChartsTable2;
    private javax.swing.JTable voltageListCharts;
    private javax.swing.JPanel voltageTab;
    private javax.swing.JLabel warningMeasure;
    private javax.swing.JLabel warningMeasure5;
    private javax.swing.JLabel warningMeasure7;
    private javax.swing.JSpinner warningSpinner;
    private javax.swing.JSpinner warningSpinner5;
    private javax.swing.JSpinner warningSpinner7;
    private javax.swing.JLabel warningText;
    private javax.swing.JLabel warningText5;
    private javax.swing.JLabel warningText7;
    // End of variables declaration//GEN-END:variables
}
