/*
  8x32 LED Matrix- Max7219 Tutorial with scrolling text
  
  website: :https://earthbondhon.com/8x32-led-matrix-max7219-tutorial-with-scrolling-text/
 */
#include <SoftwareSerial.h>
#include <SPI.h>
#include <Adafruit_GFX.h>
#include <Max72xxPanel.h>
//Vcc - Vcc
//Gnd - Gnd
//Din - Mosi (Pin 11)
//Cs  - SS (Pin 10)
//Clk - Sck (Pin 13)
#define RLED 3
#define GLED 4
#define BLED 5

SoftwareSerial BT(8,9); // 接收腳, 傳送腳
int val;  // 儲存接收資料的變數
//int val;
const int pinCS = 10;
const int numberOfHorizontalDisplays = 8;
const int numberOfVerticalDisplays = 1;
Max72xxPanel matrix = Max72xxPanel(pinCS, numberOfHorizontalDisplays, numberOfVerticalDisplays);
const int wait = 100; // 滾動速度
const int spacer = 1;
const int width = 5 + spacer; // 字體寬度為5像素

void setup(){
   Serial.begin(9600);
   BT.begin(9600);
   pinMode(RLED,OUTPUT);
   pinMode(GLED,OUTPUT);
   pinMode(BLED,OUTPUT);
   matrix. setIntensity ( 10 ) ;  // Adjust the brightness between 0 and 15
   matrix. setPosition ( 0 ,  0 ,  0 ) ;  // The first display is at <0, 0>
   matrix. setPosition ( 1 ,  1 ,  0 ) ;  // The second display is at <1, 0>
   matrix. setPosition ( 2 ,  2 ,  0 ) ;  // The third display is in <2, 0>
   matrix. setPosition ( 3 ,  3 ,  0 ) ;  // The fourth display is at <3, 0>
   matrix. setPosition ( 4 ,  4 ,  0 ) ;  // The fifth display is at <4, 0>
   matrix. setPosition ( 5 ,  5 ,  0 ) ;  // The sixth display is at <5, 0>
   matrix. setPosition ( 6 ,  6 ,  0 ) ;  // The seventh display is at <6, 0>
   matrix. setPosition ( 7 ,  7 ,  0 ) ;  // The eighth display is in <7, 0>
   matrix. setPosition ( 8 ,  8 ,  0 ) ;  // The ninth display is at <8, 0>
   matrix. setRotation ( 0 ,  1 ) ;     // Display position
   matrix. setRotation ( 1 ,  1 ) ;     // Display position
   matrix. setRotation ( 2 ,  1 ) ;     // Display position
   matrix. setRotation ( 3 ,  1 ) ;     // Display position
   matrix. setRotation ( 4 ,  1 ) ;     // Display position
   matrix. setRotation ( 5 ,  1 ) ;     // Display position
   matrix. setRotation ( 6 ,  1 ) ;     // Display position
   matrix. setRotation ( 7 ,  1 ) ;     // Display position
   matrix. setRotation ( 8 ,  1 ) ;     // Display position
}
void loop(){
    int tmp=0;
    digitalWrite(RLED, LOW);
    digitalWrite(GLED, LOW);
    digitalWrite(BLED, LOW);
    String string = "Air Quality";
    String state1 = { ":Good" };
    String state2 = { ":Moderate" };
    String state3 = { ":Unhealthy For Sensitive Group" };
    String state4 = { ":Unhealthy" };
    String state5 = { ":Very Unhealthy" };
    String state6 = { ":Hazardous" };   
    long int time = millis();
   
   while(BT.available()){
       val = BT.read();
       //val=Serial.parseInt(value);
       Serial.print(val);
       if(val>=0 && val<=50){
        tmp=1;
        digitalWrite(RLED, HIGH);
        digitalWrite(GLED, LOW);
        digitalWrite(BLED, HIGH);
        delay(1000);
        string += state1;
       }
       else if(val>=51 && val<=100){
        tmp=2;
        digitalWrite(RLED, LOW);
        digitalWrite(GLED, LOW);
        digitalWrite(BLED, HIGH);
        delay(1000);        
        string += state2;
       }
       else if(val>=101 && val<=150){
        tmp=3;
        digitalWrite(RLED, LOW);
        digitalWrite(GLED, HIGH);
        digitalWrite(BLED, LOW);        
        string += state3;
       }
       else if(val>=151 && val<=200){
        tmp=4;
        digitalWrite(RLED, LOW);
        digitalWrite(GLED, HIGH);
        digitalWrite(BLED, HIGH);        
        string += state4;
       }
       else if(val>=201 && val<=300){
        tmp=5;
        digitalWrite(RLED, HIGH);
        digitalWrite(GLED, LOW);
        digitalWrite(BLED, LOW);        
        string += state5;
       }
       else if(val>=301 && val<=400){
        tmp=6;
        digitalWrite(RLED, HIGH);
        digitalWrite(GLED, HIGH);
        digitalWrite(BLED, LOW);        
        string += state6;
       }
   }
   /*if(tmp == 1){//G
    digitalWrite(RLED, HIGH);
    digitalWrite(GLED, LOW);
    digitalWrite(BLED, HIGH);
    delay(1000);
   }
   if(tmp == 2){//Y
    digitalWrite(RLED, LOW);
    digitalWrite(GLED, LOW);
    digitalWrite(BLED, HIGH);
    delay(1000);
   }
   if(tmp == 3){//P
    digitalWrite(RLED, LOW);
    digitalWrite(GLED, HIGH);
    digitalWrite(BLED, LOW);
    delay(1000);
   }
   if(tmp == 4){//R
    digitalWrite(RLED, LOW);
    digitalWrite(GLED, HIGH);
    digitalWrite(BLED, HIGH);
    delay(1000);
   }
   if(tmp == 5){//LB
    digitalWrite(RLED, HIGH);
    digitalWrite(GLED, LOW);
    digitalWrite(BLED, LOW);
    delay(1000);
   }
   if(tmp == 6){//B
    digitalWrite(RLED, HIGH);
    digitalWrite(GLED, HIGH);
    digitalWrite(BLED, LOW);
    delay(1000);
   }*/   
   for(int i = 0; i < width * string.length() + matrix.width() - 1 - spacer; i++){
      matrix.fillScreen(LOW);
      int letter = i / width;
      int x = (matrix.width() - 1) - i % width;
      int y = (matrix.height() - 8) / 2; // 文字置中
      while(x + width - spacer >= 0 && letter >= 0){
         if(letter < string.length()){
             matrix.drawChar(x, y, string[letter], HIGH, LOW, 1);
         }
         letter--;
         x -= width;
      }
      matrix.write(); // 顯示文字
      delay(wait);
   }

}
