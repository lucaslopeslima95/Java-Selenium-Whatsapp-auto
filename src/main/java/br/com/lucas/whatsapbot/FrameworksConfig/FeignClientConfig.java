//package br.com.lucas.whatsapbot.FrameworksConfig;
//
//import br.com.lucas.whatsapbot.ViewDesktop.ControllerDesktopMethods.MessageSenderClient;
//import feign.Client;
//import feign.Feign;
//import feign.Logger;
//import feign.gson.GsonDecoder;
//import feign.gson.GsonEncoder;
//import okhttp3.OkHttpClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//@Configuration
//public class FeignClientConfig {
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//
//    @Bean
//    public MessageSenderClient messageSenderClient() {
//        return Feign.builder()
//                .client((Client) new OkHttpClient())
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())
//                .target(MessageSenderClient.class, "http://localhost:8080");
//    }
//}
