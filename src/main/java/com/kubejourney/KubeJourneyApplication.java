package com.kubejourney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class KubeJourneyApplication {

	public static void main(String[] args)throws UnknownHostException {
		String logFileName = "ms-kubejourney";
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		System.setProperty("log.file.name", logFileName + "-" + hostAddress);
		SpringApplication.run(KubeJourneyApplication.class, args);
	}

}
