package GrainGrowth;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 * 
 * @author marcinkrzyzowski
 */
public class MainInterface extends javax.swing.JFrame {

    static int width = Constants.boardWidth;
    
    static int height = Constants.boardHeight;
    
    private HandlerClass handler = new HandlerClass();
    
    private Thread thread;
    
    private Grain boardGrain[][];
    
    private boolean isInSimulationLoop;
    
    private MainGrainBoard board;
    
    private boolean isMonteCarloEnabled;
  
    private MonteCarloWorker monteCarloWorker;
    
    private int currentIteration;
    
    private int currentSeeds;
    
    private int sizeX;
    
    private int sizeY;
    
    private boolean shouldSelectGrains = false;
    
    ArrayList<Integer> selectedGrainList = new ArrayList();
        public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainInterface().setVisible(true);
            }
        });
    }

    /// Initializes view with all properties    
    public MainInterface() {
        currentIteration = 0;
        monteCarloWorker = new MonteCarloWorker();
        isMonteCarloEnabled = false;
        board = new MainGrainBoard(width, height);
        boardGrain = new Grain[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boardGrain[i][j] = new Grain();
            }
        }
        initComponents();
        jLabel12.setText("Iteration: " + currentIteration);
    
        ConditionsComboBox.setVisible(false);
 
        jLabel9.setText("" + board.getCountGrainsCristal());
        mainCanvas.addMouseListener(handler);                  
        mainCanvas.addMouseMotionListener(handler); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        seedsCounterView = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        grainInformationView = new javax.swing.JPanel();
        idLabel = new javax.swing.JLabel();
        colorValueLabel = new javax.swing.JLabel();
        colorPanel = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        ConditionsComboBox = new javax.swing.JComboBox();
        NeighborhoodComboBox = new javax.swing.JComboBox();
        GenerateButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        NeighborhoodLabel = new javax.swing.JLabel();
        mainCanvas = new GrainGrowth.Canvas();
        jLabel3 = new javax.swing.JLabel();
        randomNucleonAmountText = new javax.swing.JTextField();
        amountOfInclusionsLabel = new javax.swing.JLabel();
        sizeOfInclusionsLabel = new javax.swing.JLabel();
        inclusionsShapeLabel = new javax.swing.JLabel();
        inclusionsAmountInput = new javax.swing.JTextField();
        inclusionsSizeInput = new javax.swing.JTextField();
        inclusionShapeComboBox = new javax.swing.JComboBox<>();
        addInclusionsButton = new javax.swing.JButton();
        PropabilityInput = new javax.swing.JTextField();
        probabilityOfGrowthLabel = new javax.swing.JLabel();
        selectionTypeBox = new javax.swing.JComboBox<>();
        procedGrainSelection = new javax.swing.JButton();
        countXText = new javax.swing.JTextField();
        countYText = new javax.swing.JTextField();
        xLabelSize = new javax.swing.JLabel();
        YLabelSize = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ListGrains = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        ImportMenu = new javax.swing.JMenu();
        FromBitmapImport = new javax.swing.JMenuItem();
        FromTextFileImport = new javax.swing.JMenuItem();
        ExportMenu = new javax.swing.JMenu();
        ToBitmapExport = new javax.swing.JMenuItem();
        ToTextFileExport = new javax.swing.JMenuItem();
        showGrainsBorders = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1340, 680));
        setPreferredSize(new java.awt.Dimension(1340, 680));
        setSize(new java.awt.Dimension(1340, 680));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        mainPanel.setMaximumSize(new java.awt.Dimension(1340, 660));
        mainPanel.setMinimumSize(new java.awt.Dimension(1340, 660));
        mainPanel.setPreferredSize(new java.awt.Dimension(1340, 660));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seedsCounterView.setLayout(new java.awt.GridBagLayout());

        jLabel9.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        seedsCounterView.add(jLabel9, gridBagConstraints);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSeparator2.setMinimumSize(new java.awt.Dimension(1, 20));
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        seedsCounterView.add(jSeparator2, gridBagConstraints);

        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSeparator1.setMinimumSize(new java.awt.Dimension(1, 20));
        jSeparator1.setPreferredSize(new java.awt.Dimension(1, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        seedsCounterView.add(jSeparator1, gridBagConstraints);

        mainPanel.add(seedsCounterView, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, -1, -1));

        jPanel2.setLayout(new java.awt.GridBagLayout());
        mainPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.setLayout(new java.awt.GridBagLayout());
        mainPanel.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 560, -1, -1));

        jPanel5.setLayout(new java.awt.GridBagLayout());
        mainPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 400, -1, -1));

        grainInformationView.setLayout(new java.awt.GridBagLayout());

        idLabel.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        grainInformationView.add(idLabel, gridBagConstraints);

        colorValueLabel.setFont(new java.awt.Font("Dialog", 0, 9)); // NOI18N
        colorValueLabel.setText("(0,0,0)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        grainInformationView.add(colorValueLabel, gridBagConstraints);

        colorPanel.setMinimumSize(new java.awt.Dimension(70, 25));
        colorPanel.setPreferredSize(new java.awt.Dimension(70, 25));
        colorPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        grainInformationView.add(colorPanel, gridBagConstraints);

        mainPanel.add(grainInformationView, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 100, 80));

        startButton.setText("Simulation");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        mainPanel.add(startButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 20, 130, 80));

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        mainPanel.add(stopButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, 120, 80));

        ConditionsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Losowe", "Stała odległość", "Stała ilość ziaren" }));
        ConditionsComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                ConditionsComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        mainPanel.add(ConditionsComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 580, 310, 30));

        NeighborhoodComboBox.setMaximumRowCount(3);
        NeighborhoodComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Moore", "von Neumann'a", "Extended Moore" }));
        NeighborhoodComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                NeighborhoodComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        NeighborhoodComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NeighborhoodComboBoxActionPerformed(evt);
            }
        });
        mainPanel.add(NeighborhoodComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 200, 30));

        GenerateButton.setText("Generate");
        GenerateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateButtonActionPerformed(evt);
            }
        });
        mainPanel.add(GenerateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 120, 80));

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        mainPanel.add(clearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 110, 130, 80));

        NeighborhoodLabel.setText("Neighborhood");
        mainPanel.add(NeighborhoodLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, -1, -1));

        mainCanvas.setMinimumSize(new java.awt.Dimension(750, 460));
        mainPanel.add(mainCanvas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 500, 400));

        jLabel3.setText("Nucleon amount");
        mainPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 110, 20));

        randomNucleonAmountText.setText("10");
        randomNucleonAmountText.setMinimumSize(new java.awt.Dimension(40, 22));
        randomNucleonAmountText.setPreferredSize(new java.awt.Dimension(40, 22));
        randomNucleonAmountText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomNucleonAmountTextActionPerformed(evt);
            }
        });
        mainPanel.add(randomNucleonAmountText, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 80, 30));

        amountOfInclusionsLabel.setText("Amount of inclusions ");
        mainPanel.add(amountOfInclusionsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, -1, -1));

        sizeOfInclusionsLabel.setText("Size of inclusions");
        mainPanel.add(sizeOfInclusionsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, -1, -1));

        inclusionsShapeLabel.setText("Inclusions shape");
        mainPanel.add(inclusionsShapeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, -1, -1));

        inclusionsAmountInput.setText("10");
        inclusionsAmountInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inclusionsAmountInputActionPerformed(evt);
            }
        });
        mainPanel.add(inclusionsAmountInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 240, 180, 30));

        inclusionsSizeInput.setText("5");
        inclusionsSizeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inclusionsSizeInputActionPerformed(evt);
            }
        });
        mainPanel.add(inclusionsSizeInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, 120, -1));

        inclusionShapeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "square", "circular" }));
        inclusionShapeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inclusionShapeComboBoxActionPerformed(evt);
            }
        });
        mainPanel.add(inclusionShapeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 350, 170, 30));

        addInclusionsButton.setText("Add Inclusions");
        addInclusionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInclusionsButtonActionPerformed(evt);
            }
        });
        mainPanel.add(addInclusionsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, 170, 40));

        PropabilityInput.setText("90");
        PropabilityInput.setCaretPosition(1);
        PropabilityInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PropabilityInputActionPerformed(evt);
            }
        });
        mainPanel.add(PropabilityInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 240, 120, -1));

        probabilityOfGrowthLabel.setText("Probability of growth");
        mainPanel.add(probabilityOfGrowthLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 210, 140, 20));

        selectionTypeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selection DualPhase", "Selection Substructure", "Get Boundaries" }));
        selectionTypeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectionTypeBoxActionPerformed(evt);
            }
        });
        mainPanel.add(selectionTypeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 530, 180, 30));

        procedGrainSelection.setText("Proceed with selected Grains");
        procedGrainSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procedGrainSelectionActionPerformed(evt);
            }
        });
        mainPanel.add(procedGrainSelection, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 560, 220, 40));

        countXText.setText("10");
        countXText.setMinimumSize(new java.awt.Dimension(40, 22));
        countXText.setPreferredSize(new java.awt.Dimension(40, 22));
        countXText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countXTextActionPerformed(evt);
            }
        });
        mainPanel.add(countXText, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 60, 30));

        countYText.setText("10");
        countYText.setMinimumSize(new java.awt.Dimension(40, 22));
        countYText.setPreferredSize(new java.awt.Dimension(40, 22));
        mainPanel.add(countYText, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, 60, 30));

        xLabelSize.setText("xSize:");
        mainPanel.add(xLabelSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, -1, 20));

        YLabelSize.setText("ySize:");
        mainPanel.add(YLabelSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, -1, -1));

        jCheckBox1.setText("Set size");
        jCheckBox1.setContentAreaFilled(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        mainPanel.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, -1, -1));

        jLabel12.setText("Iteration: 0");
        mainPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 500, -1, -1));

        jLabel8.setText("Seeds count:");
        mainPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, -1, -1));

        ListGrains.setText("Selected grains:");
        mainPanel.add(ListGrains, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, 260, 30));

        getContentPane().add(mainPanel, new java.awt.GridBagConstraints());

        ImportMenu.setText("Import");
        ImportMenu.setToolTipText("");

        FromBitmapImport.setText("From Bitmap");
        FromBitmapImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromBitmapImportActionPerformed(evt);
            }
        });
        ImportMenu.add(FromBitmapImport);

        FromTextFileImport.setText("From TextFile");
        FromTextFileImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromTextFileImportActionPerformed(evt);
            }
        });
        ImportMenu.add(FromTextFileImport);

        jMenuBar1.add(ImportMenu);

        ExportMenu.setText("Export");

        ToBitmapExport.setText("To bitmap");
        ToBitmapExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToBitmapExportActionPerformed(evt);
            }
        });
        ExportMenu.add(ToBitmapExport);

        ToTextFileExport.setText("To text file");
        ToTextFileExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToTextFileExportActionPerformed(evt);
            }
        });
        ExportMenu.add(ToTextFileExport);

        showGrainsBorders.setSelected(true);
        showGrainsBorders.setText("Show grains boarders");
        ExportMenu.add(showGrainsBorders);

        jMenuBar1.add(ExportMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private BufferedImage map( int sizeX, int sizeY, boolean showBoundary){
    
        final BufferedImage res = new BufferedImage( sizeX, sizeY, BufferedImage.TYPE_INT_RGB );
            for(int j=0;j<height;j++){
            for(int i=0;i<width;i++){
                if(boardGrain[i][j].getId() == 0 || (boardGrain[i][j].isBoundary()) && showBoundary ){
                    res.setRGB(i,j, Color.BLACK.getRGB() );
                }else{
                    int R = boardGrain[i][j].getRColorParameter();
                    int G = boardGrain[i][j].getGColorParameter();
                    int B = boardGrain[i][j].getBColorParameter();
                    int grain = boardGrain[i][j].getId();
              
                    res.setRGB(i,j, new Color(R,G,B).getRGB() );
                }
            } 
        }
        return res;
    }
    
    private static void saveBMPImage( final BufferedImage bi, final String path ){
        try {
            RenderedImage rendImage = bi;
            ImageIO.write(rendImage, "bmp", new File(path));
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    private void ToBitmapExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToBitmapExportActionPerformed
        BufferedImage img = map(width, height, showGrainsBorders.isSelected());
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify file that should be saved");   
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            saveBMPImage( img, fileToSave.getAbsolutePath() + ".bmp" );
        }
    }//GEN-LAST:event_ToBitmapExportActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        currentIteration = 0;   
        currentSeeds = 0;
        jLabel12.setText("Iteration: " + currentIteration);
        boardGrain = board.clear();                                     
        mainCanvas.setGrains(boardGrain);
        mainCanvas.repaint();
        jLabel9.setText("" + currentSeeds);
    }//GEN-LAST:event_clearButtonActionPerformed

    private void GenerateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateButtonActionPerformed
        if (jCheckBox1.isSelected()) {
   
            width = Integer.parseInt(countXText.getText());

            height = Integer.parseInt(countYText.getText());

            mainCanvas.resizeBoard(Integer.parseInt(countXText.getText()), Integer.parseInt(countYText.getText()));
            mainCanvas.setPreferredSize(new java.awt.Dimension(Integer.parseInt(countXText.getText()), Integer.parseInt(countYText.getText())));
            mainCanvas.setSize(Integer.parseInt(countXText.getText()), Integer.parseInt(countYText.getText()));
            mainCanvas.setMaximumSize(new java.awt.Dimension(Integer.parseInt(countXText.getText()), Integer.parseInt(countYText.getText())));
            mainCanvas.setMinimumSize(new java.awt.Dimension(Integer.parseInt(countXText.getText()), Integer.parseInt(countYText.getText())));
        
    }
            boardGrain = board.randomBoard(ConditionsComboBox.getSelectedIndex(),  
                5,              
                Integer.parseInt(randomNucleonAmountText.getText()));          
            mainCanvas.setGrains(boardGrain);                                  
            mainCanvas.repaint();                                              
            jLabel9.setText("" + board.getCountGrainsCristal());
        
    }//GEN-LAST:event_GenerateButtonActionPerformed

    private void NeighborhoodComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NeighborhoodComboBoxActionPerformed

    }//GEN-LAST:event_NeighborhoodComboBoxActionPerformed

    private void NeighborhoodComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_NeighborhoodComboBoxPopupMenuWillBecomeInvisible

        
    }//GEN-LAST:event_NeighborhoodComboBoxPopupMenuWillBecomeInvisible

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        if (thread != null) {                          // zatrzymujemy symulacje
            thread.stop();
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        currentIteration = 0;
        jLabel12.setText("Iteration: " + currentIteration);
        if (thread != null) {   
            thread.stop();
        }
        thread = new Thread(new Runnable() { 
            @Override
            public void run() {
                startSimulation();
            }
        });
        thread.start();
    }//GEN-LAST:event_startButtonActionPerformed
    
    private void FromBitmapImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromBitmapImportActionPerformed
        try {
            HashSet<Color> treeSet = new HashSet<>();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file that should be saved");   
            int userSelection = fileChooser.showSaveDialog(this);
           
            File bmpFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
            BufferedImage image = ImageIO.read(bmpFile);
            if(image.getHeight()== height && image.getWidth() == height);
            {
                for(int y=0;y<height;y++){
                    for(int x=0;x<width;x++){
                        int clr=  image.getRGB(x,y); 
                        int  red   = (clr & 0x00ff0000) >> 16;
                        int  green = (clr & 0x0000ff00) >> 8;
                        int  blue  =  clr & 0x000000ff;
                        boardGrain[x][y].setRGB(red, green, blue);
                        if( red == 255 && blue == 255 && green == 255) {
                            boardGrain[x][y].setId(-1);
                            continue;
                        }
                        treeSet.add(new Color(red,green,blue));
                    }
                }
            }
            
            HashMap<Color, Integer> colorMapping = new HashMap<Color, Integer>();
            int uniq_id = 1;
            Color white = new Color(255,255,255);
            for(Color cl : treeSet)
            {
                if(cl == white) {
                    colorMapping.put(cl, -1);
                }
                else {
                    colorMapping.put(cl, uniq_id);
                    uniq_id++;
                }
            }
            for(int y=0;y<height;y++){
                for(int x=0;x<width;x++){
                    if (boardGrain[x][y].getId() != -1)
                        boardGrain[x][y].setId(colorMapping.get(new Color(boardGrain[x][y].getRColorParameter(),boardGrain[x][y].getGColorParameter(),boardGrain[x][y].getBColorParameter()))); 
                    }
            }
            boardGrain = board.provideEdgeGrains();
            mainCanvas.setGrains(boardGrain);
            mainCanvas.repaint();
        } catch (IOException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_FromBitmapImportActionPerformed

    private void ToTextFileExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToTextFileExportActionPerformed
        PrintWriter writer = null;
        try {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file that should be saved");   
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            writer = new PrintWriter(fileToOpen, "UTF-8");
            
            for(int y=0;y<height;y++){    
                for(int x=0;x<width;x++){
                    writer.println(boardGrain[x][y].getId() + "," 
                            + x + "," + y+ ","
                            + boardGrain[x][y].getRColorParameter() + "," 
                            + boardGrain[x][y].getGColorParameter() + "," 
                            + boardGrain[x][y].getBColorParameter());
                }
            }
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }//GEN-LAST:event_ToTextFileExportActionPerformed

    private void FromTextFileImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromTextFileImportActionPerformed

            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file that should be saved");            
                int userSelection = fileChooser.showSaveDialog(this);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();
                BufferedReader in = new BufferedReader(new FileReader(fileToOpen));
                while(true){
                    String line = in.readLine();
                    if(line == null ) break;
                    String[] propperties = line.split(",");
                    int id = Integer.parseInt(propperties[0]);
                    int x = Integer.parseInt(propperties[1]);		
                    int y = Integer.parseInt(propperties[2]);
                    int R = Integer.parseInt(propperties[3]);
                    int G = Integer.parseInt(propperties[4]);
                    int B = Integer.parseInt(propperties[5]);
                    boardGrain[x][y].setId(id);
                    boardGrain[x][y].setRGB(R,G,B); 
                }          
            }
        } catch (HeadlessException headlessException) {
        } catch (FileNotFoundException fileNotFoundException) {
        } catch (IOException ex) {
                    Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            boardGrain = board.provideEdgeGrains();
            mainCanvas.setGrains(boardGrain);
            mainCanvas.repaint();
    }//GEN-LAST:event_FromTextFileImportActionPerformed

    private void inclusionsSizeInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inclusionsSizeInputActionPerformed

    }//GEN-LAST:event_inclusionsSizeInputActionPerformed

    private void inclusionShapeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inclusionShapeComboBoxActionPerformed

    }//GEN-LAST:event_inclusionShapeComboBoxActionPerformed

    private void addInclusionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInclusionsButtonActionPerformed
        int size = Integer.parseInt(inclusionsSizeInput.getText());
        int amount = Integer.parseInt(inclusionsAmountInput.getText());
        int shape = inclusionShapeComboBox.getSelectedIndex();
        int x0 = 0;
        int y0 = 0;
        Random rand = new Random();
       
        ArrayList<Grain> grainList = board.getGrainsOnBorder();

        for(int attempt = 0; attempt< amount; attempt++)
        {
            /**checking if there are grain edge*/
            if(board.isEdgeGenerated()){
                int randomGrainIndex = rand.nextInt(grainList.size());
                Grain randomGrain = grainList.get(randomGrainIndex);  
                x0 = randomGrain.getX();
                y0 = randomGrain.getY();
            }
            /** if no generate rand points inclusion*/
            else{
                x0 = rand.nextInt(width);
                y0 = rand.nextInt(height);
            }
            /** shape is squere*/
            if (shape == 0)
            {
                int a = (int)((size/1.44)/2);    
                for(int stepX = -a; stepX <a; stepX++)
                {
                    for(int stepY = -a; stepY <a; stepY++)
                    {
                        if(x0+stepX < width && x0+stepX > 0 && y0+stepY < height && y0+stepY >0 )
                        {
                            boardGrain[x0+stepX][y0+stepY].setId(-1);
                            boardGrain[x0+stepX][y0+stepY].setRGB(255,255,255);
                        }
                    }
                }
            }
            /** shape is circular*/
            else {
                for(int y=-size; y<=size; y++)
                    for(int x=-size; x<=size; x++)
                        if(x*x+y*y <= size*size && x0+x < width && x0+x>0 && y0+y < height && y0+y>0)
                            boardGrain[x0+x][y0+y].setId(-1);
            }       
        }
        boardGrain = board.provideEdgeGrains();
        mainCanvas.setGrains(boardGrain);
        mainCanvas.repaint();
        
    }//GEN-LAST:event_addInclusionsButtonActionPerformed

    private void procedGrainSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procedGrainSelectionActionPerformed

            if (this.selectionTypeBox.getSelectedIndex() == 1 || this.selectionTypeBox.getSelectedIndex() == 0 )
                boardGrain = board.removeAllGrainsExceptSelected(selectedGrainList);
               ListGrains.setText("Selected grains: " + selectedGrainList);

            if (this.selectionTypeBox.getSelectedIndex() == 1) 
            {
                boardGrain = board.dualPhaseIdChange();
                selectedGrainList.clear();
            }

            if ( this.selectionTypeBox.getSelectedIndex() == 2)
            {
                boardGrain = board.growBoundaries(2, selectedGrainList);
            }
        
       mainCanvas.setGrains(boardGrain);
       mainCanvas.repaint();
    }//GEN-LAST:event_procedGrainSelectionActionPerformed

    private void inclusionsAmountInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inclusionsAmountInputActionPerformed

    }//GEN-LAST:event_inclusionsAmountInputActionPerformed

    private void PropabilityInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PropabilityInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PropabilityInputActionPerformed

    private void countXTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countXTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countXTextActionPerformed

    private void randomNucleonAmountTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomNucleonAmountTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_randomNucleonAmountTextActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void ConditionsComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_ConditionsComboBoxPopupMenuWillBecomeInvisible

    }//GEN-LAST:event_ConditionsComboBoxPopupMenuWillBecomeInvisible

    private void selectionTypeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectionTypeBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectionTypeBoxActionPerformed

    private void startSimulation() {

            isInSimulationLoop = true;
            while (isInSimulationLoop) {
                currentIteration++;
                jLabel12.setText("Iteration: " + currentIteration);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                board.setGrainsToSkip(selectedGrainList);
                boardGrain = board.calculate(NeighborhoodComboBox.getSelectedIndex(),
                        5, 
                        Integer.parseInt(PropabilityInput.getText()));
                
                isInSimulationLoop = board.ammountOfNotEmptyCells() != (width * height);
                mainCanvas.setGrains(boardGrain);
                mainCanvas.repaint();

                if (!isInSimulationLoop) {
                    boardGrain = board.provideEdgeGrains();
                    jLabel9.setText("" + board.getCountGrainsCristal());
                    thread.stop();
                }
               
        }

    }

    public class HandlerClass implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
                addGrainToListOnMouseClick(e.getX(), e.getY());
        }

        int xPres, yPres;

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            userDidMoveMouse(e.getX(), e.getY());
        }

    }

    private void userDidMoveMouse(int i, int j) {

        int R = boardGrain[i][j].getRColorParameter();
        int G = boardGrain[i][j].getGColorParameter();
        int B = boardGrain[i][j].getBColorParameter();
        int grain = boardGrain[i][j].getId();
        idLabel.setText("" + boardGrain[i][j].getId());
        colorValueLabel.setText("(" + R + "," + G + "," + B + ")" + "     " + boardGrain[i][j].isBoundary());
        colorPanel.setBackground(new Color(R, G, B));
    }

    private void userDidClickLeftMouseButton(int x, int y) {
        int xPoint = (int) Math.floor(x / (Constants.boardWidth / width));
        int yPoint = (int) Math.floor(y / (Constants.boardHeight / height));
        boardGrain = board.addGrain(xPoint, yPoint);
        mainCanvas.setGrains(boardGrain);
        mainCanvas.repaint();
        jLabel9.setText("" + board.getCountGrainsCristal());
    }
    
    private void addGrainToListOnMouseClick(int x, int y) {
        if (selectedGrainList.contains(boardGrain[x][y].getId())) {
             selectedGrainList.remove(selectedGrainList.indexOf(boardGrain[x][y].getId()));
            ListGrains.setText("Selected grains list: " + selectedGrainList);
        }
        else {
            selectedGrainList.add(boardGrain[x][y].getId());
            System.err.println(selectedGrainList);
            ListGrains.setText("Selected grains: " + selectedGrainList);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ConditionsComboBox;
    private javax.swing.JMenu ExportMenu;
    private javax.swing.JMenuItem FromBitmapImport;
    private javax.swing.JMenuItem FromTextFileImport;
    private javax.swing.JButton GenerateButton;
    private javax.swing.JMenu ImportMenu;
    private javax.swing.JLabel ListGrains;
    private javax.swing.JComboBox NeighborhoodComboBox;
    private javax.swing.JLabel NeighborhoodLabel;
    private javax.swing.JTextField PropabilityInput;
    private javax.swing.JMenuItem ToBitmapExport;
    private javax.swing.JMenuItem ToTextFileExport;
    private javax.swing.JLabel YLabelSize;
    private javax.swing.JButton addInclusionsButton;
    private javax.swing.JLabel amountOfInclusionsLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JLabel colorValueLabel;
    private javax.swing.JTextField countXText;
    private javax.swing.JTextField countYText;
    private javax.swing.JPanel grainInformationView;
    private javax.swing.JLabel idLabel;
    private javax.swing.JComboBox<String> inclusionShapeComboBox;
    private javax.swing.JTextField inclusionsAmountInput;
    private javax.swing.JLabel inclusionsShapeLabel;
    private javax.swing.JTextField inclusionsSizeInput;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private GrainGrowth.Canvas mainCanvas;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel probabilityOfGrowthLabel;
    private javax.swing.JButton procedGrainSelection;
    private javax.swing.JTextField randomNucleonAmountText;
    private javax.swing.JPanel seedsCounterView;
    private javax.swing.JComboBox<String> selectionTypeBox;
    private javax.swing.JRadioButtonMenuItem showGrainsBorders;
    private javax.swing.JLabel sizeOfInclusionsLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel xLabelSize;
    // End of variables declaration//GEN-END:variables
}
