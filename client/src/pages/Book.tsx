import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Book } from './../models/types';
import { api } from "../App";
import Navbar from "./NavBar";

const BookPage = () => {
  const [bookData, setBookData] = useState<Book | null>(null);
  const { id } = useParams();
  const navigate = useNavigate();
  
  useEffect(() => {
    const getBookById = async (id: string) => {
        const response = await api.getBookById(id, navigate);
        setBookData(response);
    };
    getBookById(id ?? "");
  }, []);

  return (
    <div className="App">
      <header className="App-header">    
        <Navbar/>
        <h1>{bookData?.title}</h1>
        <h2>By {bookData?.author}</h2>
        <h3>Published: {bookData?.publishingDate}</h3>
        <h3>{bookData?.rating} / 5 &#11088;</h3>
        <img src={bookData?.image} alt="Book cover."/>
      </header>
    </div>
  );
};

export default BookPage;