import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';

import HomePage from './pages/Home';
import BookPage from './pages/Book';
import BookCollectionPage from './pages/Books';
import RecommendedPage from './pages/Recommended';
import ErrorPage from './pages/Error';
import { createApiClient } from "./api";

export const api = createApiClient();

function App() {

return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='book/:id' element={<BookPage />} />
        <Route path='books' element={<BookCollectionPage />} />
        <Route path='recommended' element={<RecommendedPage />} />
        <Route path='*' element={<ErrorPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
