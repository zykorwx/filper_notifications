#encoding:utf-8
from django.shortcuts import render_to_response
from django.template import RequestContext
from django_push.views import *
from django_push.notificationsHelper import *

# Mostrara un html muy sencillo
def indexTest(request):
        return render_to_response('indexTest.html', context_instance=RequestContext(request))



# Nos servira para enviar notificaciones desde nuestro servidor
def enviaNotificacion(request):
    if (request.GET != {}):
        auxDatos = request.GET
        sendNotification(auxDatos["to"], auxDatos["from"], auxDatos["activity"], auxDatos["message"], auxDatos["title"])
    	return HttpResponse(json.dumps({"success": "Se ha guardado tu comentario"}), content_type="application/json")
    else:
        return HttpResponse(json.dumps({"error": "No se envio"}), content_type="application/json")


# Leemos mensajes push
def leeMensajes(request):
	if (request.GET != {}):
		auxDatos = request.GET
		return HttpResponse(getNotification(auxDatos["to"]), content_type="application/json")
	else:
		return HttpResponse(json.dumps({"error": "Sin resultados"}), content_type="application/json") 