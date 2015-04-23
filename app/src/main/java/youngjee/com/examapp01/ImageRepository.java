package youngjee.com.examapp01;

import java.io.File;

public class ImageRepository extends ImageCacheHandler {
    private static final String FILE_NAME = "/data/data/youngjee.com/cache/resource_image.cache";

    public static final ImageRepository INSTANCE = new ImageRepository();

    public ImageRepository() {
        super(new File(FILE_NAME).exists() ? ImageCache.toImageCache(FILE_NAME) : new ImageCache());
    }

    public void save() {
        File f = new File(FILE_NAME);
        File parent = f.getParentFile();

        if (!parent.isDirectory()) {
            parent.mkdir();
        }

        ImageCache.fromImageCache(FILE_NAME, getImageCache());
    }
}
