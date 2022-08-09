# symmetric-cryptographic-system :closed_lock_with_key:
> that encrypts/decrypts all text files in a given folder or single text file

- The general steps to encrypt/decrypt a file in Java are as follows:

1. Get the key from the user. DES algorithm requires a 64-bit key while AES operates on a default key size of 192 bits.

2. Create an instance of the class Cipher for the chosen algorithm transformation. Refer to the document of the
Cipher class for more information regarding supported algorithms and transformations.

3. Initialize the Cipher with an appropriate mode (encrypt or decrypt) and the given Key.

4. Invoke doFinal(input_bytes) method of the Cipher class to perform encryption or decryption on the
input_bytes, which returns an encrypted or decrypted byte array.

5. Read an input file to a byte array and write the encrypted/decrypted byte array to an output file accordingly

>How to Run
------------------------------------------------------
1. Download the zip file and extract the zip file.
2. Open Netbeans and then go to File->Open Project. Then browse the project path.
3. From the project right click on " cryptographic_g_m" file and select "Run File"
4. It will show Main menu on the console with Encrypt,Decrypt and exit .
5. If user select  Encrypt/Decrypt option then it will ask for (1) File (2) Folder
6. when enter file or folder you shoud writing dir for folder or file 
7. in our code we divid the programing in sub method to be easy to debug 
8. if you choice a wrong option from 1- Encrypt, 2- Decrypt and 3-exit
the programing will send to you notification about it like :
 
" Invalid choice ):  Enter choice 1 to Encrypt  or 2 to Decrypt  or 3 to  Exit "

9. if you choice a wrong option from (1) File (2) Folder
the programing will send to you notification about it like :

" Invalid choice ):   Enter choice 1 to File  or 2 to Folder  try  programing agin "



>Reference:
-----------------------------------------------------------------------------------
https://www.youtube.com/watch?v=zn_kg55GRWo&t=341s
https://www.baeldung.com/java-aes-encryption-decryption

-----------------------------------------------------------------------------------

run:

 A SYMMETRIC CRYPTO SYSTEM
======================================= 
MAIN MENU 
------------------------------------- 
1. Encrypt 
2. Decrypt 
3. Exit 
-----------------------------------------

Enter your choice: 1
(1) File (2) Folder 
Enter your choice: 1
Type your file name : G&M.txt //or input url 
Choose the algorithm (AES, DES) : AES
Enter key : aaaaaaaaaaaaaaaaaaaaaaaa
Done! File G&M.txt is encrypted using AES - 192
Output file is G&M.encrypted

 A SYMMETRIC CRYPTO SYSTEM
======================================= 
MAIN MENU 
------------------------------------- 
1. Encrypt 
2. Decrypt 
3. Exit 
-----------------------------------------

Enter your choice: 3
BUILD SUCCESSFUL (total time: 39 seconds)
