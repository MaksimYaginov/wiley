package api;

import api.base.BaseApiTest;
import api.steps.image.Image;
import api.steps.image.ImageType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImageTests extends BaseApiTest {

    private Image image = new Image();

    @Test
    public void checkGetPngImage() {
        Response response = image.getImage(ImageType.PNG);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(image.getImageFormat(response.getBody().asInputStream()), ImageType.PNG);
    }

    @Test
    public void checkGetJpegImage() {
        Response response = image.getImage(ImageType.JPEG);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(image.getImageFormat(response.getBody().asInputStream()), ImageType.JPEG);
    }
}
