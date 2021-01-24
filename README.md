# DigitalWardrobe

#Requirements:
Check requirements.txt file for python requirements

#Anuj statements
Install the latest stable versions of these: once the project is stable we will fix the versions of these libraries

Python 3
django-rest-framework : REST framework in python\
djongo : Django mongodb ORM connector\
mongodb(community edition) : Database


*Start the django-server, mongo-db*: \

considering your mobile and python server are running on same local network\
Steps:
1) Change the API_BASE_URL in android strings.xml in android project, 
2) The url is your inet ipv4 address, ifconfig/ipconfig will tell you that, and port is 8000
3) Execute the following commands: \
```*$python manage.py migrate* ``` \
this command will create the data model in the mongodb \
it will also ask you to run makemigrations, but ignore it

4) to run the python server use the following command from now on, or you can make changes in your configurations accordingly \

```$ python manage.py runserver 0.0.0.0:8000```


Before using the functionality of add clothes please make sure you have created a user using the feature in application.

Add clothes Api can be used by url *http://localhost:8000/add_clothes/* use postman for now, the functionality has  \ 
not yet been implemented on the Android front-end A sample data is posted below to add clothes. /
Feel free to make changes to the data base to extend the functionality.
```json5
 {   
    "Name": "Sleeveless Yellow dress",
    "User": <*your created username here*>,
    "Weather": "Summer",
    "Category": "Dress",
    "Sleevelength": "No sleeves",
    "Neckline": "Other shapes",
    "Pattern": "Solid",
    "Skin_exposure": "sees high skin exposure",
    "Gender": "Female",
    "Image": "red-dress-AL-60094-a.jpg"
}
```





#Akshay statements

To generate the image background removing model run the following commands
Execute the setup file in the mentioned folder
...\DigitalWardrobe\Server\digitalwardrobe\image_background_remove_tool\setup.bat
Then choose the u2net model


#Johannes Statements
