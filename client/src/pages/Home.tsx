import { useState, useEffect } from "react";
import { Book, Note } from './../models/types';
import { api } from "../App";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "./NavBar";


const HomePage = () => {
  const [latestNotes, setLatestNotes] = useState<Note[] | null>(null);
  const [dailyBook, setDailyBook] = useState<Book | null>(null);
  const navigate = useNavigate();

  const latestNotesCount = 3;

  useEffect(() => {
    const getLatestNotes = async () => {
        const response = await api.getLatestNotes(latestNotesCount, navigate);
        setLatestNotes(response);
    };
    getLatestNotes();
  }, []);

  useEffect(() => {
    const getDailyBook = async () => {
        const response = await api.getDailyBook(navigate);
        setDailyBook(response);
    };
    getDailyBook();
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <Navbar/>
        <h1>Welcome to BooksAreUs!</h1>
        <h3>The only library with no actual books &#128540;</h3>
        <h3 className="prompt">Library's Hot News:</h3>
        <div>
          {!latestNotes ? "Loading..." : (
            <ul>
              {latestNotes.map((note) => (
                <li className="note">{note.content ?? 'N.A.'}</li>
              ))}
            </ul>
          )}
        </div>
        <div>
          <h3 className="prompt">Book of the Day</h3>
          {!dailyBook ? "Loading..." : (
            <Link to={`/book/${dailyBook.id}`} style={{ textDecoration: 'none' }}>
              <span className="bookFrame" style={{"display": "block"}}>
                <div>
                  <p>
                    {dailyBook?.title} <br/>
                    By <br/>
                    {dailyBook?.author}
                  </p>
                  <img src={dailyBook?.image} alt="Book of the day cover."/>
                  </div>
              </span>
            </Link>
          )}
        </div>
      </header>
    </div>
  );
};

export default HomePage;

