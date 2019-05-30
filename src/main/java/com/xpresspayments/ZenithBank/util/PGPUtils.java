package com.xpresspayments.ZenithBank.util;





import org.bouncycastle.openpgp.*;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class PGPUtils {

//    @Value("${zt.public_key}")
//    private String publickey;
//
//    @Value("${zt.private_key}")
//    private String privatekey;






    public String encryptMessage(String data, String fileName) throws IOException, PGPException {

    // InputStream fis = new FileInputStream (new File("src/main/resources/PPZenPub.asc"));
    System.out.println("::: public key :: "+fileName);
    InputStream fis = new FileInputStream(new File(fileName));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //convert the data to input stream
        InputStream stream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));


        //encrypt it!
        SimplePgpUtil.encryptStream(out, SimplePgpUtil.readPublicKey(fis), stream);

        //close all the stream
        out.close();
        stream.close();
        return new String(out.toByteArray());

    }




    public String decryptMessage(String message,String fileName) throws Exception {

    // trim it, just to be safe

        message = message.trim();
    System.out.println(message);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //convert encrypted message to input stream
        ByteArrayInputStream bais = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));

     //   InputStream fis = new FileInputStream (new File("src/main/resources/0x8A2F107B-privateKey.asc"));

    InputStream fis = new FileInputStream(new File(fileName));

        // the real action. 'password1' is the passphrase
        SimplePgpUtil.decryptFile(bais, baos, fis, new String("welcome@123").toCharArray());

        //close all the stream
        baos.close();
        bais.close();
        fis.close();

        //return the decrypted message
        return new String(baos.toByteArray());

    }


}




