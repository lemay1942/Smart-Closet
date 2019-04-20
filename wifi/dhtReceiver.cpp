#include "dhtReceiver.h"
#include "Arduino.h"
#include <DHT11.h>
#include <ArduinoJson.h>
#include <ESP8266WiFi.h>

char ssid[]="seminar_guest02";
char password[]="guest01!";
//char mqtt_server = "broker.mqtt-dashboard.com";

int pin = 12;  // 핀설정
DHT11 dht11(pin);

void wifiSetup() {
  Serial.begin(115200);
  delay(10);
  Serial.println();
  Serial.println();
  Serial.println("Connection to~");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while(WiFi.status() != WL_CONNECTED){
    delay(500);
    Serial.print(",");
  }
  Serial.println("");
  Serial.println("Wi-Fi AP Connected!");

  Serial.println(WiFi.localIP());
}
 
void serialDataRequest() {
  
  
  int i;     
  float humi, temp; 
  if((i = dht11.read(humi, temp)) == 0) {  // 온도, 습도 값을 읽어오면
//    JSON.parse(humi, temp); //JSON으로 바꿈
//    return 
    Serial.print("humidity:");          // ‘시리얼 플로터’ 사용위해 이부분 주석 필요
    Serial.println(humi);                  // 습도값 출력
    Serial.print("temperature:");       // ‘시리얼 플로터’ 사용위해 이부분 주석 필요
    Serial.println(temp);                  // 온도값 출력 
  } 
  else{
//    return NULL; 
    Serial.print("Error:");                    
    Serial.print(i);                          
  }  
  delay(3000);
} 
