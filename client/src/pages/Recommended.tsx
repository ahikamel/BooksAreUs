import { useEffect, useState } from "react";
import { Link, useNavigate } from 'react-router-dom';
import { api } from "../App";
import { Book } from "../models/types";
import Navbar from "./NavBar";

const recommendedBooksCount = 5;

const RecommendedPage = () => {
  const [recommendedBooks, setRecommendedBooks] = useState<Book[] | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const getRecommendedBooks = async () => {
        const response = await api.getRecommendedBooks(recommendedBooksCount, navigate);
        setRecommendedBooks(response);
    };
    getRecommendedBooks();
  }, []);
  
  return (
    <div className="App">
      <header className="App-header">
        <Navbar/>
        <h1>Recommended Books</h1>
        {!recommendedBooks ? "Loading..." : (
          <div>
            {recommendedBooks?.map((book) => (
              <Link key={book.id} to={`/book/${book.id}`}>
                <span className="bookFrame" style={{"display": "block"}}>
                    <p>
                      <img src={book?.image} alt={`Cover of ${book.title}`}/> <br/>
                      {book.title}
                      <br/><br/>
                    </p>
                </span>
              </Link>
            ))}
          </div>
        )}
      </header>
    </div>
  )
};

export default RecommendedPage;