package adry.gallery;

import com.andrewoid.ApiKey;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RijksServiceTest {
    public void page() {
        // given
        RijksService service = new RijksServiceFactory().getService();
        ApiKey apiKey = new ApiKey();

        // when
        ArtObjects gallery = service.page(
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

     public void query() {
         // given
         RijksService service = new RijksServiceFactory().getService();
         ApiKey apiKey = new ApiKey();

         // when
         ArtObjects gallery = service.query(
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

     public void artist() {
         // given
         RijksService service = new RijksServiceFactory().getService();
         ApiKey apiKey = new ApiKey();

         // when
         ArtObjects gallery = service.artist(
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