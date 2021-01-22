from django.http.multipartparser import MultiPartParser
from django.shortcuts import render

# Create your views here.
import os
from django.contrib.auth.models import User, Group
from rest_framework import viewsets
from rest_framework import permissions
from rest_framework.decorators import api_view

from digitalwardrobe.api.serializers import UserSerializer, GroupSerializer
from django.http import JsonResponse
from digitalwardrobe.image_background_remove_tool.main import cli
import digitalwardrobe.clothing_attributes_detection.app.main as cad


def testAPISet(request):
    return JsonResponse({'mystring': "Test API"})


@api_view(['POST'])
def predictAttributes(request):
    temp = request.data.get('file')
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
