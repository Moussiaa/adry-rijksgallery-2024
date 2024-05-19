package adry.gallery;

import com.andrewoid.ApiKey;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RijksServiceTest {
    public void Page() {
        // given
        RijksService service = new RijksServiceFactory().getService();
        ApiKey apiKey = new ApiKey();

        // when
        ArtObjects gallery = service.Page(
                apiKey.get(), 4
        ).blockingGet();

        // then
        ArtObject artObject = gallery.artObjects[0];
        assertNotNull(artObject.title);
        assertNotNull(artObject.longTitle);
        assertNotNull(artObject.webImage);
        assertNotNull(artObject.webImage.url);
        assertNotNull(artObject.principalOrFirstMaker);
    }
     public void Query() {
         // given
         RijksService service = new RijksServiceFactory().getService();
         ApiKey apiKey = new ApiKey();

         // when
         ArtObjects gallery = service.Query(
                 apiKey.get(), 4, "Eenden"
         ).blockingGet();

         // then
         ArtObject artObject = gallery.artObjects[0];
         assertNotNull(artObject.title);
         assertNotNull(artObject.longTitle);
         assertNotNull(artObject.webImage);
         assertNotNull(artObject.webImage.url);
         assertNotNull(artObject.principalOrFirstMaker);
    }
     public void Artist() {
         // given
         RijksService service = new RijksServiceFactory().getService();
         ApiKey apiKey = new ApiKey();

         // when
         ArtObjects gallery = service.Artist(
                 apiKey.get(), 1, "Willem Maris"
         ).blockingGet();

         // then
         ArtObject artObject = gallery.artObjects[0];
         assertNotNull(artObject.title);
         assertNotNull(artObject.longTitle);
         assertNotNull(artObject.webImage);
         assertNotNull(artObject.webImage.url);
         assertNotNull(artObject.principalOrFirstMaker);
    }
}