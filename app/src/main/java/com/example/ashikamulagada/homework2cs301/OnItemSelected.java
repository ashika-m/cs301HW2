/**
 * @author Ashika Mulagada
 *
 */

package com.example.ashikamulagada.homework2cs301;

import android.view.View;
import android.widget.AdapterView;

public class OnItemSelected implements AdapterView.OnItemSelectedListener{
    protected Face myface;

    public OnItemSelected(Face face){
        myface = face;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        String style = parent.getItemAtPosition(pos).toString();
        myface.setHairStyle(style);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}

