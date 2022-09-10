# Stream-API.-Date-and-Time.-I-O-Streams
Practice tasks


Task 1

Write a method to check if a year is a leap year or not, using for this the LocalDate class.

If a year is leap then method should return true, otherwise - false.

Task 2


Write a method to get the date n-years m-months and k-days after today using new DateTime API.

Return the obtained date in the format ISO_LOCAL_DATE.


Task 3


Create the method writeFile(String filename, String text) that write the text to file as sequence of bytes in binary format.

For example, the text fragment

"Hello"
should be represented in the file as a sequence of 7-bit bytes without spaces between them:

100100011001011101100110110011011110100001
If less than 7 bits are required to represent the character then add to binary sequence leading zeros '0'.

Task 4

Create the method readFile(String filename) that read from file a sequence of bytes in binary format from previous task and return ridable string.

For example, the sequence of 7-bit bytes

100100011001011101100110110011011110100001
should be represented as text fragment:
"Hello"


Task 5


Let the key of Map is project name and value contains list of participants.
Create a Stream<String> nameList(Map<String, Stream<String>> map) method of the MyUtils class to build sorted stream of all participants without duplication.
Please ignore null or empty strings, extra spaces and case sensitivity.
Throw NullPointerException if map is null.
For example, for a given map
{"Desktop"=[" iVan", "PeTro ", " Ira "], "Web"=["STepan", "ira ", " Andriy ", "an na"], "Spring"=["Ivan", "Anna"]}
you should get
["Andriy", "Anna", "Ira", "Ivan", "Petro ", "Stepan"]


Task 6


Create a Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) method of the MyUtils class to build a Map of all phone numbers.
The key of Map is code of network and value contains sorted list of phones.
Remove all spaces, brakets and dashes from phone numbers.
For example, for a given
[["093 987 65 43", "(050)1234567", "12-345"], ["067-21-436-57", "050-2345678", "0939182736", "224-19-28"], ["(093)-11-22-334", "044 435-62-18", "721-73-45"]]
you should get
{"050"=["1234567", "2345678"], "067"=["2143657"], "093"=["1122334", "9182736", "9876543"], "044"=["4356218"], "loc"=["2241928", "7217345"], "err"=["12345"]}


Task 7 


Create a Stream<Integer> duplicateElements(Stream<Integer> stream) method of the MyUtils class to return a sorted stream of duplicated elements of the input stream.
For example, for a given elements
[3, 2, 1, 1, 12, 3, 8, 2, 4, 2]
you should get
[1, 2, 3]
