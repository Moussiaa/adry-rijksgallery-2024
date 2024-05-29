package adry.gallery;

import com.andrewoid.ApiKey;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class SearchFrame extends JFrame {

    private final RijksService service = new RijksServiceFactory().getService();
    private final ApiKey apiKey = new ApiKey();
    private int page = 1;
    private final JTextField searchBar;
    private final JPanel artGrid;

    public SearchFrame() {

        // create frame
        setTitle("Rijks Gallery");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // main panel
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        setContentPane(main);

        // header panel: prev and next page buttons, search bar
        searchBar = new JTextField(50);
        JButton prevButton = new JButton("<< Previous Page");
        JButton nextButton = new JButton("Next Page >>");

        // add listeners
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (page > 1) {
                    page--;
                    reprintGallery();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page++;
                reprintGallery();
            }
        });

        searchBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String search = searchBar.getText();
                page = 1;
                reprintGallery();
            }
        });

        // add to main panel
        JPanel topBar = new JPanel(new FlowLayout());
        topBar.add(prevButton);
        topBar.add(searchBar);
        topBar.add(nextButton);
        main.add(topBar, BorderLayout.NORTH);

        // art gallery panel, formatted as grid
        artGrid = new JPanel();
        artGrid.setLayout(new GridLayout(5, 2, 10, 10));
        main.add(new JScrollPane(artGrid), BorderLayout.CENTER);

        // load gallery
        reprintGallery();
    }

    private void reprintGallery() {
        String query = searchBar.getText();
        Disposable disposable;
        if (query.isEmpty()) {
            disposable = service.page(apiKey.get(), page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(SwingSchedulers.edt())
                    .subscribe(this::getArtwork, Throwable::printStackTrace);
        } else {
            disposable = service.query(apiKey.get(), page, query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(SwingSchedulers.edt())
                    .subscribe(this::getArtwork, Throwable::printStackTrace);
        }
    }

    private void getArtwork(ArtObjects gallery) {
        // Clear the grid
        artGrid.removeAll();

        // Load the artwork
        for (ArtObject artObject : gallery.artObjects) {
            try {
                URL url = new URL(artObject.webImage.url);
                Image image = ImageIO.read(url);
                ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(150, -1, Image.SCALE_DEFAULT));
                JLabel label = new JLabel(imageIcon);
                label.setToolTipText(artObject.title + " by " + artObject.principalOrFirstMaker);

                // Add MouseAdapter for consistent behavior with getImages method
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            new ArtworkFrame(artObject).setVisible(true);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                artGrid.add(label);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Refresh the panel
        artGrid.revalidate();
        artGrid.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchFrame().setVisible(true));
    }
}





