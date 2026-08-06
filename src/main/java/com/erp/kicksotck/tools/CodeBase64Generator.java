package com.erp.kicksotck.tools;

import org.springframework.stereotype.Component;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class CodeBase64Generator {
    private static final SecureRandom random = new SecureRandom();

    public String gerarCodigoBase64(int tamanho) {
        byte[] bytes = new byte[tamanho];
        random.nextBytes(bytes);

        String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

        return base64.substring(0, tamanho);
    }

    public String gerarCodigoContrato() {
        return gerarCodigoBase64(10);
    }
}
