
from django.contrib import admin
from django.urls import include, path
from rest_framework import routers
from digitalwardrobe.api import views

router = routers.DefaultRouter()
router.register(r'users', views.UserViewSet)
router.register(r'groups', views.GroupViewSet)

urlpatterns = [
    path(r'get/', views.testAPISet)
]