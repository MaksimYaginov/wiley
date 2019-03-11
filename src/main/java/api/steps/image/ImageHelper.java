package api.steps.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ImageHelper {

    public static String getImageFormat(InputStream inputStream) {

        try {
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(inputStream);
            Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);

            while (imageReaders.hasNext()) {
                ImageReader reader = imageReaders.next();
                return reader.getFormatName().toUpperCase();
            }

        } catch (IOException e) {
            throw new IllegalStateException("This is not image");
        }

        throw new IllegalStateException("This is not image");
    }
}
