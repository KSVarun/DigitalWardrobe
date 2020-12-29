from django.db import models

# Create your models here.
class Restaurant(models.Model):
    name = models.CharField(max_length=400)
    created_at = models.DateTimeField(auto_now=True)
    r_type = models.CharField(max_length=100,default="")
    weather = models.CharField(max_length=100,default="")


    def __str__(self):
        return self.name

    class Meta:
        ordering=("-checkins",)