import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class GUI {

    private static int presets_bph[]={12000, 14400, 18000, 19800, 21600, 25200, 28800, 36000, 43200, 72000};

    public static void main(String[] args){
        GUI.init_main_window();
    }

    private static void init_main_window() {
        JFrame main = new JFrame("Watch tool");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // При закрытии окна - останавливать программу.
        main.setSize(950,700);
        String[] string_preset_bph = new String[presets_bph.length+1];// Блок перевода пресета в String - для
        string_preset_bph[0]="Auto";                                  // всплывающего блока
        for(int n=1; n<string_preset_bph.length;n++){
            string_preset_bph[n]=presets_bph[n-1]+"";
        }
        JComboBox bph_menu = new JComboBox(string_preset_bph);
  
        main.add(new JLabel("bph"));
        main.add(bph_menu);

        main.setVisible(true);
    }
}
