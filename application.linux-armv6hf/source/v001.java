import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.awt.image.BufferedImage; 
import java.awt.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class v001 extends PApplet {




PImage screenShot;
PGraphics imgOut;

int pNum = 0;
int w, h;

public void setup() {
  //size(screen.width, screen.height);
  frameRate(6); //slices per second
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  w = (int) screenSize.getWidth();
  h = (int) screenSize.getHeight();
  imgOut = createGraphics(w, h);
  imgOut.beginDraw();
}

public void draw () {
  //image(screenShot,0,0, width, height);
  screenShot = getScreen(pNum);
  imgOut.image(screenShot, pNum, 0);
  pNum++;//add 1 pixel every frame
  if (pNum > w) { //screen.width
    imgOut.endDraw();
    imgOut.save("v001_" + day() + "_" + month() + "_" + year() + "_" + hour() + "_" + minute() + ".tiff"); 
    println("SAVED");  
    pNum = 0;
    imgOut = createGraphics(w, h);
    imgOut.beginDraw();
  }
  if(pNum % 100 == 0){
    println(pNum + "/" + w);
  }
}

//found online, referenced here: https://forum.processing.org/one/topic/get-screencapture.html
public PImage getScreen(int pPos) {
  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  GraphicsDevice[] gs = ge.getScreenDevices();
  DisplayMode mode = gs[0].getDisplayMode();
  Rectangle bounds = new Rectangle(pPos, 0, 1, mode.getHeight());
  BufferedImage desktop = new BufferedImage(mode.getWidth(), mode.getHeight(), BufferedImage.TYPE_INT_RGB);

  try {
    desktop = new Robot(gs[0]).createScreenCapture(bounds);
  }
  catch(AWTException e) {
    System.err.println("Screen capture failed.");
  }

  return (new PImage(desktop));
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "v001" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
