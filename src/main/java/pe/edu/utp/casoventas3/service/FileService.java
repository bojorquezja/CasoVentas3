package pe.edu.utp.casoventas3.service;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    public boolean exportResourceFile(String archOrigen, String archDestino){
        boolean ok = true;
        try {
            InputStream src = FileService.class.getClassLoader().getResourceAsStream(archOrigen);
            Files.copy(src, Paths.get(archDestino), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ok = false;
        }
        return ok;
    }
    public Image getImageAsIcon(String imagOrigen){
        Image img = new ImageIcon(FileService.class.getClassLoader().getResource(imagOrigen)).getImage();
        return img;
    }
}
