
def columnar_transposition_decrypt(ciphertext, key):
    # Remove spaces from ciphertext
    ciphertext = ciphertext.replace(" ", "")
    
    # Determine the number of columns based on the length of the key
    num_cols = len(key)
    
    # Determine the number of rows needed
    num_rows = len(ciphertext) // num_cols
    if len(ciphertext) % num_cols != 0:
        num_rows += 1  # Add an extra row if necessary
    
    # Create a list of column indices sorted by the key's alphabetical order
    sorted_key_indices = sorted(range(len(key)), key=lambda x: key[x])
    
    # Split ciphertext into columns in the sorted key order
    columns = ['' for _ in range(num_cols)]
    col_length = len(ciphertext) // num_cols
    extra_chars = len(ciphertext) % num_cols  # Some columns may be longer if there's an uneven division

    # Fill each column
    idx = 0
    for col_index in sorted_key_indices:
        # Determine the length for this specific column (extra_chars means some columns have +1 char)
        this_col_length = col_length + (1 if col_index < extra_chars else 0)
        columns[col_index] = ciphertext[idx:idx + this_col_length]
        idx += this_col_length

    # Reconstruct the text by reading across rows
    plaintext = []
    for i in range(num_rows):
        for col in columns:
            if i < len(col):  # Only add characters if the row exists in this column
                plaintext.append(col[i])
    
    # Join to form the final plaintext message
    return ''.join(plaintext)

# Given ciphertext and key
ciphertext = "OSLOH IAARS TLSAP SDAAO OLEJP AELLR ORUNE XOPAS VDQAD LONTA PTLSG OCOLE OCLOA RISUD TSIEC L "
key = "NACIONALIZACION"

# Decrypt using columnar transposition
decrypted_text = columnar_transposition_decrypt(ciphertext, key)
print(decrypted_text)
