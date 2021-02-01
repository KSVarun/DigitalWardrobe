import json

from django.contrib.auth import authenticate
from django.core import serializers

from django.http.multipartparser import MultiPartParser
from django.shortcuts import render

# Create your views here.
import os
from django.contrib.auth.models import User, Group, models
from djongo.database import DatabaseError
from rest_framework import viewsets
from rest_framework import permissions
from rest_framework.decorators import api_view
from rest_framework.views import exception_handler

from digitalwardrobe.api.models import Clothes
from digitalwardrobe.api.serializers import UserSerializer, GroupSerializer, ClothesSerializer
from django.http import JsonResponse
from digitalwardrobe.image_background_remove_tool.main import cli
import digitalwardrobe.clothing_attributes_detection.app.main as cad
from django.http import JsonResponse, HttpResponse, HttpResponseBadRequest
from digitalwardrobe.image_background_remove_tool.main import cli


@api_view(['POST'])
def testAPISet(request):
    activity = request.data.get("Activity")
    temperature = request.data.get("Temperature")
    condition = request.data.get("Condition")
    print(activity)
    print(temperature)
    print(condition)

    data = request.data
    user = User.objects.get(username=data["User"])
    gender = user.email

    if gender== "Male":
        if activity == "UNIVERSITY":
            if condition == "RAIN" or condition == "SNOW":
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "Shirt", "Sweater", "Rain Jacket", "Coat"])
            else:
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "Shirt", "Sweater", "Jacket", "Coat"])
        elif activity == "SPORTS_OUTDOOR":
            if condition == "RAIN" or condition == "SNOW":
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "T-shirt", "Sweater", "Rain Jacket"])
            else:
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "T-shirt", "Sweater", "Jacket"])
        elif activity == "WORK":
            if condition == "RAIN" or condition == "SNOW":
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "Shirt", "Suit", "Rain Jacket", "Coat"])
            else:
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "Shirt", "Suit", "Jacket", "Coat"])
    else:
        if activity == "UNIVERSITY":
            if condition == "RAIN" or condition == "SNOW":
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "Dress", "Sweater", "Rain Jacket", "Coat"])
            else:
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "Dress", "Tank Top",  "Sweater", "Jacket", "Coat"])
        elif activity == "SPORTS_OUTDOOR":
            if condition == "RAIN" or condition == "SNOW":
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "T-shirt","Tank Top" "Sweater", "Rain Jacket"])
            else:
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "T-shirt", "Tank Top", "Sweater", "Jacket"])
        elif activity == "WORK":
            if condition == "RAIN" or condition == "SNOW":
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "Shirt", "Suit", "Rain Jacket", "Coat"])
            else:
                cloth = Clothes.objects.filter(Max_temp__gt=temperature).filter(Min_temp__lte=temperature).filter(
                    User=user).filter(Category__in=["Trousers", "Shirt", "Dress", "Jacket", "Coat"])

    #qs_json = serializers.serialize('json', cloth)
    #return HttpResponse(qs_json, content_type='application/json')
    return JsonResponse(list(cloth.values()), safe=False)


@api_view(['POST'])
def calc_travel_items(request):
    temp = request.data.get("Temperature")
    pop = request.data.get("POP")
    print(pop)
    print(temp)
    print("_____________________________________________________________________")
    pop = float(pop)

    data = request.data
    user = User.objects.get(username=data["User"])
    gender = user.email
    print(gender)

    if gender == "Male":
        if pop < 0.3:
            cloth = Clothes.objects.filter(Max_temp__gt=temp).filter(Min_temp__lte=temp).filter(User=user).filter(
                Category__in=["Trousers", "Shirt", "T-shirt", "Sweater", "Jacket", "Coat"])
        else:
            cloth = Clothes.objects.filter(Max_temp__gt=temp).filter(Min_temp__lte=temp).filter(User=user).filter(
                Category__in=["Trousers", "Shirt", "T-shirt", "Sweatshirts", "Rain Jacket", "Coat"])
    else:
        if pop < 0.3:
            cloth = Clothes.objects.filter(Max_temp__gt=temp).filter(Min_temp__lte=temp).filter(User=user).filter(
                Category__in=["Trousers", "T-shirt", "Dress", "Sweater", "Jacket", "Coat"])
        else:
            cloth = Clothes.objects.filter(Max_temp__gt=temp).filter(Min_temp__lte=temp).filter(User=user).filter(
                Category__in=["Trousers", "Tank Top", "Sweatshirts", "Rain Jacket", "Coat"])

    #qs_json = serializers.serialize('json', cloth)
    #print(qs_json)
    #return HttpResponse(qs_json, content_type='application/json')
    return JsonResponse(list(cloth.values()), safe=False)


@api_view(['POST'])
def create_user(request):
    print(request.data)

    try:

        user = User.objects.create_user(request.data.get("userName"), "", request.data.get("password"))
        user.email = request.data.get("gender")
        user.first_name = request.data.get("firstName")
        user.last_name = request.data.get("lastName")
        user.save()
    except DatabaseError:
        return HttpResponseBadRequest("Either user already exists or this action is not permitted")

    return JsonResponse(request.data)


@api_view(['POST'])
def login_user(request):
    print(request.data)
    username = request.data.get("userName")
    password = request.data.get("password")
    user = authenticate(username=username, password=password)
    print(user)
    if user is not None:
        return JsonResponse(request.data)
    else:
        return HttpResponseBadRequest()


@api_view(['POST'])
def predict_attributes(request):
    temp = request.data.get("file")
    print(request.FILES)
    try:
        with open(temp.name, "wb") as file:
            file.write(temp.read())
            file.close()
        cli(temp.name)
        result = cad.test(temp.name)

    finally:
        os.remove(temp.name)

    return JsonResponse(result)


@api_view(['POST'])
def add_clothes(request):
    print(type(request.data))
    data = request.data
    data["User"] = User.objects.get(username=data["User"])
    cloth = Clothes(**data)
    cloth.save()
    return HttpResponse(cloth)


class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer
    permission_classes = []


class GroupViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows groups to be viewed or edited.
    """
    queryset = Group.objects.all()
    serializer_class = GroupSerializer
    permission_classes = [permissions.IsAuthenticated]
