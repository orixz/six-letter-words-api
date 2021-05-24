# six-letter-words-api

The application provides an api endpoint at "/api/file" that accept a text file with words of varying lengths (1-6).
It will look through the input to find 2 word combinations to form a 6 letter word that can also be found in the input.
Additionally each call to this endpoint logs it's result in an H2 embedded DB with a timestamp.

E.g.:
``` 
foobar  
fo  
obar
```
results in the output: 
```
fo+obar=foobar
```

For local testing you can run mvn install and excecute the resulting jar file to start the app.
Navigate to your input file and send it to the application using the following curl command.
  
  > curl -v 'http://localhost:8080/api/file' --data-binary '@input.txt' --header "Content-Type: text/plain" >> output.txt

Read output.txt for results.  
