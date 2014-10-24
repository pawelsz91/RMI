1. How to run.
In my project I was able only to finish RMI with the services required. I was
unable to make it run with servlets using Tomcat. For some reason I couldn't
dynamicly bind interface to the client, even that if I looked up the registry
there was clearly ServiceServer running...
However, I created RMI services that can be easly runed and tested, also the 
client has a GUI.
To run the application:
- open command prompt
- go to the directory where is ds.jar
- type in "java -cp ./ds.jar ie.gmit.ServiceServerImpl" (this will start 
service server)
- then open another command prompt, go to the same directory
- type in "java -cp ./ds.jar ie.gmit.ServiceBrowser"
- this will open up GUI that will help you browse through services implemented

Application has basic features - encryption/commpresion, also there are 2 test 
features I've used to test before doing other features(roll dice/day of the week)
There are comments in the code if you would like to take a look.