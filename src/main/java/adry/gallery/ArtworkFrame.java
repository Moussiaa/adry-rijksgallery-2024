package adry.gallery;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ArtworkFrame extends JFrame {

    public ArtworkFrame(ArtObject artObject) throws IOException {

        setTitle(artObject.principalOrFirstMaker + ", " + artObject.title);
        setSize(800, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        URL url = new URL(artObject.webImage.url);
        Image image = ImageIO.read(url);
        Image scaledImage = image.getScaledInstance(800, -1, Image.SCALE_DEFAULT);

        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(scaledImage);
        label.setIcon(imageIcon);

        JScrollPane scrollPane = new JScrollPane(label);
        add(scrollPane, BorderLayout.CENTER);
    }
}
