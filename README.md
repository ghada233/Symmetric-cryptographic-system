# symmetric-cryptographic-system :closed_lock_with_key:
- that encrypts/decrypts all text files in a given folder or single text file

### The general steps to encrypt/decrypt a file in Java are as follows:

1. Get the key from the user. DES algorithm requires a 64-bit key while AES operates on a default key size of 192 bits.

2. Create an instance of the class Cipher for the chosen algorithm transformation. Refer to the document of the
Cipher class for more information regarding supported algorithms and transformations.

3. Initialize the Cipher with an appropriate mode (encrypt or decrypt) and the given Key.

4. Invoke doFinal(input_bytes) method of the Cipher class to perform encryption or decryption on the
input_bytes, which returns an encrypted or decrypted byte array.

5. Read an input file to a byte array and write the encrypted/decrypted byte array to an output file accordingly
