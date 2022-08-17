/*
 This repo contains Ghadah 👍 attempt to solve the project problem */




package cryptographic_g_m;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Cryptographic_G_M {

    public static void main(String[] args) {
        // TODO code application logic here
        MenuChoice(); //استدعاء جملة الطباعة الاساسية 
        //----------------------//
        Scanner input = new Scanner(System.in); //ادخال البيانات من المستخدم  
        int choice;// تعريف قيم الاختسارات بناء على جملة الطباعه 

        while (true) { // while loop
            System.out.print("Enter your choice: ");
            choice = input.nextInt(); // اختيار المستخدم
            if (choice == 1 || choice == 2 || choice == 3) {
                if (choice != 3) {
                    if (choice == 1) {
                        //ENC                       
                        Encrypt_Option();
                    } else {
                        //DEC 
                        Decrypt_Option();

                    }//end else  
                    //اعادة ادخال البيانات 
                    MenuChoice();
                } //نوقف  
                else {
                    break;
                }
            }//big if 
            else {
                //اذا اختيار المستخدم كان خاطئ
                System.out.println(choice + " Invalid choice ): .\t Enter choice 1 to Encrypt \t or 2 to Decrypt \t or 3 to  Exit ");
            }
        }//while loop
    }//main 

    /////////////////////////////////////////////////////////
    //This method is used to show main menu in the console
    private static void MenuChoice() {
        System.out.println(" A SYMMETRIC CRYPTO SYSTEM\n"
                + "======================================= \n"
                + "MAIN MENU \n"
                + "------------------------------------- \n"
                + "1. Encrypt \n"
                + "2. Decrypt \n"
                + "3. Exit \n"
                + "-----------------------------------------\n");
    }
    /////////////////////////////////////////////////////////////

    public static void Encrypt_Option() {
        Scanner input = new Scanner(System.in); //عبارة عن اتاحه اخذ بيانات من المستخدم عشان يختار ملف اومجلد 
        int option = 0;
        String FileFolder = "";
        String algorithm;
        String key = "";

        while (true) {
            System.out.println("(1) File (2) Folder ");
            System.out.print("Enter your choice: ");
            option = input.nextInt();
            if (option == 1 || option == 2) {

                if (option == 1) {
                    System.out.print("Type your file name : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithm = input.next();
                    System.out.print("Enter key : ");
                    key = input.next();
                    if (algorithm.equalsIgnoreCase("AES")) {
                        AES_Encrypt(key, FileFolder);
                    } else if (algorithm.equalsIgnoreCase("DES")) {
                        DES_Encrypt(key, FileFolder);
                    }
                    break;
                } else if (option == 2) {
                    System.out.print("Type your Folder name : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithm = input.next();
                    System.out.print("Enter key : ");
                    key = input.next();
                    File dir = new File(FileFolder);
                    for (File file : dir.listFiles()) {
                        if (file.getName().endsWith("txt")) {
                            if (algorithm.equalsIgnoreCase("AES")) {
                                AES_Encrypt(key, file.getAbsolutePath());
                            } else if (algorithm.equalsIgnoreCase("DES")) {
                                DES_Encrypt(key, file.getAbsolutePath());
                            }

                        }
                    }
                    break;
                }

            } 
            else if(option != 1 || option != 2) {
                System.out.println("Invalid choice ):  \t  Enter choice 1 to File  \t or 2 to Folder  try agin \n");
                Encrypt_Option();
                System.out.println("");
                break;
            }

        }
    }// end Encrypt_Option

    ///////////////////////////////////////////////////////////////////////////////////
    public static void Decrypt_Option() {
        Scanner input = new Scanner(System.in); //عبارة عن اتاحه اخذ بيانات من المستخدم 
        int option = 0;
        String FileFolder = "";
        String algorithm;
        String key = "";
        while (true) {
            System.out.println("(1) File (2) Folder  ");
            System.out.print("Enter your choice: ");
            option = input.nextInt();
            if (option == 1 || option == 2) {

                if (option == 1) {
                    System.out.print("Type your file name : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithm = input.next();
                    System.out.print("Enter  key : ");
                    key = input.next();
                    if (algorithm.equalsIgnoreCase("AES")) {
                        AES_Decrypt(key, FileFolder);
                    } else if (algorithm.equalsIgnoreCase("DES")) {
                        DES_Decrypt(key, FileFolder);
                    }
                    break;
                } else if (option == 2) {
                    System.out.print("Type your Folder name : ");
                    FileFolder = input.next();
                    System.out.print("Choose the algorithm (AES, DES) : ");
                    algorithm = input.next();
                    System.out.print("Enter  key : ");
                    key = input.next();
                    File dir = new File(FileFolder);
                    for (File file : dir.listFiles()) {
                        if (file.getName().endsWith("encrypted")) {
                            if (algorithm.equalsIgnoreCase("AES")) {
                                AES_Decrypt(key, file.getAbsolutePath());//
                            } else if (algorithm.equalsIgnoreCase("DES")) {
                                DES_Decrypt(key, file.getAbsolutePath());
                            }
                        }
                    }
                    break;
                }

            } else if(option != 1 || option != 2) {
                System.out.println("Invalid choice ):  \t  Enter choice 1 to File  \t or 2 to Folder  try agin \n");
                Decrypt_Option();
                System.out.println("");
                break;
            }
        }
    }// end Decrypt_Option

    ///////////////////////////////////////////////////////////////////////////////////
    public static void AES_Encrypt(String key, String input) {
        try {
            File inFile = new File(input);
            File outFile = new File(input.replace(".txt", ".encrypted"));
            Key secretKey = new SecretKeySpec(key.getBytes(), 0, 24, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            FileInputStream inputStream = new FileInputStream(inFile);
            byte[] inputBytes = new byte[(int) inFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outFile);
            outputStream.write(outputBytes);
            outputStream.flush();
            inputStream.close();
            outputStream.close();

            System.out.println("Done! File " + inFile.getName() + " is encrypted using AES - 192");
            System.out.println("Output file is " + outFile.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public static void AES_Decrypt(String key, String input) {
        try {
            File inFile = new File(input);
            File outFile = new File(inFile.getAbsolutePath().replace(".encrypted", ".decrypted"));
            Key secretKey = new SecretKeySpec(key.getBytes(), 0, 24, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            FileInputStream inputStream = new FileInputStream(inFile);
            byte[] inputBytes = new byte[(int) inFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outFile);
            outputStream.write(outputBytes);
            outputStream.flush();
            inputStream.close();
            outputStream.close();

            System.out.println("Done! File " + inFile.getName() + " is decrypted using AES - 192");
            System.out.println("Output file is " + outFile.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public static void DES_Encrypt(String key, String input) {
        try {
            File inFile = new File(input);
            File outFile = new File(input.replace(".txt", ".encrypted"));
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFac.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            FileInputStream inputStream = new FileInputStream(inFile);
            byte[] inputBytes = new byte[(int) inFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = cipher.doFinal(inputBytes);
            FileOutputStream stream = new FileOutputStream(outFile);
            stream.write(outputBytes);
            stream.flush();
            inputStream.close();
            stream.close();

            System.out.println("Done! File " + inFile.getName() + " is Encrypted using DES");
            System.out.println("Output file is " + outFile.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public static void DES_Decrypt(String key, String input) {
        try {
            File inFile = new File(input);
            File outFile = new File(inFile.getAbsolutePath().replace(".encrypted", ".decrypted"));
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = keyFac.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, desKey);

            FileInputStream inputStream = new FileInputStream(inFile);
            byte[] inputBytes = new byte[(int) inFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream stream = new FileOutputStream(outFile);
            stream.write(outputBytes);
            stream.flush();
            inputStream.close();
            stream.close();

            System.out.println("Done! File " + inFile.getName() + " is Decrypted using DES");
            System.out.println("Output file is " + outFile.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////

}//pakage
