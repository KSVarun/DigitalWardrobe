from django.contrib.auth.models import User, Group
from rest_framework import serializers

from digitalwardrobe.api.models import Clothes


class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ['url', 'username', 'email', 'groups']


class GroupSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Group
        fields = ['url', 'name']

class ClothesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Clothes
        fields = ['Name','created_at','Username', 'Weather', 'Sleevelength', 'Category', 'Neckline',
                  'Placket','Pattern', 'Skin_exposure','Collar', 'Gender','Scarf', 'Necktie']











