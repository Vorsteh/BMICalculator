import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BmiFrame {

    double underWeight = 18.5f;
    double normalWeight = 24.9f;
    double overweight = 29.9f;

    private final int windowHeight;
    private final int windowWidth;

    public JFrame frame;
    public JButton calc;
    public JLabel weight;
    public JLabel height;
    public JLabel BMI;
    public JTextField weightText;
    public JTextField heightText;

    BmiFrame(int windowHeight, int windowWidth) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;

        frame = new JFrame("BMI Calculator");
        calc = new JButton("Calculate");
        weight = new JLabel("Weight: ");
        height = new JLabel("Height: ");
        BMI = new JLabel("0.0");
        weightText = new JTextField("Enter weight");
        heightText = new JTextField("Enter height");


        setLabel();
        setTextField();
        setButton();
        setFrame();

        calc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double bmi = calculateBMI(Double.parseDouble(heightText.getText()), Double.parseDouble(weightText.getText()));
                String bmiNew = String.format("%.1f", bmi);
                BMI.setText(String.valueOf(bmiNew));
                JOptionPane.showMessageDialog(frame, scale(bmi, underWeight, normalWeight, overweight));
            }
        });
        weightText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                weightText.setText("");
            }
        });
        heightText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                heightText.setText("");
            }
        });
    }
    public void setLabel(){
        weight.setBounds(40, 50, 100, 25);
        height.setBounds(40, 125, 100, 25);
        BMI.setBounds(175, 180, 50, 25);
        weight.setVisible(true);
        height.setVisible(true);
        BMI.setVisible(true);
    }
    public void setTextField(){
        weightText.setBounds(90, 50, 100, 25);
        heightText.setBounds(90, 125, 100, 25);
        weightText.setFocusable(false);
        weightText.setFocusable(true);
        heightText.setFocusable(false);
        heightText.setFocusable(true);



    }
    public void setButton(){
        calc.setBounds(55, 180, 100, 35);
        calc.setFocusable(false);
        calc.setVisible(true);
    }

    public void setFrame(){
        frame.getContentPane().add(weight);
        frame.getContentPane().add(height);
        frame.getContentPane().add(weightText);
        frame.getContentPane().add(heightText);
        frame.getContentPane().add(calc);
        frame.getContentPane().add(BMI);


        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static double calculateBMI(double height, double weight){
        double heightM = height / 100;
        return (weight / heightM) / heightM;
    }

    public static String scale(double bmi, double uw, double nw, double ow){
        if(bmi <= uw) {
            return "Underweight";
        }else if(bmi <= nw){
            return "Normal weight";
        }else if(bmi <= ow){
            return "Over weight";
        }else{
            return "Obesity";
        }
    }

    public static void main(String[] args) {
        new BmiFrame(300, 300);
    }
}
