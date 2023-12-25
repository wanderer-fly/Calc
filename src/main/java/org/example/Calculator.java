package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.FlatLightLaf;

public class Calculator extends JFrame {
    private JTextField textField;
    private double firstNumber = 0.0;
    private double secondNumber = 0.0;
    private char operator;

    public Calculator() {
        setTitle("Calculator");
        createUI();
        setLookAndFeel();
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 420);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 30);
        add(textField);

        JButton resetButton = new JButton("C");
        resetButton.setBounds(50, 70, 50, 50);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });
        add(resetButton);

        JButton[][] digitButtons = new JButton[4][3];
        int digit = 9;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                if (digit == -1) {
                    digitButtons[row][col] = new JButton(".");
                    digitButtons[row][col].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String currentText = textField.getText();
                            if (!currentText.contains(".")) {
                                textField.setText(currentText + ".");
                            }
                        }
                    });
                } else if (digit == -2) {
                    digitButtons[row][col] = new JButton("±"); // 取相反数操作符按钮
                    digitButtons[row][col].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String currentText = textField.getText();
                            if (!currentText.isEmpty() && !currentText.equals("0")) {
                                double currentValue = Double.parseDouble(currentText);
                                textField.setText(Double.toString(-currentValue));
                            }
                        }
                    });
                } else {
                    digitButtons[row][col] = new JButton(Integer.toString(digit));
                    final int currentDigit = digit;
                    digitButtons[row][col].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String currentText = textField.getText();
                            textField.setText(currentText + currentDigit);
                        }
                    });
                }
                digitButtons[row][col].setBounds(50 + col * 60, 130 + row * 60, 50, 50);
                add(digitButtons[row][col]);
                digit--;
            }
        }

        JButton addButton = new JButton("+");
        addButton.setBounds(240, 130, 50, 50);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }
        });
        add(addButton);

        JButton subButton = new JButton("-");
        subButton.setBounds(240, 190, 50, 50);
        subButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            }
        });
        add(subButton);

        JButton mulButton = new JButton("*");
        mulButton.setBounds(240, 250, 50, 50);
        mulButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
        });
        add(mulButton);

        JButton divButton = new JButton("/");
        divButton.setBounds(240, 310, 50, 50);
        divButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstNumber = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }
        });
        add(divButton);

        JButton equalButton = new JButton("=");
        equalButton.setBounds(300, 130, 50, 230);
        equalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secondNumber = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        textField.setText(Double.toString(firstNumber + secondNumber));
                        break;
                    case '-':
                        textField.setText(Double.toString(firstNumber - secondNumber));
                        break;
                    case '*':
                        textField.setText(Double.toString(firstNumber * secondNumber));
                        break;
                    case '/':
                        textField.setText(Double.toString(firstNumber / secondNumber));
                        break;
                }
            }
        });
        add(equalButton);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }
}
