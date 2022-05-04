package com.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
    @Autowired
   private JavaMailSender javaMailSender;
    
    @Async //el hilo de ejecucion no espera a que se termine de enviar el mail, lo ejecuta en un hilo paralelo
                //por eso el usuario tiene respuesta inmediata, no tiene que esperar a que se termine d enviar el mail para tener respuesta
                //la opreacion de enviar un mail requiere tiempo y es probable que genere esperar inactivas del usuario, generando poc usabilidad y una mala experencia de usaurio
                //AL declararlo como asincrona, se genera un nuevo hilo de ejecucion para enviar el mail y el usuario tiene una respuesta inmediata
    public void mensajeBienvenida( String mail, String nombre){
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(mail); //correo a donde se envia el mail
        mensaje.setFrom("noreply@ecommerce.com");
        mensaje.setSubject("Bienvenido al ecommerce");
        mensaje.setText("Bienvenido "+nombre+" al eccomerce!");
        
        javaMailSender.send(mensaje);
    }
    
}
