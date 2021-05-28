import javax.swing.*;

public class GetData {

    public static double getDouble(String s) {

        while (true) {
            try {
                return Double.parseDouble(getWord(s));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a decimal value.");
            }
        }
    }

    public static int getInt(String s) {

        while (true) {
            try {
                return Integer.parseInt(getWord(s));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter an integer value.");
            }
        }
    }

    public static String getWord(String s) {
        return JOptionPane.showInputDialog(s);
    }
}

