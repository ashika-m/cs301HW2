/**
 * @author Ashika Mulagada
 *
 */

package com.example.ashikamulagada.homework2cs301;

import android.view.SurfaceView;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Color;
import java.util.Random;

public class Face extends SurfaceView  {

    private int skinColor = 0xFF000000;
    private int eyeColor = 0xFF000000;
    private int hairColor = 0xFF000000;
    private int hairStyle = 1;

    protected int hairRed, hairGreen, hairBlue; //int color values for individual colors
    protected int skinRed, skinGreen, skinBlue;
    protected int eyeRed, eyeGreen, eyeBlue;

    public Face (Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);
        randomize();
    }

    public void setRand(){
        randomize();
    }

    public void randomize()
    {

        Random random = new Random();

        hairRed = random.nextInt(256);
        hairGreen = random.nextInt(256);
        hairBlue = random.nextInt(256);

        skinRed = random.nextInt(256);
        skinGreen = random.nextInt(256);
        skinBlue = random.nextInt(256);

        eyeRed = random.nextInt(256);
        eyeGreen = random.nextInt(256);
        eyeBlue = random.nextInt(256);

        setHairColor(hairRed, hairGreen, hairBlue); //call set methods with random integers in range
        setSkinColor(skinRed, skinGreen, skinBlue);
        setEyeColor(eyeRed, eyeGreen, eyeBlue);
        hairStyle = random.nextInt(4) + 1;
    }


    @Override
    public void onDraw(Canvas canvas)
    {
        drawFace(skinColor, canvas);
        drawHair(hairColor, canvas);
        drawEyes(eyeColor, canvas);
    }

    public void drawFace(int color, Canvas canvas){ //draw head
        int width = this.getWidth();
        int height = this.getHeight();
        RectF rect = new RectF((width/2)-300, (height/2)-350,(width/2)+300,(height/2)+350);
        Paint facePaint = new Paint();
        facePaint.setColor(color);
        canvas.drawOval(rect,facePaint);
    }
    public void drawHair(int color, Canvas canvas){ //draw hairstyle depending on hairStyle int value
        int width = this.getWidth();
        int height = this.getHeight();
        if(hairStyle == 1) {
            RectF rectF = new RectF((width / 2) - 300, (height / 2) - 350, (width / 2) + 300, (height / 2) + 250);
            Paint facePaint = new Paint();
            facePaint.setColor(color);
            canvas.drawArc(rectF, 180, 180, true, facePaint);
        }
        else if(hairStyle == 2){
            RectF hair = new RectF((width/2)-350, (height/2)-350,(width/2)+350,(height/2)+250);
            Paint facePaint = new Paint();
            facePaint.setColor(color);
            canvas.drawArc (hair, 180, 180, true, facePaint);
            RectF leftHair = new RectF((width/2)-350, (height/2)-80, (width/2)-250, (height/2)+200);
            canvas.drawRect(leftHair, facePaint);
            RectF rightHair = new RectF((width/2)+250, (height/2)-80, (width/2)+350, (height/2)+200);
            canvas.drawRect(rightHair, facePaint);
        }
        else{
            RectF hair = new RectF((width/2)-350, (height/2)-350,(width/2)+350,(height/2)+250);
            Paint facePaint = new Paint();
            facePaint.setColor(color);
            canvas.drawArc (hair, 180, 180, true, facePaint);
            RectF leftHair = new RectF((width/2)-350, (height/2)-80, (width/2)-250, (height/2)+300);
            canvas.drawRect(leftHair, facePaint);
            RectF rightHair = new RectF((width/2)+250, (height/2)-80, (width/2)+350, (height/2)+300);
            canvas.drawRect(rightHair, facePaint);
        }
        this.invalidate();
    }

    public void drawEyes(int color, Canvas canvas){
        int width = this.getWidth();
        int height = this.getHeight();
        Paint facePaint = new Paint();
        facePaint.setColor(color);
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        canvas.drawCircle((width/2)-150, height/2, 40, whitePaint); //outer eye (color constant)
        canvas.drawCircle((width/2)+150, height/2, 40, whitePaint);
        canvas.drawCircle((width/2)-150, height/2, 20, facePaint); //iris (color changes)
        canvas.drawCircle((width/2)+150, height/2, 20, facePaint);
    }

    public void setSkinColor(int red, int green, int blue) //converting three integers to one color value
    {
        red = (red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        green = (green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        blue = blue & 0x000000FF;
        skinColor = 0xFF000000 | red | green | blue;
        this.invalidate();
    /*
    External citation
    problem: how to convert all three ints as one int for skin color
    solution: found examply from this website
    https://stackoverflow.com/questions/18022364/how-to-convert-rgb-color-to-int-in-java
     */
    }

    public void setHairColor(int red, int green, int blue) {
        red = (red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        green = (green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        blue = blue & 0x000000FF;
        hairColor = 0xFF000000 | red | green | blue;
        this.invalidate();
    }

    public void setEyeColor(int red, int green, int blue) {
        red = (red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        green = (green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        blue = blue & 0x000000FF;
        eyeColor = 0xFF000000 | red | green | blue;
        this.invalidate();
    }

    public void setHairStyle(String string){ //sets hair style integer depending on Spinner string
        if(string.equals("Short Hair")){
            hairStyle = 1;
        }
        else if(string.equals("Medium Hair")){
            hairStyle = 2;
        }
        else{
            hairStyle = 3;
        }
    }

}
