

Indexation
----------

Folder **data**: each text file contains a text from some web-page. Each text file has **file-name**.  There is a special file **Filename_URL.txt** that contains mapping between URL and file-name of each text file in the folder **data**.

Class **Mapper** has a method *mapCount()* which:

 1. takes as input text files from folder **data**
 2. creates a file **Key_value_pairs.txt** with key-value pairs. **Key** is word and **value** is URL  where this word occurred.
 3. saves this file in "local memory" - folder **Mapper**.

Class **Reducer** has a method *reduceCount()* which:

 1. takes as input the file **Key_value_pairs.txt** from his "local memory" - folder **Reducer**.
 2.  count occurrences of each word in each URL. creates a file **Index.txt** where each line contains Word, URL, number of occurrences of this Word in URL
 3.  save this file in "remote memory" - folder **Index**.


----------
To launch indexation do the following steps:

Step 1

: Run **Server.java**.  Don't forget to specify your paths for **Mapper** and **data** folders.
Server creates an instance of **Mapper** and executes *mapCount().* Then it waits for calls from clients.

Step 2

: Run **Client.java.**  Don't forget to specify your paths for **Reducer** and **Index** folders.
Client request **Key_value_pairs.txt** from Server then save it in folder **Reducer**. Then it creates an instance of **Reducer** and executes *reduceCount().* 


----------

Search a word as a final user
-----------------------------

Once you have the **Index.txt** in **Index** folder you can run **FinalUser.java** (but never before!!! I have not done exception handling for the case when index file does not exist). Server should be running as well.
In prompt you enter a word (for the moment only 1) you want to search and hit ENTER. 
*Enjoy results* :D
