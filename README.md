# DigitalWardrobe

#Requirements:
Check requirements.txt file for python 
Installation command: \
```$ pip install -r requirements.txt```
#Backend Server
Python 3.8 \
Database: mongodb(community edition) v4.4.2\
Backend: django-rest-framework : REST framework in python\
ORM: djongo : Django mongodb ORM connector\

#Media Server
Storage Path : Server/image_server\
To start the media server execute the following command inside above mentioned directory\
```$ python -m http.server 8080 ```

#Database
Make sure mongodb is running on the default port

#Starting the Server

*considering your mobile and Django server are running on same local network* \

Steps:
1) Change the API_BASE_URL and MEDIA_SERVER_URL in strings.xml in android project, 
2) The url is your inet ipv4 address, ifconfig/ipconfig will tell you that, and \
 do not change the port in the URLs, since your servers will be running on the same port.
3) Execute the following commands inside the Folder Server: \
```*$python manage.py migrate* ``` \
this command will create the data model in the mongodb \
it will also ask you to run makemigrations, but ignore it

4) to run the python server use the following command inside the folder Server again \

```$ python manage.py runserver 0.0.0.0:8000```

#Generate the Machine learning image processing models:

Execute the setup file in the mentioned folder: \
```...\Server\digitalwardrobe\image_background_remove_tool\setup.bat```

Then choose the u2net model


