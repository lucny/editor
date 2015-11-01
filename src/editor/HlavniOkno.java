/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

/**
 * Jednoduchý editor v Javě
 *
 * @author Marek Lučný
 */
public class HlavniOkno extends javax.swing.JFrame {

    private File soubor;
    private final Soubor txtSoubor = new Soubor();
    private String kodovani = "UTF-8";
    private String searchText = "";
    private String replText = "";

    /**
     * Creates new form HlavniOkno
     */
    public HlavniOkno() {
        initComponents();
    }

    private String informaceOSouboru() {
        String info = "";
        info += "Název souboru: " + soubor.getName() + "\n";
        info += "Umístění: " + soubor.getParent() + "\n";
        info += "Velikost: " + String.valueOf(soubor.length()) + " bytes\n";
        info += "Práva: ";
        info += soubor.canRead() ? "R " : "- ";
        info += soubor.canWrite() ? "W " : "- ";
        info += soubor.canExecute() ? "X\n" : "-\n";
        info += "Skrytý soubor: " + (soubor.isHidden() ? "ano " : "ne\n");
        Date datum = new Date();
        datum.setTime(soubor.lastModified());
        info += "Datum aktualizace: " + datum.toString();
        return info;
    }

    private int pocetRadku(String s) {
        int i = 1;
        int pozice = 0;
        while (s.indexOf("\n", pozice) > -1) {
            pozice = s.indexOf("\n", pozice) + 1;
            i++;
        }
        return i;
    }

    private void statusBarInfo() {
        infoRight.setText("Počet znaků: " + String.valueOf(editor.getText().length()));
        infoCenter.setText("Počet řádků: " + String.valueOf(this.pocetRadku(editor.getText())));
    }

    private void searchOperation(String foundTxt, String replacedTxt, Boolean replace) {
        editor.requestFocusInWindow();
        int startFrom = (editor.getCaretPosition() == editor.getDocument().getLength()) ? 0 : editor.getCaretPosition();
        int max = editor.getDocument().getLength() - startFrom;
        int searchIndex = -1;
        try {
            searchIndex = editor.getDocument().getText(startFrom, max).indexOf(foundTxt);
        } catch (BadLocationException ex) {
            Logger.getLogger(HlavniOkno.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (searchIndex != -1) {
            editor.select(searchIndex + startFrom, searchIndex + startFrom + foundTxt.length());
            if (replace) {
                editor.replaceSelection(replacedTxt);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Řetězec nebyl nalezen");
            editor.setSelectionStart(-1);
            editor.setSelectionEnd(-1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupCodePages = new javax.swing.ButtonGroup();
        toolBar = new javax.swing.JToolBar();
        toolNew = new javax.swing.JButton();
        toolOpen = new javax.swing.JButton();
        toolSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        editor = new javax.swing.JEditorPane();
        statusBar = new javax.swing.JPanel();
        infoLeft = new javax.swing.JLabel();
        infoCenter = new javax.swing.JLabel();
        infoRight = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        itemNew = new javax.swing.JMenuItem();
        itemOpen = new javax.swing.JMenuItem();
        itemSave = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemInfo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itemClose = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        itemCut = new javax.swing.JMenuItem();
        itemCopy = new javax.swing.JMenuItem();
        itemPaste = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemFind = new javax.swing.JMenuItem();
        itemRaplace = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        itemSelectAll = new javax.swing.JMenuItem();
        menuSettings = new javax.swing.JMenu();
        menuView = new javax.swing.JMenu();
        itemToolBar = new javax.swing.JCheckBoxMenuItem();
        itemStatusBar = new javax.swing.JCheckBoxMenuItem();
        itemColor = new javax.swing.JMenuItem();
        itemFont = new javax.swing.JMenuItem();
        menuCodePage = new javax.swing.JMenu();
        itemUTF8 = new javax.swing.JRadioButtonMenuItem();
        itemWin1250 = new javax.swing.JRadioButtonMenuItem();
        itemISO = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editorek");
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        setName("frame"); // NOI18N
        getContentPane().setLayout(new java.awt.BorderLayout(0, 1));

        toolBar.setRollover(true);

        toolNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikony/new.png"))); // NOI18N
        toolNew.setText("Nový");
        toolNew.setFocusable(false);
        toolNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolNewActionPerformed(evt);
            }
        });
        toolBar.add(toolNew);

        toolOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikony/open.png"))); // NOI18N
        toolOpen.setMnemonic('o');
        toolOpen.setText("Otevřít");
        toolOpen.setToolTipText("Otevře textový dokument");
        toolOpen.setFocusable(false);
        toolOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolOpenActionPerformed(evt);
            }
        });
        toolBar.add(toolOpen);

        toolSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikony/save.png"))); // NOI18N
        toolSave.setMnemonic('u');
        toolSave.setText("Uložit");
        toolSave.setToolTipText("Uloží textový dokument");
        toolSave.setFocusable(false);
        toolSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolSaveActionPerformed(evt);
            }
        });
        toolBar.add(toolSave);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        editor.setPreferredSize(new java.awt.Dimension(106, 400));
        editor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                editorPropertyChange(evt);
            }
        });
        editor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                editorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editorKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(editor);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        statusBar.setBackground(new java.awt.Color(204, 204, 204));
        statusBar.setPreferredSize(new java.awt.Dimension(610, 30));
        statusBar.setLayout(new java.awt.GridLayout(1, 3, 10, 0));

        infoLeft.setBackground(new java.awt.Color(0, 102, 255));
        infoLeft.setForeground(new java.awt.Color(255, 255, 255));
        infoLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoLeft.setText("Název souboru");
        infoLeft.setOpaque(true);
        statusBar.add(infoLeft);

        infoCenter.setBackground(new java.awt.Color(255, 204, 0));
        infoCenter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoCenter.setText("Vlastnosti souboru");
        infoCenter.setOpaque(true);
        statusBar.add(infoCenter);

        infoRight.setBackground(new java.awt.Color(102, 153, 0));
        infoRight.setForeground(new java.awt.Color(255, 255, 255));
        infoRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoRight.setText("Parametry textu");
        infoRight.setOpaque(true);
        infoRight.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                infoRightInputMethodTextChanged(evt);
            }
        });
        statusBar.add(infoRight);

        getContentPane().add(statusBar, java.awt.BorderLayout.PAGE_END);

        menuFile.setMnemonic('s');
        menuFile.setText("Soubor");
        menuFile.setToolTipText("");

        itemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        itemNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikony/new-small.png"))); // NOI18N
        itemNew.setMnemonic('n');
        itemNew.setText("Nový");
        itemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewActionPerformed(evt);
            }
        });
        menuFile.add(itemNew);

        itemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        itemOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikony/open-small.png"))); // NOI18N
        itemOpen.setMnemonic('o');
        itemOpen.setText("Otevřít...");
        itemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOpenActionPerformed(evt);
            }
        });
        menuFile.add(itemOpen);

        itemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        itemSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ikony/save-small.png"))); // NOI18N
        itemSave.setMnemonic('u');
        itemSave.setText("Uložit...");
        itemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSaveActionPerformed(evt);
            }
        });
        menuFile.add(itemSave);
        menuFile.add(jSeparator1);

        itemInfo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        itemInfo.setMnemonic('i');
        itemInfo.setText("Informace o souboru");
        itemInfo.setEnabled(false);
        itemInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInfoActionPerformed(evt);
            }
        });
        menuFile.add(itemInfo);
        menuFile.add(jSeparator2);

        itemClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        itemClose.setMnemonic('k');
        itemClose.setText("Konec");
        itemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCloseActionPerformed(evt);
            }
        });
        menuFile.add(itemClose);

        menuBar.add(menuFile);

        menuEdit.setMnemonic('p');
        menuEdit.setText("Úpravy");

        itemCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        itemCut.setMnemonic('v');
        itemCut.setText("Vyjmout");
        itemCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCutActionPerformed(evt);
            }
        });
        menuEdit.add(itemCut);

        itemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        itemCopy.setMnemonic('k');
        itemCopy.setText("Kopírovat");
        itemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCopyActionPerformed(evt);
            }
        });
        menuEdit.add(itemCopy);

        itemPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        itemPaste.setMnemonic('l');
        itemPaste.setText("Vložit");
        itemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPasteActionPerformed(evt);
            }
        });
        menuEdit.add(itemPaste);
        menuEdit.add(jSeparator3);

        itemFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        itemFind.setMnemonic('h');
        itemFind.setText("Hledat...");
        itemFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFindActionPerformed(evt);
            }
        });
        menuEdit.add(itemFind);

        itemRaplace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        itemRaplace.setMnemonic('n');
        itemRaplace.setText("Nahradit...");
        itemRaplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRaplaceActionPerformed(evt);
            }
        });
        menuEdit.add(itemRaplace);
        menuEdit.add(jSeparator4);

        itemSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        itemSelectAll.setMnemonic('b');
        itemSelectAll.setText("Vybrat vše");
        itemSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSelectAllActionPerformed(evt);
            }
        });
        menuEdit.add(itemSelectAll);

        menuBar.add(menuEdit);

        menuSettings.setMnemonic('n');
        menuSettings.setText("Nastavení");

        menuView.setMnemonic('z');
        menuView.setText("Zobrazení");

        itemToolBar.setSelected(true);
        itemToolBar.setText("Panel nástrojů");
        itemToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemToolBarActionPerformed(evt);
            }
        });
        menuView.add(itemToolBar);

        itemStatusBar.setSelected(true);
        itemStatusBar.setText("Stavový řádek");
        itemStatusBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemStatusBarActionPerformed(evt);
            }
        });
        menuView.add(itemStatusBar);

        menuSettings.add(menuView);

        itemColor.setMnemonic('b');
        itemColor.setText("Barva pozadí...");
        itemColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemColorActionPerformed(evt);
            }
        });
        menuSettings.add(itemColor);

        itemFont.setMnemonic('p');
        itemFont.setText("Písmo...");
        itemFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFontActionPerformed(evt);
            }
        });
        menuSettings.add(itemFont);

        menuCodePage.setMnemonic('k');
        menuCodePage.setText("Kódování znaků");
        menuCodePage.setToolTipText("");

        groupCodePages.add(itemUTF8);
        itemUTF8.setSelected(true);
        itemUTF8.setText("UTF-8");
        itemUTF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUTF8ActionPerformed(evt);
            }
        });
        menuCodePage.add(itemUTF8);

        groupCodePages.add(itemWin1250);
        itemWin1250.setText("Windows 1250");
        itemWin1250.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemWin1250ActionPerformed(evt);
            }
        });
        menuCodePage.add(itemWin1250);

        groupCodePages.add(itemISO);
        itemISO.setText("ISO-8859-2");
        itemISO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemISOActionPerformed(evt);
            }
        });
        menuCodePage.add(itemISO);

        menuSettings.add(menuCodePage);

        menuBar.add(menuSettings);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCloseActionPerformed
        // Ukončení aplikace
        System.exit(0);
    }//GEN-LAST:event_itemCloseActionPerformed

    private void itemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOpenActionPerformed
        try {
            /* Vytvoření objektu dialogového okna pro práci se soubory */
            JFileChooser fc = new JFileChooser();
            /* Nastavení typu dialogového okna a jeho titulku */
            fc.setDialogType(JFileChooser.OPEN_DIALOG);
            fc.setDialogTitle("Otevření souboru");
            /* Nastavení aktuálního adresáře pro dialogové okno */
            fc.setCurrentDirectory(new java.io.File("."));
            /* Nastavení vlastního filtru pro výběr souborů - txt */
            FileNameExtensionFilter myFilter = new FileNameExtensionFilter("Text", "txt");
            fc.setFileFilter(myFilter);
            /* Zobrazení připraveného dialogového okna */
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                /* Do proměnné soubor bude vložen odkaz na vybraný soubor */
                soubor = fc.getSelectedFile();
                try {
                    /* Načtení dat ze souboru a jejich zobrazení v komponentě editor */
                    txtSoubor.nactiZeSouboru(soubor, kodovani);
                    editor.setText(txtSoubor.getData());
                } catch (FileNotFoundException ex) {
                    /* V případě chyby se zobrazí varovné okno */
                    JOptionPane.showMessageDialog(this, "Požadovaný soubor nebyl nalezen!", "Chyba!", JOptionPane.ERROR_MESSAGE);
                }
            }
            itemInfo.setEnabled(true);
            this.statusBarInfo();
        } /* Zachycení obecné výjimky */ catch (Exception e) {
            /* Zobrazení dialogového okna s upozorněním na chybu */
            JOptionPane.showMessageDialog(this, "Nastala chyba při otevření souboru!", "Chyba!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_itemOpenActionPerformed

    private void itemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSaveActionPerformed
        try {
            /* Vytvoření a zobrazení dialogového okna pro uložení souboru */
            JFileChooser fc = new JFileChooser();
            fc.setDialogType(JFileChooser.SAVE_DIALOG);
            fc.setDialogTitle("Uložení souboru");
            fc.setCurrentDirectory(new java.io.File("."));
            FileNameExtensionFilter myFilter = new FileNameExtensionFilter("Text", "txt");
            fc.setFileFilter(myFilter);
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                txtSoubor.setData(editor.getText());
                /* Metoda objektu txtSoubor zajistí uložení dat podle zvoleného kódování */
                txtSoubor.ulozDoSouboru(fc.getSelectedFile(), kodovani);
            }
        } // Zachycení obecné výjimky
        catch (HeadlessException | FileNotFoundException e) {
            // Zobrazení dialogového okna s upozorněním na chybu
            JOptionPane.showMessageDialog(this, "Nastala chyba při ukládání souboru!", "Chyba!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_itemSaveActionPerformed

    private void itemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewActionPerformed
        editor.setText("");
    }//GEN-LAST:event_itemNewActionPerformed

    private void toolNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolNewActionPerformed
        this.itemNewActionPerformed(evt);
        itemInfo.setEnabled(false);
    }//GEN-LAST:event_toolNewActionPerformed

    private void toolOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolOpenActionPerformed
        this.itemOpenActionPerformed(evt);
    }//GEN-LAST:event_toolOpenActionPerformed

    private void toolSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolSaveActionPerformed
        this.itemSaveActionPerformed(evt);
    }//GEN-LAST:event_toolSaveActionPerformed

    private void itemSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSelectAllActionPerformed
        editor.requestFocusInWindow();
        editor.selectAll();
    }//GEN-LAST:event_itemSelectAllActionPerformed

    private void itemCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCutActionPerformed
        editor.cut();
    }//GEN-LAST:event_itemCutActionPerformed

    private void itemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCopyActionPerformed
        editor.copy();
    }//GEN-LAST:event_itemCopyActionPerformed

    private void itemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPasteActionPerformed
        editor.paste();
    }//GEN-LAST:event_itemPasteActionPerformed

    private void itemToolBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemToolBarActionPerformed
        if (itemToolBar.isSelected()) {
            toolBar.setVisible(true);
        } else {
            toolBar.setVisible(false);
        }
    }//GEN-LAST:event_itemToolBarActionPerformed

    private void itemStatusBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStatusBarActionPerformed
        if (itemStatusBar.isSelected()) {
            statusBar.setVisible(true);
        } else {
            statusBar.setVisible(false);
        }
    }//GEN-LAST:event_itemStatusBarActionPerformed

    private void itemColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemColorActionPerformed
        Color barva = JColorChooser.showDialog(this, "Vyber barvu pozadí", editor.getBackground());
        editor.setBackground(barva);
    }//GEN-LAST:event_itemColorActionPerformed

    /**
     * Ohlasová metoda na událost spojenou s kliknutím na položku menu Nastavení
     * | Písmo...
     *
     * @param evt
     */
    private void itemFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFontActionPerformed
        /* Pomocí konstruktoru je vytvořen objekt dialogového okna FontDialog */
        FontDialog fontDialog = new FontDialog(this, true, editor.getFont(), editor.getForeground());
        /* Zobrazení dialogového okna metodou showDialog() */
        if (fontDialog.showDialog().equalsIgnoreCase("OK")) {
            /* V případě, že bylo uzavřeno tlačítkem OK, dojde k nastavení editoru podle 
             písma a barvy, kterou uživatel předvolil v dialogovém okně */
            editor.setFont(fontDialog.getPismo());
            editor.setForeground(fontDialog.getBarva());
        }
    }//GEN-LAST:event_itemFontActionPerformed

    private void itemUTF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUTF8ActionPerformed
        kodovani = "UTF-8";
    }//GEN-LAST:event_itemUTF8ActionPerformed

    private void itemWin1250ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemWin1250ActionPerformed
        kodovani = "cp1250";
    }//GEN-LAST:event_itemWin1250ActionPerformed

    private void itemISOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemISOActionPerformed
        kodovani = "iso8859-2";
    }//GEN-LAST:event_itemISOActionPerformed

    private void itemInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInfoActionPerformed
        JOptionPane.showMessageDialog(this, this.informaceOSouboru(), "Informace o souboru", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_itemInfoActionPerformed

    private void editorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_editorPropertyChange
        this.statusBarInfo();
    }//GEN-LAST:event_editorPropertyChange

    private void infoRightInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_infoRightInputMethodTextChanged
    }//GEN-LAST:event_infoRightInputMethodTextChanged

    private void editorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editorKeyTyped
        this.statusBarInfo();
    }//GEN-LAST:event_editorKeyTyped

    private void editorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editorKeyReleased
        this.statusBarInfo();
    }//GEN-LAST:event_editorKeyReleased

    private void itemFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFindActionPerformed
        //searchWord = JOptionPane.showInputDialog ("Zadej hledaný řetězec",searchWord);
        searchText = JOptionPane.showInputDialog(this, "Zadej hledaný řetězec", "Hledej", JOptionPane.PLAIN_MESSAGE, null, null, searchText).toString();
        this.searchOperation(searchText, null, false);
    }//GEN-LAST:event_itemFindActionPerformed

    private void itemRaplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRaplaceActionPerformed
        NahraditDialog nahraditDialog = new NahraditDialog(this, true, searchText, replText);
        /* zobrazení dialogového okna */
        if (nahraditDialog.showDialog().equals("Nahradit")) {
            editor.requestFocusInWindow();
            searchText = nahraditDialog.getReplacedText();
            replText = nahraditDialog.getNewText();
            this.searchOperation(searchText, replText, true);
        }
    }//GEN-LAST:event_itemRaplaceActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HlavniOkno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new HlavniOkno().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane editor;
    private javax.swing.ButtonGroup groupCodePages;
    private javax.swing.JLabel infoCenter;
    private javax.swing.JLabel infoLeft;
    private javax.swing.JLabel infoRight;
    private javax.swing.JMenuItem itemClose;
    private javax.swing.JMenuItem itemColor;
    private javax.swing.JMenuItem itemCopy;
    private javax.swing.JMenuItem itemCut;
    private javax.swing.JMenuItem itemFind;
    private javax.swing.JMenuItem itemFont;
    private javax.swing.JRadioButtonMenuItem itemISO;
    private javax.swing.JMenuItem itemInfo;
    private javax.swing.JMenuItem itemNew;
    private javax.swing.JMenuItem itemOpen;
    private javax.swing.JMenuItem itemPaste;
    private javax.swing.JMenuItem itemRaplace;
    private javax.swing.JMenuItem itemSave;
    private javax.swing.JMenuItem itemSelectAll;
    private javax.swing.JCheckBoxMenuItem itemStatusBar;
    private javax.swing.JCheckBoxMenuItem itemToolBar;
    private javax.swing.JRadioButtonMenuItem itemUTF8;
    private javax.swing.JRadioButtonMenuItem itemWin1250;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCodePage;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuSettings;
    private javax.swing.JMenu menuView;
    private javax.swing.JPanel statusBar;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton toolNew;
    private javax.swing.JButton toolOpen;
    private javax.swing.JButton toolSave;
    // End of variables declaration//GEN-END:variables
}
