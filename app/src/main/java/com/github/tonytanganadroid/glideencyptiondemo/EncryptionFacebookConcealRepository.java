package com.github.tonytanganadroid.glideencyptiondemo;


import android.content.Context;
import android.util.Base64;

import com.facebook.android.crypto.keychain.AndroidConceal;
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.CryptoConfig;
import com.facebook.crypto.Entity;
import com.facebook.crypto.keychain.KeyChain;

import hugo.weaving.DebugLog;

@DebugLog
public class EncryptionFacebookConcealRepository implements EncryptionRepository {

    public static final String DEMO_ENTITY_NAME = "mytext";
    private final Context context;

    public EncryptionFacebookConcealRepository(Context context) {
        this.context = context;
    }

    /**
     * @param plainText the plain text to be encrypted
     * @return the encrypted AND base 64 encoded cypher text.
     */
    @Override
    public String encrypt(String plainText) {
        KeyChain keyChain = new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
        Crypto crypto = AndroidConceal.get().createDefaultCrypto(keyChain);
        if (!crypto.isAvailable()) {
            throw new RuntimeException("Facebook Conceal Library Error");
        }
        try {
            byte[] plainTextBytes = plainText.getBytes("UTF-8");
            byte[] cipherText = crypto.encrypt(plainTextBytes, Entity.create(DEMO_ENTITY_NAME));
            return Base64.encodeToString(cipherText, Base64.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException("Facebook Conceal Library Error");
        }
    }


    /**
     * @param cipherTextAsEncodedBase64String the encrypted AND base 64 encoded cypher text.
     * @return the plain text
     */
    @Override
    public String decrypt(String cipherTextAsEncodedBase64String) {
        KeyChain keyChain = new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
        Crypto crypto = AndroidConceal.get().createDefaultCrypto(keyChain);
        if (!crypto.isAvailable()) {
            throw new RuntimeException("Facebook Conceal Library Error");
        }
        try {
            byte[] cipherTextBytes = Base64.decode(cipherTextAsEncodedBase64String, Base64.DEFAULT);
            byte[] decryptedText = crypto.decrypt(cipherTextBytes, Entity.create(DEMO_ENTITY_NAME));
            return new String(decryptedText, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Facebook Conceal Library Error");
        }

    }


}
