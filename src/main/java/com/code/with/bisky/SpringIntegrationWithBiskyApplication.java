package com.code.with.bisky;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.encryptor.SimpleAsymmetricConfig;
import com.ulisesbocchio.jasyptspringboot.encryptor.SimpleAsymmetricStringEncryptor;
import com.ulisesbocchio.jasyptspringboot.util.AsymmetricCryptography;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.code.with.bisky.*","org.springdoc"})
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableEncryptableProperties
public class SpringIntegrationWithBiskyApplication implements CommandLineRunner {

	@Value("${test.password.encryption}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationWithBiskyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		System.out.println("THE DECRYPTED PASSWORD IS "+password);
		encryptionDecryption();
	}

	public  void encryptionDecryption(){

		SimpleAsymmetricConfig simpleAsymmetricConfig=new SimpleAsymmetricConfig();
		simpleAsymmetricConfig.setKeyFormat(AsymmetricCryptography.KeyFormat.PEM);
		simpleAsymmetricConfig.setPublicKey("-----BEGIN PUBLIC KEY-----\n" +
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1hrlFagwAybU4A7tnDNp\n" +
				"eJ1uC7FKn1NxJhWijx5n//T1fxyYbFa5BRzi4MVGnaKzR9DD5r5wcfpo90jrsmXs\n" +
				"JK4sXSZwRb0/YhcVih1RtHr+aHOumFqr5+GsdFtsFjck32iUIJ4eraB3baYZm1ko\n" +
				"wBqH9ZHwMKofWyn1H3e48TZ8aOxFMC1iT8wysrGG0TiZXu0pQNcMo0F+i+WN+tKZ\n" +
				"bL7Ufue5OxEVQPS3k8iaLFCUF4Cbda8U+oqjrc5erEwbMhGDow8aRx2gGN/4kAIw\n" +
				"zilaONY4NQvONL+6LOTCuWN8vJA21drqy7ZENTfWibVJto8YQQcG3zeCXRRr6d4A\n" +
				"VQIDAQAB\n" +
				"-----END PUBLIC KEY-----");
		simpleAsymmetricConfig.setPrivateKey("-----BEGIN PRIVATE KEY-----\n" +
				"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDWGuUVqDADJtTg\n" +
				"Du2cM2l4nW4LsUqfU3EmFaKPHmf/9PV/HJhsVrkFHOLgxUadorNH0MPmvnBx+mj3\n" +
				"SOuyZewkrixdJnBFvT9iFxWKHVG0ev5oc66YWqvn4ax0W2wWNyTfaJQgnh6toHdt\n" +
				"phmbWSjAGof1kfAwqh9bKfUfd7jxNnxo7EUwLWJPzDKysYbROJle7SlA1wyjQX6L\n" +
				"5Y360plsvtR+57k7ERVA9LeTyJosUJQXgJt1rxT6iqOtzl6sTBsyEYOjDxpHHaAY\n" +
				"3/iQAjDOKVo41jg1C840v7os5MK5Y3y8kDbV2urLtkQ1N9aJtUm2jxhBBwbfN4Jd\n" +
				"FGvp3gBVAgMBAAECggEAOqDevlFx5EsL29b6pd1GMmsZqUYBIyPcRguCmiI/ZE2V\n" +
				"tBsGehQ9UfMZBWe/8IaPBUlbe6IGdI+Xkq97KzzRrzGqoE9YSyCJbLjZgxqINx2+\n" +
				"8KdM1hduH28ipDIspOCwziR6+3uQ9waL7ZCgTnPZvtEFDe/Kj+jl8TbKErXcBODg\n" +
				"Kxt66dEQ7CPryLbjGDvBQqgQuaSOd/gkw8k4Fykag7jvOdw+HpLRsSRzwIKZSiHC\n" +
				"YZ6dvkkmqRacKAlCA2tVZag+bNKqXjVUqwLEAsKXlWXs+n8SIykF+m1Ld6KRxCUt\n" +
				"hrvtmOgtkg6J4m4te/rTT2dJdj/Fn/nGRPUqnA6FeQKBgQD6TYWSPSIJF82tzM4Z\n" +
				"zPhWfJd+UYV4Eii1GJJOC3XYpzdsNeN4Rgdq/0OlOegoQ0dOkRqtfwTk5EJ4y2/U\n" +
				"fB/VF0RTTfzWbyFqh01iCrmjvJGNU1YODnQKlNiUItTET3vEz+zMnieMyPyFYp3f\n" +
				"MMfSy4RhhfDH4XEiyESivyElfwKBgQDa+nQ40oU34OFG5Kbqlv9ViXHV7wsqp+Iy\n" +
				"0BrnmcFTdhSlYp2aW9Ftt2eQyE++VPNLz3pWbXrJNE5P74BgLquQLtz+RoFmqpkj\n" +
				"9fMIj9wElMf0JdXNgqvVQ5cQ+ItlDffJGcfX0Wx2maXcXa846DrDNEUaOL59TfPu\n" +
				"rXDt+1VMKwKBgBzGOKq9GGKPN/fY6YCRzaKzo/7DhGJvQ/q9nmQWcS+82WQ5NbMn\n" +
				"6cZlHfOqmoO2aEYh7D0xsvBVRUb5rBtzQX2PtS0WdBB6qg7DCyRQM1MdZGYoxGXN\n" +
				"R43H2rX84xTGRmYzuPc03zsSB+WVlqyHwRD8kHVdnnZ79jOXbjx/WGIlAoGAI0cB\n" +
				"/j18FLp30HH5qgVVcTRJUIvIIsEVIdaA3xrYGFgHj3VJqppsLN7FIoACyMjqwSXV\n" +
				"saLFizCQBTSyW8xo+ztxgs1c/2swbKis1I5IhbxqUhzeStNoV10iMyu3WTpc1tr+\n" +
				"ZPhqHtk17UBygBp5jQUYtNa7aXhreeIbNVuuLC0CgYEAgUiCccWDMtYGc/8CMAlX\n" +
				"jqizHsgW1gsoS3fx/MPy0u7SfHITtnE9Jn8Eip+aR9+JFpT9+m1ElJYNY3ywc0NM\n" +
				"A1F0sbn7goldo918H0OyYZJUDCam2fklifRAKySSMaEF7pPZQ9uZbGXXK4Es1zAu\n" +
				"bjNnfs7+X9OAXKOWhGAoB1E=\n" +
				"-----END PRIVATE KEY-----");

		StringEncryptor stringEncryptor= new SimpleAsymmetricStringEncryptor(simpleAsymmetricConfig);
//		System.out.println(stringEncryptor.encrypt("123456789"));


		System.out.println(stringEncryptor.decrypt("FOS1T53oMZrkmegz3k3AMQN09D2Se/BHzr9WXjz+HTzfKK6PorSgly40F/B2NbZNhJV4EH7gt1UBW1iJMlflfitEjLgg84Osni7NVHpgce9dyf64nOtBwL6dYN239WGDvNDPpGJBKYpO/FGqQLgZvIAR0qYO7EydjUGqEJzlsnSasqdNQj/q/weqXa8k0oXft5BmUNFLo7SfQIPxy38pb6K0f/oO02rBYRs34MwHACKeYUBx5RuThlobudB+pZ13ZH6cvZj2qSaM1KoCPh8oeQ9KO80pSBBlZKd7QAwV7DQ/LvAdvwRtFPC7L5CVqgEJB8IJm3L5uOBkAVEyHmcrDw=="));


	}
}
