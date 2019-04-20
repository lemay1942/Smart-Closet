#include <ESP8266WiFi.h>
#include "PubSubClient.h"
//#include <DHT11.h>

char ssid[]="seminar_guest02";
char password[]="guest01!";

byte server1[] = {}; // MQTT 브로커 IP
int port=1883;
//DHT11 dht11(4); // 4번핀

WiFiClient client;

void msgReceived(char *topic, byte *payload, unsigned int uLen){
  char pBuffer[uLen+1]; // 데이터 담는 배열
  // c언어는 null문자를 만나면 문자열 끝
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
PubSubClient mqttClient(server1, port, msgReceived, client); 

void setup() {
  // put your setup code here, to run once:
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
  if (mqttClient.connect("Arduino")) { // MQTT브로커에 접속 시도
    Serial.println("MQTT Broker Connected!");
    mqttClient.subscribe("led");
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  mqttClient.loop();
}
