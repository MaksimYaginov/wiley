package api;

import api.base.BaseApiTest;
import api.steps.image.ImageSteps;
import api.steps.image.ImageType;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;

import static api.steps.image.ImageHelper.getImageFormat;

public class ImageTests extends BaseApiTest {

    private ImageSteps imageSteps = new ImageSteps();

    @Test
    public void checkGetPngImage() {
        InputStream image = imageSteps.getImage(ImageType.PNG, HttpStatus.SC_OK);

        Assert.assertEquals(getImageFormat(image), ImageType.PNG);
    }

    @Test
    public void checkGetJpegImage() {
        InputStream image = imageSteps.getImage(ImageType.JPEG, HttpStatus.SC_OK);

        Assert.assertEquals(getImageFormat(image), ImageType.JPEG);
    }
}
