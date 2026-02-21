package com.prueba.PruebaSpring.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.net.URI;

@Component
public class  BrowserLauncher implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String url = "http://localhost:8080 ";

        // Intenta abrir el navegador usando AWT (funciona en Windows, macOS, Linux con GUI)
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(url));
            }
        } else {
            // Método alternativo para entornos sin GUI (Linux headless, etc.)
            Runtime runtime = Runtime.getRuntime();
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                runtime.exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                runtime.exec("xdg-open " + url);
            }
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}