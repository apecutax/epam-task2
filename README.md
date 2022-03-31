# epam-task2

You need to create an application that analyzes text from a programming textbook 
from a file and allows you to perform three different operations with text.

### Requirements:
- The parsed text should be presented as an object (text) containing, for example, 
sentences and code blocks, the sentence may contain the words of the sentence.
The words of a sentence (parts of a sentence) can be, for example, simple words 
and punctuation marks. These classes are entity classes and should not be 
overloaded with logic methods.
- The parsed text must be restored to its original form, with the exception of 
spaces between elements. Spaces and tabs can be replaced with a single space 
during parsing.
- Regular expressions should be used to divide the text into sentences and other 
components. Do not forget that regular expressions for the application are
literal constants.
- The code that splits the text into its component parts should be designed in 
the form of parser classes.
- When developing parsers that parse text, it is necessary to monitor the number 
of parser objects being created.
- When implementing a task, you can use the Composite and Chain of Responsibility 
templates.
- When handling exceptional situations, the application must use the Log4j logger.
- The created application should allow you to implement a group of tasks for 
working on the text (the tasks are listed below) without "rewriting” existing code.

### Options 2, 3 and 4:
- Print all sentences of a given text in ascending order of the number of words in 
each of them.
- Find a word in the first sentence that is not in any of the other sentences.
- In all the interrogative sentences of the text, find and print words of a given 
length without repetition.