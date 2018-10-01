/**
 * @author Ashika Mulagada
 *
 */

package com.example.ashikamulagada.homework2cs301;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Spinner;



public class MainActivity extends AppCompatActivity {

    protected Face face;
    protected SeekBar seekBarRed;
    protected SeekBar seekBarGreen;
    protected SeekBar seekBarBlue;

    protected TextView textViewRed;
    protected TextView textViewGreen;
    protected TextView textViewBlue;

    ArrayAdapter<CharSequence> adapter;
    protected OnItemSelected onItemSelected;
    Spinner hairstyles;

    private int hairProgressRed, hairProgressGreen, hairProgressBlue;
    private int skinProgressRed, skinProgressGreen, skinProgressBlue;
    private int eyeProgressRed, eyeProgressGreen, eyeProgressBlue;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = (Face)findViewById(R.id.surfaceViewFace);

        hairstyles = (Spinner)findViewById(R.id.spinnerHair);
        adapter = ArrayAdapter.createFromResource(this, R.array.hair_styles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        hairstyles.setAdapter(adapter);

        onItemSelected = new OnItemSelected(face);

        seekBarRed = (SeekBar) findViewById(R.id.seekBarRed);
        seekBarRed.setMax(255);

        seekBarGreen = (SeekBar) findViewById(R.id.seekBarGreen);
        seekBarGreen.setMax(255);

        seekBarBlue = (SeekBar) findViewById(R.id.seekBarBlue);
        seekBarBlue.setMax(255);

        textViewRed = (TextView) findViewById(R.id.textViewRed);
        textViewGreen = (TextView) findViewById(R.id.textViewGreen);
        textViewBlue = (TextView) findViewById(R.id.textViewBlue);

        Button buttonRand = (Button) findViewById(R.id.buttonRand);
        buttonRand.setOnClickListener(buttonOnClickListener);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(radioOnCheckListener);
    }

    private Button.OnClickListener buttonOnClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
                    face.setRand();

                    hairProgressRed = face.hairRed; //setting progress values to be able to update seekbar
                    hairProgressGreen = face.hairGreen;
                    hairProgressBlue = face.hairBlue;

                    skinProgressRed = face.skinRed;
                    skinProgressGreen = face.skinGreen;
                    skinProgressBlue = face.skinBlue;

                    eyeProgressRed = face.eyeRed;
                    eyeProgressGreen = face.eyeGreen;
                    eyeProgressBlue = face.eyeBlue;
        }
    };

    private RadioGroup.OnCheckedChangeListener radioOnCheckListener = new RadioGroup.OnCheckedChangeListener(){
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.radioButtonHair:
                    hairstyles.setOnItemSelectedListener(onItemSelected);

                    seekBarRed.setOnSeekBarChangeListener(seekBarChangeListenerHair);
                    seekBarGreen.setOnSeekBarChangeListener(seekBarChangeListenerHair);
                    seekBarBlue.setOnSeekBarChangeListener(seekBarChangeListenerHair);

                    seekBarRed.setProgress(hairProgressRed);
                    seekBarGreen.setProgress(hairProgressGreen);
                    seekBarBlue.setProgress(hairProgressBlue);
                    break;
                case R.id.radioButtonSkin:
                    seekBarRed.setOnSeekBarChangeListener(seekBarChangeListenerSkin);
                    seekBarGreen.setOnSeekBarChangeListener(seekBarChangeListenerSkin);
                    seekBarBlue.setOnSeekBarChangeListener(seekBarChangeListenerSkin);

                    seekBarRed.setProgress(skinProgressRed);
                    seekBarGreen.setProgress(skinProgressGreen);
                    seekBarBlue.setProgress(skinProgressBlue);
                    break;
                case R.id.radioButtonEye:
                    seekBarRed.setOnSeekBarChangeListener(seekBarChangeListenerEyes);
                    seekBarGreen.setOnSeekBarChangeListener(seekBarChangeListenerEyes);
                    seekBarBlue.setOnSeekBarChangeListener(seekBarChangeListenerEyes);

                    seekBarRed.setProgress(eyeProgressRed);
                    seekBarGreen.setProgress(eyeProgressGreen);
                    seekBarBlue.setProgress(eyeProgressBlue);
                    break;

            }
            /*
              External Citation
              problem: trying to think of a way to use all four buttons with minimal code
              https://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android
            */
        }
    };


    private SeekBar.OnSeekBarChangeListener seekBarChangeListenerHair = new SeekBar.OnSeekBarChangeListener()
    {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {

            switch (seekBar.getId()) {

                case R.id.seekBarRed:
                    hairProgressRed = progress;
                    break;
                case R.id.seekBarGreen:
                    hairProgressGreen = progress;
                    break;
                case R.id.seekBarBlue:
                    hairProgressBlue = progress;
                    break;
            }

            textViewRed.setText("Red: " + seekBarRed.getProgress());
            textViewGreen.setText("Green: " + seekBarGreen.getProgress());
            textViewBlue.setText("Blue: " + seekBarBlue.getProgress());
            updateHair();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    public void updateHair()
    {
        int red = seekBarRed.getProgress();
        int green= seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();
        face.setHairColor(red,green,blue);

    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListenerSkin = new SeekBar.OnSeekBarChangeListener()
    {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {

            switch (seekBar.getId()) {

                case R.id.seekBarRed:
                    skinProgressRed = progress;
                    break;
                case R.id.seekBarGreen:
                    skinProgressGreen = progress;
                    break;
                case R.id.seekBarBlue:
                    skinProgressBlue = progress;
                    break;
            }

            textViewRed.setText("Red: " + seekBarRed.getProgress());
            textViewGreen.setText("Green: " + seekBarGreen.getProgress());
            textViewBlue.setText("Blue: " + seekBarBlue.getProgress());
            updateSkin();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void updateSkin()
    {
        int red = seekBarRed.getProgress();
        int green= seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();
        face.setSkinColor(red,green,blue);

    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListenerEyes = new SeekBar.OnSeekBarChangeListener()
    {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {

            switch (seekBar.getId()) {

                case R.id.seekBarRed:
                    eyeProgressRed = progress;
                    break;
                case R.id.seekBarGreen:
                    eyeProgressGreen = progress;
                    break;
                case R.id.seekBarBlue:
                    eyeProgressBlue = progress;
                    break;
            }

            textViewRed.setText("Red: " + seekBarRed.getProgress());
            textViewGreen.setText("Green: " + seekBarGreen.getProgress());
            textViewBlue.setText("Blue: " + seekBarBlue.getProgress());
            updateEyes();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void updateEyes()
    {
        int red = seekBarRed.getProgress();
        int green= seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();
        face.setEyeColor(red,green,blue);

    }

    /*
    External citation
    Problem: figuring out how to incorparate all three seekbar values for setting the color
    Solution: found a concise way to code this in an example
    http://android-er.blogspot.com/2009/08/change-background-color-by-seekbar.html
     */
}
