package com.xwl.DesignPattern.CreationType;

/**
 * @Author: xiewanlin
 * @Date: 2020/4/1
 */
public class FactoryPattern {

  public static void main(String[] args) {
    Provider smsFactory = new SmsFactory();
    Sender smsSender = smsFactory.produce();
    smsSender.send();

    Provider mailFactory = new MailFactory();
    Sender mailSender = mailFactory.produce();
    mailSender.send();
  }
}

interface Sender {
  void send();
}
class MailSender implements Sender {
  @Override
  public void send(){
    System.out.println("send mail");
  }
}
class SmsSender implements Sender {
  @Override
  public void send(){
    System.out.println("send sms");
  }
}

interface Provider {
  Sender produce();
}
class MailFactory implements Provider{
  @Override
  public Sender produce(){
    return new MailSender();
  }
}
class SmsFactory implements Provider{
  @Override
  public Sender produce(){
    return new SmsSender();
  }
}