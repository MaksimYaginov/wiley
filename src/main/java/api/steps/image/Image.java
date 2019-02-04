package api.steps.image;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import static api.URI.*;
import static io.restassured.RestAssured.given;

public class Image {

    public Response getImage(String type) {

        return given()
                .contentType(ContentType.JSON)
                .pathParam("imageType", type.toLowerCase())
                .when()
                .get("/" + IMAGE + "/" + "{imageType}");
    }

    public String getImageFormat(InputStream inputStream) {

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