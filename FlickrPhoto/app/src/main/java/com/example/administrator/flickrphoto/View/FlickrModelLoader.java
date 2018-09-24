package com.example.administrator.flickrphoto.View;


import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.example.administrator.flickrphoto.Model.Photo;
import com.example.administrator.flickrphoto.Util.Api;

import java.io.InputStream;
import java.util.List;

/**
 * An implementation of ModelStreamLoader that leverages the StreamOpener class and the
 * ExecutorService backing the Engine to download the image and resize it in memory before saving
 * the resized version directly to the disk cache.
 */
public class FlickrModelLoader extends BaseGlideUrlLoader<Photo> {


    public static class Factory implements ModelLoaderFactory<Photo, InputStream> {
        private final ModelCache<Photo, GlideUrl> modelCache = new ModelCache<Photo, GlideUrl>(500);

        @Override
        public ModelLoader<Photo, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new FlickrModelLoader(multiFactory.build(GlideUrl.class, InputStream.class),
                    modelCache);
        }

        @Override
        public void teardown() {
        }
    }

    public FlickrModelLoader(ModelLoader<GlideUrl, InputStream> urlLoader,
                             ModelCache<Photo, GlideUrl> modelCache) {
        super(urlLoader, modelCache);
    }

    @Override
    public boolean handles(Photo model) {
        return true;
    }

    @Override
    protected String getUrl(Photo model, int width, int height, Options options) {
        return Api.getPhotoURL(model, width, height);
    }

    @Override
    protected List<String> getAlternateUrls(Photo photo, int width, int height, Options options) {
        return Api.getAlternateUrls(photo, width, height);
    }
}
