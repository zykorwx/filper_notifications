from django.conf.urls import patterns, include, url

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('',
	url(r'', include('django_push.urls')),
	url(r'^$', 'test_push.views.indexTest'),
    url(r'^enviaNotificacion$', 'test_push.views.enviaNotificacion'),
    url(r'^leeMensajes$', 'test_push.views.leeMensajes'),
)

# Esta linea hace que en modo produccion o trabajando con el wsgi funcionen
# los static files
from django.contrib.staticfiles.urls import staticfiles_urlpatterns
urlpatterns += staticfiles_urlpatterns()
