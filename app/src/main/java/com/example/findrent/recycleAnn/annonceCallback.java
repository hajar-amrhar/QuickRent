package com.example.findrent.recycleAnn;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public interface annonceCallback {

    void onAnnonceItemClick(int pos, ImageView imaannonce,
                            TextView titleann, TextView descriptionann,
                            TextView adresseann, TextView dateann,
                             TextView prixann
    );

}


