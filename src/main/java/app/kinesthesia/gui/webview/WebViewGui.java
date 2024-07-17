package app.kinesthesia.gui.webview;

import javax.swing.*;

public class WebViewGui extends JFrame {

    public WebViewGui() {
        super("Kinesthesia");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new WebViewGui();
    }
}
