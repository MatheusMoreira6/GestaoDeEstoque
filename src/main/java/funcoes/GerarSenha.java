package funcoes;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * @author Matheus
 * @author Gabriel
 */
public class GerarSenha {

    public static String criptografar(String senha) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(senha.getBytes(Charset.forName("UTF-8")));
        byte[] digest = messageDigest.digest();

        StringBuffer retorno = new StringBuffer();
        for (byte b : digest) {
            retorno.append(Integer.toHexString(b));
        }

        return (retorno.toString());
    }

}
