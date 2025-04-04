def vigenere_decrypt(ciphertext, key):
    # Convert ciphertext and key to uppercase and remove spaces for simplicity
    ciphertext = ciphertext.replace(" ", "").upper()
    key = key.upper()

    # Decrypt using Vigenère cipher with the provided key
    decrypted_text = []
    key_length = len(key)
    
    # Loop through each character in ciphertext and decrypt
    for i in range(len(ciphertext)):
        # Convert ciphertext and key characters to their alphabet index (A=0, ..., Z=25)
        c = ord(ciphertext[i]) - ord('A')
        k = ord(key[i % key_length]) - ord('A')
        
        # Vigenere decryption formula: (c - k + 26) % 26
        decrypted_char = (c - k + 26) % 26
        decrypted_text.append(chr(decrypted_char + ord('A')))

    # Join the decrypted characters and return the first 50 characters
    return ''.join(decrypted_text[:50])

# Given ciphertext and key
ciphertext = "HLBES LOJYS VGGIJ CFJJS OWXQC CLOJL PJOKH WRPZR WGIUF GOKSF CFIQS RWLBK WWFJA QRFSC TTFES JRWEM CCKAW TBCSK DJEDZ CKCKC PDCWZ HLBKW FCWNE"
key = "SABROSO"

# Decrypt the first 50 characters
decrypted_text = vigenere_decrypt(ciphertext, key)
print (decrypted_text)