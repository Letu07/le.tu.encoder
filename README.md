# le.tu.encoder

1.What I have managed to do:
*Create a FileService class that has methods for file reading and writing,
as well as a method for creating a new file with a designated name.
*Create a Runner class for program processing.
*Create a Constants class to store the English and Ukrainian alphabets.
*Use an enumeration called Command to store necessary commands.
 
2.What I did NOT manage to do from the basic requirements:
*I was unable to decrypt the text using statistical analysis. 

3.Project features:
*The programme encrypts and decrypts text using a key.
*Can also utilize brute force to select the key for decryption. 
*The program creates a new file with the name of the selected text handling method, 
where it contains the result.

4.What interesting solutions have been implemented:
*I managed to decrypt the text using the brute-force approach, 
focusing on the frequent occurrence of the article "the" in English language.

5.What a mentor should pay attention to when checking:
*The Constants class includes an unused Ukrainian alphabet. 
Due to time constraints, I could not implement support for this language. 
*Have I succeeded in writing concise code?
