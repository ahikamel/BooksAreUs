# BooksAreUs
End-to-end project simulating a library website.
Backend side wrriten in Java (Spring Boot) and Frontend side wrriten in React.

Main webpage consists of library's recent news section and a daily book recommendation.
Use the navigation bar at the top to view the book collection page or the recommended books page (by rating).
Clicking any book anywhere on the website, will navigate to the book's specific page.

## How to run the project locally ?
```bash
git clone https://github.com/ahikamel/BooksAreUs.git
```

### Server
Prerequisites -
- Make sure Java and Maven are installed (https://mkyong.com/maven/how-to-install-maven-in-windows/).

Steps -
- Open the project root directory in your IDE.
- Run the ```main``` method located at ```src.main.java.com.example.restservice.RestServiceApplication``` class.

## Client
Prerequisites -
- Make sure Node.js is installed (https://nodejs.org/).

Steps -
- Open the project root directory in terminal.
```
C:\...\BooksAreUs>
cd client
npm install
npm start
```
- Open the browser at http://localhost:3000/.

## Notes
- Daily book changes every one minute (not 24 hours), in order to see its lifecycle in a reasonable time frame.
