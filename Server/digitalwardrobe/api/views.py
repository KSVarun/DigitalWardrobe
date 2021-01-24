from django.contrib.auth import authenticate
from django.http.multipartparser import MultiPartParser
from django.shortcuts import render

# Create your views here.
import os
from django.contrib.auth.models import User, Group
from djongo.database import DatabaseError
from rest_framework import viewsets
from rest_framework import permissions
from rest_framework.decorators import api_view
from rest_framework.views import exception_handler

from digitalwardrobe.api.serializers import UserSerializer, GroupSerializer
from django.http import JsonResponse
from digitalwardrobe.image_background_remove_tool.main import cli
import digitalwardrobe.clothing_attributes_detection.app.main as cad
from django.http import JsonResponse, HttpResponse, HttpResponseBadRequest
from digitalwardrobe.image_background_remove_tool.main import cli


def testAPISet(request):
    return JsonResponse({'mystring': "Hello From Bayreuth"})


@api_view(['POST'])
def create_user(request):
    print(request.data)

    try:

        user = User.objects.create_user(request.data.get("userName"), "", request.data.get("password"))
        user.gender = request.data.get("gender")
        user.first_name = request.data.get("firstName")
        user.last_name = request.data.get("lastName")
        user.save()
    except DatabaseError:
        return HttpResponseBadRequest("Either user already exists or this action is not permitted");

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
    temp = request.data.get('clothImage')
    print(type(temp))
    print(temp)
    try:
        with open(temp.name, "wb") as file:
            file.write(temp.read())
            file.close()
            cli(temp.name)
            result = cad.test(temp.name)

    finally:
        os.remove(temp.name)

    return JsonResponse(result)


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
