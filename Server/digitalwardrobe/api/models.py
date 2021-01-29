from django.contrib.auth.models import User
from django.db import models


# Create your models here.
class Clothes(models.Model):
    Name = models.CharField(max_length=400)
    User = models.ForeignKey(User, on_delete=models.CASCADE)
    Created_at = models.DateTimeField(auto_now=True)
    Weather = models.CharField(max_length=100, default="all")
    Category = models.CharField(max_length=100, default="")
    Sleevelength = models.CharField(max_length=100, default="")
    Neckline = models.CharField(max_length=100, default="")
    Pattern = models.CharField(max_length=100, default="")
    Skin_exposure = models.CharField(max_length=100, default="")
    Collar = models.CharField(max_length=100, default="")
    Gender = models.CharField(max_length=100, default="")
    Scarf = models.CharField(max_length=100, default="")
    Necktie = models.CharField(max_length=100, default="")
    Placket = models.CharField(max_length=100, default="")
    Image = models.CharField(max_length=100, default="")

    def __str__(self):
        return self.Name
