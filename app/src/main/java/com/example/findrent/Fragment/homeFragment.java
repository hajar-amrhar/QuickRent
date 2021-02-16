package com.example.findrent.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.findrent.Home;
import com.example.findrent.R;
import com.google.android.material.chip.Chip;

public class homeFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    ImageView botTri;
    Chip app, chambre, garc, duplexe, maison, loccom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        app = container.findViewById(R.id.chipApp);
        garc = container.findViewById(R.id.chipGarc);
        chambre = container.findViewById(R.id.chipCham);
        duplexe = container.findViewById(R.id.chipDuplexe);
        maison = container.findViewById(R.id.chipMaison);
        loccom = container.findViewById(R.id.chipLocCom);

        //botTri.setOnClickListener(more);


        app.setOnClickListener(filterListener);
        garc.setOnClickListener(filterListener);
        chambre.setOnClickListener(filterListener);
        maison.setOnClickListener(filterListener);
        duplexe.setOnClickListener(filterListener);
        loccom.setOnClickListener(filterListener);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private View.OnClickListener filterListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.chipApp:
                    break;
                case R.id.chipCham:

                    break;
                case R.id.chipGarc:
                    break;
                case R.id.chipDuplexe:
                    break;
                case R.id.chipMaison:
                    break;
                case R.id.chipLocCom:
                    break;
            }
        }
    };


    private View.OnClickListener more = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            PopupMenu popup = new PopupMenu(getActivity(),v);
            popup.inflate(R.menu.popup_menu);
            popup.setOnMenuItemClickListener(homeFragment.this::onMenuItemClick);
            popup.show();
        }
    };



    /*
    public void ShowPopup(View v){
        PopupMenu popup = new PopupMenu (getActivity(),v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


     */


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_1:
                //tri par date
               // Toast.makeText(this, "tri par date", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option_2:
                //tri par date
               // Toast.makeText(this, "tri par date", Toast.LENGTH_LONG).show();
                return true;
            case R.id.option_3:
                //tri par date
                //Toast.makeText(this, "tri par date", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }
}