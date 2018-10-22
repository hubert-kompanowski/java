package main.java.zad5;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.Properties;

public class EmailMessage {
    private String from; //required (must be e-mail)
    private LinkedList<String> to; //required at least one (must be e-mail)
    private String subject; //optional
    private String content; //optional
    private String mimeType;  // optional
    private LinkedList<String> cc; //optional
    private LinkedList<String> bcc; // optional
    private static Properties mailServerProperties;
    private static Session getMailSession;
    private static MimeMessage generateMailMessage;

    private EmailMessage(Builder builder){
        this.from=builder.from;
        this.to=builder.to;
        this.subject=builder.subject;
        this.content=builder.content;
        this.mimeType=builder.mimeType;
        this.cc=builder.cc;
        this.bcc=builder.bcc;

    }

    public static class Builder {
        private String from; //required (must be e-mail)
        private LinkedList<String> to; //required at least one (must be e-mail)
        private String subject; //optional
        private String content; //optional
        private String mimeType;  // optional
        private LinkedList<String> cc; //optional
        private LinkedList<String> bcc; // optional

        private Builder(){
            to = new LinkedList<>();
            cc = new LinkedList<>();
            bcc = new LinkedList<>();
        }


        public Builder from(String from){
            this.from = from;
            return this;
        }

        public Builder to(LinkedList<String> to){
            this.to.addAll(to);
            return this;
        }

        public Builder to(String to){
            this.to.add(to);
            return this;
        }

        public Builder subject(String subject){
            this.subject=subject;
            return this;
        }

        public Builder content(String content){
            this.content=content;
            return this;
        }

        public Builder mimeType(String mimeType){
            this.mimeType=mimeType;
            return this;
        }

        public Builder cc(LinkedList<String> cc){
            this.to.addAll(cc);
            return this;
        }

        public Builder bcc(LinkedList<String> bcc){
            this.to.addAll(bcc);
            return this;
        }

        public EmailMessage build(){
            if(from == null || from.isEmpty()){
                throw new IllegalArgumentException("Błędny e-mail z");
            }

            if(to.size()<1){
                throw new IllegalArgumentException("Błędny e-mail do");
            }

            if(subject==null){
                subject="";
            }

            if(content==null){
                content="";
            }

            if(mimeType==null){
                mimeType="";
            }


            return new EmailMessage(this);
        }

    }


    public static Builder builder() {
        return new Builder();
    }


    public void send() throws AddressException, MessagingException {

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        for(String i:to){
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(i));
        }
        generateMailMessage.setSubject(subject);
        generateMailMessage.setContent(content, mimeType);

        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", from, "xxx");                        //         <<<<<<<<< HASŁO TUTAJ !!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }


}