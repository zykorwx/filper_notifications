from django.conf.urls import patterns, url, include


urlpatterns = patterns('',
	url(r'^changeUserStateConexion/$', 'django_push.views.changeUserStateConexion'),
)