package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.*;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MathFunctionWindow extends JFrame {
    private JComboBox<String> functionComboBox = new JComboBox<>();
    private JLabel fromLabel = new JLabel("from:");
    private JLabel toLabel = new JLabel("to:");
    private JLabel countLabel = new JLabel("count:");
    private JTextField countField = new JTextField();
    private JTextField fromField = new JTextField();
    private JTextField toField = new JTextField();
    private JButton buttonOk = new JButton("OK");
    private Map<String, MathFunction> nameFuncMap = new HashMap<>();
    private TabulatedFunction factory;

    public static void main(String[] args) {
        MathFunctionWindow app = new MathFunctionWindow();
        app.setVisible(true);
    }

    public MathFunctionWindow() {
        super("Window");
        this.setBounds(300, 200, 500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fillMap();
        compose();
        addButtonListeners();
    }

    public void fillMap() {
        nameFuncMap.put("reverse", new ReverseFunction());
        nameFuncMap.put("cosh", new CoshFunction ());
        nameFuncMap.put("sqr", new SqrFunction());
        nameFuncMap.put("zero", new ZeroFunction());
        nameFuncMap.put("unit", new UnitFunction());
        String[] functions = new String[5];
        int i = 0;
        for (String string : nameFuncMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(buttonOk)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(buttonOk)
        );

    }

    public void addButtonListeners() {
        buttonOk.addActionListener(event -> {
            try {
                String myFunction = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFuncMap.get(myFunction);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                TabulatedFunction result = new ArrayTabulatedFunction(selectedFunction, from, to, count);
            } catch (Exception e) {
                Error myError = new Error(this, e);
                myError.showError(this, e);
            }
        });
    }
}

