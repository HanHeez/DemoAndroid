package com.example.administrator.flickrphoto.Model;

import com.example.administrator.flickrphoto.Model.Photo;

import java.util.List;

public interface PhotoViewer {

    public void onPhotosUpdated(List<Photo> photos);
}