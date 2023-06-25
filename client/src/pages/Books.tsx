import { useEffect, useState } from "react";
import { Link, useNavigate } from 'react-router-dom';
import { api } from "../App";
import { Book } from "../models/types";
import Navbar from "./NavBar";

const PAGE_SIZE = 5;

const BookCollectionPage = () => {
  const [booksCollection, setBooksCollection] = useState<Book[] | null>(null);
  const [totalBooksCount, setTotalBooksCount] = useState<number | null>(null);
  
  const [activePage, setActivePage] = useState<number>(1);
  const navigate = useNavigate();

  useEffect(() => {
    const getBooks = async () => {
        const response = await api.getBooks(activePage, PAGE_SIZE, navigate);
        setBooksCollection(response);
    };
    getBooks();
  }, [activePage]);

  useEffect(() => {
    const getTotalBooksCount = async () => {
        const response = await api.getTotalBooksCount(navigate);
        setTotalBooksCount(response);
    };
    getTotalBooksCount();
  }, []);

  
  const handlePageClick = (pageNumber: number) => {
    setActivePage(pageNumber);
  };

  const showPageNumbers = () => {
    return (
    <div>
      {Array.from({ length: calculateMaxPageNumber()??0 }, (_, index) => {
        const pageNumber = index + 1;
        const isCurrentPage = pageNumber === activePage;

        return (
          <button
            key={pageNumber}
            onClick={() => handlePageClick(pageNumber)}
            disabled={isCurrentPage}
          >
            {pageNumber}
          </button>
        );
      })}
    </div>)
  }

  const calculateMaxPageNumber = () => {
    return totalBooksCount ? Math.ceil(totalBooksCount / PAGE_SIZE) : null;
  }

  return (
    <div className="App">
      <header className="App-header">
        <Navbar/>
        <h1>Book Collection</h1>
        {!totalBooksCount ? "Loading..." : (
          <div>
            {showPageNumbers()}
            <br/>
              {!booksCollection ? "Loading books..." : (
                <div>
                  {booksCollection?.map((book) => (
                    <Link key={book.id} to={`/book/${book.id}`}>
                      <span className="bookFrame" style={{"display": "block"}}>
                          <p>
                            <img src={book?.image} alt={`Cover of ${book.title}`}/> <br/>
                            {book.title} <br/><br/>
                          </p>
                      </span>
                    </Link>
                  ))}
                </div>
              )}
          </div>
        )}
      </header>
    </div>
  )
};

export default BookCollectionPage;