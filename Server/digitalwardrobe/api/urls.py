from django.contrib import admin
from django.urls import path, include
from rest_framework import routers
from digitalwardrobe.api import views

router = routers.DefaultRouter()
router.register(r'users', views.UserViewSet)
router.register(r'groups', views.GroupViewSet)

urlpatterns = [
    path(r'createUser/', views.create_user),
    path(r'authenticateUser/', views.login_user),
    path(r'get/', views.testAPISet),
    path(r'predict/', views.predict_attributes),
    path(r'add_clothes/', views.add_clothes),
    path(r'calc_travel/', views.calc_travel_items),
    path(r'get_all_clothes/', views.get_all_clothes),
]
