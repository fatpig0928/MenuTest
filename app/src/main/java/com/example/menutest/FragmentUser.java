package com.example.menutest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentUser extends Fragment {

    private Button review_allspell;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_user, container, false);
        review_allspell= (Button) rootView.findViewById(R.id.review_allspell);
        review_allspell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity()
                        .getApplicationContext(), ReviewAllSpellActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
