echo off 
set path=C:\Program Files\Java\jdk-14.0.2\bin
set include=C:\Program Files\Java\jdk-14.0.2\include 
set lib=C:\Program Files\Java\jdk-14.0.2\lib 
set link=C:\Program Files\Java\jdk-14.0.2\bin 
javac -version Lab_1.java 
java Lab_1 
javap -c Lab_1>1.txt
javadoc Lab_1.java -d 1
pause 

