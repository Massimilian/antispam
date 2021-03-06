package ru.progwards.java1.lessons.classloaders;

import javax.swing.*;
import java.net.URL;

public class LoadResourcesAsURLDialog {
    public static void main(String[] args) throws Exception {
        URL url = ClassLoader.getSystemResource("progwards_bw.png");

        Icon icon = new ImageIcon(url);

        JOptionPane.showMessageDialog(
                null,
                "URL ресурса:\n" + url,
                "Пример загрузки ресурса",
                JOptionPane.INFORMATION_MESSAGE,
                icon
        );
    }
}
