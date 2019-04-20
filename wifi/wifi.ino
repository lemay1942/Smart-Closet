#include "PubSubClient.h"
#include "dhtReceiver.h"
#include <ESP8266WiFi.h>

//DHT11 dht11(4); // 4번핀

byte server1[] = {192, 168, 211, 64}; // MQTT 브로커 IP
int port=1883;

void msgReceived(char *topic, byte *payload, unsigned int uLen){
  char pBuffer[uLen+1]; // 데이터 담는 배열
  int i;
  for (i=0; i<uLen; i++){
    pBuffer[i] = payload[i];
  }
  pBuffer[i] = '\0';
  Serial.println(pBuffer);
  if (pBuffer[0] == '1'){
    digitalWrite(14, HIGH);
  }else if (pBuffer[0] == '2'){
    digitalWrite(14, LOW);
  }
} 

WiFiClient client;
PubSubClient mqttClient(server1, port, msgReceived, client); 

//wifiSetup();

void loop() {
  if (mqttClient.connect("Arduino")) { // MQTT브로커에 접속 시도
    Serial.println("MQTT Broker Connected!");
    
    //mqttClient.publish("dht11", );
    //Serial.print("data published : ", );
  }
}
