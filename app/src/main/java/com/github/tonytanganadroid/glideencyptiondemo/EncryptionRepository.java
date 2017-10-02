package com.github.tonytanganadroid.glideencyptiondemo;

public interface EncryptionRepository {

    /**
     * @param anyText the plain text to be encrypted
     * @return the encrypted AND base 64 encoded cypher text.
     */
    String encrypt(String anyText);

    /**
     * @param encryptedTextInBase64Format the encrypted AND base 64 encoded cypher text.
     * @return the raw text that has been encrypted.
     */
    String decrypt(String encryptedTextInBase64Format);

}